package money.kuxuan.platform.moneyplatfrom.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoRelativeLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import money.kuxuan.platform.common.Common;
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.common.utils.DateTimeUtil;
import money.kuxuan.platform.common.utils.DisplayUtil;
import money.kuxuan.platform.common.widget.PortraitView;
import money.kuxuan.platform.common.widget.TextWatcherAdapter;
import money.kuxuan.platform.factory.Constant;
import money.kuxuan.platform.factory.model.api.product.ProductDetail;
import money.kuxuan.platform.factory.model.db.Amount;
import money.kuxuan.platform.factory.model.db.Dialogs;
import money.kuxuan.platform.factory.model.db.Product;
import money.kuxuan.platform.factory.model.db.User;
import money.kuxuan.platform.factory.presenter.detail.DetailContract;
import money.kuxuan.platform.factory.presenter.detail.DetailPresenter;
import money.kuxuan.platform.factory.util.LoginUtil;
import money.kuxuan.platform.factory.util.SPUtil;
import money.kuxuan.platform.moneyplatfrom.Adapter.GridViewAdapter;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.frags.AlertFragment;
import money.kuxuan.platform.moneyplatfrom.frags.dialog.DgFragment;
import money.kuxuan.platform.moneyplatfrom.helper.DensityUtil;
import money.kuxuan.platform.moneyplatfrom.util.ToastUtil;
import money.kuxuan.platform.moneyplatfrom.web.WebActivity;

//产品详情页
public class DetailActivity extends PresenterActivity<DetailContract.Presenter>
        implements DetailContract.View, DgFragment.DialogClick {
    private static final String TAG = "DetailActivity";
    //产品ID
    public static final String PRODUCT_ID = "PRODUCT_ID";
    //标记，用来区分由谁进入详情页，带flag为AdActivity页进入
    public static final String PRODUCT_FLAG = "PRODUCT_FLAG";
    public static final String TYPE_CHANNEL = "channel_type";
    public static final String FLAG = "1";

    //未登陆弹框
    DgFragment dgFragment;
    //产品Id
    private String mProductId;
    //入口
    private String enter_source;
    //产品Name
    private String mProductName;
    //产品icon
    private String mProductIcon;
    //产品申请人数
    private String mProductNum;
    //产品URL
    private String mProductUrl;

    private ArrayList<ProductDetail> productDetails;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.res_data)
    AutoRelativeLayout res_data;

    @BindView(R.id.im_portrait)
    PortraitView im_portrait;

    @BindView(R.id.tv_num)
    TextView tv_num;

    @BindView(R.id.tv_product_name)
    TextView tv_product_name;

    @BindView(R.id.tv_monthlyrate)
    TextView tv_monthlyrate;

    @BindView(R.id.loanlimit_tv)
    TextView loanlimit_tv;

    @BindView(R.id.edit_money)
    EditText edit_money;

    @BindView(R.id.tv_fasttime)
    TextView tv_fasttime;

    @BindView(R.id.tv_period)
    TextView tv_period;

    @BindView(R.id.InstallmentPeriod_tv)
    TextView InstallmentPeriod_tv;

    @BindView(R.id.data_tv)
    TextView data_tv;

//    @BindView(R.id.show_day)
//    TextView show_day;

    @BindView(R.id.tv_rate)
    TextView tv_rate;

    @BindView(R.id.conditions_tv)
    TextView conditions_tv;

    @BindView(R.id.tv_sum)
    TextView tv_sum;

    @BindView(R.id.grid)
    GridView gridView;

    @BindView(R.id.data_needed_tv)
    TextView data_needed_tv;

    @BindView(R.id.detailed_description_tv)
    TextView detailed_description_tv;

    @BindView(R.id.change_lin)
    LinearLayout change_lin;

    @BindView(R.id.need_lin)
    LinearLayout need_lin;

    @BindView(R.id.an_text)
    TextView an_text;

    @BindView(R.id.an_image)
    ImageView an_image;

    @BindView(R.id.bottom_lin)
    LinearLayout bottom_lin;

    private String flag;

    private boolean show = false;

    ProductDetail mProductDetail;


    ListView dialog_lv;
    @BindView(R.id.top_lin)
    LinearLayout top_lin;
    @BindView(R.id.lin_more)
    LinearLayout lin_more;


    private Integer start[] = {R.id.start1, R.id.start2,
            R.id.start3, R.id.start4, R.id.start5};
    private TextView tv_phone;
    private TextView phonename;
    private View view2;
    private ImageView callphone;
    private int lang;
    private double data;

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
    public static void show(Context context, String id, String type_channel,int num) {

        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(PRODUCT_ID, id);
        intent.putExtra(TYPE_CHANNEL, type_channel);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("Lang",num);
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

        mProductId = bundle.getString(PRODUCT_ID);
//        Log.e(TAG,mProductId);
        enter_source = bundle.getString(TYPE_CHANNEL);
        flag = bundle.getString(PRODUCT_FLAG);
        lang = bundle.getInt("Lang");
        return !TextUtils.isEmpty(mProductId);

    }

    //常见问题点击
    @OnClick(R.id.callphone)
    void callPhone() {


        showDialog();

    }

    AlertDialog alertDialog;
    public void showDialog(){

        callphone.setEnabled(false);
        view2 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.callphone_dialog,null);
        phonename = (TextView) view2.findViewById(R.id.phone_name);
        tv_phone = (TextView) view2.findViewById(R.id.tv_phone);
        phonename.setText(mProductDetail.getName()+"客服电话");
        tv_phone.setText(mProductDetail.getCustomer_service_number()+"");
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        WindowManager.LayoutParams  lp= alertDialog.getWindow().getAttributes();
        int width = DisplayUtil.dip2px(264);
        int height = DisplayUtil.dip2px(130);
        lp.width=width;//定义宽度
        lp.height=height;//定义高度
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.update_border);
        alertDialog.getWindow().setAttributes(lp);
        Window window = alertDialog.getWindow();

        AutoRelativeLayout rel_next = (AutoRelativeLayout) view2.findViewById(R.id.rel_next);

        rel_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(alertDialog!=null&&alertDialog.isShowing())
                    alertDialog.dismiss();
                callphone.setEnabled(true);
            }
        });


        AutoRelativeLayout rel_good = (AutoRelativeLayout) view2.findViewById(R.id.rel_good);
        rel_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callphone.setEnabled(true);
                TextView textView = (TextView) view2.findViewById(R.id.tv_phone);
                String phoneNum = textView.getText().toString().trim();

                if(phoneNum!=null&&!phoneNum.equals("")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + phoneNum);
                    intent.setData(data);
                    startActivity(intent);
                    alertDialog.dismiss();
                }else {
                    ToastUtil.show(getApplicationContext(),"手机号有问题");
                    alertDialog.dismiss();
                }


            }
        });

        window.setContentView(view2);



    }



    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        edit_money.setCursorVisible(false);
        edit_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_money.setCursorVisible(true);

            }
        });
        edit_money.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                edit_money.setFocusable(true);
                edit_money.setFocusableInTouchMode(true);
                edit_money.requestFocus();
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                return false;
            }
        });
        //设置查看更多的高度
        int start = DensityUtil.dip2px(this, 68);
        LinearLayout.LayoutParams linearParams =
                (LinearLayout.LayoutParams) change_lin.getLayoutParams();

        linearParams.height = start;
        change_lin.setLayoutParams(linearParams);


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
        mProductId =product.getId();
        initProductData(product);
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

    /**
     * 初始化数据
     *
     * @param product
     */
    private void initProductData(final ProductDetail product) {

        callphone = (ImageView) findViewById(R.id.callphone);
        hideLoading();
        int startCount = Integer.parseInt(product.getStar());
        for (int i = 0; i < startCount; i++) {
            ImageView im = (ImageView) findViewById(start[i]);
            im.setImageResource(R.drawable.start);
        }
        loanlimit_tv.setText(product.getUpper_amount() + "-" + product.getLower_amount());

        if(!product.getCustomer_service_number().equals("")){

            callphone.setVisibility(View.VISIBLE);

        }else {
            callphone.setVisibility(View.GONE);
        }
        mProductUrl = product.getLink();
        edit_money.setText(product.getUpper_amount() + "");

        tv_fasttime.setText(product.getLend_time());


        if (product.getTerm().length() > 2) {
            tv_period.setText( product.getTerm());
        } else {
            tv_period.setText("");
        }
        String v = product.getLoan_period().get(0).getV();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(v);
        m.find();
        InstallmentPeriod_tv.setText(m.group());

        String group = m.group();
        data = Double.parseDouble(group);

        boolean contains = v.contains("天");
        boolean contains1 = v.contains("月");
        if(contains){
            data_tv.setText("天");
        }
        if(contains1){
            data_tv.setText("月");
        }
        tv_monthlyrate.setText(product.getShow_day());

//        show_day.setText(product.getShow_day());
        if (product.getShow_day().equals("参考日利率")) {
            tv_rate.setText(product.getDay_rate() + "%");
            double c = Double.parseDouble(product.getDay_rate());
            double a = Double.parseDouble(edit_money.getText().toString());
            double b = data;
            double ben = (a*b*c/100)+a;
            tv_sum.setText(String.format("%.2f", ben) + "元");
        } else {
            tv_rate.setText(product.getMonthly_rate() + "%");
            double a = Double.parseDouble(edit_money.getText().toString());
            double b = Double.parseDouble(product.getMonthly_rate());
            double c=data;
            Log.e(TAG, product.getUpper_term() + "ssssssss");
            double ben = a + b * c * a / 100;
            tv_sum.setText(String.format("%.2f", ben) + "元");
        }
        conditions_tv.setText(product.getRequirements());
        data_needed_tv.setText(product.getDocument());
        detailed_description_tv.setText(Html.fromHtml(product.getExplain()));

        edit_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                    String trim = s.toString().trim();
                    if(!trim.equals("")){
                        String s1 = InstallmentPeriod_tv.getText().toString();
                        double v2 = Double.parseDouble(s1);
                        double v1 = Double.parseDouble(trim);
                        if(!TextUtils.isEmpty(trim)){
                            if (product.getShow_day().equals("参考日利率")) {
                                double c = Double.parseDouble(product.getDay_rate());
                                double ben = v1+v1*v2*c/100;
                                tv_sum.setText(String.format("%.2f", ben) + "元");
                            } else {
                                double c = Double.parseDouble(product.getMonthly_rate());
                                Log.e(TAG, product.getUpper_term() + "ssssssss");
                                double ben = v1+v1*v2*c/100;
                                tv_sum.setText(String.format("%.2f", ben) + "元");
                            }
                        }
                    }else {
                        tv_sum.setText(String.format("%.2f", 0.0) + "元");
                    }

                }



        });

        tv_title.setText(product.getName());

        im_portrait.setup(Glide.with(this), product.getIcon());

        tv_num.setText(product.getApplicants() + "人成功申请");

        tv_product_name.setText(product.getName());


        if(product.getStatus().equals("1")){
            flag1 = true;
        }


        if(!flag1){
            btn_submit.setTextColor(Color.WHITE);
            btn_submit.setText("已下架");
            btn_submit.setBackgroundResource(R.drawable.bu_gray_bg);
        }else {
//            if(lang==2){
//                btn_submit.setText("我要还款");
//                btn_submit.setTextColor(Color.WHITE);
//                btn_submit.setBackgroundResource(R.drawable.bu_yellow_bg);
//            }else {
                btn_submit.setText("立即申请");
                btn_submit.setTextColor(Color.WHITE);
                btn_submit.setBackgroundResource(R.drawable.bu_yellow_bg);
//            }

        }


    }


    @BindView(R.id.btn_submit)
    Button btn_submit;

    boolean flag1 = false;

    /**
     * 推荐产品的回调
     *
     * @param amountList
     */
    @Override
    public void onHorData(final List<Amount> amountList) {
        int size = amountList.size();
        int length = 80;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(2); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数

        GridViewAdapter adapter = new GridViewAdapter(getApplicationContext(),
                amountList);
        gridView.setAdapter(adapter);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mProductId = amountList.get(position).getId();
                DetailActivity.show(DetailActivity.this,
                        amountList.get(position).getId(), enter_source,0);

            }
        });
    }

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

        mPresenter.apply(mProductDetail.getId(), InstallmentPeriod_tv.getText().toString(),edit_money.getText().toString());

    }

    /**
     * 未登录状态
     */
    @Override
    public void stateError() {
        Boolean isExit = (Boolean) SPUtil.get(this, Constant.UserInfo.ISEXITE, true);
        if (isExit) {
            dgFragment = new DgFragment();
            dgFragment.setDialogLisener(this);
            dgFragment.show(getFragmentManager(), "EditNameDialog");
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

        int clickNumber = (int) SPUtil.get(getApplicationContext(), Common.Daichao.CLICKNUMBER,0);
        String clickdate = (String) SPUtil.get(getApplicationContext(),Common.Daichao.CLICKDATEFOUR,"");
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date="";
        if(clickdate.equals("")){
            clickNumber++;
            if(clickNumber==4){
                SPUtil.putAndApply(getApplicationContext(),Common.Daichao.CLICKNUMBER,0);
                date = sdf.format(d);
                SPUtil.putAndApply(getApplicationContext(),Common.Daichao.CLICKDATEFOUR,date);
                ispop = true;
            }else {
                SPUtil.putAndApply(getApplicationContext(),Common.Daichao.CLICKNUMBER,clickNumber);
            }
        }else {
            date = sdf.format(d);
            int day = DateTimeUtil.getDatedifference(clickdate,date);

            if(day>=10){
                SPUtil.putAndApply(getApplicationContext(),Common.Daichao.CLICKDATEFOUR,"");
            }
        }

        WebActivity.show(this, mProductDetail.getName(),
                mProductDetail.getLink(), mProductDetail.getId(), id, "0",ispop);

    }


    // 底部弹出的dialog
    @OnClick(R.id.res_data)
    void bPoint() {
        if (mProductDetail.getLoan_period() != null) {
            createDialog(mProductDetail.getLoan_period());
        } else {
            Toast.makeText(this, "火星出问题了", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 查看更多点击事件
     */
    @OnClick(R.id.lin_more)
    void loadMore() {
        performAnim2(new Runnable() {
            @Override
            public void run() {
                if (show) {
                    //显示view，高度从0变到height值
                    an_text.setText("收起");
                    an_image.setImageResource(R.drawable.point6);
                    LinearLayout.LayoutParams linearParams =
                            (LinearLayout.LayoutParams) change_lin.getLayoutParams();

                    linearParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    change_lin.setLayoutParams(linearParams);
                    Log.e(TAG, linearParams.height + "-----------------------------");
                } else {
                    //隐藏view，高度从height变为0
                    an_text.setText("查看更多");
                    an_image.setImageResource(R.drawable.bpoint);
                }
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
     * 查看更多动画
     *
     * @param endCallback 动画结束回调
     */
    private void performAnim2(final Runnable endCallback) {
        int start = DensityUtil.dip2px(this, 68);
        Display display = this.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        change_lin.measure(size.x, size.y);
        final int end = change_lin.getMeasuredHeight();
        Log.e(TAG, "end3" + end);


        //View是否显示的标志
        show = !show;
        //属性动画对象
        ValueAnimator va;
        if (show) {
            //显示view，高度从0变到height值
            va = ValueAnimator.ofInt(start, end);

        } else {
            //隐藏view，高度从height变为0
            va = ValueAnimator.ofInt(end, start);
        }
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取当前的height值
                int h = (Integer) valueAnimator.getAnimatedValue();
                //动态更新view的高度
                change_lin.getLayoutParams().height = h;
                change_lin.requestLayout();
                Log.e(TAG, change_lin.getMeasuredHeight() + "donghuajianitng");
            }
        });
        va.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 结束时触发
                endCallback.run();
            }
        });
        va.setInterpolator(new AccelerateInterpolator());
        va.setDuration(300);
        //开始动画
        va.start();
    }


    // 初始化输入框监听
    private void initEditContent() {
        edit_money.addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                countSum(s.toString());
            }
        });


    }


    /**
     * 粗陋的计算封装
     *
     * @param s
     */
    private void countSum(String s) {
        if (s.toString().equals("")) {
            tv_sum.setText("0.00元");
            return;
        }
        double a = Integer.parseInt(s);
//        if (isDay()) {
//            if (a > mProductDetail.getLower_amount()) {
//                a = mProductDetail.getLower_amount();
//                edit_money.setText(String.format("%.0f", a));
//            }
//            tv_sum.setText(count(a));
//        }
    }

    /**
     * 计算
     *
     * @param a
     * @return 格式化后的计算结果
     */
    public String count(double a) {
        double b = Double.parseDouble(mProductDetail.getDay_rate());
        double ben = a + b * mProductDetail.getUpper_term() * a / 100;
        return String.format("%.2f", ben) + "元";
    }

    /**
     * 是否是按日利率计算
     *
     * @return
     */
    public boolean isDay() {
        return (!TextUtils.isEmpty(mProductDetail.getShow_day()))
                && mProductDetail.getShow_day().equals("参考日利率");
    }

    /**
     * 创建底部dialog
     *
     * @param data 创建所需数据
     * @return Dialog
     */
    public void createDialog(final List<Dialogs> data) {
        AlertFragment alertFragment = new AlertFragment();
        alertFragment.setListener(new AlertFragment.OnSelectedListener() {
            @Override
            public void onSelectedItem(String k, String v) {
                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(v);
                m.find();
                InstallmentPeriod_tv.setText(m.group());
                count(k);

            }
        });
        alertFragment.setData(data);
        alertFragment.show(getSupportFragmentManager(), AlertFragment.class.getName());
    }

    //顶部导航栏返回键点击事件gdd
    @OnClick(R.id.back)
    void back() {
        hideSoftKeyboard();
        if (onBack()) {
            finish();
            if (!TextUtils.isEmpty(flag)) {
                MainActivity.show(this);
            }
        }

    }

    public void count(String k) {
          if(edit_money.getText().toString().equals("")){
          }else {
              double a = Double.parseDouble(edit_money.getText().toString());
              double b = Double.parseDouble(InstallmentPeriod_tv.getText().toString());
              if (mProductDetail.getShow_day().equals("参考日利率")) {
                  double c = Double.parseDouble(mProductDetail.getDay_rate());
                  double ben = a + b * c * a / 100;
                  tv_sum.setText(String.format("%.2f", ben) + "元");
              } else {
                  double c = Double.parseDouble(mProductDetail.getMonthly_rate());
                  Log.e(TAG, mProductDetail.getUpper_term() + "ssssssss");
                  double ben = a + b * c * a / 100;
                  tv_sum.setText(String.format("%.2f", ben) + "元");
              }
          }

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    //立即申请
    @SuppressLint("WrongConstant")
    @OnClick(R.id.btn_submit)
    void Submit() {
        if(!flag1){
            return;
        }
        Log.e("点击", "ssa");

        mPresenter.loginState();
       //WebActivity.show(this,mProductName,mProductUrl);
    }

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
        if(onBack()){
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                finish();
            }
            if (!TextUtils.isEmpty(flag)) {
                MainActivity.show(this);
            }
        }
        return false;
    }
}
