package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smileflowpig.money.R;

import java.util.List;

public class TaskHongbaoAdapter extends BaseQuickAdapter<String, TaskHongbaoViewHolder> {
    public TaskHongbaoAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public TaskHongbaoAdapter(@Nullable List<String> data) {
        super(data);
    }

    public TaskHongbaoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(TaskHongbaoViewHolder helper, String item) {

        if (helper.getAdapterPosition() % 2 == 0) {
            helper.frame_layout.setBackgroundResource(R.mipmap.icon_task_big_bg);
            helper.title_layout.setVisibility(View.VISIBLE);
            helper.line_view.setVisibility(View.VISIBLE);
            helper.loadding_img.setVisibility(View.VISIBLE);
        } else {
            helper.frame_layout.setBackgroundResource(R.mipmap.icon_task_small_bg);
            helper.title_layout.setVisibility(View.GONE);
            helper.line_view.setVisibility(View.GONE);
            helper.loadding_img.setVisibility(View.GONE);

        }

    }
}
