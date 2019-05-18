package iterator;

import com.google.common.collect.Lists;
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
        Map<String, Tuple.FieldMap> dest = new LinkedHashMap<>();
        Map<String, Tuple.FieldMap> map = next.getAttributeMap();
        map.keySet().forEach(e -> {
            if(columns.indexOf(e) > -1){
                dest.put(e, map.get(e));
            }
        });
        return new Tuple(next.getDb(), next.getTableName(), dest);
    }

    public void close() {

    }

}
