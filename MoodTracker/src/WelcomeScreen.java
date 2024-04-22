import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class WelcomeScreen extends JFrame {
    public void loadScreen() {
        JLabel appNameText = getAppNameText();
        JButton loginButton = getButtons().get(0);
        JButton registerButton = getButtons().get(1);

        JFrame welcomeScreen = new JFrame();
        welcomeScreen.setLayout(null);
        welcomeScreen.setTitle("Welcome Screen");
        welcomeScreen.setVisible(true);
        welcomeScreen.setResizable(false);
        welcomeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeScreen.setSize(414, 896);
        welcomeScreen.getContentPane().setBackground(new Color(243, 213, 176));
        //welcomeScreen.setLocationRelativeTo(null);

        welcomeScreen.add(appNameText);
        welcomeScreen.add(new JLabel(""));
        welcomeScreen.add(loginButton);
        welcomeScreen.add(registerButton);

        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeScreen.setVisible(false);
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.loadScreen();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeScreen.setVisible(false);
                RegistrationScreen registrationScreen = new RegistrationScreen();
                registrationScreen.loadScreen();
            }
        });

    }


    public JLabel getAppNameText() {
        JLabel appNameText = new JLabel();
        appNameText.setText("Mood Tracker");
        appNameText.setFont(new Font("Tahoma", Font.PLAIN, 50));

        appNameText.setBounds(50,200,414,100);

        return appNameText;
    }

    public List<JButton> getButtons() {
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50,500,300,80);
        loginButton.setBackground(new Color(255, 255, 255));
        loginButton.setForeground(new Color(0, 0, 0));



        JButton registerButton = new JButton("Register");
        registerButton.setBounds(50,600,300,80);
        registerButton.setBackground(new Color(255, 255, 255));
        registerButton.setForeground(new Color(0, 0, 0));

        List<JButton> buttons = new ArrayList<JButton>();
        buttons.add(loginButton);
        buttons.add(registerButton);
        return buttons;
    }
}
