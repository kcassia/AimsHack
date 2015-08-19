package net.wherewhat.matereal.setting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import net.wherewhat.matereal.R;
import net.wherewhat.matereal.persistence.DaoFactory;
import net.wherewhat.matereal.persistence.DatabaseFactory;
import net.wherewhat.matereal.persistence.dao.SystemSettingDao;
import net.wherewhat.matereal.startup.AndroidModuleRepository;
import net.wherewhat.matereal.startup.AndroidStartUpModule;
import net.wherewhat.matereal.startup.StartUpException;
import net.wherewhat.matereal.startup.VariableStore;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by RyuIkHan on 15. 7. 10..
 */
public class SystemSettingRepository implements AndroidStartUpModule, VariableStore {

    private Map<String, String> settingMap = null;

    private static final String VERSION_COLUMN_NAME = "version";

    private boolean startUp = false;

    private static final String VALUE = "value";
    private static final String TYPE = "type";
    private static final String CATEGORY = "category";
    private static final String DESCRIPTION = "description";

    @Override
    public void init(Context context) throws StartUpException {

        //get database
        DatabaseFactory dbFactory = AndroidModuleRepository.getModule(DatabaseFactory.class);
        SQLiteDatabase db = dbFactory.getDatabase();

        //get SysetmSettingDao
        DaoFactory daoFactory = AndroidModuleRepository.getModule(DaoFactory.class);
        SystemSettingDao systemSettingDao = daoFactory.getDao(SystemSettingDao.class);

        if (!checkSystemIsSet(systemSettingDao, db)) {
            initDatabase(context, systemSettingDao, db);
        }

        settingMap = new HashMap<>();

        String jsonStr = context.getString(R.string.default_system_setting);

        JSONObject jsonObj;

        try {
            jsonObj = new JSONObject(jsonStr);
        } catch (JSONException e) {
            throw new StartUpException(e);
        }

        for (Iterator<String> it = jsonObj.keys(); it.hasNext(); ) {

            String key = it.next();

            SystemSetting systemSetting;

            try {
                systemSetting = systemSettingDao.getSystemSetting(db, key);
            } catch (SQLException e) {
                throw new StartUpException(e);
            }

            settingMap.put(systemSetting.getKey(), systemSetting.getValue());
        }

        startUp = true;
    }

    @Override
    public boolean isSet() throws StartUpException {

        if (!startUp)
            return false;

        return settingMap != null;
    }

    private boolean checkSystemIsSet(SystemSettingDao settingDao, SQLiteDatabase db) throws StartUpException {

        SystemSetting systemSetting;

        try {
            systemSetting = settingDao.getSystemSetting(db, VERSION_COLUMN_NAME);
        } catch (SQLException e) {
            throw new StartUpException(e);
        }

        return systemSetting != null;
    }

    private void initDatabase(Context context, SystemSettingDao systemSettingDao, SQLiteDatabase db) throws StartUpException {

        String jsonStr = context.getString(R.string.default_system_setting);

        JSONObject jsonObj;

        try {
            jsonObj = new JSONObject(jsonStr);
        } catch (JSONException e) {
            throw new StartUpException(e);
        }

        Iterator<String> it = jsonObj.keys();

        while (it.hasNext()) {

            String key = it.next();

            JSONObject value;
            SystemSetting tempSetting;

            try {

                value = jsonObj.getJSONObject(key);
                tempSetting = makeSystemSettingFromJsonObject(key, value);

            } catch (JSONException e) {
                throw new StartUpException(e);
            }

            try {
                systemSettingDao.insertSystemSetting(db, tempSetting);
            } catch (SQLException e) {
                throw new StartUpException(e);
            }
        }
    }

    private SystemSetting makeSystemSettingFromJsonObject(String key, JSONObject jsonObj) throws JSONException {

        SystemSetting systemSetting = new SystemSetting();

        systemSetting.setKey(key);
        systemSetting.setValue(jsonObj.getString(VALUE));
        systemSetting.setType(jsonObj.getString(TYPE));
        systemSetting.setCategory(jsonObj.getString(CATEGORY));
        systemSetting.setDescription(jsonObj.getString(DESCRIPTION));

        return systemSetting;
    }

    @Override
    public int getIntegerValue(String key) {

        String value = this.settingMap.get(key);

        int returnValue = Integer.parseInt(value);

        return returnValue;
    }

    @Override
    public long getLongValue(String key) {

        String value = this.settingMap.get(key);

        long returnValue = Long.parseLong(value);

        return returnValue;
    }

    @Override
    public double getDoubleValue(String key) {

        String value = this.settingMap.get(key);

        double returnValue = Double.parseDouble(value);

        return returnValue;
    }

    @Override
    public String getStringValue(String key) {

        String value = this.settingMap.get(key);

        return value;
    }

    @Override
    public boolean getBooleanValue(String key) {

        String value = this.settingMap.get(key);

        boolean flag = "true".equalsIgnoreCase(value.trim()) ||
                "t".equalsIgnoreCase(value.trim());

        return flag;
    }
}
