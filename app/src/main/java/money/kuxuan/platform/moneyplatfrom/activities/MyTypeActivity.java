package money.kuxuan.platform.moneyplatfrom.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.factory.bean.MyHomeListBean;
import money.kuxuan.platform.factory.netword.NetRequestUtils;
import money.kuxuan.platform.moneyplatfrom.Adapter.MyHomeAdapter;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.util.DisplayUtils3;

public class MyTypeActivity extends PresenterActivity implements View.OnClickListener{

    private TextView tv_title;
    private ImageView iv_add;
    private ImageView iv_mine;
    private RecyclerView rv;
    private LinearLayout timeup;
    private LinearLayout moneyup;
    private boolean money=false;
    private boolean time=false;
    private TextView timetext;
    private TextView moneytext;
    private int typeid;
    private TextView cang;
    private SmartRefreshLayout refreshlayout;
    private int pager=1;
    private boolean istrue=false;
    private List<MyHomeListBean.RstBean.DataBean> list2;
    private MyHomeAdapter myHomeAdapter;
    private LinearLayout layout;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initview();

        iv_mine.setImageResource(R.drawable.guoshen_back);
        iv_add.setVisibility(View.GONE);

        iv_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String overtype = getIntent().getStringExtra("overtype");
        typeid = getIntent().getIntExtra("typeid", -1);
        tv_title.setText(overtype);

        moneyup.setOnClickListener(this);
        timeup.setOnClickListener(this);
        list2 = new ArrayList<>();
        //请求数据  默认金额升序
        getdata(typeid,1,"amount_up");

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
                    getdata(typeid,pager,"amount_up");
                }else {
                    Toast.makeText(MyTypeActivity.this,"没有更多啦",Toast.LENGTH_SHORT).show();
                    refreshlayout.finishRefresh();
                    refreshlayout.finishLoadmore();
                }
            }
        });



    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        getpopwindow();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        popupWindow.dismiss();
    }

    public void getdata(int type, int page, String rank){

        Observable<MyHomeListBean> myHomeListBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().gettypelist(type, page, rank).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        myHomeListBeanObservable.subscribe(new Observer<MyHomeListBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MyHomeListBean myHomeListBean) {

                popupWindow.dismiss();
                refreshlayout.finishRefresh();
                refreshlayout.finishLoadmore();
                if(myHomeListBean!=null){

                    boolean hasNext = myHomeListBean.rst.pageinfo.hasNext;
                    if(hasNext){
                        pager++;
                        istrue=true;
                    }else {
                        istrue=false;
                    }

                final List<MyHomeListBean.RstBean.DataBean> data = myHomeListBean.rst.data;
                if(data!=null&&data.size()>0) {
                    list2.addAll(data);
                    if(myHomeAdapter==null){
                        myHomeAdapter = new MyHomeAdapter(MyTypeActivity.this, data,"foot");
                        rv.setLayoutManager(new LinearLayoutManager(MyTypeActivity.this));
                        rv.setAdapter(myHomeAdapter);
                    }else {
                        myHomeAdapter.notifyDataSetChanged();
                    }


                    myHomeAdapter.Setnum(new MyHomeAdapter.Getnum() {
                        @Override
                        public void onclice(int pos) {

                            int id = data.get(pos).id;
                            Intent intent = new Intent(MyTypeActivity.this, MyHomeActivity.class);
                            intent.putExtra("itemid", id);
                            startActivity(intent);

                        }
                    });
                }else {

                    rv.setVisibility(View.GONE);
                    cang.setVisibility(View.VISIBLE);

                }}
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

        iv_mine = (ImageView) findViewById(R.id.iv_mine);
        iv_add = (ImageView) findViewById(R.id.iv_add);
        tv_title = (TextView) findViewById(R.id.tv_title);
        rv = (RecyclerView) findViewById(R.id.typerv);
        rv.addItemDecoration(new DisplayUtils3.SpacesItemDecoration());
        moneyup = (LinearLayout) findViewById(R.id.moneyup);
        timeup = (LinearLayout) findViewById(R.id.timeup);
        moneytext = (TextView) findViewById(R.id.moneytext);
        timetext = (TextView) findViewById(R.id.timetext);
        cang = (TextView) findViewById(R.id.typecang);
        refreshlayout = (SmartRefreshLayout) findViewById(R.id.refreshlayout);
        layout = (LinearLayout) findViewById(R.id.typelayout);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_my_type;
    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.moneyup:
                time=false;
                timetext.setTextColor(Color.parseColor("#666666"));
                pager=1;
                if(money){
                    moneytext.setTextColor(Color.parseColor("#666666"));
                    money=false;
                    myHomeAdapter=null;
                    //金额升序
                    getpopwindow();
                    getdata(typeid,pager,"amount_up");
                }else {
                    moneytext.setTextColor(Color.parseColor("#4935F0"));
                    money=true;
                    //金额降序
                    getpopwindow();
                    myHomeAdapter=null;
                    getdata(typeid,pager,"amount_down");
                }
                break;
            case R.id.timeup:
                money=false;
                moneytext.setTextColor(Color.parseColor("#666666"));
                pager=1;
                if(time){
                    timetext.setTextColor(Color.parseColor("#666666"));
                    time=false;
                    //时间升序
                    getpopwindow();
                    myHomeAdapter=null;
                    getdata(typeid,pager,"time_up");
                }else {
                    timetext.setTextColor(Color.parseColor("#4935F0"));
                    time=true;
                    //时间降序
                    getpopwindow();
                    myHomeAdapter=null;
                    getdata(typeid,pager,"time_down");
                }
                break;
        }
    }

    public void getpopwindow(){
        int width = getWindowManager().getDefaultDisplay().getWidth();
        View inflate = LayoutInflater.from(MyTypeActivity.this).inflate(R.layout.loading_layout, null, false);
        popupWindow = new PopupWindow(inflate, width/3,width/3);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(layout, Gravity.CENTER,0,0);
    }
}
