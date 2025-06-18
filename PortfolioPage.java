/*import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PortfolioPage extends JFrame {
    private JTable portfolioTable;
    private String username;

    public PortfolioPage(String username) {
        this.username = username;  // Store the username
        setTitle("Current Portfolio");
        setBounds(300, 90, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Layout
        setLayout(new BorderLayout());

        // Table to display portfolio
        String[] columnNames = {"Investment Name", "Investment Type", "Quantity", "Purchase Price", "Current Price", "Date of Investment"};
        Object[][] data = fetchPortfolioData(username);  // Fetch data using username

        portfolioTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(portfolioTable);
        add(scrollPane, BorderLayout.CENTER);

        // Back button to go to the dashboard
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> {
            dispose();
            new Dashboard(username); // Return to the dashboard
        });
        add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method to fetch portfolio data based on username
    private Object[][] fetchPortfolioData(String username) {
        Object[][] data = new Object[0][];
        try {
            // Load JDBC driver and connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb", "root", "srivakrish@1234");

            // SQL query to fetch portfolio data for the given username
            String sql = "SELECT investment_name, investment_type, quantity, purchase_price, current_price, date_of_investment FROM portfolio WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);  // Use the passed username

            // Execute the query
            ResultSet rs = pst.executeQuery();
            List<Object[]> list = new ArrayList<>();
            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("investment_name"),
                    rs.getString("investment_type"),
                    rs.getInt("quantity"),
                    rs.getBigDecimal("purchase_price"),
                    rs.getBigDecimal("current_price"),
                    rs.getDate("date_of_investment")
                };
                list.add(row);
            }

            data = list.toArray(new Object[0][]);  // Convert list to array
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        new PortfolioPage("testuser");  // For testing
    }
}

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PortfolioPage extends JFrame {
    private JTable portfolioTable;
    private String username;

    public PortfolioPage(String username) {
        this.username = username;  // Store the username
        setTitle("Current Portfolio");
        setBounds(300, 90, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Layout
        setLayout(new BorderLayout());

        // Table to display portfolio
        String[] columnNames = {"Investment Name", "Investment Type", "Quantity", "Purchase Price", "Current Price", "Date of Investment"};
        Object[][] data = fetchPortfolioData(username);  // Fetch data using username

        portfolioTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(portfolioTable);
        add(scrollPane, BorderLayout.CENTER);

        // Back button to go to the dashboard
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> {
            dispose();
            new Dashboard(username); // Return to the dashboard
        });
        add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method to fetch portfolio data based on username
    private Object[][] fetchPortfolioData(String username) {
        Object[][] data = new Object[0][];
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb", "root", "srivakrish@1234");

            // SQL query to fetch portfolio data for the given username
            String sql = "SELECT investment_name, investment_type, quantity, purchase_price, current_price, date_of_investment FROM portfolio WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);  // Use the passed username

            // Execute the query
            ResultSet rs = pst.executeQuery();
            List<Object[]> list = new ArrayList<>();
            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("investment_name"),
                    rs.getString("investment_type"),
                    rs.getInt("quantity"),
                    rs.getBigDecimal("purchase_price"),
                    rs.getBigDecimal("current_price"),
                    rs.getDate("date_of_investment")
                };
                list.add(row);
            }

            data = list.toArray(new Object[0][]);  // Convert list to array
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        new PortfolioPage("testuser");  // For testing
    }
}
*/
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PortfolioPage extends JFrame {
    private JTable portfolioTable;
    private String username;

    public PortfolioPage(String username) {
        this.username = username;  // Store the username
        setTitle("Current Portfolio");
        setBounds(100, 100, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Layout
        setLayout(new BorderLayout());

        // Table to display portfolio
        String[] columnNames = {"Investment Name", "Investment Type", "Quantity", "Purchase Price", "Current Price", "Date of Investment"};
        Object[][] data = fetchPortfolioData(username);  // Fetch data using username

        portfolioTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(portfolioTable);
        add(scrollPane, BorderLayout.CENTER);

        // Back button to go to the dashboard
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> {
            dispose();
            new Dashboard(username); // Return to the dashboard
        });
        add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method to fetch portfolio data based on username
    private Object[][] fetchPortfolioData(String username) {
        Object[][] data = new Object[0][];
        try {
            // Load JDBC driver and connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb", "root", "srivakrish@1234");

            // SQL query to fetch portfolio data for the given username
            String sql = "SELECT investment_name, investment_type, quantity, purchase_price, current_price, date_of_investment FROM portfolio WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);  // Use the passed username

            // Execute the query
            ResultSet rs = pst.executeQuery();
            List<Object[]> list = new ArrayList<>();
            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("investment_name"),
                    rs.getString("investment_type"),
                    rs.getInt("quantity"),
                    rs.getBigDecimal("purchase_price"),
                    rs.getBigDecimal("current_price"),
                    rs.getDate("date_of_investment")
                };
                list.add(row);
            }

            data = list.toArray(new Object[0][]);  // Convert list to array
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        new PortfolioPage("testuser");  // For testing
    }
}

