package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import com.smileflowpig.money.factory.bean.BillManagerBean;

public class BillManagerAdapter extends BaseQuickAdapter<BillManagerBean, BillManagerViewHolder> {


    public BillManagerAdapter(int layoutResId, @Nullable List<BillManagerBean> data) {
        super(layoutResId, data);
    }

    public BillManagerAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BillManagerViewHolder helper, final BillManagerBean item) {
        if (!item.isTrueData()) {
            helper.data_layout.setVisibility(View.GONE);
            helper.add_layout.setVisibility(View.VISIBLE);
        } else {
            helper.data_layout.setVisibility(View.VISIBLE);
            helper.add_layout.setVisibility(View.GONE);
        }

        helper.add_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.goToAdd();
                }
            }
        });
        helper.gotoDetial_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.goToDetial(item);
            }
        });

        helper.setting_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.goTosettingyihuan(item);
            }
        });
        if (item.isTrueData()) {
            Glide.with(mContext).load(item.getPlatform_icon()).into(helper.imageView);
            helper.name_text.setText(item.getPlatform_name());
            helper.money_text.setText("￥" + item.getAmount());

            int huankuanData = item.getHuankuanData();
            if (item.isDaoqi()) {
                helper.day_text.setTextColor(Color.RED);
                helper.status_text.setTextColor(Color.RED);
                helper.status_text.setText("天已逾期");
            } else {
                helper.status_text.setText("天到期");
                helper.day_text.setTextColor(Color.BLUE);
                helper.status_text.setTextColor(Color.BLUE);
            }
            helper.day_text.setText(Math.abs(huankuanData) + "");
            helper.time_text.setText(item.getDue_date()+"到期");

//            helper.time_text.setText(item.getDue_date()+"到期");
        }
    }


    public OnGoListener listener;

    public void setGoListener(OnGoListener listener) {
        this.listener = listener;
    }

    public interface OnGoListener {
        void goToDetial(BillManagerBean item);

        void goTosettingyihuan(BillManagerBean item);

        void goToAdd();
    }
}
