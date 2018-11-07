package com.smileflowpig.money.moneyplatfrom.frags.main.state;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.smileflowpig.money.factory.bean.MessBannerBean;
import com.smileflowpig.money.factory.bean.MessTypeBean;
import com.smileflowpig.money.factory.netword.NetRequestUtils;

/**
 * Created by 小狼 on 2018/11/4.
 */

public class MessageFragment extends PresenterFragment {

    @BindView(R.id.mess_vp)
    ViewPager vp;
    @BindView(R.id.mess_tablayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.mess_day)
    TextView day;
    @BindView(R.id.mess_orgin)
    TextView orgin;
    @BindView(R.id.mess_data)
    TextView messdata;
    @BindView(R.id.messimage)
    ImageView image;
    @BindView(R.id.messtext)
    TextView messtext;
    private List<MessTypeBean.RstBean> rst;
    private List<Fragment> list;
    private  Fragment  currentFragment=new Fragment();
    private FrameLayout fram;
    private Calendar calendar;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initview();
        //获取banner数据
        getbanner();
        //获取类别数据
        gettype();

    //获取当前时间
        calendar = Calendar.getInstance();
    // 获取当前年份
    int nian = calendar.get(Calendar.YEAR);
    // 获取当前月份以（0开头，所以要+1）
    int mMonth = calendar.get(Calendar.MONTH);
    // 获取当前为几号以（0开头）
    int mDay = calendar.get(Calendar.DAY_OF_MONTH);

    String week = getWeek();
    if(mDay<10){
        day.setText("0"+mDay);
    }else {
        day.setText(mDay+"");
    }
       messdata.setText(nian+"年"+(mMonth+1)+"月");
       orgin.setText(week);

    }

    public void initview(){

        list = new ArrayList<>();
        list.add(new AllFragment());
        list.add(new TwoFragment());
        list.add(new ThreeFragment());
        list.add(new FourFragment());
    }

    public void gettype(){

        Observable<MessTypeBean> messTypeBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().gettexttype().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        messTypeBeanObservable.subscribe(new Observer<MessTypeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MessTypeBean messTypeBean) {
                rst = messTypeBean.rst;

                MyvpAdapter myvpAdapter=new MyvpAdapter(getChildFragmentManager());
                vp.setAdapter(myvpAdapter);
                tabLayout.setViewPager(vp);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getbanner(){

        Observable<MessBannerBean> objectObservable = new NetRequestUtils().bucuo().getbaseretrofit().gettextbanner().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        objectObservable.subscribe(new Observer<MessBannerBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MessBannerBean messageBean) {

                MessBannerBean.RstBean rst = messageBean.rst;
                Glide.with(getActivity()).load(rst.img).into(image);
                messtext.setText(rst.content);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }


    public class MyvpAdapter extends FragmentPagerAdapter{

        public MyvpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return rst.get(position).name;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }

    }


    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.message_fragment_layout;
    }

    private FragmentTransaction switchFragment(Fragment targetFragment) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.frame, targetFragment,targetFragment.getClass().getName());

        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;

    }


    public  String getWeek(){

        int i = calendar.get(Calendar.DAY_OF_WEEK);
        switch (i){
            case 1:
                return "星期天";

            case 2:
                return "星期一";

            case 3:
                return "星期二";

            case 4:
                return "星期三";

            case 5:
                return "星期四";

            case 6:
                return "星期五";

            case 7:
                return "星期六";

            default:

                return "";

        }

    }
}
