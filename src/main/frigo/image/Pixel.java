
package frigo.image;

public interface Pixel {

    Number getBlue ();

    Number getGreen ();

    Number getRed ();

    void setRGB (byte r, byte g, byte b);

    void setRGB (double r, double g, double b);

    void setRGB (float r, float g, float b);

    void setRGB (int r, int g, int b);

    void setRGB (long r, long g, long b);

    void setRGB (Number r, Number g, Number b);

    void setRGB (short r, short g, short b);
}
