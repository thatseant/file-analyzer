package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.FilterCriteria;

public class FilterDialog extends JDialog {
    private JComboBox<String> columnComboBox;
    private JComboBox<String> conditionComboBox;
    private JTextField valueField;
    private JButton okButton;
    private FilterCriteria filterCriteria;


    public FilterDialog(JFrame parent, String[] columns) {
        super(parent, "Filter", true);

        // Create UI components
        columnComboBox = new JComboBox<>(columns);
        conditionComboBox = new JComboBox<>(new String[]{"Greater Than", "Less Than"});
        valueField = new JTextField();
        okButton = new JButton("OK");

        // Layout components
        setLayout(new GridLayout(4, 2));
        add(new JLabel("Column:"));
        add(columnComboBox);
        add(new JLabel("Show Values:"));
        add(conditionComboBox);
        add(new JLabel("Value:"));
        add(valueField);
        add(okButton);

        // Add event handler for OK button
        okButton.addActionListener(e -> {
            filterCriteria = new FilterCriteria(
                    (String) columnComboBox.getSelectedItem(),
                    valueField.getText(),
                    (String) conditionComboBox.getSelectedItem());
            setVisible(false);
        });

        pack();
        setLocationRelativeTo(parent);
    }

    public FilterCriteria getFilterCriteria() {
        return filterCriteria;
    }
}