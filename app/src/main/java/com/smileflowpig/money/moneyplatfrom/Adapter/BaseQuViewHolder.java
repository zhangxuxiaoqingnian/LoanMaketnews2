package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.autolayout.utils.AutoUtils;

public class BaseQuViewHolder extends BaseViewHolder {

    public BaseQuViewHolder(View view) {
        super(view);
        AutoUtils.auto(view);
    }
}
