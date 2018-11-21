package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;
import com.smileflowpig.money.moneyplatfrom.util.GlideUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class HongbaoRecordActivity extends Activity {


    @BindView(R.id.activity_hongbao_record_money_text)
    TextView money_text;
    @BindView(R.id.activity_hongbao_record_image)
    ImageView imageView;
    @BindView(R.id.activity_hongbao_record_name_text)
    TextView name_text;

    @BindView(R.id.activity_hongbao_record_title)
    TextView title_text;

    @OnClick(R.id.back_img)
    void onBack() {
        finish();
    }


    @OnClick(R.id.hongbao_record_text)
    void goToRecord() {
        // TODO: 2018/11/20 红包记录 

    }

    public static final String TASK_NAME = "task_name";
    public static final String MONEY_COUNT = "money_count";
    public static final String ISGET = "isget";
    public static final String TITLE = "title";


    public static void show(Context context,String title, String task_name, String money_count, boolean isGet) {
        Intent intent = new Intent(context, HongbaoRecordActivity.class);
        intent.putExtra(TASK_NAME, task_name);
        intent.putExtra(TITLE, title);
        intent.putExtra(MONEY_COUNT, money_count);
        intent.putExtra(ISGET, isGet);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_hongbao_record_layout;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        Intent intent = getIntent();
        String task_name = intent.getStringExtra(TASK_NAME);
        String money_count = intent.getStringExtra(MONEY_COUNT);
        String title = intent.getStringExtra(TITLE);
        boolean isGet = intent.getBooleanExtra(ISGET, false);
        GlideUtil.setImageViewCrop(this,R.mipmap.ic_flowpig,imageView);
        title_text.setText(title);
        if (isGet) {
            name_text.setText("您已领取过" + task_name);
        } else {
            name_text.setText("您成功领取" + task_name);
        }
        money_text.setText(money_count);
    }
}
