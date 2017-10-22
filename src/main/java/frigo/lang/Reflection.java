
package frigo.lang;

import java.lang.reflect.Field;

import lombok.SneakyThrows;

public class Reflection {

    public static <T> T getField (Object object, String fieldName) {
        return getFieldInSuperclasses(object.getClass(), object, fieldName);
    }

    public static <T> T getStaticField (Class<?> clazz, String fieldName) {
        return getFieldInSuperclasses(clazz, null, fieldName);
    }

    @SneakyThrows
    private static <T> T getFieldInSuperclasses (Class<?> clazz, Object object, String fieldName) {
        for( Class<?> actual = clazz; actual != null; actual = actual.getSuperclass() ){
            try( Rethrow rethrow = Rethrow.canThrow(NoSuchFieldException.class) ){
                return getFieldInConcreteClass(actual, object, fieldName);
            }catch( NoSuchFieldException e ){
            }
        }
        throw new NoSuchFieldException();
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    private static <T> T getFieldInConcreteClass (Class<?> clazz, Object object, String fieldName) {
        Field field = clazz.getDeclaredField(fieldName);
        boolean accessible = field.isAccessible();
        try{
            field.setAccessible(true);
            return (T) field.get(object);
        }finally{
            field.setAccessible(accessible);
        }
    }

    public static <T> void setField (Object object, String fieldName, T value) {
        setFieldInSuperclasses(object.getClass(), object, fieldName, value);
    }

    public static <T> void setStaticField (Class<?> clazz, String fieldName, T value) {
        setFieldInSuperclasses(clazz, null, fieldName, value);
    }

    @SneakyThrows
    private static <T> void setFieldInSuperclasses (Class<?> clazz, Object object, String fieldName, T value) {
        for( Class<?> actual = clazz; actual != null; actual = actual.getSuperclass() ){
            try( Rethrow rethrow = Rethrow.canThrow(NoSuchFieldException.class) ){
                setFieldInConcreteClass(actual, object, fieldName, value);
                return;
            }catch( NoSuchFieldException e ){
            }
        }
        throw new NoSuchFieldException();
    }

    @SneakyThrows
    private static <T> void setFieldInConcreteClass (Class<?> clazz, Object object, String fieldName, T value) {
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
