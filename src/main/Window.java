package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window {

    Window(Dimension dimension, String header) {
        var frame = new JFrame(header);

        frame.setSize(dimension);
        frame.setLocationRelativeTo(null);

        frame.setBackground(Color.getHSBColor(0.4f, 1, 0.2f));

//        frame.add(canvas);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX());
                super.mouseClicked(e);
            }
        });
    }

    Window(Dimension dimension, String header, Canvas canvas) {
        var frame = new JFrame(header);

        frame.setSize(dimension);
        frame.setLocationRelativeTo(null);

        frame.setBackground(Color.getHSBColor(0.4f, 1, 0.2f));

        frame.add(canvas);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
