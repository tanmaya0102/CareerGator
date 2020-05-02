package com.tanmaya.careergator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CustomerCare extends AppCompatActivity {
    WebView webView;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_care);
        webView=findViewById(R.id.webview);
        webView.loadUrl("https://www.tidio.com/talk/nj93zc7oabjmrzz54yyyhgrf6dvtrcnb");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
    }

}
