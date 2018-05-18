package money.kuxuan.platform.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.OnClick;
import money.kuxuan.platform.common.app.Activity;
import money.kuxuan.platform.common.app.Fragment;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.frags.main.CalculateFragment;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class PlaceActivity extends Activity {
    private Fragment mCurFragment;
    private Fragment mCalculateFragment;

    @BindView(R.id.place)
    FrameLayout frameLayout;

//    @BindView(R.id.tv_title)
//    TextView tv_title;

    String title;

    private static final String TITLE = "TITLE";

    /**
     * 账户Activity显示的入口
     *
     * @param context Context
     */
    public static void show(Context context,String title) {
       Intent intent = new Intent(context,PlaceActivity.class);
        intent.putExtra(TITLE,title);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_place;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        // 初始化Fragment
        mCurFragment = mCalculateFragment = new CalculateFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.place, mCurFragment)
                .commit();

//        tv_title.setText(title);
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        title = bundle.getString(TITLE);

        return !TextUtils.isEmpty(title);
    }




}
