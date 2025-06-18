/* 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {
    // Components of the Form
    private Container container;
    private JLabel userLabel, passwordLabel, titleLabel;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton loginButton, resetButton;
    private JLabel message;

    // Constructor
    public LoginPage() {
        setTitle("Financial Application Login");
        setBounds(300, 90, 400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Setting a light background color
        container = getContentPane();
        container.setLayout(null);
        container.setBackground(new Color(240, 248, 255)); // Light color (Alice Blue)

        // Title Label
        titleLabel = new JLabel("Welcome to Financial App");
        titleLabel.setBounds(70, 20, 260, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(70, 130, 180)); // Steel Blue color
        container.add(titleLabel);

        // Username label
        userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 70, 100, 30);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        container.add(userLabel);

        // Username text field
        userTextField = new JTextField();
        userTextField.setBounds(150, 70, 150, 30);
        container.add(userTextField);

        // Password label
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 110, 100, 30);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        container.add(passwordLabel);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 110, 150, 30);
        container.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(50, 160, 100, 30);
        loginButton.setBackground(new Color(70, 130, 180)); // Steel Blue color
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        container.add(loginButton);

        // Reset button
        resetButton = new JButton("Reset");
        resetButton.setBounds(200, 160, 100, 30);
        resetButton.setBackground(new Color(220, 20, 60)); // Crimson color
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(this);
        container.add(resetButton);

        // Message label
        message = new JLabel("");
        message.setBounds(50, 200, 300, 30);
        message.setFont(new Font("Arial", Font.PLAIN, 12));
        message.setForeground(new Color(220, 20, 60)); // Crimson color
        container.add(message);

        setVisible(true);
    }

    // ActionListener for the buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userTextField.getText();
            String password = new String(passwordField.getPassword());

            if (validateLogin(username, password)) {
                message.setText("Login Successful!");
                JOptionPane.showMessageDialog(this, "Welcome, " + username + "!");

                // Open the Dashboard and close the login frame
                new Dashboard(username);  // Pass the username to Dashboard
                dispose();  // Closes the login window
            } else {
                message.setText("Invalid Username or Password");
            }
        } else if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
            message.setText("");
        }
    }

    // Function to validate login using JDBC
    private boolean validateLogin(String username, String password) {
        boolean isValid = false;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb", "root", "srivakrish@1234");

            // SQL query to check for matching username and password
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            // Execute the query
            ResultSet rs = pst.executeQuery();

            // Check if there is a match
            if (rs.next()) {
                isValid = true;
            }

            // Close the connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return isValid;
    }

    // Main method to run the application
    public static void main(String[] args) {
        new LoginPage();
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {
    private Container container;
    private JLabel userLabel, passwordLabel, titleLabel;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton loginButton, resetButton;
    private JLabel message;
    private Image backgroundImage;

    // Constructor
    public LoginPage() {
        setTitle("Financial Application Login");
        setBounds(300, 90, 500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Load background image
        try {
            backgroundImage = ImageIO.read(new File("financialbackground.jpeg"));  // Path to the financial-themed image
            if (backgroundImage == null) {
                System.out.println("Image file not found! Please check the path.");
            } else {
                System.out.println("Image loaded successfully!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Custom JPanel to paint the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        backgroundPanel.setLayout(null);  // Use absolute layout to position elements
        setContentPane(backgroundPanel);

        // Title Label
        titleLabel = new JLabel("Welcome to Financial App");
        titleLabel.setBounds(70, 20, 260, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);  // Set title color to white for contrast
        backgroundPanel.add(titleLabel);

        // Username label
        userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 70, 100, 30);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userLabel.setForeground(Color.WHITE);  // Set label color to white for contrast
        backgroundPanel.add(userLabel);

        // Username text field
        userTextField = new JTextField();
        userTextField.setBounds(150, 70, 150, 30);
        backgroundPanel.add(userTextField);

        // Password label
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 110, 100, 30);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.WHITE);  // Set label color to white for contrast
        backgroundPanel.add(passwordLabel);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 110, 150, 30);
        backgroundPanel.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(50, 160, 100, 30);
        loginButton.setBackground(new Color(70, 130, 180));  // Steel Blue color
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        backgroundPanel.add(loginButton);

        // Reset button
        resetButton = new JButton("Reset");
        resetButton.setBounds(200, 160, 100, 30);
        resetButton.setBackground(new Color(220, 20, 60));  // Crimson color
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(this);
        backgroundPanel.add(resetButton);

        // Message label
        message = new JLabel("");
        message.setBounds(50, 200, 300, 30);
        message.setFont(new Font("Arial", Font.PLAIN, 12));
        message.setForeground(Color.RED);
        backgroundPanel.add(message);

        setVisible(true);
    }

    // ActionListener for the buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userTextField.getText();
            String password = new String(passwordField.getPassword());

            if (validateLogin(username, password)) {
                message.setText("Login Successful!");
                JOptionPane.showMessageDialog(this, "Welcome, " + username + "!");

                // Open the Dashboard and close the login frame
                new Dashboard(username);  // Pass the username to Dashboard
                dispose();  // Closes the login window
            } else {
                message.setText("Invalid Username or Password");
            }
        } else if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
            message.setText("");
        }
    }

    // Function to validate login using JDBC
    private boolean validateLogin(String username, String password) {
        boolean isValid = false;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC Driver loaded!");

            // Establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb", "root", "srivakrish@1234");
            System.out.println("Database connection successful!");

            // SQL query to check for matching username and password
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            // Execute the query
            ResultSet rs = pst.executeQuery();

            // Check if there is a match
            if (rs.next()) {
                isValid = true;
            } else {
                System.out.println("Invalid credentials for user: " + username);
            }

            // Close the connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return isValid;
    }

    // Main method to run the application
    public static void main(String[] args) {
        new LoginPage();
    }
}
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {
    private JLabel userLabel, passwordLabel, titleLabel;
    private JTextField userTextField;
    private JPasswordField passwordField;
    private JButton loginButton, resetButton;
    private JLabel message;
    private Image backgroundImage;

    // Constructor
    public LoginPage() {
        setTitle("Financial Application Login");
        setBounds(300, 90, 500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Load background image
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
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        backgroundPanel.setLayout(null);  // Use absolute layout to position elements
        setContentPane(backgroundPanel);

        // Title Label
        titleLabel = new JLabel("Welcome to Financial App");
        titleLabel.setBounds(70, 20, 260, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        backgroundPanel.add(titleLabel);

        // Username label
        userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 70, 100, 30);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userLabel.setForeground(Color.WHITE);
        backgroundPanel.add(userLabel);

        // Username text field
        userTextField = new JTextField();
        userTextField.setBounds(150, 70, 150, 30);
        backgroundPanel.add(userTextField);

        // Password label
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 110, 100, 30);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.WHITE);
        backgroundPanel.add(passwordLabel);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 110, 150, 30);
        backgroundPanel.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(50, 160, 100, 30);
        loginButton.setBackground(new Color(70, 130, 180));  // Steel Blue color
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        backgroundPanel.add(loginButton);

        // Reset button
        resetButton = new JButton("Reset");
        resetButton.setBounds(200, 160, 100, 30);
        resetButton.setBackground(new Color(220, 20, 60));  // Crimson color
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(this);
        backgroundPanel.add(resetButton);

        // Message label
        message = new JLabel("");
        message.setBounds(50, 200, 300, 30);
        message.setFont(new Font("Arial", Font.PLAIN, 12));
        message.setForeground(Color.RED);
        backgroundPanel.add(message);

        setVisible(true);
    }

    // ActionListener for the buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userTextField.getText();
            String password = new String(passwordField.getPassword());

            if (validateLogin(username, password)) {
                message.setText("Login Successful!");
                JOptionPane.showMessageDialog(this, "Welcome, " + username + "!");
                new Dashboard(username);  // Open the Dashboard
                dispose();  // Closes the login window
            } else {
                message.setText("Invalid Username or Password");
            }
        } else if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
            message.setText("");
        }
    }

    // Function to validate login using JDBC
    private boolean validateLogin(String username, String password) {
        boolean isValid = false;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("JDBC Driver loaded!");

            // Establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb", "root", "srivakrish@1234");
            System.out.println("Database connection successful!");

            // SQL query to check for matching username and password
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            // Execute the query
            ResultSet rs = pst.executeQuery();

            // Check if there is a match
            if (rs.next()) {
                isValid = true;
            } else {
                System.out.println("Invalid credentials for user: " + username);
            }

            // Close the connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return isValid;
    }

    // Main method to run the application
    public static void main(String[] args) {
        new LoginPage();
    }
}





        



    


