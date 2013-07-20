
package frigo.util;

import java.lang.reflect.Field;

public class Reflection {

    public static <T> T getField (Object object, String fieldName) throws Exception {
        return getField(object.getClass(), object, fieldName);
    }

    public static <T> T getStaticField (Class<?> clazz, String fieldName) throws Exception {
        return getField(clazz, null, fieldName);
    }

    public static <T> T getStaticField (String className, String fieldName) throws Exception {
        return getStaticField(Class.forName(className), fieldName);
    }

    private static <T> T getField (Class<?> clazz, Object object, String fieldName) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        boolean accessible = field.isAccessible();
        try{
            field.setAccessible(true);
            return (T) field.get(object);
        }finally{
            field.setAccessible(accessible);
        }
    }

    public static <T> void setField (Object object, String fieldName, T value) throws Exception {
        setField(object.getClass(), object, fieldName, value);
    }

    public static <T> void setStaticField (Class<?> clazz, String fieldName, T value) throws Exception {
        setField(clazz, null, fieldName, value);
    }

    public static <T> void setStaticField (String className, String fieldName, T value) throws Exception {
        setStaticField(Class.forName(className), fieldName, value);
    }

    private static <T> void setField (Class<?> clazz, Object object, String fieldName, T value) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        boolean accessible = field.isAccessible();
        try{
            field.setAccessible(true);
            field.set(object, value);
        }finally{
            field.setAccessible(accessible);
        }
    }

}
