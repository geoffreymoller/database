package entity;

import com.google.common.collect.Lists;
import db.Database;
import db.Field;
import db.Table;

import java.util.ArrayList;

public class Tuple {
    private final String tableName;
    private final ArrayList<String> parts;
    private final Database db;

    public String getTableName() {
        return tableName;
    }

    public Tuple(Database db, String tableName, String data) {
        this.db = db;
        this.tableName = tableName;
        parts = Lists.newArrayList(data.split(","));
    }

    public Object get(String fieldName) {
        Table table = db.getSchema().get(tableName);
        Field field = table.getFields().get(fieldName);
        String s = parts.get(field.getIndex()-1);
        if(field.getType() == Integer.TYPE){
            return Integer.valueOf(s);
        } else if (field.getType() == Double.TYPE){
            return Double.valueOf(s);
        } else if (field.getType() == Float.TYPE){
            return Float.valueOf(s);
        } else if(field.getType() == String.class){
            return s;
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
