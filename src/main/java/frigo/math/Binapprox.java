
package frigo.math;

import static com.google.common.base.Preconditions.checkArgument;

public class Binapprox {

    public static double binapprox (double[] v) {
        return new Binapprox(v, 1000).run();
    }

    private double[] v;
    private int bins;

    private double mu;
    private double sigma;

    private Binapprox (double[] v, int bins) {
        checkArgument(v.length != 0);
        this.v = v;
        this.bins = bins;
    }

    public double run () {
        mu = Statistics.average(v);
        sigma = Statistics.standardDeviation(v);
        if( sigma == 0 ){
            return v[0];
        }

        int bottomcount = 0;
        int[] bincounts = new int[bins + 1];

        double scalefactor = bins / (2 * sigma);
        double leftend = mu - sigma;
        double rightend = mu + sigma;

        for( double value : v ){
            if( value < leftend ){
                bottomcount++;
                continue;
            }
            if( value < rightend ){
                int bin = (int) ((value - leftend) * scalefactor);
                bincounts[bin]++;
                continue;
            }
        }

        int k = (v.length + 1) / 2;
        int count = bottomcount;

        if( v.length % 2 == 1 ){
            for( int i = 0; i < bins + 1; i++ ){
                count += bincounts[i];
                if( count >= k ){
                    return leftend + (i + 0.5) / scalefactor;
                }
            }
        }else{
            for( int i = 0; i < bins + 1; i++ ){
                count += bincounts[i];
                if( count >= k ){
                    int j = i;
                    while( count == k ){
                        j++;
                        count += bincounts[j];
                    }
                    return leftend + (i + j + 1) / (2 * scalefactor);
                }
            }
        }

        throw new IllegalArgumentException();
    }

}
