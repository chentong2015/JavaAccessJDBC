package view;

import javax.swing.*;
import java.awt.*;

public class GcrWindows {

    private final static String TITLE_NAME = "Demo Access Management Tool";

    public GcrWindows(String message) {
        JFrame frame = new JFrame(TITLE_NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 300));
        frame.pack();
        JLabel label = new JLabel(message, JLabel.CENTER);
        frame.getContentPane().add(label);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
