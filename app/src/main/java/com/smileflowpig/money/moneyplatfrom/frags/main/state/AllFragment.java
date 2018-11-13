package com.smileflowpig.money.moneyplatfrom.frags.main.state;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.smileflowpig.money.moneyplatfrom.Adapter.MessageAdapter;
import com.smileflowpig.money.moneyplatfrom.activities.CaseurlActivity;
import com.smileflowpig.money.moneyplatfrom.util.DividerItemDecoration3;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterFragment;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.bean.MessageBean;
import com.smileflowpig.money.factory.netword.NetRequestUtils;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by 小狼 on 2018/11/4.
 */

public class AllFragment extends PresenterFragment implements OnRefreshLoadmoreListener{

    @BindView(R.id.allmess_rv)
    RecyclerView allrv;

    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.all_layout)
    LinearLayout layout;

    private int page=1;
    private MessageAdapter messageAdapter;
    private List<MessageBean.RstBean.NewListBean> list;
    private PopupWindow popupWindow;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        refreshLayout.setOnRefreshLoadmoreListener(this).
                setEnableLoadmore(true)
                .setEnableRefresh(true);
        list = new ArrayList<>();
        DividerItemDecoration3 decoration=new DividerItemDecoration3(getActivity(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.item_shap));  //把样式放进去
        allrv.addItemDecoration(decoration);
        if(!getActivity().isFinishing()) {
            getpopwindow();
        }

        getdata();
    }
    public void getdata(){

        Observable<MessageBean> messageBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().gettextlist("1", "0", page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        messageBeanObservable.subscribe(new Observer<MessageBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MessageBean messageBean) {
                if(popupWindow!=null){
                    popupWindow.dismiss();
                }
                refreshLayout.finishLoadmore();
                refreshLayout.finishRefresh();
                if(messageBean.rst.get(0).pageinfo.hasNext){
                    page++;
                }else {
                    refreshLayout.setLoadmoreFinished(true);
                }
                List<MessageBean.RstBean.NewListBean> new_list = messageBean.rst.get(0).new_list;
                list.addAll(new_list);
                if(messageAdapter==null){
                    messageAdapter = new MessageAdapter(getActivity(),list);
                    allrv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    allrv.setAdapter(messageAdapter);
                }else {
                    messageAdapter.notifyDataSetChanged();
                }

                messageAdapter.setitemposition(new MessageAdapter.getItemposition() {
                    @Override
                    public void success(int pos) {
                        MobclickAgent.onEvent(getActivity(), "informAll");
                        Intent intent=new Intent(getActivity(), CaseurlActivity.class);
                        intent.putExtra("urlid",list.get(pos).id);
                        intent.putExtra("urlname",list.get(pos).view_num);
                        intent.putExtra("urladdress","");
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
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
       return R.layout.allmess_layout;
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getdata();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

        refreshLayout.finishRefresh();
    }

    public void getpopwindow() {
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.loadingtwo_layout, null, false);
        TextView loading = (TextView) inflate.findViewById(R.id.loadingtext);
        loading.setText("加载中...");
        popupWindow = new PopupWindow(inflate, width / 3, width / 3);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

    }
}
