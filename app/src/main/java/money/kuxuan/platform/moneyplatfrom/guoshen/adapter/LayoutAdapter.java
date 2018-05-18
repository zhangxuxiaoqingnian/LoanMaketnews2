/*
 * Copyright (C) 2014 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package money.kuxuan.platform.moneyplatfrom.guoshen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import money.kuxuan.platform.factory.model.api.guoshen.RepaymentListBean;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.guoshen.activity.AddActivity;

public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.SimpleViewHolder> {
    private static final int DEFAULT_ITEM_COUNT = 0;

    private final Context mContext;
    private final RecyclerView mRecyclerView;
    private List<RepaymentListBean> mItems;
    private int mCurrentItemId = 0;


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView tv_name;
        public final TextView tv_bqyhnum;
        public final TextView tv_hkrqnum;
        public final TextView tv_hkts;
        public final TextView tv_syqsnum;
        public final ImageView iv_edit;
        public SimpleViewHolder(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_bqyhnum = (TextView) view.findViewById(R.id.tv_bqyhnum);
            tv_hkrqnum = (TextView) view.findViewById(R.id.tv_hkrqnum);
            tv_hkts = (TextView) view.findViewById(R.id.tv_hkts);
            tv_syqsnum = (TextView) view.findViewById(R.id.tv_syqsnum);
            iv_edit = (ImageView) view.findViewById(R.id.iv_edit);
        }
    }

    public LayoutAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        this.mItems =new ArrayList<>();
        mRecyclerView = recyclerView;
    }


    public LayoutAdapter(Context context, RecyclerView recyclerView, List<RepaymentListBean> repaymentDetails) {
        mContext = context;
        this.mItems = repaymentDetails;
        mRecyclerView = recyclerView;
    }


    public void setNewData(List<RepaymentListBean> repaymentDetails){
        this.mItems = repaymentDetails;
        notifyDataSetChanged();
    }

    public void addItem(int position) {
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = LayoutInflater.from(mContext).inflate(R.layout.guoshen_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {

        if(mItems.get(position).getStatus()==2){
            holder.tv_hkts.setText("未开始");
        }else if(mItems.get(position).getStatus()==1){
            holder.tv_hkts.setText("已结清");
        }else if(mItems.get(position).getStatus()==0){
            holder.tv_hkts.setText("待还款");
        }


        holder.tv_name.setText(mItems.get(position).getPlatform()+"");
        holder.tv_bqyhnum.setText(mItems.get(position).getAmount()+"");

        String time = mItems.get(position).getFirst_time();

        String[] timeArr = time.split(" ");

        holder.tv_hkrqnum.setText(timeArr[0]);




        holder.iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddActivity.show(mContext,mItems.get(position).getId());
            }
        });

        holder.tv_syqsnum.setText(mItems.get(position).getSurplus()+"期");


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
