
package frigo.electronics;

import static java.lang.Math.floor;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

import java.util.NoSuchElementException;

public class IEC60063 {

    /**
     * 20% tolerance.
     */
    public static final double[] E6 = {10, 15, 22, 33, 47, 68};

    /**
     * 10% tolerance.
     */
    public static final double[] E12 = {10, 12, 15, 18, 22, 27, 33, 39, 47, 56, 68, 82};

    /**
     * 5% tolerance.
     */
    public static final double[] E24 = {10, 11, 12, 13, 15, 16, 18, 20, 22, 24, 27, 30, 33, 36, 39, 43, 47, 51, 56, 62,
        68, 75, 82, 91};

    /**
     * 2% tolerance.
     */
    public static final double[] E48 = {100, 105, 110, 115, 121, 127, 133, 140, 147, 154, 162, 169, 178, 187, 196, 205,
        215, 226, 237, 249, 261, 274, 287, 301, 316, 332, 348, 365, 383, 402, 422, 442, 464, 487, 511, 536, 562, 590,
        619, 649, 681, 715, 750, 787, 825, 866, 909, 953};

    /**
     * 1% tolerance.
     */
    public static final double[] E96 = {100, 102, 105, 107, 110, 113, 115, 118, 121, 124, 127, 130, 133, 137, 140, 143,
        147, 150, 154, 158, 162, 165, 169, 174, 178, 182, 187, 191, 196, 200, 205, 210, 215, 221, 226, 232, 237, 243,
        249, 255, 261, 267, 274, 280, 287, 294, 301, 309, 316, 324, 332, 340, 348, 357, 365, 374, 383, 392, 402, 412,
        422, 432, 442, 453, 464, 475, 487, 499, 511, 523, 536, 549, 562, 576, 590, 604, 619, 634, 649, 665, 681, 698,
        715, 732, 750, 768, 787, 806, 825, 845, 866, 887, 909, 931, 953, 976};

    /**
     * 0.5% tolerance.
     */
    public static final double[] E192 = {100, 101, 102, 104, 105, 106, 107, 109, 110, 111, 113, 114, 115, 117, 118,
        120, 121, 123, 124, 126, 127, 129, 130, 132, 133, 135, 137, 138, 140, 142, 143, 145, 147, 149, 150, 152, 154,
        156, 158, 160, 162, 164, 165, 167, 169, 172, 174, 176, 178, 180, 182, 184, 187, 189, 191, 193, 196, 198, 200,
        203, 205, 208, 210, 213, 215, 218, 221, 223, 226, 229, 232, 234, 237, 240, 243, 246, 249, 252, 255, 258, 261,
        264, 267, 271, 274, 277, 280, 284, 287, 291, 294, 298, 301, 305, 309, 312, 316, 320, 324, 328, 332, 336, 340,
        344, 348, 352, 357, 361, 365, 370, 374, 379, 383, 388, 392, 397, 402, 407, 412, 417, 422, 427, 432, 437, 442,
        448, 453, 459, 464, 470, 475, 481, 487, 493, 499, 505, 511, 517, 523, 530, 536, 542, 549, 556, 562, 569, 576,
        583, 590, 597, 604, 612, 619, 626, 634, 642, 649, 657, 665, 673, 681, 690, 698, 706, 715, 723, 732, 741, 750,
        759, 768, 777, 787, 796, 806, 816, 825, 835, 845, 856, 866, 876, 887, 898, 909, 920, 931, 942, 953, 965, 976,
        988};

    public static double[] applyDecades (double[] series, double[] decades) {
        double[] result = new double[series.length * decades.length];
        for( int i = 0; i < series.length; i++ ){
            for( int j = 0; j < decades.length; j++ ){
                result[i + j * series.length] = series[i] * decades[j] / series[0];
            }
        }
        return result;
    }

    public static double lower (double value, double[] series) {
        double tenPower = pow(10, floor(log10(value)));
        for( int i = series.length - 1; i >= 0; i-- ){
            double quantized = series[i] / series[0] * tenPower;
            if( quantized <= value ){
                return quantized;
            }
        }
        throw new NoSuchElementException();
    }

    public static double higher (double value, double[] series) {
        double tenPower = pow(10, floor(log10(value)));
        for( int i = 0; i < series.length; i++ ){
            double quantized = series[i] / series[0] * tenPower;
            if( quantized >= value ){
                return quantized;
            }
        }
        return series[0] / series[0] * tenPower * 10;
    }

}
