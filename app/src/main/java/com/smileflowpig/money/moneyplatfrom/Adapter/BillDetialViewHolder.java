package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.view.View;
import android.widget.TextView;

import com.smileflowpig.money.R;

class BillDetialViewHolder extends BaseQuViewHolder {


    public TextView time_text, status_text,money_text;

    public BillDetialViewHolder(View view) {
        super(view);
        time_text = (TextView) view.findViewById(R.id.item_bill_detial_name_text);
        status_text = (TextView) view.findViewById(R.id.item_bill_detial_status_text);
        money_text = (TextView) view.findViewById(R.id.item_bill_detial_money_text);
    }
}
