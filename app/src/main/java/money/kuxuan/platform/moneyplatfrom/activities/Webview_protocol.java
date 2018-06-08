package money.kuxuan.platform.moneyplatfrom.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.moneyplatfrom.R;

public class Webview_protocol extends PresenterActivity {

    private String pp;

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        WebView wv = (WebView) findViewById(R.id.wv);
        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setDisplayZoomControls(true);
        wv.getSettings().setDomStorageEnabled(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setSupportZoom(true);
        wv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv.getSettings().setTextSize(WebSettings.TextSize.LARGER);
        wv.loadUrl("http://m.henhaojie.com/AppH5/protocol.html?appName="+"现金小额钱包");

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_webview_protocol;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }
}
