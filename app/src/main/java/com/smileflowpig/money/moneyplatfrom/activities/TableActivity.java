package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.smileflowpig.money.moneyplatfrom.Adapter.TabAdapter;
import com.smileflowpig.money.moneyplatfrom.util.DisplayUtils3;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.bean.TabBean;
import com.smileflowpig.money.factory.net.Network;
import com.smileflowpig.money.factory.netword.NetRequestUtils;

public class TableActivity extends PresenterActivity implements View.OnClickListener,OnRefreshLoadmoreListener {

    @BindView(R.id.table_back)
    ImageView back;
    @BindView(R.id.table_edit)
    TextView edit;
    @BindView(R.id.tab_title)
    TextView title;
    @BindView(R.id.table_rv)
    RecyclerView rv;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshLayout;

    private int tabid;
    private String channelId;
    private int page=1;
    private List<TabBean.RstBean.DataBean> list;
    private TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();
        list = new ArrayList<>();
        channelId = Network.channelId;
        Intent intent = getIntent();
        String tabtitle = intent.getStringExtra("tabtitle");
        tabid = intent.getIntExtra("tabid", -1);
        title.setText(tabtitle);
        //请求数据
        if(tabtitle.equals("信用卡")){
            //信用卡
            getcardlist();
        }else {
            //其他类型
            getdatalist();
        }

    }

    public void getcardlist(){

        Observable<Object> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getcardlist(1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

                System.out.println("成功了");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getdatalist(){

        Observable<TabBean> tabBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().gettablist(tabid, page, channelId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        tabBeanObservable.subscribe(new Observer<TabBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TabBean tabBean) {
                refreshLayout.finishLoadmore();
                refreshLayout.finishRefresh();
                if(tabBean.rst.pageinfo.hasNext){
                    page++;
                }else {
                    refreshLayout.setLoadmoreFinished(true);
                }
                final List<TabBean.RstBean.DataBean> data = tabBean.rst.data;
                list.addAll(data);
                if(tabAdapter==null){
                    tabAdapter = new TabAdapter(TableActivity.this,list);
                    rv.setLayoutManager(new LinearLayoutManager(TableActivity.this));
                    rv.setAdapter(tabAdapter);
                }else {
                    tabAdapter.notifyDataSetChanged();
                }


                tabAdapter.setItempostion(new TabAdapter.getItempostion() {
                    @Override
                    public void success(int pos) {
                        DetailActivity.show(TableActivity.this, list.get(pos).id+"","notice",0);

                    }
                });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void initview(){
        back.setOnClickListener(this);
        edit.setOnClickListener(this);
        //rv.addItemDecoration(new DisplayUtils3.SpacesItemDecoration());
        refreshLayout.setOnRefreshLoadmoreListener(this).
                setEnableLoadmore(true)
                .setEnableRefresh(true);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_table;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.table_back:
                finish();
                break;
            case R.id.table_edit:
                setResult(3);
                finish();
                break;
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getdatalist();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshLayout.finishRefresh();
    }
}
