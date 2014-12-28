
package frigo.math.integer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FindFewestQuadraticResidues {

    public static void main (String[] args) throws IOException {
        try( FileWriter writer = new FileWriter(new File("c:/temp/whatever.txt")) ){
            long limit = 100_000;
            double smallest = Double.MAX_VALUE;
            FactorizerSieve sieve = new FactorizerSieve((int) limit);
            for( int n = 2; n <= limit; n++ ){
                Set<Long> residues = quadraticResidues(n);
                double ratio = (double) residues.size() / n;
                writer.write("n=" + n + ", |r|=" + residues.size() + ", |r|/n=" + ratio + "\n");

                if( smallest > ratio ){
                    smallest = Math.min(smallest, ratio);
                    writer.write("Found new smallest! n=" + n + ", |r|=" + residues.size() + ", |r|/n=" + ratio
                        + ", factors=" + sieve.factor(n) + "\n");
                }
            }
        }
    }

    private static Set<Long> quadraticResidues (long n) {
        Set<Long> residues = new HashSet<>();
        for( long x = 0; x < n; x++ ){
            residues.add(x * x % n);
        }
        return residues;
    }

}
