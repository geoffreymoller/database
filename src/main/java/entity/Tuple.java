package entity;

import com.google.common.collect.Lists;
import db.Database;
import db.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class Tuple {
    private final Database db;
    private final String tableName;
    private final Map<String, FieldMap> attributeMap;

    public String getTableName() {
        return tableName;
    }

    public Database getDb() {
        return db;
    }

    public Map<String, FieldMap> getAttributeMap() {
        return attributeMap;
    }

    //projection
    public Tuple(Database db, String tableName, Map<String, FieldMap> map) {
        this.db = db;
        this.attributeMap = map;
        this.tableName = tableName;
    }

    //db row
    public Tuple(Database db, String tableName, String data) {
        Map<String, FieldMap> map = makeFieldMap(db, tableName, data);
        this.db = db;
        this.attributeMap = map;
        this.tableName = tableName;
    }

    private Map<String, FieldMap> makeFieldMap(Database db, String tableName, String data) {
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

    public Object get(String fieldName, String tableName) {
        Map<String, FieldMap> map = attributeMap.entrySet().stream()
            .filter(entry -> {
                return entry.getValue().getField().getTableName().equals(tableName)
                    && entry.getValue().getField().getName().equals(fieldName);
            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        FieldMap f = map.get(fieldName);
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

    public final class FieldMap {
        private final String attribute;
        private final Field field;

        public String getAttribute() {
            return attribute;
        }

        public Field getField() {
            return field;
        }

        FieldMap(String attribute, Field field) {
            this.attribute = attribute;
            this.field = field;
        }
    }

}

