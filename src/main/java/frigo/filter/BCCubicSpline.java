
package frigo.filter;

/**
 * Implemented according to <a href="http://www.cg.tuwien.ac.at/~theussl/DA/node33.html">Sampling and Reconstruction in
 * Volume Visualization</a>.
 */
public class BCCubicSpline extends SymmetricCubic {

    public BCCubicSpline (double b, double c) {
        p = (12 - 9 * b - 6 * c) / 6;
        q = (-18 + 12 * b + 6 * c) / 6;
        r = 0;
        s = (6 - 2 * b) / 6;
        t = (-b - 6 * c) / 6;
        u = (6 * b + 30 * c) / 6;
        v = (-12 * b - 48 * c) / 6;
        w = (8 * b + 24 * c) / 6;
    }
}
