import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegistrationScreen extends JFrame {
    public void loadScreen() {
        JLabel appNameLabel = getLabels().get(0);
        JLabel registerLabel = getLabels().get(1);
        JLabel usernameLabel = getLabels().get(2);
        JLabel passwordLabel = getLabels().get(3);
        JLabel confirmPasswordLabel = getLabels().get(4);
        JLabel loginLabel = getLabels().get(5);

        JButton loginButton = getButtons().get(0);
        JButton registerButton = getButtons().get(1);

        JTextField usernameField = getTextFields().get(0);
        JTextField passwordField = getTextFields().get(1);
        JTextField confirmPasswordField = getTextFields().get(2);

        JFrame registerScreen = new JFrame();
        registerScreen.setLayout(null);
        registerScreen.setTitle("Welcome Screen");
        registerScreen.setVisible(true);
        registerScreen.setResizable(false);
        registerScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerScreen.setSize(414, 896);
        registerScreen.getContentPane().setBackground(new Color(255, 235, 198));
        //registerScreen.setLocationRelativeTo(null);

        registerScreen.add(appNameLabel);
        registerScreen.add(registerLabel);
        registerScreen.add(usernameLabel);
        registerScreen.add(passwordLabel);
        registerScreen.add(confirmPasswordLabel);
        registerScreen.add(loginLabel);

        registerScreen.add(loginButton);
        registerScreen.add(registerButton);

        registerScreen.add(usernameField);
        registerScreen.add(passwordField);
        registerScreen.add(confirmPasswordField);

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                registerScreen.setVisible(false);
                new WelcomeScreen().loadScreen();

            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerScreen.setVisible(false);
            }
        });

    }


    public List<JLabel> getLabels() {
        JLabel appNameLabel = new JLabel();
        appNameLabel.setText("Mood Tracker");
        appNameLabel.setFont(new Font("Georgia", Font.PLAIN, 50));
        appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appNameLabel.setBounds(0,100,414,100);

        JLabel registerLabel = new JLabel();
        registerLabel.setText("Sign up");
        registerLabel.setFont(new Font("Georgia", Font.PLAIN, 50));
        registerLabel.setForeground(new Color(101, 85, 32));
        registerLabel.setBounds(50,200,414,100);

        JLabel usernameLabel = new JLabel();
        usernameLabel.setText("Username");
        usernameLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        usernameLabel.setBounds(50,260,414,100);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        passwordLabel.setBounds(50,350,414,100);

        JLabel confirmPasswordLabel = new JLabel();
        confirmPasswordLabel.setText("Confirm Password");
        confirmPasswordLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        confirmPasswordLabel.setBounds(50,440,414,100);

        JLabel loginLabel = new JLabel();
        loginLabel.setText("I've an account");
        loginLabel.setFont(new Font("Georgia", Font.PLAIN, 15));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setBounds(0,630,414,100);

        List<JLabel> labels = new ArrayList<>(Arrays.asList(
                appNameLabel, registerLabel, usernameLabel, passwordLabel, confirmPasswordLabel, loginLabel
        ));
        return labels;
    }

    public List<JButton> getButtons() {
        JButton registerButton = new JButton("Sign up");
        registerButton.setBounds(50,570,120,40);
        registerButton.setBackground(new Color(101, 85, 32));
        registerButton.setForeground(new Color(255, 255, 255));
        registerButton.setFont(new Font("Georgia", Font.PLAIN, 20));

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100,700,200,50);
        loginButton.setBackground(new Color(101, 85, 32));
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setFont(new Font("Georgia", Font.PLAIN, 20));

        List<JButton> buttons = new ArrayList<>(Arrays.asList(loginButton, registerButton));
        return buttons;
    }

    public List<JTextField> getTextFields() {
        JTextField usernameText = new JTextField();
        usernameText.setBounds(50,330,300,40);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(50,420,300,40);

        JPasswordField confirmPasswordText = new JPasswordField();
        confirmPasswordText.setBounds(50,510,300,40);

        List<JTextField> textFields = new ArrayList<>(Arrays.asList(
                usernameText, passwordText, confirmPasswordText
        ));

        return textFields;
    }
}
