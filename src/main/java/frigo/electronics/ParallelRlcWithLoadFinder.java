
package frigo.electronics;

import static com.google.common.base.Preconditions.checkArgument;
import static frigo.electronics.Util.decibelToAmplitudeRatio;
import static frigo.electronics.Util.ordinaryToAngularFrequency;
import static frigo.math.MathAux.sqr;
import static frigo.util.Bisection.bisect;
import static java.lang.Math.sqrt;

import com.google.common.base.Function;

public class ParallelRlcWithLoadFinder {

    private double q;
    private double load;

    private double R;
    private double LC;

    public ParallelRlcWithLoadFinder (double f0, double gain, double q, double load) {
        checkArgument(gain < -3.0, "Sorry gain has to be at most -3dB");
        this.q = q;
        this.load = load;
        R = (1.0 / decibelToAmplitudeRatio(gain) - 1.0) * load;
        LC = sqr(1.0 / ordinaryToAngularFrequency(f0));
    }

    public ParallelRlcWithLoad getFilter () {
        Function<Double, Double> function = new Function<Double, Double>() {

            @Override
            public Double apply (Double CperL) {
                ParallelRlcWithLoad rlc = new ParallelRlcWithLoad(R, sqrt(LC / CperL), sqrt(LC * CperL), load);
                return q - rlc.q();
            }

        };
        double estimate = q * q / (R * R);
        double CperL = bisect(function, estimate / 2, estimate * 2);
        return new ParallelRlcWithLoad(R, sqrt(LC / CperL), sqrt(LC * CperL), load);
    }

}
