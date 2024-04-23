import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalendarScreen extends JFrame {
    JFrame calendarScreen = new JFrame();
    public void loadScreen() {
        JLabel appNameLabel = getLabels().get(0);
        JLabel welcomeLabel = getLabels().get(1);

        calendarScreen.setLayout(null);
        calendarScreen.setTitle("Register");
        calendarScreen.setVisible(true);
        calendarScreen.setResizable(false);
        calendarScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calendarScreen.setSize(414, 896);
        calendarScreen.getContentPane().setBackground(new Color(255, 235, 198));
        //calendarScreen.setLocationRelativeTo(null);

        calendarScreen.add(appNameLabel);
        calendarScreen.add(welcomeLabel);

    }

    public void setUser (String username) {
        JLabel usernameLabel = new JLabel();
        if (username.isEmpty()) {
            usernameLabel.setText("Test");
        } else {
            usernameLabel.setText(username);
        }
        usernameLabel.setFont(new Font("Georgia", Font.PLAIN, 30));
        usernameLabel.setForeground(new Color(101, 85, 32));
        usernameLabel.setBounds(260,70,414,100);

        calendarScreen.add(usernameLabel);
    }


    public List<JLabel> getLabels() {
        JLabel appNameLabel = new JLabel();
        appNameLabel.setText("Mood Tracker");
        appNameLabel.setFont(new Font("Georgia", Font.PLAIN, 50));
        appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appNameLabel.setBounds(0,20,414,100);

        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setText("Welcome back ");
        welcomeLabel.setFont(new Font("Georgia", Font.PLAIN, 30));
        welcomeLabel.setForeground(new Color(101, 85, 32));
        welcomeLabel.setBounds(50,70,414,100);

        List<JLabel> labels = new ArrayList<>(Arrays.asList(
                appNameLabel, welcomeLabel
        ));
        return labels;
    }
    
}
