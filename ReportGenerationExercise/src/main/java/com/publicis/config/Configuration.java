package com.publicis.config;

public class Configuration {
    private String directoryPath;
    private long timeToLive = 60 * 1000;
    private long refreshInterval = 10 * 1000;

    private static volatile Configuration configuration = null;

    private Configuration() {
    }

    public static Configuration getInstance() {
        if (configuration == null) {
            synchronized (Configuration.class) {
                if (configuration == null) {
                    configuration = new Configuration();
                }
            }
        }
        return configuration;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public long getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public long getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(long refreshInterval) {
        this.refreshInterval = refreshInterval;
    }

    @Override
    public String toString() {
        return "Configuration [directoryPath=" + directoryPath + ", timeToLive=" + timeToLive + ", refreshInterval="
                + refreshInterval + "]";
    }
}
