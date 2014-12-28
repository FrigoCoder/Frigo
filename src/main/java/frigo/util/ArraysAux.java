
package frigo.util;

import static com.google.common.base.Preconditions.checkArgument;
import static frigo.math.integer.MathAux.sqr;

import java.util.ArrayList;
import java.util.List;

import frigo.math.Complex;

public class ArraysAux {

    private static char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

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

    public static byte[] encodeInByteArray (double[] v) {
        byte[] result = new byte[v.length * 8];
        for( int i = 0; i < v.length; i++ ){
            long bits = Double.doubleToLongBits(v[i]);
            for( int j = 0; j < 8; j++ ){
                result[8 * i + j] = (byte) (bits & 0xff);
                bits >>>= 8;
            }
        }
        return result;
    }

    public static byte[] bytes (int... array) {
        byte[] result = new byte[array.length];
        for( int i = 0; i < result.length; i++ ){
            result[i] = (byte) array[i];
        }
        return result;
    }

    public static String toHexString (byte[] bytes) {
        char[] result = new char[bytes.length * 2];
        int position = 0;
        for( byte b : bytes ){
            result[position++] = hexArray[b >> 4 & 0xf];
            result[position++] = hexArray[b & 0xf];
        }
        return new String(result);
    }

}
