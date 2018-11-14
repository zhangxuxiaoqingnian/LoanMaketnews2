package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.smileflowpig.money.moneyplatfrom.Adapter.DaiAdapter3;
import com.smileflowpig.money.moneyplatfrom.Bean.NoteEntity;
import com.smileflowpig.money.moneyplatfrom.sqlite.DatabaseAdapter;
import com.smileflowpig.money.moneyplatfrom.util.DisplayUtils3;
import com.smileflowpig.money.moneyplatfrom.web.WebActivity;

import java.util.ArrayList;

import butterknife.BindView;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.umeng.analytics.MobclickAgent;

public class RecordActivity extends PresenterActivity implements View.OnClickListener,OnRefreshLoadmoreListener {

    @BindView(R.id.record_back)
    LinearLayout back;
    @BindView(R.id.record_edit)
    TextView edit;
    @BindView(R.id.record_rv)
    RecyclerView rv;
    @BindView(R.id.ll_empty)
    LinearLayout ll_empty;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private int pageSize=5;
    private int pageNum=0;
    private DatabaseAdapter adapter;
    private ArrayList<NoteEntity> limit;
    private ArrayList<NoteEntity> limits=new ArrayList<>();
    private DaiAdapter3 daiAdapter;
    public static final String POSITION="POSITION";
    private boolean liulang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();

    }
    public void initview(){

        edit.setOnClickListener(this);
        back.setOnClickListener(this);
        refreshlayout.setOnRefreshLoadmoreListener(this).
                setEnableLoadmore(true)
                .setEnableRefresh(true);
        adapter = new DatabaseAdapter(this);
        limit = adapter.findLimit(pageSize, pageNum);
        if (limit!=null&&limit.size()>0){
            limits.addAll(limit);
            daiAdapter = new DaiAdapter3(this, limits);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.addItemDecoration(new DisplayUtils3.SpacesItemDecoration());
            rv.setAdapter(daiAdapter);

            daiAdapter.setItemposition(new DaiAdapter3.getItemposition() {
                @Override
                public void success(int pos) {
                    MobclickAgent.onEvent(RecordActivity.this,"mineBrowseList");
                    DetailActivity.show(RecordActivity.this, limits.get(pos).productid+"","notice",0,10);
                }

                @Override
                public void requst(int pos) {
                    if(liulang){
                        MobclickAgent.onEvent(RecordActivity.this, "mineBrowseApply");
                        WebActivity.show(RecordActivity.this, limits.get(pos).title,
                                limits.get(pos).url, limits.get(pos).productid, "", "0",false);
                    }else {
                        Intent intent=new Intent(RecordActivity.this, AccountActivity.class);
                        startActivity(intent);
                    }
                }
            });
            edit.setClickable(true);
        }else{
            edit.setClickable(false);
            showView();
        }

    }

    private void showView() {
        ll_empty.setVisibility(View.VISIBLE);
        refreshlayout.setVisibility(View.GONE);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_record;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.record_back:
                finish();
                break;
            case R.id.record_edit:
                pageNum=0;
                adapter.deleteAll();
                limits.clear();
                daiAdapter.notifyDataSetChanged();
                ArrayList<NoteEntity> all = adapter.findAll();
                Log.e("", "onClick: "+all.size());
                showView();
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("Deng", Context.MODE_PRIVATE);
        liulang = sp.getBoolean("liulang", false);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        pageNum+=5;
        limit = adapter.findLimit(pageSize, pageNum);
        if (limit.size()<=0){
            refreshlayout.setLoadmoreFinished(true);
        }else{
            refreshlayout.setLoadmoreFinished(false);
            limits.addAll(limit);
            daiAdapter.notifyDataSetChanged();
        }
        refreshlayout.finishLoadmore(1000);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        limits.clear();
        pageNum=0;
        limit = adapter.findLimit(pageSize, pageNum);
        limits.addAll(limit);
        daiAdapter.notifyDataSetChanged();
        refreshlayout.finishRefresh(1000);
    }

    public void gotoShopping(View view) {
       /* Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(POSITION,1);
        startActivity(intent);*/
       setResult(2);
       finish();
    }
}
