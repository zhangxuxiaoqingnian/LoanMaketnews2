package money.kuxuan.platform.moneyplatfrom.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.common.widget.AdDialog;
import money.kuxuan.platform.common.widget.StatusBarUtil;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.model.api.account.LoginModel;
import money.kuxuan.platform.factory.model.api.launcher.RspAdModel;
import money.kuxuan.platform.factory.net.Network;
import money.kuxuan.platform.factory.presenter.home.MainContract;
import money.kuxuan.platform.factory.presenter.home.MainPresenter;
import money.kuxuan.platform.moneyplatfrom.Constant;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.frags.main.ActiveFragment;
import money.kuxuan.platform.moneyplatfrom.frags.main.CreditCardFragment;
import money.kuxuan.platform.moneyplatfrom.frags.main.HomeFragment;
import money.kuxuan.platform.moneyplatfrom.frags.main.MineFragment;
import money.kuxuan.platform.moneyplatfrom.frags.main.SearchFragment;
import money.kuxuan.platform.moneyplatfrom.frags.main.state.ExaimeFragment;
import money.kuxuan.platform.moneyplatfrom.frags.main.state.ExpertFragment;
import money.kuxuan.platform.moneyplatfrom.helper.DataGenerator;
import money.kuxuan.platform.moneyplatfrom.helper.FragmentHelper;
import money.kuxuan.platform.moneyplatfrom.helper.SPUtil;
import money.kuxuan.platform.moneyplatfrom.web.WebActivity;

import static money.kuxuan.platform.factory.presenter.launcher.LauncherPresenter.FILEPATH;
import static money.kuxuan.platform.factory.presenter.launcher.LauncherPresenter.LINK;
import static money.kuxuan.platform.factory.presenter.launcher.LauncherPresenter.NEW_DIALOG;
import static money.kuxuan.platform.factory.presenter.launcher.LauncherPresenter.PRODUCTID;
import static money.kuxuan.platform.factory.presenter.launcher.LauncherPresenter.SKIPTYPE;
import static money.kuxuan.platform.moneyplatfrom.helper.DataGenerator.mTabChannelRes;
import static money.kuxuan.platform.moneyplatfrom.helper.DataGenerator.mTabRes;
import static money.kuxuan.platform.moneyplatfrom.helper.DataGenerator.mTabTitle;
import static money.kuxuan.platform.moneyplatfrom.helper.DataGenerator.mTabXTitle;

public class MainActivity extends PresenterActivity<MainContract.Presenter>
        implements TabLayout.OnTabSelectedListener, FragmentHelper.OnTabChangeListener<Integer>, MainContract.View {
    @BindView(R.id.bottom_tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.lay_container)
    FrameLayout lay_container;

    @BindView(R.id.appbar)
    View mLayAppbar;

    @BindView(R.id.txt_title)
    TextView mTitle;

    @BindView(R.id.line)
    View line;

    private FragmentHelper<Integer> mFragmentHelper;

    AdDialog adDialog;

    int time;

    int state = 0;  //默认为过审状态
    //点击的时间间隔
    private long exitTime = 0;

    private String dialogFile; //弹框图的存储位置


    private static final String NEW_PICTURE = "NEW_PICTURE";

    private static final String TAG = "MainActivity";

    private static final String KEY_TIME = "KEY_IMAGE_FILE";

    private static final String SHOWADDIALOG = "SHOWADDIALOG";

    private static final String CHANNEL = "CHANNEL";

    private static final String CHANNELOKORNOTOK = "CHANNELOKORNOTOK";
    private String link;
    private String skiptype;
    private String product_id;
    private boolean showaddialog = false;

    private static final String FILE_END = ".apk"; //文件后缀名

    private String mPatchFileDir; //patch要保存的文件夹
    private String mFilePtch; //patch文件保存路径


    public static void show(Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        context.startActivity(intent);

    }


    @Override
    protected void initWidows() {
        super.initWidows();
        StatusBarUtil.StatusBarLightMode(this);



    }



    private void loginAuto() {
        String phone = (String) SPUtil.get(this, Constant.UserInfo.USERNAME, "");
        String pwd = (String) SPUtil.get(this, Constant.UserInfo.PASSWORD, "");
        if (!TextUtils.isEmpty(phone)) {
            Network.remote().accountLogin(new LoginModel(phone, pwd));
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        loginAuto();
        mFragmentHelper = new FragmentHelper(this, R.id.lay_container,
                getSupportFragmentManager(), this);
        if (checkChannel()==false) {
            mFragmentHelper.add(0, new FragmentHelper.Tab<Integer>(HomeFragment.class, R.string.title_home))
                    .add(1, new FragmentHelper.Tab<Integer>(SearchFragment.class, R.string.title_find))
                    .add(2,new FragmentHelper.Tab<Integer>(CreditCardFragment.class,R.string.action_creditcard))
                    .add(3, new FragmentHelper.Tab<Integer>(ActiveFragment.class, R.string.title_active))
                    .add(4, new FragmentHelper.Tab<Integer>(MineFragment.class, R.string.title_mine));
        } else {
            mFragmentHelper.add(0, new FragmentHelper.Tab<Integer>(ExaimeFragment.class, R.string.title_quick))
                    .add(1, new FragmentHelper.Tab<Integer>(ExpertFragment.class, R.string.title_special))
//                    .add(2, new FragmentHelper.Tab<Integer>(CalculateFragment.class, R.string.title_cal))
                    .add(2, new FragmentHelper.Tab<Integer>(MineFragment.class, R.string.title_mine));

        }
        //添加底部导航的监听
        mTabLayout.addOnTabSelectedListener(this);

    }

    @Override
    protected void initData() {
        super.initData();
        setTabs(mTabLayout, this.getLayoutInflater());
        mPresenter.start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constant.Code.REQUEST_CODE){
            if(resultCode==Constant.Code.RESULT_LOGINSUC_CODE){
                //登录之后
                mTabLayout.getTabAt(0).select();
            }
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.e(TAG, tab.getText() + "");
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            View view = mTabLayout.getTabAt(i).getCustomView();
            ImageView icon = (ImageView) view.findViewById(R.id.tab_content_image);
            TextView text = (TextView) view.findViewById(R.id.tab_content_text);
            if (checkChannel()==false) {
                if (i == tab.getPosition()) {
                    icon.setImageResource(DataGenerator.mTabResPressed[i]);
                    text.setTextColor(0xff01d09c);
                } else {
                    icon.setImageResource(DataGenerator.mTabRes[i]);
                    text.setTextColor(getResources().getColor(R.color.textSecond));
                }
            } else {
                if (i == tab.getPosition()) {
                    icon.setImageResource(DataGenerator.mTabResChanelPressed[i]);
                    text.setTextColor(0xff01d09c);
                } else {
                    icon.setImageResource(DataGenerator.mTabChannelRes[i]);
                    text.setTextColor(getResources().getColor(R.color.textSecond));
                }
            }

        }
        mFragmentHelper.performClickMenu(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void setTabs(TabLayout tabLayout, LayoutInflater inflater) {
        //修改底部标题栏
        if (checkChannel()==false) {
            for (int i = 0; i < mTabRes.length; i++) {
                TabLayout.Tab tab = tabLayout.newTab();

                View view = inflater.inflate(R.layout.tab_content, null);

                tab.setCustomView(view);

                TextView tvTitle = (TextView) view.findViewById(R.id.tab_content_text);

                tvTitle.setText(mTabTitle[i]);

                ImageView imgTab = (ImageView) view.findViewById(R.id.tab_content_image);

                imgTab.setImageResource(mTabRes[i]);

                tabLayout.addTab(tab);
            }
        } else {
            for (int i = 0; i < mTabChannelRes.length; i++) {

                TabLayout.Tab tab = tabLayout.newTab();

                View view = inflater.inflate(R.layout.tab_content, null);

                tab.setCustomView(view);

                TextView tvTitle = (TextView) view.findViewById(R.id.tab_content_text);

                tvTitle.setText(mTabXTitle[i]);

                ImageView imgTab = (ImageView) view.findViewById(R.id.tab_content_image);

                imgTab.setImageResource(mTabChannelRes[i]);

                tabLayout.addTab(tab);

            }
        }
    }


    @Override
    public void onTabChanged(FragmentHelper.Tab<Integer> newTab, FragmentHelper.Tab<Integer> oldTab) {
        mTitle.setText(newTab.extra);
        if (newTab.extra.equals(R.string.title_home)) {
            mLayAppbar.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
        } else {
            mLayAppbar.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
        }

        Log.e(TAG, newTab.extra + "");

    }

    /**
     * Dialog
     */
    public void createDialog(final RspAdModel rspAdModel) {
        adDialog = new AdDialog(this);

        adDialog.setImageView(rspAdModel.getData().getImage_url());
        adDialog.setNoOnclickListener("确定", new AdDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                adDialog.dismiss();
            }
        });

        adDialog.setImageClickListener(new AdDialog.onImageOnclickListener() {
            @Override
            public void onImageClick() {
                Log.e(TAG, rspAdModel.getData().getProduct_id() + "product_id");
                Log.e(TAG, rspAdModel.getData().getSkip_type() + "skiptype");
                product_id = rspAdModel.getData().getProduct_id();
                skiptype = rspAdModel.getData().getSkip_type();
                link = rspAdModel.getData().getLink();
                if (rspAdModel.getData().getSkip_type().equals("0")) {
                    if (rspAdModel.getData().getProduct_id().equals("0")) {
                        WebActivity.show(MainActivity.this, null, link, product_id, skiptype);
                    } else {
                        DetailActivity.show(MainActivity.this, product_id,"homeAd",0);
                    }
                    adDialog.dismiss();
                } else {
                    WebActivity.show(MainActivity.this, null, link, product_id, skiptype);
                    adDialog.dismiss();
                }

            }
        });
        adDialog.show();

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    /**
     * 判断是否是线上
     *
     * @return 线上
     */
    private boolean checkChannel() {
        SharedPreferences sp = getSharedPreferences(CHANNEL,
                Context.MODE_PRIVATE);
        String channelOk = sp.getString(CHANNELOKORNOTOK, "1");
        return channelOk.equals("0");
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    /**
     * 判断弹框图缓存成功或失败
     *
     * @return
     */
    private String loadDialog() {
        SharedPreferences sp = getSharedPreferences(NEW_DIALOG,
                Context.MODE_PRIVATE);
        dialogFile = sp.getString(FILEPATH, "");
        link = sp.getString(LINK, "");
        product_id = sp.getString(PRODUCTID, "");
        skiptype = sp.getString(SKIPTYPE, "");
        return dialogFile;

    }

    private void save(String time) {
        SharedPreferences sp = Factory.app().getSharedPreferences(NEW_PICTURE,
                Context.MODE_PRIVATE);
        sp.edit()
                .putString(KEY_TIME, time)

                .apply();
    }

    private String loadTime() {
        SharedPreferences sp = getSharedPreferences(NEW_PICTURE,
                Context.MODE_PRIVATE);

        return sp.getString(KEY_TIME, "2209017600");

    }


    @Override
    public void setAdModel(String time, RspAdModel rspAdModel) {
        hideLoading();
        if (completeTime(time, loadTime()) && checkChannel()) {
            save(time);
            createDialog(rspAdModel);
        }


    }

    public static String stampToData(String s) {
        String res;
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Long timestamp = Long.parseLong(s) * 1000;
        Date date = new Date(timestamp);
        res = simpleFormatter.format(date);
        return res;
    }

    public static boolean completeTime(String newTime, String oldTime) {
        String newData = stampToData(newTime);
        String oldData = stampToData(oldTime);
        String newDataSpli[] = newData.split("-");
        String oldDataSpli[] = oldData.split("-");
        if (Integer.parseInt(newDataSpli[0]) > Integer.parseInt(oldDataSpli[0])) {
            return true;
        } else if (Integer.parseInt(newDataSpli[1]) > Integer.parseInt(oldDataSpli[1])) {
            return true;
        } else if (Integer.parseInt(newDataSpli[2]) > Integer.parseInt(oldDataSpli[2])) {
            return true;
        }
        return false;
    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void showError(int str) {
        super.showError(str);
        hideLoading();
    }
}

