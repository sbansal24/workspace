package com.publicis.generator;

import com.publicis.model.FileModel;

import java.io.File;

public interface FileGenerator {
    boolean generateReport(FileModel fileModel, File file, String directoryPath);
    void setNextReportGenerator(FileGenerator nextProcess);
}
