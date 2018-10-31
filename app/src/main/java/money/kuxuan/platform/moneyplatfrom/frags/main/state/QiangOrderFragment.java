package money.kuxuan.platform.moneyplatfrom.frags.main.state;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import money.kuxuan.platform.common.app.PresenterFragment;
import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.factory.bean.AllMyBean;
import money.kuxuan.platform.factory.netword.NetRequestUtils;
import money.kuxuan.platform.moneyplatfrom.Adapter.MyAllAdapter;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.AccountActivity;
import money.kuxuan.platform.moneyplatfrom.activities.MyHomeActivity;
import money.kuxuan.platform.moneyplatfrom.util.DisplayUtils3;

/**
 * Created by 小狼 on 2018/10/10.
 */

public class QiangOrderFragment extends PresenterFragment {

    private View inflate;
    private RecyclerView rv;
    private TextView qiangnull;
    private Button qiang;
    private LinearLayout login;
    private SmartRefreshLayout refreshlayout;
    private int pager=1;
    private boolean istrue=false;
    private List<AllMyBean.RstBean.DataBean> list2;
    private MyAllAdapter myAllAdapter;
    private PopupWindow popupWindow;
    private RelativeLayout layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.allqiang_layout, container, false);
        return inflate;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.allqiang_layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rv = (RecyclerView) inflate.findViewById(R.id.qiangrv);
        qiangnull = (TextView) inflate.findViewById(R.id.qiangnull);
        login = (LinearLayout) inflate.findViewById(R.id.qianglogin);
        qiang = (Button) inflate.findViewById(R.id.qianggo);
        refreshlayout = (SmartRefreshLayout) inflate.findViewById(R.id.qiangrefreshlayout);
        rv.addItemDecoration(new DisplayUtils3.SpacesItemDecoration());
        layout = (RelativeLayout) inflate.findViewById(R.id.qianglayout);

        getpopwindow();
        qiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountActivity.class);
                startActivityForResult(intent, money.kuxuan.platform.moneyplatfrom.Constant.Code.REQUEST_CODE);
            }
        });

        refreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                refreshlayout.finishRefresh();
                refreshlayout.finishLoadmore();


            }
        });
        refreshlayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                if(istrue){
                    getdata(2);
                }else {
                    Toast.makeText(getActivity(),"没有更多啦",Toast.LENGTH_SHORT).show();
                    refreshlayout.finishRefresh();
                    refreshlayout.finishLoadmore();
                }


            }
        });

    }

    public void getdata(int id){

        Observable<AllMyBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getmylist(id,pager).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<AllMyBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AllMyBean o) {
                popupWindow.dismiss();
                refreshlayout.finishRefresh();
                refreshlayout.finishLoadmore();
                if(o!=null){
                    if(o.err.equals("没有数据")){
                        rv.setVisibility(View.GONE);
                        qiangnull.setVisibility(View.VISIBLE);
                    }else {
                        boolean hasNext = o.rst.pageInfo.hasNext;
                        if(hasNext){
                            pager++;
                            istrue=true;
                        }else {
                            istrue=false;
                        }
                        final List<AllMyBean.RstBean.DataBean> data = o.rst.data;
                        if (data != null) {
                            list2.addAll(data);
                            if(myAllAdapter==null){
                                myAllAdapter = new MyAllAdapter(getActivity(), list2);
                                rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                                rv.setAdapter(myAllAdapter);
                            }else {
                                myAllAdapter.notifyDataSetChanged();
                            }


                            myAllAdapter.Setnum(new MyAllAdapter.Getnum() {
                                @Override
                                public void getpost(int pos) {

                                    int id1 = list2.get(pos).id;
                                    Intent intent = new Intent(getActivity(), MyHomeActivity.class);
                                    intent.putExtra("itemid", id1);
                                    startActivity(intent);
                                }
                            });
                        }

                    }
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
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sp = getActivity().getSharedPreferences("Deng", Context.MODE_PRIVATE);
        boolean liulang = sp.getBoolean("liulang", false);
        if (liulang) {
            rv.setVisibility(View.VISIBLE);
            qiangnull.setVisibility(View.GONE);
            login.setVisibility(View.GONE);

            list2 = new ArrayList<>();
            pager=1;
            istrue=false;
            myAllAdapter=null;
            //请求数据
            getdata(2);

        } else {
            rv.setVisibility(View.GONE);
            qiangnull.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);
             popupWindow.dismiss();
        }
    }

    public void getpopwindow(){
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.loading_layout, null, false);
        popupWindow = new PopupWindow(inflate, width/3,width/3);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(layout, Gravity.CENTER,0,0);
    }
}
