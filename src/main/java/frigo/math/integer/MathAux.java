
package frigo.math.integer;

import frigo.math.Complex;

public class MathAux {

    public static double sinc (double x) {
        if( x == 0.0 ){
            return 1.0;
        }
        double piX = Math.PI * x;
        return Math.sin(piX) / piX;
    }

    public static float sinc (float x) {
        if( x == 0.0 ){
            return 1.0f;
        }
        double piX = Math.PI * x;
        return (float) (Math.sin(piX) / piX);
    }

    public static Complex sqr (Complex complex) {
        return complex.sqr();
    }

    public static double sqr (double x) {
        return x * x;
    }

    public static float sqr (float x) {
        return x * x;
    }

    public static int ceil (double x) {
        return (int) Math.round(Math.ceil(x));
    }

    public static int floor (double x) {
        return (int) Math.round(Math.floor(x));
    }

}
