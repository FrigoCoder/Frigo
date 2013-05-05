
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
        this.left = left;
        this.right = right;
        leftValue = f(left);
        rightValue = f(right);
        updateMid();
        checkArgument(left < right);
        checkArgument(rootIsInInterval(leftValue, rightValue),
            "Root can not be in interval, values at boundaries are: " + leftValue + ", " + rightValue);
    }

    private double f (double x) {
        return function.apply(x);
    }

    private void updateMid () {
        mid = (left + right) / 2;
        midValue = f(mid);
    }

    private boolean rootIsInInterval (double startValue, double endValue) {
        return startValue * endValue <= 0;
    }

    private double calculateRoot () {
        while( precisionStillHolds() ){
            if( rootIsInInterval(leftValue, midValue) ){
                restrictToLeftInterval();
            }else{
                restrictToRightInterval();
            }
        }
        return mid;
    }

    private boolean precisionStillHolds () {
        return left < mid && mid < right;
    }

    private void restrictToLeftInterval () {
        right = mid;
        rightValue = midValue;
        updateMid();
    }

    private void restrictToRightInterval () {
        left = mid;
        leftValue = midValue;
        updateMid();
    }

}
