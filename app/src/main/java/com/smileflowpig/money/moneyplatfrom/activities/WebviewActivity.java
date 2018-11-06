package com.smileflowpig.money.moneyplatfrom.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.smileflowpig.money.R;


/**
 * Created by xieshengqi on 2017/4/8.
 */

public class WebviewActivity extends Activity {
    WebView mWebview;

    public static final String URL = "url";
    public static final String TITLE = "title";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caseurl);
        String url = getIntent().getExtras().getString(URL);

//        mWebview  = findViewById(R.id.case_wv);
//        initWebview();
//        getTitleView(1).setTitle(getIntent().getExtras().getString(TITLE));
//        getTitleView(1).setLeft_text("返回", new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mWebview.canGoBack()) {
//                    mWebview.goBack();
//                } else {
//                    finish();
//                }
//            }
//        });
//        url = "http://mzb.quyaqu.com/divide_red_envelopes/index.html" +
//                "?userId=11855587&activityId=1&appName=金牛记账";
//        url = "http://bw.quyaqu.com/xiaohuazhu/information.html?id=1&view_num=3&sessionid=";
//        if (url == null) {
////            ToastUtil.show(this, "无效链接");
//            return;
//        } else {
//            mWebview.loadUrl(url);
//        }
    }


    private void initWebview() {
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setUseWideViewPort(true);
        mWebview.setInitialScale(57);
        mWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //   支持弹窗式 显示 div
        mWebview.getSettings().setDomStorageEnabled(true);
        mWebview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebview.getSettings().setLayoutAlgorithm(
                WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mWebview.getSettings().setSupportZoom(false);
        mWebview.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebview.getSettings().setLoadWithOverviewMode(true);
        mWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebview.getSettings().setSupportMultipleWindows(true);
        mWebview.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (mWebview.canGoBack()) {
                mWebview.goBack();
                return false;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
