package net.wherewhat.matereal.activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;

import net.wherewhat.matereal.R;
import net.wherewhat.matereal.setting.SystemSettingRepository;
import net.wherewhat.matereal.startup.AndroidModuleRepository;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 *
 * @author RyuIkHan
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends android.app.Activity {

    WebViewFragment webViewFragment = null;

    SystemSettingRepository repository = AndroidModuleRepository.getModule(SystemSettingRepository.class);

    public static void startActivity(Context context){
        Intent intent = new Intent(context, WebViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {

        if(webViewFragment.canGoBack())
            webViewFragment.goBack();
        else
            super.onBackPressed();

    }

    @AfterViews
    void after() {

        String url = repository.getStringValue("start_url");

        webViewFragment =   WebViewFragment.newInstance(url);

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ft.add(R.id.activity_main_frame , webViewFragment);

        ft.commit();
    }
}
