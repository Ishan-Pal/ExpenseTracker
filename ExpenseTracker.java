import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.*;

class ExpenseTracker extends JFrame {
    private JLabel expenseLabel, incomeLabel, balanceLabel;
    private JTextField expenseTextField, incomeTextField, balanceTextField;
    private JButton addButton, generateReportButton;
    private JTextArea reportTextArea;

    private ArrayList<Integer> expenses = new ArrayList<>();
    private ArrayList<Integer> incomes = new ArrayList<>();

    public ExpenseTracker() {
        setTitle("Expense Tracker");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        add(mainPanel);

        JPanel expensePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        expenseLabel = new JLabel("Expense:");
        expenseTextField = new JTextField(10);
        expensePanel.add(expenseLabel);
        expensePanel.add(expenseTextField);

        JPanel incomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        incomeLabel = new JLabel("Income:");
        incomeTextField = new JTextField(10);
        incomePanel.add(incomeLabel);
        incomePanel.add(incomeTextField);

        JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        balanceLabel = new JLabel("Balance:");
        balanceTextField = new JTextField(10);
        balanceTextField.setEditable(false);
        balancePanel.add(balanceLabel);
        balancePanel.add(balanceTextField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int expense = Integer.parseInt(expenseTextField.getText());
                    expenses.add(expense);
                    expenseTextField.setText("");
                    int income = Integer.parseInt(incomeTextField.getText());
                    incomes.add(income);
                    incomeTextField.setText("");
                    int balance = calculateBalance();
                    balanceTextField.setText(Integer.toString(balance));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ExpenseTracker.this, "Please enter a valid number");
                }
            }
        });
        generateReportButton = new JButton("Generate Report");
        generateReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reportTextArea.setText("");
                reportTextArea.append("Expenses:\n");
                for (int expense : expenses) {
                    reportTextArea.append(Integer.toString(expense) + "\n");
                }
                reportTextArea.append("\nIncomes:\n");
                for (int income : incomes) {
                    reportTextArea.append(Integer.toString(income) + "\n");
                }
                reportTextArea.append("\nBalance: " + balanceTextField.getText());
            }
        });
        buttonPanel.add(addButton);
        buttonPanel.add(generateReportButton);

        JPanel reportPanel = new JPanel(new BorderLayout());
        reportTextArea = new JTextArea(20, 30);
        reportTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportTextArea);
        reportPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(expensePanel);
        mainPanel.add(incomePanel);
        mainPanel.add(balancePanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(reportPanel);

        setVisible(true);
    }

    private int calculateBalance() {
        int totalExpenses = 0;
        for (int expense : expenses) {
            totalExpenses += expense;
        }
        int totalIncomes = 0;
        for (int income : incomes) {
            totalIncomes += income;
        }
        return totalIncomes - totalExpenses;
    }

    public static void main(String[] args) {
        new ExpenseTracker();
    }
}
