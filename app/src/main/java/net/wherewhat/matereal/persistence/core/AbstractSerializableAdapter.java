package net.wherewhat.matereal.persistence.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.StringTokenizer;

/**
 *
 * @RyuIkHan
 */
public abstract class AbstractSerializableAdapter implements SerializableAdapter {

    private String relativePath = null;

    public AbstractSerializableAdapter(String relativePath){
        this.setRelativePath(relativePath);
    }

    public void setRelativePath(String relativePath){

        relativePath = relativePath.trim();

        if(!relativePath.endsWith("/"))
            relativePath += "/";

        this.relativePath = relativePath;
    }

    protected String getRelativePath(){

        return this.relativePath;
    }

    protected String getFullPath(String path){

        path = path.trim();

        if(path.startsWith("/"))
            path = path.substring(1);

        return getRelativePath() + path;
    }

    protected File getFileFromRelativePath(String path) throws IOException{

        String fullPath = this.getFullPath(path);

        return this.getFileFromAbsolutePath(fullPath);
    }

    protected File getFileFromAbsolutePath(String path) throws IOException{

        StringTokenizer tokenizer = new StringTokenizer(path , "/");

        String folderPath = "/";
        String fileName = "";

        while(tokenizer.hasMoreElements()){

            String token = tokenizer.nextToken();

            //current token is last
            if(!tokenizer.hasMoreElements()){
                fileName = token;
            }else{
                folderPath += token +"/";
            }
        }

        File tempFile = new File(folderPath);

        if(!tempFile.exists())
            tempFile.mkdirs();

        tempFile = new File(tempFile , fileName);

        if(!tempFile.exists())
            tempFile.createNewFile();

        return new File(folderPath , fileName);
    }



    protected ObjectOutputStream getObjectOutputStream(File file) throws IOException{

        if(!file.exists()) {
            file.mkdirs();
            file.createNewFile();
        }

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

        return oos;
    }

    protected ObjectInputStream getObjectInputStream(File file) throws IOException{

        if(!file.exists()) {
            file.mkdirs();
            file.createNewFile();
        }

        ObjectInputStream ios = new ObjectInputStream(new FileInputStream(file));

        return ios;
    }
}
