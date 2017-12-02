package com.touristhan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.GeolocationPermissions.Callback;

public class findplaces extends AppCompatActivity {
  WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findplaces);
        wv1=(WebView)findViewById(R.id.findplc);
        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings webSettings = wv1.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        wv1.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }
        });

        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);

        wv1.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin,
                                                           Callback callback) {

                callback.invoke(origin, true, false);
            }
        });

        wv1.loadUrl("https://e-lite.000webhostapp.com/map.html");

    }

}
