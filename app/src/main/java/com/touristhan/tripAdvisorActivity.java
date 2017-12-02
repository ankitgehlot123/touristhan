package com.touristhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class tripAdvisorActivity extends AppCompatActivity {
   private WebView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripadvisor);
        wv1=(WebView)findViewById(R.id.tripad);
        wv1.setWebViewClient(new MyBrowser());


                wv1.getSettings().setLoadsImagesAutomatically(true);
                wv1.getSettings().setJavaScriptEnabled(true);
                wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                wv1.loadUrl("https://e-lite.000webhostapp.com/main.php?name=ankit");

    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
