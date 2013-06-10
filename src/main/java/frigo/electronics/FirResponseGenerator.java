
package frigo.electronics;

import static java.lang.Math.min;

public class FirResponseGenerator {

    public static void main (String[] args) {
        FirResponseGenerator generator = new FirResponseGenerator("0_96000_4096_kaiser7.txt", 500, 16000, 500, 134);
        generator.dumpResponses();
    }

    private double min;
    private double max;
    private double interval;
    private double baseline;

    private ResponseInterpolator response;

    public FirResponseGenerator (String fileName, double min, double max, double interval, double baseline) {
        this(new ResponseInterpolator(fileName), min, max, interval, baseline);
    }

    public FirResponseGenerator (ResponseInterpolator response, double min, double max, double interval, double baseline) {
        this.response = response;
        this.min = min;
        this.max = max;
        this.interval = interval;
        this.baseline = baseline;
    }

    public void dumpResponses () {
        for( double f = min; f <= max; f += interval ){
            System.out.println(String.format("%5.0f, % 2.2f", f, getResponse(f)));
        }
    }

    private double getResponse (double frequency) {
        double absolute = response.response(frequency);
        return min(baseline - absolute, 0.0);
    }

}
