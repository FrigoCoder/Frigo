
package frigo.filter;

public class DirichletNaive extends Dirichlet {

    private final int n;

    public DirichletNaive () {
        this(3);
    }

    public DirichletNaive (int n) {
        super(n);
        this.n = n;
    }

    @Override
    public double evaluate (double t) {
        if( !isInDomain(t) ){
            return 0.0;
        }
        double x = piPerRadius * t;
        double result = 0.5 + 0.5 * Math.cos(n * x);
        for( int i = 1; i < n; i++ ){
            result += Math.cos(i * x);
        }
        return result / radius;
    }
}
