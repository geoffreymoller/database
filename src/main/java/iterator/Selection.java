package iterator;

import db.Database;
import entity.Tuple;

import java.util.function.Predicate;

public class Selection implements Iterator {

    private Predicate<Tuple> predicate;
    private FileScan fileScan;
    private String tableName;
    private Database db;

    public String getTableName() {
        return tableName;
    }

    public Database getDb() {

        return db;
    }

    public Selection(String tableName, Predicate<Tuple> p, Database db) {
        this.tableName = tableName;
        this.db = db;
        this.fileScan = new FileScan(tableName, db);
        this.predicate = p;
    }

    public Tuple next() {
        Tuple next = fileScan.next();
        //TODO - null or throw?
        while(next != null && !predicate.test(next)){
            next = fileScan.next();
        }
        return next;
    }

    public void close() {

    }

}
