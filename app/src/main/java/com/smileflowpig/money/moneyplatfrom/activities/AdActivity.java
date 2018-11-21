package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smileflowpig.money.moneyplatfrom.web.WebActivity;

import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;
import com.smileflowpig.money.common.timer.BaseTimerTask;
import com.smileflowpig.money.common.timer.ITimerListener;
import com.umeng.analytics.MobclickAgent;

public class AdActivity extends Activity
    implements ITimerListener
{

    private static final String KEY_IMAGE_FILE = "KEY_IMAGE_FILE";
    private static final String KEY_AD_LINK = "KEY_AD_LINK";
    private static final String KEY_AD_TYPE = "KEY_AD_TYPE";
    private static final String KEY_AD_ID = "KEY_AD_ID";

    public  final String FLAG= "1";
    public  final int STEPTYPE= 1;
    private static final String X = "X";  //需要显示弹框图的跳转

    @BindView(R.id.tv_launcher_timer)
    TextView tv_launcher_timer;

    @BindView(R.id.ad_img)
    ImageView imageView;


    private String filePath;
    private String id;
    private String link;
    private String type;

    private int mCount = 3;
    private static final String TAG = "AdActivity";
    private static final String SHOWADDIALOG = "SHOWADDIALOG";
   private  boolean showAdDialog;
    private static final String OLD_PICTURE = "OLD_PICTURE";

    private static final String LINK = "LINK";
    private static final String URL = "URL";
    private static final String ID = "ID";
    private static final String TYPE = "TYPE";


    private Timer mTimer = null;

    public static void show(Context context) {

        Intent intent = new Intent(context, AdActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_ad;
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
    protected void initWidget() {
        super.initWidget();
        //初始化时间
        initTimer();
        //加载广告图片
        load();
        Intent intent = getIntent();
        showAdDialog = intent.getBooleanExtra(SHOWADDIALOG,false);


//        Observable<HomeBase> homeBaseObservable = new NetRequestUtils().bucuo().getbaseretrofit().getAdPicture("750x1334").subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//        homeBaseObservable.subscribe(new Observer<HomeBase>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(HomeBase homeBase) {
//
//                System.out.println("成功了");
//                String image_url = homeBase.rst.data.image_url;
//                //加载
//                Glide.with(AdActivity.this).load(image_url).into(imageView);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });


            //加载
            Glide.with(AdActivity.this).load(filePath).into(imageView);



    }



    /**
     * 加载缓存的广告图片
     */
    private void load(){
        SharedPreferences sp = getSharedPreferences(OLD_PICTURE,
                Context.MODE_PRIVATE);
        filePath = sp.getString(KEY_IMAGE_FILE,"");
        id = sp.getString(KEY_AD_ID,"");
        type = sp.getString(KEY_AD_TYPE,"");
        link = sp.getString(KEY_AD_LINK,"");
    }


    @OnClick(R.id.tv_launcher_timer)
    void onClickTimerView(){
        if(mTimer!=null){
            mTimer.cancel();
            mTimer = null;
        }
        MainActivity.show(this);
        finish();

    }

    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }



    @Override
    public void onTimer() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mTimer!=null){
                    tv_launcher_timer.setText(String.format("倒计时%ss",mCount));
               mCount--;
                    if(mCount<0){
                        if(mTimer!=null){
                            mTimer.cancel();
                            mTimer = null;
                        }
                        MainActivity.show(AdActivity.this);
                        finish();
                    }
                }
            }
        });
    }

    @OnClick(R.id.ad_img)
    void clickAdImg(){
        Log.e(TAG,"收到点击");
        if(mTimer!=null){
            mTimer.cancel();
            mTimer = null;
        }
        Log.e(TAG,"type"+type);
        Log.e(TAG,"id"+id);
        MainActivity.show(this);
        if(type.equals("0")){
            if(id.equals("0")){
                WebActivity.show(this,null,link,id,type,STEPTYPE);
                finish();
            }else{
                DetailActivity.show(this,id,FLAG,"starAd");
                finish();
            }
        }else{
            WebActivity.show(this,null,link,id,type,STEPTYPE);
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }
}
