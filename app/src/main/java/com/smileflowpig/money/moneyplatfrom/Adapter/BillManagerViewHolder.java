package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smileflowpig.money.R;

public class BillManagerViewHolder extends BaseQuViewHolder {


    public LinearLayout data_layout;

    public RelativeLayout add_layout;


    public TextView gotoDetial_text,setting_text,name_text,day_text,time_text,money_text,status_text;
    public ImageView imageView;

    public BillManagerViewHolder(View view) {
        super(view);
        gotoDetial_text = (TextView) view.findViewById(R.id.item_bill_gotodetial_text);
        setting_text = (TextView) view.findViewById(R.id.item_bill_setting_text);
        name_text = (TextView) view.findViewById(R.id.item_bill_name_text);
        day_text = (TextView) view.findViewById(R.id.item_bill_day_text);
        time_text = (TextView) view.findViewById(R.id.item_bill_daoqitime_text);
        money_text = (TextView) view.findViewById(R.id.item_bill_money_text);
        imageView = (ImageView) view.findViewById(R.id.item_bill_imageView);
        status_text  = (TextView) view.findViewById(R.id.item_bill_status_text);
        data_layout = (LinearLayout) view.findViewById(R.id.item_bill_data_layout);
        add_layout = (RelativeLayout) view.findViewById(R.id.item_bill_addlayout);
    }
}
