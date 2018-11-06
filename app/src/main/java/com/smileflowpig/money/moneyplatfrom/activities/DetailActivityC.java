package com.smileflowpig.money.moneyplatfrom.activities;

import com.smileflowpig.money.moneyplatfrom.frags.dialog.DgFragment;

import java.util.List;

import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.factory.model.api.product.ProductDetail;
import com.smileflowpig.money.factory.model.db.Amount;
import com.smileflowpig.money.factory.presenter.detail.DetailContract;

/**
 * 产品详情页2
 * 由于产品的要求和某些机型的适配原因，当地二次
 * 跳转的时候，跳入的是此activity。后续有机会希望优化掉
 */
public class DetailActivityC extends PresenterActivity<DetailContract.Presenter>
        implements DetailContract.View, DgFragment.DialogClick {
    private static final String TAG = "DetailActivity";
    //产品ID
    public static final String PRODUCT_ID = "PRODUCT_ID";
    //标记，用来区分由谁进入详情页，带flag为AdActivity页进入
    public static final String PRODUCT_FLAG = "PRODUCT_FLAG";
    public static final String FLAG = "1";

    //未登陆弹框
    DgFragment dgFragment;
    //产品Id
    private String mProductId;
    //产品Name
    private String mProductName;
    //产品icon
    private String mProductIcon;
    //产品申请人数
    private String mProductNum;
    //产品URL
    private String mProductUrl;

    @Override
    public void onDone(ProductDetail product) {

    }

    @Override
    public void onHorData(List<Amount> amountList) {

    }

    @Override
    public void codeSuccess() {

    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void state() {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void getApplyId(String id) {

    }

    @Override
    protected DetailContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
        return 0;
    }

    @Override
    public void dialog(String phone, String code) {

    }

    @Override
    public void codePush(String phone, String smstype) {

    }

    @Override
    public void dialogLogin(String phone, String password) {

    }

    @Override
    public void LoginSound(String phone) {

    }

//    @BindView(R.id.tv_title)
//    TextView tv_title;
//
//    @BindView(R.id.im_portrait)
//    PortraitView im_portrait;
//
//    @BindView(R.id.tv_num)
//    TextView tv_num;
//
//    @BindView(R.id.tv_product_name)
//    TextView tv_product_name;
//
//    @BindView(R.id.loanlimit_tv)
//    TextView loanlimit_tv;
//
//    @BindView(R.id.edit_money)
//    EditText edit_money;
//
//    @BindView(R.id.tv_fasttime)
//    TextView tv_fasttime;
//
//    @BindView(R.id.tv_period)
//    TextView tv_period;
//
//    @BindView(R.id.InstallmentPeriod_tv)
//    TextView InstallmentPeriod_tv;
//
////    @BindView(R.id.show_day)
////    TextView show_day;
//
//    @BindView(R.id.tv_rate)
//    TextView tv_rate;
//
//    @BindView(R.id.conditions_tv)
//    TextView conditions_tv;
//
//    @BindView(R.id.tv_sum)
//    TextView tv_sum;
//
//    @BindView(R.id.grid)
//    GridView gridView;
//
//    @BindView(R.id.data_needed_tv)
//    TextView data_needed_tv;
//
//    @BindView(R.id.detailed_description_tv)
//    TextView detailed_description_tv;
//
//    @BindView(R.id.change_lin)
//    LinearLayout change_lin;
//
//    @BindView(R.id.need_lin)
//    LinearLayout need_lin;
//
//    @BindView(R.id.an_text)
//    TextView an_text;
//
//    @BindView(R.id.an_image)
//    ImageView an_image;
//
//    @BindView(R.id.bottom_lin)
//    LinearLayout bottom_lin;
//
//    private String flag;
//
//    private boolean show = false;
//
//    ProductDetail mProductDetail;
//
//    ListView dialog_lv;
//
//
//    @BindView(R.id.top_lin)
//    LinearLayout top_lin;
//
//    private Integer start[] = {R.id.start1, R.id.start2,
//            R.id.start3, R.id.start4, R.id.start5};

//    /**
//     * 产品详情页
//     *
//     * @param context context
//     * @param product 商品信息
//     */
//    public static void show(Context context, Product product) {
//        if (product == null || context == null || TextUtils.isEmpty(product.getId()))
//            return;
//        Intent intent = new Intent(context, DetailActivityC.class);
//        intent.putExtra(PRODUCT_ID, product.getId());
//
//        context.startActivity(intent);
//    }
//
//    /**
//     * 产品详情页
//     *
//     * @param context context
//     */
//    public static void show(Context context, String id) {
//
//        Intent intent = new Intent(context, DetailActivityC.class);
//        intent.putExtra(PRODUCT_ID, id);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
//
//
//    }
//
//
//    /**
//     * 产品详情页
//     *
//     * @param context context
//     */
//    public static void show(Context context, String id, String flag) {
//        if (id == null || context == null)
//            return;
//        Intent intent = new Intent(context, DetailActivity.class);
//        intent.putExtra(PRODUCT_ID, id);
//        intent.putExtra(PRODUCT_FLAG, flag);
//
//        context.startActivity(intent);
//    }
//
//    @Override
//    protected boolean initArgs(Bundle bundle) {
//
//        mProductId = bundle.getString(PRODUCT_ID);
////        Log.e(TAG,mProductId);
//
//        flag = bundle.getString(PRODUCT_FLAG);
//
//        return !TextUtils.isEmpty(mProductId);
//
//    }
//
//    //常见问题点击
//    @OnClick(R.id.callphone)
//    void WhyClick() {
//
//
//
//
//    }
//
//    @Override
//    protected int getContentLayoutId() {
//        return R.layout.activity_detail;
//    }
//
//    @Override
//    protected void initWidget() {
//        super.initWidget();
//        edit_com.setCursorVisible(false);
//        edit_com.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                edit_com.setCursorVisible(true);
//
//            }
//        });
//        int start = DensityUtil.dip2px(this, 68);
//        LinearLayout.LayoutParams linearParams =
//                (LinearLayout.LayoutParams) change_lin.getLayoutParams();
//
//        linearParams.height = start;
//        change_lin.setLayoutParams(linearParams);
//    }
//
//
//    @Override
//    protected void initData() {
//        super.initData();
//
//        mPresenter.start();
//
//
//        initEditContent();
//
//    }
//
//    @Override
//    protected DetailContract.Presenter initPresenter() {
//        return new DetailPresenter(this, mProductId);
//    }
//
//    @Override
//    public void onDone(ProductDetail product) {
//        this.mProductDetail = product;
//        hideLoading();
//
//        int startCount = Integer.parseInt(product.getStar());
//        for (int i = 0; i < startCount; i++) {
//            ImageView im = (ImageView) findViewById(start[i]);
//            im.setImageResource(R.drawable.start);
//        }
//        loanlimit_tv.setText("(" + product.getUpper_amount() + "-" + product.getLower_amount() + ")");
//        mProductUrl = product.getLink();
//        edit_com.setText(product.getUpper_amount() + "");
//        tv_fasttime.setText(product.getLend_time());
//
//        if (product.getTerm().length() > 2) {
//            tv_period.setText("(" + product.getTerm() + ")");
//        } else {
//            tv_period.setText("");
//        }
//        InstallmentPeriod_tv.setText(product.getLoan_period().get(0).getV());
//
////        show_day.setText(product.getShow_day());
//        if (product.getShow_day().equals("参考日利率")) {
//            tv_rate.setText(product.getDay_rate() + "%");
//            double a = Double.parseDouble(edit_com.getText().toString());
//            double b = Double.parseDouble(product.getDay_rate());
//            double ben = a + b * product.getUpper_term() * a / 100;
//            tv_sum.setText(String.format("%.0f", ben) + "元");
//        } else {
//            tv_rate.setText(product.getMonthly_rate() + "%");
//            double a = Double.parseDouble(edit_com.getText().toString());
//            double b = Double.parseDouble(product.getMonthly_rate());
//            Log.e(TAG, product.getUpper_term() + "ssssssss");
//            double ben = a + b * product.getUpper_term() * a / 100 / 30;
//            tv_sum.setText(String.format("%.0f", ben) + "元");
//        }
//        conditions_tv.setText(product.getRequirements());
//        data_needed_tv.setText(product.getDocument());
//        detailed_description_tv.setText(Html.fromHtml(product.getExplain()));
//
//        tv_title.setText(product.getName());
//
//        im_portrait.setup(Glide.with(this), product.getIcon());
//
//        tv_num.setText(product.getApplicants() + "人成功申请");
//
//        tv_product_name.setText(product.getName());
//    }
//
//    @Override
//    public void onHorData(final List<Amount> amountList) {
//        int size = amountList.size();
//        int length = 80;
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        float density = dm.density;
//        int gridviewWidth = (int) (size * (length + 4) * density);
//        int itemWidth = (int) (length * density);
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
//        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
//        gridView.setColumnWidth(itemWidth); // 设置列表项宽
//        gridView.setHorizontalSpacing(2); // 设置列表项水平间距
//        gridView.setStretchMode(GridView.NO_STRETCH);
//        gridView.setNumColumns(size); // 设置列数量=列表集合数
//
//        GridViewAdapter adapter = new GridViewAdapter(getApplicationContext(),
//                amountList);
//        gridView.setAdapter(adapter);
//        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                DetailActivityC.show(DetailActivityC.this,
//                        amountList.get(position).getId());
//                finish();
//            }
//        });
//    }
//
//    @Override
//    public void codeSuccess() {
//        Toast.makeText(this, "验证码发送成功", Toast.LENGTH_SHORT).show();
//        dgFragment.setCodeStart();
//        dgFragment.setVisible(false);
//    }
//
//    @Override
//    public void loginSuccess() {
//        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
//        dgFragment.dismiss();
//    }
//
//    @Override
//    public void state() {
//        mPresenter.apply(mProductDetail.getId(), edit_com.getText().toString(), InstallmentPeriod_tv.getText().toString());
//
//    }
//
//    @Override
//    public void stateError() {
//        Boolean isExit = (Boolean) SPUtil.get(this, Constant.UserInfo.ISEXITE, true);
//        if (isExit) {
//            dgFragment = new DgFragment();
//            dgFragment.setDialogLisener(this);
//            dgFragment.show(getFragmentManager(), "EditNameDialog");
//        } else {
//            LoginUtil.getInstance().reLoadLogin(new LoginUtil.OnLoadListener() {
//                @Override
//                public void onLoadSuccess(User user) {
//                    state();
//                }
//
//                @Override
//                public void onLoadFail(int strRes) {
////登录失败
////                    ToastUtil.show(DetailActivityC.this,getResources().getString(strRes));
//                }
//            });
//        }
//
//    }
//
//    @Override
//    public void getApplyId(String id) {
//        Log.e(TAG, id + "ID是");
//        WebActivity.show(this, mProductDetail.getName(),
//                mProductDetail.getLink(), mProductDetail.getId(), id, "0");
//
//    }
//
//
//    @OnClick(R.id.bpoint)
//    void bPoint() {
//        if (mProductDetail.getLoan_period() != null) {
//            createDialog(mProductDetail.getLoan_period());
//        } else {
//            Toast.makeText(this, "火星出问题了", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//
//    /**
//     * 查看更多点击事件
//     */
//    @OnClick(R.id.lin_more)
//    void loadMore() {
//        performAnim2(new Runnable() {
//            @Override
//            public void run() {
//                if (show) {
//                    //显示view，高度从0变到height值
//                    an_text.setText("收起");
//                    an_image.setImageResource(R.drawable.point6);
//                } else {
//                    //隐藏view，高度从height变为0
//
//                    an_text.setText("查看更多");
//                    an_image.setImageResource(R.drawable.bpoint);
//                }
//            }
//        });
//    }
//
//
//    // 隐藏软件盘
//    private void hideSoftKeyboard() {
//        // 当前焦点的View
//        View view = getCurrentFocus();
//        if (view == null)
//            return;
//
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }
//
//    /**
//     * 查看更多动画
//     *
//     * @param endCallback 动画结束回调
//     */
//    private void performAnim2(final Runnable endCallback) {
//        int start = DensityUtil.dip2px(this, 85);
//        Display display = this.getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getSize(size);
//        need_lin.measure(size.x, size.y);
//        bottom_lin.measure(size.x, size.y);
//        int end = need_lin.getMeasuredHeight();
//        int end2 = bottom_lin.getMeasuredHeight();
//        int end3 = top_lin.getMeasuredHeight();
//        //View是否显示的标志
//        show = !show;
//        //属性动画对象
//        ValueAnimator va;
//        if (show) {
//            //显示view，高度从0变到height值
//            va = ValueAnimator.ofInt(start, end + end2 + end3);
//        } else {
//            //隐藏view，高度从height变为0
//            va = ValueAnimator.ofInt(end + end2 + end3, start);
//        }
//        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator valueAnimator) {
//                //获取当前的height值
//                int h = (Integer) valueAnimator.getAnimatedValue();
//                //动态更新view的高度
//                change_lin.getLayoutParams().height = h;
//                change_lin.requestLayout();
//            }
//        });
//
//        va.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                // 结束时触发
//                endCallback.run();
//            }
//        });
//        va.setInterpolator(new AccelerateInterpolator());
//        va.setDuration(300);
//        //开始动画
//        va.start();
//    }
//
//
//    // 初始化输入框监听
//    private void initEditContent() {
//        edit_com.addTextChangedListener(new TextWatcherAdapter() {
//            @Override
//            public void afterTextChanged(Editable s) {
//                countSum(s.toString());
//            }
//        });
//
//
//    }
//
//
//    /**
//     * 粗陋的计算封装
//     *
//     * @param s
//     */
//    private void countSum(String s) {
//        if (s.toString().equals("")) {
//            tv_sum.setText("0元");
//            return;
//        }
//        double a = Integer.parseInt(s);
//        if (isDay()) {
//            if (a > mProductDetail.getLower_amount()) {
//                a = mProductDetail.getLower_amount();
//                edit_com.setText(String.format("%.0f", a));
//            }
//            tv_sum.setText(count(a));
//        }
//    }
//
//    /**
//     * 计算
//     *
//     * @param a
//     * @return 格式化后的计算结果
//     */
//    public String count(double a) {
//        double b = Double.parseDouble(mProductDetail.getDay_rate());
//        double ben = a + b * mProductDetail.getUpper_term() * a / 100;
//        return String.format("%.0f", ben) + "元";
//    }
//
//    /**
//     * 是否是按日利率计算
//     *
//     * @return
//     */
//    public boolean isDay() {
//        return (!TextUtils.isEmpty(mProductDetail.getShow_day())) && mProductDetail.getShow_day().equals("参考日利率");
//    }
//
//    /**
//     * 创建底部dialog
//     *
//     * @param data 创建所需数据
//     * @return Dialog
//     */
//    public void createDialog(final List<Dialogs> data) {
//        AlertFragment alertFragment = new AlertFragment();
//        alertFragment.setListener(new AlertFragment.OnSelectedListener() {
//            @Override
//            public void onSelectedItem(String k, String v) {
//                InstallmentPeriod_tv.setText(v);
//                count(k);
//            }
//        });
//        alertFragment.setData(data);
//        alertFragment.show(getSupportFragmentManager(), AlertFragment.class.getName());
//    }
//
//    //顶部导航栏返回键点击事件
//    @OnClick(R.id.back)
//    void back() {
//        hideSoftKeyboard();
//        finish();
//        if (!TextUtils.isEmpty(flag)) {
//            MainActivity.show(this);
//        }
//    }
//
//    public void count(String k) {
//        double a = Double.parseDouble(edit_com.getText().toString());
//        double b = Double.parseDouble(mProductDetail.getDay_rate());
//        double c = Double.parseDouble(k);
//        double ben = a + b * c * a / 100;
//        tv_sum.setText(String.format("%.0f", ben) + "元");
//    }
//
//    @OnClick(R.id.btn_submit)
//    void Submit() {
//        mPresenter.loginState();
//
//
////        WebActivity.show(this,mProductName,mProductUrl);
//    }
//
//    @Override
//    public void dialog(String phone, String code) {
//        mPresenter.login(phone, code);
//    }
//
//    @Override
//    public void codePush(String phone, String smstype) {
//        mPresenter.codePush(phone, smstype);
//    }
//
//    @Override
//    public void dialogLogin(String phone, String password) {
//        mPresenter.loginP(phone, password);
//    }
//
//    @Override
//    public void LoginSound(String phone) {
//        mPresenter.SoundCode(phone);
//    }
}
