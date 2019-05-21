//package iterator;
//
//import db.Database;
//import entity.Tuple;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import query.PredicateBuilder;
//
//import java.util.function.BiFunction;
//import java.util.function.Function;
//import java.util.function.Predicate;
//
//import static db.Database.LINKS;
//import static db.Database.MOVIES;
//import static db.Database.MOVIE_ID;
//import static org.junit.jupiter.api.Assertions.*;
//
//class JoinTest {
//
//    Database db;
//
//    @BeforeEach
//    void setUp() {
//        db = new Database("/Users/geoffreymoller/Code/database/src/test/resources/next/");
//    }
//
//    @Test
//    void next() {
//        Predicate<Tuple> p = t -> true;
//        Selection selectionLinks = new Selection(LINKS, p, db);
//        Selection selectionMovies = new Selection(MOVIES, p, db);
//
//        BiFunction<Tuple, Tuple, Boolean> joinPredicate = (tuple, tuple2) -> {
////        BiFunction<Tuple, Integer, Boolean> bf2 = (tuple, movieId) -> tuple.get(MOVIE_ID) == movieId;
//
////        Join j = new Join(selectionLinks, selectionMovies, joinPredicate, db);
////        Tuple t = j.next();
////        System.out.println(t);
////        t = j.next();
////        System.out.println(t);
////        t = j.next();
////        System.out.println(t);
////        t = j.next();
////        System.out.println(t);
////        t = j.next();
////        System.out.println(t);
//    }
//
//}