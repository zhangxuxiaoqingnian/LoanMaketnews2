package com.smileflowpig.money.moneyplatfrom.frags.main.state;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smileflowpig.money.moneyplatfrom.Adapter.MessageAdapter;
import com.smileflowpig.money.moneyplatfrom.Adapter.MyAdapter;
import com.smileflowpig.money.moneyplatfrom.Adapter.PlatformAdapter;
import com.smileflowpig.money.moneyplatfrom.Constant;
import com.smileflowpig.money.moneyplatfrom.LampView;
import com.smileflowpig.money.moneyplatfrom.activities.CaseurlActivity;
import com.smileflowpig.money.moneyplatfrom.activities.DetailActivity;
import com.smileflowpig.money.moneyplatfrom.activities.TableActivity;
import com.smileflowpig.money.moneyplatfrom.util.CustomViewpagerView;
import com.smileflowpig.money.moneyplatfrom.util.DisplayUtils3;
import com.smileflowpig.money.moneyplatfrom.util.DividerItemDecoration3;
import com.smileflowpig.money.moneyplatfrom.util.ScaleTransformer;
import com.stx.xhb.xbanner.XBanner;

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
import com.smileflowpig.money.factory.bean.Allbanner;
import com.smileflowpig.money.factory.bean.HomedataBean;
import com.smileflowpig.money.factory.bean.LapviewBean;
import com.smileflowpig.money.factory.bean.MessageBean;
import com.smileflowpig.money.factory.net.Network;
import com.smileflowpig.money.factory.netword.NetRequestUtils;
import com.smileflowpig.money.factory.presenter.notice.Notice;

/**
 * Created by 小狼 on 2018/11/1.
 */

public class PigHomeFragment extends PresenterFragment implements View.OnClickListener{

    @BindView(R.id.piglampview)
    LampView lampView;

    @BindView(R.id.pigvp)
    CustomViewpagerView vp;

    @BindView(R.id.pigbanner)
    XBanner banner;

//    @BindView(R.id.pig_feature)
//    RecyclerView featurerv;

    @BindView(R.id.platform_rv)
    RecyclerView platformrv;

    @BindView(R.id.message_rv)
    RecyclerView messagerv;

    @BindView(R.id.home_scroll)
    NestedScrollView scroll;

    @BindView(R.id.piglayout)
    LinearLayout piglayout;

    @BindView(R.id.shoulayout)
    LinearLayout shoulayout;

    @BindView(R.id.pig_alldata)
    TextView alldata;

    @BindView(R.id.pig_allnum)
    TextView allnum;

    @BindView(R.id.overimg)
    ImageView overimg;

    @BindView(R.id.onedata)
    LinearLayout onedata;

    @BindView(R.id.twodata)
    LinearLayout twodata;

    @BindView(R.id.threedata)
    LinearLayout threedata;

    @BindView(R.id.fourdata)
    LinearLayout fourdata;

    private List<String> list;
    private int measuredHeight;
    private int changid;
    private Alldatacont alldatacont;
    private AnimatorSet animatorSet;
    private boolean isscrll=false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initview();
        //获取当前渠道号
        String channelId = Network.channelId;
        changid = Integer.parseInt(channelId);
        list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add("");
        }
        //滚动文字
        getlapview();
        //banner
        getbannerdata();
        //特色功能
        getfeature();
        //热门平台
        getplatform();
        //热门资讯
        getmessage();

        //滑动模块
        getclose();
//        MyAdapter myAdapter=new MyAdapter();
//        vp.setAdapter(myAdapter);
//        vp.setCurrentItem(1);
//        vp.setOffscreenPageLimit(3);
//        vp.setPageMargin(-50);
//        vp.setPageTransformer(false,new ScaleTransformer());


        //获取控件高度
        ViewTreeObserver vto = shoulayout.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                measuredHeight = shoulayout.getMeasuredHeight();
                return true;
            }
        });

    }

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.pighome_layout;
    }

    public void getclose(){

        Observable<HomedataBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().gethomedata(13, changid,6,1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<HomedataBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomedataBean o) {

                final List<HomedataBean.RstBean.DataBean> data = o.rst.data;


                MyAdapter myAdapter=new MyAdapter(getActivity(),data);
                vp.setAdapter(myAdapter);
                vp.setCurrentItem(10000*data.size()+1);
                vp.setOffscreenPageLimit(3);
                vp.setPageMargin(-120);
                vp.setPageTransformer(false,new ScaleTransformer());
            }

            @Override
            public void onError(Throwable e) {

                System.out.println(e.toString()+"错误");
            }

            @Override
            public void onComplete() {

            }
        });
    }
    //滚动文字资源
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
                lampView.setList(list);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getmessage(){

        Observable<MessageBean> messageBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().gettextlist("1", "0", 1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        messageBeanObservable.subscribe(new Observer<MessageBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MessageBean messageBean) {

                List<MessageBean.RstBean> rst = messageBean.rst;
                final List<MessageBean.RstBean.NewListBean> new_list = rst.get(0).new_list;
                MessageAdapter messageAdapter=new MessageAdapter(getActivity(),new_list);
                messagerv.setLayoutManager(new LinearLayoutManager(getActivity()){
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                });
                messagerv.setAdapter(messageAdapter);

                messageAdapter.setitemposition(new MessageAdapter.getItemposition() {
                    @Override
                    public void success(int pos) {
                        Intent intent=new Intent(getActivity(), CaseurlActivity.class);
                        intent.putExtra("urlid",new_list.get(pos).id);
                        intent.putExtra("urlname",new_list.get(pos).view_num);
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

    public void getplatform(){

        Observable<HomedataBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().gethomedata(12, changid,18,1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<HomedataBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomedataBean o) {

                final List<HomedataBean.RstBean.DataBean> data = o.rst.data;
                PlatformAdapter platformAdapter=new PlatformAdapter(getActivity(),data);
                platformrv.setLayoutManager(new LinearLayoutManager(getActivity()){
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                });
                platformrv.setAdapter(platformAdapter);

                platformAdapter.setItempostion(new PlatformAdapter.getItempostion() {
                    @Override
                    public void success(int pos) {
                        DetailActivity.show(getActivity(), data.get(pos).id+"","notice",0);

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

    public void getfeature(){

//        FeatureAdapter featureAdapter=new FeatureAdapter(getActivity(),list);
//        LinearLayoutManager ms=new LinearLayoutManager(getActivity());
//        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
//        featurerv.setLayoutManager(ms);
//        featurerv.setAdapter(featureAdapter);
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
                                DetailActivity.show(getActivity(), homeact.get(position).product_id+"","notice",0);
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


//    public class MyAdapter extends PagerAdapter{
//
//        @Override
//        public int getCount() {
//            return list.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view==object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.myvp_layout, null);
//            container.addView(inflate);
//            return inflate;
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView((View) object);
//        }
//    }
    public void initview(){

        animatorSet = new AnimatorSet();
        platformrv.setFocusable(false);
        platformrv.setNestedScrollingEnabled(false);
        messagerv.setFocusable(false);
        messagerv.setNestedScrollingEnabled(false);
        alldata.setOnClickListener(this);
        allnum.setOnClickListener(this);
        onedata.setOnClickListener(this);
        twodata.setOnClickListener(this);
        threedata.setOnClickListener(this);
        fourdata.setOnClickListener(this);
//        featurerv.setNestedScrollingEnabled(false);
//        featurerv.addItemDecoration(new DisplayUtils5.SpacesItemDecoration());
        //platformrv.addItemDecoration(new DisplayUtils3.SpacesItemDecoration());
        DividerItemDecoration3 decoration=new DividerItemDecoration3(getActivity(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.item_shap));  //把样式放进去
        messagerv.addItemDecoration(decoration);
        scroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if(scrollY>=measuredHeight){
                    piglayout.setVisibility(View.VISIBLE);
                    shoulayout.setVisibility(View.GONE);
                }else {
                    piglayout.setVisibility(View.GONE);
                    shoulayout.setVisibility(View.VISIBLE);

                }

            }
        });
        scroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_MOVE){
                    Animetion(overimg);
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    isscrll=false;
                    Animetion2(overimg);
                }
                return false;
            }
        });
    }

    public void Animetion(ImageView ic){

        if(!isscrll){
            ObjectAnimator translationX = new ObjectAnimator().ofFloat(ic,"translationX",0,200f);
            ObjectAnimator translationY = new ObjectAnimator().ofFloat(ic,"translationY",0,0);

            //组合动画
            animatorSet.playTogether(translationX,translationY); //设置动画
            animatorSet.setDuration(500);  //设置动画时间
            animatorSet.start(); //启动
            isscrll=true;
        }


    }
    public void Animetion2(ImageView ic){

        ObjectAnimator translationX = new ObjectAnimator().ofFloat(ic,"translationX",200,0);
        ObjectAnimator translationY = new ObjectAnimator().ofFloat(ic,"translationY",0,0);

        animatorSet.playTogether(translationX,translationY); //设置动画
        animatorSet.setDuration(500);  //设置动画时间
        animatorSet.start(); //启动

    }

    public Alldatacont listener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (Alldatacont) activity;
    }


    public interface Alldatacont{

        void daikuan();
        void message();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pig_alldata:
                if(listener!=null)
                    listener.daikuan();
                break;
            case R.id.pig_allnum:
                if(listener!=null)
                    listener.message();
                break;
            case R.id.onedata:
                Intent intent=new Intent(getActivity(), TableActivity.class);
                intent.putExtra("tabtitle","快借1500");
                intent.putExtra("tabid",1);
                startActivityForResult(intent, Constant.Code.REQUEST_CODEF);
                break;
            case R.id.twodata:
                Intent intent2=new Intent(getActivity(), TableActivity.class);
                intent2.putExtra("tabtitle","最新口子");
                intent2.putExtra("tabid",2);
                startActivityForResult(intent2, Constant.Code.REQUEST_CODEF);
                break;
            case R.id.threedata:
                Intent intent3=new Intent(getActivity(), TableActivity.class);
                intent3.putExtra("tabtitle","一定借到钱");
                intent3.putExtra("tabid",3);
                startActivityForResult(intent3, Constant.Code.REQUEST_CODEF);
                break;
            case R.id.fourdata:
                Intent intent4=new Intent(getActivity(), TableActivity.class);
                intent4.putExtra("tabtitle","信用卡");
                intent4.putExtra("tabid",4);
                startActivityForResult(intent4, Constant.Code.REQUEST_CODEF);
                break;
        }
    }


}
