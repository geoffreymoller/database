package iterator;

import db.Database;

import java.lang.reflect.Method;
import java.util.function.Predicate;

public class Average<T> implements Iterator {

    private Predicate<T> predicate;
    private Selection<T> selection;
    private int total;
    private int count;

    public Average(Class<T> klass, Predicate<T> f, String field, Database db) {
        Predicate<T> p = t -> true;
//        Method method = klass.getClass().getMethod(methodName);
        this.selection = new Selection<T>(klass, p, db);
        this.predicate = f;
    }

    public T next() {
        T next = selection.next();
        //TODO - null or throw?
        while(next != null && !predicate.test(next)){
            next = selection.next();
        }
        return next;
    }

    public void close() {

    }

}
