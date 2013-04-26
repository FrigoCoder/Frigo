
package frigo.util;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Math.signum;

import com.google.common.base.Function;

public class Bisection {

    public static double bisect (Function<Double, Double> function, double left, double right) {
        return new Bisection(function, left, right).calculateRoot();
    }

    private Function<Double, Double> function;

    private double left;
    private double mid;
    private double right;

    private double leftValue;
    private double midValue;
    private double rightValue;

    public Bisection (Function<Double, Double> function, double left, double right) {
        this.function = function;
        setLeft(left);
        setRight(right);
        updateMid();
        checkArgument(left < right);
        checkArgument(leftValue * rightValue <= 0);
    }

    private double calculateRoot () {
        while( precisionStillHolds() ){
            if( midValue == 0 ){
                return mid;
            }
            if( signum(leftValue) == signum(midValue) ){
                setLeft(mid);
            }else{
                setRight(mid);
            }
            updateMid();
        }
        return mid;
    }

    private boolean precisionStillHolds () {
        return left < mid && mid < right;
    }

    private void setLeft (double left) {
        this.left = left;
        leftValue = function.apply(left);
    }

    private void setRight (double right) {
        this.right = right;
        rightValue = function.apply(right);
    }

    private void updateMid () {
        mid = (left + right) / 2;
        midValue = function.apply(mid);
    }

}
