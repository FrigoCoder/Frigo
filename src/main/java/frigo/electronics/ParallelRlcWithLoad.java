
package frigo.electronics;

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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;

import frigo.math.Complex;

public class ParallelRlcWithLoad {

    public final double R;
    public final double L;
    public final double C;
    public final double load;

    public final double f0;
    public final double gain;
    private Double q;
    @VisibleForTesting
    Double obw;
    private ParallelRlc rlc;

    public ParallelRlcWithLoad (double R, double L, double C, double load) {
        this.R = R;
        this.L = L;
        this.C = C;
        this.load = load;
        rlc = new ParallelRlc(R, L, C);
        f0 = angularToOrdinaryFrequency(1.0 / sqrt(L * C));
        gain = response(f0);
    }

    public double q () {
        if( q == null ){
            q = f0 / (f2() - f1());
            obw = qFactorToOctaveBandwidth(q());
        }
        return q;
    }

    @VisibleForTesting
    double f1 () {
        return sqr(f0) / f2();
    }

    @VisibleForTesting
    double f2 () {
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
        return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
    }

}
