package db;

public class Field {
    private int index;
    private String name;
    private Class type;
    private Boolean nullable;

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public Class getType() {
        return type;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public Field(int index, String name, Class type, Boolean nullable) {
        this.index = index;
        this.name = name;
        this.type = type;
        this.nullable = nullable;
    }
}
