package money.kuxuan.platform.moneyplatfrom.frags.main.state;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
import money.kuxuan.platform.factory.bean.MessageBean;
import money.kuxuan.platform.factory.netword.NetRequestUtils;
import money.kuxuan.platform.moneyplatfrom.Adapter.MessageAdapter;
import money.kuxuan.platform.moneyplatfrom.Adapter.MessageTwoAdapter;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.CaseurlActivity;
import money.kuxuan.platform.moneyplatfrom.util.DividerItemDecoration3;

/**
 * Created by 小狼 on 2018/11/4.
 */

public class TwoFragment extends PresenterFragment implements OnRefreshLoadmoreListener{

    @BindView(R.id.twomess_rv)
    RecyclerView rv;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshLayout;
    private int page=1;
    private MessageTwoAdapter messageAdapter;
    private List<MessageBean.RstBean.NewListBean> list;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list = new ArrayList<>();
        refreshLayout.setOnRefreshLoadmoreListener(this).
                setEnableLoadmore(true)
                .setEnableRefresh(true);
        DividerItemDecoration3 decoration=new DividerItemDecoration3(getActivity(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.item_shap));  //把样式放进去
        rv.addItemDecoration(decoration);
        getdata();
    }
    public void getdata(){

        Observable<MessageBean> messageBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().gettextlist("1", "1", page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        messageBeanObservable.subscribe(new Observer<MessageBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MessageBean messageBean) {

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
                    messageAdapter = new MessageTwoAdapter(getActivity(),list);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv.setAdapter(messageAdapter);
                }else {
                    messageAdapter.notifyDataSetChanged();
                }
                messageAdapter.setitemposition(new MessageTwoAdapter.getitemposition() {
                    @Override
                    public void success(int pos) {
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
        return R.layout.twomess_layout;
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
