package iterator;

import com.google.common.collect.Lists;
import db.Schema;
import entity.Tuple;

import java.util.ArrayList;
import java.util.Objects;

public class InMemorySort implements Iterator {

    private final String attribute;
    private Schema schema;
    private Iterator child;
    private ArrayList<Tuple> all;

    InMemorySort(String attribute, Iterator child, Schema schema) {
        this.child = child;
        this.attribute = attribute;
        this.schema = schema;
        this.init();
    }

    @Override
    public void init() {
        this.child.init();

        Tuple next = child.next();
        this.all = Lists.newArrayList();
        while (next != null) {
            all.add(next);
            next = child.next();
        }
        all.sort((t1, t2) -> {
            Integer v1 = (Integer) t1.get(attribute);
            Integer v2 = (Integer) t2.get(attribute);
            if(Objects.equals(v1, v2)) {
                return 0;
            } else if(v1 < v2){
                return -1;
            } else {
                return 1;
            }
        });
    }

    public Tuple next() {
        if (all.size() > 0) {
            return all.remove(0);
        } else {
            return null;
        }
    }

    @Override
    public void close() {

    }

}
