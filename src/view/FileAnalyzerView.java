package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.FileData;
import model.FilterCriteria;
import model.RowData;
import model.SortOptions;

public class FileAnalyzerView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton openButton;
    private JButton sortButton;
    private JButton statsButton;
    private JButton filterButton;

    public FileAnalyzerView() {

        // Create UI components
        this.tableModel = new DefaultTableModel();
        this.table = new JTable(this.tableModel);
        this.openButton = new JButton("Open");
        this.sortButton = new JButton("Sort");
        this.statsButton = new JButton("Stats");
        this.filterButton = new JButton("Filter");


        // Layout components
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.add(openButton);
        panel.add(sortButton);
        panel.add(statsButton);
        panel.add(filterButton);
        add(panel, BorderLayout.SOUTH);
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public SortOptions showSortDialog(String[] headers) {
        JComboBox<String> columnBox = new JComboBox<>(headers);
        JComboBox<String> orderBox = new JComboBox<>(new String[]{"Ascending", "Descending"});

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Select column to sort by:"));
        panel.add(columnBox);
        panel.add(new JLabel("Select sort order:"));
        panel.add(orderBox);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Sort",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String selectedColumn = (String) columnBox.getSelectedItem();
            String sortOrder = (String) orderBox.getSelectedItem();
            return new SortOptions(selectedColumn, sortOrder);
        }
        return null;
    }



    public void showStatsDialog(int totalWords, int totalLetters) {
        JOptionPane.showMessageDialog(this,
                "Total Words: " + totalWords + "\nTotal Letters: " + totalLetters,
                "Statistics",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public FilterCriteria showFilterDialog(String[] headers) {
        FilterDialog filterDialog = new FilterDialog(this, headers);
        filterDialog.setVisible(true);
        return filterDialog.getFilterCriteria();
    }

    public JButton getOpenButton() {
        return openButton;
    }

    public JButton getSortButton() {
        return sortButton;
    }

    public JButton getStatsButton() {
        return statsButton;
    }

    public JButton getFilterButton() {
      return filterButton;
    }

public void updateTable(FileData fileData) {
    tableModel.setRowCount(0);
    tableModel.setColumnIdentifiers(fileData.getHeaders());

    ArrayList<RowData> data = fileData.getData();
    for (RowData rowData : data) {
        Vector<String> rowVector = new Vector<>();
        for (String header : fileData.getHeaders()) {
            // Converting each value to String for display purposes
            rowVector.add(String.valueOf(rowData.getColumnValue(header)));
        }
        tableModel.addRow(rowVector);
    }
}

}

