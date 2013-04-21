
package frigo.electronics;

import static com.google.common.base.Preconditions.checkArgument;
import static frigo.electronics.Util.decibelToAmplitudeRatio;
import static frigo.electronics.Util.ordinaryToAngularFrequency;
import static frigo.math.MathAux.sqr;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

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
        double low = q * q / (R * R) / 2;
        double high = q * q / (R * R) * 2;
        double tolerance = 1E-20;
        while( true ){
            double mid = (low + high) / 2;
            ParallelRlcWithLoad rlc = new ParallelRlcWithLoad(R, sqrt(LC / mid), sqrt(LC * mid), load);
            double difference = q - rlc.q();
            if( abs(difference) < tolerance || high - low < tolerance ){
                return rlc;
            }
            if( difference < 0 ){
                high = mid;
            }else{
                low = mid;
            }
        }
    }

}
