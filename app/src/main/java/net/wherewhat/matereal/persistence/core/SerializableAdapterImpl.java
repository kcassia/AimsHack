package net.wherewhat.matereal.persistence.core;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 *
 * @author RyuIkHan
 */
public class SerializableAdapterImpl extends AbstractSerializableAdapter{

    public SerializableAdapterImpl(String relativePath){
        super(relativePath);
    }

    @Override
    public void storeObjectAtRelativePath(String path, Serializable obj) throws IOException {

        File file = this.getFileFromRelativePath(path);

        ObjectOutputStream oos = this.getObjectOutputStream(file);

        oos.writeObject(obj);
    }

    @Override
    public void storeObjectAtAbsolutePath(String path, Serializable obj) throws IOException {

        File file = new File(path);

        ObjectOutputStream oos = this.getObjectOutputStream(file);

        oos.writeObject(obj);
    }

    @Override
    public Serializable loadObjectFromRelativePath(String path) throws IOException, ClassNotFoundException {

        File file = this.getFileFromRelativePath(path);

        ObjectInputStream ios = this.getObjectInputStream(file);

        Serializable obj = (Serializable)ios.readObject();

        return obj;
    }

    @Override
    public Serializable loadObjectFromAbsolutePath(String path) throws IOException , ClassNotFoundException{

        File file = new File(path);

        ObjectInputStream ios = this.getObjectInputStream(file);

        Serializable obj = (Serializable)ios.readObject();

        return obj;
    }

    @Override
    public void deleteSerializableAtRelativePath(String path) throws IOException {

        File file = new File(this.getFullPath(path));

        if(file.exists())
            file.delete();
    }

    @Override
    public void deleteSerializableAtAbsolutePath(String path) throws IOException {

        File file = new File(path);

        if(file.exists())
            file.delete();
    }

    @Override
    public boolean checkExistAtRelativePath(String path) throws IOException {

        File file = new File(this.getFullPath(path));

        return file.exists();
    }

    @Override
    public boolean checkExistAtAbsolutePath(String path) throws IOException {

        File file = new File(path);

        return file.exists();
    }
}
