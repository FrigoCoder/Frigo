
package frigo.electronics;

import java.io.FileOutputStream;
import java.io.IOException;

import frigo.math.Statistics;
import frigo.util.ArraysAux;

public class DeconvolutedImpulseResponseGenerator {

    public static void main (String[] args) throws IOException {
        // DeconvolutedImpulseResponseGenerator generator =
        // new DeconvolutedImpulseResponseGenerator("0_96000_4096_kaiser7.txt");
        DeconvolutedImpulseResponseGenerator generator = new DeconvolutedImpulseResponseGenerator("response.txt");
        generator.write("output.snd");
    }

    private int fftSize;
    private ResponseInterpolator interpolator;

    private DeconvolutedImpulseResponseGenerator (String fileName) {
        fftSize = 1024;
        interpolator = new ResponseInterpolator(fileName);
    }

    private void write (String fileName) throws IOException {
        double[] impulseResponse = getImpulseResponse();
        byte[] byteArray = ArraysAux.encodeInByteArray(impulseResponse);
        try( FileOutputStream out = new FileOutputStream(fileName) ){
            out.write(byteArray);
        }
    }

    private double[] getImpulseResponse () {
        DigitalFrequencyResponse headphoneResponse = new DigitalFrequencyResponse(interpolator, fftSize);
        DigitalFrequencyResponse correctionResponse = headphoneResponse.invert();
        DigitalFrequencyResponse identity = headphoneResponse.convolute(correctionResponse);
        // return normalize(correctionResponse.toShiftedKernel());
        return normalize(identity.toShiftedKernel());
    }

    private double[] normalize (double[] v) {
        double max = Statistics.maxAbs(v);
        double[] result = new double[v.length];
        for( int i = 0; i < result.length; i++ ){
            result[i] = v[i] / max;
        }
        return result;
    }
}
