
package frigo.electronics;

import static java.lang.Math.PI;
import static java.lang.Math.log;
import static java.lang.Math.log10;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Util {

    public static double angularToOrdinaryFrequency (double w) {
        return w / (2 * PI);
    }

    public static double ordinaryToAngularFrequency (double f) {
        return 2 * PI * f;
    }

    public static double powerRatioToDecibel (double ratio) {
        return 10 * log10(ratio);
    }

    public static double amplitudeRatioToDecibel (double ratio) {
        return 20 * log10(ratio);
    }

    public static double decibelToPowerRatio (double decibel) {
        return pow(10, decibel / 10);
    }

    public static double decibelToAmplitudeRatio (double decibel) {
        return pow(10, decibel / 20);
    }

    public static double octaveBandwidthToQFactor (double N) {
        double _2_N = pow(2, N);
        return sqrt(_2_N) / (_2_N - 1);
    }

    public static double qFactorToOctaveBandwidth (double Q) {
        return 2.0 / log(2) * asinh(1 / (2 * Q));
    }

    private static double asinh (double x) {
        return log(x + sqrt(x * x + 1.0));
    }

}
