package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;

import butterknife.BindView;

public class FlowMeActivity extends PresenterActivity implements View.OnClickListener{

    @BindView(R.id.flowme_back)
    LinearLayout back;

    @BindView(R.id.banben)
    TextView banben;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        back.setOnClickListener(this);

        banben.setText("版本名"+getVersionName());

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
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_flow_me;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.flowme_back:
                finish();
                break;
        }
    }
}
