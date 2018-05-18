package money.kuxuan.platform.moneyplatfrom.helper;



import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import money.kuxuan.platform.common.widget.PortraitView;
import money.kuxuan.platform.common.widget.recycler.RecyclerAdapter;
import money.kuxuan.platform.factory.model.db.Product;
import money.kuxuan.platform.moneyplatfrom.R;

/**
 * 每一个Cell的布局操作
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */

public class ProductViewHolder extends RecyclerAdapter.ViewHolder<Product> {
    private Context context;
    //产品日利率或月利率数值
    @BindView(R.id.txt_rate_number)
    TextView txt_rate_number;
    //日利率或月利率
    @BindView(R.id.txt_rate)
    TextView txt_rate;
    //产品人数
    @BindView(R.id.txt_people_number)
    TextView txt_people_number;
    //产品描述
    @BindView(R.id.txt_desc)
    TextView txt_desc;
    //产品图片
    @BindView(R.id.im_portrait)
    PortraitView im_portrait;
    //产品名称
    @BindView(R.id.txt_name)
    TextView txt_name;
    //产品副标题
    @BindView(R.id.txt_prod_title)
    TextView txt_prod_title;

    public ProductViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
    }

    protected void onBind(Product product) {
        im_portrait.setup(Glide.with(context), product);
        txt_people_number.setText(product.getApplicants());
        txt_name.setText(product.getName());
        if (!TextUtils.isEmpty(product.getProd_title())) {
            txt_prod_title.setText(product.getProd_title());
        } else {
            txt_prod_title.setVisibility(View.GONE);
        }
        txt_desc.setText(product.getDescription());
        txt_rate.setText(product.getShow_day());
        if (product.getShow_day().equals("日利率")) {
            txt_rate_number.setText(product.getDay_rate() + "%");
        } else {
            txt_rate_number.setText(product.getMonthly_rate() + "%");
        }

    }
}
