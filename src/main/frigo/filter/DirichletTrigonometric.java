
package frigo.filter;

public class DirichletTrigonometric extends Dirichlet {

    public DirichletTrigonometric () {
        this(3.0);
    }

    public DirichletTrigonometric (double radius) {
        super(radius);
    }

    @Override
    public double evaluate (double t) {
        if( !isInDomain(t) ){
            return 0.0;
        }
        if( t != 0.0 ){
            double x = piPerRadius * t;
            return halfPerRadius * (1.0 + Math.cos(x)) * Math.sin(radius * x) / Math.sin(x);
        }
        return 1.0;
    }
}
