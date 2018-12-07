package com.smileflowpig.money.moneyplatfrom.weight;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smileflowpig.money.R;
import com.smileflowpig.money.moneyplatfrom.Adapter.CheckGuildAdapter;
import com.smileflowpig.money.moneyplatfrom.Bean.CheckGuidJson;

import java.util.ArrayList;
import java.util.List;

public class CheckGuildPop extends PopupWindow {

    private Context mContext;
    private RecyclerView recyclerView;
    private CheckGuidJson currentData;
    private boolean isTime = false;


    public CheckGuildPop(Context context) {
        super(context);
        initView(context);
    }

    public CheckGuildPop(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CheckGuildPop(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View conentView = inflater.inflate(R.layout.pop_checkinterval_layout, null);
        recyclerView = (RecyclerView) conentView.findViewById(R.id.pop_checkinterval_recyclerview);
        //获取popupwindow的高度与宽度
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        final ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
//         设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        this.setOutsideTouchable(true);
        conentView.findViewById(R.id.pop_checkinterval_bgview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        initAdapter();
        initData();
    }


    CheckGuildAdapter adapter;

    private void initAdapter() {
        adapter = new CheckGuildAdapter(R.layout.item_checkguild_layout);
        adapter.bindToRecyclerView(recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter a, View view, int position) {
                List<CheckGuidJson> data = adapter.getData();
                for (CheckGuidJson p : data) {
                    p.setSelect(false);
                }
                data.get(position).setSelect(true);
                currentData = data.get(position);
                adapter.notifyDataSetChanged();
                if (listener != null)
                    listener.onCheck(currentData, isTime);
                dismiss();

            }
        });

    }

    public void setTime(boolean time) {
        isTime = time;
        if (isTime) {
            adapter.setNewData(timeData);
        } else {
            adapter.setNewData(moneyData);
        }
    }

    private ArrayList<CheckGuidJson> moneyData;
    private ArrayList<CheckGuidJson> timeData;

    private void initData() {
        moneyData = new ArrayList<>();
        timeData = new ArrayList<>();
        moneyData.add(new CheckGuidJson("1-6个月", false));
        moneyData.add(new CheckGuidJson("6-12个月", false));
        moneyData.add(new CheckGuidJson("12-24个月", false));
        timeData.add(new CheckGuidJson("2千以下", false));
        timeData.add(new CheckGuidJson("2千-1万", false));
        timeData.add(new CheckGuidJson("1万-3万", false));
        timeData.add(new CheckGuidJson("3万以上", false));
        if (isTime) {
            adapter.setNewData(timeData);
        } else {
            adapter.setNewData(moneyData);
        }

    }

    OnCheckClickListener listener;

    public void setCheckListener(OnCheckClickListener mlis) {
        listener = mlis;
    }

    public interface OnCheckClickListener {

        void onCheck(CheckGuidJson checkGuidJson, boolean isTime);
    }
}
