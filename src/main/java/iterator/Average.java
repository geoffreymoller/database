package iterator;

import db.Database;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Predicate;

public class Average<T> implements Iterator {

    private Predicate<T> predicate;
    private Selection<T> selection;
    private int total;
    private int count;
    private String attribute;

    public Average(Class<T> klass, Predicate<T> f, String attribute, Database db) {
        Predicate<T> p = t -> true;
        this.attribute = attribute;
        this.selection = new Selection<T>(klass, p, db);
        this.predicate = f;
    }

    public void next() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "get" + StringUtils.capitalize(attribute);
        T next = selection.next();
        while(next != null && !predicate.test(next)){
            count++;
            total += (int) next.getClass().getMethod(methodName).invoke(next);
            next = selection.next();
        }
    }

    public int close() {
        return count/total;
    }

}
