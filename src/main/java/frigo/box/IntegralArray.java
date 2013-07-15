
package frigo.box;

public class IntegralArray {

    private final int n;
    private final double[] integral;

    public IntegralArray (double[] source) {
        n = source.length;
        integral = new double[n + 1];
        for( int x = 1; x < integral.length; x++ ){
            integral[x] = integral[x - 1] + source[x - 1];
        }
    }

    public double getAverageInside (Box box) {
        return getSumInside(box) / box.getCount();
    }

    public double getAverageOutside (Box box) {
        int countOutside = n - box.getCount();
        if( countOutside == 0 ){
            return 0.0;
        }
        return getSumOutside(box) / countOutside;
    }

    public double getSumInside (Box box) {
        return integral[box.right + 1] - integral[box.left];
    }

    public double getSumOutside (Box box) {
        return integral[n] - getSumInside(box);
    }

}
