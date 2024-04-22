import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RegistrationScreen extends JFrame {
    public void loadScreen() {
        JLabel appNameLabel = getLabels().get(0);
        JLabel userNameLabel = getLabels().get(1);
        JLabel passwordLabel = getLabels().get(2);
        JLabel confirmPasswordLabel = getLabels().get(3);

        JButton registerButton = getButtons().get(0);
        JButton backButton = getButtons().get(1);

        JTextField userNameText = getTextFields().get(0);
        JTextField passwordText = getTextFields().get(1);
        JTextField confirmPasswordText = getTextFields().get(2);

        JFrame registrationScreen = new JFrame();
        registrationScreen.setLayout(null);
        registrationScreen.setTitle("Login");
        registrationScreen.setVisible(true);
        registrationScreen.setResizable(false);
        registrationScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registrationScreen.setSize(414, 896);
        registrationScreen.getContentPane().setBackground(new Color(243, 213, 176));
        //registrationScreen.setLocationRelativeTo(null);

        registrationScreen.add(appNameLabel);
        registrationScreen.add(userNameLabel);
        registrationScreen.add(passwordLabel);
        registrationScreen.add(confirmPasswordLabel);

        registrationScreen.add(userNameText);
        registrationScreen.add(passwordText);
        registrationScreen.add(confirmPasswordText);

        registrationScreen.add(registerButton);
        registrationScreen.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrationScreen.setVisible(false);
                new WelcomeScreen().loadScreen();
            }
        });

    }


    public List<JLabel> getLabels() {
        JLabel appNameLabel = new JLabel();
        appNameLabel.setText("Registration");
        appNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 50));
        appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appNameLabel.setBounds(0,150,414,100);

        JLabel userNameLabel = new JLabel();
        userNameLabel.setText("Username");
        userNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        userNameLabel.setBounds(50,240,414,100);

        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        passwordLabel.setBounds(50,340,414,100);

        JLabel confirmPasswordLabel = new JLabel();
        confirmPasswordLabel.setText("Confirm password");
        confirmPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        confirmPasswordLabel.setBounds(50,440,414,100);

        List<JLabel> labels = new ArrayList<>();
        labels.add(appNameLabel);
        labels.add(userNameLabel);
        labels.add(passwordLabel);
        labels.add(confirmPasswordLabel);
        return labels;
    }

    public List<JButton> getButtons() {
        JButton registerButton = new JButton("Create new account");
        registerButton.setBounds(50,600,300,80);
        registerButton.setBackground(new Color(255, 255, 255));
        registerButton.setForeground(new Color(0, 0, 0));

        JButton backButton = new JButton("Back");
        backButton.setBounds(50,700,300,80);
        backButton.setBackground(new Color(255, 255, 255));
        backButton.setForeground(new Color(0, 0, 0));

        List<JButton> buttons = new ArrayList<>();
        buttons.add(registerButton);
        buttons.add(backButton);
        return buttons;
    }

    public List<JTextField> getTextFields() {
        JTextField userNameText = new JTextField();
        userNameText.setBounds(50,300,300,50);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(50,400,300,50);

        JPasswordField confirmPasswordText = new JPasswordField();
        confirmPasswordText.setBounds(50,500,300,50);

        List<JTextField> textFields = new ArrayList<>();
        textFields.add(userNameText);
        textFields.add(passwordText);
        textFields.add(confirmPasswordText);
        return textFields;
    }
}

