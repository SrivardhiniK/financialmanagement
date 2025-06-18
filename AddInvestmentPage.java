import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddInvestmentPage extends JFrame {
    private JTextField investmentNameField, investmentTypeField, quantityField, purchasePriceField, currentPriceField;
    private JButton saveButton, backButton;
    private String username;

    public AddInvestmentPage(String username) {
        this.username = username;

        setTitle("Add New Investment");
        setBounds(100, 100, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Layout
        Container container = getContentPane();
        container.setLayout(null);

        JLabel investmentNameLabel = new JLabel("Investment Name:");
        investmentNameLabel.setBounds(50, 50, 150, 30);
        container.add(investmentNameLabel);

        investmentNameField = new JTextField();
        investmentNameField.setBounds(200, 50, 150, 30);
        container.add(investmentNameField);

        JLabel investmentTypeLabel = new JLabel("Investment Type:");
        investmentTypeLabel.setBounds(50, 100, 150, 30);
        container.add(investmentTypeLabel);

        investmentTypeField = new JTextField();
        investmentTypeField.setBounds(200, 100, 150, 30);
        container.add(investmentTypeField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(50, 150, 150, 30);
        container.add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(200, 150, 150, 30);
        container.add(quantityField);

        JLabel purchasePriceLabel = new JLabel("Purchase Price:");
        purchasePriceLabel.setBounds(50, 200, 150, 30);
        container.add(purchasePriceLabel);

        purchasePriceField = new JTextField();
        purchasePriceField.setBounds(200, 200, 150, 30);
        container.add(purchasePriceField);

        JLabel currentPriceLabel = new JLabel("Current Price:");
        currentPriceLabel.setBounds(50, 250, 150, 30);
        container.add(currentPriceLabel);

        currentPriceField = new JTextField();
        currentPriceField.setBounds(200, 250, 150, 30);
        container.add(currentPriceField);

        // Save button
        saveButton = new JButton("Save Investment");
        saveButton.setBounds(50, 300, 150, 30);
        saveButton.addActionListener(e -> saveInvestment());
        container.add(saveButton);

        // Back button
        backButton = new JButton("Back to Dashboard");
        backButton.setBounds(220, 300, 150, 30);
        backButton.addActionListener(e -> {
            dispose();
            new Dashboard(username);  // Return to dashboard
        });
        container.add(backButton);

        setVisible(true);
    }

    private void saveInvestment() {
        String investmentName = investmentNameField.getText();
        String investmentType = investmentTypeField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double purchasePrice = Double.parseDouble(purchasePriceField.getText());
        double currentPrice = Double.parseDouble(currentPriceField.getText());

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb", "root", "srivakrish@1234");

            // SQL query to insert new investment
            String sql = "INSERT INTO portfolio (username, investment_name, investment_type, quantity, purchase_price, current_price, date_of_investment) VALUES (?, ?, ?, ?, ?, ?, NOW())";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, investmentName);
            pst.setString(3, investmentType);
            pst.setInt(4, quantity);
            pst.setDouble(5, purchasePrice);
            pst.setDouble(6, currentPrice);

            pst.executeUpdate(); // Execute the insert
            JOptionPane.showMessageDialog(this, "Investment Added Successfully!");

            // Clear fields after adding investment
            investmentNameField.setText("");
            investmentTypeField.setText("");
            quantityField.setText("");
            purchasePriceField.setText("");
            currentPriceField.setText("");

            // Close the connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AddInvestmentPage("testuser");  // For testing
    }
}

