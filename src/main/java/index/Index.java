package index;

public class Index {
    private final int offset;
    private final int length;

    public int getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }

    public Index(int offset, int length) {
        this.offset = offset;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Index{" +
            "offset=" + offset +
            ", length=" + length +
            '}';
    }
}
