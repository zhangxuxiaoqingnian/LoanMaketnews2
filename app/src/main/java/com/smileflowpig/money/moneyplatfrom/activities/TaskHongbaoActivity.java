package com.smileflowpig.money.moneyplatfrom.activities;

import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;
import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.factory.bean.TaskBean;
import com.smileflowpig.money.factory.bean.TaskHeadJson;
import com.smileflowpig.money.factory.bean.TaskJson;
import com.smileflowpig.money.factory.data.helper.HongbaoHelper;
import com.smileflowpig.money.moneyplatfrom.Adapter.TaskHongbaoAdapter;
import com.smileflowpig.money.moneyplatfrom.util.CountDownTimerUtil;
import com.smileflowpig.money.moneyplatfrom.weight.MyMoneyTextView;
import com.smileflowpig.money.moneyplatfrom.weight.MyTextView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 红包任务(暂时搁置)
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

    @BindView(R.id.activity_task_title_contentview)
    TextView titleContent_view;


    @BindView(R.id.activity_task_recyclerView)
    RecyclerView recyclerView;
    @Override
    protected boolean isNeedNotch() {
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_task_hongbao_layout;
    }


    @Override
    protected void initWidget() {
        super.initWidget();
        initTTF();
        initRecyclerView();
        getLists();
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
        adapter = new TaskHongbaoAdapter(R.layout.item_task_hongbao_layout);
        adapter.bindToRecyclerView(recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnSettingClickListener(new TaskHongbaoAdapter.OnSettingClickListener() {
            @Override
            public void onTaskClick(TaskJson taskJson) {
                // TODO: 2018/11/21 去完成任务
            }
        });

    }


    private void getLists() {
        HongbaoHelper.taskLists(new DataSource.Callback<TaskBean>() {
            @Override
            public void onDataNotAvailable(int strRes) {

            }

            @Override
            public void onDataLoaded(TaskBean taskBean) {
                if (taskBean != null) {
                    ArrayList<TaskJson> task_completed = taskBean.getTask_completed();
                    ArrayList<TaskJson> task_now = taskBean.getTask_now();
                    ArrayList<TaskJson> task_other = taskBean.getTask_other();
                    ArrayList<TaskJson> taskJsons = new ArrayList<>();
                    if (task_now != null && task_now.size() != 0) {
                        for (int i = 0; i < task_now.size(); i++) {
                            TaskJson taskJson = task_now.get(i);
                            taskJson.setTitleContent("当前任务");
                            if (i == 0) {
                                taskJson.setTitle(true);
                            }
                            taskJsons.add(taskJson);
                        }
                    }
                    if (task_other != null && task_other.size() != 0) {
                        for (int i = 0; i < task_other.size(); i++) {
                            TaskJson taskJson = task_other.get(i);
                            taskJson.setTitleContent("其他任务");
                            if (i == 0) {
                                taskJson.setTitle(true);
                            }
                            taskJsons.add(taskJson);
                        }
                    }
                    if (task_completed != null && task_completed.size() != 0) {
                        for (int i = 0; i < task_completed.size(); i++) {
                            TaskJson taskJson = task_completed.get(i);
                            taskJson.setTitleContent("已完成任务");
                            taskJson.setComplete(true);
                            if (i == 0) {
                                taskJson.setTitle(true);
                            }
                            taskJsons.add(taskJson);
                        }
                    }
                    adapter.setNewData(taskJsons);
                    ArrayList<TaskHeadJson> task_head = taskBean.getTask_head();
                    if (task_head != null && task_head.size() != 0) {
                        setHeadData(task_head.get(0));
                    }
                }
            }
        });

    }

    private void setHeadData(TaskHeadJson taskHeadJson) {
        if (taskHeadJson == null) {
            return;
        }


        String name = taskHeadJson.getName();
        if (!TextUtils.isEmpty(name))
            task_text.setText(name);
        String money = taskHeadJson.getMoney();
        if (!TextUtils.isEmpty(money))
            money_text.setText(money);
        String count_down = taskHeadJson.getCount_down();
        if (!TextUtils.isEmpty(count_down))
            titleContent_view.setText(count_down);

        String end_time = taskHeadJson.getEnd_time();
        try {
            long end = Long.parseLong(end_time) * 1000;
            long endTime = System.currentTimeMillis() - end;
            initCountDownUtil(endTime);
        } catch (Exception e) {

        }

    }
}
