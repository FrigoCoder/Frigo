
package frigo.util;

public class Minimum<T> {

    public T bestObject;
    public double bestValue;

    public void add (T object, double score) {
        if( bestObject == null || bestValue > score ){
            bestObject = object;
            bestValue = score;
        }
    }

}
