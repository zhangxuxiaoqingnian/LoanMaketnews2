package com.smileflowpig.money.moneyplatfrom.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smileflowpig.money.moneyplatfrom.frags.main.ActiveFragment;
import com.smileflowpig.money.moneyplatfrom.frags.main.HomeFragment;
import com.smileflowpig.money.moneyplatfrom.frags.main.MineFragment;
import com.smileflowpig.money.moneyplatfrom.frags.main.SearchFragment;

import java.util.ArrayList;
import java.util.List;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Fragment;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class DataGenerator {
    public static final int[] mTabRes = new int[]{
            R.drawable.tabbar_home, R.drawable.tabbar_search,R.drawable.tabbar_creditcard,
            R.drawable.tabbar_active, R.drawable.tabbar_mine
    };

    private static List<Integer> list2;
    private static List<Integer> list3;

    public static final List<Integer> icon(){
        list2 = new ArrayList<>();
        list2.add(R.drawable.tabbar_home16);
        list2.add(R.drawable.tabbar_search16);
//        list2.add(R.drawable.tabbar_creditcard8);
        list2.add(R.drawable.tabbar_active16);
        list2.add(R.drawable.tabbar_mine16);
        return list2;
    }
    public static final int[] mTabChannelRes = new int[]{
            R.drawable.tabbar_home10, R.drawable.tabbar_exper10,
            R.drawable.tabbar_color10,R.drawable.tabbar_mine10
    };
    public static final int[] mTabResPressed = new int[]{
            R.drawable.tabbar_home_highlight, R.drawable.tabbar_search_highlight, R.drawable.tabbar_creditcard_highlight,
            R.drawable.tabbar_active_hightlight, R.drawable.tabbar_mine_highlight
    };
    public static final List<Integer> icon2(){
        list3 = new ArrayList<>();
        list3.add(R.drawable.tabbar_home_highligh16);
        list3.add(R.drawable.tabbar_search_highlight16);
//        list3.add(R.drawable.tabbar_creditcard_highlight8);
        list3.add(R.drawable.tabbar_active_hightlight16);
        list3.add(R.drawable.tabbar_mine_highligh16);
        return list3;
    }
    public static final int[] mTabResChanelPressed = new int[]{
            R.drawable.tabbar_home_highligh10, R.drawable.tabbar_expert_hightligh10,
            R.drawable.tabbar_color_highligh10,R.drawable.tabbar_mine_highligh10
    };

    public static final String[] mTabTitle = new String[]{"首页", "贷款","信用卡","资讯", "我的"};
    private static List<String> list;

    public static final List<String> cx(){
        list = new ArrayList<>();
        list.add("首页");
        list.add("贷款");
//        list.add("信用卡");
        list.add("资讯");
        list.add("我的");
        return list;
    }

    public static final String[] mTabXTitle = new String[]{"首页", "发单","订单","我的"};
    public static Fragment[] getFragments() {
        Fragment fragments[] = new Fragment[list2.size()];
        fragments[0] = new HomeFragment();
        fragments[1] = new SearchFragment();
        fragments[2] = new ActiveFragment();
        fragments[3] = new MineFragment();
        return fragments;
    }
    public static View getTabView(Context context,int position){
        View view = LayoutInflater.from(context).inflate(R.layout.tab_content,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.list2.get(position));
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(list.get(position));
        return view;
    }
}
