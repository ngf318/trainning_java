package com.test.neil.kavayi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity1 extends AppCompatActivity implements View.OnClickListener {
    WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        String processName = MyApplication.getProcessName(this, android.os.Process.myPid());

        TextView tv = findViewById(R.id.tv);
        tv.setText(processName);

        findViewById(R.id.tv).setOnClickListener(this);
        Log.d("nie", "======33333=======");


        mWebView = findViewById(R.id.webview);
        initWebView();
//        mWebView.loadUrl("file:///android_asset/test.html");
//        mWebView.loadUrl("http://admin.funnymamu.com/?tdsourcetag=s_pctim_aiomsg");
        mWebView.loadUrl("http://www.baidu.com");


    }


    private void initWebView() {
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setGeolocationEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setLightTouchEnabled(true);
        webSettings.setSupportMultipleWindows(false);
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        // HTML5 API flags
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("nie", "-----normal webview ----redirect url = " + url);
                return false;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv:
                Intent i = new Intent(this, MainActivity2.class);
                Intent data = getIntent();
                i.putExtras(data);
                i.setData(data.getData());
                startActivity(i);
                break;
        }
    }
}
