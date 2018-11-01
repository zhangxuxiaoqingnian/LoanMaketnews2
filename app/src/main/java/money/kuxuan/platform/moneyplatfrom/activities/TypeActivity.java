package money.kuxuan.platform.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.factory.bean.HomedataBean;
import money.kuxuan.platform.factory.netword.NetRequestUtils;
import money.kuxuan.platform.moneyplatfrom.Adapter.DaiAdapter4;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.util.DisplayUtils3;
import money.kuxuan.platform.moneyplatfrom.web.WebActivity;


public class TypeActivity extends PresenterActivity implements View.OnClickListener,OnRefreshLoadmoreListener{

    @BindView(R.id.type_back)
    ImageView back;
    @BindView(R.id.typename)
    TextView typename;
    @BindView(R.id.typedata_rv)
    RecyclerView rv;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshLayout;
    private int typeid;
    private int typechid;
    private int page=1;
    private List<HomedataBean.RstBean.DataBean> list;
    private DaiAdapter4 daiAdapter4;
    private boolean liulang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();

        Intent intent = getIntent();
        String name = intent.getStringExtra("typename");
        typeid = intent.getIntExtra("typeid", -1);
        typechid = intent.getIntExtra("typechid", -1);
        typename.setText(name);
        list = new ArrayList<>();


        getdata();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences("Deng", Context.MODE_PRIVATE);
        liulang = sp.getBoolean("liulang", false);
    }

    public void initview(){
        back.setOnClickListener(this);
        refreshLayout.setOnRefreshLoadmoreListener(this).
                setEnableLoadmore(true)
                .setEnableRefresh(true);
        rv.addItemDecoration(new DisplayUtils3.SpacesItemDecoration());
    }
    public void getdata(){

        Observable<HomedataBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().gethomedata(typeid, typechid,10,page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<HomedataBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomedataBean homedataBean) {

                refreshLayout.finishLoadmore();
                refreshLayout.finishRefresh();
                if(homedataBean.rst.pageinfo.hasNext){
                    page++;
                }else {
                    refreshLayout.setLoadmoreFinished(true);
                }
                List<HomedataBean.RstBean.DataBean> data = homedataBean.rst.data;
                list.addAll(data);
                if(daiAdapter4==null){
                    daiAdapter4 = new DaiAdapter4(TypeActivity.this,list);
                    rv.setLayoutManager(new LinearLayoutManager(TypeActivity.this));
                    rv.setAdapter(daiAdapter4);
                }else {
                    daiAdapter4.notifyDataSetChanged();
                }


                daiAdapter4.setItemposition(new DaiAdapter4.getItemposition() {
                    @Override
                    public void success(int pos) {

                        DetailActivity.show(TypeActivity.this, list.get(pos).id+"","notice",0);

                    }

                    @Override
                    public void request(int pos) {
                        if(liulang){
                            WebActivity.show(TypeActivity.this, list.get(pos).name,
                                    list.get(pos).link, list.get(pos).id, "", "0",false);
                        }else {
                            Intent intent=new Intent(TypeActivity.this, AccountActivity.class);
                            startActivity(intent);
                        }
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
    protected int getContentLayoutId() {
        return R.layout.activity_type;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.type_back:
                finish();
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
