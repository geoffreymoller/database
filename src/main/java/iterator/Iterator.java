package iterator;

import entity.Tuple;

public interface Iterator<T> {
    void init();
    Tuple next();
    void close();
}
