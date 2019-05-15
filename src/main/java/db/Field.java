package db;

public class Field {
    private int tid;
    private String name;
    private Class type;
    private Boolean nullable;

    public int getTid() {
        return tid;
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

    public Field(int tid, String name, Class type, Boolean nullable) {
        this.tid = tid;
        this.name = name;
        this.type = type;
        this.nullable = nullable;
    }
}
