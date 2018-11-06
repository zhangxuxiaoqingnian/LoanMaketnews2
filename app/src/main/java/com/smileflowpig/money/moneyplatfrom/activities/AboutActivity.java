package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;


/**
 * 关于我们
 */
public class AboutActivity extends Activity {

    @BindView(R.id.banben)
    TextView banben;
    //修改
    /**
     * 关于我们现实的入口
     * @param context Context
     */
    public static void  show(Context context){
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_about;
    }


    @Override
    protected void initData() {
        super.initData();
        banben.setText("版本名"+getVersionName());
    }

    //返回键
    @OnClick(R.id.back)
    void backClick(){
        finish();
    }
    /**
     * 获取版本名
     * @return 版本名
     */
    public String getVersionName(){
        PackageManager manager = getPackageManager();
        try {
            //第二个参数代表额外的信息，例如获取当前应用中的所有的Activity
            PackageInfo packageInfo = manager.getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES
            );
            ActivityInfo[] activities = packageInfo.activities;

            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

}
