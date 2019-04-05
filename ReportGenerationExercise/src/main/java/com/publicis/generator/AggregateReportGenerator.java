package com.publicis.generator;

import com.publicis.model.FileModel;

import java.io.*;

public class AggregateReportGenerator implements FileGenerator {
    private FileGenerator nextProcess;

    @Override
    public void setNextReportGenerator(FileGenerator nextProcess) {
        this.nextProcess = nextProcess;
    }

    @Override
    public boolean generateReport(FileModel fileModel, File file, String directoryPath) {
        File currentDirectory = new File(directoryPath);

        String aggregateDirPath = directoryPath + File.separator + currentDirectory.getName() + ".dmtd";

        File aggDirObj = new File(aggregateDirPath);

        if (!aggDirObj.exists())
            aggDirObj.mkdir();

        File[] listFiles = currentDirectory.listFiles(pathname -> pathname.getName().endsWith(".mtd"));


        String aggregateReportFileName = "aggrgateReport.mtd";

        FileModel aggrfileModel = new FileModel(aggregateReportFileName);

        for (File currentFile : listFiles) {
            readMTDReportFile(currentFile, aggrfileModel);
        }

        writeReportFile(aggrfileModel, aggDirObj + File.separator + aggregateReportFileName);

        return false;
    }

    private void readMTDReportFile(File file, FileModel fileModel) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String dataLine = null;

            while ((dataLine = bufferedReader.readLine()) != null) {
                String[] arrData = dataLine.split("=");

                if (arrData[0].equals("Words"))
                    fileModel.setWords(fileModel.getWords() + Integer.parseInt(arrData[1]));

                if (arrData[0].equals("Vowels"))
                    fileModel.setVowels(fileModel.getVowels() + Integer.parseInt(arrData[1]));

                if (arrData[0].equals("Special Characters"))
                    fileModel.setSpecialChars(fileModel.getSpecialChars() + Integer.parseInt(arrData[1]));
            }
        } catch (IOException e) {
            System.err.println("Cuaght exception in reading file(" + file.getAbsolutePath() + ", Error : " + e.getMessage());
            return;
        } finally {
            closeReadResources(fileReader, bufferedReader);
        }
    }

    private boolean writeReportFile(FileModel fileModel, String filePath) {

        FileWriter fileWriter = null;
        BufferedWriter writer = null;

        File file = new File(filePath);

        if (file.exists())
            file.delete();

        try {
            fileWriter = new FileWriter(file);
            writer = new BufferedWriter(fileWriter);
            writer.write(fileModel.printReport());
            writer.flush();

            System.out.println("Report file(" + filePath + ") is generated successfully in directory "
                    + file.getParentFile().getAbsolutePath() + ".");

            return true;
        } catch (IOException e) {
            System.err.println(
                    "Caught exception in writing report file = " + filePath + ", Error : " + e.getMessage());
            e.printStackTrace();
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

    public void closeReadResources(FileReader fileReader, BufferedReader bufferedReader) {
        try {
            if (bufferedReader != null)
                bufferedReader.close();

            if (fileReader != null)
                fileReader.close();
        } catch (Exception e) {
            System.out.println("Caught Exception in closing reeaders : " + e.getMessage());
        }
    }

}
