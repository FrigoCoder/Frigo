
package frigo.lang;

public class Rethrow {

    public static RuntimeException unchecked (Throwable e) {
        Rethrow.<RuntimeException>throwAny(e);
        return null;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Throwable> T throwAny (Throwable e) throws T {
        throw (T) e;
    }

    @SuppressWarnings("unused")
    public static <T extends Throwable> void canThrow (Class<T> clazz) throws T {}

}
