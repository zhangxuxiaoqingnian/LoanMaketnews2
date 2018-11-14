package com.smileflowpig.money.moneyplatfrom.frags.main.state;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.smileflowpig.money.common.widget.EmptyView;
import com.smileflowpig.money.moneyplatfrom.Adapter.HuaDaiAdapter;
import com.smileflowpig.money.moneyplatfrom.Adapter.SerchAdapter;
import com.smileflowpig.money.moneyplatfrom.Adapter.Typeadapter;
import com.smileflowpig.money.moneyplatfrom.activities.DetailActivity;
import com.smileflowpig.money.moneyplatfrom.activities.TableActivity;
import com.smileflowpig.money.moneyplatfrom.util.DisplayUtils3;
import com.smileflowpig.money.moneyplatfrom.util.DisplayUtils4;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterFragment;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.bean.DaiBanner;
import com.smileflowpig.money.factory.netword.NetRequestUtils;
import com.smileflowpig.money.moneyplatfrom.util.WrapContentLinearLayoutManager;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by 小狼 on 2018/10/18.
 */

public class MoneyFragment extends PresenterFragment implements View.OnClickListener,OnRefreshLoadmoreListener {

    private View inflate;
    private TextView all;
    private TextView two;
    private TextView three;
    private TextView four;
    private TextView five;
    private RecyclerView rv;
    private RecyclerView moneyrv;
    private List<DaiBanner.RstBean.DataBean> list3;
    private HuaDaiAdapter huaDaiAdapter;
    private int page=1;
    private String type;
    private SmartRefreshLayout refreshlayout;
    private SerchAdapter serchAdapter;
    private PopupWindow popupWindow;
    private LinearLayout layout;

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.money_layout;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.money_layout, null);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initview();

        list3 = new ArrayList<>();
        getdata("不限");

        layout.post(new Runnable() {
            @Override
            public void run() {
                getpopwindow();
            }
        });
        final List<String> list=new ArrayList<>();
        list.add("不限");
        list.add("2千以下");
        list.add("2千-1万");
        list.add("1万-3万");
        list.add("3万以上");

        final List<String> list2=new ArrayList<>();
        list2.add("不限");
        list2.add("2000");
        list2.add("10000");
        list2.add("30000");
        list2.add("1000000");

        refreshlayout.setOnRefreshLoadmoreListener(this).
                setEnableLoadmore(true)
                .setEnableRefresh(true);
        final Typeadapter typeadapter=new Typeadapter(getActivity(),list);
        LinearLayoutManager ms=new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(ms);
        rv.setAdapter(typeadapter);

        typeadapter.SetNum(new Typeadapter.GetNum() {
            @Override
            public void success(int pos) {

                refreshlayout.setLoadmoreFinished(false);
                list3.clear();
                serchAdapter.notifyDataSetChanged();
                page=1;
                typeadapter.selectedItemPosition(pos);
                typeadapter.notifyDataSetChanged();
                //请求数据
                if(!getActivity().isFinishing()){
                    getpopwindow();
                }
                getdata(list2.get(pos));
                type=list2.get(pos);
                if(pos==0){
                    MobclickAgent.onEvent(getActivity(), "loanMoneyAll");
                }else if(pos==1){
                    MobclickAgent.onEvent(getActivity(), "loanMoney1");
                }else if(pos==2){
                    MobclickAgent.onEvent(getActivity(), "loanMoney2");
                }else if(pos==3){
                    MobclickAgent.onEvent(getActivity(), "loanMoney3");
                }else if(pos==4){
                    MobclickAgent.onEvent(getActivity(), "loanMoney4");
                }
            }
        });

    }

    public void getdata(String amout){
        Observable<DaiBanner> daiBannerObservable = new NetRequestUtils().bucuo().getbaseretrofit().getlistdata("1","不限",amout,"不限",12,page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        daiBannerObservable.subscribe(new Observer<DaiBanner>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DaiBanner daiBanner) {
                if(popupWindow!=null){
                    popupWindow.dismiss();
                }
                refreshlayout.finishLoadmore();
                refreshlayout.finishRefresh();
                final List<DaiBanner.RstBean.DataBean> data = daiBanner.rst.data;
                if(daiBanner.rst.pageinfo.hasNext){
                    page++;
                }else {
                    refreshlayout.setLoadmoreFinished(true);
                }
                list3.addAll(data);
                if(serchAdapter==null){
                    serchAdapter = new SerchAdapter(getActivity(),list3);
                    moneyrv.setLayoutManager(new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    moneyrv.setAdapter(serchAdapter);
                }else {
                    serchAdapter.notifyDataSetChanged();
                }

                serchAdapter.setItempostion(new SerchAdapter.getItempostion() {
                    @Override
                    public void success(int pos) {
                        DetailActivity.show(getActivity(), list3.get(pos).id+"","notice",0,8);
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
    public void onDestroy() {
        popupWindow.dismiss();
        super.onDestroy();

    }

    public void initview(){

        rv = (RecyclerView) inflate.findViewById(R.id.typeallrv);
        moneyrv = (RecyclerView) inflate.findViewById(R.id.money_rv);
        layout = (LinearLayout) inflate.findViewById(R.id.money_layout);
        //moneyrv.addItemDecoration(new DisplayUtils3.SpacesItemDecoration());
        rv.addItemDecoration(new DisplayUtils4.SpacesItemDecoration());
        refreshlayout = (SmartRefreshLayout) inflate.findViewById(R.id.refreshlayout);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {

        getdata(type);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh();
    }

    public void getpopwindow() {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.loadingtwo_layout, null, false);
        TextView loading = (TextView) inflate.findViewById(R.id.loadingtext);
        loading.setText("加载中...");
        popupWindow = new PopupWindow(inflate, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

    }
}
