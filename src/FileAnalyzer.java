import javax.swing.*;

import controller.FileAnalyzerController;
import model.CSVFileLoader;
import model.FileLoader;
import view.FileAnalyzerView;

public class FileAnalyzer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FileAnalyzerView view = new FileAnalyzerView();
            FileLoader fileLoader = new CSVFileLoader();
            FileAnalyzerController controller = new FileAnalyzerController(view, fileLoader);
            view.setVisible(true);
        });
    }
}
