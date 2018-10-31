package money.kuxuan.platform.moneyplatfrom.frags.main.state;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import money.kuxuan.platform.common.app.PresenterFragment;
import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.moneyplatfrom.R;

/**
 * Created by 小狼 on 2018/10/10.
 */

public class MyOrderFragment extends PresenterFragment {

    private View inflate;
    private ViewPager vp;
    private SlidingTabLayout tablayout;
    private List<Fragment> list;
    private List<String> list2;

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.myorder_layout;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.myorder_layout, null);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initview();


        list = new ArrayList<>();
        list.add(new AllOrderFragment());
        list.add(new MyitemOrderFragment());
        list.add(new QiangOrderFragment());

        list2 = new ArrayList<>();
        list2.add("全部订单");
        list2.add("我的发布");
        list2.add("我的抢单");
        MyAdapter myAdapter=new MyAdapter(getChildFragmentManager());
        vp.setAdapter(myAdapter);
        vp.setOffscreenPageLimit(2);

        tablayout.setViewPager(vp);

    }


    public void initview(){

        tablayout = (SlidingTabLayout) inflate.findViewById(R.id.ordertablayout);
        vp = (ViewPager) inflate.findViewById(R.id.ordervp);
    }

    public class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
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
            return list2.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }

}
