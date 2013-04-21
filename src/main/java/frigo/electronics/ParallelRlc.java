
package frigo.electronics;

import static frigo.math.Complex.ONE;
import static frigo.math.Complex.complex;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import frigo.math.Complex;

public class ParallelRlc {

    private double R;
    private double L;
    private double C;

    public ParallelRlc (double R, double L, double C) {
        this.R = R;
        this.L = L;
        this.C = C;
    }

    // public double w0 () {
    // return 1.0 / sqrt(L * C);
    // }
    //
    // public double f0 () {
    // return angularToOrdinaryFrequency(w0());
    // }

    public Complex admittance (double w) {
        return complex(1 / R, w * C - 1 / (w * L));
    }

    public Complex impedance (double w) {
        return ONE.div(admittance(w));
    }

    @Override
    public String toString () {
        return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
    }

}
