import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CalendarScreen extends JFrame {
    JFrame calendarScreen = new JFrame();
    JPanel calendar = new JPanel();

    public void loadScreen() {
        calendar();

        JLabel appNameLabel = getLabels().get(0);
        JLabel welcomeLabel = getLabels().get(1);

        calendarScreen.setLayout(null);
        calendarScreen.setTitle("Calendar");
        calendarScreen.setVisible(true);
        calendarScreen.setResizable(false);
        calendarScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calendarScreen.setSize(414, 896);
        calendarScreen.getContentPane().setBackground(new Color(255, 235, 198));
        //calendarScreen.setLocationRelativeTo(null);



        calendarScreen.add(appNameLabel);
        calendarScreen.add(welcomeLabel);

    }

    public void calendar() {
        calendar.setLayout(null);
        calendar.setBackground(new Color(169, 125, 40));
        calendar.setSize(350,420);
        calendar.setVisible(true);
        calendar.setLocation(25, 200);

        dayNames();
        makeDayPanels();
        calendarScreen.add(calendar);
    }

    public void dayNames() {
        JPanel monday = new JPanel();
        JPanel tuesday = new JPanel();
        JPanel wednesday = new JPanel();
        JPanel thursday = new JPanel();
        JPanel friday = new JPanel();
        JPanel saturday = new JPanel();
        JPanel sunday = new JPanel();

        JLabel mondayLabel = new JLabel("M");
        JLabel tuesdayLabel = new JLabel("T");
        JLabel wednesdayLabel = new JLabel("W");
        JLabel thursdayLabel = new JLabel("T");
        JLabel fridayLabel = new JLabel("F");
        JLabel saturdayLabel = new JLabel("S");
        JLabel sundayLabel = new JLabel("S");


        List<JPanel> dayNames = new ArrayList<>(Arrays.asList(
                monday, tuesday, wednesday, thursday, friday, saturday, sunday));

        List<JLabel> dayNamesLabel = new ArrayList<>(Arrays.asList(
                mondayLabel, tuesdayLabel, wednesdayLabel, thursdayLabel, fridayLabel, saturdayLabel, sundayLabel));


        for (int i = 0; i < dayNames.size(); i++) {
            dayNames.get(i).setVisible(true);
            dayNames.get(i).setLayout(null);
            dayNames.get(i).setBackground(new Color(14, 144, 190));
            dayNames.get(i).setBounds(i * 50, 0, 50, 70);
            dayNames.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel dayNameLabel = dayNamesLabel.get(i);
            dayNameLabel.setVisible(true);
            dayNameLabel.setForeground(Color.BLACK);
            dayNameLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
            dayNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            dayNameLabel.setVerticalAlignment(SwingConstants.CENTER);
            dayNameLabel.setBounds(0,0,50,70);

            dayNames.get(i).add(dayNameLabel);
            calendar.add(dayNames.get(i));

        }
    }

    public Integer days() {
        LocalDate todayDate = LocalDate.now();
        String firstDay = todayDate.withDayOfMonth(1).getDayOfWeek().name();
        return switch (firstDay) {
            case "MONDAY" -> 0;
            case "TUESDAY" -> 1;
            case "WEDNESDAY" -> 2;
            case "THURSDAY" -> 3;
            case "FRIDAY" -> 4;
            case "SATURDAY" -> 5;
            case "SUNDAY" -> 6;
            default -> null;
        };
    }

    public void makeDayPanels() {
        int firstDay = days();

        String[] dayOfTheWeek = {"M", "T", "W", "T", "F", "S", "S"};

        int numRows = 5;
        DefaultTableModel model = new DefaultTableModel(numRows, dayOfTheWeek.length);
        model.setColumnIdentifiers(dayOfTheWeek);
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(SwingConstants.CENTER);


        JTable calendarTable = new JTable(model);
        calendarTable.setDefaultRenderer(Integer.class, centerRender);
        calendarTable.setVisible(true);
        calendarTable.setBounds(0,70,350,420);
        calendarTable.setRowHeight(70);
        calendarTable.setFont(new Font("Georgia", Font.PLAIN, 20));

        int dayNumber = 1;
        for (int i = 0; i < 7 - firstDay; i++) {
            model.setValueAt(dayNumber, 0, firstDay + i);
            dayNumber++;
        }

        LocalDate todayDate = LocalDate.now();
        int lastDay = todayDate.withDayOfMonth(1).lengthOfMonth();
        for (int i = 1; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                model.setValueAt(dayNumber, i, j);
                dayNumber++;
                if (dayNumber == lastDay + 1) {
                    break;
                }
            }
        }
        calendar.add(calendarTable);
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