
package frigo.filter;

public abstract class Dirichlet extends ResamplingKernel {

    protected final double halfPerRadius;
    protected final double onePerRadius;
    protected final double piPerRadius;

    public Dirichlet (double radius) {
        super(radius);
        halfPerRadius = 0.5 / radius;
        onePerRadius = 1.0 / radius;
        piPerRadius = Math.PI / radius;
    }
}
