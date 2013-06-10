
package frigo.electronics;

import static frigo.util.Rethrow.unchecked;
import static java.lang.Double.parseDouble;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.google.common.io.Files;

public class ResponseInterpolator {

    private TreeMap<Double, Double> response = new TreeMap<>();

    public ResponseInterpolator (String fileName) {
        this(new File(ClassLoader.getSystemResource(fileName).getFile()));
    }

    public ResponseInterpolator (File file) {
        this(readLines(file, 4));
    }

    private static List<String> readLines (File file, int fromIndex) {
        try{
            List<String> lines = Files.readLines(file, Charset.forName("UTF-8"));
            return lines.subList(fromIndex, lines.size());
        }catch( IOException e ){
            throw unchecked(e);
        }
    }

    public ResponseInterpolator (List<String> lines) {
        for( String line : lines ){
            double[] values = parseLine(line);
            response.put(values[0], values[1]);
        }
    }

    private double[] parseLine (String line) {
        String[] split = line.split(",");
        double[] result = new double[split.length];
        for( int i = 0; i < result.length; i++ ){
            result[i] = parseDouble(split[i]);
        }
        return result;
    }

    public double response (double frequency) {
        Entry<Double, Double> floor = response.floorEntry(frequency);
        if( floor == null ){
            return response.firstEntry().getValue();
        }
        Entry<Double, Double> ceil = response.ceilingEntry(frequency);
        if( ceil == null ){
            return response.lastEntry().getValue();
        }
        if( floor.getKey().equals(ceil.getKey()) ){
            return floor.getValue();
        }
        double ratio = (frequency - floor.getKey()) / (ceil.getKey() - floor.getKey());
        return (1.0 - ratio) * floor.getValue() + ratio * ceil.getValue();
    }

    public double[] sample (double sampleRate, int samples) {
        double[] result = new double[samples];
        for( int i = 0; i < samples; i++ ){
            double f = sampleRate / 2 * i / (samples - 1);
            result[i] = response(f);
        }
        return result;
    }
}
