package model;

public class SortOptions {
    private String selectedColumn;
    private String sortOrder;

    public SortOptions(String selectedColumn, String sortOrder) {
        this.selectedColumn = selectedColumn;
        this.sortOrder = sortOrder;
    }

    public String getSelectedColumn() {
        return selectedColumn;
    }

    public String getSortOrder() {
        return sortOrder;
    }
}
