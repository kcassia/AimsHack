package net.wherewhat.matereal;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import net.wherewhat.matereal.persistence.DatabaseFactory;
import net.wherewhat.matereal.startup.AndroidModuleRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RyuIkHan
 */
public class IntroActivity extends android.app.Activity{

    private static long START_UP_TIME = 1500l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        deleteDatabase(DatabaseFactory.DATABASE_NAME);

        new StartUpAsyncTask().execute();
    }

    @Override
    public void onBackPressed() {

    }

    class StartUpAsyncTask extends AsyncTask implements AndroidModuleRepository.StartUpModuleClassNameListGetter{

        @Override
        protected Object doInBackground(Object[] params) {

            AndroidModuleRepository.startUp(IntroActivity.this , this);

            try {
                Thread.sleep(START_UP_TIME);
            } catch (InterruptedException e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {

            MainActivity.startActivity(IntroActivity.this);

            IntroActivity.this.finish();
        }

        @Override
        public List<String> getClassNameList(Context context)  {

            List<String> set = new ArrayList<>();

            String jsonStr = context.getString(R.string.start_up_module_class_list);

            JSONObject jsonObj;
            try {
                jsonObj = new JSONObject(jsonStr);
            } catch (JSONException e) {

                return null;
            }

            int size = 0;

            try {
                size = jsonObj.getInt("size");
            } catch (JSONException e) {

                return null;
            }

            for(int i = 0; i < size; i++){

                try {
                    String className = jsonObj.getString("s"+String.valueOf(i));

                    set.add(className);
                } catch (JSONException e) {

                    return null;
                }
            }

            return set;
        }
    }


}
