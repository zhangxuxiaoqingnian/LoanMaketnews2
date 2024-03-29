package com.smileflowpig.money.moneyplatfrom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;


import com.smileflowpig.money.factory.Constant;
import com.smileflowpig.money.factory.Factory;
import com.smileflowpig.money.factory.util.LoginStatusUtil;
import com.smileflowpig.money.factory.util.SPUtil;
import com.smileflowpig.money.moneyplatfrom.activities.AdActivity;
import com.smileflowpig.money.moneyplatfrom.activities.MainActivity;

import butterknife.BindView;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.factory.model.api.launcher.RspAdModel;
import com.smileflowpig.money.factory.model.db.AdModel;
import com.smileflowpig.money.factory.presenter.launcher.LauncherContract;
import com.smileflowpig.money.factory.presenter.launcher.LauncherPresenter;
import com.smileflowpig.money.moneyplatfrom.push.JumpJson;
import com.smileflowpig.money.moneyplatfrom.push.OnPushRecivcerListener;
import com.smileflowpig.money.moneyplatfrom.push.PushManager;
import com.umeng.analytics.MobclickAgent;


public class LaunchActivity extends PresenterActivity<LauncherContract.Presenter>
        implements LauncherContract.View, LauncherPresenter.AdPictureListener {
    @Override
    protected boolean isNeedNotch() {
        return false;
    }

    private static final int MSG_VISIBLE = 0;

    private static final String KEY_TIME = "KEY_IMAGE_FILE";
    private static final int MSG_ERROR = 1;
    @BindView(R.id.activity_launch)
    ImageView mImageView;


    String filePath = "";

    private static final long delayTime = 100;


    private static final String X = "X";  //需要显示弹框图的跳转

    private static final String TAG = "LaunchActivity";

    private static final String KEY_IMAGE_FILE = "KEY_IMAGE_FILE";
    private static final String KEY_AD_LINK = "KEY_AD_LINK";
    private static final String KEY_AD_TYPE = "KEY_AD_TYPE";
    private static final String KEY_AD_ID = "KEY_AD_ID";
    private static final String CHANNEL = "CHANNEL";

    private static final String CHANNELOKORNOTOK = "CHANNELOKORNOTOK";

    private static final String OLD_PICTURE = "OLD_PICTURE";

    private static final String NEW_PICTURE = "NEW_PICTURE";
    private AdModel adModel;

    private boolean showAdDialog = true;
    private SharedPreferences sp;
    private SharedPreferences sp2;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_launch;
    }


    @Override
    protected void initWidget() {
        super.initWidget();
        mPresenter.start();
        mImageView.setAlpha(0.1f);

        sp = getSharedPreferences("Overs", MODE_PRIVATE);
        sp2 = getSharedPreferences("isshowimg",
                Context.MODE_PRIVATE);
        PushManager.register(this);
        PushManager.huaweiConnect(this);
        //添加推送别名
        if (LoginStatusUtil.isLogin()) {
            String phone = (String) SPUtil.get(this, Constant.UserInfo.USERNAME, "");
            if (!TextUtils.isEmpty(phone)) {
                PushManager.setPushAlais(this, phone);
            }
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void initWidows() {
        super.initWidows();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTheme(R.style.AppTheme_Main);
    }

    @Override
    protected void initData() {
        super.initData();
        // 动画进入到50%等待PushId获取到
        startAnim(0.5f, new Runnable() {
            @Override
            public void run() {
                // 检查等待状态
                waitPushReceiverId();
            }
        });
    }

    /**
     * 等待个推框架对我们的PushId设置好值
     */
    private void waitPushReceiverId() {
        skip();
    }

    /**
     * 在跳转之前需要把剩下的50%进行完成
     */
    private void skip() {
        startAnim(600, new Runnable() {
            @Override
            public void run() {
                reallySkip();
            }
        });
    }

    /**
     * 真实的跳转
     */
    private void reallySkip() {
        //检测跳转到过审页还是线上页
//        if (checkChannel()) {
        // 检查跳转到广告页还是跳转到主页
//            if (checkData()==false) {


        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("custorm://com.smileflowpig.money/test?title=goodnews&content=this is test"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        String s = intent.toUri(Intent.URI_INTENT_SCHEME);
        Log.e("LauncherPresenter", s);
        boolean iscover = sp2.getBoolean("iscover", false);
        boolean oldwer = sp.getBoolean("oldwer", false);
        if (iscover) {
            MainActivity.show(this);
            finish();
        } else {
            if (oldwer) {
                AdActivity.show(this);
                finish();
            } else {
                sp.edit().putBoolean("oldwer", true).commit();
                MainActivity.show(this);
                finish();
            }
        }

        finish();
//        } else {
//      //     HomeActivity.show(this);
////           finish();
//            MainActivity.show(this);
//            Log.e(TAG, showAdDialog + "--A-DASDASDAS");
//            finish();
//            // 检查跳转到广告页还是跳转到主页
////            if (checkData()==false) {
////                AdActivity.show(this);
////                finish();
////            } else {
////                Log.e(TAG, showAdDialog + "--A-DASDASDAS");
////                MainActivity.show(this);
////                finish();
////            }
////            finish();
//        }
    }

    /**
     * 给背景设置一个动画
     *
     * @param endProgress 动画的结束进度
     * @param endCallback 动画结束时触发
     */
    private void startAnim(float endProgress, final Runnable endCallback) {
        //获取图片
        float openAlpha = mImageView.getAlpha();
        ObjectAnimator anim = ObjectAnimator.ofFloat(mImageView, "alpha", 1f, 1f);
        anim.setDuration(600); // 时间

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 结束时触发
                endCallback.run();
            }
        });
        anim.start();
    }

    @Override
    public void setImage(String url) {

    }


    @Override
    public void setStartImage(AdModel adModel) {
        this.adModel = adModel;
    }


    @Override
    protected LauncherContract.Presenter initPresenter() {
        return new LauncherPresenter(this, this);
    }

    public boolean checkData() {
        return !load().equals("");
    }

    private String load() {
        SharedPreferences sp = getSharedPreferences(OLD_PICTURE,
                Context.MODE_PRIVATE);
        filePath = sp.getString(KEY_IMAGE_FILE, "");
        return filePath;

    }


    @Override
    public void loadTry(String filePath, RspAdModel rspAdModel) {
        this.filePath = filePath;
        save(filePath, rspAdModel);


    }

    private void save(String filePath, RspAdModel rspAdModel) {
        SharedPreferences sp = getSharedPreferences(OLD_PICTURE,
                Context.MODE_PRIVATE);
        sp.edit()
                .putString(KEY_IMAGE_FILE, filePath)
                .putString(KEY_AD_LINK, rspAdModel.getData().getLink())
                .putString(KEY_AD_TYPE, rspAdModel.getData().getSkip_type())
                .putString(KEY_AD_ID, rspAdModel.getData().getProduct_id())
                .apply();
    }

    private boolean checkChannel() {
//        SharedPreferences sp = getSharedPreferences(CHANNEL,
//                Context.MODE_PRIVATE);
//        String channelOk = sp.getString(CHANNELOKORNOTOK, "1");
//        return channelOk.equals("0");
        return true;
    }


}
