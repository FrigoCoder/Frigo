
package frigo.filter;

public abstract class ResamplingKernel extends Kernel {

    public ResamplingKernel (double radius) {
        super(radius);
    }

    public ResamplingKernel (double left, double right) {
        super(left, right);
    }
}
