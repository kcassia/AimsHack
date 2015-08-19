package net.wherewhat.matereal.persistence.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import net.wherewhat.matereal.setting.SystemSetting;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RyuIkHan on 15. 7. 10..
 */
public class AndroidSystemSettingDaoImpl extends AndroidSystemSettingDao{

    public AndroidSystemSettingDaoImpl(Context context) {
        super(context);
    }

    @Override
    public List<SystemSetting> getAllSystemSetting(SQLiteDatabase sqLiteDatabase) throws SQLException {

        Cursor cursor = sqLiteDatabase.rawQuery(super.selectAll , null);

        List<SystemSetting> returnList = new ArrayList<>();

        if(!cursor.moveToFirst())
            return returnList;

        do{
            returnList.add(makeObject(cursor));

        }while(cursor.moveToNext());

        return returnList;
    }

    @Override
    public SystemSetting getSystemSetting(SQLiteDatabase sqLiteDatabase, String key) throws SQLException {

        Cursor cursor = sqLiteDatabase.rawQuery(super.select , new String[]{ key });

        if(!cursor.moveToFirst())
            return null;

        return makeObject(cursor);
    }

    @Override
    public void insertSystemSetting(SQLiteDatabase sqLiteDatabase, SystemSetting systemSetting) throws SQLException {

        SQLiteStatement stmt = sqLiteDatabase.compileStatement(super.insert);


        stmt.bindString(1 , systemSetting.getKey());
        stmt.bindString(2 , systemSetting.getValue());
        stmt.bindString(3 , systemSetting.getCategory());
        stmt.bindString(4 , systemSetting.getType());
        stmt.bindString(5 , systemSetting.getDescription());

        stmt.executeInsert();
    }

    @Override
    public void updateSystemSetting(SQLiteDatabase sqLiteDatabase, SystemSetting systemSetting) throws SQLException {

    }
}
