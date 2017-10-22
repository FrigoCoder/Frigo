
package frigo.lang;

public class Rethrow implements AutoCloseable {

    public static <E extends Exception> Rethrow canThrow (Class<E> e) throws E {
        return new Rethrow(e);
    }

    public static <R, E extends Exception> R canThrow (R result, Class<E> e) throws E {
        return result;
    }

    public static <E extends Exception> void canThrow (Runnable runnable, Class<E> e) throws E {
        runnable.run();
    }

    public <E extends Exception> Rethrow (Class<E> e) throws E {}

    @Override
    public void close () {}

}
