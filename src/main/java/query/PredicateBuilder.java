package query;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Predicate;

public class PredicateBuilder<T> {

    public Predicate<T> build(T thing, String sql) {

        //TODO - from stack/array?
        String[] parts = sql.split(" ");
        String attribute = parts[0];
        String operator = parts[1];
        String value = parts[2];
        String methodName = "get" + StringUtils.capitalize(attribute);

        return t -> {
            try {
                Method method = thing.getClass().getMethod(methodName);
                if (method.getReturnType().equals(Integer.TYPE)) {
                    switch (operator) {
                        case ("="):
                            return ((Integer) method.invoke(thing)) == Integer.parseInt(value);
                        case ("!="):
                            return !(((Integer) method.invoke(thing)) == Integer.parseInt(value));
                        case (">"):
                            return ((Integer) method.invoke(thing)) > Integer.parseInt(value);
                        case ("<"):
                            return ((Integer) method.invoke(thing)) < Integer.parseInt(value);
                        default:
                            throw new UnsupportedOperationException();
                    }
                } else if (method.getReturnType().equals(Float.TYPE)) {
                    switch (operator) {
                        case ("="):
                            return ((Float) method.invoke(thing)) == Float.parseFloat(value);
                        case ("!="):
                            return !(((Float) method.invoke(thing)) == Float.parseFloat(value));
                        case (">"):
                            return ((Float) method.invoke(thing)) > Float.parseFloat(value);
                        case ("<"):
                            return ((Float) method.invoke(thing)) < Float.parseFloat(value);
                        default:
                            throw new UnsupportedOperationException();
                    }
                } else if (method.getReturnType().toString().contains("String")) {
                    switch (operator) {
                        case ("="):
                            return method.invoke(thing).equals(value);
                        case ("!="):
                            return !method.invoke(thing).equals(value);
                        case (">"):
                            return ((String) method.invoke(thing)).compareTo(value) > 0;
                        case ("<"):
                            return ((String) method.invoke(thing)).compareTo(value) < 0;
                        default:
                            throw new UnsupportedOperationException();
                    }
                } else {
                    throw new UnsupportedOperationException();
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException();
            }
        };


    }

}
