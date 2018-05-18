package money.kuxuan.platform.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import money.kuxuan.platform.common.app.Activity;
import money.kuxuan.platform.common.timer.BaseTimerTask;
import money.kuxuan.platform.common.timer.ITimerListener;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.web.WebActivity;

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
        Intent intent = new Intent(context,AdActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_ad;
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
        //加载
        Glide.with(this).load(filePath).into(imageView);
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
