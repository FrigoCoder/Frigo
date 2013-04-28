
package frigo.util;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Function;

public class Bisection {

    public static double bisect (Function<Double, Double> function, double left, double right) {
        return new Bisection(function, left, right, null).calculateRoot();
    }

    public static double bisectIncreasing (Function<Double, Double> function, double left, double right) {
        return new Bisection(function, left, right, true).calculateRoot();
    }

    public static double bisectDecreasing (Function<Double, Double> function, double left, double right) {
        return new Bisection(function, left, right, false).calculateRoot();
    }

    private Function<Double, Double> function;
    private Boolean increasing;
    private double left;
    private double mid;
    private double right;
    private double midValue;

    private Bisection (Function<Double, Double> function, double left, double right, Boolean increasing) {
        checkArgument(left < right);
        if( increasing == null ){
            checkArgument(function.apply(left) * function.apply(right) <= 0);
        }
        this.function = function;
        this.left = left;
        this.right = right;
        updateMid();
        this.increasing = increasing;
    }

    private double calculateRoot () {
        while( precisionStillHolds() ){
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
        if( increasing == null ){
            return function.apply(left) * midValue > 0;
        }
        return increasing ^ midValue > 0;
    }

    private void updateMid () {
        mid = (left + right) / 2;
        midValue = function.apply(mid);
    }

}
