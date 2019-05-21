package iterator;

import db.Database;
import entity.Tuple;

import java.util.Map;
import java.util.function.BiFunction;

public class Join implements Iterator {

    private final Selection s1;
    private final Selection s2;
    private final BiFunction<Tuple, Tuple, Boolean> f;
    private final Database db;

    public Database getDb() {
        return db;
    }

    Join(Selection s1, Selection s2, BiFunction<Tuple, Tuple, Boolean> f, Database db) {
        this.s1 = s1;
        this.s2 = s2;
        this.f = f;
        this.db = db;
    }

    public Tuple next() {
//        //at least nested loops
//        //stretch hash join
//        //stretch sort-merge
//        for(Tuple t1 = s1.next(); t1 != null;){
//            t1 = s1.next();
//            for(Tuple t2 = s2.next(); t2 != null;){
//                t2 = s2.next();
//                if (f.apply(t1, t2)){
//                    Map<String, Tuple.FieldMap> map1 = t1.getAttributeMap();
//                    Map<String, Tuple.FieldMap> map2 = t2.getAttributeMap();
//                    map2.putAll(map1);
//                    return new Tuple(db, "ass", map2);
//                }
//            }
//            t1 = s1.next();
//        }
        return new Tuple(db, "links", "string");
    }

    public void close() {

    }

}
