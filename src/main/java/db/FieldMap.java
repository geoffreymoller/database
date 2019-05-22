package db;

public final class FieldMap {
    private final String attribute;
    private final Field field;

    public String getAttribute() {
        return attribute;
    }

    public Field getField() {
        return field;
    }

    public FieldMap(String attribute, Field field) {
        this.attribute = attribute;
        this.field = field;
    }

    @Override
    public String toString() {
        return "FieldMap{" +
            "attribute='" + attribute + '\'' +
            ", field=" + field +
            '}';
    }
}