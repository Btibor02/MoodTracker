import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Arrays;

public class RegistrationScreen extends JFrame {
    private final JLabel appNameLabel = new JLabel("Mood Tracker");
    private final JLabel registerLabel = new JLabel("Sign up");
    private final JLabel usernameLabel = new JLabel("Username");
    private final JLabel passwordLabel = new JLabel("Password");
    private final JLabel confirmPasswordLabel = new JLabel("Confirm Password");
    private final JLabel loginLabel = new JLabel("I've an account");

    private final JButton loginButton = new JButton("Sign up");
    private final JButton registerButton = new JButton("Login");

    private final JTextField usernameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JPasswordField confirmPasswordField = new JPasswordField();

    private final JFrame registerScreen = new JFrame();

    public void init() {
        loadScreen();
    }

    private void loadScreen() {
        setLabels();
        setTextFields();
        setButtons();

        registerScreen.setLayout(null);
        registerScreen.setTitle("Register");
        registerScreen.setVisible(true);
        registerScreen.setResizable(false);
        registerScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerScreen.setSize(414, 896);
        registerScreen.getContentPane().setBackground(new Colors().backgroundColor);
        //registerScreen.setLocationRelativeTo(null);

        loginButton.addActionListener(_ -> {
            registerScreen.setVisible(false);
            new WelcomeScreen().init();
        });

        registerButton.addActionListener(_ -> {
            String username, query;
            char[] password, confirmPassword;
            username = usernameField.getText();
            password = passwordField.getPassword();
            confirmPassword = confirmPasswordField.getPassword();

            try {
                Statement st = new DatabaseConnection().connection();
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter username");
                } else if (password.length == 0) {
                    JOptionPane.showMessageDialog(null, "Please enter password");
                } else if (confirmPassword.length == 0) {
                    JOptionPane.showMessageDialog(null, "Please confirm password");
                } else if (!Arrays.equals(password, confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match");
                } else if (password.length < 8) {
                    JOptionPane.showMessageDialog(null, "Password must be at least 8 characters");
                } else {
                    query = "INSERT INTO user(username, password)" + "VALUES ('" + username + "', '" + Arrays.toString(password) + "')";
                    st.execute(query);
                    usernameField.setText("");
                    passwordField.setText("");
                    confirmPasswordField.setText("");
                    JOptionPane.showMessageDialog(null, "Account has been created successfully!");

                    registerScreen.setVisible(false);
                    CalendarScreen calendarScreen = new CalendarScreen();
                    calendarScreen.setUser(username);
                    calendarScreen.init();
                }
            } catch (Exception err) {
                System.out.println("Error! " + err.getMessage());
            }
        });

    }

    private void setLabels() {
        appNameLabel.setFont(new Font("Georgia", Font.PLAIN, 50));
        appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appNameLabel.setBounds(0, 100, 414, 100);

        registerLabel.setFont(new Font("Georgia", Font.PLAIN, 50));
        registerLabel.setForeground(new Colors().textColor);
        registerLabel.setBounds(50, 200, 414, 100);

        usernameLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        usernameLabel.setBounds(50, 260, 414, 100);

        passwordLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        passwordLabel.setBounds(50, 350, 414, 100);

        confirmPasswordLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        confirmPasswordLabel.setBounds(50, 440, 414, 100);

        loginLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setBounds(0, 630, 414, 100);

        registerScreen.add(appNameLabel);
        registerScreen.add(registerLabel);
        registerScreen.add(usernameLabel);
        registerScreen.add(passwordLabel);
        registerScreen.add(confirmPasswordLabel);
        registerScreen.add(loginLabel);
    }

    private void setButtons() {
        registerButton.setBounds(50, 570, 120, 40);
        registerButton.setBackground(new Colors().textColor);
        registerButton.setForeground(new Color(255, 255, 255));
        registerButton.setFont(new Font("Georgia", Font.PLAIN, 20));

        loginButton.setBounds(100, 700, 200, 50);
        loginButton.setBackground(new Colors().textColor);
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setFont(new Font("Georgia", Font.PLAIN, 20));

        registerScreen.add(loginButton);
        registerScreen.add(registerButton);
    }

    private void setTextFields() {
        usernameField.setBounds(50, 330, 300, 40);
        passwordField.setBounds(50, 420, 300, 40);
        confirmPasswordField.setBounds(50, 510, 300, 40);

        registerScreen.add(usernameField);
        registerScreen.add(passwordField);
        registerScreen.add(confirmPasswordField);
    }
}
