package net.wherewhat.matereal.persistence;

import android.content.Context;

import net.wherewhat.matereal.persistence.core.SerializableAdapter;
import net.wherewhat.matereal.persistence.core.SerializableAdapterImpl;
import net.wherewhat.matereal.startup.AndroidStartUpModule;
import net.wherewhat.matereal.startup.StartUpException;

/**
 * @author RyuIkHan
 */
public class SerializableAdapterFactory implements AndroidStartUpModule {

    private SerializableAdapter adapter = null;

    public static SerializableAdapter getSerializableAdapter(Context context){

        SerializableAdapterImpl adapter = new SerializableAdapterImpl(context.getFilesDir().getAbsolutePath());

        return adapter;
    }

    @Override
    public void init(Context context) throws StartUpException {

        this.adapter = new SerializableAdapterImpl(context.getFilesDir().getAbsolutePath());
    }

    @Override
    public boolean isSet() throws StartUpException {
        return false;
    }

    public SerializableAdapter getSerializableAdapter(){

        return this.adapter;
    }
}
