package com.smileflowpig.money.moneyplatfrom.frags.main.state;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocationClient;
import com.bumptech.glide.Glide;
import com.smileflowpig.money.moneyplatfrom.Adapter.HuaAdapter;
import com.smileflowpig.money.moneyplatfrom.Adapter.LikeAdapter;
import com.smileflowpig.money.moneyplatfrom.Adapter.LoseAdapter;
import com.smileflowpig.money.moneyplatfrom.Bean.CaseBean;
import com.smileflowpig.money.moneyplatfrom.Constant;
import com.smileflowpig.money.moneyplatfrom.LampView;
import com.smileflowpig.money.moneyplatfrom.activities.AccountActivity;
import com.smileflowpig.money.moneyplatfrom.activities.CaseurlActivity;
import com.smileflowpig.money.moneyplatfrom.activities.CityotherActivity;
import com.smileflowpig.money.moneyplatfrom.activities.DetailActivity;
import com.smileflowpig.money.moneyplatfrom.activities.TypeActivity;
import com.smileflowpig.money.moneyplatfrom.util.DisplayUtils5;
import com.smileflowpig.money.moneyplatfrom.util.DividerItemDecoration2;
import com.smileflowpig.money.moneyplatfrom.util.RequestCodeInfo;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterFragment;
import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.bean.Allbanner;
import com.smileflowpig.money.factory.bean.HomedataBean;
import com.smileflowpig.money.factory.bean.HomeurlBean;
import com.smileflowpig.money.factory.bean.LapviewBean;
import com.smileflowpig.money.factory.bean.LikeBean;
import com.smileflowpig.money.factory.bean.LoseBean;
import com.smileflowpig.money.factory.net.Network;
import com.smileflowpig.money.factory.netword.NetRequestUtils;
import com.smileflowpig.money.factory.presenter.notice.Notice;

/**
 * Created by 小狼 on 2018/10/19.
 */

public class HuaHomeFragment extends PresenterFragment implements View.OnClickListener{

    private View inflate;
    private LampView lampview;
    private XBanner banner;
    private RecyclerView timerv;
    private RecyclerView tuirv;
    private RecyclerView newrv;
    private LinearLayout smils;
    private LinearLayout bigmoney;
    private LinearLayout allnew;
    private LinearLayout success;
    private LinearLayout card;
    public AMapLocationClient mLocationClient = null;
    private TextView address;
    private TextView tiao;
    private LinearLayout disclose;
    private String channelId;
    private int changid;
    private LinearLayout loselayout;
    private TextView lose;
    private RecyclerView possrv;
    private RecyclerView likerv;
    private TextView hourtime;
    private TextView minutetime;
    private TextView secondtime;
    private CountDownTimer countDownTimer;
    private TextView settime;
    private LinearLayout alldata;
    private LinearLayout kand;
    private LinearLayout newss;
    private List<CaseBean> list;
    private LinearLayout appraisal;
    private TextView wantmore;
    private boolean liulang;

    @Override
    protected int getContentLayoutId() {
        return R.layout.huahome_layout;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.huahome_layout, null);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initview();

        timerv.setNestedScrollingEnabled(false);

        tuirv.setNestedScrollingEnabled(false);

        newrv.setNestedScrollingEnabled(false);


        smils.setOnClickListener(this);
        bigmoney.setOnClickListener(this);
        allnew.setOnClickListener(this);
        success.setOnClickListener(this);
        card.setOnClickListener(this);
        disclose.setOnClickListener(this);
        lose.setOnClickListener(this);
        alldata.setOnClickListener(this);
        kand.setOnClickListener(this);
        newss.setOnClickListener(this);
        appraisal.setOnClickListener(this);
        wantmore.setOnClickListener(this);

//        //初始化定位
//        mLocationClient = new AMapLocationClient(getActivity());
//
//        AMapLocationListener mLocationListener = new AMapLocationListener() {
//            @Override
//            public void onLocationChanged(AMapLocation aMapLocation) {
//
//                String city1 = aMapLocation.getProvince();
//                address.setText(city1);
//            }
//        };
//        //设置定位回调监听
//        mLocationClient.setLocationListener(mLocationListener);
//        //初始化AMapLocationClientOption对象
//        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        mLocationOption.setOnceLocation(true);
//        //给定位客户端对象设置定位参数
//        mLocationClient.setLocationOption(mLocationOption);
//        //启动定位
//        mLocationClient.startLocation();

        //获取当前渠道号
        channelId = Network.channelId;
        changid = Integer.parseInt(channelId);


        //请求banner数据
        getbannerdata();
        //请求滚动文字数据
        getlapview();

        //请求限时模块数据
        getlimit();
        getnewdata();
        getalldata();

        //请求忽略模块
        getlosedata();

        //猜你喜欢
        getyoulikedata();

        //获取h5链接
        geturl();

        timerv.addItemDecoration(new DividerItemDecoration2(getActivity(),R.drawable.item_style,R.dimen.alphabet_size2));
        newrv.addItemDecoration(new DividerItemDecoration2(getActivity(),R.drawable.item_style,R.dimen.alphabet_size2));
        tuirv.addItemDecoration(new DividerItemDecoration2(getActivity(),R.drawable.item_style,R.dimen.alphabet_size2));
        possrv.addItemDecoration(new DisplayUtils5.SpacesItemDecoration());
        likerv.addItemDecoration(new DisplayUtils5.SpacesItemDecoration());

        tiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 startActivityForResult(new Intent(getActivity(),CityotherActivity.class), RequestCodeInfo.GETCITY);
            }
        });
    }

    public void getyoulikedata(){

        Observable<LikeBean> likeBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().getlikedata(335).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        likeBeanObservable.subscribe(new Observer<LikeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LikeBean likeBean) {

                final List<LikeBean.RstBean.DataBean> data = likeBean.rst.data;
                LikeAdapter likeAdapter=new LikeAdapter(getActivity(),data);
                LinearLayoutManager ms=new LinearLayoutManager(getActivity());
                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
                likerv.setLayoutManager(ms);
                likerv.setAdapter(likeAdapter);

                likeAdapter.SetItem(new LikeAdapter.GetNum() {
                    @Override
                    public void success(int pos) {

                        DetailActivity.show(getActivity(), data.get(pos).id+"","notice",0,20);
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
    public void getlosedata(){

        Observable<LoseBean> loseBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().gethomelose().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        loseBeanObservable.subscribe(new Observer<LoseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoseBean loseBean) {

                final List<LoseBean.RstBean.DataBean> data = loseBean.rst.data;
                LoseAdapter loseAdapter=new LoseAdapter(getActivity(),data);
                LinearLayoutManager ms=new LinearLayoutManager(getActivity());
                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
                possrv.setLayoutManager(ms);
                possrv.setAdapter(loseAdapter);

                loseAdapter.SetItemposition(new LoseAdapter.Getitempostion() {
                    @Override
                    public void success(int pos) {
                        DetailActivity.show(getActivity(), data.get(pos).id,"notice",0,20);

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

    public void geturl(){

        Observable<HomeurlBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().geturllist(channelId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<HomeurlBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeurlBean homeurlBean) {

                List<HomeurlBean.RstBean> rst = homeurlBean.rst;
                list = new ArrayList<>();
                for (int i = 0; i < rst.size(); i++) {
                    list.add(new CaseBean(rst.get(i).id,rst.get(i).name,rst.get(i).url));
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

    public void getnewdata(){
        Observable<HomedataBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().gethomedata(13, changid,9,1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<HomedataBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomedataBean o) {

                final List<HomedataBean.RstBean.DataBean> data = o.rst.data;
                HuaAdapter huaAdapter=new HuaAdapter(getActivity(),data);
                newrv.setLayoutManager(new GridLayoutManager(getActivity(),3));
                newrv.setAdapter(huaAdapter);

                huaAdapter.Setnum(new HuaAdapter.Getnum() {
                    @Override
                    public void successover(int pos) {

                        DetailActivity.show(getActivity(), data.get(pos).id+"","notice",0,20);

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
    public void getalldata(){
        Observable<HomedataBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().gethomedata(12, changid,9,1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<HomedataBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomedataBean o) {

                final List<HomedataBean.RstBean.DataBean> data = o.rst.data;
                HuaAdapter huaAdapter=new HuaAdapter(getActivity(),data);
                tuirv.setLayoutManager(new GridLayoutManager(getActivity(),3));
                tuirv.setAdapter(huaAdapter);

                huaAdapter.Setnum(new HuaAdapter.Getnum() {
                    @Override
                    public void successover(int pos) {

                        DetailActivity.show(getActivity(), data.get(pos).id+"","notice",0,20);

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
    public void getlimit(){

        Observable<HomedataBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().gethomedata(11, changid,9,1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<HomedataBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomedataBean o) {

                final List<HomedataBean.RstBean.DataBean> data = o.rst.data;

                int deadline = data.get(0).deadline;
                countDownTimer = new CountDownTimer(1000*deadline,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        int totalSeconds = (int) (millisUntilFinished / 1000);
                        int seconds = totalSeconds % 60;
                        int minutes = (totalSeconds / 60) % 60;
                        int hours = totalSeconds / 3600;
                        if(seconds<10){
                            settime.setText("0"+seconds);
                        }else {
                            settime.setText(seconds+"");
                        }
                        if(minutes<10){
                            minutetime.setText("0"+minutes);
                        }else {
                            minutetime.setText(minutes+"");
                        }
                        if(hours<10){
                            hourtime.setText("0"+hours);
                        }else {
                            hourtime.setText(hours+"");
                        }





                    }

                    @Override
                    public void onFinish() {

                    }
                };
                countDownTimer.start();
                HuaAdapter huaAdapter=new HuaAdapter(getActivity(),data);
                timerv.setLayoutManager(new GridLayoutManager(getActivity(),3));
                timerv.setAdapter(huaAdapter);

                huaAdapter.Setnum(new HuaAdapter.Getnum() {
                    @Override
                    public void successover(int pos) {

                        DetailActivity.show(getActivity(), data.get(pos).id+"","notice",0,20);

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

    public void getlapview(){
        Observable<LapviewBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getgao().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<LapviewBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LapviewBean o) {

                List<LapviewBean.RstBean.DataBean> data = o.rst.data;
                List<Notice> list=new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    list.add(new Notice(data.get(i).product_id,data.get(i).content,data.get(i).link,data.get(i).skip_type+""));
                }
                lampview.setList(list);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void getbannerdata(){

        Observable<Allbanner> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().getallbanner(2).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<Allbanner>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Allbanner o) {
                if(o!=null){
                    final List<Allbanner.RstBean.HomeactBean> homeact = o.rst.homeact;
                    if(homeact!=null&&homeact.size()>0){

                        banner.setData(homeact,null);
                        banner.loadImage(new XBanner.XBannerAdapter() {
                            @Override
                            public void loadBanner(XBanner banner, Object model, View view, int position2) {

                                Glide.with(getActivity()).load(homeact.get(position2).photo).into((ImageView) view);
                            }
                        });
                        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                            @Override
                            public void onItemClick(XBanner banner, Object model,View view, int position) {
                                DetailActivity.show(getActivity(), homeact.get(position).product_id+"","notice",0,20);
                            }
                        });

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

        banner = (XBanner) inflate.findViewById(R.id.homebanner);
        lampview = (LampView) inflate.findViewById(R.id.homelampview);
        timerv = (RecyclerView) inflate.findViewById(R.id.timerv);
        tuirv = (RecyclerView) inflate.findViewById(R.id.tuirv);
        newrv = (RecyclerView) inflate.findViewById(R.id.newrv);
        smils = (LinearLayout) inflate.findViewById(R.id.smils);
        bigmoney = (LinearLayout) inflate.findViewById(R.id.bigmoney);
        allnew = (LinearLayout) inflate.findViewById(R.id.allnew);
        success = (LinearLayout) inflate.findViewById(R.id.successmoney);
        card = (LinearLayout) inflate.findViewById(R.id.cardwant);
        address = (TextView) inflate.findViewById(R.id.address);
        tiao = (TextView) inflate.findViewById(R.id.tiao);
        disclose = (LinearLayout) inflate.findViewById(R.id.disclose);
        loselayout = (LinearLayout) inflate.findViewById(R.id.lose_layout);
        lose = (TextView) inflate.findViewById(R.id.lose);
        possrv = (RecyclerView) inflate.findViewById(R.id.poss_rv);
        likerv = (RecyclerView) inflate.findViewById(R.id.youlikerv);
        hourtime = (TextView) inflate.findViewById(R.id.hourtime);
        minutetime = (TextView) inflate.findViewById(R.id.minutetime);
        settime = (TextView) inflate.findViewById(R.id.settime);
        alldata = (LinearLayout) inflate.findViewById(R.id.alltime_data);
        kand = (LinearLayout) inflate.findViewById(R.id.allkand_data);
        newss = (LinearLayout) inflate.findViewById(R.id.allnews_data);
        appraisal = (LinearLayout) inflate.findViewById(R.id.appraisal);
        wantmore = (TextView) inflate.findViewById(R.id.wantmore);
    }


    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
        SharedPreferences sp = getActivity().getSharedPreferences("Deng", Context.MODE_PRIVATE);
        liulang = sp.getBoolean("liulang", false);
    }

   public OnDaikuanClickListener listener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (OnDaikuanClickListener) activity;
    }


    public interface OnDaikuanClickListener{

        void gotoDaikuan();
    }
    @Override
    public void onStop() {
        super.onStop();

        banner.stopAutoPlay();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.smils:
                Intent intent=new Intent(getActivity(), CaseurlActivity.class);
                intent.putExtra("urlid",list.get(0).id);
                intent.putExtra("urlname",list.get(0).name);
                intent.putExtra("urladdress",list.get(0).url);
                startActivity(intent);
                break;
            case R.id.bigmoney:
                Intent intent2=new Intent(getActivity(), CaseurlActivity.class);
                intent2.putExtra("urlid",list.get(1).id);
                intent2.putExtra("urlname",list.get(1).name);
                intent2.putExtra("urladdress",list.get(1).url);
                startActivity(intent2);
                break;
            case R.id.allnew:
                Intent intent3=new Intent(getActivity(), CaseurlActivity.class);
                intent3.putExtra("urlid",list.get(2).id);
                intent3.putExtra("urlname",list.get(2).name);
                intent3.putExtra("urladdress",list.get(2).url);
                startActivity(intent3);
                break;
            case R.id.successmoney:
                Intent intent4=new Intent(getActivity(), CaseurlActivity.class);
                intent4.putExtra("urlid",list.get(3).id);
                intent4.putExtra("urlname",list.get(3).name);
                intent4.putExtra("urladdress",list.get(3).url);
                startActivity(intent4);
                break;
            case R.id.cardwant:
                Intent intent5=new Intent(getActivity(), CaseurlActivity.class);
                intent5.putExtra("urlid",list.get(4).id);
                intent5.putExtra("urlname",list.get(4).name);
                intent5.putExtra("urladdress",list.get(4).url);
                startActivity(intent5);
                break;
            case R.id.disclose:
                if(liulang){
                    Intent intent6=new Intent(getActivity(), CaseurlActivity.class);;
                    intent6.putExtra("urlid",0);
                    intent6.putExtra("urlname","下款爆料");
                    intent6.putExtra("urladdress","");
                    startActivity(intent6);
                }else {
                    Intent intent11 = new Intent(getActivity(), AccountActivity.class);
                    startActivityForResult(intent11, Constant.Code.REQUEST_CODE);
                }

                break;
            case R.id.appraisal:
                if(liulang){
                    Intent intent10=new Intent(getActivity(), CaseurlActivity.class);;
                    intent10.putExtra("urlid",0);
                    intent10.putExtra("urlname","口子测评");
                    intent10.putExtra("urladdress","");
                    startActivity(intent10);
                }else {
                    Intent intent12 = new Intent(getActivity(), AccountActivity.class);
                    startActivityForResult(intent12, Constant.Code.REQUEST_CODE);
                }

                break;
            case R.id.lose:
                loselayout.setVisibility(View.GONE);
                break;
            case R.id.alltime_data:
                Intent intent7=new Intent(getActivity(), TypeActivity.class);
                intent7.putExtra("typename","限时降息");
                intent7.putExtra("typeid",11);
                intent7.putExtra("typechid",changid);
                startActivity(intent7);
                break;
            case R.id.allkand_data:
                Intent intent8=new Intent(getActivity(), TypeActivity.class);
                intent8.putExtra("typename","为你推荐");
                intent8.putExtra("typeid",12);
                intent8.putExtra("typechid",changid);
                startActivity(intent8);
                break;
            case R.id.allnews_data:
                Intent intent9=new Intent(getActivity(), TypeActivity.class);
                intent9.putExtra("typename","最新产品");
                intent9.putExtra("typeid",13);
                intent9.putExtra("typechid",changid);
                startActivity(intent9);
                break;
            case R.id.wantmore:
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                NewSearchFragment searchFragment=new NewSearchFragment();
//                fragmentTransaction.replace(R.id.lay_container, searchFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
                if(listener!=null)
                    listener.gotoDaikuan();
                break;

        }
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RequestCodeInfo.GETCITY:
                    String city=data.getExtras().getString("city");
                    if(city!= null) {
                        address.setText(city);
                    }
                    break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        countDownTimer.onFinish();
    }



}
