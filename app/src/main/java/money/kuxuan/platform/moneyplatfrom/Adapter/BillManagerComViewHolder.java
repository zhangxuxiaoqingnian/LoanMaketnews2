package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import money.kuxuan.platform.moneyplatfrom.R;

public class BillManagerComViewHolder extends BaseQuViewHolder {


    public TextView name_text, time_text, money_text;
    public ImageView imageView;

    public BillManagerComViewHolder(View view) {
        super(view);
        name_text = (TextView) view.findViewById(R.id.item_bill_name_text);
        time_text = (TextView) view.findViewById(R.id.item_bill_com_time_text);
        money_text = (TextView) view.findViewById(R.id.item_bill_com_money_text);
        imageView = (ImageView) view.findViewById(R.id.item_bill_imageView);
    }
}
