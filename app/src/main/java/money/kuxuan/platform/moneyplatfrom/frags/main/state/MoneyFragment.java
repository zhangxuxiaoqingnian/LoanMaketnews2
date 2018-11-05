package money.kuxuan.platform.moneyplatfrom.frags.main.state;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import money.kuxuan.platform.common.app.PresenterFragment;
import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.factory.bean.DaiBanner;
import money.kuxuan.platform.factory.netword.NetRequestUtils;
import money.kuxuan.platform.moneyplatfrom.Adapter.HuaAdapter;
import money.kuxuan.platform.moneyplatfrom.Adapter.HuaDaiAdapter;
import money.kuxuan.platform.moneyplatfrom.Adapter.SerchAdapter;
import money.kuxuan.platform.moneyplatfrom.Adapter.Typeadapter;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.DetailActivity;
import money.kuxuan.platform.moneyplatfrom.activities.NewDetailActivity;
import money.kuxuan.platform.moneyplatfrom.util.DisplayUtils3;
import money.kuxuan.platform.moneyplatfrom.util.DisplayUtils4;
import money.kuxuan.platform.moneyplatfrom.util.DividerItemDecoration2;

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
                page=1;
                typeadapter.selectedItemPosition(pos);
                typeadapter.notifyDataSetChanged();
                //请求数据
                getdata(list2.get(pos));
                type=list2.get(pos);
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
                    moneyrv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    moneyrv.setAdapter(serchAdapter);
                }else {
                    serchAdapter.notifyDataSetChanged();
                }

                serchAdapter.setItempostion(new SerchAdapter.getItempostion() {
                    @Override
                    public void success(int pos) {
                        DetailActivity.show(getActivity(), list3.get(pos).id+"","notice",0);
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

        rv = (RecyclerView) inflate.findViewById(R.id.typeallrv);
        moneyrv = (RecyclerView) inflate.findViewById(R.id.money_rv);
        moneyrv.addItemDecoration(new DisplayUtils3.SpacesItemDecoration());
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
}
