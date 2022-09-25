package main;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;

public class Application {
    public static void main(String[] args) {

        Dimension dimension = new Dimension(1600, 1200);

        Pen pen = new Pen();
        pen.setDimension(dimension);
        pen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                pen.start(e.getKeyChar());
            }
        });

        pen.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pen.start(new Dimension(e.getX(), e.getY()));
            }
        });

//        pen.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                pen.start(new Dimension(e.getX(), e.getY()));
//            }
//        });

        var window = new Window(dimension, "Mandelbrot", pen);
    }
}
