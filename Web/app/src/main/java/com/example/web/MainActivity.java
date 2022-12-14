package com.example.web;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        webView=(WebView)findViewById(R.id.web);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("210.212.27.79");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
           webView.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }
}
