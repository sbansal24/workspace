package com.publicis.processor;

import com.publicis.cache.ReportCache;
import com.publicis.config.Configuration;
import com.publicis.model.Status;
import com.publicis.utils.ReportUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class DirectoryProcessor implements Runnable
{
	private String directoryPath;
	private long startTime = -1L;
	private StringBuffer errMsg;
	private ReportCache reportCache;
	private HashMap<String, File> currentFileMap = new HashMap<>();
	private ExecutorService executorService = null;
    private Configuration configuration;
    
	public DirectoryProcessor(StringBuffer errMsg) 
	{
		//Initializing configuration object
		this.configuration = Configuration.getInstance();
		this.directoryPath = configuration.getDirectoryPath();
		this.errMsg = errMsg;
		Thread thread = new ReportCache();
		thread.start();
		reportCache = (ReportCache) thread;
	}

	private void stopExecutor() {
		if (executorService != null)
			executorService.shutdown();

		reportCache.setToBeStopped(true);
	}

	public void run() {
		System.out.println("Starting directory Processor");

		if (!ReportUtils.validateDirectory(directoryPath)) {
			errMsg.append("Given directory path(" + this.directoryPath + ") is invalid");
			System.err.println("Given directory is invalid");
			return;
		}

		startTime = System.currentTimeMillis();

		try {
			while (true) {

				currentFileMap.clear();

				Status searchStatus = searchFilesInReportDirectories();

				if (searchStatus == Status.FOUND) {
					if (executorService == null) {
						
						//Creating cached threadPool due to using IO Task
						executorService = Executors.newCachedThreadPool();
					}

					Set<String> fileSet = currentFileMap.keySet();

					for (String currentFile : fileSet) {
						File currentFileObj = currentFileMap.get(currentFile);

						System.out.println("Submitting File(" + currentFile + ") to processing ...");
						executorService.execute(new FileProcessor(currentFileObj.getAbsolutePath(),
								currentFileObj.getParentFile().getAbsolutePath(), reportCache));
					}
				}

				if (searchStatus == Status.EXIT) {
					errMsg.append("Directory Process is stopped for Directory:" + this.directoryPath + ".");
					System.err.println("Directory Process is stopped for Directory:" + this.directoryPath + ".");

					return;
				}

				System.out.println("Directory Processor is sleeping for " + configuration.getRefreshInterval() + " ms");
				Thread.sleep(configuration.getRefreshInterval());
			}
		} catch (InterruptedException ie) {
			System.err.println("Directory Processor has been interrupted due to error: " + ie.getMessage());
		} catch (Exception e) {
			System.err.println(
					"Caught exception in processing directory(" + directoryPath + "). Error: " + e.getMessage());
		}
		finally {
			stopExecutor();
		}
	}

	public void searchFileInDir(String directoryPath, List<File> files) {
		File directory = new File(directoryPath);
		File[] fileList = directory.listFiles();

		if (fileList != null) {
			for (File file : fileList) {
				if (file.isFile()) {
					files.add(file);
				} else if (file.isDirectory()) {
					searchFileInDir(file.getAbsolutePath(), files);
				}
			}
		}
	}

	/*
	 * This method will search all files in given directory as well as in sub-directory
	 */
	private Status searchFilesInReportDirectories()
	{
		System.out.println("Method called for searching new Files in directory:" + directoryPath);

		if (!ReportUtils.validateDirectory(directoryPath))
			return Status.EXIT;

		List<File> fileList = new ArrayList<>();

		searchFileInDir(directoryPath, fileList);
		
		Stream<File> fileListStream = fileList.stream();
		
		/*
		 * Filter conditions
		 *   only accepted csv,txt format
		 *   should not be in cache
		 *   should not be old files
		 */
		fileListStream.filter(fileObj -> ReportUtils.isExtMatch(fileObj.getName()))
		               .filter(fileObj -> fileObj.lastModified() >= startTime)
		               .filter(fileObj -> reportCache.get(fileObj.getAbsolutePath()) == null)
		               .forEach(fileObj -> currentFileMap.put(fileObj.getAbsolutePath(), fileObj));

		if (currentFileMap.size() > 0) {
			System.out.println("Following files need to be processed + " + currentFileMap.keySet());
			return Status.FOUND;
		} else {
			System.out.println("No files founds to be processed for directory" + directoryPath);
			return Status.NOT_FOUND;
		}
	}
}
