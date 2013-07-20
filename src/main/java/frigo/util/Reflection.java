
package frigo.util;

import java.lang.reflect.Field;

public class Reflection {

    public static <T> T getField (Object object, String fieldName) throws Exception {
        return getFieldInSuperclasses(object.getClass(), object, fieldName);
    }

    public static <T> T getStaticField (String className, String fieldName) throws Exception {
        return getStaticField(Class.forName(className), fieldName);
    }

    public static <T> T getStaticField (Class<?> clazz, String fieldName) throws Exception {
        return getFieldInSuperclasses(clazz, null, fieldName);
    }

    private static <T> T getFieldInSuperclasses (Class<?> clazz, Object object, String fieldName) throws Exception {
        for( Class<?> actual = clazz; actual != null; actual = actual.getSuperclass() ){
            try{
                return getFieldInConcreteClass(actual, object, fieldName);
            }catch( NoSuchFieldException e ){
            }
        }
        throw new NoSuchFieldException();
    }

    private static <T> T getFieldInConcreteClass (Class<?> clazz, Object object, String fieldName) throws Exception {
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
        setFieldInSuperclasses(object.getClass(), object, fieldName, value);
    }

    public static <T> void setStaticField (String className, String fieldName, T value) throws Exception {
        setStaticField(Class.forName(className), fieldName, value);
    }

    public static <T> void setStaticField (Class<?> clazz, String fieldName, T value) throws Exception {
        setFieldInSuperclasses(clazz, null, fieldName, value);
    }

    private static <T> void setFieldInSuperclasses (Class<?> clazz, Object object, String fieldName, T value)
        throws Exception {
        for( Class<?> actual = clazz; actual != null; actual = actual.getSuperclass() ){
            try{
                setFieldInConcreteClass(actual, object, fieldName, value);
                return;
            }catch( NoSuchFieldException e ){
            }
        }
        throw new NoSuchFieldException();
    }

    private static <T> void setFieldInConcreteClass (Class<?> clazz, Object object, String fieldName, T value)
        throws Exception {
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
