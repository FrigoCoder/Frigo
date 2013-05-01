
package frigo.util;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Function;

public class Bisection {

    public static double bisect (Function<Double, Double> function, double left, double right) {
        return new Bisection(function, left, right).calculateRoot();
    }

    private Function<Double, Double> function;
    private double left;
    private double right;
    private double mid;
    private double midValue;

    private Bisection (Function<Double, Double> function, double left, double right) {
        this.function = function;
        setInterval(left, right);
        checkArgument(left < right);
        checkArgument(rootIsInInterval(f(left), f(right)), "Root can not be in interval, values at boundaries are: "
            + f(left) + ", " + f(right));
    }

    private void setInterval (double left, double right) {
        this.left = left;
        this.right = right;
        mid = (left + right) / 2;
        midValue = f(mid);
    }

    private boolean rootIsInInterval (double leftValue, double rightValue) {
        return leftValue * rightValue <= 0;
    }

    private double f (double x) {
        return function.apply(x);
    }

    private double calculateRoot () {
        while( precisionStillHolds() ){
            if( rootIsInInterval(f(left), midValue) ){
                setInterval(left, mid);
            }else{
                setInterval(mid, right);
            }
        }
        return mid;
    }

    private boolean precisionStillHolds () {
        return left < mid && mid < right;
    }

}
