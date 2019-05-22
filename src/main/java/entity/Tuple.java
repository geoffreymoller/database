package entity;

import com.google.common.collect.Lists;
import db.Schema;
import db.Field;
import db.FieldMap;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Tuple {
    private final Schema schema;
    private final String tableName;
    private final Map<String, FieldMap> attributeMap;

    public String getTableName() {
        return tableName;
    }

    public Schema getSchema() {
        return schema;
    }

    public Map<String, FieldMap> getAttributeMap() {
        return attributeMap;
    }

    //projection, join
    public Tuple(Schema schema, String tableName, Map<String, FieldMap> map) {
        this.schema = schema;
        this.attributeMap = map;
        this.tableName = tableName;
    }

    //schema row
    public Tuple(Schema schema, String tableName, String data) {
        Map<String, FieldMap> map = makeFieldMap(schema, tableName, data);
        this.schema = schema;
        this.attributeMap = map;
        this.tableName = tableName;
    }

    private Map<String, FieldMap> makeFieldMap(Schema db, String tableName, String data) {
        ArrayList<String> attributes = Lists.newArrayList(data.split(","));
        Map<String, Field> fields = db.getSchema().get(tableName).getFields();
        ArrayList<String> keys = new ArrayList<>(fields.keySet());
        Map<String, FieldMap> map = new LinkedHashMap<>();
        int i = 0;
        for (String attribute : attributes) {
            Field field = fields.get(keys.get(i));
            FieldMap fm = new FieldMap(attribute, field);
            map.put(field.getName(), fm);
            i++;
        }
        return map;
    }

    public Object get(String attribute) {
        FieldMap f = attributeMap.get(attribute);
        Class fieldType = f.getField().getType();
        String s = f.getAttribute();
        if (fieldType == Integer.TYPE) {
            return Integer.valueOf(s);
        } else if (fieldType == Double.TYPE) {
            return Double.valueOf(s);
        } else if (fieldType == Float.TYPE) {
            return Float.valueOf(s);
        } else if (fieldType == String.class) {
            return s;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String toString() {
        return "Tuple{" +
            "schema=" + schema +
            ", tableName='" + tableName + '\'' +
            ", attributeMap=" + attributeMap +
            '}';
    }
}

