package net.wherewhat.matereal.persistence.dao;

import android.database.sqlite.SQLiteDatabase;

import net.wherewhat.matereal.setting.SystemSetting;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author RyuIkHan
 */
public interface SystemSettingDao {

    public List<SystemSetting> getAllSystemSetting(SQLiteDatabase sqLiteDatabase) throws SQLException;
    public SystemSetting getSystemSetting(SQLiteDatabase sqLiteDatabase , String key) throws SQLException;
    public void insertSystemSetting(SQLiteDatabase sqLiteDatabase , SystemSetting systemSetting) throws SQLException;
    public void updateSystemSetting(SQLiteDatabase sqLiteDatabase , SystemSetting systemSetting) throws SQLException;
}
