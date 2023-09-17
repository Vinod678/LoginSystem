import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationPage implements ActionListener {

    JFrame frame = new JFrame();
    JLabel registrationLabel = new JLabel("Registration");
    JLabel nameLabel = new JLabel("Name:");
    JLabel passwordLabel = new JLabel("Password:");
    JTextField nameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton registerButton = new JButton("Register");
    JButton loginButton = new JButton("Login");
    JLabel registrationMessage = new JLabel();

    RegistrationPage() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2)); // 5 rows, 2 columns

        registrationLabel.setFont(new Font(null, Font.PLAIN, 15));
        panel.add(registrationLabel);

        panel.add(new JLabel()); // Empty label for spacing

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(new JLabel()); // Empty label for spacing

        registerButton.setFocusable(false);
        registerButton.addActionListener(this);
        panel.add(registerButton);

        loginButton.setFocusable(false);
        loginButton.setEnabled(false);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        panel.add(registrationMessage);

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); // Automatically adjust the frame size based on component sizes
        frame.setVisible(true);
    }

    // Rest of the actionPerformed and methods remain the same
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String username = nameField.getText();
            String password = new String(passwordField.getPassword());

            // Hash and salt the password (using a secure method, not shown here)
            String hashedPassword = hashAndSaltPassword(password);

            // Insert user data into the database
            if (insertUserData(username, hashedPassword)) {
                registrationMessage.setForeground(Color.green);
                registrationMessage.setText("Registration Successful");
                loginButton.setEnabled(true);
            } else {
                registrationMessage.setForeground(Color.red);
                registrationMessage.setText("Registration Failed");
            }
        }

        // Handle the login button click as before
    }

    private String hashAndSaltPassword(String password) {
        // Implement password hashing and salting here (e.g., using BCrypt or PBKDF2)
        // Return the securely hashed and salted password
        // For now, let's assume a simple plaintext password (not recommended for production)
        return password;
    }

    private boolean insertUserData(String username, String hashedPassword) {
        try {
            // Establish a database connection (replace with your database credentials)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "vinod");

            // Prepare the SQL statement
            String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);

            // Execute the SQL statement to insert data
            int rowsAffected = preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();
            connection.close();

            // Check if the insertion was successful
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationPage());
    }
}
