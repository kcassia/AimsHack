package net.wherewhat.matereal.startup;

import android.content.Context;

/**
 * @author RyuIkHan
 */
public interface AndroidStartUpModule {

    public void init(Context context) throws StartUpException;
    public boolean isSet() throws StartUpException;
}
