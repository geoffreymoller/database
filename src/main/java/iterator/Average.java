//TODO - treat aggregation iterators as a tuple with length 1
package iterator;

import db.Field;
import db.FieldMap;
import db.Schema;
import entity.Tuple;

import java.util.HashMap;

public class Average implements Iterator {

    private Iterator child;
    private Double total = 0d;
    private Double count = 0d;
    private String attribute;
    private Schema schema;

    public Average(String attribute, Iterator child, Schema schema) {
        this.child = child;
        this.schema = schema;
        this.attribute = attribute;
        this.init();
    }

    @Override
    public void init() {
        this.child.init();
    }

    public Tuple next() {
        Tuple next = child.next();
        while(next != null){
            count++;
            //TODO - clean up Tuple.get
            total += (Double) next.get(attribute);
            next = child.next();
        }
        double avg = total / count;
        HashMap<String, FieldMap> m = new HashMap<>();
        FieldMap fm = new FieldMap(Double.toString(avg), new Field("aggregate", "average", Double.TYPE, false));
        m.put("average", fm);
        return new Tuple(schema, "average", m);
    }

    @Override
    public void close() {

    }

}
