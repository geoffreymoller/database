package iterator;

import db.Schema;
import entity.Tuple;

import java.util.function.Predicate;

public class Selection implements Iterator {

    private Predicate<Tuple> predicate;
    private FileScan fileScan;
    private String tableName;
    private Schema schema;

    public Predicate<Tuple> getPredicate() {
        return predicate;
    }

    public FileScan getFileScan() {
        return fileScan;
    }

    public String getTableName() {
        return tableName;
    }

    public Schema getSchema() {
        return schema;
    }

    public Selection(String tableName, Predicate<Tuple> p, Schema schema) {
        this.tableName = tableName;
        this.schema = schema;
        this.predicate = p;
        this.init();
    }

    public void init(){
        this.fileScan = new FileScan(this);
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
