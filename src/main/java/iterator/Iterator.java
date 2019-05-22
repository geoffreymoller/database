package iterator;

import java.io.IOException;

//TODO - you should be able to reorder most non-Filescan nodes
public interface Iterator<T> {
//    void init();
    T next() throws IOException;
    void close();
}
