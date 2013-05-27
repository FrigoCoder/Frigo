
package frigo.util;

import java.util.NoSuchElementException;

public class BinarySearch {

    public static int findExact (double[] array, double value) {
        int found = new BinarySearch(array, value).find();
        if( array[found] == value ){
            return found;
        }
        throw new NoSuchElementException();
    }

    public static int findLower (double[] array, double value) {
        int found = new BinarySearch(array, value).find();
        if( array[found] <= value ){
            return found;
        }
        found--;
        if( found < 0 ){
            throw new NoSuchElementException();
        }
        return found;
    }

    public static int findHigher (double[] array, double value) {
        int found = new BinarySearch(array, value).find();
        if( array[found] >= value ){
            return found;
        }
        found++;
        if( found >= array.length ){
            throw new NoSuchElementException();
        }
        return found;
    }

    private double[] array;
    private double value;

    private int left;
    private int right;
    private int mid;

    private BinarySearch (double[] array, double value) {
        this.array = array;
        this.value = value;
        setInterval(0, array.length - 1);
    }

    private int find () {
        while( left < right ){
            if( valueIsInLeftInterval() ){
                setInterval(left, mid);
            }else{
                setInterval(mid + 1, right);
            }
        }
        return mid;
    }

    private boolean valueIsInLeftInterval () {
        return value <= array[mid];
    }

    private void setInterval (int left, int right) {
        this.left = left;
        this.right = right;
        mid = (left + right) / 2;
    }

}
