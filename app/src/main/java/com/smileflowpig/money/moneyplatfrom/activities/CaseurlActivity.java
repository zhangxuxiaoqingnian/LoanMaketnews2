package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.smileflowpig.money.moneyplatfrom.util.ToastUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.Constant;
import com.smileflowpig.money.factory.net.Network;
import com.smileflowpig.money.factory.util.SPUtil;

public class CaseurlActivity extends PresenterActivity implements View.OnClickListener{

    @BindView(R.id.case_wv)
    WebView wv;
    @BindView(R.id.casename)
    TextView casename;
    @BindView(R.id.case_back)
    ImageView case_back;
    @BindView(R.id.data_share)
    TextView share;
    @BindView(R.id.sharelayout)
    LinearLayout layout;
    private String urlname;
    private LinearLayout wei_circle;
    private LinearLayout wei_chat;
    private TextView cancle;
    private String strurl;
    private String urlid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        urlid = intent.getStringExtra("urlid");
        urlname = intent.getStringExtra("urlname");

        String urladdress = intent.getStringExtra("urladdress");
        case_back.setOnClickListener(this);
        share.setOnClickListener(this);
//       SharedPreferences sharedPreferences = getSharedPreferences("Logintype", Context.MODE_PRIVATE);
       String sessionid = (String) SPUtil.get(this, Constant.UserInfo.SESSIONID,"");
       String channelId = Network.channelId;

       //casename.setText(urlname);
        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setDisplayZoomControls(true);
        wv.getSettings().setDomStorageEnabled(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setSupportZoom(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setBlockNetworkImage(false);
        //wv.setLayerType(View.LAYER_TYPE_HARDWARE,null);
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

                if(view.getTitle().equals("资讯")){

                    casename.setVisibility(View.VISIBLE);
                    share.setVisibility(View.VISIBLE);
                }else {
                    casename.setVisibility(View.GONE);
                    share.setVisibility(View.GONE);
                }

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

        //资讯h5
        strurl = "http://bw.quyaqu.com/xiaohuazhu/information.html?id="+ urlid +"&view_num="+urlname+"&sessionid="+sessionid;
//        wv.loadUrl("http://bw.quyaqu.com/xiaohuazhu/information.html?id="+ urlid +"&view_num="+urlname+"&sessionid="+sessionid);
        wv.loadUrl(strurl);

        //第一版被抛弃的小花猪
//        if(urlid!=0){
//
//            wv.loadUrl(urladdress+"?id="+urlid+"&title="+urlname+"&sessionid="+sessionid+"&channel_id"+channelId);
//            wv.addJavascriptInterface(new CaseClase(CaseurlActivity.this),"messageHandlers");
//            wv.addJavascriptInterface(new CaseClase(CaseurlActivity.this),"messageHandlers");
//
//        }else {
//
//            wv.loadUrl("https://m.henhaojie.com/xiaohuazhu/burst.html?title="+urlname+"&sessionid="+sessionid+"&channel_id="+channelId);
//        }

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
            case R.id.data_share:
                getsharepopwindow();
                break;
        }
    }

    public void getsharepopwindow(){

        View inflate = LayoutInflater.from(CaseurlActivity.this).inflate(R.layout.activity_share_popup, null, false);
        final PopupWindow popupWindow=new PopupWindow(inflate, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.pop_anim);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(layout, Gravity.BOTTOM,0,0);
        light(0.8f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                light(1.0f);
            }
        });

        wei_circle = (LinearLayout) inflate.findViewById(R.id.m_share_pop_up_wei_circle);
        wei_chat = (LinearLayout) inflate.findViewById(R.id.m_share_pop_up_wei_chat);
        cancle = (TextView) inflate.findViewById(R.id.m_share_pop_up_cancel);

        //朋友圈
        wei_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                UMImage image = new UMImage(CaseurlActivity.this, "");
                UMWeb web = new UMWeb(strurl);
                web.setTitle("测试");
                web.setDescription("测试地址");
                web.setThumb(new UMImage(CaseurlActivity.this, R.mipmap.ic_pig));
                new ShareAction(CaseurlActivity.this)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                        .withMedia(web)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {
                                ToastUtil.show(CaseurlActivity.this, "分享成功");
                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                ToastUtil.show(CaseurlActivity.this, "分享失败");
                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        })//回调监听器
                        .share();
                popupWindow.dismiss();
            }
        });
        wei_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMWeb web = new UMWeb(strurl);
                web.setTitle("测试");
                web.setDescription("测试地址");
                web.setThumb(new UMImage(CaseurlActivity.this, R.mipmap.ic_pig));
                new ShareAction(CaseurlActivity.this)
                        .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .withMedia(web)
                        .setCallback(new UMShareListener() {
                            @Override
                            public void onStart(SHARE_MEDIA share_media) {

                            }

                            @Override
                            public void onResult(SHARE_MEDIA share_media) {
                                ToastUtil.show(CaseurlActivity.this, "分享成功");
                            }

                            @Override
                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                ToastUtil.show(CaseurlActivity.this, "分享失败");
                            }

                            @Override
                            public void onCancel(SHARE_MEDIA share_media) {

                            }
                        })//回调监听器
                        .share();
                popupWindow.dismiss();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }
    public void light(float t){
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = t;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Toast.makeText(CaseurlActivity.this,"开始分享",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {

            Toast.makeText(CaseurlActivity.this,"分享成功",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {

            Toast.makeText(CaseurlActivity.this,"分享失败",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

            Toast.makeText(CaseurlActivity.this,"分享取消",Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
