package iterator;

import db.Schema;
import db.FieldMap;
import entity.Tuple;

import java.util.LinkedHashMap;
import java.util.function.BiFunction;

public class Join implements Iterator {

    private final Iterator s1;
    private Iterator s2;
    private final BiFunction<Tuple, Tuple, Boolean> f;
    private final Schema schema;
    private final java.util.Iterator<Tuple> iterator;

    public Schema getSchema() {
        return schema;
    }

    Join(Iterator s1, Iterator s2, BiFunction<Tuple, Tuple, Boolean> f, Schema schema) {
        this.s1 = s1;
        this.s2 = s2;
        this.f = f;
        this.schema = schema;
        JoinGenerator generator = new JoinGenerator();
        this.iterator = generator.iterator();
    }

    @Override
    public void init() {

    }

    public Tuple next() {
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            return null;
        }
    }

    private class JoinGenerator extends Generator<Tuple> {
        @Override
        protected void run() throws InterruptedException {
            Tuple t1 = s1.next();
            while (t1 != null) {
                Tuple t2 = s2.next();
                if(t2 == null){
                   s2.init();
                   continue;
                }
                while (t2 != null) {
                    if (f.apply(t1, t2)) {
                        LinkedHashMap<String, FieldMap> sum = new LinkedHashMap<>();
                        sum.putAll(t1.getAttributeMap());
                        sum.putAll(t2.getAttributeMap());
                        yield(new Tuple(schema, t1.getTableName()+"-"+t2.getTableName(), sum));
                    }
                    t2 = s2.next();
                }
                t1 = s1.next();
            }
        }
    }

    public void close() {

    }

}
