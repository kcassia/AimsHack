package net.wherewhat.matereal.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.wherewhat.matereal.R;

/**
 * Created by RyuIkHan on 15. 7. 9..
 */
public class WebViewActivity extends android.app.Activity{

    private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = (WebView)findViewById(R.id.main_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new Callback());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptThirdPartyCookies(webView, true);
        String cookieStr = cookieManager.getCookie("http://aimshack.in");
        webView.loadUrl("http://aimshack.in");
    }

    @Override
    public void onBackPressed() {

        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();

    }

    private class Callback extends WebViewClient {  //HERE IS THE MAIN CHANGE.

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }

    }

}