package money.kuxuan.platform.moneyplatfrom.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import money.kuxuan.platform.common.widget.PortraitView;
import money.kuxuan.platform.factory.model.db.Product;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.DetailActivity;
import money.kuxuan.platform.moneyplatfrom.web.WebActivity;

import static money.kuxuan.platform.moneyplatfrom.R.id.im_portrait;


/**
 * @author: BigFaceCat
 * @function:
 * @date: 17/6/11
 */
//// TODO: 2017/8/25 0025  有机会一定把你优化掉
public class HomeAdapter extends BaseAdapter {
    /**
     * Common
     */


    private LayoutInflater mInflate;
    private Context mContext;
    private List<Product> mData;
    private ViewHolder mViewHolder;


    public HomeAdapter(Context context, List<Product> data) {
        mContext = context;
        mData = data;
        mInflate = LayoutInflater.from(mContext);

    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //获取数据的type类型

        final Product value = (Product) getItem(position);
        //无tag时
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = mInflate.inflate(R.layout.cell_home_list, parent, false);
            mViewHolder.im_portrait = (PortraitView) convertView.findViewById(im_portrait);
            mViewHolder.txt_desc = (TextView) convertView.findViewById(R.id.txt_desc);
            mViewHolder.txt_name = (TextView) convertView.findViewById(R.id.txt_name);
            mViewHolder.txt_people_number =(TextView)  convertView.findViewById(R.id.txt_people_number);
            mViewHolder.txt_prod_title =(TextView)  convertView.findViewById(R.id.txt_prod_title);
            mViewHolder.txt_rate = (TextView)  convertView.findViewById(R.id.txt_rate);
            mViewHolder.txt_rate_number = (TextView)  convertView.findViewById(R.id.txt_rate_number);
            mViewHolder.mLayout = (LinearLayout) convertView.findViewById(R.id.mlayout);
            mViewHolder.smialtext = (TextView) convertView.findViewById(R.id.smialtext);
            mViewHolder.txt_prod_title.setTag(position);
            convertView.setTag(mViewHolder);
        }
        //有tag时
        else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        //填充item的数据
        mViewHolder.txt_name.setText(mData.get(position).getName());
        mViewHolder.txt_desc.setText(mData.get(position).getApplicants()+"人申请");

        if (mData.get(position).getShow_day().equals("日利率")) {
            mViewHolder.txt_rate_number.setText(mData.get(position).getDay_rate() + "%");
        } else {
            mViewHolder.txt_rate_number.setText(mData.get(position).getMonthly_rate() + "%");
        }
        mViewHolder.txt_rate.setText(mData.get(position).getShow_day());
        /*if(mViewHolder.txt_prod_title.getTag().equals(position)){
            if (mData.get(position).getProd_title() == null) {
                mViewHolder.txt_prod_title.setVisibility(View.GONE);
            } else {
                mViewHolder.txt_prod_title.setText(mData.get(position).getProd_title());
            }
        }*/
        if (!TextUtils.isEmpty(mData.get(position).getProd_title())) {
            mViewHolder.txt_prod_title.setText(mData.get(position).getProd_title());
            mViewHolder.txt_prod_title.setVisibility(View.VISIBLE);
        } else {
            mViewHolder.txt_prod_title.setVisibility(View.GONE);
        }

        mViewHolder.im_portrait.setup(Glide.with(mContext),mData.get(position));
        mViewHolder.txt_people_number.setText(mData.get(position).getLend_time());
        mViewHolder.smialtext.setText(mData.get(position).getUpper_amount()+"-"+mData.get(position).getLower_amount());
        mViewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mData.get(position).getSkip_type().equals("0")){
                    DetailActivity.show(mContext,mData.get(position),"hotList");
                }else{
                    WebActivity.show(mContext,mData.get(position).getName(),
                            mData.get(position).getLink(),mData.get(position).getId(),mData.get(position).getSkip_type());
                }
            }
        });
        return convertView;
    }


    private static class ViewHolder {

        //产品日利率或月利率数值
        private TextView txt_rate_number;
        //日利率或月利率
        private TextView txt_rate;
        //产品人数
        private TextView txt_people_number;
        //产品描述
        private TextView txt_desc;
        //产品图片
        private PortraitView im_portrait;
        //产品名称
        private TextView txt_name;
        //产品副标题
        private TextView txt_prod_title;
        //产品布局
        private LinearLayout mLayout;
        private TextView smialtext;
    }
}
