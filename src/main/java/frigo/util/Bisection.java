
package frigo.util;

import static com.google.common.base.Preconditions.checkArgument;
import static frigo.math.MathAux.xor;

import com.google.common.base.Function;

public class Bisection {

    public static double bisect (Function<Double, Double> function, double left, double right) {
        return new Bisection(function, left, right).calculateRoot();
    }

    public static double bisectIncreasing (Function<Double, Double> function, double left, double right) {
        return new Bisection(function, left, right, true).calculateRoot();
    }

    public static double bisectDecreasing (Function<Double, Double> function, double left, double right) {
        return new Bisection(function, left, right, false).calculateRoot();
    }

    private Function<Double, Double> function;
    private boolean increasing;

    private double left;
    private double mid;
    private double right;

    private double midValue;

    public Bisection (Function<Double, Double> function, double left, double right) {
        this(function, left, right, function.apply(left) <= 0);
        checkArgument(function.apply(left) * function.apply(right) <= 0);
    }

    public Bisection (Function<Double, Double> function, double left, double right, boolean increasing) {
        checkArgument(left < right);
        this.function = function;
        this.left = left;
        this.right = right;
        updateMid();
        this.increasing = increasing;
    }

    private double calculateRoot () {
        while( precisionStillHolds() ){
            if( midValue == 0 ){
                return mid;
            }
            if( rootIsInRightHalf() ){
                left = mid;
            }else{
                right = mid;
            }
            updateMid();
        }
        return mid;
    }

    private boolean precisionStillHolds () {
        return left < mid && mid < right;
    }

    private boolean rootIsInRightHalf () {
        return xor(increasing, midValue > 0);
    }

    private void updateMid () {
        mid = (left + right) / 2;
        midValue = function.apply(mid);
    }

}
