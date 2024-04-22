import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;

public class LoginScreen extends JFrame {
    public void loadScreen() {
        JLabel appNameLabel = getLabels().get(0);
        JLabel userNameLabel = getLabels().get(1);
        JLabel passwordLabel = getLabels().get(2);

        JButton loginButton = getButtons().get(0);

        JTextField userNameText = getTextFields().get(0);
        JTextField passwordText = getTextFields().get(1);

        JFrame loginScreen = new JFrame();
        loginScreen.setLayout(null);
        loginScreen.setTitle("Login");
        loginScreen.setVisible(true);
        loginScreen.setResizable(false);
        loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginScreen.setSize(414, 896);
        loginScreen.getContentPane().setBackground(new Color(243, 213, 176));
        //loginScreen.setLocationRelativeTo(null);

        loginScreen.add(appNameLabel);
        loginScreen.add(new JLabel(""));
        loginScreen.add(loginButton);
        loginScreen.add(userNameLabel);
        loginScreen.add(userNameText);
        loginScreen.add(passwordLabel);
        loginScreen.add(passwordText);

    }


    public List<JLabel> getLabels() {
        JLabel appNameLabel = new JLabel();
        appNameLabel.setText("Login");
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

        List<JLabel> labels = new ArrayList<>();
        labels.add(appNameLabel);
        labels.add(userNameLabel);
        labels.add(passwordLabel);
        return labels;
    }

    public List<JButton> getButtons() {
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50,600,300,80);
        loginButton.setBackground(new Color(255, 255, 255));
        loginButton.setForeground(new Color(0, 0, 0));

        List<JButton> buttons = new ArrayList<>();
        buttons.add(loginButton);
        return buttons;
    }

    public List<JTextField> getTextFields() {
        JTextField userNameText = new JTextField();
        userNameText.setBounds(50,300,300,50);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(50,400,300,50);

        List<JTextField> textFields = new ArrayList<>();
        textFields.add(userNameText);
        textFields.add(passwordText);
        return textFields;
    }
}

