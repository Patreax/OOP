package Models;

import java.io.*;
import java.util.ArrayList;

public class Serializator<O> {
    public void serialize(O object, ArrayList<O> arrayList, File file) throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        arrayList.add(object);
        out.writeObject(arrayList);
        out.close();
    }

    public void saveData(ArrayList<O> arrayList, File file) throws IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(arrayList);
        out.close();
    }
}
