package query;

import entity.Tuple;

import java.util.function.Predicate;

public class PredicateBuilder {

    public Predicate<Tuple> build(Tuple tuple, String sql) {

        //TODO - from stack/array?
        String[] parts = sql.split(" ");
        String attribute = parts[0];
        String operator = parts[1];
        String value = parts[2];

        return t -> {
            Class klass = tuple.getDb().getSchema()
                .get(tuple.getTableName()).getFields().get(attribute).getType();
            if (klass.equals(Integer.TYPE)) {
                switch (operator) {
                    case ("="):
                        return ((Integer) tuple.get(attribute, tuple.getTableName())) == Integer.parseInt(value);
                    case ("!="):
                        return !(((Integer) tuple.get(attribute, tuple.getTableName())) == Integer.parseInt(value));
                    case (">"):
                        return ((Integer) tuple.get(attribute, tuple.getTableName())) > Integer.parseInt(value);
                    case ("<"):
                        return ((Integer) tuple.get(attribute, tuple.getTableName())) < Integer.parseInt(value);
                    default:
                        throw new UnsupportedOperationException();
                }
            } else if (klass.equals(Double.TYPE)) {
                switch (operator) {
                    case ("="):
                        return ((Double) tuple.get(attribute, tuple.getTableName())) == Double.parseDouble(value);
                    case ("!="):
                        return !(((Double) tuple.get(attribute, tuple.getTableName())) == Double.parseDouble(value));
                    case (">"):
                        return ((Double) tuple.get(attribute, tuple.getTableName())) > Double.parseDouble(value);
                    case ("<"):
                        return ((Double) tuple.get(attribute, tuple.getTableName())) < Double.parseDouble(value);
                    default:
                        throw new UnsupportedOperationException();
                }
            } else if (klass.equals(Float.TYPE)) {
                switch (operator) {
                    case ("="):
                        return ((Float) tuple.get(attribute, tuple.getTableName())) == Float.parseFloat(value);
                    case ("!="):
                        return !(((Float) tuple.get(attribute, tuple.getTableName())) == Float.parseFloat(value));
                    case (">"):
                        return ((Float) tuple.get(attribute, tuple.getTableName())) > Float.parseFloat(value);
                    case ("<"):
                        return ((Float) tuple.get(attribute, tuple.getTableName())) < Float.parseFloat(value);
                    default:
                        throw new UnsupportedOperationException();
                }
            } else if (klass.equals(String.class)) {
                switch (operator) {
                    case ("="):
                        return tuple.get(attribute, tuple.getTableName()).equals(value);
                    case ("!="):
                        return !tuple.get(attribute, tuple.getTableName()).equals(value);
                    case (">"):
                        return ((String) tuple.get(attribute, tuple.getTableName())).compareTo(value) > 0;
                    case ("<"):
                        return ((String) tuple.get(attribute, tuple.getTableName())).compareTo(value) < 0;
                    default:
                        throw new UnsupportedOperationException();
                }
            } else {
                throw new UnsupportedOperationException();
            }
        };


    }

}
