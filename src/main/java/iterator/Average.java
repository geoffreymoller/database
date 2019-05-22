//TODO - treat aggregation iterators as a tuple with length 1
package iterator;

import db.Database;
import db.Field;
import db.FieldMap;
import entity.Tuple;

import java.util.HashMap;
import java.util.function.Predicate;

public class Average implements Iterator {

    private Predicate<Tuple> predicate;
    private Selection selection;
    private Double total = 0d;
    private Double count = 0d;
    private String attribute;
    private String tableName;
    private Database db;

    public Average(String tableName, Predicate<Tuple> f, String attribute, Database db) {
        this.tableName = tableName;
        this.db = db;
        Predicate<Tuple> p = t -> true;
        this.attribute = attribute;
        this.selection = new Selection(tableName, p, db);
        this.predicate = f;
    }

    public Tuple next() {
        Tuple next = selection.next();
        while(next != null && predicate.test(next)){
            count++;
            total += (Double) next.get(attribute, tableName);
            next = selection.next();
        }
        double avg = total / count;
        HashMap<String, FieldMap> m = new HashMap<>();
        FieldMap fm = new FieldMap(Double.toString(avg), new Field("aggregate", "average", Double.TYPE, false));
        m.put("average-aggregate", fm);
        return new Tuple(db, "average", m);
    }

    @Override
    public void close() {

    }

}
