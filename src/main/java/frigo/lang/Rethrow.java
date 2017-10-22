
package frigo.lang;

public class Rethrow {

    @SuppressWarnings("unused")
    public static <T extends Throwable> void canThrow (Class<T> clazz) throws T {}

}
