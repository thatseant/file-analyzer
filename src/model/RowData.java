package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class RowData {
    //Stored as Object reference to support multiple data types, actual object can be cast to Comparable later
    private final Map<String, Object> columns = new HashMap<>();

public void addColumn(String header, String value) {
    Object convertedValue;
    try {
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            // Boolean
            convertedValue = Boolean.parseBoolean(value);
        } else if (value.matches("\\d+")) {
            // Integer
            convertedValue = Integer.parseInt(value);
        } else if (value.matches("\\d+\\.\\d+")) {
            // Double
            convertedValue = Double.parseDouble(value);
        } else if (value.matches("\\d{4}-\\d{2}-\\d{2}")){
            // Try parsing as a date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            convertedValue = dateFormat.parse(value);
        } else {
            convertedValue = value;
        }
    } catch (ParseException e) {
        // Default to String
        convertedValue = value;
        e.printStackTrace();
    }
    columns.put(header, convertedValue);
}


    public Object getColumnValue(String header) {
        return columns.get(header);
    }
}
