package com.test.neil.kavayi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> mData = new ArrayList<>();
    ArrayList<String> mTemp = new ArrayList<>();
    WebView mWebView;
//    LottieAnimationView loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = findViewById(R.id.webview);
        initWebView();
//        mWebView.loadUrl("file:///android_asset/test.html");
//        mWebView.loadUrl("http://admin.funnymamu.com/?tdsourcetag=s_pctim_aiomsg");
        mWebView.loadUrl("http://www.baidu.com");

        String xx = "---~!@^[^\\\\:\\\\*\\\\?\\\"<>]+$@% i";
        Log.d("niexxx", String.format(Locale.US, "%s ఇప్పుడు సీటు తీసుకోవడానికి మిమ్మల్ని ఆహ్వానిస్తున్నారు.", ""));
//        String path = "/sdcard/svgaxx/";
        String path = getApplication().getCacheDir().getAbsolutePath() + "/svga1xx/";
        File file = new File(path);
        if (!file.exists() && file.mkdir()) {
            Log.d("niexxx", "=====ccccc=========oooooo");
            File cacheFile = new File(path + "sss.svga");
            try {
                if (file.exists()) {
                    Log.d("niexxx", "=====exists=========oooooo");
                }
                cacheFile.createNewFile();
                FileOutputStream outputStream = new FileOutputStream(cacheFile);
                byte[] bytes = new byte[] {0, 1, 0x11};
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("niexxx", "=====error ===========" + e.toString());
            }
        } else {
            Log.d("niexxx", "===eeeeee===========oooooo");
        }

        if (file.exists())  Log.d("niexxx", "=====xxxxxx=========oooooo");

        findViewById(R.id.coordinate_layout).setOnClickListener(this);


        Map<String, Object> value = new HashMap<>();
        value.put("platform", "mi");
        value.put("title", "this is a title");
        value.put("event", 1);
        Gson gson = new Gson();
        Toast.makeText(this, gson.toJson(value), Toast.LENGTH_LONG).show();



        mData.addAll(mTemp);

//        loading = findViewById(R.id.loading);

    }

    private void Test1() {
        /*loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.coordinate_layout:
                /*if (loading.getVisibility() == View.GONE) {
                    loading.setVisibility(View.VISIBLE);
                } else {
                    loading.setVisibility(View.GONE);
                }*/

                startActivity(new Intent(this, ActivityDsign.class));
               /* Intent i = new Intent(this, MainActivity1.class);
                i.putExtra("test_1", "hello");
                i.putExtra("test_2", 1001);
                Uri uri = Uri.parse("http://uri.xxx.pa/aaa");
                i.setData(uri);
                startActivity(i);*/

              /*  PackageManager packageManager = getPackageManager();

                  Intent intent = packageManager.getLaunchIntentForPackage("com.nebula.mamu");
                  startActivity(intent);*/

/*                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("fun://ui.activity/ViewPagerVerticalActivity?postId=21113&heighDivideWidth=1"));
                intent.putExtra("target", "what are you");
                intent.putExtra("pushType", "2");
                startActivity(intent);*/

        /*        Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://4funindia.page.link?sd=This%20link%20works%20whether%20the%20app%20is%20installed%20or%20not!&si=http%3A%2F%2Fpic37.nipic.com%2F20140110%2F17563091_221827492154_2.jpg&st=Example%20of%20a%20Dynamic%20Link&apn=com.nebula.mamu&link=https%3A%2F%2Fplay.google.com%2Fstore%2Fapps%2Fdetails%3Fid%3Dcom.nebula.mamu"));
                startActivity(intent);*/

                break;
        }
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
}
