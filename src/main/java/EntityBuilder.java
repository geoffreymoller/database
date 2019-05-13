//import entity.Link;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//public class EntityBuilder {
//
//    public EntityBuilder() {
//    }
//
//    public void build() {
//        InputStream inputstream = null;
//        try {
//            inputstream = new FileInputStream("/Users/geoffreymoller/Code/database/ml-20m/links.csv");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            String line;
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputstream));
//            int i = 0;
//            while ((line = bufferedReader.readLine()) != null) {
//                if(i != 0) {
//                    Link link = new Link(line);
//                    System.out.printf("%s\n", link.getMovieId());
//                    System.out.printf("%s\n", link.getImdbId());
//                    System.out.printf("%s\n", link.getTmdbId());
//                    System.out.printf("%s\n", "");
//                }
//                i += 1;
//            }
//        } catch (IOException e) {
//            System.err.println("Error: " + e);
//        }
//
//    }
//
//    public static void main() throws IOException {
////        List<Entity> entities = Lists.newArrayList();
////        private MappingIterator<TruckEvent> readTruckEventsFromCsv(InputStream csvStream) throws IOException {
////            CsvSchema bootstrap = CsvSchema.builder()
////// driverId,truckId,eventTime,eventType,longitude,latitude,eventKey,correlationId,driverName,routeId,routeName,eventDate
////                .addColumn("driverId", CsvSchema.ColumnType.NUMBER)
////                .addColumn("truckId", CsvSchema.ColumnType.NUMBER)
////                .addColumn("eventTime", CsvSchema.ColumnType.STRING)
////                .addColumn("eventType", CsvSchema.ColumnType.STRING)
////                .addColumn("longitude", CsvSchema.ColumnType.NUMBER)
////                .addColumn("latitude", CsvSchema.ColumnType.NUMBER)
////                .addColumn("eventKey", CsvSchema.ColumnType.STRING)
////                .addColumn("correlationId", CsvSchema.ColumnType.NUMBER)
////                .addColumn("driverName", CsvSchema.ColumnType.STRING)
////                .addColumn("routeId", CsvSchema.ColumnType.NUMBER)
////                .addColumn("routeName", CsvSchema.ColumnType.STRING)
////                .addColumn("eventDate", CsvSchema.ColumnType.STRING)
//////                .addColumn("miles", CsvSchema.ColumnType.NUMBER)
////                .build().withHeader();
////
////            CsvMapper csvMapper = new CsvMapper();
////            return csvMapper.readerFor(TruckEvent.class).with(bootstrap).readValues(csvStream);
////        }
//    }
//
//}
