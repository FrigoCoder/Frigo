
package frigo.electronics;

import static frigo.electronics.Util.amplitudeRatioToDecibel;
import static frigo.electronics.Util.angularToOrdinaryFrequency;
import static frigo.electronics.Util.ordinaryToAngularFrequency;
import static frigo.electronics.Util.powerRatioToDecibel;
import static frigo.math.Complex.div;
import static frigo.math.MathAux.sqr;
import static frigo.util.Bisection.bisect;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;

import frigo.math.Complex;

public class ParallelRlcWithLoad {

    private ParallelRlc rlc;
    private double R;
    private double L;
    private double C;
    private double load;

    public ParallelRlcWithLoad (double R, double L, double C, double load) {
        rlc = new ParallelRlc(R, L, C);
        this.R = R;
        this.L = L;
        this.C = C;
        this.load = load;
    }

    public double f0 () {
        return angularToOrdinaryFrequency(w0());
    }

    private double w0 () {
        return 1.0 / sqrt(L * C);
    }

    public double gain () {
        double ratio = load / (R + load);
        return amplitudeRatioToDecibel(ratio);
    }

    public double q () {
        return f0() / (f2() - f1());
    }

    @VisibleForTesting
    double f1 () {
        return sqr(f0()) / f2();
    }

    @VisibleForTesting
    double f2 () {
        Function<Double, Double> function = new Function<Double, Double>() {

            @Override
            public Double apply (Double frequency) {
                double target = gain() + 3;
                return target - response(frequency);
            }
        };
        return bisect(function, f0(), pow(f0(), 2));
    }

    public double response (double f) {
        return powerRatioToDecibel(transfer(f).sqrAbs());
    }

    private Complex transfer (double f) {
        double w = ordinaryToAngularFrequency(f);
        return div(load, rlc.impedance(w).add(load));
    }

    @Override
    public String toString () {
        return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
    }

}
