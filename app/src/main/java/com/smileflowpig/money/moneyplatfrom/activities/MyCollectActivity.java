package com.smileflowpig.money.moneyplatfrom.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.smileflowpig.money.moneyplatfrom.Adapter.DaiAdapter;
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
import com.smileflowpig.money.factory.bean.CancleCollectBean;
import com.smileflowpig.money.factory.bean.CollectListBean;
import com.smileflowpig.money.factory.netword.NetRequestUtils;
import com.umeng.analytics.MobclickAgent;

public class MyCollectActivity extends PresenterActivity implements View.OnClickListener,OnRefreshLoadmoreListener{

    @BindView(R.id.collect_back)
    ImageView back;
    @BindView(R.id.collect_edit)
    TextView edit;
    @BindView(R.id.collect_checkall)
    LinearLayout checkall;
    @BindView(R.id.collect_delete)
    TextView delete;
    @BindView(R.id.collectrv)
    RecyclerView rv;
    @BindView(R.id.collect_bottomtitel)
    LinearLayout bottom;
    @BindView(R.id.collect_checkbox)
    CheckBox checkBox;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    @BindView(R.id.collect_goto)
    TextView gotosele;

    @BindView(R.id.collect_null)
    LinearLayout layoutnull;

    private boolean istrue=true;
    private DaiAdapter daiAdapter;
    private List<String> list;
    private int comment=-1;
    private int sum=0;
    private boolean isdelete=false;
    private List<CollectListBean.RstBean.DataBean> data;
    private int page=1;
    private List<CollectListBean.RstBean.DataBean> list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();

        list2 = new ArrayList<>();

        getdata();


        rv.addItemDecoration(new DisplayUtils3.SpacesItemDecoration());


    }

    public void getdata(){

        Observable<CollectListBean> collectListBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().getcollectlist(page,10).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        collectListBeanObservable.subscribe(new Observer<CollectListBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CollectListBean collectListBean) {

                refreshlayout.finishLoadmore();
                refreshlayout.finishRefresh();
                data = collectListBean.rst.data;
                if(data.size()<=0){
                    layoutnull.setVisibility(View.VISIBLE);
                    edit.setVisibility(View.GONE);
                }else {
                    layoutnull.setVisibility(View.GONE);
                    edit.setVisibility(View.VISIBLE);
                    if(collectListBean.rst.pageinfo.hasNext){
                        page++;
                    }else {
                        refreshlayout.setLoadmoreFinished(true);
                    }
                    list2.addAll(data);
                    if(daiAdapter==null){
                        daiAdapter = new DaiAdapter(MyCollectActivity.this, list2);
                        rv.setLayoutManager(new LinearLayoutManager(MyCollectActivity.this));
                        rv.setAdapter(daiAdapter);
                    }else {

                        daiAdapter.notifyDataSetChanged();
                    }


                    daiAdapter.setItemposition(new DaiAdapter.getItemposition() {
                        @Override
                        public void success(int pos) {


                            if(!istrue){
                                if(pos==comment){
                                    list2.get(pos).setIscont(false);
                                    comment=-1;
                                }else {
                                    list2.get(pos).setIscont(true);
                                    comment=pos;
                                }
                                daiAdapter.notifyDataSetChanged();
                                sum=0;
                                for (int i = 0; i < list2.size(); i++) {
                                    if(list2.get(i).iscont){
                                        sum++;
                                    }
                                }
                                if(sum== list2.size()){
                                    checkBox.setChecked(true);
                                }else {
                                    checkBox.setChecked(false);
                                }
                            }else {
                                MobclickAgent.onEvent(MyCollectActivity.this,"mineCollectInto");
                                DetailActivity.show(MyCollectActivity.this, list2.get(pos).id+"","notice",0,9);

                            }
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
    public void initview(){
        back.setOnClickListener(this);
        edit.setOnClickListener(this);
        delete.setOnClickListener(this);
        checkall.setOnClickListener(this);
        gotosele.setOnClickListener(this);
        refreshlayout.setOnRefreshLoadmoreListener(this).
                setEnableLoadmore(true)
                .setEnableRefresh(true);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_collect;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.collect_back:
                finish();
                break;
            case R.id.collect_edit:
                istrue=!istrue;
                if(istrue){
                    bottom.setVisibility(View.GONE);
                    edit.setText("编辑");
                    daiAdapter.setShow(false);
                    for (int i = 0; i < list2.size(); i++) {
                        list2.get(i).setIscont(false);
                    }
                    sum=0;
                    checkBox.setChecked(false);

                }else {
                    daiAdapter.setShow(true);
                    bottom.setVisibility(View.VISIBLE);
                    edit.setText("取消");
                }
                break;
            case R.id.collect_checkall:
                if(checkBox.isChecked()){
                    checkBox.setChecked(false);
                    for (int i = 0; i < list2.size(); i++) {
                        list2.get(i).setIscont(false);
                    }
                    daiAdapter.notifyDataSetChanged();
                }else {
                    checkBox.setChecked(true);
                    for (int i = 0; i < list2.size(); i++) {
                        list2.get(i).setIscont(true);
                    }
                    daiAdapter.notifyDataSetChanged();

                }
                break;
            case R.id.collect_delete:
                isdelete=false;
                String[] str=new String[]{};
                final List<String> list3=new ArrayList<>();
                for (int i = 0; i < list2.size(); i++) {
                    if(list2.get(i).iscont){
                        isdelete=true;
                        //把当前id添加到数组中
                        list3.add(list2.get(i).id+"");
                    }
                }
                if(isdelete){

                    final SelfDialog selfDialog=new SelfDialog(MyCollectActivity.this);
                    selfDialog.setTitle("提示");
                    selfDialog.setMessage(R.string.mycollect);
                    selfDialog.setYesOnclickListener("删除", new SelfDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            //进行删除
                            getdetele(list3);
                            selfDialog.dismiss();

                        }
                    });
                    selfDialog.setNoOnclickListener("我再想想", new SelfDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            selfDialog.dismiss();
                        }
                    });
                    selfDialog.show();

                }else {
                    Toast.makeText(MyCollectActivity.this,"请先选择要删除的内容",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.collect_goto:
                setResult(3);
                finish();
                break;
        }
    }
    public void getdetele(List<String> arr){

        Observable<CancleCollectBean> collectBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().getnocollect(arr).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        collectBeanObservable.subscribe(new Observer<CancleCollectBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CancleCollectBean collectBean) {
                Toast.makeText(MyCollectActivity.this,"删除成功",Toast.LENGTH_SHORT).show();

                daiAdapter.setShow(false);
                for (int i = 0; i < list2.size(); i++) {
                    list2.get(i).setIscont(false);
                }
                sum=0;
                checkBox.setChecked(false);
                list2.clear();
                daiAdapter=null;
                refreshlayout.setLoadmoreFinished(false);
                page=1;
                bottom.setVisibility(View.GONE);
                edit.setText("编辑");
                istrue=false;
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

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getdata();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

        refreshlayout.finishRefresh();
    }
}
