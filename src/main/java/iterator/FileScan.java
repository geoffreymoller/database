package iterator;

import db.Database;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

public class FileScan<T> implements Iterator {

    private Class<T> clazz;
    private BufferedReader bufferedReader;
    private String line;
    private int i = 0;

    public FileScan(Class<T> klass, Database db) {
        this.clazz = klass;
        InputStream inputstream;
        try {
            inputstream = new FileInputStream(db.getPath() +
                clazz.getSimpleName().toLowerCase() +
                "s.csv");
            bufferedReader = new BufferedReader(new InputStreamReader(inputstream));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public T next() {
        T entity = null;
        try {
            if (i == 0) {
                line = bufferedReader.readLine();
            }
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO - null or throw?
        if(line.equals("")) {
            return null;
        }

        try {
            Class[] cArg = new Class[1];
            cArg[0] = String.class;
            entity = (T) clazz.getDeclaredConstructor(cArg).newInstance(line);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
        i += 1;
        return entity;
    }

    public void close() {
        //noop?
    }
}
