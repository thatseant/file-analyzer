package model;

public class FileLoaderFactory {

  public static FileLoader getFileLoader(String fileType) {
    switch (fileType.toLowerCase()) {
      case "csv":
        return new CSVFileLoader();
      default:
        return null;
    }
  }

}
