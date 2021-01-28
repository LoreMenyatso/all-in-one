package com.example.gridds270;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AliExpressWeb extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_express_web);

        webView = (WebView) findViewById(R.id.aliexpressWb);
        webView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.loadUrl(getUrl());

    }

    public void saveUrl(String url){
        SharedPreferences sp = getSharedPreferences("SP_WEBVIEW_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("SAVED_URL", url);
        editor.commit();
    }

    public String getUrl(){

        SharedPreferences sp = getSharedPreferences("SP_WEBVIEW_PREFS", MODE_PRIVATE);
        //If you haven't saved the url before, the default value will be google's page
        return sp.getString("SAVED_URL", "https://www.aliexpress.com");

    }

    private class MyWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            //Save the last visited URL
            saveUrl(url);
        }

    }
    /**
     * Called when the activity has detected the user's press of the back
     * key. The {@link #getOnBackPressedDispatcher() OnBackPressedDispatcher} will be given a
     * chance to handle the back button before the default behavior of
     * {@link InstagramWeb #onBackPressed()} is invoked.
     *
     * @see #getOnBackPressedDispatcher()
     */


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }

    }
}
