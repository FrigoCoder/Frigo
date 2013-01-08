
package frigo.util;

import java.lang.reflect.Field;
import java.util.Map;

public class Reflection {

    public static boolean isEnv (String key) {
        return System.getenv().containsKey(key);
    }

    public static String getEnv (String key) {
        if( !isEnv(key) ){
            throw new IllegalArgumentException();
        }
        return System.getenv().get(key);
    }

    public static void setEnv (String key, String value) throws Exception {
        try{
            Map<String, String> env = getStaticField("java.lang.ProcessEnvironment", "theEnvironment");
            env.put(key, value);
            Map<String, String> cienv = getStaticField("java.lang.ProcessEnvironment", "theCaseInsensitiveEnvironment");
            cienv.put(key, value);
        }catch( NoSuchFieldException e ){
            Map<String, String> map = getField(System.getenv(), "m");
            map.put(key, value);
        }
    }

    public static void removeEnv (String key) throws Exception {
        try{
            Map<String, String> env = getStaticField("java.lang.ProcessEnvironment", "theEnvironment");
            env.remove(key);
            Map<String, String> cienv = getStaticField("java.lang.ProcessEnvironment", "theCaseInsensitiveEnvironment");
            cienv.remove(key);
        }catch( NoSuchFieldException e ){
            Map<String, String> map = getField(System.getenv(), "m");
            map.remove(key);
        }
    }

    public static <T> T getField (Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(object);
    }

    public static <T> void setField (Object object, String fieldName, T value) throws Exception {
        Class<?> clazz = object.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }

    public static <T> T getStaticField (String className, String fieldName) throws Exception {
        return getStaticField(Class.forName(className), fieldName);
    }

    public static <T> T getStaticField (Class<?> clazz, String fieldName) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(null);
    }

    public static <T> void setStaticField (String className, String fieldName, T value) throws Exception {
        setStaticField(Class.forName(className), fieldName, value);
    }

    public static <T> void setStaticField (Class<?> clazz, String fieldName, T value) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(null, value);
    }

}
