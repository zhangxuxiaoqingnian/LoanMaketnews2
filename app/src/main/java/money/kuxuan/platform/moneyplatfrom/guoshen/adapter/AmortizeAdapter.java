package money.kuxuan.platform.moneyplatfrom.guoshen.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import money.kuxuan.platform.factory.model.api.guoshen.RepaymentListBean;
import money.kuxuan.platform.moneyplatfrom.R;

/**
 * Created by Allence on 2018/5/7 0007.
 */

public class AmortizeAdapter extends BaseQuickAdapter<RepaymentListBean.Detail,BaseViewHolder>{


    public AmortizeAdapter(int layoutResId, @Nullable List<RepaymentListBean.Detail> data) {
        super(layoutResId, data);
    }

    public AmortizeAdapter(@Nullable List<RepaymentListBean.Detail> data) {
        super(data);
    }

    public AmortizeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, RepaymentListBean.Detail item) {



        TextView tv_date = helper.getView(R.id.tv_date);
        TextView qishu = helper.getView(R.id.qishu);
        TextView repayment = helper.getView(R.id.repayment);
        TextView tv_status = helper.getView(R.id.tv_status);

        String date = item.getDate();
        String[] dateArr = date.split(" ");
        tv_date.setText(dateArr[0]);
        qishu.setText(item.getPeriods());
        repayment.setText(item.getAmount());

        if(item.getStatus()==0){
            tv_status.setText("待还款");
            tv_status.setTextColor(Color.parseColor("#333333"));

        }else {
            tv_status.setText("已结清");
            tv_status.setTextColor(Color.parseColor("#cccccc"));
        }

    }
}
