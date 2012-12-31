
package frigo.filter;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class Kernel {

    public final double left;
    public final double radius;
    public final double right;

    public Kernel (double radius) {
        if( radius < 0.0 ){
            throw new IllegalArgumentException("Radius must be positive or zero");
        }
        this.radius = radius;
        left = -radius;
        right = radius;
    }

    public Kernel (double left, double right) {
        checkArgument(left <= 0.0, "Left must be negative or zero");
        checkArgument(right >= 0.0, "Right must be positive or zero");
        radius = Math.max(-left, right);
        this.left = left;
        this.right = right;
    }

    public abstract double evaluate (double x);

    protected boolean isInDomain (double x) {
        return left < x && x < right;
    }
}
