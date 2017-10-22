
package frigo.lang;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

import lombok.SneakyThrows;

public class Environment {

    public static boolean has (String key) {
        return System.getenv().containsKey(key);
    }

    public static String get (String key) {
        checkArgument(has(key), "Environment variable not found: %s", key);
        return System.getenv().get(key);
    }

    public static void set (String key, String value) {
        try( Rethrow rethrow = Rethrow.canThrow(NoSuchFieldException.class) ){
            getTheEnvironment().put(key, value);
            getTheCaseInsensitiveEnvironment().put(key, value);
        }catch( NoSuchFieldException e ){
            getEnvM().put(key, value);
        }
    }

    public static void remove (String key) {
        try( Rethrow rethrow = Rethrow.canThrow(NoSuchFieldException.class) ){
            getTheEnvironment().remove(key);
            getTheCaseInsensitiveEnvironment().remove(key);
        }catch( NoSuchFieldException e ){
            getEnvM().remove(key);
        }
    }

    private static Map<String, String> getEnvM () {
        return Reflection.getField(System.getenv(), "m");
    }

    @SneakyThrows
    private static Map<String, String> getTheCaseInsensitiveEnvironment () {
        return Reflection.getStaticField(Class.forName("java.lang.ProcessEnvironment"),
            "theCaseInsensitiveEnvironment");
    }

    @SneakyThrows
    private static Map<String, String> getTheEnvironment () {
        return Reflection.getStaticField(Class.forName("java.lang.ProcessEnvironment"), "theEnvironment");
    }

}
