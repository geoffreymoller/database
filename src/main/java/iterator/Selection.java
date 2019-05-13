package iterator;

import db.Database;

import java.util.function.Predicate;

public class Selection<T> implements Iterator {

    private Predicate<T> predicate;
    private FileScan<T> fileScan;

    public Selection(Class<T> klass, Predicate<T> f, Database db) {
        this.fileScan = new FileScan<T>(klass, db);
        this.predicate = f;
    }

    public T next() {
        T next = fileScan.next();
        //TODO - null or throw?
        while(next != null && !predicate.test(next)){
            next = fileScan.next();
        }
        return next;
    }

    public void close() {

    }

}
