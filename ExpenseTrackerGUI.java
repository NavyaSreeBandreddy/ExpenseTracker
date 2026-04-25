import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ExpenseTrackerGUI extends JFrame {

    ExpenseManager manager = new ExpenseManager();

    JTextField titleField, amountField, categoryField, dateField;
    JLabel totalLabel;
    DefaultTableModel tableModel;
    JTable table;

    public ExpenseTrackerGUI() {
        setTitle("Expense Tracker");
        setSize(750, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //  Top Panel (Form)
        JPanel panel = new JPanel(new GridLayout(2, 5, 5, 5));
        panel.setBackground(Color.LIGHT_GRAY);

        titleField = new JTextField();
        amountField = new JTextField();
        categoryField = new JTextField();
        dateField = new JTextField();

        JButton addBtn = new JButton("Add Expense");
        addBtn.setBackground(Color.GREEN);

        panel.add(new JLabel("Title"));
        panel.add(new JLabel("Amount"));
        panel.add(new JLabel("Category"));
        panel.add(new JLabel("Date"));
        panel.add(new JLabel(""));

        panel.add(titleField);
        panel.add(amountField);
        panel.add(categoryField);
        panel.add(dateField);
        panel.add(addBtn);

        add(panel, BorderLayout.NORTH);

        //  Table
        tableModel = new DefaultTableModel(
                new String[]{"Title", "Amount", "Category", "Date"}, 0);
        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);

        //  Bottom Panel
        JPanel bottom = new JPanel();

        JButton deleteBtn = new JButton("Delete");
        JButton chartBtn = new JButton("Show Chart ");
        totalLabel = new JLabel("Total: ₹0");

        bottom.add(deleteBtn);
        bottom.add(chartBtn);
        bottom.add(totalLabel);

        add(bottom, BorderLayout.SOUTH);

        //  Add Button Action
        addBtn.addActionListener(e -> {
            try {
                String title = titleField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String category = categoryField.getText();
                String date = dateField.getText();

                if (title.isEmpty() || category.isEmpty() || date.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields!");
                    return;
                }

                Expense exp = new Expense(title, amount, category, date);
                manager.addExpense(exp);

                tableModel.addRow(new Object[]{title, amount, category, date});
                updateTotal();

                clearFields();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid amount!");
            }
        });

        //  Delete Button
        deleteBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                manager.removeExpense(selectedRow);
                tableModel.removeRow(selectedRow);
                updateTotal();
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to delete!");
            }
        });

        //  Chart Button
        chartBtn.addActionListener(e -> {
            if (manager.getAllExpenses().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No data to show!");
                return;
            }

            ChartWindow chart = new ChartWindow(manager.getCategoryTotals());
            chart.setVisible(true);
        });
    }

    void updateTotal() {
        totalLabel.setText("Total: ₹" + manager.getTotal());
    }

    void clearFields() {
        titleField.setText("");
        amountField.setText("");
        categoryField.setText("");
        dateField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ExpenseTrackerGUI().setVisible(true);
        });
    }
}