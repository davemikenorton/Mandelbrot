package main;

import javax.swing.*;
import java.awt.*;

public class Window {

    Window(Dimension dimension, String header, Canvas canvas) {
        var frame = new JFrame(header);

        frame.setSize(dimension);
        frame.setLocationRelativeTo(null);

//        frame.setBackground(Color.getHSBColor(0.4f, 1, 0.2f));

        frame.add(canvas);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
