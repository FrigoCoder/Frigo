
package frigo.electronics;

import static frigo.electronics.Util.amplitudeRatioToDecibel;
import static frigo.electronics.Util.angularToOrdinaryFrequency;
import static frigo.electronics.Util.ordinaryToAngularFrequency;
import static frigo.electronics.Util.powerRatioToDecibel;
import static frigo.math.Complex.div;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

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
        double w0 = 1.0 / sqrt(L * C);
        return angularToOrdinaryFrequency(w0);
    }

    public double f1 () {
        double low = 0;
        double high = f0();
        double target = gain() + 3;
        double tolerance = 1E-15;
        while( true ){
            double mid = (low + high) / 2;
            double actual = response(mid);
            if( abs(target - actual) < tolerance || high - low < tolerance ){
                return mid;
            }
            if( target < actual ){
                low = mid;
            }else{
                high = mid;
            }
        }
    }

    public double f2 () {
        return f0() * f0() / f1();
    }

    public double gain () {
        double ratio = load / (R + load);
        return amplitudeRatioToDecibel(ratio);
    }

    public double q () {
        return f0() / (f2() - f1());
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
