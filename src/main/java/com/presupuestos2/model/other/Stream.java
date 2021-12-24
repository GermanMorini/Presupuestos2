package com.presupuestos2.model.other;

import java.io.*;

public class Stream {

    static public void writeObject(Object obj, String savePath) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savePath));
        oos.writeObject(obj);
        oos.close();
    }

    static public Object readObject(String savePath) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savePath));
        Object obj= ois.readObject();
        ois.close();
        return obj;
    }
}
