import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.Statement;

public class WelcomeScreen extends JFrame {
    private final JLabel appNameLabel = new JLabel("Mood Tracker");
    private final JLabel loginLabel = new JLabel("Login");
    private final JLabel usernameLabel = new JLabel("Username");
    private final JLabel passwordLabel = new JLabel("Password");
    private final JLabel registerLabel = new JLabel("I don't have an account");

    private final JButton loginButton = new JButton("Login");
    private final JButton registerButton = new JButton("Sign up");

    private final JTextField usernameField = new JTextField();
    private final JTextField passwordField = new JPasswordField();

    public WelcomeScreen() {
        loadScreen();
    }

    private void loadScreen() {
        setLabels();
        setButtons();
        setTextFields();
        appNameLabel.setName("appNameLabel");

        setLayout(null);
        setTitle("Login");
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(414, 896);
        getContentPane().setBackground(new Colors().backgroundColor);
        //welcomeScreen.setLocationRelativeTo(null);

        loginButton.addActionListener(_ -> {
            String username, password, passwordDb = null, query;
            int notFound = 0;

            try {
                Statement st = new DatabaseConnection().connection();

                username = usernameField.getText();
                password = passwordField.getText();

                query = "SELECT * FROM user WHERE username = '" + username + "'";
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    passwordDb = rs.getString("password");
                    notFound = 1;
                }
                if (notFound == 1 && passwordDb.equals(password)) {
                    JOptionPane.showMessageDialog(null, "You have logged in!");
                    setVisible(false);
                    CalendarScreen calendarScreen = new CalendarScreen();
                    calendarScreen.setUser(username);
                    new CalendarScreen();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect username or password", "error", JOptionPane.ERROR_MESSAGE);
                }

                st.execute(query);
                usernameField.setText("");
                passwordField.setText("");

            } catch (Exception err) {
                System.out.println("Error!" + err.getMessage());
            }
        });

        registerButton.addActionListener(_ -> {
            setVisible(false);
            new RegistrationScreen();
        });

    }

    private void setLabels() {
        appNameLabel.setName("appNameLabel");
        appNameLabel.setFont(new Font("Georgia", Font.PLAIN, 50));
        appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appNameLabel.setBounds(0,100,414,100);

        loginLabel.setName("loginLabel");
        loginLabel.setFont(new Font("Georgia", Font.PLAIN, 50));
        loginLabel.setForeground(new Colors().textColor);
        loginLabel.setBounds(50,200,414,100);

        usernameLabel.setName("usernameLabel");
        usernameLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        usernameLabel.setBounds(50,260,414,100);

        passwordLabel.setName("passwordLabel");
        passwordLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        passwordLabel.setBounds(50,350,414,100);

        registerLabel.setName("registerLabel");
        registerLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setBounds(0,630,414,100);

        add(appNameLabel);
        add(loginLabel);
        add(usernameLabel);
        add(passwordLabel);
        add(registerLabel);
    }

    private void setButtons() {
        loginButton.setName("loginButton");
        loginButton.setBounds(50,470,120,40);
        loginButton.setBackground(new Colors().textColor);
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setFont(new Font("Georgia", Font.PLAIN, 20));

        registerButton.setName("registerButton");
        registerButton.setBounds(100,700,200,50);
        registerButton.setBackground(new Colors().textColor);
        registerButton.setForeground(new Color(255, 255, 255));
        registerButton.setFont(new Font("Georgia", Font.PLAIN, 20));

        add(loginButton);
        add(registerButton);
    }

    private void setTextFields() {
        usernameField.setBounds(50,330,300,40);
        usernameField.setName("usernameField");
        passwordField.setBounds(50,420,300,40);
        passwordField.setName("passwordField");

        add(usernameField);
        add(passwordField);
    }
}
