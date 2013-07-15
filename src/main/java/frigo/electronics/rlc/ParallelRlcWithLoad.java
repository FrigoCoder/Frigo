
package frigo.electronics.rlc;

import static frigo.electronics.Util.angularToOrdinaryFrequency;
import static frigo.electronics.Util.ordinaryToAngularFrequency;
import static frigo.electronics.Util.powerRatioToDecibel;
import static frigo.electronics.Util.qFactorToOctaveBandwidth;
import static frigo.math.Complex.div;
import static frigo.math.MathAux.sqr;
import static frigo.util.Bisection.bisect;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.base.Function;

import frigo.math.Complex;

public class ParallelRlcWithLoad {

    public final double R;
    public final double L;
    public final double C;
    public final double load;

    public final double f0;
    public final double gain;
    public final double q;
    public final double obw;

    private ParallelRlc rlc;

    public ParallelRlcWithLoad (double R, double L, double C, double load) {
        this.R = R;
        this.L = L;
        this.C = C;
        this.load = load;
        rlc = new ParallelRlc(R, L, C);
        f0 = angularToOrdinaryFrequency(1.0 / sqrt(L * C));
        gain = response(f0);
        q = f0 / (f2() - f1());
        obw = qFactorToOctaveBandwidth(q);
    }

    private double f1 () {
        return sqr(f0) / f2();
    }

    private double f2 () {
        Function<Double, Double> function = new Function<Double, Double>() {

            @Override
            public Double apply (Double frequency) {
                double target = gain + 3;
                return target - response(frequency);
            }
        };
        return bisect(function, f0, pow(f0, 2));
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
        ToStringBuilder builder = new ToStringBuilder(this, SHORT_PREFIX_STYLE);
        builder.append("R", format(R) + "Ω");
        builder.append("L", format(L * 1_000) + "mH");
        builder.append("C", format(C * 1_000_000_000) + "nF");
        builder.append("load", format(load) + "Ω");
        builder.append("f0", format(f0) + "Hz");
        builder.append("gain", format(gain) + "dB");
        builder.append("Q", format(q));
        builder.append("BW", format(obw) + " octave");
        return builder.toString();
    }

    private String format (double value) {
        return Double.toString(value);
    }

}
