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

    private final JButton loginButton = new JButton("Login");
    private final JButton registerButton = new JButton("Sign up");

    private final JTextField usernameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JPasswordField confirmPasswordField = new JPasswordField();

    public RegistrationScreen() {
        loadScreen();
    }

    private void loadScreen() {
        setLabels();
        setTextFields();
        setButtons();

        setLayout(null);
        setTitle("Register");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(414, 896);
        getContentPane().setBackground(new Colors().backgroundColor);
        //setLocationRelativeTo(null);

        loginButton.addActionListener(_ -> {
            setVisible(false);
            new WelcomeScreen();
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

                    setVisible(false);
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
        appNameLabel.setName("appNameLabel");
        appNameLabel.setFont(new Font("Georgia", Font.PLAIN, 50));
        appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appNameLabel.setBounds(0, 100, 414, 100);

        registerLabel.setName("registerLabel");
        registerLabel.setFont(new Font("Georgia", Font.PLAIN, 50));
        registerLabel.setForeground(new Colors().textColor);
        registerLabel.setBounds(50, 200, 414, 100);

        usernameLabel.setName("usernameLabel");
        usernameLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        usernameLabel.setBounds(50, 260, 414, 100);

        passwordLabel.setName("passwordLabel");
        passwordLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        passwordLabel.setBounds(50, 350, 414, 100);

        confirmPasswordLabel.setName("confirmPasswordLabel");
        confirmPasswordLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        confirmPasswordLabel.setBounds(50, 440, 414, 100);

        loginLabel.setName("loginLabel");
        loginLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setBounds(0, 630, 414, 100);

        add(appNameLabel);
        add(registerLabel);
        add(usernameLabel);
        add(passwordLabel);
        add(confirmPasswordLabel);
        add(loginLabel);
    }

    private void setButtons() {
        registerButton.setName("registerButton");
        registerButton.setBounds(50, 570, 120, 40);
        registerButton.setBackground(new Colors().textColor);
        registerButton.setForeground(new Color(255, 255, 255));
        registerButton.setFont(new Font("Georgia", Font.PLAIN, 20));

        loginButton.setName("loginButton");
        loginButton.setBounds(100, 700, 200, 50);
        loginButton.setBackground(new Colors().textColor);
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setFont(new Font("Georgia", Font.PLAIN, 20));

        add(loginButton);
        add(registerButton);
    }

    private void setTextFields() {
        usernameField.setName("usernameField");
        usernameField.setBounds(50, 330, 300, 40);

        passwordField.setName("passwordField");
        passwordField.setBounds(50, 420, 300, 40);

        confirmPasswordField.setName("confirmPasswordField");
        confirmPasswordField.setBounds(50, 510, 300, 40);

        add(usernameField);
        add(passwordField);
        add(confirmPasswordField);
    }
}
