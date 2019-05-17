package iterator;

import db.Database;
import entity.Tuple;

public class Projection implements Iterator {

    private final String columns;
    private final Selection s;
    private final Database db;
    private FileScan fileScan;

    public Projection(String columns, Selection s, Database db) {
        this.columns = columns;
        this.s = s;
        this.db = db;
    }

    public Tuple next() {
        Tuple next = s.next();
        while(next != null){
            next = s.next();
        }
        return next;
    }

    public void close() {

    }

}
