package net.wherewhat.matereal.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.wherewhat.matereal.R;
import net.wherewhat.matereal.startup.AndroidStartUpModule;
import net.wherewhat.matereal.startup.StartUpException;

/**
 * DatabaseFactory holds the SQLiteDatabase. At start-up time, It sets the
 * SQLiteDatabase.
 *
 * @author RyuIkHan
 */
public class DatabaseFactory implements AndroidStartUpModule {

    private SQLiteDatabase sqLiteDatabase = null;
    private boolean startUp = false;

    public static final String DATABASE_NAME = "db_name.db";
    static final int DATABASE_VERSION = 1;

    @Override
    public void init(Context context) throws StartUpException {

        this.sqLiteDatabase = new SQLiteDBHelper(context,DATABASE_NAME,null,DATABASE_VERSION).getWritableDatabase();

        startUp = true;
    }

    public SQLiteDatabase getDatabase(){

        return this.sqLiteDatabase;
    }

    @Override
    public boolean isSet() throws StartUpException {

        if(!startUp)
            return false;

        return sqLiteDatabase != null;
    }

    class SQLiteDBHelper extends SQLiteOpenHelper {

        private String sampleTable = null;

        SQLiteDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, null, version);

            sampleTable = context.getString(R.string.table_system_setting);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(sampleTable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
