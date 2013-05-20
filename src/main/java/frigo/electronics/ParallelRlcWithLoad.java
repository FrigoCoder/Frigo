
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

    @VisibleForTesting
    ParallelRlc rlc;

    private double load;

    public ParallelRlcWithLoad (double R, double L, double C, double load) {
        rlc = new ParallelRlc(R, L, C);
        this.load = load;
    }

    public double f0 () {
        double w0 = 1.0 / sqrt(rlc.L * rlc.C);
        return angularToOrdinaryFrequency(w0);
    }

    public double gain () {
        double ratio = load / (rlc.R + load);
        return amplitudeRatioToDecibel(ratio);
    }

    public double q () {
        return f0() / (f2() - f1());
        // double w0 = 1.0 / sqrt(rlc.L * rlc.C);
        // double XL = w0 * rlc.L;
        // double XC = 1 / (w0 * rlc.C);
        // double Rp = load;
        // return XL / Rp;
        // return rlc.admittance(w0).re / rlc.admittance(w0).im;
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
