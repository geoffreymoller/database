package entity;

import com.google.common.collect.Lists;
import db.Database;
import db.Field;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Tuple {
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

    public Tuple(Database db, String tableName, Map<String, FieldMap> map) {
        this.db = db;
        this.tableName = tableName;
        this.attributeMap = map;
    }

    public Tuple(Database db, String tableName, String data) {
        Map<String, FieldMap> map = getFieldMap(db, tableName, data);
        this.db = db;
        this.tableName = tableName;
        this.attributeMap = map;
    }

    private Map<String, FieldMap> getFieldMap(Database db, String tableName, String data) {
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

    public Object get(String fieldName) {
        Field field = attributeMap.get(fieldName).getField();
        String s = attributeMap.get(fieldName).getAttribute();
        if (field.getType() == Integer.TYPE) {
            return Integer.valueOf(s);
        } else if (field.getType() == Double.TYPE) {
            return Double.valueOf(s);
        } else if (field.getType() == Float.TYPE) {
            return Float.valueOf(s);
        } else if (field.getType() == String.class) {
            return s;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public class FieldMap{
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

