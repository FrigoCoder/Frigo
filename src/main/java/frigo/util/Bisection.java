
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
    private double leftValue;
    private double rightValue;
    private double midValue;

    private Bisection (Function<Double, Double> function, double left, double right) {
        this.function = function;
        setInterval(left, right, f(left), f(right));
        checkArgument(left < right);
        checkArgument(rootIsInInterval(leftValue, rightValue),
            "Root can not be in interval, values at boundaries are: " + leftValue + ", " + rightValue);
    }

    private double f (double x) {
        return function.apply(x);
    }

    private void setInterval (double left, double right, double leftValue, double rightValue) {
        this.left = left;
        this.leftValue = leftValue;
        this.right = right;
        this.rightValue = rightValue;
        mid = (left + right) / 2;
        midValue = f(mid);
    }

    private boolean rootIsInInterval (double startValue, double endValue) {
        return startValue * endValue <= 0;
    }

    private double calculateRoot () {
        while( precisionStillHolds() ){
            if( rootIsInInterval(leftValue, midValue) ){
                setInterval(left, mid, leftValue, midValue);
            }else{
                setInterval(mid, right, midValue, rightValue);
            }
        }
        return mid;
    }

    private boolean precisionStillHolds () {
        return left < mid && mid < right;
    }

}
