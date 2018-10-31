package money.kuxuan.platform.moneyplatfrom.frags.main.state;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import money.kuxuan.platform.common.app.PresenterFragment;
import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.factory.bean.MyHomeFragmentBean;
import money.kuxuan.platform.factory.bean.MyHomeListBean;
import money.kuxuan.platform.factory.netword.NetRequestUtils;
import money.kuxuan.platform.moneyplatfrom.Adapter.MyHomeAdapter;
import money.kuxuan.platform.moneyplatfrom.Adapter.MyHomeAdapter2;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.MyHomeActivity;
import money.kuxuan.platform.moneyplatfrom.activities.MyTypeActivity;
import money.kuxuan.platform.moneyplatfrom.util.DisplayUtils2;
import money.kuxuan.platform.moneyplatfrom.util.DisplayUtils3;

/**
 * Created by 小狼 on 2018/10/9.
 */

public class MyHomeFragment extends PresenterFragment implements View.OnClickListener{

    private View inflate;
    private RecyclerView rv;
    private List<MyHomeFragmentBean.RstBean.BannerBean> homelist;
    private SmartRefreshLayout refreshlayout;
    private ClassicsHeader header;
    private ClassicsFooter footer;
    private TextView allfooter;
    private boolean istrue=false;
    private List<MyHomeListBean.RstBean.DataBean> list;
    private List<MyHomeListBean.RstBean.DataBean> list2;
    private int pager=1;
    private MyHomeAdapter2 myHomeAdapter;
    private PopupWindow popupWindow;
    private LinearLayout layout;


    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.myhomefragment_layout;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.myhomefragment_layout, null);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initview();

        getpopwindow();
        //list2 = new ArrayList<>();
        //获取banner
         //getbanner();

        //获取首页列表
//        getrvlist();


    }

    public void initview(){

        rv = (RecyclerView) inflate.findViewById(R.id.homerv);
        rv.addItemDecoration(new DisplayUtils3.SpacesItemDecoration());

        refreshlayout = (SmartRefreshLayout) inflate.findViewById(R.id.refreshlayout);
        header = (ClassicsHeader) inflate.findViewById(R.id.header);
        footer = (ClassicsFooter) inflate.findViewById(R.id.footer);
        layout = (LinearLayout) inflate.findViewById(R.id.homelayout);


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
                    getrvlist();
                }else {
                    Toast.makeText(getActivity(),"没有更多啦",Toast.LENGTH_SHORT).show();
                    refreshlayout.finishRefresh();
                    refreshlayout.finishLoadmore();
                }


            }
        });
    }

    public void getrvlist(){

        Observable<MyHomeListBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().gethomelist(0,pager).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<MyHomeListBean>() {


            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MyHomeListBean o) {

                popupWindow.dismiss();
                refreshlayout.finishRefresh();
                refreshlayout.finishLoadmore();
                if(o!=null){
                final List<MyHomeListBean.RstBean.DataBean> data = o.rst.data;
                boolean hasNext = o.rst.pageinfo.hasNext;
                if(hasNext){
                    pager++;
                    istrue=true;
                }else {
                    istrue=false;
                }

               if(data!=null&&data.size()>0) {

                   list2.addAll(data);
               }
                    if(myHomeAdapter==null){
                        myHomeAdapter = new MyHomeAdapter2(getActivity(), list2,"head",homelist);
                        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                        rv.setAdapter(myHomeAdapter);
                    }else {
                        myHomeAdapter.notifyDataSetChanged();
                    }


                    myHomeAdapter.Setnum(new MyHomeAdapter2.Getnum() {
                        @Override
                        public void onclice(int pos) {

                            int id = data.get(pos).id;
                            Intent intent = new Intent(getActivity(), MyHomeActivity.class);
                            intent.putExtra("itemid", id);
                            startActivity(intent);

                        }
                    });

            }}

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


    public void getbanner(){

        Observable<MyHomeFragmentBean> request = new NetRequestUtils().bucuo().getbaseretrofit().gethomebanner("package_14").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        request.subscribe(new Observer<MyHomeFragmentBean>() {


            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MyHomeFragmentBean o) {

                if(o!=null) {

                    homelist = o.rst.banner;
                    //获取首页列表
                    getrvlist();

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
    public void onResume() {
        super.onResume();


         pager=1;
         istrue=false;
         list2 = new ArrayList<>();
         myHomeAdapter=null;
        //获取banner
          getbanner();
        //banner.startAutoPlay();
    }

    public void getpopwindow(){
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.loading_layout, null, false);
        popupWindow = new PopupWindow(inflate, width/3,width/3);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(layout, Gravity.CENTER,0,0);
    }

    @Override
    public void onStop() {
        super.onStop();

        //banner.stopAutoPlay();
    }

    @Override
    public void onClick(View v) {

        Intent intent=new Intent(getActivity(), MyTypeActivity.class);
        switch (v.getId()){

//            case R.id.shungou:
//                intent.putExtra("overtype","顺购");
//                intent.putExtra("typeid",1);
//                startActivity(intent);
//                break;
//            case R.id.lineup:
//                intent.putExtra("overtype","排队");
//                intent.putExtra("typeid",2);
//                startActivity(intent);
//                break;
//            case R.id.otherup:
//                intent.putExtra("overtype","其他");
//                intent.putExtra("typeid",3);
//                startActivity(intent);
//                break;

        }
    }
}
