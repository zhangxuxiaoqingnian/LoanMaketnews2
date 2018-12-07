package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smileflowpig.money.R;
import com.smileflowpig.money.moneyplatfrom.Bean.CheckGuidJson;

import java.util.List;

public class CheckGuildAdapter extends BaseQuickAdapter<CheckGuidJson, PopCheckIntervalViewHolder> {
    public CheckGuildAdapter(int layoutResId, @Nullable List<CheckGuidJson> data) {
        super(layoutResId, data);
    }

    public CheckGuildAdapter(@Nullable List<CheckGuidJson> data) {
        super(data);
    }

    public CheckGuildAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(PopCheckIntervalViewHolder helper, CheckGuidJson item) {
        helper.name_text.setText(item.getName());
        if (item.isSelect()) {
            helper.imageView.setVisibility(View.VISIBLE);
        } else {
            helper.imageView.setVisibility(View.INVISIBLE);
        }
    }
}