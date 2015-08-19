package net.wherewhat.matereal.persistence;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by RyuIkHan on 15. 7. 10..
 */
public abstract class AndroidDao<T> {

    public AndroidDao(Context context){
        initSqls(context);
    }

    protected abstract void initSqls(Context context);

    protected abstract T makeObject(Cursor cursor);
}
