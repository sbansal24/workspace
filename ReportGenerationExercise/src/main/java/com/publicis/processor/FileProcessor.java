package com.publicis.processor;

import com.publicis.cache.ReportCache;
import com.publicis.generator.AggregateReportGenerator;
import com.publicis.generator.FileGenerator;
import com.publicis.generator.SingleFileReportGenerator;
import com.publicis.model.FileModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class FileProcessor implements Runnable {
	private String filePath;
	private String fileName;
	private String directoryPath;
	private ReportCache reportCache;
	private final String wordSeparator = " ";

	public FileProcessor(String filePath, String dirPath, ReportCache reportCache) {
		this.filePath = filePath;
		this.directoryPath = dirPath;
		this.reportCache = reportCache;
	}

	public void run() {
		System.out.println("Starting process file = " + filePath + " for directory = " + directoryPath);

		File file = new File(filePath);

		if (!file.exists()) {
			System.out.println("File(" + filePath + ") does not exit");
			return;
		}

		fileName = file.getName();

		FileModel fileModel = readAndProcessFile();

		if (fileModel == null) {
			System.err.println("File(" + filePath + ") is not processed successfully");
			return;
		}

		System.out.println("Report for File(" + filePath + "): " + fileModel);

		String sourceFileName = fileName.replace("csv", "mtd").replace("txt", "mtd");

		File sourceFilePath = new File(directoryPath + File.separator + sourceFileName);

		System.out.println("Source Report File to Write : " + sourceFilePath);

		FileGenerator metaReportGenerator = new SingleFileReportGenerator();
		FileGenerator aggregateReportGenerator = new AggregateReportGenerator();
		
		metaReportGenerator.setNextReportGenerator(aggregateReportGenerator);

		metaReportGenerator.generateReport(fileModel, sourceFilePath, directoryPath);

		reportCache.put(filePath, file.lastModified());
	}

	private FileModel readAndProcessFile() {
		FileReader fileReader = null;
		BufferedReader reader = null;

		try {
			fileReader = new FileReader(filePath);
			reader = new BufferedReader(fileReader);

			FileModel fileModel = new FileModel(filePath);

			String dataLine = null;
			while ((dataLine = reader.readLine()) != null) {
				if (dataLine.length() == 0)
					continue;

				String[] words = dataLine.split(wordSeparator);

				fileModel.addWords(words.length);

				Stream<String> wordStream = Stream.of(words);

				wordStream.forEach(word -> {
					Stream<Character> stream = word.chars().mapToObj(c -> (char) c);

					stream.forEach(ch -> {

						// a,i,e,o,u
						if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
							ch = Character.toLowerCase(ch);

							if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
								fileModel.addVowel();
						}

						// @, #, $, *
						if (ch == '@' || ch == '#' || ch == '$' || ch == '*')
							fileModel.addSpecialChar();

					});
				});
			}

			return fileModel;

		} catch (IOException e) {
			System.err.println("Caught exception in reading file = " + filePath + ", Error : " + e.getMessage());
			return null;
		} finally {
			try {
				if (reader != null)
					reader.close();
				if (fileReader != null)
					fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
