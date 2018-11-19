package com.smileflowpig.money.moneyplatfrom.activities;

import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;
import com.smileflowpig.money.moneyplatfrom.Adapter.TaskHongbaoAdapter;
import com.smileflowpig.money.moneyplatfrom.util.CountDownTimerUtil;
import com.smileflowpig.money.moneyplatfrom.weight.MyMoneyTextView;
import com.smileflowpig.money.moneyplatfrom.weight.MyTextView;

import java.util.ArrayList;

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
    MyMoneyTextView money_text;
    //任务
    @BindView(R.id.activity_task_textView)
    MyTextView task_text;
    @BindView(R.id.activity_task_complete_textView)
    MyTextView compltet_text;
    @BindView(R.id.activity_task_zhuan_textView)
    MyTextView zhuan_text;
    @BindView(R.id.activity_task_xianjinhongbao_textView)
    MyTextView xianji_text;


    @BindView(R.id.activity_task_recyclerView)
    RecyclerView recyclerView;


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_task_hongbao_layout;
    }


    @Override
    protected void initWidget() {
        super.initWidget();
        initTTF();
        initCountDownUtil(24 * 60 * 60 * 1000);
        initRecyclerView();
    }

    private void initTTF() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/pangtongzhengti.ttf");
        xianji_text.setTypeface(typeface);
        zhuan_text.setTypeface(typeface);
        task_text.setTypeface(typeface);
        money_text.setTypeface(typeface);
        compltet_text.setTypeface(typeface);

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


    TaskHongbaoAdapter adapter;

    private void initRecyclerView() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        strings.add("5");
        strings.add("6");

        adapter = new TaskHongbaoAdapter(R.layout.item_task_hongbao_layout, strings);
        adapter.bindToRecyclerView(recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }
}
