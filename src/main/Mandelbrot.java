package main;


import org.apache.commons.numbers.complex.Complex;


public class Mandelbrot {

    private Complex oldZ = Complex.ofCartesian(0, 0);

    public Complex create(Complex c) {

//        System.out.println(c);

        oldZ = (oldZ.pow(2)).add(c);

        return oldZ;
    }
}
