package controller;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.FileData;
import model.FileLoader;
import model.FileLoaderFactory;
import model.FilterCriteria;
import model.SortOptions;
import view.FileAnalyzerView;

public class FileAnalyzerController {
    private FileAnalyzerView view;
    private FileData fileData;

    public FileAnalyzerController(FileAnalyzerView view) {
        this.view = view;
        this.view.getOpenButton().addActionListener(e -> openFile());
        this.view.getSortButton().addActionListener(e -> sortData());
        this.view.getFilterButton().addActionListener(e -> filterData());
        this.view.getStatsButton().addActionListener(e -> showStats());
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
        fileChooser.setFileFilter(filter);
        
        int returnValue = fileChooser.showOpenDialog(view);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            FileLoader fileLoader = FileLoaderFactory.getFileLoader("CSV"); //Allow for multiple file types in future
            
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