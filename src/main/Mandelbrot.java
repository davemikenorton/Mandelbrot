package main;

import java.awt.*;

import org.apache.commons.numbers.complex.Complex;


public class Mandelbrot {

    private Complex oldZ = Complex.ofPolar(0, 0);

    private final Complex c;

    public Mandelbrot(Complex c) {
        this.c = c;
    }

    public Complex create() {

        oldZ = (oldZ.pow(2)).add(c);

        if (oldZ.abs() > 10000) throw new RuntimeException("Абсолютное значение превысило 10000");

        return oldZ;
    }
}
