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
        Map<String, Map<String, Tuple.FieldMap>> map = next.getAttributeMap();
        Map<String, Map<String, Tuple.FieldMap>> dest = new LinkedHashMap<>();
        map.keySet().forEach(tableName -> {
            Map<String, Tuple.FieldMap> attributes = map.get(tableName);
            Map<String, Tuple.FieldMap> destMap = new LinkedHashMap<>();
            attributes.keySet().forEach(attributeName -> {
                if(columns.indexOf(attributeName) > -1){
                    destMap.put(attributeName, map.get(tableName).get(attributeName));
                }
            });
            dest.put(tableName, destMap);
        });
        return new Tuple(next.getDb(), next.getTableName(), dest);
    }

    public void close() {

    }

}
