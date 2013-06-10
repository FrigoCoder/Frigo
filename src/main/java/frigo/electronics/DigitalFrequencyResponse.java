
package frigo.electronics;

import static com.google.common.base.Preconditions.checkArgument;
import frigo.math.Complex;

public class DigitalFrequencyResponse {

    private Complex[] response;

    public DigitalFrequencyResponse (ResponseInterpolator interpolator, int fftSize) {
        double[] decibels = interpolator.sample(44100, fftSize / 2 + 1);
        double[] amplitudes = Util.decibelsToAmplitudeRatios(decibels);
        response = toComplexArray(amplitudes, amplitudes.length - 2);
    }

    private DigitalFrequencyResponse (int fftSize) {
        response = new Complex[fftSize];
    }

    private Complex[] toComplexArray (double[] values, int padding) {
        Complex[] result = new Complex[values.length + padding];
        for( int i = values.length; i < result.length; i++ ){
            result[i] = Complex.ZERO;
        }
        for( int i = 0; i < values.length; i++ ){
            result[i] = new Complex(values[i]);
        }
        return result;
    }

    public DigitalFrequencyResponse convolute (DigitalFrequencyResponse other) {
        checkArgument(response.length == other.response.length);
        DigitalFrequencyResponse result = new DigitalFrequencyResponse(response.length);
        for( int i = 0; i < result.response.length; i++ ){
            result.response[i] = response[i].mul(other.response[i]);
        }
        return result;
    }

    public DigitalFrequencyResponse invert () {
        DigitalFrequencyResponse result = new DigitalFrequencyResponse(response.length);
        for( int i = 0; i < response.length; i++ ){
            result.response[i] = response[i].equals(Complex.ZERO) ? Complex.ZERO : Complex.ONE.div(response[i]);
        }
        return result;
    }

    public double[] toShiftedKernel () {
        double[] result = new double[response.length];
        for( int i = 0; i < result.length; i++ ){
            result[i] = response[(i + response.length / 2) % response.length].re;
        }
        return result;
    }

}
