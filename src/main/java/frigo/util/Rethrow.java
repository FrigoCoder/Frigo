
package frigo.util;

public class Rethrow {

    public static void rethrow (Throwable e) {
        Rethrow.<RuntimeException> throwUnchecked(e);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> void throwUnchecked (Throwable e) throws T {
        throw (T) e;
    }

}
