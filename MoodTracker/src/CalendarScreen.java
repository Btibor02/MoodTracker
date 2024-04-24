import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalendarScreen extends JFrame {
    JFrame calendarScreen = new JFrame();
    JPanel calendar = new JPanel();
    JTable calendarTable = new JTable();
    LocalDate todayDate = LocalDate.now();
    JLabel currentMonthLabel = new JLabel();
    JLabel currentYearLabel = new JLabel();

    public void loadScreen() {

        calendar(todayDate);

        JLabel appNameLabel = getLabels().get(0);
        JLabel welcomeLabel = getLabels().get(1);
        JLabel currentMonthLabel = getCurrentMonthLabel(todayDate);
        JLabel currentYearLabel = getCurrentYearLabel(todayDate);

        JButton previousMonthButton = getButtons().get(0);
        JButton nextMonthButton = getButtons().get(1);

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
        calendarScreen.add(currentMonthLabel);
        calendarScreen.add(currentYearLabel);

        calendarScreen.add(previousMonthButton);
        calendarScreen.add(nextMonthButton);

        previousMonthButton.addActionListener(e ->  {
            todayDate = todayDate.minusMonths(1);
            calendar(todayDate);
            getCurrentMonthLabel(todayDate);
            getCurrentYearLabel(todayDate);
        });

        nextMonthButton.addActionListener(e -> {
            todayDate = todayDate.plusMonths(1);
            calendar(todayDate);
            getCurrentMonthLabel(todayDate);
            getCurrentYearLabel(todayDate);
        });

    }

    private void calendar(LocalDate todayDate) {
        calendar.setLayout(null);
        calendar.setBackground(new Color(169, 125, 40));
        calendar.setSize(350,360);
        calendar.setVisible(true);
        calendar.setLocation(25, 250);

        dayNames();
        makeDayPanels(todayDate);
        calendarScreen.add(calendar);
    }

    private void dayNames() {
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
            dayNames.get(i).setBackground(new Color(236, 203, 124));
            dayNames.get(i).setBounds(i * 50, 0, 50, 60);

            JLabel dayNameLabel = dayNamesLabel.get(i);
            dayNameLabel.setVisible(true);
            dayNameLabel.setForeground(Color.BLACK);
            dayNameLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
            dayNameLabel.setForeground(new Color(101, 85, 32));
            dayNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
            dayNameLabel.setVerticalAlignment(SwingConstants.CENTER);
            dayNameLabel.setBounds(0,0,50,60);

            dayNames.get(i).add(dayNameLabel);
            calendar.add(dayNames.get(i));

        }
    }

    private Integer days(LocalDate todayDate) {

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

    private void makeDayPanels(LocalDate todayDate) {
        int firstDay = days(todayDate);

        String[] dayOfTheWeek = {"M", "T", "W", "T", "F", "S", "S"};

        int numRows = 5;

        DefaultTableModel model = new DefaultTableModel(numRows, dayOfTheWeek.length) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(dayOfTheWeek);
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(SwingConstants.CENTER);



        calendarTable.setModel(model);
        for (int i = 0; i < model.getColumnCount(); i++) {
            calendarTable.getColumnModel().getColumn(i).setCellRenderer(centerRender);
        }
        calendarTable.setVisible(true);
        calendarTable.setBounds(0,60,350,360);
        calendarTable.setRowHeight(60);
        calendarTable.setFont(new Font("Georgia", Font.PLAIN, 20));
        calendarTable.setForeground(new Color(101, 85, 32));

        int dayNumber = 1;
        for (int i = 0; i < 7 - firstDay; i++) {
            model.setValueAt(dayNumber, 0, firstDay + i);
            dayNumber++;
        }

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

    void setUser(String username) {
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

    private JLabel getCurrentMonthLabel(LocalDate todayDate) {
        currentMonthLabel.setText(String.valueOf(todayDate.getMonth()));
        currentMonthLabel.setFont(new Font("Georgia", Font.PLAIN, 30));
        currentMonthLabel.setForeground(new Color(101, 85, 32));
        currentMonthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentMonthLabel.setBounds(0,180,414,100);
        return currentMonthLabel;
    }

    private JLabel getCurrentYearLabel(LocalDate todayDate) {
        currentYearLabel.setText(String.valueOf(todayDate.getYear()));
        currentYearLabel.setFont(new Font("Georgia", Font.PLAIN, 25));
        currentYearLabel.setForeground(new Color(101, 85, 32));
        currentYearLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentYearLabel.setBounds(0,150,414,100);
        return currentYearLabel;
    }


    private List<JLabel> getLabels() {
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

    private List<JButton> getButtons() {
        JButton prevMonthButton = new JButton();
        prevMonthButton.setText("<-");
        prevMonthButton.setFont(new Font("Georgia", Font.PLAIN, 30));
        prevMonthButton.setForeground(new Color(101, 85, 32));;
        prevMonthButton.setBackground(new Color(236, 203, 124));
        prevMonthButton.setBounds(60, 190, 70, 70 );
        prevMonthButton.setOpaque(false);
        prevMonthButton.setBorderPainted(false);

        JButton nextMonthButton = new JButton();
        nextMonthButton.setText("->");
        nextMonthButton.setFont(new Font("Georgia", Font.PLAIN, 30));
        nextMonthButton.setForeground(new Color(101, 85, 32));;
        nextMonthButton.setBackground(new Color(236, 203, 124));
        nextMonthButton.setBounds(280, 190, 70, 70 );
        nextMonthButton.setOpaque(false);
        nextMonthButton.setBorderPainted(false);

        List<JButton> buttons = new ArrayList<>(Arrays.asList(prevMonthButton, nextMonthButton));
        return buttons;
    }

}
