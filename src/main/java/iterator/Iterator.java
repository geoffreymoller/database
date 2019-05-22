package iterator;

import entity.Tuple;

//TODO - you should be able to reorder most non-Filescan nodes
public interface Iterator<T> {
    void init();
    Tuple next();
    void close();
}
