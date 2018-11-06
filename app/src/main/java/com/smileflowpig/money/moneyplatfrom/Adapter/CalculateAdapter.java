package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.factory.model.Calculate;


/**
 * @author: BigFaceCat
 * @function:
 * @date: 17/6/11
 */
public class CalculateAdapter extends BaseAdapter {
    /**
     * Common
     */


    private LayoutInflater mInflate;
    private Context mContext;
    private List<Calculate> mData;
    private ViewHolder mViewHolder;



    public CalculateAdapter(Context context, List<Calculate> data) {
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

        final Calculate value = (Calculate) getItem(position);
        //无tag时
        if (convertView == null) {

            mViewHolder = new ViewHolder();
            convertView = mInflate.inflate(R.layout.item_calculate_listview, parent, false);
            mViewHolder.stock_list_header_qi = (TextView) convertView.findViewById(R.id.stock_list_header_qi);
            mViewHolder.stock_list_header_price = (TextView) convertView.findViewById(R.id.stock_list_header_price);
            mViewHolder.stock_list_header_last_lixi = (TextView) convertView.findViewById(R.id.stock_list_header_last_lixi);
            mViewHolder.stock_list_header_price_change = (TextView) convertView.findViewById(R.id.stock_list_header_price_change);

            //为对应布局创建播放器
            convertView.setTag(mViewHolder);
        }

        //有tag时
        else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

       mViewHolder.stock_list_header_qi.setText(mData.get(position).numberofperiods);
       mViewHolder.stock_list_header_price.setText(mData.get(position).principalpayable);
       mViewHolder.stock_list_header_last_lixi.setText(mData.get(position).interestpayable);
       mViewHolder.stock_list_header_price_change.setText(mData.get(position).repaymentperinstallment);




        return convertView;
    }





    private static class ViewHolder {

        private TextView stock_list_header_qi;
        private TextView stock_list_header_price;
        private TextView stock_list_header_last_lixi;
        private TextView stock_list_header_price_change;

    }
}
