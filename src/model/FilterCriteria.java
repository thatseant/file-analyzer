package model;

public class FilterCriteria {
    private String columnName;
    private String value;
    private String condition;

    public FilterCriteria(String columnName, String value, String condition) {
        this.columnName = columnName;
        this.value = value;
        this.condition = condition;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getValue() {
        return value;
    }

    public String getCondition() {
        return condition;
    }
}
