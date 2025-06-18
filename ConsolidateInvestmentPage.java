/*import javax.swing.*;
import java.awt.*;

public class ConsolidateInvestmentPage extends JFrame {
    private JTable consolidatedTable;
    private JButton backButton;

    public ConsolidateInvestmentPage(String username) {
        setTitle("Consolidated Investments");
        setBounds(100, 100, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Layout
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Dummy data for the table
        String[] columnNames = {"Investment Name", "Total Quantity", "Avg Purchase Price"};
        Object[][] data = {
            {"Mutual Fund A", 150, "$200"},
            {"Real Estate", 2, "$100,000"}
        };

        consolidatedTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(consolidatedTable);
        container.add(scrollPane, BorderLayout.CENTER);

        // Back button
        backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> {
            dispose();
            new Dashboard(username);  // Return to dashboard
        });
        container.add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ConsolidateInvestmentPage("testuser");  // For testing
    }

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsolidateInvestmentPage extends JFrame {
    private JTable consolidatedTable;
    private JButton backButton;

    public ConsolidateInvestmentPage(String username) {
        setTitle("Consolidated Investments");
        setBounds(100, 100, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Layout
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Fetch data from the database using a JOIN query
        String[] columnNames = {"Investment Name", "Total Quantity", "Avg Purchase Price"};
        Object[][] data = fetchConsolidatedInvestmentData(username);

        consolidatedTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(consolidatedTable);
        container.add(scrollPane, BorderLayout.CENTER);

        // Back button
        backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> {
            dispose();
            new Dashboard(username);  // Return to dashboard
        });
        container.add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method to fetch consolidated investment data using SQL JOIN
    private Object[][] fetchConsolidatedInvestmentData(String username) {
        Object[][] data = new Object[0][]; // Placeholder for data
        try {
            // Load JDBC driver and connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginDB", "root", "srivakrish@1234");

            // SQL query to fetch consolidated investment data
            String sql = "SELECT investment_name, SUM(quantity) AS total_quantity, AVG(purchase_price) AS avg_purchase_price " +
                         "FROM portfolio " +
                         "WHERE username = ? " +
                         "GROUP BY investment_name";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);  // Use the passed username

            ResultSet rs = pst.executeQuery();
            List<Object[]> list = new ArrayList<>();

            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("investment_name"),
                    rs.getInt("total_quantity"),
                    rs.getBigDecimal("avg_purchase_price")
                };
                list.add(row);
            }

            // Convert list to array
            data = list.toArray(new Object[0][]);
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        new ConsolidateInvestmentPage("testuser");  // For testing
    }
}
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ConsolidateInvestmentPage extends JFrame {
    private JTable consolidatedTable;
    private JButton backButton;
    private Image backgroundImage;

    public ConsolidateInvestmentPage(String username) {
        setTitle("Consolidated Investments");
        setBounds(100, 100, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("financialbackground.jpeg"));  // Path to the financial-themed image
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

        backgroundPanel.setLayout(new BorderLayout());  // Set layout to BorderLayout
        setContentPane(backgroundPanel);

        // Fetch data from the database using a JOIN query
        String[] columnNames = {"Investment Name", "Total Quantity", "Avg Purchase Price"};
        Object[][] data = fetchConsolidatedInvestmentData(username);

        consolidatedTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(consolidatedTable);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        // Back button
        backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> {
            dispose();
            new Dashboard(username);  // Return to dashboard
        });
        backgroundPanel.add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method to fetch consolidated investment data using SQL JOIN
    private Object[][] fetchConsolidatedInvestmentData(String username) {
        Object[][] data = new Object[0][];  // Placeholder for data
        try {
            // Load JDBC driver and connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginDB", "root", "srivakrish@1234");

            // SQL query to fetch consolidated investment data using JOIN
            String sql = "SELECT p.investment_name, SUM(p.quantity) AS total_quantity, AVG(p.purchase_price) AS avg_purchase_price " +
                         "FROM portfolio p " +
                         "JOIN users u ON p.user_id = u.user_id " +  // Joining portfolio with users
                         "WHERE u.username = ? " +  // Filter by username
                         "GROUP BY p.investment_name";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);  // Use the passed username

            ResultSet rs = pst.executeQuery();
            List<Object[]> list = new ArrayList<>();

            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("investment_name"),
                    rs.getInt("total_quantity"),
                    rs.getBigDecimal("avg_purchase_price")
                };
                list.add(row);
            }

            // Convert list to array
            data = list.toArray(new Object[0][]);
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        new ConsolidateInvestmentPage("testuser");  // For testing
    }
}*/
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsolidateInvestmentPage extends JFrame {
    private JTable consolidatedTable;
    private JButton backButton;

    public ConsolidateInvestmentPage(String username) {
        setTitle("Consolidated Investments");
        setBounds(100, 100, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Layout
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Fetch data from the database using a JOIN query
        String[] columnNames = {"Investment Name", "Total Quantity", "Avg Purchase Price", "Sector", "Risk Level", "Expected Return"};
        Object[][] data = fetchConsolidatedInvestmentData(username);

        consolidatedTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(consolidatedTable);
        container.add(scrollPane, BorderLayout.CENTER);

        // Back button
        backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> {
            dispose();
            new Dashboard(username);  // Return to dashboard
        });
        container.add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method to fetch consolidated investment data using SQL JOIN
    private Object[][] fetchConsolidatedInvestmentData(String username) {
        Object[][] data = new Object[0][]; // Placeholder for data
        try {
            // Load JDBC driver and connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginDB", "root", "srivakrish@1234");

            // SQL query to fetch consolidated investment data with JOIN
            String sql = "SELECT p.investment_name, SUM(p.quantity) AS total_quantity, AVG(p.purchase_price) AS avg_purchase_price, " +
                         "d.sector, d.risk_level, d.expected_return " +
                         "FROM portfolio p " +
                         "JOIN investment_details d ON p.investment_name = d.investment_name " +
                         "WHERE p.username = ? " +
                         "GROUP BY p.investment_name, d.sector, d.risk_level, d.expected_return";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);  // Use the passed username

            ResultSet rs = pst.executeQuery();
            List<Object[]> list = new ArrayList<>();

            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("investment_name"),
                    rs.getInt("total_quantity"),
                    rs.getBigDecimal("avg_purchase_price"),
                    rs.getString("sector"),
                    rs.getString("risk_level"),
                    rs.getBigDecimal("expected_return")
                };
                list.add(row);
            }

            // Convert list to array
            data = list.toArray(new Object[0][]);
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        new ConsolidateInvestmentPage("testuser");  // For testing
    }
}



