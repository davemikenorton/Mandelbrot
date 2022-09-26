package main;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.numbers.complex.Complex;

import java.awt.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
public class Pen extends Canvas {

    private Dimension mouseCursor = new Dimension(0, 0);

    private Dimension dimension;

    private Graphics g;

    private Long rank = 300L;

    @Override
    public void paint(Graphics graphics) {

        System.out.println("Paint was started");


        this.g = graphics;

        setBackground(Color.getHSBColor(0.4f, 1, 0.2f));
        setForeground(Color.getHSBColor(0.4f, 1, 0.9f));

        var minX = -dimension.width / 2;
        var maxX = dimension.width / 2;
        var minY = -dimension.height / 2;
        var maxY = dimension.height / 2;

        int step = 1;

        List<Integer> range = IntStream.range(0, 100).boxed().toList();

        for (int x = minX; x < maxX; x = x + step) {
            final int xx = x;
            for (int y = minY; y < maxY; y = y + step) {
                final int yy = y;
                Mandelbrot mandelbrot = new Mandelbrot();
                Complex z = null;

//                Complex z = range.parallelStream().map(i -> {
//                    Complex zz = mandelbrot.create(convertDimensionToComplex(new Dimension(xx + this.mouseCursor.width, yy + this.mouseCursor.height), this.rank));
//
//                    return zz;
//                }).reduce((first, second) -> second).orElse(null);
//                drawDot(new Dimension(xx, yy), Color.getHSBColor(0.1f, 1, (float) z.abs() * 0.8f));


                for (int i = 0; i < 100; i++) {
                    z = mandelbrot.create(convertDimensionToComplex(new Dimension(x + this.mouseCursor.width, y + this.mouseCursor.height), this.rank));
                    if (z.abs() > 2) {
                        drawDot(new Dimension(x, y), Color.getHSBColor(0.4f, 1, (float) z.abs() / 10));
                        break;
                    }
                }
                if (z.abs() <= 2) {
                    drawDot(new Dimension(x, y), Color.getHSBColor(0.1f, 1, (float) z.abs() * 0.8f));
                    System.out.println(z.abs());
                }
            }
        }
    }

    public void start(char key) {

        double zooming = 1.5;

        this.repaint();

        if (key == 'a') {
            this.rank = Double.valueOf(this.rank * zooming).longValue(); // Math.log10(rank));
            var center = getCenteredDimension();
            System.out.println(mouseCursor.getWidth() + " tttt ");
//            this.mouseCursor.setSize(/*mouseCursor.getWidth() +*/ dimension.width/8, mouseCursor.getHeight());
            System.out.println(rank);
        }

        if (key == 'z') {
            this.rank = Double.valueOf(this.rank / zooming).longValue(); // * Math.log10(rank));
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
    }

    private Complex convertDimensionToComplex(Dimension d, Long rank) {

        return Complex.ofCartesian(d.width / (double) rank, d.height / (double) rank);
    }

    public void start(Dimension cur) {
        this.repaint();
        System.out.println(cur.getWidth() + " xxxx " + cur.getHeight());
        var center = getCenteredDimension();
//        this.mouseCursor.setSize(cur.getWidth() , cur.getHeight() );
        this.mouseCursor.setSize(mouseCursor.getWidth() + cur.getWidth() - center.width, mouseCursor.getHeight() + cur.getHeight() - center.height);
    }

    private Dimension convertComplexToDimension(Complex z, Long rank) {

        return new Dimension((int) (rank * z.getReal()), (int) (rank * z.getImaginary()));
    }

    private void drawDot(Dimension target, Color color) {
        this.g.setColor(color);
        var center = getCenteredDimension();

        this.g.drawLine(center.width + target.width, center.height + target.height, center.width + target.width, center.height + target.height);
    }

    private Dimension getCenteredDimension() {
        return new Dimension(this.dimension.width / 2, this.dimension.height / 2);
//        return new Dimension(this.dimension.width / 2 - this.mouseCursor.width, this.dimension.height / 2 - this.mouseCursor.height);
    }
}
