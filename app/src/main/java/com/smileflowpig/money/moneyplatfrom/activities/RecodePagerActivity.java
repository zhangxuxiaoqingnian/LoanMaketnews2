package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.bean.MoneygetBean;
import com.smileflowpig.money.factory.bean.PagerBean;
import com.smileflowpig.money.factory.netword.NetRequestUtils;
import com.smileflowpig.money.moneyplatfrom.Adapter.RecodeAdapter;
import com.smileflowpig.money.moneyplatfrom.Adapter.RecodeTwoAdapter;
import com.smileflowpig.money.moneyplatfrom.util.DividerItemDecoration3;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RecodePagerActivity extends PresenterActivity implements OnRefreshLoadmoreListener {

    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.recode_rv)
    RecyclerView rv;
    @BindView(R.id.recode_money)
    TextView money;
    @BindView(R.id.tab_title)
    TextView title;
    @BindView(R.id.isfaust)
    RelativeLayout isfaust;
    @BindView(R.id.collnull)
    TextView collnull;
    @BindView(R.id.datanull)
    LinearLayout datanull;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private int page=1;
    private String cardname;
    private List<MoneygetBean.RstBean.DataBean> list;
    private List<PagerBean.RstBean.DataBean> list2;
    @Override
    protected boolean isNeedNotch() {
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        cardname = intent.getStringExtra("cardname");
        title.setText(cardname);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        refreshlayout.setOnRefreshLoadmoreListener(this).
                setEnableLoadmore(true)
                .setEnableRefresh(true);

        list = new ArrayList<>();
        list2 = new ArrayList<>();

        if(cardname.equals("红包记录")){
//            RecodeAdapter recodeAdapter=new RecodeAdapter(this, list,1);
//            rv.setLayoutManager(new LinearLayoutManager(this));
//            rv.setAdapter(recodeAdapter);
            getpager();
        }else if (cardname.equals("提现记录")){
            isfaust.setVisibility(View.GONE);
            getmoney();
        }else if(cardname.equals("消费记录")){
            isfaust.setVisibility(View.GONE);
//            RecodeAdapter recodeAdapter=new RecodeAdapter(this, list,3);
//            rv.setLayoutManager(new LinearLayoutManager(this));
//            rv.setAdapter(recodeAdapter);
        }


        DividerItemDecoration3 decoration=new DividerItemDecoration3(this, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.item_shap));  //把样式放进去
        rv.addItemDecoration(decoration);

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_recode_pager;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    public void getmoney(){

        Observable<MoneygetBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getmoneyone(page+"").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<MoneygetBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MoneygetBean o) {

                refreshlayout.finishLoadmore();
                refreshlayout.finishRefresh();
                if(o.rst.pageinfo.hasNext){
                    page++;
                }else {
                    refreshlayout.setLoadmoreFinished(true);
                }
                List<MoneygetBean.RstBean.DataBean> data = o.rst.data;
                if(data.size()==0){
                    datanull.setVisibility(View.GONE);
                    collnull.setVisibility(View.VISIBLE);
                }else {
                    list.addAll(data);
                    RecodeAdapter recodeAdapter=new RecodeAdapter(RecodePagerActivity.this,list,2);
                    rv.setLayoutManager(new LinearLayoutManager(RecodePagerActivity.this));
                    rv.setAdapter(recodeAdapter);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        if(cardname.equals("红包记录")){
            getpager();
        }else if(cardname.equals("提现记录")){
            getmoney();
        }

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh();
    }

    public void getpager(){

        Observable<PagerBean> pagerBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().getpager(1, 10).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        pagerBeanObservable.subscribe(new Observer<PagerBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PagerBean pagerBean) {

                refreshlayout.finishLoadmore();
                refreshlayout.finishRefresh();
                if(pagerBean.rst.pageinfo.hasNext){
                    page++;
                }else {
                    refreshlayout.setLoadmoreFinished(true);
                }
                List<PagerBean.RstBean.DataBean> data = pagerBean.rst.data;

                if(data.size()==0){
                    money.setText("0.00元");
                }else {
                    list2.addAll(data);
                    money.setText(data.get(0).total_money+"元");
                    RecodeTwoAdapter recodeTwoAdapter=new RecodeTwoAdapter(RecodePagerActivity.this,list2,1);
                    rv.setLayoutManager(new LinearLayoutManager(RecodePagerActivity.this));
                    rv.setAdapter(recodeTwoAdapter);
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
