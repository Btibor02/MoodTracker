import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {
    public JPanel navigationMenu(String currentScreen) throws IOException {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setVisible(true);
        panel.setBackground(new Color(236, 203, 124));
        panel.setBounds(0,760,414,100);

        JButton calendarButton = getButtons().get(0);
        JButton otherButton = getButtons().get(1);
        JButton otherSecondButton = getButtons().get(2);

        panel.add(calendarButton);
        panel.add(otherButton);
        panel.add(otherSecondButton);


        return panel;
    }

    private List<JButton> getButtons() throws IOException {
        //https://www.flaticon.com/free-icons/calendar Calendar icons created by Freepik - Flaticon
        JButton calendarButton = new JButton(new ImageIcon("images/calendarIcon.png"));
        calendarButton.setFocusPainted(false);
        calendarButton.setVisible(true);
        calendarButton.setOpaque(false);
        calendarButton.setBackground(new Color(236, 203, 124));
        calendarButton.setBorderPainted(false);
        calendarButton.setBounds(20, 10, 80, 80);

        //<a href="https://www.flaticon.com/free-icons/analysis" title="analysis icons">Analysis icons created by deemakdaksina - Flaticon</a>
        JButton otherButton = new JButton(new ImageIcon("images/analyticsIcon.png"));
        otherButton.setFocusPainted(false);
        otherButton.setVisible(true);
        otherButton.setOpaque(false);
        otherButton.setBackground(new Color(236, 203, 124));
        otherButton.setBorderPainted(false);
        otherButton.setBounds(160, 10, 80, 80);

        JButton otherSecondButton = new JButton(new ImageIcon("images/settingIcon.png"));
        otherSecondButton.setFocusPainted(false);
        otherSecondButton.setVisible(true);
        otherSecondButton.setOpaque(false);
        otherSecondButton.setBackground(new Color(236, 203, 124));
        otherSecondButton.setBorderPainted(false);
        otherSecondButton.setBounds(300, 10, 80, 80);


        List<JButton> buttons =  new ArrayList<>(Arrays.asList(
                calendarButton, otherButton, otherSecondButton));
        return buttons;



    }
}
