import javax.swing.*;
import java.awt.*;
//import java.util.Map;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class analyticsScreen extends JFrame{
    private final JLabel welcomeMsg = new JLabel("Welcome to your analytics page. ");
    private final JLabel welcomeMsg2 = new JLabel("Here you can see your mood trends. ");
    private final JLabel appNameLabel = new JLabel("Mood Tracker");

    private final JButton prevMonthButton = new JButton("<-");
    private final JButton nextMonthButton = new JButton("->");

    private final Menu navigationMenu = new Menu();
    private final JPanel analytics = new JPanel();
    private final JPanel menu = navigationMenu.navigationMenu("analytics");
    private LocalDate todayDate = LocalDate.now();
    private final JFrame analyticsScreen = new JFrame();

    public void init() {
        loadScreen();
    }
    private void monthMenu(){
        string monhts["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

    }
    private void loadMood(String month){

    }
    private void loadScreen(){

        setButtons();

        analyticsScreen.setLayout(null);
        analyticsScreen.setSize(414, 896);
        analyticsScreen.setTitle("Your Analytics ");
        analyticsScreen.setResizable(false);
        analyticsScreen.setVisible(true);
        analyticsScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        analyticsScreen.getContentPane().setBackground(new Colors().backgroundColor);

        analyticsScreen.add(menu);
    }
    private void setLabels() {
        appNameLabel.setFont(new Font("Georgia", Font.PLAIN, 50));
        appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appNameLabel.setBounds(0,20,414,100);

        welcomeMsg.setText("Welcome to your analytics page ");
        welcomeLabel.setFont(new Font("Georgia", Font.PLAIN, 30));
        welcomeLabel.setForeground(new Colors().textColor);
        welcomeLabel.setBounds(50,70,414,100);

        welcomeMsg2.setText("Here you can see your mood trends ");
        welcomeLabel.setFont(new Font("Georgia", Font.PLAIN, 30));
        welcomeLabel.setForeground(new Colors().textColor);
        welcomeLabel.setBounds(50,70,414,100);

        calendarScreen.add(appNameLabel);
        calendarScreen.add(welcomeMsg);
        calendarScreen.add(welcomeMsg2);
    }

}