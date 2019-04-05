package com.publicis.generator;

import com.publicis.model.FileModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SingleFileReportGenerator implements FileGenerator {
    private FileGenerator nextProcess;

    @Override
    public void setNextReportGenerator(FileGenerator nextProcess) {
        this.nextProcess = nextProcess;
    }

    @Override
    public boolean generateReport(FileModel fileModel, File fileObj, String directoryPath) {
        FileWriter fileWriter = null;
        BufferedWriter writer = null;

        if (fileObj.exists())
            fileObj.delete();

        try {
            fileWriter = new FileWriter(fileObj);
            writer = new BufferedWriter(fileWriter);
            writer.write(fileModel.printReport());
            writer.flush();

            System.out.println("Report file(" + fileObj.getAbsolutePath() + ") is generated " + directoryPath + ".");
            nextProcess.generateReport(fileModel, fileObj, directoryPath);
            return true;

        } catch (IOException e) {
            System.err.println("Exception writing file = " + fileObj.getAbsolutePath() + " \n" + e.getMessage());
            return false;
        } finally {
            try {
                if (writer != null)
                    writer.close();
                if (fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


}
