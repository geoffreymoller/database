package iterator;

import com.google.common.collect.Lists;
import db.FieldMap;
import entity.Tuple;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Projection implements Iterator {

    private ArrayList<String> columns;
    private final Iterator t;
    private final String columnString;

    Projection(Iterator t, String columns) {
        this.columnString = columns;
        this.t = t;
        this.init();
    }

    @Override
    public void init() {
        this.columns = Lists.newArrayList(columnString.split(","));
    }

    public Tuple next() {
        Tuple next = t.next();
        Map<String, FieldMap> map = next.getAttributeMap();
        Map<String, FieldMap> dest = new LinkedHashMap<>();
        map.keySet().forEach(fieldName -> {
            FieldMap field = map.get(fieldName);
            if (columns.indexOf(fieldName) > -1) {
                dest.put(fieldName, field);
            }
        });
        return new Tuple(next.getSchema(), next.getTableName(), dest);
    }

    public void close() {

    }

}
