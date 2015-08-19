package net.wherewhat.matereal.persistence.dao;

import android.content.Context;
import android.database.Cursor;

import net.wherewhat.matereal.R;
import net.wherewhat.matereal.persistence.AndroidDao;
import net.wherewhat.matereal.setting.SystemSetting;

/**
 * Created by RyuIkHan on 15. 7. 10..
 */
public abstract class AndroidSystemSettingDao extends AndroidDao<SystemSetting> implements SystemSettingDao{

    protected String selectAll;
    protected String select;
    protected String insert;

    private static final String KEY = "key";
    private static final String VALUE = "value";
    private static final String CATEGORY = "category";
    private static final String TYPE = "type";
    private static final String DESCRIPTION = "description";

    public AndroidSystemSettingDao(Context context){
        super(context);
    }

    @Override
    protected void initSqls(Context context) {

        selectAll =  context.getString(R.string.system_setting_select_all);
        select = context.getString(R.string.system_setting_select);
        insert = context.getString(R.string.system_setting_insert);
    }

    @Override
    protected SystemSetting makeObject(Cursor cursor) {

        int keyCol = cursor.getColumnIndex(KEY);
        int valueCol = cursor.getColumnIndex(VALUE);
        int categoryCol = cursor.getColumnIndex(CATEGORY);
        int typeCol = cursor.getColumnIndex(TYPE);
        int descriptionCol = cursor.getColumnIndex(DESCRIPTION);

        SystemSetting systemSetting = new SystemSetting();

        systemSetting.setKey(cursor.getString(keyCol));
        systemSetting.setValue(cursor.getString(valueCol));
        systemSetting.setCategory(cursor.getString(categoryCol));
        systemSetting.setType(cursor.getString(typeCol));
        systemSetting.setDescription(cursor.getString(descriptionCol));

        return systemSetting;
    }
}
