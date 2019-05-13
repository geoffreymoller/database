package db;

public class Database {

    private final String path;

    public Database(String path) {
           this.path = path;
    }

    public String getPath() {
        return path;
    }
}
