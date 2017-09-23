
package frigo.newcrack;

import java.util.List;
import java.util.NoSuchElementException;

import com.google.common.collect.Lists;

public class ExtendedEuclidean {

    public static void main (String[] args) {

        int n = 11 * 17;

        for( int x = 2; x < n - 1; x++ ){
            int invx;
            try{
                invx = getInverse(n, x);
                if( x == invx ){
                    print("Found root!");
                }else{
                    continue;
                }
            }catch( NoSuchElementException e ){
                continue;
            }

            print("n=" + n + ", " + "x=" + x);
            ExtendedEuclidean euclidx = new ExtendedEuclidean(n, x);
            print();

            print("n=" + n + ", " + "inv(" + x + ")=" + invx);
            ExtendedEuclidean euclidinvx = new ExtendedEuclidean(n, invx);
            print();

            print("----");
        }
    }

    private final List<Integer> q;
    private final List<Integer> r;
    private final List<Integer> s;
    private final List<Integer> t;

    public ExtendedEuclidean (int n, int x) {
        q = Lists.newArrayList(0, 0);
        r = Lists.newArrayList(n, x);
        s = Lists.newArrayList(1, 0);
        t = Lists.newArrayList(0, 1);
        run();
    }

    private void run () {
        print(0);
        print(1);
        for( int i = 1; r.get(i) != 0; i++ ){
            int quotient = r.get(i - 1) / r.get(i);
            int remainder = r.get(i - 1) - r.get(i) * quotient;
            int scoeff = s.get(i - 1) - s.get(i) * quotient;
            int tcoeff = t.get(i - 1) - t.get(i) * quotient;
            q.add(quotient);
            r.add(remainder);
            s.add(scoeff);
            t.add(tcoeff);
            print(i + 1);
        }
    }

    private void print (int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("i=" + i + ", ");
        sb.append("q=" + q.get(i) + ", ");
        sb.append("r=" + r.get(i) + ", ");
        sb.append("s=" + s.get(i) + ", ");
        sb.append("t=" + t.get(i) + ", ");
        sb.append(s.get(i) + "*" + r.get(0) + "+" + t.get(i) + "*" + r.get(1) + "="
            + (s.get(i) * r.get(0) + t.get(i) * r.get(1)));
        System.out.println(sb);
    }

    private static void print () {
        System.out.println();
    }

    private static void print (String s) {
        System.out.println(s);
    }

    private static int getInverse (int n, int x) {
        for( int y = 0; y < n; y++ ){
            if( x * y % n == 1 ){
                return y;
            }
        }
        throw new NoSuchElementException();
    }

}
