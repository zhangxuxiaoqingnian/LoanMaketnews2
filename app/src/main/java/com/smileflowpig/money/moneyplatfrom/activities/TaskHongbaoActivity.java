package com.smileflowpig.money.moneyplatfrom.activities;

import android.widget.TextView;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;
import com.smileflowpig.money.moneyplatfrom.util.CountDownTimerUtil;

import butterknife.BindView;

/**
 * 红包任务
 */
public class TaskHongbaoActivity extends Activity {

    //天
    @BindView(R.id.activity_task_day_textView)
    TextView day_text;
    //天
    @BindView(R.id.activity_task_shi_textView)
    TextView shi_text;
    //天
    @BindView(R.id.activity_task_fen_textView)
    TextView fen_text;
    //天
    @BindView(R.id.activity_task_miao_textView)
    TextView miao_text;
    //钱
    @BindView(R.id.activity_task_money_textView)
    TextView money_text;
    //任务
    @BindView(R.id.activity_task_textView)
    TextView task_text;


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_task_hongbao_layout;
    }


    @Override
    protected void initWidget() {
        super.initWidget();
        initCountDownUtil(24 * 60 * 60 * 1000);
    }

    CountDownTimerUtil countDownTimerUtil;

    private void initCountDownUtil(long endTime) {

        if (countDownTimerUtil == null)
            countDownTimerUtil = new CountDownTimerUtil(endTime, 1000, day_text, shi_text, fen_text, miao_text);

        countDownTimerUtil.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimerUtil != null)
            countDownTimerUtil.onFinish();
    }
}
