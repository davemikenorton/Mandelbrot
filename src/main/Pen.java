package main;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.numbers.complex.Complex;

import java.awt.*;
import java.util.logging.Logger;

@Getter
@Setter
public class Pen extends Canvas {

    private Dimension mouseCursor = new Dimension(0, 0);

    private Dimension dimension;

    private Graphics g;

    private Long rank = 1000L;

    @Override
    public void paint(Graphics graphics) {

        System.out.println("Paint was started");


        this.g = graphics;

        setBackground(Color.getHSBColor(0.4f, 1, 0.2f));
        setForeground(Color.getHSBColor(0.4f, 1, 0.9f));

//        Complex c = Complex.ofPolar(0.3, 0.08321342234);
        Complex c = Complex.ofCartesian(0, 0.5);
//        Complex c = Complex.ofCartesian(-1.7433419053321,0.0000907687489);
//        Complex c = Complex.ofPolar(0.1, 0.00324234534);
//        Complex c = Complex.ofCartesian(0.04,0.04);
//        Complex c = Complex.ofCartesian(-0.777807810193171, 0.131645108003206);
//        Complex c = Complex.ofCartesian(-1.7433419053321, 0.0000907687489);

        Mandelbrot mandelbrot = new Mandelbrot(c);

        var number = 0;

        for (int i = 0; i < 1000000; i++) {
            try {
                number = i;
                drawDot(convertComplexToDimension(mandelbrot.create(), this.rank));
            } catch (RuntimeException e) {
                System.out.println(e.getMessage() + " при количестве циклов: " + i);
                graphics.drawString(e.getMessage() + " при количестве циклов: " + i, 50, 20);
                return;
            }
        }
        System.out.println("Расчет завершен при количестве циклов: " + number);
        graphics.drawString("Расчет завершен при количестве циклов: " + number, 50, 40);
    }

    public void start(char key) {
        this.repaint();

        if (key == 'a') {
            this.rank = (Long) (this.rank * 2); // / Math.log10(rank)/Math.log10(100));
            System.out.println(rank);
        }

        if (key == 'z') {
            this.rank = (Long) (this.rank / 2); // * Math.log10(rank));
            System.out.println(rank);
        }

        if (key == 'x') {
            this.mouseCursor.setSize(mouseCursor.getWidth() + 20, mouseCursor.getHeight());
        }

        if (key == 'c') {
            this.mouseCursor.setSize(mouseCursor.getWidth() - 20, mouseCursor.getHeight());
        }

        if (key == 'w') {
            this.mouseCursor.setSize(mouseCursor.getWidth(), mouseCursor.getHeight() + 20);
        }

        if (key == 's') {
            this.mouseCursor.setSize(mouseCursor.getWidth(), mouseCursor.getHeight() - 20);
        }

//        paint(g);
    }

    public void start(Dimension cur) {
        this.repaint();
        System.out.println(cur.getWidth() + " " + cur.getHeight());
        var center = getCenteredDimension();
        this.mouseCursor.setSize(cur.getWidth() - center.width, cur.getHeight() - center.height);
//        paint(g);
    }

    private Dimension convertComplexToDimension(Complex z, Long rank) {

        return new Dimension((int) (rank * z.getReal()), (int) (rank * z.getImaginary()));
    }

    private void drawDot(Dimension target) {
        var center = getCenteredDimension();
        this.g.drawLine(center.width + target.width, center.height + target.height, center.width + target.width, center.height + target.height);
    }

    private Dimension getCenteredDimension() {
        return new Dimension(this.dimension.width / 2 - this.mouseCursor.width, this.dimension.height / 2 - this.mouseCursor.height);
    }
}
