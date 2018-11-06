package com.smileflowpig.money.moneyplatfrom.frags.main.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterFragment;
import com.smileflowpig.money.common.factory.presenter.BaseContract;

/**
 * Created by 小狼 on 2018/10/18.
 */

public class NewSearchFragment extends PresenterFragment implements View.OnClickListener{

    private View inflate;
    private FrameLayout frame;
    private TextView money;
    private TextView data;
    private TextView identity;
    private  Fragment  currentFragment=new Fragment();
    private MoneyFragment moneyFragment;
    private IndentFragment indentFragment;
    private DataFragment dataFragment;

    @Override
    protected BaseContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.newsearch_layout;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.newsearch_layout, null);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initview();

        switchFragment(new MoneyFragment()).commit();
        money.setTextColor(Color.parseColor("#000000"));
        money.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        money.setOnClickListener(this);
        data.setOnClickListener(this);
        identity.setOnClickListener(this);

    }
    public void initview(){

        frame = (FrameLayout) inflate.findViewById(R.id.newframe);
        money = (TextView) inflate.findViewById(R.id.newmoney);
        data = (TextView) inflate.findViewById(R.id.newdata);
        identity = (TextView) inflate.findViewById(R.id.newidentity);

        moneyFragment = new MoneyFragment();
        dataFragment = new DataFragment();
        indentFragment = new IndentFragment();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newmoney:
                switchFragment(moneyFragment).commit();
                //com.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                data.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                identity.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                //com.setTextColor(Color.parseColor("#000000"));
                data.setTextColor(Color.parseColor("#444444"));
                identity.setTextColor(Color.parseColor("#444444"));
                break;
            case R.id.newdata:
                switchFragment(dataFragment).commit();
                data.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                //com.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                identity.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));

                data.setTextColor(Color.parseColor("#000000"));
                money.setTextColor(Color.parseColor("#444444"));
                identity.setTextColor(Color.parseColor("#444444"));
                break;
            case R.id.newidentity:
                switchFragment(indentFragment).commit();
                identity.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                data.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                money.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));

                identity.setTextColor(Color.parseColor("#000000"));
                data.setTextColor(Color.parseColor("#444444"));
                money.setTextColor(Color.parseColor("#444444"));
                break;
        }
    }

    private FragmentTransaction switchFragment(Fragment targetFragment) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.newframe, targetFragment,targetFragment.getClass().getName());

        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;

    }
}
