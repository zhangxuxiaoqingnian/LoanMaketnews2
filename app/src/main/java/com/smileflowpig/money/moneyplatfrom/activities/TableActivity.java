package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.asm.base.android.ui.launcher.BaseApplication;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.smileflowpig.money.factory.bean.CardBean;
import com.smileflowpig.money.moneyplatfrom.Adapter.CardAdapter;
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
import com.smileflowpig.money.moneyplatfrom.web.WebActivity;
import com.umeng.analytics.MobclickAgent;

public class TableActivity extends PresenterActivity implements View.OnClickListener,OnRefreshLoadmoreListener {
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @BindView(R.id.table_back)
    LinearLayout back;
    @BindView(R.id.table_edit)
    TextView edit;
    @BindView(R.id.tab_title)
    TextView title;
    @BindView(R.id.table_rv)
    RecyclerView rv;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.table_layout)
    LinearLayout layout;

    private int tabid;
    private String channelId;
    private int page=1;
    private List<TabBean.RstBean.DataBean> list;
    private TabAdapter tabAdapter;
    private List<CardBean.RstBean.ListBean> list2;
    private CardAdapter cardAdapter;
    private String tabtitle;
    private PopupWindow popupWindow;
    @Override
    protected boolean isNeedNotch() {
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        channelId = Network.channelId;
        Intent intent = getIntent();
        tabtitle = intent.getStringExtra("tabtitle");
        tabid = intent.getIntExtra("tabid", -1);
        title.setText(tabtitle);
        //请求数据
        if(tabtitle.equals("信用卡")){
            //信用卡
            edit.setVisibility(View.GONE);
            rv.addItemDecoration(new DisplayUtils3.SpacesItemDecoration());
            getcardlist();
        }else {
            //其他类型
            getdatalist();
        }

    }

    public void getcardlist(){

        Observable<CardBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getcardlist(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<CardBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CardBean o) {
                refreshLayout.finishLoadmore();
                refreshLayout.finishRefresh();
                if(o.rst.pageInfo.hasNext){
                    page++;
                }else {
                    refreshLayout.setLoadmoreFinished(true);
                }

                List<CardBean.RstBean.ListBean> list = o.rst.list;
                list2.addAll(list);
                if(cardAdapter==null){
                    cardAdapter = new CardAdapter(TableActivity.this,list2);
                    rv.setLayoutManager(new LinearLayoutManager(TableActivity.this));
                    rv.setAdapter(cardAdapter);
                }else {
                    cardAdapter.notifyDataSetChanged();
                }

                cardAdapter.setItemposition(new CardAdapter.getItemposition() {
                    @Override
                    public void success(int pos) {
                        MobclickAgent.onEvent(TableActivity.this, "homeCreditCardApply");
                        WebActivity.show(TableActivity.this, list2.get(pos).name,
                                list2.get(pos).url);
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

                        if(tabtitle.equals("快借1500")){
                            DetailActivity.show(TableActivity.this, list.get(pos).id+"","notice",0,1);
                        }else if(tabtitle.equals("最新口子")){
                            DetailActivity.show(TableActivity.this, list.get(pos).id+"","notice",0,2);
                        }else if(tabtitle.equals("一定借到钱")){
                            DetailActivity.show(TableActivity.this, list.get(pos).id+"","notice",0,3);
                        }

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
        if(tabtitle.equals("信用卡")){
            getcardlist();
        }else {
            getdatalist();
        }

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshLayout.finishRefresh();
    }


    public void getpopwindow() {

        int width = getWindowManager().getDefaultDisplay().getWidth();
        View inflate = LayoutInflater.from(TableActivity.this).inflate(R.layout.loadingtwo_layout, null, false);
        TextView loading = (TextView) inflate.findViewById(R.id.loadingtext);
        loading.setText("加载中...");
        popupWindow = new PopupWindow(inflate, width / 3, width / 3);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
