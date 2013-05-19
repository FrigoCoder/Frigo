
package frigo.electronics;

public class Minimum<T> {

    public T bestObject;
    public double bestValue;

    public void add (T object, double value) {
        if( bestObject == null || bestValue > value ){
            bestObject = object;
            bestValue = value;
        }
    }

}
