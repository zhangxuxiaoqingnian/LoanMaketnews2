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
import com.smileflowpig.money.moneyplatfrom.Adapter.HuaDaiAdapter;
import com.smileflowpig.money.moneyplatfrom.Adapter.SerchAdapter;
import com.smileflowpig.money.moneyplatfrom.Adapter.Typeadapter;
import com.smileflowpig.money.moneyplatfrom.activities.DetailActivity;
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

public class IndentFragment extends PresenterFragment implements OnRefreshLoadmoreListener{

    private View inflate;
    private TextView all;
    private TextView two;
    private TextView three;
    private TextView four;
    private RecyclerView rv;
    private RecyclerView indentrv;
    private SmartRefreshLayout refreshlayout;
    private int page=1;
    private String type;
    private List<DaiBanner.RstBean.DataBean> list3;
    private HuaDaiAdapter huaDaiAdapter;
    private SerchAdapter serchAdapter;
    private PopupWindow popupWindow;
    private LinearLayout layout;

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.indent_layout;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.indent_layout, null);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initview();

        list3 = new ArrayList<>();
        layout.post(new Runnable() {
            @Override
            public void run() {
                getpopwindow();
            }
        });
        getdata("不限");
        List<String> list=new ArrayList<>();
        list.add("不限");
        list.add("上班族");
        list.add("个体户");
        list.add("企业主");

        final List<String> list2=new ArrayList<>();
        list2.add("不限");
        list2.add("上班族");
        list2.add("个体户");
        list2.add("学生党");


        final Typeadapter typeadapter=new Typeadapter(getActivity(),list);
        LinearLayoutManager ms=new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(ms);
        rv.setAdapter(typeadapter);

        typeadapter.SetNum(new Typeadapter.GetNum() {
            @Override
            public void success(int pos) {

                try {
                    refreshlayout.setLoadmoreFinished(false);
                    list3.clear();
                    if(serchAdapter!=null)
                    serchAdapter.notifyDataSetChanged();
                    page=1;
                    typeadapter.selectedItemPosition(pos);
                    typeadapter.notifyDataSetChanged();
                    if(!getActivity().isFinishing()){
                        getpopwindow();
                    }
                    getdata(list2.get(pos));
                    type=list2.get(pos);

                }catch (Exception e){

                }

                if(pos==0){
                    MobclickAgent.onEvent(getActivity(), "loanVocationAll");
                }else if(pos==1){
                    MobclickAgent.onEvent(getActivity(), "loanVocationWork");
                }else if(pos==2){
                    MobclickAgent.onEvent(getActivity(), "loanVocationSelf");
                }else if(pos==3){
                    MobclickAgent.onEvent(getActivity(), "loanVocationCompany");
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        popupWindow.dismiss();
        super.onDestroy();

    }

    public void getdata(String data){

        Observable<DaiBanner> daiBannerObservable = new NetRequestUtils().bucuo().getbaseretrofit().getlistdata("1","不限","不限",data,12,page).subscribeOn(Schedulers.io())
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
                refreshlayout.finishRefresh();
                refreshlayout.finishLoadmore();
                if(daiBanner.rst.pageinfo.hasNext){
                    page++;
                }else {
                    refreshlayout.setLoadmoreFinished(true);
                }
                final List<DaiBanner.RstBean.DataBean> data = daiBanner.rst.data;
                list3.addAll(data);
                if(serchAdapter==null){
                    serchAdapter = new SerchAdapter(getActivity(),list3);
                    indentrv.setLayoutManager(new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    indentrv.setAdapter(serchAdapter);
                }else {
                    serchAdapter.notifyDataSetChanged();
                }

                serchAdapter.setItempostion(new SerchAdapter.getItempostion() {
                    @Override
                    public void success(int pos) {
                        try{

                        DetailActivity.show(getActivity(), list3.get(pos).id+"","notice",0,8);
                        }catch (Exception E){

                        }
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void initview(){

        rv = (RecyclerView) inflate.findViewById(R.id.indentallrv);
        rv.addItemDecoration(new DisplayUtils4.SpacesItemDecoration());
        layout = (LinearLayout) inflate.findViewById(R.id.indent_layout);
        indentrv = (RecyclerView) inflate.findViewById(R.id.indentrv);
        //indentrv.addItemDecoration(new DisplayUtils3.SpacesItemDecoration());
        refreshlayout = (SmartRefreshLayout) inflate.findViewById(R.id.refreshlayout);
        refreshlayout.setOnRefreshLoadmoreListener(this).
                setEnableLoadmore(true)
                .setEnableRefresh(true);

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
