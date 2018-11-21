package com.smileflowpig.money.moneyplatfrom.activities;


import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smileflowpig.money.common.app.Activity;
import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.common.utils.DisplayUtil;
import com.smileflowpig.money.factory.Constant;
import com.smileflowpig.money.factory.bean.HongbaoBean;
import com.smileflowpig.money.factory.bean.HongbaoJson;
import com.smileflowpig.money.factory.data.helper.HongbaoHelper;
import com.smileflowpig.money.factory.persistence.Account;
import com.smileflowpig.money.factory.util.LoginStatusUtil;
import com.smileflowpig.money.factory.util.SPUtil;
import com.smileflowpig.money.moneyplatfrom.frags.main.state.CreditFragment;
import com.smileflowpig.money.moneyplatfrom.frags.main.state.HuaHomeFragment;
import com.smileflowpig.money.moneyplatfrom.frags.main.state.MessageFragment;
import com.smileflowpig.money.moneyplatfrom.frags.main.state.MineMyFragment;
import com.smileflowpig.money.moneyplatfrom.frags.main.state.MyBillFragment;
import com.smileflowpig.money.moneyplatfrom.frags.main.state.MyHomeFragment;
import com.smileflowpig.money.moneyplatfrom.frags.main.state.MyOrderFragment;
import com.smileflowpig.money.moneyplatfrom.frags.main.state.NewMineFragment;
import com.smileflowpig.money.moneyplatfrom.frags.main.state.NewSearchFragment;
import com.smileflowpig.money.moneyplatfrom.frags.main.state.PigHomeFragment;
import com.smileflowpig.money.moneyplatfrom.helper.DataGenerator;
import com.smileflowpig.money.moneyplatfrom.helper.FragmentHelper;
import com.smileflowpig.money.moneyplatfrom.util.HongbaoOperator;
import com.smileflowpig.money.moneyplatfrom.util.ToastUtil;
import com.smileflowpig.money.moneyplatfrom.web.WebActivity;
import com.smileflowpig.money.moneyplatfrom.weight.HongbaoDialog;
import com.smileflowpig.money.moneyplatfrom.weight.JieriDialog;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.widget.AdDialog;
import com.smileflowpig.money.common.widget.SelfDialog;
import com.smileflowpig.money.common.widget.StatusBarUtil;
import com.smileflowpig.money.factory.Factory;
import com.smileflowpig.money.factory.bean.PaoBean;
import com.smileflowpig.money.factory.model.api.RspModel;
import com.smileflowpig.money.factory.model.api.account.LoginModel;
import com.smileflowpig.money.factory.model.api.launcher.LaunchRspModel;
import com.smileflowpig.money.factory.model.api.launcher.RspAdModel;
import com.smileflowpig.money.factory.net.Network;
import com.smileflowpig.money.factory.net.RemoteService;
import com.smileflowpig.money.factory.netword.NetRequestUtils;
import com.smileflowpig.money.factory.presenter.home.MainContract;
import com.smileflowpig.money.factory.presenter.home.MainPresenter;
import com.umeng.analytics.MobclickAgent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.smileflowpig.money.factory.presenter.launcher.LauncherPresenter.FILEPATH;
import static com.smileflowpig.money.factory.presenter.launcher.LauncherPresenter.LINK;
import static com.smileflowpig.money.factory.presenter.launcher.LauncherPresenter.NEW_DIALOG;
import static com.smileflowpig.money.factory.presenter.launcher.LauncherPresenter.PRODUCTID;
import static com.smileflowpig.money.factory.presenter.launcher.LauncherPresenter.SKIPTYPE;
import static com.smileflowpig.money.moneyplatfrom.helper.DataGenerator.cx;
import static com.smileflowpig.money.moneyplatfrom.helper.DataGenerator.icon;
import static com.smileflowpig.money.moneyplatfrom.helper.DataGenerator.icon2;
import static com.smileflowpig.money.moneyplatfrom.helper.DataGenerator.mTabChannelRes;
import static com.smileflowpig.money.moneyplatfrom.helper.DataGenerator.mTabXTitle;

public class MainActivity extends PresenterActivity<MainContract.Presenter>
        implements TabLayout.OnTabSelectedListener, FragmentHelper.OnTabChangeListener<Integer>, MainContract.View, HuaHomeFragment.OnDaikuanClickListener, PigHomeFragment.Alldatacont {
    @BindView(R.id.bottom_tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.lay_container)
    FrameLayout lay_container;

    @BindView(R.id.appbar)
    View mLayAppbar;

    @BindView(R.id.txt_title)
    TextView mTitle;

    @BindView(R.id.headset)
    TextView headset;

    @BindView(R.id.timemore)
    ImageView timemore;

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
    private List<Integer> icon;
    private List<Integer> icon2;
    private List<String> cx;
    private SharedPreferences sp;
    private String credit_hidden;
    private Fragment currentFragment = new Fragment();
    private List<Fragment> list;
    private SelfDialog selfDialog;
    private boolean liulang2;
    private List<Fragment> list2;
    private int mPosition = 0;
    private int position;
    private CountDownTimer countDownTimer;
    private PopupWindow popupWindow;
    private int contvert = 0;
    private int measuredHeight;


    public static void show(Context context) {

        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void initWidows() {
        super.initWidows();
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        StatusBarUtil.StatusBarLightMode(this);
        RxPermissions rxPermission = new RxPermissions(MainActivity.this);
        rxPermission
                .requestEach(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CALENDAR,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                        Manifest.permission.SEND_SMS)
                .subscribe(new io.reactivex.functions.Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {

                        if (permission.granted) {
                            // 用户已经同意该权限

                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』

                        }
                    }
                });

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


    }

    public void getcarvter() {

        mFragmentHelper = new FragmentHelper(this, R.id.lay_container,
                getSupportFragmentManager(), this);

        if (checkChannel()) {

            cx = cx();
            icon = icon();
            icon2 = icon2();


            //请求气泡
            getpao();

            list2 = new ArrayList<>();
            //保留一份
            //list2.add(new HuaHomeFragment());
//            list2.add(new NewSearchFragment());
//            list2.add(new ActiveFragment());
//            list2.add(new NewMineFragment());
//            list2.add(new NewMineFragment());
            list2.add(new PigHomeFragment());
            list2.add(new NewSearchFragment());
            list2.add(new CreditFragment());
            list2.add(new MessageFragment());
            list2.add(new NewMineFragment());
//            list2.add(new NewMineFragment());
            switchFragment(list2.get(position)).commit();
            getvisible2(position);
            //显示隐藏
//            cx.remove(2);
//            icon.remove(2);
//            icon2.remove(2);
            setTabs(mTabLayout, this.getLayoutInflater());
            initBottomTitle();
            mPresenter.start();
            initHongbao();

//                if (credit_hidden.equals("0")) {
//                    mFragmentHelper.add(0, new FragmentHelper.Tab<Integer>(HuaHomeFragment.class, R.string.title_home))
//                            .add(1, new FragmentHelper.Tab<Integer>(NewSearchFragment.class, R.string.title_find))
////                            .add(2, new FragmentHelper.Tab<Integer>(CreditCardFragment.class, R.string.action_creditcard))
//                            .add(2, new FragmentHelper.Tab<Integer>(ActiveFragment.class, R.string.title_active))
//                            .add(3, new FragmentHelper.Tab<Integer>(MineFragment.class, R.string.title_mine));
//                } else {
//                    mFragmentHelper.add(0, new FragmentHelper.Tab<Integer>(HomeFragment.class, R.string.title_home))
//                            .add(1, new FragmentHelper.Tab<Integer>(SearchFragment.class, R.string.title_find))
//                            .add(2, new FragmentHelper.Tab<Integer>(ActiveFragment.class, R.string.title_active))
//                            .add(3, new FragmentHelper.Tab<Integer>(MineFragment.class, R.string.title_mine));
//
//                    cx.remove(2);
//                    icon.remove(2);
//                    icon2.remove(2);
//
//            }


        } else {
            //      mFragmentHelper.add(0, new FragmentHelper.Tab<Integer>(ExaimeNewFragment.class, R.string.title_quick))
//                    .add(1, new FragmentHelper.Tab<Integer>(ExpertFragment.class, R.string.title_special))
            //               .add(2, new FragmentHelper.Tab<Integer>(Calculate2Fragment.class, R.string.title_cal))
//                    .add(3, new FragmentHelper.Tab<Integer>(MineFragment.class, R.string.title_mine));


            list = new ArrayList<>();
            list.add(new MyHomeFragment());
            list.add(new MyBillFragment());
            list.add(new MyOrderFragment());
            //list.add(new MineFragment());
            list.add(new MineMyFragment());
            switchFragment(list.get(0)).commit();
            getvisible(0);
            //显示隐藏
            setTabs(mTabLayout, this.getLayoutInflater());
            initBottomTitle2();
            mPresenter.start();


        }
        //添加底部导航的监听
        mTabLayout.addOnTabSelectedListener(this);
    }

    HongbaoOperator hongbaoOperator;

    private void initHongbao() {
        hongbaoOperator = HongbaoOperator.getInstance(this);
        hongbaoOperator.init(new HongbaoOperator.OnDialogChangeListener() {
            @Override
            public void goToLogin() {
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                intent.putExtra("hongbao", true);
                startActivityForResult(intent, HongbaoOperator.HONGBAO_REQUEST_CODE);

            }

            @Override
            public void showLoadding() {


            }

            @Override
            public void closeLoadding() {

            }

            @Override
            public void goToRecord(String title, String task_name, String money_count, boolean isGet) {
                HongbaoRecordActivity.show(MainActivity.this, title, task_name, money_count, isGet);

            }


        });
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
//        if (hasFocus) {
//            ViewTreeObserver vto = mTabLayout.getViewTreeObserver();
//            vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                public boolean onPreDraw() {
//                    measuredHeight = mTabLayout.getMeasuredHeight();
//                    System.out.println(measuredHeight + "没有呢");
//                    return true;
//                }
//            });
//            boolean b = checkDeviceHasNavigationBar(MainActivity.this);
//            if (b) {
//                System.out.println("有虚拟键");
//                //获取虚拟键高度
//                int actionBarHeight = getActionBarHeight(MainActivity.this);
//                contvert = actionBarHeight + measuredHeight;
//            } else {
//                System.out.println("没有虚拟键");
//                contvert = measuredHeight;
//            }
//
//        }

        super.onWindowFocusChanged(hasFocus);
    }

    public void getpao() {
        Observable<PaoBean> paoBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().gettextpao().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        paoBeanObservable.subscribe(new Observer<PaoBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PaoBean paoBean) {
                final String content = paoBean.rst.content;
                final String icon = paoBean.rst.icon;

                countDownTimer = new CountDownTimer(10 * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                        if (millisUntilFinished / 1000 == 0) {
                            getpopshort(content, icon);
                        }

                    }

                    @Override
                    public void onFinish() {

                    }
                };
                countDownTimer.start();


            }

            @Override
            public void onError(Throwable e) {

                System.out.println("错误了" + e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }


    public void getvisible(int pos) {

        if (pos == 0) {
            //line.setVisibility(View.GONE);
            mTitle.setText("首页");
            headset.setVisibility(View.GONE);
        } else if (pos == 2) {
            mTitle.setText("我的订单");
            headset.setVisibility(View.GONE);
        } else if (pos == 3) {
            mTitle.setText("我的");
            headset.setVisibility(View.VISIBLE);
        }
    }

    public void getvisible2(int pos) {

        if (pos == 0) {
            mTitle.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
            //mTitle.setText("首页");
        } else if (pos == 2) {
            mTitle.setVisibility(View.VISIBLE);
            mTitle.setText("发现");
        } else if (pos == 3) {
            mTitle.setVisibility(View.VISIBLE);
            mTitle.setText("我的");
        } else if (pos == 1) {
            mTitle.setVisibility(View.VISIBLE);
            mTitle.setText("贷款");
        }
    }


    public void createDialog(@StringRes int str) {
        selfDialog = new SelfDialog(MainActivity.this);
        selfDialog.setTitle("温馨提示");
        selfDialog.setMessage(str);
        selfDialog.setNoOnclickListener("确定", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
            }
        });
        selfDialog.show();
    }

    private FragmentTransaction switchFragment(Fragment targetFragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.lay_container, targetFragment, targetFragment.getClass().getName());

        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;

    }

    private void initBottomTitle() {
        View view = mTabLayout.getTabAt(0).getCustomView();
        ImageView icon3 = (ImageView) view.findViewById(R.id.tab_content_image);
        TextView text = (TextView) view.findViewById(R.id.tab_content_text);
        icon3.setImageResource(icon2.get(0));
        text.setTextColor(Color.parseColor("#FFAA48"));
    }

    private void initBottomTitle2() {
        View view = mTabLayout.getTabAt(0).getCustomView();
        ImageView icon3 = (ImageView) view.findViewById(R.id.tab_content_image);
        TextView text = (TextView) view.findViewById(R.id.tab_content_text);
        icon3.setImageResource(DataGenerator.mTabResChanelPressed[0]);
        text.setTextColor(Color.parseColor("#FFAA48"));
    }

    public void cardvisible() {

        RemoteService service = Network.remote();
        Call<RspModel<LaunchRspModel>> call = service.getChannel(Network.channelId);

        // 异步的请求
        call.enqueue(new Callback<RspModel<LaunchRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<LaunchRspModel>> call, Response<RspModel<LaunchRspModel>> response) {
                RspModel<LaunchRspModel> rspModel = response.body();

                if (rspModel != null && rspModel.success()) {

                    LaunchRspModel rst = rspModel.getRst();
                    String version = rst.create_time;
                    credit_hidden = rst.getCredit_hidden();
                    String url = rst.getUrl();
                    getcarvter();


                }
            }

            @Override
            public void onFailure(Call<RspModel<LaunchRspModel>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        position = getIntent().getIntExtra(RecordActivity.POSITION, 0);
        getcarvter();

    }

    public void getpopshort(String cont, String img) {

        //获取屏幕宽度
        int screenWidth = DisplayUtil.getScreenWidth();
        int i = screenWidth / 4;

        View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.qipao_layout, null);
        popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(false);
        //获取自身的长宽高
        inflate.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupHeight = inflate.getMeasuredHeight();
        int popupWidth = inflate.getMeasuredWidth();
        int i1 = (i - popupWidth) / 2;
        contvert = i + i1;
        //获取控件在屏幕上的位置，并赋值给location数组
        int[] location = new int[2];
        mTabLayout.getLocationOnScreen(location);
        popupWindow.showAtLocation(mTabLayout, Gravity.NO_GRAVITY, contvert, location[1] - popupHeight);

        TextView timer = (TextView) inflate.findViewById(R.id.ggtimer);
        ImageView ggicon = (ImageView) inflate.findViewById(R.id.ggicon);
        timer.setText(cont);
        Glide.with(MainActivity.this).load(img).into(ggicon);

        countDownTimer = new CountDownTimer(5 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                System.out.println("剩余时间" + millisUntilFinished / 1000);
                if (millisUntilFinished / 1000 == 0) {
                    System.out.println("剩余不进来");
                    popupWindow.dismiss();
                    countDownTimer.onFinish();
                }
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null) {
            countDownTimer.onFinish();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (hongbaoOperator != null)
            hongbaoOperator.OnActivityForResult(requestCode, resultCode);
        if (requestCode == Constant.Code.REQUEST_CODE) {
            if (resultCode == Constant.Code.RESULT_LOGINSUC_CODE) {
                //登录之后
                mTabLayout.getTabAt(0).select();
            }
        }
        if (resultCode == 2) {
            mTabLayout.getTabAt(1).select();

        } else if (resultCode == 3) {
            mTabLayout.getTabAt(1).select();
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        Log.e(TAG, tab.getText() + "");
        int position = tab.getPosition();
        initTab(position);

    }

    private void initTab(int position) {
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            View view = mTabLayout.getTabAt(i).getCustomView();
            ImageView icon3 = (ImageView) view.findViewById(R.id.tab_content_image);
            TextView text = (TextView) view.findViewById(R.id.tab_content_text);
            if (checkChannel()) {
                if (i == position) {
                    switchFragment(list2.get(i)).commit();
                    getvisible2(i);
                    icon3.setImageResource(icon2.get(i));
                    text.setTextColor(Color.parseColor("#FFAA48"));
                    if (i == 0) {
                        MobclickAgent.onEvent(MainActivity.this, "homeTab");
                    } else if (i == 1) {
                        MobclickAgent.onEvent(MainActivity.this, "loanTab");
                    } else if (i == 2) {
                        MobclickAgent.onEvent(MainActivity.this, "informTab");
                    } else if (i == 3) {
                        MobclickAgent.onEvent(MainActivity.this, "mineTab");
                    }
                } else {
                    icon3.setImageResource(icon.get(i));
                    text.setTextColor(getResources().getColor(R.color.textSecond));
                }
            }
//            } else {
//
//                if(position!=1) {
//                    if (i == position) {
//                        if(i!=1){
//                            switchFragment(list.get(i)).commit();
//                            getvisible(i);
//                            icon3.setImageResource(DataGenerator.mTabResChanelPressed[i]);
//                            text.setTextColor(Color.parseColor("#FFAA48"));
//                        }
//
//                    } else {
//                        icon3.setImageResource(DataGenerator.mTabChannelRes[i]);
//                        text.setTextColor(getResources().getColor(R.color.textSecond));
//
//
//                    }
//                }else {
//
//                    if(position==1&&i==1) {
//                        SharedPreferences sp = getSharedPreferences("Deng", Context.MODE_PRIVATE);
//                        boolean liulang = sp.getBoolean("liulang", false);
//                        if (liulang) {
//                            Intent intent = new Intent(MainActivity.this, MyBillActivity.class);
//                            startActivity(intent);
//                        } else {
//                            Intent intent = new Intent(MainActivity.this, AccountActivity.class);
//                            startActivityForResult(intent, Constant.Code.REQUEST_CODE);
//                        }
//                    }
//                }
//            }

        }
        mFragmentHelper.performClickMenu(position);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    private void setTabs(TabLayout tabLayout, LayoutInflater inflater) {
        mFragmentHelper.performClickMenu(position);
        //修改底部标题栏
        if (checkChannel()) {

            for (int i = 0; i < icon.size(); i++) {
                TabLayout.Tab tab = tabLayout.newTab();

                View view = inflater.inflate(R.layout.tab_content, null);

                tab.setCustomView(view);

                TextView tvTitle = (TextView) view.findViewById(R.id.tab_content_text);

                tvTitle.setText(cx.get(i));

                ImageView imgTab = (ImageView) view.findViewById(R.id.tab_content_image);

                imgTab.setImageResource(icon.get(i));

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
            mLayAppbar.setVisibility(View.VISIBLE);
            line.setVisibility(View.GONE);
        } else {
            mLayAppbar.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
        }
        if (checkChannel()) {
            if (newTab.extra.equals(R.string.title_mine)) {
                timemore.setVisibility(View.VISIBLE);
            } else {
                timemore.setVisibility(View.GONE);
            }
        } else {

            line.setVisibility(View.GONE);

        }


        Log.e(TAG, newTab.extra + "");

        timemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });

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
                        DetailActivity.show(MainActivity.this, product_id, "homeAd", 0, 15);
                    }
                    adDialog.dismiss();
                } else {
                    WebActivity.show(MainActivity.this, null, link, product_id, skiptype);
                    adDialog.dismiss();
                }

            }
        });
        adDialog.show();

//        JieriDialog hongbaoDialog = new JieriDialog(this);
//        hongbaoDialog.setNoOnclickListener(new JieriDialog.OnHongBaoClickListener() {
//            @Override
//            public void onCancle() {
//
//            }
//
//            @Override
//            public void onHongbaoClicck() {
//                ToastUtil.show(MainActivity.this, "点击区域");
//
//            }
//        });
//
//        hongbaoDialog.show();
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
//        SharedPreferences sp = getSharedPreferences(CHANNEL,
//                Context.MODE_PRIVATE);
//        String channelOk = sp.getString(CHANNELOKORNOTOK, "1");
//        return channelOk.equals("0");
        return true;
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
        sp.edit().putString(KEY_TIME, time).apply();
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


    @Override
    public void gotoDaikuan() {
        mTabLayout.getTabAt(1).select();
    }

    @Override
    public void daikuan() {
        mTabLayout.getTabAt(1).select();
    }

    @Override
    public void message() {
        mTabLayout.getTabAt(2).select();
    }

    /**
     * 获取是否存在NavigationBar，是否有虚拟按钮
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;

    }

    //获取虚拟键高度
    public static int getActionBarHeight(Activity activity) {
        TypedValue tv = new TypedValue();
        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
        }
        return 0;
    }


}

