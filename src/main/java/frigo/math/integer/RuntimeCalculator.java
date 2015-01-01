
package frigo.math.integer;

public class RuntimeCalculator {

    private double max = 0;
    private double sum = 0;
    private int count = 0;

    public void feed (long n, long steps) {
        double runtime = calc(n, steps);
        if( max < runtime ){
            System.out.println("Found largest n=" + n + ", steps=" + steps);
            max = calc(n, steps);
        }
        sum += calc(n, steps);
        count++;
    }

    private double calc (long n, long steps) {
        return steps / Math.sqrt(n);
    }

    public void print () {
        System.out.println("Largest=" + max);
        System.out.println("Average=" + sum / count);
    }

}
