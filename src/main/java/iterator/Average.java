package iterator;

import db.Database;
import entity.Tuple;

import java.util.function.Predicate;

public class Average implements Iterator {

    private Predicate<Tuple> predicate;
    private Selection selection;
    private Double total = 0d;
    private Double count = 0d;
    private String attribute;
    private String tableName;

    public Average(String tableName, Predicate<Tuple> f, String attribute, Database db) {
        this.tableName = tableName;
        Predicate<Tuple> p = t -> true;
        this.attribute = attribute;
        this.selection = new Selection(tableName, p, db);
        this.predicate = f;
    }

    public Object next() {
        Tuple next = selection.next();
        while(next != null && predicate.test(next)){
            count++;
            total += (Double) next.get(attribute, tableName);
            next = selection.next();
        }
        return total/count;
    }

}
