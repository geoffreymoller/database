package entity;

import com.google.common.collect.Lists;
import db.Database;
import db.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Tuple {
    private final Database db;
    private final String tableName;
    private final Map<String, Map<String, FieldMap>> attributeMap;

    public String getTableName() {
        return tableName;
    }

    public Database getDb() {
        return db;
    }

    public Map<String, Map<String, FieldMap>> getAttributeMap() {
        return attributeMap;
    }

    public Tuple(Database db, String tableName, Map<String, Map<String, FieldMap>> map) {
        this.db = db;
        this.tableName = tableName;
        this.attributeMap = map;
    }

    public Tuple(Database db, String tableName, String data) {
        Map<String, Map<String, FieldMap>> map = getFieldMap(db, tableName, data);
        this.db = db;
        this.tableName = tableName;
        this.attributeMap = map;
    }

    private Map<String, Map<String, FieldMap>> getFieldMap(Database db, String tableName, String data) {
        ArrayList<String> attributes = Lists.newArrayList(data.split(","));
        Map<String, Field> fields = db.getSchema().get(tableName).getFields();
        ArrayList<String> keys = new ArrayList<>(fields.keySet());
        Map<String, Map<String, FieldMap>> map = new LinkedHashMap<>();
        Map<String, FieldMap> fieldMap = new HashMap<String, FieldMap>();
        int i = 0;
        for (String attribute : attributes) {
            Field field = fields.get(keys.get(i));
            FieldMap fm = new FieldMap(attribute, field);
            fieldMap.put(field.getName(), fm);
            i++;
        }
        map.put(tableName, fieldMap);
        return map;
    }

    public Object get(String fieldName) {
        FieldMap fm = attributeMap.get(tableName).get(fieldName);
        Class fieldType = fm.getField().getType();
        String s = fm.getAttribute();
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

    public final class FieldMap{
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

