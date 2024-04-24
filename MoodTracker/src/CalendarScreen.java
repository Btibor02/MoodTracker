import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalendarScreen extends JFrame {
    Menu navigationMenu = new Menu();

    JFrame calendarScreen = new JFrame();
    JPanel calendar = new JPanel();
    JTable calendarTable = new JTable();
    LocalDate todayDate = LocalDate.now();
    JLabel currentMonthLabel = new JLabel();
    JLabel currentYearLabel = new JLabel();
    JPanel menu = navigationMenu.navigationMenu("calendar");
    JDialog moodSelectorDialog;
    DefaultTableModel model;

    JButton saveButton;
    JComboBox<String> moodComboBox;
    String selectedMood;

    int selectedRow;
    int selectedCol;
    public CalendarScreen() throws IOException {
    }

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
        calendarScreen.getContentPane().setBackground(new Colors().backgroundColor);
        //calendarScreen.setLocationRelativeTo(null);


        calendarScreen.add(menu);

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

        calendarTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model = (DefaultTableModel) calendarTable.getModel();
                selectedRow = calendarTable.getSelectedRow();
                selectedCol = calendarTable.getSelectedColumn();
                int selectedDay;
                String input = calendarTable.getValueAt(selectedRow, selectedCol).toString();
                System.out.println(input);
                if (input.contains("html")) {
                    int startIndex = input.indexOf(">");
                    int endIndex = input.indexOf("<", startIndex);
                    String day = input.substring(startIndex + 1, endIndex);
                    System.out.println(day);
                    selectedDay = Integer.parseInt(day);
                } else {
                    selectedDay = (int) calendarTable.getValueAt(selectedRow, selectedCol);
                }

                moodSelectorDialog = new JDialog();
                moodSelectorDialog.setTitle("Select Mood");
                moodSelectorDialog.setVisible(true);
                moodSelectorDialog.setResizable(false);
                moodSelectorDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                moodSelectorDialog.setBounds(50,100,300,600);

                try {
                    moodSelectorDialog.add(moodSelector(todayDate, selectedDay));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    public void refreshTable(String selectedMood) {
        moodSelectorDialog.setVisible(false);
        String inputString = model.getValueAt(selectedRow, selectedCol).toString();
        if (inputString.contains("html")) {
            int startIndex = inputString.indexOf(">");
            int endIndex = inputString.indexOf("<", startIndex);
            inputString = inputString.substring(startIndex + 1, endIndex);
        }
        switch (selectedMood) {
            case "Perfect":
                model.setValueAt("<html>" + inputString + "<p style=\"text-align:center;color:#02f702;font-size: 20px;\"> ● " + "</p> </html>" ,selectedRow, selectedCol);
                break;
            case "Good":
                model.setValueAt("<html>" + inputString + "<p style=\"text-align:center;color:#0c400c;font-size: 20px;\"> ● " + "</p> </html>" ,selectedRow, selectedCol);
                break;
            case "Meh":
                model.setValueAt("<html>" + inputString + "<p style=\"text-align:center;color:#104e85;font-size: 20px;\"> ● " + "</p> </html>" ,selectedRow, selectedCol);
                break;
            case "Bad":
                model.setValueAt("<html>" + inputString + "<p style=\"text-align:center;color:#ba3811;font-size: 20px;\"> ● " + "</p> </html>" ,selectedRow, selectedCol);
                break;
            case "Awful":
                model.setValueAt("<html>" + inputString + "<p style=\"text-align:center;color:#ba1111;font-size: 20px;\"> ● " + "</p> </html>" ,selectedRow, selectedCol);
                break;
        }
        System.out.println(model.getValueAt(selectedRow, selectedCol).toString());

    }

    public JPanel moodSelector(LocalDate selectedDate, Integer selectedDay) throws IOException {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setVisible(true);
        panel.setBackground(new Colors().backgroundColor);
        panel.setBounds(0,760,300,600);

        JLabel monthLabel = getMonthLabel(selectedDate);
        JLabel dayLabel = getDayLabel(selectedDay);
        JLabel dayQuestionLabel = getDialogLabels().get(0);
        JLabel noteLabel = getDialogLabels().get(1);

        JComboBox<String> moodComboBox = getComboBox();
        JTextArea noteTextField = getTextField();
        JButton saveButton = getDialogButtons().get(0);

        panel.add(monthLabel);
        panel.add(dayLabel);
        panel.add(dayQuestionLabel);
        panel.add(noteLabel);

        panel.add(moodComboBox);
        panel.add(noteTextField);
        panel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMood = (String) moodComboBox.getSelectedItem();
                refreshTable(selectedMood);

            }
        });

        return panel;
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
            dayNames.get(i).setBackground(new Colors().secondaryBackgroundColor);
            dayNames.get(i).setBounds(i * 50, 0, 50, 60);

            JLabel dayNameLabel = dayNamesLabel.get(i);
            dayNameLabel.setVisible(true);
            dayNameLabel.setForeground(Color.BLACK);
            dayNameLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
            dayNameLabel.setForeground(new Colors().textColor);
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

        calendarTable.setRowSelectionAllowed(false);
        calendarTable.setCellSelectionEnabled(false);
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
        calendarTable.setForeground(new Colors().textColor);

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
        usernameLabel.setForeground(new Colors().textColor);
        usernameLabel.setBounds(260,70,414,100);

        calendarScreen.add(usernameLabel);
    }

    private JLabel getMonthLabel(LocalDate selectedDate) {
        JLabel monthLabel = new JLabel();
        monthLabel.setText(String.valueOf(selectedDate.getMonth()));
        monthLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
        monthLabel.setForeground(new Colors().textColor);
        monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        monthLabel.setVerticalAlignment(SwingConstants.TOP);
        monthLabel.setBounds(-25,20,300,600);
        return monthLabel;
    }

    private JLabel getDayLabel(Integer selectedDay) {
        JLabel dayLabel = new JLabel();
        dayLabel.setText(String.valueOf(selectedDay));
        dayLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
        dayLabel.setForeground(new Colors().textColor);
        dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dayLabel.setVerticalAlignment(SwingConstants.TOP);
        dayLabel.setBounds(25,20,300,600);
        return dayLabel;
    }

    private List<JLabel> getDialogLabels() {
        JLabel dayQuestionLabel = new JLabel();
        dayQuestionLabel.setText("How was your day? ");
        dayQuestionLabel.setFont(new Font("Georgia", Font.PLAIN, 25));
        dayQuestionLabel.setForeground(new Colors().textColor);
        dayQuestionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dayQuestionLabel.setVerticalAlignment(SwingConstants.TOP);
        dayQuestionLabel.setBounds(0,60,300,600);

        JLabel noteLabel = new JLabel();
        noteLabel.setText("Note ");
        noteLabel.setFont(new Font("Georgia", Font.PLAIN, 25));
        noteLabel.setForeground(new Colors().textColor);
        noteLabel.setHorizontalAlignment(SwingConstants.LEFT);
        noteLabel.setVerticalAlignment(SwingConstants.TOP);
        noteLabel.setBounds(40,160,300,600);

        List<JLabel> labels = new ArrayList<>(Arrays.asList(
                dayQuestionLabel, noteLabel
        ));
        return labels;
    }

    private JLabel getCurrentMonthLabel(LocalDate todayDate) {
        currentMonthLabel.setText(String.valueOf(todayDate.getMonth()));
        currentMonthLabel.setFont(new Font("Georgia", Font.PLAIN, 30));
        currentMonthLabel.setForeground(new Colors().textColor);
        currentMonthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentMonthLabel.setBounds(0,180,414,100);
        return currentMonthLabel;
    }

    private JLabel getCurrentYearLabel(LocalDate todayDate) {
        currentYearLabel.setText(String.valueOf(todayDate.getYear()));
        currentYearLabel.setFont(new Font("Georgia", Font.PLAIN, 25));
        currentYearLabel.setForeground(new Colors().textColor);
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
        welcomeLabel.setForeground(new Colors().textColor);
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
        prevMonthButton.setForeground(new Colors().textColor);;
        prevMonthButton.setBackground(new Colors().secondaryBackgroundColor);
        prevMonthButton.setBounds(60, 190, 70, 70 );
        prevMonthButton.setOpaque(false);
        prevMonthButton.setBorderPainted(false);

        JButton nextMonthButton = new JButton();
        nextMonthButton.setText("->");
        nextMonthButton.setFont(new Font("Georgia", Font.PLAIN, 30));
        nextMonthButton.setForeground(new Colors().textColor);;
        nextMonthButton.setBackground(new Colors().secondaryBackgroundColor);
        nextMonthButton.setBounds(280, 190, 70, 70 );
        nextMonthButton.setOpaque(false);
        nextMonthButton.setBorderPainted(false);

        List<JButton> buttons = new ArrayList<>(Arrays.asList(prevMonthButton, nextMonthButton));
        return buttons;
    }

    private List<JButton> getDialogButtons() throws IOException {
        saveButton = new JButton("Save");
        saveButton.setFocusPainted(false);
        saveButton.setVisible(true);
        saveButton.setBackground(new Colors().secondaryBackgroundColor);
        saveButton.setBorder(BorderFactory.createLineBorder(new Colors().secondaryBackgroundColor));
        saveButton.setHorizontalAlignment(SwingConstants.CENTER);
        saveButton.setBounds(80, 510, 100, 40);

        List<JButton> buttons =  new ArrayList<>(Arrays.asList(
                saveButton));
        return buttons;



    }

    private JComboBox<String> getComboBox() {
        String[] moods = {"Perfect", "Good", "Meh", "Bad", "Awful"};

        moodComboBox = new JComboBox<>(moods);
        moodComboBox.setFont(new Font("Georgia", Font.PLAIN, 20));
        moodComboBox.setForeground(new Colors().textColor);
        moodComboBox.setEditable(false);
        moodComboBox.setSelectedIndex(0);
        moodComboBox.setOpaque(false);
        moodComboBox.setBounds(50,100,200,50);
        moodComboBox.setBackground(Color.WHITE);
        moodComboBox.setVisible(true);

        return moodComboBox;
    }

    private JTextArea getTextField() {
        JTextArea noteText = new JTextArea();
        noteText.setFont(new Font("Georgia", Font.PLAIN, 20));
        noteText.setForeground(new Colors().textColor);
        noteText.setLineWrap(true);
        noteText.setWrapStyleWord(true);
        noteText.setBounds(10, 200, 265, 300);

        return noteText;
    }

}
