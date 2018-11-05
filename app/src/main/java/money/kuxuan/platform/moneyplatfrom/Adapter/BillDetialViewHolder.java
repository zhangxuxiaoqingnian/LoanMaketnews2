package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.view.View;
import android.widget.TextView;

import money.kuxuan.platform.moneyplatfrom.R;

class BillDetialViewHolder extends BaseQuViewHolder {


    public TextView time_text, status_text,money_text;

    public BillDetialViewHolder(View view) {
        super(view);
        time_text = view.findViewById(R.id.item_bill_detial_name_text);
        status_text = view.findViewById(R.id.item_bill_detial_status_text);
        money_text = view.findViewById(R.id.item_bill_detial_money_text);
    }
}
