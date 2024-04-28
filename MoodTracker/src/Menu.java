import javax.swing.*;
import java.io.IOException;

public class Menu {
    private final JButton calendarButton = new JButton(new ImageIcon("images/calendarIcon.png"));
    private final JButton otherButton = new JButton(new ImageIcon("images/analyticsIcon.png"));
    private final JButton otherSecondButton = new JButton(new ImageIcon("images/settingIcon.png"));
    private final JPanel panel = new JPanel();

    public JPanel navigationMenu(String currentScreen) throws IOException {
        setButtons(currentScreen);
        panel.setLayout(null);
        panel.setVisible(true);
        panel.setBackground(new Colors().secondaryBackgroundColor);
        panel.setBounds(0,760,414,100);

        return panel;
    }

    private void setButtons(String currentScreen) throws IOException {
        //https://www.flaticon.com/free-icons/calendar Calendar icons created by Freepik - Flaticon
        calendarButton.setFocusPainted(false);
        calendarButton.setVisible(true);
        calendarButton.setOpaque(false);
        calendarButton.setBorderPainted(true);
        calendarButton.setBackground(new Colors().secondaryBackgroundColor);
        calendarButton.setBorder(BorderFactory.createLineBorder(new Colors().secondaryBackgroundColor));
        calendarButton.setBounds(20, 10, 80, 80);

        //https://www.flaticon.com/free-icons/analysis Analysis icons created by deemakdaksina - Flaticon</a>
        otherButton.setFocusPainted(false);
        otherButton.setVisible(true);
        otherButton.setOpaque(false);
        otherButton.setBorderPainted(false);
        otherButton.setBackground(new Colors().secondaryBackgroundColor);
        otherButton.setBorder(BorderFactory.createLineBorder(new Colors().secondaryBackgroundColor));
        otherButton.setBounds(160, 10, 80, 80);

        otherSecondButton.setFocusPainted(false);
        otherSecondButton.setVisible(true);
        otherSecondButton.setOpaque(false);
        otherSecondButton.setBorderPainted(false);
        otherSecondButton.setBackground(new Colors().secondaryBackgroundColor);
        otherSecondButton.setBorder(BorderFactory.createLineBorder(new Colors().secondaryBackgroundColor));
        otherSecondButton.setBounds(300, 10, 80, 80);

        switch(currentScreen) {
            case "calendar": calendarButton.setBorder(BorderFactory.createLineBorder(new Colors().borderColor, 3));; break;
            case "analytics": otherButton.setBorder(BorderFactory.createLineBorder(new Colors().borderColor, 3));; break;
            case "settings": otherSecondButton.setBorder(BorderFactory.createLineBorder(new Colors().borderColor, 3));; break;
        }

        panel.add(calendarButton);
        panel.add(otherButton);
        panel.add(otherSecondButton);
    }
}
