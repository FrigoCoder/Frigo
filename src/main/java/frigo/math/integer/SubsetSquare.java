
package frigo.math.integer;


public class SubsetSquare {

    public static void main (String[] args) {
        // long[] U =
        // {124989250231L, 249978500462L, 499957000924L, 999914001848L, 1999828003696L, 3999656007392L,
        // 7999312014784L, 15998624029568L, 31997248059136L, 63994496118272L, 127988992236544L, 255977984473088L,
        // 511955968946176L, 1023911937892352L, 2047823875784704L, 4095647751569408L, 8191295503138816L,
        // 16382591006277632L, 32765182012555264L, 65530364025110528L, 131060728050221056L, 262121456100442112L,
        // 524242912200884224L, 1048485824401768448L, 2096971648803536896L};

        long start = System.currentTimeMillis();

        long n = 124989250231L;
        for( long k = 0; k <= n / 4; k++ ){
            try{
                if( isSquare(k * n + 1) ){
                    long x = MathAux.isqrt(k * n + 1);
                    System.out.println("Solution: k=" + k + ", x=" + x);
                }
            }catch( StackOverflowError e ){

                System.out.println(k * n + 1);
                return;
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Took " + (end - start) / 1000 + " seconds");
    }

    private static boolean isSquare (long x) {
        long sx = MathAux.isqrt(x);
        return sx * sx == x;
    }

}
