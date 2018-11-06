package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import com.smileflowpig.money.R;
import com.smileflowpig.money.factory.bean.BillDetialBean;

public class BillDetialAdapter extends BaseQuickAdapter<BillDetialBean, BillDetialViewHolder> {
    public BillDetialAdapter(int layoutResId) {
        super(layoutResId);
    }


    public BillDetialAdapter(int layoutResId, @Nullable List<BillDetialBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BillDetialViewHolder helper, final BillDetialBean item) {

        helper.time_text.setText((helper.getLayoutPosition() + 1) + ".  " + item.getDue_date() + "到期");
//        helper.time_text.setText(item.getDue_date() + "到期");
        helper.money_text.setText("￥" + item.getAmount());
        if (item.getStatus().equals("1")) {
            helper.status_text.setText("已还款");
            helper.status_text.setTextColor(Color.RED);
            helper.status_text.setBackground(null);

        } else {
            helper.status_text.setTextColor(Color.WHITE);
            helper.status_text.setBackground(mContext.getResources().getDrawable(R.drawable.bill_setting_bg));
            helper.status_text.setText("设置已还");
        }
        helper.status_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mlis != null)
                    mlis.onChangeStatus(item);
            }
        });

    }


    OnSettingClickListener mlis;

    public void setOnSettingListener(OnSettingClickListener listener) {
        this.mlis = listener;
    }


    public interface OnSettingClickListener {
        void onChangeStatus(BillDetialBean billDetialBean);
    }
}
