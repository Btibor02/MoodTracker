import javax.swing.*;
//import java.util.Map;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnalyticsScreen extends JFrame{
    private final JLabel welcomeMsg = new JLabel("Welcome to your analytics page. ");
    private final JLabel welcomeMsg2 = new JLabel("Here you can see your mood trends. ");
    private final JLabel appNameLabel = new JLabel("Mood Tracker");

    private final JButton prevMonthButton = new JButton("<-");
    private final JButton nextMonthButton = new JButton("->");

    private final Menu navigationMenu = new Menu();
    private final JPanel analytics = new JPanel();
    private final JPanel menu = navigationMenu.navigationMenu("analytics");
    private final LocalDate todayDate = LocalDate.now();
    private final JFrame analyticsScreen = new JFrame();
    private String chosenMonth;

    //initializes the screen
    public void init() {
        loadScreen();
    }

    //drop down box to choose the months they want to see the analytics for (mouse listener needed)
    public void comboBoxMonths(){
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

        JComboBox<String> comboBoxMonths = new JComboBox<>(months);
        comboBoxMonths.setFont(new Font("Georgia", Font.PLAIN, 20));
        comboBoxMonths.setForeground(new Colors().textColor);
        comboBoxMonths.setEditable(false);
        comboBoxMonths.setSelectedIndex(0);
        comboBoxMonths.setOpaque(false);
        //comboBoxMonths.setBounds();
        comboBoxMonths.setBackground(Color.WHITE);
        comboBoxMonths.setVisible(true);

        JButton choiceButton = new JButton();
        choiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosenMonth = comboBoxMonths.getSelectedItem().toString();
            }
        });
    }
    //loads all the components
    private void loadScreen(){

        //setButtons(); //WIP
        setLabels();
        analyticsScreen.setLayout(null);
        analyticsScreen.setSize(414, 896);
        analyticsScreen.setTitle("Your Analytics ");
        analyticsScreen.setResizable(false);
        analyticsScreen.setVisible(true);
        analyticsScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        analyticsScreen.getContentPane().setBackground(new Colors().backgroundColor);

        analyticsScreen.add(menu);
    }
    //Method to get retrieve mood from the DB
    private void getMoodFromDB(String chosenMonth)throws SQLException, ClassNotFoundException{
        try{
            String monthQuery = "SELECT mood FROM user WHERE MONTH(date) = ?";

            Connection con = new DatabaseConnection().preparedConnection();
            PreparedStatement preparedSt = con.prepareStatement(monthQuery);
            preparedSt.setString(1, chosenMonth);
            try (ResultSet resultSet = preparedSt.executeQuery()){
                List<String> retrievedMoods = new ArrayList<>();
                while (resultSet.next()){
                    String moodFromDB = resultSet.getString("mood");
                    retrievedMoods.add(moodFromDB);
                }
            }
        }catch(Exception ignored) {
        }
    }
    //Method to analyze data
    //
    private void analyzeData(List<String> moodData){

    }
    private void setLabels() {
        appNameLabel.setFont(new Font("Georgia", Font.PLAIN, 30));
        appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        appNameLabel.setBounds(0,20,414,100);

        welcomeMsg.setText("Welcome to your analytics page ");
        welcomeMsg.setFont(new Font("Georgia", Font.PLAIN, 30));
        welcomeMsg.setForeground(new Colors().textColor);
        welcomeMsg.setBounds(40,50,414,100);

        welcomeMsg2.setText("Here you can see your mood trends ");
        welcomeMsg2.setFont(new Font("Georgia", Font.PLAIN, 30));
        welcomeMsg2.setForeground(new Colors().textColor);
        welcomeMsg2.setBounds(60,70,414,100);

        analyticsScreen.add(appNameLabel);
        analyticsScreen.add(welcomeMsg);
        analyticsScreen.add(welcomeMsg2);
    }

}