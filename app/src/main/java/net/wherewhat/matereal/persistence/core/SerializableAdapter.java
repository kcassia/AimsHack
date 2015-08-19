package net.wherewhat.matereal.persistence.core;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by RyuIkHan on 15. 6. 11..
 */
public interface SerializableAdapter {

    public void storeObjectAtRelativePath(String path , Serializable obj) throws IOException;
    public void storeObjectAtAbsolutePath(String path , Serializable obj) throws IOException;

    public Serializable loadObjectFromRelativePath(String path) throws IOException , ClassNotFoundException;
    public Serializable loadObjectFromAbsolutePath(String path) throws IOException , ClassNotFoundException;

    public void deleteSerializableAtRelativePath(String path) throws IOException;
    public void deleteSerializableAtAbsolutePath(String path) throws IOException;

    public boolean checkExistAtRelativePath(String path) throws IOException;
    public boolean checkExistAtAbsolutePath(String path) throws IOException;

}
