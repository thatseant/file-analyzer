package controller;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.FileData;
import model.FileLoader;
import model.FilterCriteria;
import model.SortOptions;
import view.FileAnalyzerView;

public class FileAnalyzerController {
    private FileAnalyzerView view;
    private FileData fileData;
    private FileLoader fileLoader;

    public FileAnalyzerController(FileAnalyzerView view, FileLoader fileLoader) {
        this.view = view;
        this.fileLoader = fileLoader;
        this.view.getOpenButton().addActionListener(e -> openFile());
        this.view.getSortButton().addActionListener(e -> sortData());
        this.view.getStatsButton().addActionListener(e -> showStats());
        this.view.getFilterButton().addActionListener(e -> filterData());
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fileChooser.setFileFilter(filter);
        
        int returnValue = fileChooser.showOpenDialog(view);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            this.fileData = fileLoader.loadFile(selectedFile);
            view.updateTable(this.fileData);
        }
    }


        private void sortData() {
            SortOptions sortOptions = view.showSortDialog(fileData.getHeaders());
            if (sortOptions != null) {
                fileData.resetData();  // Reset data before sorting
                fileData.sort(sortOptions.getSelectedColumn(), sortOptions.getSortOrder());
                view.updateTable(fileData);
            }
        }


private void filterData() {
    FilterCriteria criteria = view.showFilterDialog(fileData.getHeaders());
    if (criteria != null) {
        if (criteria.getValue() == null || criteria.getValue().trim().isEmpty()) {
            // If value is blank, reset filter
            fileData.resetData();
        } else {
            fileData.resetData();  // Reset data before filtering
            fileData.filter(criteria);
        }
        view.updateTable(fileData);
    }
}


    private void showStats() {
        fileData.calculateStats();
        int totalWords = fileData.getTotalWords();
        int totalLetters = fileData.getTotalLetters();
        view.showStatsDialog(totalWords, totalLetters);
    }
}