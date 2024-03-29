package com.smileflowpig.money.common.app;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.android.tu.loadingdialog.LoadingDailog;
import com.android.tu.loadingdialog.LoadingDialog;
import com.smileflowpig.money.common.R;
import com.smileflowpig.money.common.utils.NavigationBarUtil;
import com.smileflowpig.money.common.utils.NotchUtil;
import com.smileflowpig.money.common.widget.AndroidNeigation;
import com.smileflowpig.money.common.widget.StatusBarUtil;
import com.smileflowpig.money.common.widget.convention.PlaceHolderView;
import com.zhy.autolayout.AutoLayoutActivity;

import java.lang.reflect.Method;
import java.util.List;

import butterknife.ButterKnife;


/**
 * Activity的基类
 *
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public abstract class Activity extends AutoLayoutActivity {

    protected PlaceHolderView mPlaceHolderView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在界面未初始化之前调用的初始化窗口


        initWidows();

        if (initArgs(getIntent().getExtras())) {
            // 得到界面Id并设置到Activity界面中
            int layId = getContentLayoutId();
            ViewGroup inflate = (ViewGroup) LayoutInflater.from(this).inflate(layId, null);
            setContentView(inflate);
            //判断是不是androidP系统
            if (Build.VERSION.SDK_INT >= 28 &&isNeedNotch()) {
                NotchUtil.adaptationView(inflate, this);
            }
            initBefore();
            initWidget();
            initData();
            //虚拟键适配
            if (NavigationBarUtil.checkDeviceHasNavigationBar(this)) {
//                NavigationBarUtil.initActivity(findViewById(android.R.id.content));
                AndroidNeigation.assistActivity(inflate);
            }
        } else {
            finish();
        }

    }

    protected abstract boolean isNeedNotch();


    LoadingDailog dialog;

    /**
     * 只有progressDialog
     */
    public void showOnlyDialogLoadding() {
        if (dialog == null) {
            LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(this)
                    .setMessage("加载中...")
                    .setCancelable(true)
                    .setCancelOutside(true);
            dialog = loadBuilder.create();
        }
        if (!dialog.isShowing())
            dialog.show();
    }

    public void hideOnDialogLoading() {
        if (dialog != null)
            dialog.dismiss();

    }


    /**
     * 初始化控件调用之前
     */
    protected void initBefore() {

    }

    /**
     * 初始化窗口
     */
    protected void initWidows() {


//
//        if(isFlymeOS()){
//
//            System.out.println("是魅族手机");
//
//        }else {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        StatusBarUtil.StatusBarLightMode(this);

    }

    /**
     * 获取魅族系统操作版本标识
     */
    private static String getMeizuFlymeOSFlag() {
        String str = getSystemProperty("ro.build.display.id", "");
        return str;
    }

    private static String getSystemProperty(String key, String defaultValue) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String) get.invoke(clz, key, defaultValue);
        } catch (Exception e) {
        }
        return defaultValue;
    }

    // 判断是魅族操作系统
    private static boolean isFlymeOS() {
        return getMeizuFlymeOSFlag().toLowerCase().contains("flyme");
    }


    /**
     * 初始化相关参数
     *
     * @param bundle 参数Bundle
     * @return 如果参数正确返回True，错误返回False
     */
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    /**
     * 得到当前界面的资源文件Id
     *
     * @return 资源文件Id
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     */
    protected void initWidget() {
        ButterKnife.bind(this);
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }


    @Override
    public boolean onSupportNavigateUp() {
        // 当点击界面导航返回时，Finish当前界面
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        // 得到当前Activity下的所有Fragment
        @SuppressLint("RestrictedApi")
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        // 判断是否为空
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment : fragments) {
                // 判断是否为我们能够处理的Fragment类型
                if (fragment instanceof com.smileflowpig.money.common.app.Fragment) {
                    // 判断是否拦截了返回按钮
                    if (((com.smileflowpig.money.common.app.Fragment) fragment).onBackPressed()) {
                        // 如果有直接Return
                        return;
                    }
                }
            }
        }

        super.onBackPressed();
        finish();
    }

    /**
     * 设置占位布局
     *
     * @param placeHolderView 继承了占位布局规范的View
     */
    public void setPlaceHolderView(PlaceHolderView placeHolderView) {
        this.mPlaceHolderView = placeHolderView;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
