package net.wherewhat.matereal.persistence;

import android.content.Context;

import net.wherewhat.matereal.R;
import net.wherewhat.matereal.startup.AndroidStartUpModule;
import net.wherewhat.matereal.startup.StartUpException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author RyuIkHan
 */
public class DaoFactory implements AndroidStartUpModule{

    private Map<String , Object> daoMap = null;
    private List<Class<? extends Object>> classList = null;

    private boolean startUp = false;

    @Override
    public void init(Context context) throws StartUpException {

        daoMap = new HashMap<>();
        classList = new ArrayList<>();

        String[] daoClassArr = context.getResources().getStringArray(R.array.dao_class_name_list);

        for(String className : daoClassArr){

            Class<? extends AndroidDao> clazz;

            try {
                clazz = (Class<? extends AndroidDao>)Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new StartUpException(e);
            }

            classList.add(clazz);

            AndroidDao tempDao;

            try {
                tempDao = clazz.getDeclaredConstructor(Context.class).newInstance(context);
            }catch(Exception e){

                throw new StartUpException(e);
            }

            daoMap.put(className , tempDao);
        }

        startUp = true;
    }

    @Override
    public boolean isSet() throws StartUpException {

        if(!startUp)
            return false;

        return daoMap != null;
    }

    public <T> T getDao(Class<? extends T> clazz){

        String className = null;

        for(Class<? extends Object> tempClazz : classList){

            if(clazz.isAssignableFrom(tempClazz)){

                className = tempClazz.getName();

                break;
            }
        }


        if(className == null)
            return null;

        return clazz.cast(daoMap.get(className));
    }
}
