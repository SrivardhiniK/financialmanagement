import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Portfolioperformance extends JFrame {
    private JTable portfolioTable;
    private String username;  // Changed from userId to username
    private Image backgroundImage;

    public Portfolioperformance(String username) {  // Constructor takes username
        this.username = username;
        setTitle("Portfolio Performance");
        setBounds(300, 90, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Load background image
        try {
            backgroundImage = ImageIO.read(new File("financialbackground.jpeg")); // Path to your background image
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Custom JPanel to paint the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        // Table to display portfolio with performance metrics
        String[] columnNames = {"Investment Name", "Investment Type", "Quantity", "Purchase Price", "Current Price", "Performance (%)", "Date of Investment"};
        Object[][] data = fetchPortfolioData(username); // Fetch data using username

        portfolioTable = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(portfolioTable);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        // Back button to return to the dashboard
        JButton backButton = createStyledButton("Back to Dashboard", new Color(135, 206, 235)); // Light Sky Blue
        backButton.addActionListener(e -> {
            dispose();
            new Dashboard(username); // Return to the dashboard
        });
        backgroundPanel.add(backButton, BorderLayout.SOUTH);

        // Calculate and display total portfolio value and performance
        displayPortfolioSummary(username);

        setVisible(true);
    }

    // Method to fetch portfolio data along with performance based on username
    private Object[][] fetchPortfolioData(String username) {
        List<Object[]> list = new ArrayList<>();
        try {
            // Load JDBC driver and connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb", "root", "srivakrish@1234");

            // SQL query to fetch portfolio data
            String sql = "SELECT investment_name, investment_type, quantity, purchase_price, current_price, (current_price - purchase_price) / purchase_price * 100 AS performance, date_of_investment FROM portfolio WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);

            // Execute the query
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("investment_name"),
                    rs.getString("investment_type"),
                    rs.getInt("quantity"),
                    rs.getBigDecimal("purchase_price"),
                    rs.getBigDecimal("current_price"),
                    rs.getBigDecimal("performance"),
                    rs.getDate("date_of_investment")
                };
                list.add(row);
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list.toArray(new Object[0][]); // Convert list to array
    }

    // Method to calculate and display total portfolio value and performance
    private void displayPortfolioSummary(String username) {
        try {
            // Load JDBC driver and connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb", "root", "srivakrish@1234");

            // SQL query to calculate total portfolio value
            String sql = "SELECT SUM(quantity * current_price) AS total_value FROM portfolio WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                double totalValue = rs.getDouble("total_value");
                JOptionPane.showMessageDialog(this, "Total Portfolio Value: $" + totalValue, "Portfolio Summary", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No portfolio data found for user: " + username, "Portfolio Summary", JOptionPane.WARNING_MESSAGE);
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to create styled buttons
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setForeground(Color.DARK_GRAY);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(300, 40));
        return button;
    }

    public static void main(String[] args) {
        new Portfolioperformance("testuser"); // Test with sample username
    }
}

