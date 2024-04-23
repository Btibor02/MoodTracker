import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WelcomeScreen extends JFrame {
    public void loadScreen() {
        JLabel appNameLabel = getLabels().get(0);
        JLabel loginLabel = getLabels().get(1);
        JLabel usernameLabel = getLabels().get(2);
        JLabel passwordLabel = getLabels().get(3);
        JLabel registerLabel = getLabels().get(4);

        JButton loginButton = getButtons().get(0);
        JButton registerButton = getButtons().get(1);

        JTextField usernameField = getTextFields().get(0);
        JTextField passwordField = getTextFields().get(1);

        JFrame welcomeScreen = new JFrame();
        welcomeScreen.setLayout(null);
        welcomeScreen.setTitle("Login");
        welcomeScreen.setVisible(true);
        welcomeScreen.setResizable(false);
        welcomeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeScreen.setSize(414, 896);
        welcomeScreen.getContentPane().setBackground(new Color(255, 235, 198));
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

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeScreen.setVisible(false);
                new RegistrationScreen().loadScreen();
            }
        });

    }


    public List<JLabel> getLabels() {
        JLabel appNameLabel = new JLabel();
        appNameLabel.setText("Mood Tracker");
        appNameLabel.setFont(new Font("Georgia", Font.PLAIN, 50));
        appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appNameLabel.setBounds(0,100,414,100);

        JLabel loginLabel = new JLabel();
        loginLabel.setText("Login");
        loginLabel.setFont(new Font("Georgia", Font.PLAIN, 50));
        loginLabel.setForeground(new Color(101, 85, 32));
        loginLabel.setBounds(50,200,414,100);

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username");
        usernameLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        usernameLabel.setBounds(50,260,414,100);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        passwordLabel.setBounds(50,350,414,100);

        JLabel registerLabel = new JLabel();
        registerLabel.setText("I don't have an account");
        registerLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setBounds(0,630,414,100);

        List<JLabel> labels = new ArrayList<>(Arrays.asList(
                appNameLabel, loginLabel, usernameLabel, passwordLabel, registerLabel
        ));
        return labels;
    }

    public List<JButton> getButtons() {
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50,470,120,40);
        loginButton.setBackground(new Color(101, 85, 32));
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setFont(new Font("Georgia", Font.PLAIN, 20));

        JButton registerButton = new JButton("Sign up");
        registerButton.setBounds(100,700,200,50);
        registerButton.setBackground(new Color(101, 85, 32));
        registerButton.setForeground(new Color(255, 255, 255));
        registerButton.setFont(new Font("Georgia", Font.PLAIN, 20));

        List<JButton> buttons = new ArrayList<>(Arrays.asList(loginButton, registerButton));
        return buttons;
    }

    public List<JTextField> getTextFields() {
        JTextField usernameText = new JTextField();
        usernameText.setBounds(50,330,300,40);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(50,420,300,40);

        List<JTextField> textFields = new ArrayList<>(Arrays.asList(usernameText, passwordText));

        return textFields;
    }
}
