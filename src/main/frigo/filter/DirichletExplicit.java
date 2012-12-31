
package frigo.filter;

public class DirichletExplicit {

    public static Dirichlet get () {
        return get(3);
    }

    public static Dirichlet get (int n) {
        switch( n ){
            case 1:
                return getDirichlet1();
            case 2:
                return getDirichlet2();
            case 3:
                return getDirichlet3();
            case 4:
                return getDirichlet4();
            default:
                return new DirichletTrigonometric(n);
        }
    }

    private static Dirichlet getDirichlet1 () {
        return new Dirichlet(1.0) {

            @Override
            public double evaluate (double t) {
                if( !isInDomain(t) ){
                    return 0.0;
                }
                double c = Math.cos(piPerRadius * t);
                return 0.5 * c + 0.5;
            }
        };
    }

    private static Dirichlet getDirichlet2 () {
        return new Dirichlet(2.0) {

            @Override
            public double evaluate (double t) {
                if( !isInDomain(t) ){
                    return 0.0;
                }
                double c = Math.cos(piPerRadius * t);
                return (0.5 * c + 0.5) * c;
            }
        };
    }

    private static Dirichlet getDirichlet3 () {
        return new Dirichlet(3.0) {

            @Override
            public double evaluate (double t) {
                if( !isInDomain(t) ){
                    return 0.0;
                }
                double c = Math.cos(piPerRadius * t);
                return 2.0 / 3.0 * (c * c - 0.25) * (c + 1.0);
            }
        };
    }

    private static Dirichlet getDirichlet4 () {
        return new Dirichlet(4.0) {

            @Override
            public double evaluate (double t) {
                if( !isInDomain(t) ){
                    return 0.0;
                }
                double c = Math.cos(piPerRadius * t);
                double c2 = c * c;
                return (c2 + c) * (c2 - 0.5);
            }
        };
    }
}
