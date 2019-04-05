package com.publicis;

import com.publicis.config.Configuration;
import com.publicis.processor.DirectoryProcessor;

public class ReadFileCacheMain {

    private Configuration configuration;

    private ReadFileCacheMain(ReportBuilder reportBuilder) {
        configuration = Configuration.getInstance();
        configuration.setDirectoryPath(reportBuilder.directoryPath);
        configuration.setRefreshInterval(reportBuilder.refreshInterval);
        configuration.setTimeToLive(reportBuilder.timeToLive);
    }


    public static class ReportBuilder {
        private String directoryPath;
        private long timeToLive;
        private long refreshInterval;

        public ReportBuilder(String directoryPath) {
            this.directoryPath = directoryPath;
        }

        public ReportBuilder setTimeToLive(long timeToLive) {
            this.timeToLive = timeToLive;
            return this;
        }

        public ReportBuilder setRefreshInterval(long refreshInterval) {
            this.refreshInterval = refreshInterval;
            return this;
        }

        public ReadFileCacheMain build() {
            return new ReadFileCacheMain(this);
        }
    }

    public void startDirectoryProcessor() {
        StringBuffer errMsg = new StringBuffer();

        Thread processor = new Thread(new DirectoryProcessor(errMsg), "Directory Processor");
        processor.start();

        try {
            processor.join();
        } catch (InterruptedException e) {
            System.out.println("Directory Processor is got interrupted. Error: " + e.getMessage());
        }

        System.out.println("Directory Processor is got exit.");
    }

    /*
     * Following command line arguments are required At First Index: Directory Path
     * is mandatory
     */
    public static void main(String args[]) {
//        if (args == null || args.length == 0) {
//            System.err.println("You need to provide a path to watch!");
//            System.exit(-1);
//        }
//
//        Path p = Paths.get(args[0]);
//        if (!Files.isDirectory(p)) {
//            System.err.println(p + " is not a directory!");
//            System.exit(-1);
//        }

//        String directoryPath = args[0];
        String directoryPath = "D:\\Wellington";

        long ttl = 60 * 1000;
        long refreshInterval = 10 * 1000;


        ReadFileCacheMain report = new ReadFileCacheMain.ReportBuilder(directoryPath)
                .setTimeToLive(ttl)
                .setRefreshInterval(refreshInterval)
                .build();

        report.startDirectoryProcessor();
    }
}
