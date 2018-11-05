package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import money.kuxuan.platform.factory.bean.BillManagerBean;

public class BillManagerComAdapter extends BaseQuickAdapter<BillManagerBean,BillManagerComViewHolder> {


    public BillManagerComAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BillManagerComViewHolder helper, BillManagerBean item) {
        Glide.with(mContext).load(item.getPlatform_icon()).into(helper.imageView);
        helper.name_text.setText(item.getPlatform_name());
        helper.money_text.setText("￥" + item.getAmount());
        helper.time_text.setText(item.getRepayment_time()+"结清");
    }
}
