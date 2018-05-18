package money.kuxuan.platform.moneyplatfrom.guoshen.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.autolayout.utils.AutoUtils;

import money.kuxuan.platform.moneyplatfrom.R;

/**
 * Created by Allence on 2018/5/8 0008.
 */

public class guoshen_PopViewHolder extends BaseViewHolder {


    public TextView tv_qs;
    public TextView tv_hkrnum;
    public TextView tv_hkjenum;
    public TextView tv_status;
    public CheckBox guoshen_checkbox;


    public guoshen_PopViewHolder(View view) {
        super(view);
        AutoUtils.autoSize(view);
        tv_qs = (TextView) view.findViewById(R.id.tv_qs);
        tv_hkrnum = (TextView) view.findViewById(R.id.tv_hkrnum);
        tv_hkjenum = (TextView) view.findViewById(R.id.tv_hkjenum);
        tv_status = (TextView) view.findViewById(R.id.tv_status);
        guoshen_checkbox = (CheckBox) view.findViewById(R.id.guoshen_checkbox);
    }




}
