package iterator;

import entity.Tuple;

import java.io.IOException;

//TODO - you should be able to reorder most non-Filescan nodes
public interface Iterator<T> {
//    void init();
    Tuple next() throws IOException;
    void close();
}
