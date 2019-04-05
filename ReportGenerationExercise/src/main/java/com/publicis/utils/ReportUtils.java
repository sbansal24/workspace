package com.publicis.utils;

import java.io.File;

public class ReportUtils {
    public static boolean isExtMatch(String fileName) {
        String[] supportedFileFormat = {".csv", ".txt"};
        boolean isExtMatch = false;
        for (String ext : supportedFileFormat) {
            if (fileName.endsWith(ext)) {
                isExtMatch = true;
                break;
            }
        }
        return isExtMatch;
    }

    public static boolean validateDirectory(String directoryPath) {
        File directoryFileObj = new File(directoryPath);

        if (!directoryFileObj.exists() || !directoryFileObj.isDirectory())
            return false;

        return true;
    }
}
