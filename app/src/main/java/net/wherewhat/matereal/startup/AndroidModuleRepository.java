package net.wherewhat.matereal.startup;

import android.content.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AndroidModuleRepository contains AndroidStartUpModule. It provides static
 * method 'startUp()' for start up modules.
 *
 * @author RyuIkHan
 * @see net.wherewhat.matereal.startup.AndroidStartUpModule
 */
public class AndroidModuleRepository {

    private static Map<String , Object> moduleMap = null;

    private AndroidModuleRepository(){

    }

    public static void startUp(Context context , StartUpModuleClassNameListGetter getter){

        startUp(context , getter.getClassNameList(context));
    }

    /**
     * It creates modules using class name and call init() method of created module.
     * After calling init method , it puts the created module in moduleMap.
     *
     * @param context the context of caller
     * @param classNameList the list of class name for start up module
     * */
    public static void startUp(Context context , List<String> classNameList){

        moduleMap = new HashMap<>();

        for(String className : classNameList){

            Class<? extends AndroidStartUpModule> clazz;

            try {
                clazz = (Class<? extends AndroidStartUpModule>)Class.forName(className);

                AndroidStartUpModule module = clazz.newInstance();

                module.init(context);

                moduleMap.put(className , module);

            } catch(Exception e){
                throw new StartUpException(e);
            }
        }
    }

    /**
     * It checks modules in moduleMap are all set or not.
     *
     * @return true if all module is set, false if not
     * */
    public static boolean isStartUp(){

        if(moduleMap == null)
            return false;

        for(Map.Entry<String , Object> entry : moduleMap.entrySet()){

            AndroidStartUpModule module = (AndroidStartUpModule)entry.getValue();

            if(!module.isSet())
                return false;
        }

        return true;
    }

    /**
     *
     * @param clazz the class of module to be retrieved
     *
     * @return module
     * */
    public static <T> T getModule(Class<T> clazz){

        Object tempModule = moduleMap.get(clazz.getName());

        return tempModule == null ? null : clazz.cast(tempModule);
    }

    public interface StartUpModuleClassNameListGetter {

        public List<String> getClassNameList(Context context);
    }
}
