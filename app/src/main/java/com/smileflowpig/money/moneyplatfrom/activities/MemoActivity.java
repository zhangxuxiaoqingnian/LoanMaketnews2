package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.smileflowpig.money.moneyplatfrom.Adapter.MemoAdapter;
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
import com.smileflowpig.money.common.widget.SelfDialog;
import com.smileflowpig.money.factory.bean.MemoBean;
import com.smileflowpig.money.factory.netword.NetRequestUtils;
import com.umeng.analytics.MobclickAgent;

public class MemoActivity extends PresenterActivity implements View.OnClickListener,OnRefreshLoadmoreListener{

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @BindView(R.id.memo_back)
    ImageView back;
    @BindView(R.id.memo_add)
    ImageView add;
    @BindView(R.id.memo_rv)
    RecyclerView rv;
    @BindView(R.id.memo_null)
    LinearLayout memonull;
    @BindView(R.id.gotoadd)
    TextView toadd;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    private int page=1;
    private MemoAdapter memoAdapter;
    private List<MemoBean.RstBean.DataBean> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();

        list2 = new ArrayList<>();
        rv.addItemDecoration(new DisplayUtils3.SpacesItemDecoration());


    }
    @Override
    protected boolean isNeedNotch() {
        return true;
    }
    public void getdata(){
        Observable<MemoBean> memoBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().getmemolist(page+"").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        memoBeanObservable.subscribe(new Observer<MemoBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MemoBean memoBean) {

                refreshlayout.finishRefresh();
                refreshlayout.finishLoadmore();
                final List<MemoBean.RstBean.DataBean> data = memoBean.rst.data;
                if(memoBean.err.equals("数据为空")){
                    memonull.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.GONE);
                }else {
                    rv.setVisibility(View.VISIBLE);
                    memonull.setVisibility(View.GONE);
                    if(memoBean.rst.pageinfo.hasNext){
                        page++;
                    }else {
                        refreshlayout.setLoadmoreFinished(true);
                    }
                    list2.addAll(data);
                    if(memoAdapter==null){
                        memoAdapter = new MemoAdapter(MemoActivity.this,list2);
                        rv.setLayoutManager(new LinearLayoutManager(MemoActivity.this));
                        rv.setAdapter(memoAdapter);
                    }else {
                        memoAdapter.notifyDataSetChanged();
                    }


                    memoAdapter.Setnum(new MemoAdapter.Getnum() {
                        @Override
                        public void success(int pos) {
                            //条目点击
                            Intent intent=new Intent(MemoActivity.this, MemoTwoActivity.class);
                            intent.putExtra("beiid",list2.get(pos).id);
                            startActivity(intent);
                        }

                        @Override
                        public void zipos(final int pos) {
                            //删除点击
                            final SelfDialog selfDialog=new SelfDialog(MemoActivity.this);
                            selfDialog.setTitle("提示");
                            selfDialog.setMessage(R.string.beiwang);
                            selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
                                @Override
                                public void onYesClick() {
                                    //进行删除操作
                                    selfDialog.dismiss();
                                    String id = list2.get(pos).id;
                                    getdetele(id);
                                }
                            });
                            selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                                @Override
                                public void onNoClick() {
                                    selfDialog.dismiss();
                                }
                            });
                            selfDialog.show();
                        }
                    });
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

    public void getdetele(String arr){
        Observable<Object> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getmemodetele(arr).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

                Toast.makeText(MemoActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                getdata();
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
        add.setOnClickListener(this);
        toadd.setOnClickListener(this);
        refreshlayout.setOnRefreshLoadmoreListener(this).
                setEnableLoadmore(true)
                .setEnableRefresh(true);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_memo;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(MemoActivity.this,MemoTwoActivity.class);
        switch (v.getId()){
            case R.id.memo_back:
                finish();
                break;
            case R.id.memo_add:
                intent.putExtra("beiid","");
                startActivity(intent);
                break;
            case R.id.gotoadd:
                intent.putExtra("beiid","");
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        page=1;
        list2.clear();
        getdata();
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {

        getdata();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

        refreshlayout.finishRefresh();

    }
}
