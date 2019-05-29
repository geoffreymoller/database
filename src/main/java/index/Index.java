package index;

public class Index {
    private final int offset;

    public int getOffset() {
        return offset;
    }

    public Index(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "Index{" +
            "offset=" + offset +
            '}';
    }
}
