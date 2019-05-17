package entity;

import com.google.common.collect.Lists;
import db.Database;
import db.Field;
import db.Table;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Tuple {
    private final String tableName;
    private final Database db;
    private final LinkedHashMap<String, Field> attributeFields;
    private ArrayList<String> attributes;

    public String getTableName() {
        return tableName;
    }

    public Database getDb() {
        return db;
    }

    public LinkedHashMap<String, Field> getAttributeFields() {
        return attributeFields;
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public Tuple(Database db, String tableName, String data) {
        this.db = db;
        this.tableName = tableName;
        attributes = Lists.newArrayList(data.split(","));
        attributeFields = new LinkedHashMap<>();
        Map<String, Field> fields = db.getSchema().get(tableName).getFields();
        ArrayList<String> keys = new ArrayList<>(fields.keySet());
        int i = 0;
        for (String attribute : attributes) {
            attributeFields.put(attribute, fields.get(keys.get(i)));
            i++;
        }
    }

    public Object get(String fieldName) {
        Table table = db.getSchema().get(tableName);
        Field field = table.getFields().get(fieldName);
        String s = attributes.get(field.getIndex()-1);
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

}
