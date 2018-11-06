package com.smileflowpig.money.moneyplatfrom.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smileflowpig.money.moneyplatfrom.activities.InfoActivity;

import java.util.List;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.widget.PortraitView;
import com.smileflowpig.money.factory.model.db.DataE;


/**
 * @author: BigFaceCat
 * @function:
 * @date: 17/6/11
 */
//// TODO: 2017/8/25 0025  有机会一定把你优化掉
public class ExaimeAdapter extends BaseAdapter {
    /**
     * Common
     */


    private LayoutInflater mInflate;
    private Context mContext;
    private List<DataE> mData;
    private ViewHolder mViewHolder;


    public ExaimeAdapter(Context context, List<DataE> data) {
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

        final DataE value = (DataE) getItem(position);
        //无tag时
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = mInflate.inflate(R.layout.cell_exaimne_layout, parent, false);

            mViewHolder.im_portrait = (PortraitView) convertView.findViewById(R.id.im_message);
            mViewHolder.title = (TextView) convertView.findViewById(R.id.title);
            mViewHolder.subtitle = (TextView) convertView.findViewById(R.id.subtitle);
            mViewHolder.num =(TextView)  convertView.findViewById(R.id.num);
//            mViewHolder.time =(TextView)  convertView.findViewById(R.id.time);
            mViewHolder.source = (TextView) convertView.findViewById(R.id.subtitle);
            mViewHolder.mLayout = (LinearLayout) convertView.findViewById(R.id.mlayout);
            convertView.setTag(mViewHolder);
        }
        //有tag时
        else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
        //填充item的数据
        mViewHolder.title.setText(mData.get(position).getTitle());
        mViewHolder.subtitle.setText(mData.get(position).getSource());
        mViewHolder.num.setText(mData.get(position).getPage_views());

        mViewHolder.im_portrait.setup(Glide.with(mContext),mData.get(position).getThumbnail());

        mViewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InfoActivity.show(mContext,mData.get(position).getId());
            }
        });
        return convertView;
    }


    private static class ViewHolder {


        //产品图片
        private PortraitView im_portrait;

        private TextView title;

        private TextView subtitle;

        private  TextView num;

//        private TextView time;

        private TextView source;

        //产品布局
        private LinearLayout mLayout;
    }
}
