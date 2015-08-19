package net.wherewhat.matereal;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

/**
 *
 */
public class WebViewFragment extends Fragment {

    private WebView webView = null;
    private ProgressBar progressBar = null;
    private FrameLayout frameLayout = null;

    private static final String CURRENT_URL = "_current_url_!";

    private String currentUrl  = null;

    private WebViewSetter webViewSetter = null;

    public static WebViewFragment newInstance(String url , WebViewSetter setter) {

        WebViewFragment fragment = new WebViewFragment();

        Bundle args = new Bundle();
        args.putString(CURRENT_URL, url);

        fragment.setArguments(args);

        fragment.setWebViewSetter(setter);

        return fragment;
    }

    public static WebViewFragment newInstance(String url){
        return newInstance(url , null);
    }

    public WebViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            currentUrl = getArguments().getString(CURRENT_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();

        if(savedInstanceState != null){
            currentUrl = savedInstanceState.getString(CURRENT_URL);
        }

        if(webViewSetter != null)
            webViewSetter.setWebView(webView);
        else
            setDefaultWebViewSetting();

        webView.loadUrl(currentUrl);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String url = webView.getUrl();

        outState.putString(CURRENT_URL , url);
    }

    private void initViews(){

        this.webView = (WebView)getActivity().findViewById(R.id.fragment_web_webview);
        this.progressBar = (ProgressBar)getActivity().findViewById(R.id.fragment_web_progress);
        this.frameLayout = (FrameLayout)getActivity().findViewById(R.id.fragment_web_frame);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            //mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setWebViewSetter(WebViewSetter setter){
        this.webViewSetter = setter;
    }

    private void setDefaultWebViewSetting(){

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new DefaultWebViewClient());
    }

    public boolean canGoBack(){

        return webView.canGoBack();
    }

    public void goBack(){
        webView.goBack();
    }

    public interface WebViewSetter{

        public void setWebView(WebView webView);
    }

    private class DefaultWebViewClient extends WebViewClient {  //HERE IS THE MAIN CHANGE.

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            showProgress();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            hideProgress();
        }

        private void showProgress(){

            progressBar.setVisibility(View.VISIBLE);
            frameLayout.setVisibility(View.VISIBLE);
        }

        private void hideProgress(){

            progressBar.setVisibility(View.INVISIBLE);
            frameLayout.setVisibility(View.INVISIBLE);
        }

    }
}
