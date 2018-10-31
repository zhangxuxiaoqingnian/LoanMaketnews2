package money.kuxuan.platform.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.factory.Constant;
import money.kuxuan.platform.factory.net.Network;
import money.kuxuan.platform.factory.util.SPUtil;
import money.kuxuan.platform.moneyplatfrom.Bean.CaseClase;
import money.kuxuan.platform.moneyplatfrom.R;

public class CaseurlActivity extends PresenterActivity implements View.OnClickListener{

    @BindView(R.id.case_wv)
    WebView wv;
    @BindView(R.id.casename)
    TextView casename;
    @BindView(R.id.case_back)
    ImageView case_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int urlid = intent.getIntExtra("urlid", -1);
        String urlname = intent.getStringExtra("urlname");
        String urladdress = intent.getStringExtra("urladdress");
        case_back.setOnClickListener(this);
//       SharedPreferences sharedPreferences = getSharedPreferences("Logintype", Context.MODE_PRIVATE);
       String sessionid = (String) SPUtil.get(this, Constant.UserInfo.SESSIONID,"");
       String channelId = Network.channelId;

       casename.setText(urlname);
        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setDisplayZoomControls(true);
        wv.getSettings().setDomStorageEnabled(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setSupportZoom(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setBlockNetworkImage(false);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            wv.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        wv.goBack(); //后退
        wv.goForward();//前进
        wv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv.setWebViewClient(new WebViewClient() {
             public boolean shouldOverrideUrlLoading(WebView view, String url)
               { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                  view.loadUrl(url);
                  return true;
               }
         });

        wv.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                casename.setText(view.getTitle());


            }
        });

        wv.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && wv.canGoBack()) { // 表示按返回键
                        // 时的操作
                        wv.goBack(); // 后退
                        // webview.goForward();//前进
                        return true; // 已处理
                    }
                }
                return false;
            }
        });


        if(urlid!=0){
            wv.loadUrl(urladdress+"?id="+urlid+"&title="+urlname+"&sessionid="+sessionid+"&channel_id"+channelId);
            wv.addJavascriptInterface(new CaseClase(CaseurlActivity.this),"messageHandlers");
            wv.addJavascriptInterface(new CaseClase(CaseurlActivity.this),"messageHandlers");

        }else {

            wv.loadUrl("https://m.henhaojie.com/xiaohuazhu/burst.html?title="+urlname+"&sessionid="+sessionid+"&channel_id="+channelId);
        }

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_caseurl;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.case_back:
                if(wv.canGoBack()){
                    wv.goBack();
                }else {
                    finish();
                }
                break;
        }
    }
}
