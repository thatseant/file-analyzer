package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class FileData {
    private final ArrayList<RowData> data;
    private final ArrayList<RowData> originalData;
    private String[] headers;
    private HashMap<String, Integer> stats;

    public FileData() {
        this.data = new ArrayList<>();
        this.originalData = new ArrayList<>();
        this.stats = new HashMap<>();
    }

    public void addRow(RowData row) {
        data.add(row);
        originalData.add(row);
    }

    public ArrayList<RowData> getData() {
        return data;
    }

    public void resetData() {
        data.clear();
        data.addAll(originalData);
    }

    public void setHeaders(String[] headers) {
      this.headers = headers;
    }

    public String[] getHeaders() {
      return headers;
    }

    public int getTotalLetters() {
      return this.stats.get("totalLetters");
    }

    public int getTotalWords() {
      return this.stats.get("totalWords");
    }

    public void calculateStats() {

        //Currently, calculate totalWords and totalLetters
        int totalWords = 0;
        int totalLetters = 0;
        for (RowData rowData : data) {
            for (String header : headers) {
                Object value = rowData.getColumnValue(header);
                if (value instanceof String) {
                    String stringValue = (String) value;
                    totalWords += stringValue.split("\\s+").length;
                    totalLetters += stringValue.replaceAll("\\s+", "").length();
                }
            }
        }
        
        this.stats.put("totalWords", totalWords);
        this.stats.put("totalLetters", totalLetters);
    }

    public void sort(String selectedColumn, String sortOrder) {
        data.sort((o1, o2) -> {
            Comparable val1 = (Comparable) o1.getColumnValue(selectedColumn);
            Comparable val2 = (Comparable) o2.getColumnValue(selectedColumn);
            int comparison = val1.compareTo(val2);
            return sortOrder.equals("Ascending") ? comparison : -comparison;
        });
    }

    public void filter(FilterCriteria criteria) {
        data.removeIf(rowData -> {
            Object columnValue = rowData.getColumnValue(criteria.getColumnName());
            if (columnValue instanceof Comparable) {
                Comparable comparableColumnValue = (Comparable) columnValue;
                String filterValueString = criteria.getValue();
                Comparable filterValue;
                if (columnValue instanceof Boolean) {
                    filterValue = Boolean.parseBoolean(filterValueString);
                } else if (columnValue instanceof Integer) {
                    filterValue = Integer.parseInt(filterValueString);
                } else if (columnValue instanceof Double) {
                    filterValue = Double.parseDouble(filterValueString);
                } else if (columnValue instanceof Date) {
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        filterValue = dateFormat.parse(filterValueString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        return false;
                    }
                } else {
                    filterValue = filterValueString;
                }
                int comparison = comparableColumnValue.compareTo(filterValue);
                return (criteria.getCondition().equalsIgnoreCase("Greater Than") && comparison <= 0)
                        || (criteria.getCondition().equalsIgnoreCase("Less Than") && comparison >= 0);
            }
            return false;
        });
    }


}