
package frigo.lang;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

public class Environment {

    public static boolean has (String key) {
        return System.getenv().containsKey(key);
    }

    public static String get (String key) {
        checkArgument(has(key), "Environment variable not found: %s", key);
        return System.getenv().get(key);
    }

    public static void set (String key, String value) throws Exception {
        try{
            getTheEnvironment().put(key, value);
            getTheCaseInsensitiveEnvironment().put(key, value);
        }catch( NoSuchFieldException e ){
            getEnvM().put(key, value);
        }
    }

    public static void remove (String key) throws Exception {
        try{
            getTheEnvironment().remove(key);
            getTheCaseInsensitiveEnvironment().remove(key);
        }catch( NoSuchFieldException e ){
            getEnvM().remove(key);
        }
    }

    private static Map<String, String> getEnvM () throws Exception {
        return Reflection.getField(System.getenv(), "m");
    }

    private static Map<String, String> getTheCaseInsensitiveEnvironment () throws Exception {
        return Reflection
            .getStaticField(Class.forName("java.lang.ProcessEnvironment"), "theCaseInsensitiveEnvironment");
    }

    private static Map<String, String> getTheEnvironment () throws Exception {
        return Reflection.getStaticField(Class.forName("java.lang.ProcessEnvironment"), "theEnvironment");
    }

}
