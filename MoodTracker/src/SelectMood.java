import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectMood {
    public JPanel moodSelector(LocalDate selectedDate, Integer selectedDay) throws IOException {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setVisible(true);
        panel.setBackground(new Colors().backgroundColor);
        panel.setBounds(0,760,300,600);

        JLabel monthLabel = getMonthLabel(selectedDate);
        JLabel dayLabel = getDayLabel(selectedDay);
        JLabel dayQuestionLabel = getLabels().get(0);
        JLabel noteLabel = getLabels().get(1);

        JComboBox<String> moodComboBox = getComboBox();
        JTextArea noteTextField = getTextField();
        JButton saveButton = getButtons().get(0);

        panel.add(monthLabel);
        panel.add(dayLabel);
        panel.add(dayQuestionLabel);
        panel.add(noteLabel);

        panel.add(moodComboBox);
        panel.add(noteTextField);
        panel.add(saveButton);

        return panel;
    }
    // Limit max characters
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


    private List<JLabel> getLabels() {
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

    private JComboBox<String> getComboBox() {
        String[] moods = {"Perfect", "Good", "Meh", "Bad", "Awful"};

        JComboBox<String> moodComboBox = new JComboBox<>(moods);
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

    private List<JButton> getButtons() throws IOException {
        JButton saveButton = new JButton("Save");
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
}
