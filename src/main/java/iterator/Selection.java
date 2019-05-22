package iterator;

import db.Schema;
import entity.Tuple;

import java.util.function.Predicate;

public class Selection implements Iterator {

    private Predicate<Tuple> predicate;
    private Schema schema;
    private Iterator child;

    public Predicate<Tuple> getPredicate() {
        return predicate;
    }

    public Schema getSchema() {
        return schema;
    }

    public Selection(Predicate<Tuple> p, Iterator child, Schema schema) {
        this.child = child;
        this.schema = schema;
        this.predicate = p;
        this.init();
    }

    public void init(){
        this.child.init();
    }

    public Tuple next() {
        Tuple next = this.child.next();
        //TODO - null or throw?
        while(next != null && !predicate.test(next)){
            next = this.child.next();
        }
        return next;
    }

    public void close() {

    }

}
