package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smileflowpig.money.moneyplatfrom.Bean.NoteEntity;
import com.smileflowpig.money.moneyplatfrom.frags.AlertFragment;
import com.smileflowpig.money.moneyplatfrom.frags.dialog.DgFragment;
import com.smileflowpig.money.moneyplatfrom.sqlite.DatabaseAdapter;
import com.smileflowpig.money.moneyplatfrom.util.GlideUtil;
import com.smileflowpig.money.moneyplatfrom.util.MarkView;
import com.smileflowpig.money.moneyplatfrom.web.WebActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.Common;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.utils.DateTimeUtil;
import com.smileflowpig.money.factory.Constant;
import com.smileflowpig.money.factory.bean.CancleCollectBean;
import com.smileflowpig.money.factory.bean.CollectBean;
import com.smileflowpig.money.factory.model.api.product.ProductDetail;
import com.smileflowpig.money.factory.model.db.Amount;
import com.smileflowpig.money.factory.model.db.Dialogs;
import com.smileflowpig.money.factory.model.db.Product;
import com.smileflowpig.money.factory.model.db.User;
import com.smileflowpig.money.factory.netword.NetRequestUtils;
import com.smileflowpig.money.factory.presenter.detail.DetailContract;
import com.smileflowpig.money.factory.presenter.detail.DetailPresenter;
import com.smileflowpig.money.factory.util.LoginUtil;
import com.smileflowpig.money.factory.util.SPUtil;
import com.umeng.analytics.MobclickAgent;


//产品详情页
public class DetailActivity extends PresenterActivity<DetailContract.Presenter>
        implements DetailContract.View, DgFragment.DialogClick, View.OnClickListener {
    private static final String TAG = "DetailActivity";
    //产品ID
    public static final String PRODUCT_ID = "PRODUCT_ID";
    //标记，用来区分由谁进入详情页，带flag为AdActivity页进入
    public static final String PRODUCT_FLAG = "PRODUCT_FLAG";
    public static final String TYPE_CHANNEL = "channel_type";
    public static final String FLAG = "1";
    private boolean istrue = false;
    //未登陆弹框
    DgFragment dgFragment;
    //产品Id
    private String mProductId;
    //入口
    private String enter_source;
    private int convert;
    //产品Name
    private String mProductName;
    //产品icon
    private String mProductIcon;
    //产品申请人数
    private String mProductNum;
    //产品URL
    private String mProductUrl;

    private ArrayList<ProductDetail> productDetails;

    private int lang;

    private String flag;

    private boolean show = false;

    ProductDetail mProductDetail;

    private TextView time;
    private TextView give;
    private LinearLayout data;
    private EditText money;
    private ImageView icon;
    private TextView name;
    private TextView text;
    private MarkView mark;
    private TextView money1;
    private TextView timer;
    private TextView obser;
    private TextView lilv;
    private TextView type;
    private TextView success;
    private TextView cond;
    private TextView datum;
    private TextView state;
    private TextView phone;
    private TextView title;
    private LinearLayout back;
    private TextView timetype;
    private LinearLayout moneylayout;
    private ImageView moneyedit;
    private LinearLayout collectlayout;
    private ImageView nocollect;
    private TextView apply;
    private boolean iscont = false;
    private String num;
    private ImageView share;
    private RelativeLayout layout;
    private LinearLayout wei_chat;
    private LinearLayout wei_circle;
    private TextView cancle;
    private AlertFragment alertFragment;
    private ImageView quality;
    private int pricessid;
    private boolean isPush = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void initview() {
        money = (EditText) findViewById(R.id.summoney);
        data = (LinearLayout) findViewById(R.id.ppdata);
        give = (TextView) findViewById(R.id.successgive);
        time = (TextView) findViewById(R.id.pptime);
        icon = (ImageView) findViewById(R.id.ppicon);
        name = (TextView) findViewById(R.id.ppname);
        text = (TextView) findViewById(R.id.pptext);
        mark = (MarkView) findViewById(R.id.ppmark);
        money1 = (TextView) findViewById(R.id.ppmoney);
        timer = (TextView) findViewById(R.id.pptimer);
        obser = (TextView) findViewById(R.id.pp_obser);
        lilv = (TextView) findViewById(R.id.pp_lilv);
        type = (TextView) findViewById(R.id.pp_lilvtype);
        success = (TextView) findViewById(R.id.pp_success);
        cond = (TextView) findViewById(R.id.pp_cond);
        datum = (TextView) findViewById(R.id.pp_datum);
        state = (TextView) findViewById(R.id.pp_state);
        title = (TextView) findViewById(R.id.tv_title);
        back = (LinearLayout) findViewById(R.id.detal_back);
        timetype = (TextView) findViewById(R.id.pp_timetype);
        moneyedit = (ImageView) findViewById(R.id.money_edit);
        collectlayout = (LinearLayout) findViewById(R.id.collect_layout);
        nocollect = (ImageView) findViewById(R.id.no_collect);
        apply = (TextView) findViewById(R.id.apply_success);
        share = (ImageView) findViewById(R.id.share);
        layout = (RelativeLayout) findViewById(R.id.detaillayout);
        quality = (ImageView) findViewById(R.id.detail_quality);
    }

    /**
     * 产品详情页
     *
     * @param context context
     * @param product 商品信息
     */
    public static void show(Context context, Product product, String type_channel) {
        if (product == null || context == null || TextUtils.isEmpty(product.getId()))
            return;
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(PRODUCT_ID, product.getId());
        intent.putExtra(TYPE_CHANNEL, type_channel);

        context.startActivity(intent);
    }

    /**
     * 产品详情页
     *
     * @param context context
     */
    public static void show(Context context, String id, String type_channel, int num, int convert) {

        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(PRODUCT_ID, id);
        intent.putExtra(TYPE_CHANNEL, type_channel);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("Lang", num);
        intent.putExtra("pricessid", convert);
        context.startActivity(intent);


    }


    /**
     * 产品详情页
     *
     * @param context context
     */
    public static void show(Context context, String id, String flag, String type_channel) {
        if (id == null || context == null)
            return;
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(PRODUCT_ID, id);
        intent.putExtra(TYPE_CHANNEL, type_channel);
        intent.putExtra(PRODUCT_FLAG, flag);
        context.startActivity(intent);
    }

    @Override
    protected boolean initArgs(Bundle bundle) {

        Uri data = getIntent().getData();
        if (data != null) {
            mProductId = data.getQueryParameter("PRODUCT_ID");
            enter_source = data.getQueryParameter("channel_type");
            flag = data.getQueryParameter("PRODUCT_FLAG");
            lang = 1;
            pricessid = 13;
        } else {
            mProductId = bundle.getString(PRODUCT_ID);
            enter_source = bundle.getString(TYPE_CHANNEL);
            flag = bundle.getString(PRODUCT_FLAG);
            lang = bundle.getInt("Lang");
            pricessid = bundle.getInt("pricessid", -1);
        }
//        if(isPush==1){
//           MobclickAgent.onEvent(this,"push_product");
//           mProductId = getIntent().getStringExtra(PRODUCT_ID);
//           enter_source = getIntent().getStringExtra(TYPE_CHANNEL);
//           flag = getIntent().getStringExtra(PRODUCT_FLAG);
//           lang = getIntent().getIntExtra("Lang",1);
//           pricessid = getIntent().getIntExtra( "pricessid", -1);
//       }else{
//           mProductId = bundle.getString(PRODUCT_ID);
//           enter_source = bundle.getString(TYPE_CHANNEL);
//           flag = bundle.getString(PRODUCT_FLAG);
//           lang = bundle.getInt("Lang");
//           pricessid = bundle.getInt("pricessid", -1);
//       }


        return !TextUtils.isEmpty(mProductId);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_new_detail;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        initview();
        back.setOnClickListener(this);
        moneyedit.setOnClickListener(this);
        collectlayout.setOnClickListener(this);
        apply.setOnClickListener(this);
        share.setOnClickListener(this);

        money.setCursorVisible(false);
        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                money.setCursorVisible(true);

            }
        });
        money.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                money.setFocusable(true);
                money.setFocusableInTouchMode(true);
                money.requestFocus();
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                return false;
            }
        });

    }


    @Override
    protected void initData() {
        super.initData();
        mPresenter.start();
        productDetails = new ArrayList<>();
        //initEditContent();

    }

    @Override
    protected DetailContract.Presenter initPresenter() {
        return new DetailPresenter(this, mProductId, enter_source);
    }

    @Override
    public void onDone(ProductDetail product) {
        productDetails.add(product);
        this.mProductDetail = product;
        mProductId = product.getId();
        initProductData(product);
    }

    @Override
    public void onHorData(List<Amount> amountList) {

    }


//    @Override
//    public void onBackPressed() {
//        if (onBack())
//            super.onBackPressed();
//    }


    private boolean onBack() {
        if (productDetails.size() <= 1) {
            return true;
        } else {
            ProductDetail productDetail = productDetails.get(productDetails.size() - 2);
            initProductData(productDetail);
            productDetails.remove(productDetail);
            return false;
        }
    }

    @Override
    protected boolean isNeedNotch() {
        return true;
    }

    /**
     * 初始化数据
     *
     * @param product
     */
    private void initProductData(final ProductDetail product) {

        NoteEntity noteEntity = new NoteEntity();
        noteEntity.imgurl = product.getIcon();
        noteEntity.title = product.getName();
        noteEntity.content = product.getDescription();
        noteEntity.productid = product.getId();
        noteEntity.peoplenum = product.getApplicants();
        noteEntity.price = product.getUpper_amount() + "-" + product.getLower_amount() + "元";
        noteEntity.interest = product.getMonthly_rate() + "%";
        noteEntity.url = product.getLink();
        //保存数据库
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(DetailActivity.this);
        databaseAdapter.create(noteEntity);
        // ArrayList<NoteEntity> limit = databaseAdapter.findLimit(1, 1);
        // Log.e(TAG, "initProductData: "+limit.get(0).url );


        hideLoading();
//        Glide.with(DetailActivity.this).load(product.getIcon()).into(icon);
        GlideUtil.setImageViewCrop(this, product.getIcon(), icon);
        name.setText(product.getName());
        text.setText(product.getDescription());
        mark.setValue(Double.parseDouble(product.getStar()));
        money1.setText("金额（" + product.getUpper_amount() + "-" + product.getLower_amount() + ")");
        timer.setText("期限（" + product.getTerm() + ")");
        if (product.getIs_quality() != null) {
            if (product.getIs_quality().equals("0")) {
                quality.setVisibility(View.GONE);
            } else {
                quality.setVisibility(View.VISIBLE);
            }
        }

        obser.setText(product.getLend_time());
        num = product.getId();
        type.setText(product.getShow_day());
        if (product.getShow_day().equals("参考月利率")) {
            lilv.setText(product.getMonthly_rate() + "%");
            timetype.setText("每月还款金额");
        } else {
            lilv.setText(product.getDay_rate() + "%");
            timetype.setText("每日还款金额");
        }

        success.setText("成功放款人数" + product.getApplicants() + "人");
        cond.setText(product.getRequirements());
        datum.setText(product.getDocument());
        state.setText(Html.fromHtml(product.getExplain()));

        String applicants = product.getApplicants();
        double v = Double.parseDouble(applicants);
        double v1 = v / 10000;
        String format = String.format("%.1f", v1);
        apply.setText("立即申请 (" + format + "万人已经申请)");

//        if(product.getCustomer_service_number()!=null){
//            if(product.getCustomer_service_number().equals("")){
//                phone.setVisibility(View.GONE);
//            }else {
//                phone.setText("客服电话："+ product.getCustomer_service_number());
//
//            }
//        }
        title.setText(product.getName());
        money.setText(product.getUpper_amount() + "");
        time.setText(product.getLoan_period().get(0).getV());

        if (product.getIs_collection() == 0) {
            //未收藏
            nocollect.setImageResource(R.mipmap.noshoucang);
            iscont = true;
        } else {
            //已收藏
            iscont = false;
            nocollect.setImageResource(R.mipmap.collect);
        }
        final List<Dialogs> loan_period = product.getLoan_period();
//        final List<Dialogs> list=new ArrayList<>();
//        for (int i = 0; i < loan_period.size(); i++) {
//            list.add(new Dialogs(loan_period.get(i).k,loan_period.get(i).v));
//        }
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
                if (product.getLoan_period() != null) {
                    alertFragment = new AlertFragment();
                    alertFragment.setListener(new AlertFragment.OnSelectedListener() {
                        @Override
                        public void onSelectedItem(String k, String v) {
                            time.setText(v);
                            //选择之后重新计算
                            count(product);

                        }
                    });
                    alertFragment.setData(product.getLoan_period());
                    alertFragment.show(getSupportFragmentManager(), AlertFragment.class.getName());

                }
            }
        });

        count(product);

        //借钱的变化监听
        money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                String trim = s.toString().trim();
                if (!trim.equals("")) {
                    String s1 = time.getText().toString();
                    Pattern p = Pattern.compile("\\d+");
                    Matcher m = p.matcher(s1);
                    m.find();
                    double v2 = Double.parseDouble(m.group());
                    double v1 = Double.parseDouble(trim);
                    if (!TextUtils.isEmpty(trim)) {
                        if (product.getShow_day().equals("参考日利率")) {
                            double c = Double.parseDouble(product.getDay_rate());
                            double ben = (v1 + v1 * v2 * c / 100) / v2;
                            give.setText(String.format("%.2f", ben) + "元");
                        } else {
                            double c = Double.parseDouble(product.getMonthly_rate());
                            double ben = (v1 + v1 * v2 * c / 100) / v2;
                            give.setText(String.format("%.2f", ben) + "元");
                        }
                    }
                } else {
                    give.setText(String.format("%.2f", 0.0) + "元");
                }

            }
        });


    }


    public void count(ProductDetail product) {
        if (money.getText().toString().equals("")) {
        } else {
            double a = Double.parseDouble(money.getText().toString());
            String s = time.getText().toString();
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(s);
            m.find();
            double b = Double.parseDouble(m.group());
            if (product.getShow_day().equals("参考日利率")) {
                double c = Double.parseDouble(product.getDay_rate());
                double ben = (a + b * c * a / 100) / b;
                give.setText(String.format("%.2f", ben) + "元");
            } else {
                double c = Double.parseDouble(product.getMonthly_rate());
                double ben = (a + b * c * a / 100) / b;
                give.setText(String.format("%.2f", ben) + "元");
            }
        }
    }


    boolean flag1 = false;


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        mProductId = getIntent().getStringExtra(PRODUCT_ID);
        DetailPresenter presenter = (DetailPresenter) mPresenter;
        if (presenter != null) {
            presenter.setId(mProductId);
            presenter.start();
        }

    }

    /**
     * 获取验证码成功
     */
    @Override
    public void codeSuccess() {
        Toast.makeText(this, "验证码发送成功", Toast.LENGTH_SHORT).show();
        dgFragment.setCodeStart();
        dgFragment.setVisible(false);
    }

    /**
     * 登录成功
     */
    @Override
    public void loginSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        dgFragment.dismiss();
    }

    /**
     * 已登录状态
     */
    @Override
    public void state() {

        mPresenter.apply(mProductDetail.getId(), time.getText().toString(), money.getText().toString());
    }

    /**
     * 未登录状态
     */
    @Override
    public void stateError() {
        Boolean isExit = (Boolean) SPUtil.get(this, Constant.UserInfo.ISEXITE, true);
        if (isExit) {

            Intent intent = new Intent(DetailActivity.this, AccountActivity.class);
            startActivity(intent);
            //未登录弹框
//            dgFragment = new DgFragment();
//            dgFragment.setDialogLisener(this);
//            dgFragment.show(getFragmentManager(), "EditNameDialog");
        } else {
            LoginUtil.getInstance().reLoadLogin(new LoginUtil.OnLoadListener() {
                @Override
                public void onLoadSuccess(User user) {
                    state();
                }

                @Override
                public void onLoadFail(int strRes) {
//登录失败
//                    ToastUtil.show(DetailActivity.this,getResources().getString(strRes));
                }
            });
        }

    }

    //获取申请id
    @Override
    public void getApplyId(String id) {
        Log.e(TAG, id + "ID是");

        boolean ispop = false;

        int clickNumber = (int) SPUtil.get(getApplicationContext(), Common.Daichao.CLICKNUMBER, 0);
        String clickdate = (String) SPUtil.get(getApplicationContext(), Common.Daichao.CLICKDATEFOUR, "");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = "";
        if (clickdate.equals("")) {
            clickNumber++;
            if (clickNumber == 4) {
                SPUtil.putAndApply(getApplicationContext(), Common.Daichao.CLICKNUMBER, 0);
                date = sdf.format(d);
                SPUtil.putAndApply(getApplicationContext(), Common.Daichao.CLICKDATEFOUR, date);
                ispop = true;
            } else {
                SPUtil.putAndApply(getApplicationContext(), Common.Daichao.CLICKNUMBER, clickNumber);
            }
        } else {
            date = sdf.format(d);
            int day = DateTimeUtil.getDatedifference(clickdate, date);

            if (day >= 10) {
                SPUtil.putAndApply(getApplicationContext(), Common.Daichao.CLICKDATEFOUR, "");
            }
        }


        if (!istrue) {
            //判断取消还是收藏
            if (iscont) {
                //进行收藏
                getcollect(num);
                MobclickAgent.onEvent(DetailActivity.this, "collectClick");
            } else {
                //取消收藏
                String[] arr = new String[]{num};
                List<String> list = new ArrayList<>();
                list.add(num);
                getnocollect(list);
            }
        } else {
            if (pricessid == 1) {
                //快借
                MobclickAgent.onEvent(DetailActivity.this, "homeFastKindApply");
            } else if (pricessid == 2) {
                //最新口子
                MobclickAgent.onEvent(DetailActivity.this, "homeFreshKindApply");
            } else if (pricessid == 3) {
                //一定借到
                MobclickAgent.onEvent(DetailActivity.this, "homeMastKindApply");
            } else if (pricessid == 4) {
                //公告
                MobclickAgent.onEvent(DetailActivity.this, "noticeApply");
            } else if (pricessid == 5) {
                //viewpager
                MobclickAgent.onEvent(DetailActivity.this, "homeRecommendTurnsApply");
            } else if (pricessid == 6) {
                //banner
                MobclickAgent.onEvent(DetailActivity.this, "homeBannerApply");
            } else if (pricessid == 7) {
                //热门平台
                MobclickAgent.onEvent(DetailActivity.this, "homeHotLoanLIstApply");
            } else if (pricessid == 8) {
                //贷款列表
                MobclickAgent.onEvent(DetailActivity.this, "loanListApply");
            } else if (pricessid == 9) {
                //我的收藏列表
                MobclickAgent.onEvent(DetailActivity.this, "minecollectListApply");
            } else if (pricessid == 10) {
                //我的足迹列表
                MobclickAgent.onEvent(DetailActivity.this, "mineBrowseListApply");
            } else if (pricessid == 11) {
                //首页消息
                MobclickAgent.onEvent(DetailActivity.this, "homeNewsApply");
            } else if (pricessid == 12) {
                //我的消息
                MobclickAgent.onEvent(DetailActivity.this, "mineNewsApply");
            } else if (pricessid == 13) {
                MobclickAgent.onEvent(DetailActivity.this, "pushApply");
            }
            WebActivity.show(this, mProductDetail.getName(),
                    mProductDetail.getLink(), mProductDetail.getId(), id, "0", ispop);
        }

    }

    public void getcollect(String pos) {


        Observable<CollectBean> likeBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().getcollect(pos).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        likeBeanObservable.subscribe(new Observer<CollectBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CollectBean collectBean) {

                Toast.makeText(DetailActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                iscont = false;
                nocollect.setImageResource(R.mipmap.collect);
            }

            @Override
            public void onError(Throwable e) {


            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getnocollect(List<String> arr) {

        Observable<CancleCollectBean> collectBeanObservable = new NetRequestUtils().bucuo().getbaseretrofit().getnocollect(arr).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        collectBeanObservable.subscribe(new Observer<CancleCollectBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CancleCollectBean collectBean) {

                Toast.makeText(DetailActivity.this, "已取消", Toast.LENGTH_SHORT).show();
                iscont = true;
                nocollect.setImageResource(R.mipmap.noshoucang);

            }

            @Override
            public void onError(Throwable e) {


            }

            @Override
            public void onComplete() {

            }
        });
    }


    // 隐藏软件盘
    private void hideSoftKeyboard() {
        // 当前焦点的View
        View view = getCurrentFocus();
        if (view == null)
            return;

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    /**
     * 创建底部dialog
     *
     * @param
     * @return Dialog
     */
//    public void createDialog(final List<Dialogs> data) {
//        AlertFragment alertFragment = new AlertFragment();
//        alertFragment.setListener(new AlertFragment.OnSelectedListener() {
//            @Override
//            public void onSelectedItem(String k, String v) {
//                time.setText(v);
//                //选择之后重新计算
//                count();
//
//            }
//        });
//        alertFragment.setData(data);
//        alertFragment.show(getSupportFragmentManager(), AlertFragment.class.getName());
//    }
    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

//    //立即申请
//    @SuppressLint("WrongConstant")
//    @OnClick(R.id.btn_submit)
//    void Submit() {
//        if(!flag1){
//            return;
//        }
//        Log.e("点击", "ssa");
//
//        mPresenter.loginState();
//        //WebActivity.show(this,mProductName,mProductUrl);
//    }

    //   dialog 的点击
    @Override
    public void dialog(String phone, String code) {
        mPresenter.login(phone, code);
    }

    /**
     * 验证码提交
     *
     * @param phone
     * @param smstype
     */
    @Override
    public void codePush(String phone, String smstype) {
        mPresenter.codePush(phone, smstype);
    }

    //对话框的登录
    @Override
    public void dialogLogin(String phone, String password) {
        mPresenter.loginP(phone, password);
    }

    //语音登录
    @Override
    public void LoginSound(String phone) {
        mPresenter.SoundCode(phone);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        hideSoftKeyboard();
        if (onBack()) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                finish();
            }
            if (!TextUtils.isEmpty(flag)) {
                MainActivity.show(this);
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detal_back:
                InputMethodManager imm2 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm2.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                finish();
                if (!TextUtils.isEmpty(flag)) {
                    MainActivity.show(this);
                }
                break;
            case R.id.money_edit:
                money.requestFocus();
                if (alertFragment != null) {
                    alertFragment.dismiss();
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(money, 0);
                String s = money.getText().toString();
                money.setSelection(s.length());
                break;
            case R.id.collect_layout:
                //收藏与取消收藏   先判断是否登录
                istrue = false;
                mPresenter.loginState();
                break;
            case R.id.apply_success:
                istrue = true;
                mPresenter.loginState();
                break;
            case R.id.share:
                //显示分享列表
                getsharepopwindow();
                break;

        }
    }

    public void getsharepopwindow() {

        View inflate = LayoutInflater.from(DetailActivity.this).inflate(R.layout.activity_share_popup, null, false);
        final PopupWindow popupWindow = new PopupWindow(inflate, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.pop_anim);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
        light(0.8f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                light(1.0f);
            }
        });

        wei_circle = (LinearLayout) inflate.findViewById(R.id.m_share_pop_up_wei_circle);
        wei_chat = (LinearLayout) inflate.findViewById(R.id.m_share_pop_up_wei_chat);
        cancle = (TextView) inflate.findViewById(R.id.m_share_pop_up_cancel);

        //朋友圈
        wei_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        wei_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }

    public void light(float t) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = t;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
