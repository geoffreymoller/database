package iterator;

import db.Database;
import entity.Tuple;

import java.util.LinkedHashMap;
import java.util.function.BiFunction;

public class Join implements Iterator {

    private final Selection s1;
    private Selection s2;
    private final BiFunction<Tuple, Tuple, Boolean> f;
    private final Database db;
    private final JoinGenerator generator;
    private final java.util.Iterator<Tuple> iterator;

    public Database getDb() {
        return db;
    }

    Join(Selection s1, Selection s2, BiFunction<Tuple, Tuple, Boolean> f, Database db) {
        this.s1 = s1;
        this.s2 = s2;
        this.f = f;
        this.db = db;
        this.generator = new JoinGenerator();
        this.iterator = generator.iterator();
    }

    private class JoinGenerator extends Generator<Tuple> {
        @Override
        protected void run() throws InterruptedException {
            Tuple t1 = s1.next();
            while (t1 != null) {
                Tuple t2 = s2.next();
                while (t2 != null) {
                    if (f.apply(t1, t2)) {
                        LinkedHashMap<String, Tuple.FieldMap> sum = new LinkedHashMap<>();
                        sum.putAll(t1.getAttributeMap());
                        sum.putAll(t2.getAttributeMap());
                        yield(new Tuple(db, t1.getTableName()+"-"+t2.getTableName(), sum));
                    }
                    t2 = s2.next();
                }
                t1 = s1.next();
            }
        }
    }

    public Tuple next() {
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            return null;
        }
    }

    public void close() {

    }

}
