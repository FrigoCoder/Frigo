
package frigo.util;

import static com.google.common.base.Preconditions.checkArgument;
import static frigo.math.MathAux.sqr;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import frigo.math.Complex;

public class ArraysAux {

    @SafeVarargs
    public static <T> ArrayList<T> asArrayList (T... elements) {
        ArrayList<T> result = new ArrayList<>();
        for( T element : elements ){
            result.add(element);
        }
        return result;
    }

    @SafeVarargs
    public static <T> Vector<T> asVector (T... elements) {
        Vector<T> result = new Vector<>();
        for( T element : elements ){
            result.add(element);
        }
        return result;
    }

    public static Complex[] getRandomComplexArray (int length) {
        return getRandomComplexArray(length, new Random());
    }

    public static Complex[] getRandomComplexArray (int length, Random random) {
        Complex[] array = new Complex[length];
        for( int i = 0; i < array.length; i++ ){
            array[i] = new Complex(random.nextDouble(), random.nextDouble());
        }
        return array;
    }

    public static double[] getRandomDoubleArray (int length) {
        return getRandomDoubleArray(length, new Random());
    }

    public static double[] getRandomDoubleArray (int length, Random random) {
        double[] array = new double[length];
        for( int i = 0; i < array.length; i++ ){
            array[i] = random.nextDouble();
        }
        return array;
    }

    public static double squaredEuclideanDistance (Complex[] v, Complex[] w) {
        checkArgument(v.length == w.length);
        double distance = 0.0;
        for( int i = 0; i < v.length; i++ ){
            distance += v[i].sub(w[i]).sqrAbs();
        }
        return distance;
    }

    public static double squaredEuclideanDistance (double[] v, double[] w) {
        checkArgument(v.length == w.length);
        double distance = 0.0;
        for( int i = 0; i < v.length; i++ ){
            distance += sqr(v[i] - w[i]);
        }
        return distance;
    }

    public static List<Integer> toList (int[] array) {
        List<Integer> list = new ArrayList<>();
        for( int value : array ){
            list.add(value);
        }
        return list;
    }

    public static <T> T[] toObjectArray (T[] array) {
        return array;
    }

    public static Byte[] toObjectArray (byte[] array) {
        Byte[] result = new Byte[array.length];
        for( int i = 0; i < result.length; i++ ){
            result[i] = array[i];
        }
        return result;
    }

    public static Short[] toObjectArray (short[] array) {
        Short[] result = new Short[array.length];
        for( int i = 0; i < result.length; i++ ){
            result[i] = array[i];
        }
        return result;
    }

    public static Integer[] toObjectArray (int[] array) {
        Integer[] result = new Integer[array.length];
        for( int i = 0; i < result.length; i++ ){
            result[i] = array[i];
        }
        return result;
    }

    public static Long[] toObjectArray (long[] array) {
        Long[] result = new Long[array.length];
        for( int i = 0; i < result.length; i++ ){
            result[i] = array[i];
        }
        return result;
    }

    public static Float[] toObjectArray (float[] array) {
        Float[] result = new Float[array.length];
        for( int i = 0; i < result.length; i++ ){
            result[i] = array[i];
        }
        return result;
    }

    public static Double[] toObjectArray (double[] array) {
        Double[] result = new Double[array.length];
        for( int i = 0; i < result.length; i++ ){
            result[i] = array[i];
        }
        return result;
    }

    public static Boolean[] toObjectArray (boolean[] array) {
        Boolean[] result = new Boolean[array.length];
        for( int i = 0; i < result.length; i++ ){
            result[i] = array[i];
        }
        return result;
    }

    public static Character[] toObjectArray (char[] array) {
        Character[] result = new Character[array.length];
        for( int i = 0; i < result.length; i++ ){
            result[i] = array[i];
        }
        return result;
    }

}
