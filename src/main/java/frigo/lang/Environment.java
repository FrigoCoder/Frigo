
package frigo.lang;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Map;

public class Environment {

    private static final String JAVA_LANG_PROCESS_ENVIRONMENT = "java.lang.ProcessEnvironment";

    public static boolean has (String key) {
        return System.getenv().containsKey(key);
    }

    public static String get (String key) {
        checkArgument(has(key), "Is not an environment variable");
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
            Map<String, String> map = getEnvM();
            map.remove(key);
        }
    }

    private static Map<String, String> getEnvM () throws Exception {
        return Reflection.getField(System.getenv(), "m");
    }

    private static Map<String, String> getTheCaseInsensitiveEnvironment () throws Exception {
        return Reflection.getStaticField(JAVA_LANG_PROCESS_ENVIRONMENT, "theCaseInsensitiveEnvironment");
    }

    private static Map<String, String> getTheEnvironment () throws Exception {
        return Reflection.getStaticField(JAVA_LANG_PROCESS_ENVIRONMENT, "theEnvironment");
    }

}
