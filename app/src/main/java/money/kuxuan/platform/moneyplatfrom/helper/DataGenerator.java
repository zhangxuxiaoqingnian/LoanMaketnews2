package money.kuxuan.platform.moneyplatfrom.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import money.kuxuan.platform.common.app.Fragment;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.frags.main.ActiveFragment;
import money.kuxuan.platform.moneyplatfrom.frags.main.HomeFragment;
import money.kuxuan.platform.moneyplatfrom.frags.main.MineFragment;
import money.kuxuan.platform.moneyplatfrom.frags.main.SearchFragment;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class DataGenerator {
    public static final int[] mTabRes = new int[]{
            R.drawable.tabbar_home, R.drawable.tabbar_search,R.drawable.tabbar_creditcard,
            R.drawable.tabbar_active, R.drawable.tabbar_mine
    };
    public static final int[] mTabChannelRes = new int[]{
            R.drawable.tabbar_home, R.drawable.tabbar_expert,
            R.drawable.tabbar_mine
    };
    public static final int[] mTabResPressed = new int[]{
            R.drawable.tabbar_home_highlight, R.drawable.tabbar_search_highlight, R.drawable.tabbar_creditcard_highlight,
            R.drawable.tabbar_active_hightlight, R.drawable.tabbar_mine_highlight
    };
    public static final int[] mTabResChanelPressed = new int[]{
            R.drawable.tabbar_home_highlight, R.drawable.tabbar_expert_hightlight,
            R.drawable.tabbar_mine_highlight
    };

    public static final String[] mTabTitle = new String[]{"首页", "贷款","信用卡","活动", "我的"};

    public static final String[] mTabXTitle = new String[]{"首页", "专栏", "我的"};
    public static Fragment[] getFragments() {
        Fragment fragments[] = new Fragment[mTabRes.length];
        fragments[0] = new HomeFragment();
        fragments[1] = new SearchFragment();
        fragments[2] = new ActiveFragment();
        fragments[3] = new MineFragment();
        return fragments;
    }
    public static View getTabView(Context context,int position){
        View view = LayoutInflater.from(context).inflate(R.layout.tab_content,null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}
