package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.FrameLayout;

import com.smileflowpig.money.moneyplatfrom.frags.main.CalculateFragment;

import butterknife.BindView;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;
import com.smileflowpig.money.common.app.Fragment;
import com.umeng.analytics.MobclickAgent;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class PlaceActivity extends Activity {
    private Fragment mCurFragment;
    private Fragment mCalculateFragment;



    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @BindView(R.id.place)
    FrameLayout frameLayout;

//    @BindView(R.id.tv_title)
//    TextView tv_title;
@Override
protected boolean isNeedNotch() {
    return true;
}
    String title;

    private static final String TITLE = "TITLE";

    /**
     * 账户Activity显示的入口
     *
     * @param context Context
     */
    public static void show(Context context,String title) {
       Intent intent = new Intent(context, PlaceActivity.class);
        intent.putExtra(TITLE,title);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_place;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        // 初始化Fragment
        mCurFragment = mCalculateFragment = new CalculateFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.place, mCurFragment)
                .commit();

//        tv_title.setText(title);
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        title = bundle.getString(TITLE);

        return !TextUtils.isEmpty(title);
    }




}
