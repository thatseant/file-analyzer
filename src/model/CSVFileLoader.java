package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVFileLoader implements FileLoader {
    @Override
    public FileData loadFile(File file) {
        FileData fileData = new FileData();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line != null) {
                String[] headerValues = line.split(",");
                fileData.setHeaders(headerValues);
                while ((line = br.readLine()) != null) {
                  String[] rowValues = line.split(",");
                    RowData rowData = new RowData();
                    for (int i = 0; i < rowValues.length; i++) {
                        rowData.addColumn(fileData.getHeaders()[i], rowValues[i].trim());
                    }
                    fileData.addRow(rowData);
                  }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData;
    }
}