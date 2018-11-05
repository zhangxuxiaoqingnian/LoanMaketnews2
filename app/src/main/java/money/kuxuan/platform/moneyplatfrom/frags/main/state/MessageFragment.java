package money.kuxuan.platform.moneyplatfrom.frags.main.state;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.stx.xhb.xbanner.XBanner;

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
import money.kuxuan.platform.factory.bean.MessBannerBean;
import money.kuxuan.platform.factory.bean.MessTypeBean;
import money.kuxuan.platform.factory.bean.MessageBean;
import money.kuxuan.platform.factory.netword.NetRequestUtils;
import money.kuxuan.platform.moneyplatfrom.Adapter.MessTypeAdapter;
import money.kuxuan.platform.moneyplatfrom.Adapter.MessageAdapter;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.util.DisplayUtils4;
import money.kuxuan.platform.moneyplatfrom.util.DisplayUtils6;
import money.kuxuan.platform.moneyplatfrom.util.DividerItemDecoration3;

/**
 * Created by 小狼 on 2018/11/4.
 */

public class MessageFragment extends PresenterFragment {

    @BindView(R.id.mess_banner)
    XBanner banner;
    @BindView(R.id.mess_vp)
    ViewPager vp;
    @BindView(R.id.mess_tablayout)
    SlidingTabLayout tabLayout;
    private List<MessTypeBean.RstBean> rst;
    private List<Fragment> list;
    private  Fragment  currentFragment=new Fragment();
    private FrameLayout fram;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initview();
        //获取banner数据
        getbanner();
        //获取类别数据
        gettype();

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
                final List<String> list=new ArrayList<>();
                list.add(rst.img);
                banner.setData(list,null);
                banner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position2) {

                        Glide.with(getActivity()).load(list.get(position2)).into((ImageView) view);
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
}
