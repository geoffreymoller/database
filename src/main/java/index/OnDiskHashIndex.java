package index;

import com.sleepycat.je.DatabaseEntry;
import db.Schema;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

//https://blog-en.openalfa.com/how-to-use-berkeley-db-java-edition
//TODO - hashtable bucketing
  //create one file per bucket
  //https://stackoverflow.com/questions/9073903/what-does-bucket-entries-mean-in-the-context-of-a-hashtable

public class OnDiskHashIndex<K,V> {

    private String type;

    public OnDiskHashIndex(String type) {
        this.type = type;
    }

    public void put(K title, V index) throws FileNotFoundException {
        Schema schema = new Schema("/Users/geoffreymoller/Code/database/src/test/resources/next/");
        RandomAccessFile file = new RandomAccessFile(schema.getPath() + "indexes/" + type, "r");
        //TODO - scan, look for indexed value
        //if found, overwrite persisting old values
        //if not, create
        //this.map.put(title, index);
    }

    public V get(K title) {
        //TODO - scan, look for indexed value
        //if found, return offset values
        //if not return null
//        FileInputStream inputstream = new FileInputStream(filePath);
//        return map.get(title);
        return null;
    }

}
