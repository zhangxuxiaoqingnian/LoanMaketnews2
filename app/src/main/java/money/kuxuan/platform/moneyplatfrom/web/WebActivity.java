package money.kuxuan.platform.moneyplatfrom.web;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.common.utils.DisplayUtil;
import money.kuxuan.platform.common.widget.SelfDialog;
import money.kuxuan.platform.factory.model.db.H5Model;
import money.kuxuan.platform.factory.presenter.web.WebContract;
import money.kuxuan.platform.factory.presenter.web.WebPresenter;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.DetailActivity;
import money.kuxuan.platform.moneyplatfrom.activities.MainActivity;
import money.kuxuan.platform.moneyplatfrom.helper.ImageUtils;


/**
 * Created by Administrator on 2017/6/28 0028.
 */

public class WebActivity extends PresenterActivity<WebContract.Presenter>
        implements WebContract.View{
    @BindView(R.id.webview)
    WebView webview;
    String title_name;
    Intent intent;
    String url;
    @BindView(R.id.tv_title)
   TextView tv_title;
    SelfDialog selfDialog;
//    @BindView(R.id.tv_back)
//    TextView tv_back;
    @BindView(R.id.pg)
    ProgressBar pg;

    String id;
    String appply_id;
    String skip_type;
    int type = 0;
    @BindView(R.id.back_text)
    TextView back_text;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    String enter_type = "activityList";
    private static final String TAG = "WebActivity";
    public static void show(Context context,String name,String url){
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("name",name);
        context.startActivity(intent);
    }
    public static void show(Context context,String name,String url,String id,String skip_type){
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("name",name);
        intent.putExtra("id",id);
        intent.putExtra("skip_type",skip_type);
        context.startActivity(intent);
    }

    public static void show(Context context,String name,String url,String id,String skip_type,int type){
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("name",name);
        intent.putExtra("id",id);
        intent.putExtra("skip_type",skip_type);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    public static void show(Context context,String name,String url,String id,String appply_id,String skip_type){
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("name",name);
        intent.putExtra("id",id);
        intent.putExtra("skip_type",skip_type);
        intent.putExtra("appply_id",appply_id);
        context.startActivity(intent);
    }


    public static void show(Context context,String name,String url,String id,String appply_id,String skip_type,boolean flag){
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra("flag",flag);
        intent.putExtra("url",url);
        intent.putExtra("name",name);
        intent.putExtra("id",id);
        intent.putExtra("skip_type",skip_type);
        intent.putExtra("appply_id",appply_id);
        context.startActivity(intent);
    }


    boolean isPop=false;
    @Override
    protected boolean initArgs(Bundle bundle) {
        title_name = bundle.getString("name");
        url = bundle.getString("url");
        id = bundle.getString("id");
        isPop = bundle.getBoolean("flag");
        appply_id = bundle.getString("appply_id");

        skip_type = bundle.getString("skip_type");
        type = bundle.getInt("type");
        return true;
    }


    @Override
    protected void initWidget() {
        super.initWidget();

        if(!TextUtils.isEmpty(appply_id)&&!TextUtils.isEmpty(id)) {
            mPresenter.pushData(appply_id, "3", skip_type, id);
        }

        if(!TextUtils.isEmpty(title_name)){
            tv_title.setText(title_name);
            if(title_name.equals("身价计算器")){
                enter_type = "valueCalculationa";
            }
        }

        if(TextUtils.isEmpty(id)){
           id = "0";
        }
        if(TextUtils.isEmpty(appply_id)){
            appply_id = "0";
        }
        if(TextUtils.isEmpty(skip_type)){
//            tv_back.setVisibility(View.GONE);
            back_text.setVisibility(View.GONE);
        }
        Log.e(TAG,type+"---type");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webview, true);
        }
        initWebView();



        webview.loadUrl(url);
        webview.setWebViewClient(new MyWebViewClient());
        webview.setWebChromeClient(new MyWebChromeClient());
        webview.addJavascriptInterface(new AndroidtoJs(), "native");//AndroidtoJS类对象映射到js的test对象


        if(isPop){
        lin_parent.post(new Runnable() {
            @Override
            public void run() {
                showPopWindow();
            }
        });
        }
    }

    @BindView(R.id.lin_parent)
    LinearLayout lin_parent;

    private void showPopWindow() {


        View popView = getLayoutInflater().inflate(R.layout.commentpop,null,false);

        final PopupWindow popupWindow = new PopupWindow(popView, DisplayUtil.dip2px(272),DisplayUtil.dip2px(362));

        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);


        popView.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popView.findViewById(R.id.tv_comment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(popupWindow!=null&&popupWindow.isShowing())
                    popupWindow.dismiss();
                                try{
                                    Uri uri = Uri.parse("market://details?id="+getPackageName());
                                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                   overridePendingTransition(R.anim.alpha_in,R.anim.alpha_out);
                                }catch(Exception e){
                                    Toast.makeText(getApplicationContext(), "您的手机没有安装Android应用市场", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }

            }
        });


        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });

        popupWindow.showAtLocation(lin_parent, Gravity.CENTER,0,0);

    }

    @OnClick(R.id.back)
    void onBack(){

         if(TextUtils.isEmpty(skip_type)){
             finish();
         }else{
             if(skip_type.equals("1")) {
                 Log.e(TAG,type+"--------------type");
                 if(type == 0){
                     finish();
                 }else{
                     MainActivity.show(WebActivity.this);
                 }
             }else{
                 finish();
             }
         }


    }
    @OnClick(R.id.back_text)
    void onBackText(){

        if(TextUtils.isEmpty(skip_type)){
            finish();
        }else{
            if(skip_type.equals("1")) {
                Log.e(TAG,type+"--------------type");
                if(type == 0){
                    finish();
                }else{
                    MainActivity.show(WebActivity.this);
                }
            }else{
                finish();
            }
        }

    }
//    @OnClick(R.id.tv_back)


    void tvback()
    {
        createDialog(R.string.apply);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_webview_layout;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (webview != null) {
                if (webview.canGoBack()) {
                    webview.goBack();
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if ((null == mUploadMessage) && (mUploadCallbackAboveL == null)) {
            return;
        }
        if (resultCode == RESULT_OK && (requestCode == PhotoPicker.REQUEST_CODE
                || requestCode == PhotoPreview.REQUEST_CODE)) {
            List<String> photos = null;
            if (intent != null) {
                photos = intent.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                Log.d("photos", photos.get(0) + "," + Uri.parse(photos.get(0)));
                Uri uri = Uri.parse(MediaStore.Images.Media
                        .insertImage(getContentResolver(), ImageUtils.ratio(photos.get(0), 950, 1280),
                                null, null));
                if (mUploadCallbackAboveL != null) {
                    Uri[] uris = new Uri[] {uri};
                    mUploadCallbackAboveL.onReceiveValue(uris);
                    mUploadCallbackAboveL = null;
                } else {
                    mUploadMessage.onReceiveValue(uri);
                    mUploadMessage = null;
                }
            }
        }

    }

    private void initWebView() {
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        WebSettings settings = webview.getSettings();
        settings.setLoadsImagesAutomatically(true);
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ECLAIR_MR1) {
            settings.setAppCacheEnabled(true);
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.FROYO) {
            settings.setPluginState(WebSettings.PluginState.ON);
        }
//        settings.setUserAgentString(
//                "channel=" + BuildConfig.CHANNLE + "&version=" + 2.0 + "&isAndroid=1");
        settings.setAllowFileAccess(true);
        settings.setBuiltInZoomControls(false);

        settings.setDomStorageEnabled(true);
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        //启用地理定位
        settings.setGeolocationEnabled(true);
        settings.setSupportMultipleWindows(true);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webview.setWebViewClient(new MyWebViewClient());
        webview.setWebChromeClient(new MyWebChromeClient());
    }

    @Override
    protected WebContract.Presenter initPresenter() {
        return new WebPresenter(this);
    }


    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, final String url) {
            if (NativeActionUtil.dealNativeUrl(WebActivity.this, url)) {
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
        }

        public void onPageFinished(WebView view, String url) {
        }
    }
    public void showOptions() {
        //如果大于6.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionGen.with(WebActivity.this).addRequestCode(100)
                    .permissions(android.Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE).request();
        } else {
            try {
                PhotoPicker.builder().setPhotoCount(1).start(WebActivity.this);
            } catch (Exception e) {
                Toast.makeText(this, "请打开相机拍照及读写内存卡的权限", Toast.LENGTH_SHORT).show();
            }

        }
    }
    @PermissionSuccess(requestCode = 100) public void camera_success() {
        PhotoPicker.builder().setPhotoCount(1).start(WebActivity.this);
    }

    @PermissionFail(requestCode = 100) private void camera_fail() {
        Toast.makeText(this, "请打开相机拍照及读写内存卡的权限", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
    @Override
    protected void onResume() {
        super.onResume();

        //从相机或相册取出照片后如果重新选择需要制空变量
        if (mUploadCallbackAboveL != null) {
            mUploadCallbackAboveL.onReceiveValue(null);
        }
        if (mUploadMessage != null) {
            mUploadMessage.onReceiveValue(null);
        }

    }

    /**
     * webView加载进度监听
     */
    private class MyWebChromeClient extends WebChromeClient {


        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);

            if(newProgress==100){
                if(pg!=null) {
                    pg.setVisibility(View.GONE);//加载完网页进度条消失
                }
            } else{
                if(pg!=null) {
                    pg.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    pg.setProgress(newProgress);//设置进度值
                }
            }

        }

        @Override
        public void onGeolocationPermissionsShowPrompt(String origin,
                                                       GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }
        // For Android < 3.0
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            mUploadMessage = uploadMsg;
            showOptions();

        }
//
        // For Android  > 4.1.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType,
                                    String capture) {
            mUploadMessage = uploadMsg;
            showOptions();
        }
//
        // For Android > 5.0支持多张上传
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> uploadMsg,
                                         FileChooserParams fileChooserParams) {
            mUploadCallbackAboveL = uploadMsg;
            showOptions();
            return true;
        }

    }
    private class ReOnCancelListener implements DialogInterface.OnCancelListener {

        @Override
        public void onCancel(DialogInterface dialogInterface) {
            if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(null);
                mUploadMessage = null;
            }
            if (mUploadCallbackAboveL != null) {
                mUploadCallbackAboveL.onReceiveValue(null);
                mUploadCallbackAboveL = null;
            }
        }
    }


    /**
     * Dialog
     */
    public void createDialog(@StringRes int str) {
        selfDialog = new SelfDialog(this);
        selfDialog.setTitle("本次是否完成了申请");
        selfDialog.setMessage(R.string.action_active);
        selfDialog.setNoOnclickListener("只是看看", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
                mPresenter.pushData(appply_id,"2",skip_type,id);
                Log.e(TAG,type+"---type");
                if(type == 0){
                    finish();
                }else{
                    MainActivity.show(WebActivity.this);
                }

            }
        });
        selfDialog.setYesOnclickListener("已申请", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                selfDialog.dismiss();
                Log.e(TAG,appply_id+"apply_id");
                mPresenter.pushData(appply_id,"3",skip_type,id);
                Log.e(TAG,type+"---type");
                if(type == 0){
                    finish();
                }else{
                    MainActivity.show(WebActivity.this);
                }
            }
        });
        selfDialog.show();

    }

    private class AndroidtoJs {
        @JavascriptInterface
        public void JSGetProductId(String message) {   //提供给js调用的方法
          Gson gson = new Gson();
            H5Model data = gson.fromJson(message,H5Model.class);
            Log.e(TAG,message);
            if(data.getType().equals("0")){
                DetailActivity.show(WebActivity.this,data.getProduct_id(),enter_type,0);
            }else{
                show(WebActivity.this,null,data.getLink(),data.getProduct_id(),data.getType());
            }

        }

    }


}






