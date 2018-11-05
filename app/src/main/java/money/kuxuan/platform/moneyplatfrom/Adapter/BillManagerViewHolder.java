package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import money.kuxuan.platform.moneyplatfrom.R;

public class BillManagerViewHolder extends BaseQuViewHolder {


    public LinearLayout data_layout;

    public RelativeLayout add_layout;


    public TextView gotoDetial_text,setting_text,name_text,day_text,time_text,money_text,status_text;
    public ImageView imageView;

    public BillManagerViewHolder(View view) {
        super(view);
        gotoDetial_text =view.findViewById(R.id.item_bill_gotodetial_text);
        setting_text =view.findViewById(R.id.item_bill_setting_text);
        name_text =view.findViewById(R.id.item_bill_name_text);
        day_text =view.findViewById(R.id.item_bill_day_text);
        time_text =view.findViewById(R.id.item_bill_daoqitime_text);
        money_text =view.findViewById(R.id.item_bill_money_text);
        imageView =view.findViewById(R.id.item_bill_imageView);
        status_text  =view.findViewById(R.id.item_bill_status_text);
        data_layout = view.findViewById(R.id.item_bill_data_layout);
        add_layout = view.findViewById(R.id.item_bill_addlayout);
    }
}
