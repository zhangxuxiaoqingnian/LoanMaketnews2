package com.smileflowpig.money.moneyplatfrom.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.bean.PushBean;
import com.smileflowpig.money.factory.netword.NetRequestUtils;
import com.smileflowpig.money.factory.netword.NetRequestUtils2;
import com.smileflowpig.money.moneyplatfrom.Adapter.MessAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MessContextActivity extends PresenterActivity implements View.OnClickListener{

    @BindView(R.id.mess_rv)
    RecyclerView rv;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.nullmess)
    TextView nulltv;
    private List<PushBean.ResBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();
        list = new ArrayList<>();
        //请求数据
        getdata();

    }
    public void getdata(){

        Observable<PushBean> pushBeanObservable = new NetRequestUtils2().bucuo().getbaseretrofit().getpushmess(3).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        pushBeanObservable.subscribe(new Observer<PushBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }
            @Override
            public void onNext(PushBean pushBean) {
                if(pushBean.message.equals("成功")){
                    if(pushBean.res.size()>0){
                        List<PushBean.ResBean> res = pushBean.res;
                        for (int i = 0; i < res.size(); i++) {
                            if(res.get(i).xhz_type!=1){
                                list.add(res.get(i));
                            }
                        }
                        MessAdapter messAdapter=new MessAdapter(MessContextActivity.this,list);
                        rv.setLayoutManager(new LinearLayoutManager(MessContextActivity.this));
                        rv.setAdapter(messAdapter);
                    }else {
                        nulltv.setVisibility(View.VISIBLE);
                        rv.setVisibility(View.GONE);
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
    public void initview(){
        back.setOnClickListener(this);
    }

    @Override
    protected boolean isNeedNotch() {
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_mess_context;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
