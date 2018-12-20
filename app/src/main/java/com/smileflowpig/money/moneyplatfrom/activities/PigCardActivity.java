package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.bean.CardBean;
import com.smileflowpig.money.factory.netword.NetRequestUtils;
import com.smileflowpig.money.moneyplatfrom.Adapter.CardAdapter;
import com.smileflowpig.money.moneyplatfrom.Adapter.PigCardAdapter;
import com.smileflowpig.money.moneyplatfrom.util.DisplayUtils2;
import com.smileflowpig.money.moneyplatfrom.util.DisplayUtils3;
import com.smileflowpig.money.moneyplatfrom.util.DisplayUtils4;
import com.smileflowpig.money.moneyplatfrom.util.DividerItemDecoration3;
import com.smileflowpig.money.moneyplatfrom.web.WebActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PigCardActivity extends PresenterActivity implements View.OnClickListener,OnRefreshLoadmoreListener {

    @BindView(R.id.pigcardrv)
    RecyclerView rv;
    @BindView(R.id.table_back)
    LinearLayout back;
    @BindView(R.id.caard_edit)
    TextView caard_edit;
    @BindView(R.id.pig_layout)
    LinearLayout piglayout;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshLayout;
    private int page=1;
    private List<CardBean.RstBean.ListBean> list2;
    private PigCardAdapter pigCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();
        list2 = new ArrayList<>();
        DividerItemDecoration3 decoration=new DividerItemDecoration3(this, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.item_shap));  //把样式放进去
        rv.addItemDecoration(decoration);
        rv.addItemDecoration(new DisplayUtils2.SpacesItemDecoration());
        //获取信用卡列表
        getdata();
    }
    public void initview(){
        back.setOnClickListener(this);
        caard_edit.setOnClickListener(this);
        refreshLayout.setOnRefreshLoadmoreListener(this).
                setEnableLoadmore(true)
                .setEnableRefresh(true);
    }
    public void getdata(){

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
                if(list2.size()>4){
                    piglayout.setBackgroundResource(R.mipmap.rectanglecopy);
                }
                if(pigCardAdapter==null){
                    pigCardAdapter = new PigCardAdapter(list2,PigCardActivity.this);
                    rv.setLayoutManager(new LinearLayoutManager(PigCardActivity.this));
                    rv.setAdapter(pigCardAdapter);
                }else {
                    pigCardAdapter.notifyDataSetChanged();
                }

                pigCardAdapter.setItemshap(new PigCardAdapter.getItemshap() {
                    @Override
                    public void success(int pos) {
                        Intent intent=new Intent(PigCardActivity.this,CardDetailActivity.class);
                        startActivity(intent);
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

    @Override
    protected boolean isNeedNotch() {
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_pig_card;
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
            case R.id.caard_edit:

                break;
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getdata();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshLayout.finishRefresh();
    }
}
