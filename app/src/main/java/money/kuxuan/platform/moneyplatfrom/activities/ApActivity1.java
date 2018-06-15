package money.kuxuan.platform.moneyplatfrom.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.autolayout.AutoRelativeLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemLongClick;
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.utils.DisplayUtil;
import money.kuxuan.platform.common.widget.EmptyView;
import money.kuxuan.platform.common.widget.PortraitView;
import money.kuxuan.platform.common.widget.recycler.RecyclerAdapter;
import money.kuxuan.platform.factory.data.helper.MineHelper;
import money.kuxuan.platform.factory.model.db.ApplyProduct;
import money.kuxuan.platform.factory.model.db.CreditCardAppliProduct;
import money.kuxuan.platform.factory.model.db.DeleteApp;
import money.kuxuan.platform.factory.model.db.DeleteTwo;
import money.kuxuan.platform.factory.presenter.application.ApplicationContract;
import money.kuxuan.platform.factory.presenter.application.ApplicationPresenter;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.web.WebActivity;
import okhttp3.FormBody;

//我的申请activity
public class ApActivity1 extends PresenterActivity<ApplicationContract.Presenter>
        implements ApplicationContract.View {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @BindView(R.id.empty)
    EmptyView mEmptyView;

    private ApplicationPresenter applicationPresenter;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_tv_borrow_money)
    TextView tv_borrow_money;

    @BindView(R.id.tv_credit_card)
    TextView tv_credit_card;

    RecyclerAdapter.AdapterListenerImpl adapterListener;

    private boolean flag=true;
    private List<ApplyProduct> productData;
    private List<CreditCardAppliProduct> cardData;
    private static final String TAG = "ApActivity";
    private Intent intent;

    /**
     * 我的申请界面入口
     *
     * @param context context
     */
    public static void show(Context context) {
        Intent intent = new Intent(context,ApActivity1.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_ap1;
    }


    @OnClick(R.id.tv_add)
    void jump(){

        intent = new Intent(this,Activity_AddressBook.class);
        if(flag==true){
            intent.putExtra("All_App",true);
        }else if(flag==false){
            intent.putExtra("All_App",false);
        }
        startActivity(intent);

    }

    @Override
    protected void initData() {
        super.initData();
        //网络请求
        applicationPresenter.setIsClick(flag,"3");
        clickChange(flag);
        //这个是网络请求
        mPresenter.start();
        initWidget();
    }


    //点击改变数据
    @OnClick({ R.id.tv_tv_borrow_money, R.id.tv_credit_card})
    public void onClicks(View view){

        switch (view.getId()){

            case R.id.tv_tv_borrow_money:
                flag = true;
                break;

            case R.id.tv_credit_card:
                flag = false;
                break;

        }
        applicationPresenter.setIsClick(flag,"3");
        clickChange(flag);
        //这个是网络请求
        mPresenter.start();
        initWidget();

    }

    RecyclerAdapter<ApplyProduct> adapter1;
    RecyclerAdapter<CreditCardAppliProduct> adapter2;

    @Override
    protected void initWidget() {
        super.initWidget();

        if(flag){

            if(adapter1==null) {
                adapter1 = new RecyclerAdapter<ApplyProduct>() {
                    @Override
                    protected int getItemViewType(int position, ApplyProduct product) {
                        // 返回cell的布局id
                        return R.layout.cell_home_list1;
                    }

                    @Override
                    protected ViewHolder<ApplyProduct> onCreateViewHolder(View root, int viewType) {
                        return new ApplyViewHolder(root, ApActivity1.this);
                    }

                };
            }

            adapterListener = new RecyclerAdapter.AdapterListenerImpl<ApplyProduct>() {
                @Override
                public void onItemClick(RecyclerAdapter.ViewHolder holder, ApplyProduct product,int pos) {
                    DetailActivity.show(ApActivity1.this,product.getProduct_id(),"applicationList",2);
                }

                @Override
                public void onItemLongClick(RecyclerAdapter.ViewHolder holder, ApplyProduct applyProduct, int pos) {
                    //super.onItemLongClick(holder, applyProduct, pos);
                    View inflate = getLayoutInflater().inflate(R.layout.activity_delete2_layout, null);
                    showDialog(inflate,pos);
                }
            };

            initwidget1(adapter1);

        }else {

            //初始化
            if(adapter2==null) {
                adapter2 = new RecyclerAdapter<CreditCardAppliProduct>() {
                    @Override
                    protected int getItemViewType(int position, CreditCardAppliProduct product) {
                        // 返回cell的布局id
                        return R.layout.cell_creditcard_list;
                    }

                    @Override
                    protected ViewHolder<CreditCardAppliProduct> onCreateViewHolder(View root, int viewType) {
                        return new CreditCardAppliViewHolder(root, ApActivity1.this);
                    }

                };
            }

            adapterListener = new RecyclerAdapter.AdapterListenerImpl<CreditCardAppliProduct>() {
                @Override
                public void onItemClick(RecyclerAdapter.ViewHolder holder, CreditCardAppliProduct product,int pos) {

                    WebActivity.show(ApActivity1.this, product.getProduct_name(),
                            product.getUrl());

                }

                @Override
                public void onItemLongClick(RecyclerAdapter.ViewHolder holder, CreditCardAppliProduct creditCardAppliProduct, int pos) {
                    super.onItemLongClick(holder, creditCardAppliProduct, pos);
                    View inflate = getLayoutInflater().inflate(R.layout.activity_delete2_layout, null);
                    showDialog(inflate,pos);
                }
            };

            initwidget2(adapter2);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

            //网络请求
            applicationPresenter.setIsClick(flag,"3");
            clickChange(flag);
            //这个是网络请求
            mPresenter.start();
            initWidget();


    }

    AlertDialog alertDialog;
    public void showDialog(View view, final int pos){

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


        AutoRelativeLayout rel_next = (AutoRelativeLayout) view.findViewById(R.id.rel_next);
        AutoRelativeLayout rel_good = (AutoRelativeLayout) view.findViewById(R.id.rel_good);
        final TextView phone_name2 = (TextView) view.findViewById(R.id.phone_name2);

        phone_name2.setText("您确定删除此申请？");
        rel_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(alertDialog!=null&&alertDialog.isShowing())
                    alertDialog.dismiss();
            }
        });

        rel_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();

                if(flag){
                    //删除app申请

                    FormBody.Builder builder=new FormBody.Builder();
                    builder.add("product_apply_ids[0]",productData.get(pos).getId()+"");
                    FormBody build = builder.build();
                    MineHelper.deletetapp(build, new DataSource.Callback<Integer>() {

                        @Override
                        public void onDataNotAvailable(int strRes) {


                        }

                        @Override
                        public void onDataLoaded(Integer integer) {


                            productData.remove(pos);
                            adapter1.replace(productData);


                            if(productData.size()==0){
                                //网络请求
                                applicationPresenter.setIsClick(flag,"3");
                                clickChange(flag);
                                //这个是网络请求
                                mPresenter.start();
                                initWidget();

                            }
                        }
                    });
                }else {
                    //删除信用卡申请
                    FormBody.Builder builder=new FormBody.Builder();
                    builder.add("credit_card_apply_ids[0]",cardData.get(pos).getId()+"");
                    FormBody build = builder.build();
                    MineHelper.deletetcard(build, new DataSource.Callback<Integer>() {
                        @Override
                        public void onDataNotAvailable(int strRes) {


                        }

                        @Override
                        public void onDataLoaded(Integer integer) {

                            cardData.remove(pos);
                            adapter2.replace(cardData);

                            if(cardData.size()==0){
                                //网络请求
                                applicationPresenter.setIsClick(flag,"3");
                                clickChange(flag);
                                //这个是网络请求
                                mPresenter.start();
                                initWidget();

                            }
                        }
                    });
                }



            }
        });


        window.setContentView(view);

    }

    @BindView(R.id.lin_bottomtitel)
    LinearLayout lin_bottomtitel;
    private void showPopupwindow(){

        View popview = getLayoutInflater().inflate(R.layout.apply_popview,null,false);

        PopupWindow popupWindow = new PopupWindow(popview, DisplayUtil.dip2px(160),DisplayUtil.dip2px(80));

        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(lin_bottomtitel, Gravity.NO_GRAVITY,x,y);

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {


        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
            x = (int) ev.getRawX();
            y = (int) ev.getY();


            break;
        }

        return super.dispatchTouchEvent(ev);
    }

    int y;
    int x;

    private void initwidget1(RecyclerAdapter<ApplyProduct> mAdapter){


        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mAdapter);
        mRecycler.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        // 初始化占位布局
        mEmptyView.bind(mRecycler);
        setPlaceHolderView(mEmptyView);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.refreshData();
            }
        });

        mAdapter.setListener(adapterListener);

        ClassicsFooter footer = (ClassicsFooter)findViewById(R.id.footer);

        footer.setFinishDuration(0);//设置刷新完成显示的停留时间


    }


    private void initwidget2(RecyclerAdapter<CreditCardAppliProduct> mAdapter){

        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mAdapter);
        mRecycler.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        // 初始化占位布局
        mEmptyView.bind(mRecycler);
        setPlaceHolderView(mEmptyView);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.refreshData();
            }
        });

        mAdapter.setListener(adapterListener);

        ClassicsFooter footer = (ClassicsFooter)findViewById(R.id.footer);

        footer.setFinishDuration(0);//设置刷新完成显示的停留时间

    }




    @Override
    public void requestData(List<ApplyProduct> products) {
        hideLoading();
        productData=products;
        adapter1.replace(products);
        adapter1.notifyDataSetChanged();
        // 如果有数据，则是OK，没有数据就显示空布局
        if(products.size()==0){
            mPlaceHolderView.triggerEmpty("暂无申请记录，赶快去申请");
        }else{
            mPlaceHolderView.triggerOk();
        }
    }


    @Override
    public void requestData1(List<CreditCardAppliProduct> products) {
        hideLoading();
        cardData=products;
        adapter2.replace(products);
        adapter2.notifyDataSetChanged();
        // 如果有数据，则是OK，没有数据就显示空布局
        if(products.size()==0){
            mPlaceHolderView.triggerEmpty("暂无申请记录，赶快去申请");
        }else{
            mPlaceHolderView.triggerOk();
        }
    }

    /**
     * 上拉加载
     * @param products
     */
    @Override
    public void refresh(List<ApplyProduct> products) {
        adapter1.add(products);
        adapter1.notifyDataSetChanged();
        refreshLayout.finishLoadmore(true);
    }


    @Override
    public void refresh1(List<CreditCardAppliProduct> products) {
        adapter2.add(products);
        adapter2.notifyDataSetChanged();
        refreshLayout.finishLoadmore(true);
    }

    //没有数据的回调
    @Override
    public void NoData() {
        refreshLayout.setLoadmoreFinished(true);
    }

    @Override
    public void clickChange(boolean click) {

        if (click){
            tv_borrow_money.setTextColor(Color.parseColor("#FFFFFF"));
            tv_borrow_money.setBackgroundColor(Color.parseColor("#21d09c"));
            tv_credit_card.setTextColor(Color.parseColor("#21d09c"));
            tv_credit_card.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }else {
            tv_borrow_money.setTextColor(Color.parseColor("#21d09c"));
            tv_borrow_money.setBackgroundColor(Color.parseColor("#FFFFFF"));
            tv_credit_card.setTextColor(Color.parseColor("#FFFFFF"));
            tv_credit_card.setBackgroundColor(Color.parseColor("#21d09c"));
        }


    }

    @Override
    protected ApplicationContract.Presenter initPresenter() {
        applicationPresenter = new ApplicationPresenter(this);

        return applicationPresenter;
    }

    //返回键操作
    @OnClick(R.id.back)
    void backClick(){
        finish();
    }

    List<Integer> checklist = new ArrayList<>();


    /**
     * 每一个Cell的布局操作
     * @author HFRX hfrx1314@qq.com
     * @version 1.0.0
     */

    public class ApplyViewHolder extends RecyclerAdapter.ViewHolder<ApplyProduct> {
        private Context context;
        //产品日利率或月利率数值
        @BindView(R.id.txt_rate_number)
        public TextView txt_rate_number;
        //日利率或月利率
        @BindView(R.id.txt_rate)
        public  TextView txt_rate;
        //产品人数
        @BindView(R.id.txt_people_number)
        public TextView txt_people_number;
        //产品描述
        @BindView(R.id.txt_desc)
        public TextView txt_desc;
        //产品图片
        @BindView(R.id.im_portrait)
        public PortraitView im_portrait;
        //产品名称
        @BindView(R.id.txt_name)
        public TextView txt_name;
        //产品副标题
        @BindView(R.id.txt_prod_title)
        public  TextView txt_prod_title;

        @BindView(R.id.application)
        public TextView txtApplication;

        @BindView(R.id.textqi)
        public TextView textqi;


        public ApplyViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
        }

        protected void onBind(ApplyProduct applyProduct) {

            im_portrait.setup(Glide.with(context), applyProduct.getProduct_icon());
            txt_people_number.setText(applyProduct.getMoney());
            txt_name.setText(applyProduct.getProduct_name());

            txt_desc.setText(applyProduct.getCreate_time());
            txtApplication.setText("贷款金额");
            txt_prod_title.setVisibility(View.GONE);
            txt_rate.setText("贷款期数");
            txt_rate_number.setText(applyProduct.getPeriods());
            textqi.setVisibility(View.GONE);

        }


    }




    public class CreditCardAppliViewHolder extends RecyclerAdapter.ViewHolder<CreditCardAppliProduct> {
        private Context context;
        //产品日利率或月利率数值
        @BindView(R.id.txt_rate_number)
        public TextView txt_rate_number;
        //日利率或月利率
        @BindView(R.id.txt_rate)
        public TextView txt_rate;
        //产品人数
        @BindView(R.id.txt_people_number)
        public TextView txt_people_number;
        //产品描述
        @BindView(R.id.txt_desc)
        public TextView txt_desc;
        //产品图片
        @BindView(R.id.im_portrait)
        public PortraitView im_portrait;
        //产品名称
        @BindView(R.id.txt_name)
        public TextView txt_name;

        @BindView(R.id.application)
        public TextView txtApplication;

        @BindView(R.id.iv_first)
        public ImageView iv_first;

        @BindView(R.id.iv_second)
        public ImageView iv_second;

        @BindView(R.id.textqi)
        public TextView textqi;

        public CreditCardAppliViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
        }

        protected void onBind(CreditCardAppliProduct creditCardAppliProduct) {

            im_portrait.setup(Glide.with(context), creditCardAppliProduct.getIcon());
            txt_name.setText(creditCardAppliProduct.getProduct_name());
            txt_desc.setText(creditCardAppliProduct.getDesc());

            txtApplication.setText(creditCardAppliProduct.getCreate_time());
            txt_people_number.setVisibility(View.GONE);
            txt_rate.setVisibility(View.GONE);
            textqi.setVisibility(View.GONE);
            txt_rate_number.setVisibility(View.GONE);

        }


    }




}
