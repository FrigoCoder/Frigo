
package frigo;

public class IntegralArray {

    protected final double[] integral;
    private final int sourceLength;

    public IntegralArray (double[] source) {
        sourceLength = source.length;
        integral = new double[source.length + 1];
        for( int x = 1; x < integral.length; x++ ){
            integral[x] = integral[x - 1] + source[x - 1];
        }
    }

    public double getAverage (Box box) {
        int count = getCount(box);
        if( count == 0 ){
            return 0.0;
        }
        return getSum(box) / count;
    }

    public double getAverageOutside (Box box) {
        int count = getCountOutside(box);
        if( count == 0 ){
            return 0.0;
        }
        return getSumOutside(box) / count;
    }

    public double getSum (Box box) {
        checkInterval(box);
        return integral[box.right + 1] - integral[box.left];
    }

    public double getSumOutside (Box box) {
        return integral[sourceLength] - getSum(box);
    }

    protected void checkInterval (Box box) {
        if( box.left < 0 || box.left > box.right || box.right >= sourceLength ){
            throw new IllegalArgumentException("Invalid interval");
        }
    }

    protected int getCount (Box box) {
        checkInterval(box);
        return box.right + 1 - box.left;
    }

    protected int getCountOutside (Box box) {
        return sourceLength - getCount(box);
    }
}
