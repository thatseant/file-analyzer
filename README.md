# This File Analyzer Application is written in Java and built with the MVC (Model-View-Controller) architecture. Users to load, sort, filter and analyze CSV files.

## Features:

### 1. **Load CSV Files:**
   - Users can open CSV files (values themselves should not contain commas).
   - Recognizes and detects `Integer`, `Double`, and `Date` ("yyyy-MM-dd" format) data types for each column.
   - All other values are treated as `Strings`.

### 2. **Sort Columns:**
   - The sort button triggers a dialog box for column selection and order preference (ascending or descending).

### 3. **Filter Columns:**
   - The filter button allows column filtering for values less than or greater than a specified user-inputted value.
   - Not inputting a value resets the data to its original state.

### 4. **Analyze All Data:**
   - The analyze button allows users to view the number of words or letters in the file.

## Running the File:

Input the following commands in the root directory of file-analyzer.

```bash
javac -d bin src/*.java src/model/*.java src/view/*.java src/controller/*.java
java -cp bin FileAnalyzer
```

## Suggested Enhancements:

Enhancements can be easily made due to the clear separation of concerns and modularity of the program with separate classes and methods for different functionalities. For ease of packing, unit tests were not included to avoid having to include JUnit and Mockito in program's dependencies.

### 1. Analyze Data functionality can be improved to show more relevant statistics for columns. Similar to a spreadsheet program, average, min, max, sum or other functions for a column can be shown.

### 2. File can be directly edited and saved in the File Analyzer program.