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

    private final JFrame welcomeScreen = new JFrame();

    public void init() {
        loadScreen();
    }

    private void loadScreen() {
        setLabels();
        setButtons();
        setTextFields();

        welcomeScreen.setLayout(null);
        welcomeScreen.setTitle("Login");
        welcomeScreen.setVisible(true);
        welcomeScreen.setResizable(false);
        welcomeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeScreen.setSize(414, 896);
        welcomeScreen.getContentPane().setBackground(new Colors().backgroundColor);
        //welcomeScreen.setLocationRelativeTo(null);

        welcomeScreen.add(appNameLabel);
        welcomeScreen.add(loginLabel);
        welcomeScreen.add(usernameLabel);
        welcomeScreen.add(passwordLabel);
        welcomeScreen.add(registerLabel);

        welcomeScreen.add(loginButton);
        welcomeScreen.add(registerButton);

        welcomeScreen.add(usernameField);
        welcomeScreen.add(passwordField);

        loginButton.addActionListener(e -> {
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
                    welcomeScreen.setVisible(false);
                    CalendarScreen calendarScreen = new CalendarScreen();
                    calendarScreen.setUser(username);
                    calendarScreen.loadScreen();
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

        registerButton.addActionListener(e -> {
            welcomeScreen.setVisible(false);
            new RegistrationScreen().loadScreen();
        });

    }

    private void setLabels() {
        appNameLabel.setFont(new Font("Georgia", Font.PLAIN, 50));
        appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appNameLabel.setBounds(0,100,414,100);

        loginLabel.setFont(new Font("Georgia", Font.PLAIN, 50));
        loginLabel.setForeground(new Colors().textColor);
        loginLabel.setBounds(50,200,414,100);

        usernameLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        usernameLabel.setBounds(50,260,414,100);

        passwordLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        passwordLabel.setBounds(50,350,414,100);

        registerLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setBounds(0,630,414,100);
    }

    private void setButtons() {
        loginButton.setBounds(50,470,120,40);
        loginButton.setBackground(new Colors().textColor);
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setFont(new Font("Georgia", Font.PLAIN, 20));

        registerButton.setBounds(100,700,200,50);
        registerButton.setBackground(new Colors().textColor);
        registerButton.setForeground(new Color(255, 255, 255));
        registerButton.setFont(new Font("Georgia", Font.PLAIN, 20));
    }

    private void setTextFields() {
        usernameField.setBounds(50,330,300,40);
        passwordField.setBounds(50,420,300,40);
    }
}
