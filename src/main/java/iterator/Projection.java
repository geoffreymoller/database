package iterator;

import com.google.common.collect.Lists;
import db.FieldMap;
import entity.Tuple;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Projection implements Iterator {

    private final ArrayList<String> columns;
    private final Selection s;

    Projection(Selection s, String columns) {
        this.columns = Lists.newArrayList(columns.split(","));
        this.s = s;
    }

    public Tuple next() {
        Tuple next = s.next();
        Map<String, FieldMap> map = next.getAttributeMap();
        Map<String, FieldMap> dest = new LinkedHashMap<>();
        map.keySet().forEach(fieldName -> {
            FieldMap field = map.get(fieldName);
            if (columns.indexOf(fieldName) > -1) {
                dest.put(fieldName, field);
            }
        });
        return new Tuple(next.getDb(), next.getTableName(), dest);
    }

    public void close() {

    }

}
