
package frigo.math;

public class DirichletKernelPolynomial {

    private static ChebyshevPolynomial chebyshev = new ChebyshevPolynomial();

    /**
     * Returns the analytical Dirichlet kernel based on the definition at <a
     * href=http://en.wikipedia.org/wiki/Dirichlet_kernel>Wikipedia</a>, using trigonometric linearization of cosine
     * terms to arrive at a polynomial in terms of cos(x)
     **/
    public static Polynomial dirichlet (int n) {
        Polynomial dirichlet = chebyshev.chebyshev(0);
        for( int i = 1; i <= n; i++ ){
            dirichlet = dirichlet.add(chebyshev.chebyshev(i).mul(2.0));
        }
        return dirichlet;
    }

    /**
     * Returns the Fejér kernel based on the definition at <a
     * href=http://en.wikipedia.org/wiki/Fej%C3%A9r_kernel>Wikipedia</a>, built from Dirichlet kernels, as a polynomial
     * in terms of cos(x).
     **/
    public static Polynomial fejer (int n) {
        Polynomial fejer = dirichlet(0);
        for( int i = 1; i < n; i++ ){
            fejer = fejer.add(dirichlet(i));
        }
        return fejer.div(n);
    }

    /**
     * Returns the <a href=http://frigocoder.dyndns.org/wiki/Modified_Dirichlet_Kernel>modified Dirichlet kernel</a>, as
     * a polynomial in terms of cos(x), without the domain restriction. Apart from that, it differs from the analytical
     * kernel in the n-th term and scaling
     **/
    public static Polynomial modifiedDirichlet (int n) {
        Polynomial dirichlet = chebyshev.chebyshev(0).add(chebyshev.chebyshev(n)).mul(0.5);
        for( int i = 1; i < n; i++ ){
            dirichlet = dirichlet.add(chebyshev.chebyshev(i));
        }
        return dirichlet.div(n);
    }
}
