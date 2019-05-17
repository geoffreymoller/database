package iterator;

import entity.Tuple;

import java.util.Arrays;

public class Projection implements Iterator {

    private final String[] columns;
    private final Selection s;
    private FileScan fileScan;

    public Projection(Selection s, String columns) {
        this.columns = columns.split(",");
        this.s = s;
    }

    public Tuple next() {
        Tuple next = s.next();
//        Arrays.stream(columns).map(c -> {

//        })
////        Tuple projected = new Tuple(s.getDb(), s.getTableName(), columns);
        return next;
    }

    public void close() {

    }

}
