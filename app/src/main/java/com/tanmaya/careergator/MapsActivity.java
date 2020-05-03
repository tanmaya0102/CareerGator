package com.tanmaya.careergator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.GeolocationPermissions;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
public class MapsActivity extends AppCompatActivity {

    public class GeoWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if( URLUtil.isNetworkUrl(url) ) {
                return false;
            }
            if (appInstalledOrNot(url)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity( intent );
            } else {
                // do something if app is not installed
            }
            return true;
        }

    }


    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }
    public class GeoWebChromeClient extends WebChromeClient {
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin,
                                                       GeolocationPermissions.Callback callback) {
            // Always grant permission since the app itself requires location
            // permission and the user has therefore already granted it
            callback.invoke(origin, true, false);
        }
    }
        WebView mWebView;
        @SuppressLint("SetJavaScriptEnabled")
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
            mWebView = findViewById(R.id.webview);
            // Browser niceties -- pinch / zoom, follow links in place
            mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            mWebView.getSettings().setBuiltInZoomControls(true);
            mWebView.setWebViewClient(new GeoWebViewClient());
            // Below required for geolocation
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setGeolocationEnabled(true);
            mWebView.setWebChromeClient(new GeoWebChromeClient());
            // Load google.com
            mWebView.loadUrl("https://www.google.co.in/maps/search/coaching+center/");
        }
        @Override
        public void onBackPressed() {
            // Pop the browser back stack or exit the activity
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            }
            else {
                super.onBackPressed();
            }


}

}

