package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smileflowpig.money.R;
import com.smileflowpig.money.factory.bean.TaskJson;

import java.util.List;

public class TaskHongbaoAdapter extends BaseQuickAdapter<TaskJson, TaskHongbaoViewHolder> {
    public TaskHongbaoAdapter(int layoutResId, @Nullable List<TaskJson> data) {
        super(layoutResId, data);
    }

    public TaskHongbaoAdapter(@Nullable List<TaskJson> data) {
        super(data);
    }

    public TaskHongbaoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(TaskHongbaoViewHolder helper, final TaskJson item) {


        if (item.isTitle()) {
            helper.frame_layout.setBackgroundResource(R.mipmap.icon_task_big_bg);
            helper.title_layout.setVisibility(View.VISIBLE);
            helper.line_view.setVisibility(View.VISIBLE);
            if (item.isLoadding())
                helper.loadding_img.setVisibility(View.VISIBLE);
            else
                helper.loadding_img.setVisibility(View.INVISIBLE);
            helper.title_text.setText(item.getTitleContent());

        } else {
            helper.frame_layout.setBackgroundResource(R.mipmap.icon_task_small_bg);
            helper.title_layout.setVisibility(View.GONE);
            helper.line_view.setVisibility(View.GONE);
            helper.loadding_img.setVisibility(View.GONE);
        }

        helper.task_name_text.setText(item.getName());
        helper.money_text.setText(item.getMoney());

        if (item.isComplete()) {
            helper.setting_text.setText("已完成");
            helper.setting_text.setBackgroundResource(R.drawable.bill_setting_gray_bg);
            helper.setting_text.setClickable(false);
        } else {
            helper.setting_text.setText("去完成");
            helper.setting_text.setBackgroundResource(R.drawable.bill_setting_bg);
            helper.setting_text.setClickable(true);
        }
        helper.setting_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLis != null)
                    mLis.onTaskClick(item);
            }
        });
    }


    OnSettingClickListener mLis;

    public void setOnSettingClickListener(OnSettingClickListener lis) {
        this.mLis = lis;
    }

    public interface OnSettingClickListener {

        void onTaskClick(TaskJson taskJson);
    }
}
