package money.kuxuan.platform.moneyplatfrom.frags.main;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import money.kuxuan.platform.common.app.PresenterFragment;
import money.kuxuan.platform.common.widget.EmptyView;
import money.kuxuan.platform.common.widget.recycler.RecyclerAdapter;
import money.kuxuan.platform.factory.model.api.search.FilterRspModel;
import money.kuxuan.platform.factory.model.db.Dialogs;
import money.kuxuan.platform.factory.model.db.Product;
import money.kuxuan.platform.factory.presenter.search.SearchContract;
import money.kuxuan.platform.factory.presenter.search.SearchPresent;
import money.kuxuan.platform.moneyplatfrom.Adapter.DialogAdapter;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.DetailActivity;
import money.kuxuan.platform.moneyplatfrom.frags.AlertFragment;
import money.kuxuan.platform.moneyplatfrom.helper.DensityUtil;
import money.kuxuan.platform.moneyplatfrom.helper.ProductViewHolder;
import money.kuxuan.platform.moneyplatfrom.web.WebActivity;

/**
 *
 */
public class SearchFragment extends PresenterFragment<SearchContract.Presenter>
        implements SearchContract.View, TabLayout.OnTabSelectedListener {

    @BindView(R.id.top_tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.gray_layout)
    View gray_layout;

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @BindView(R.id.search_layout)
    LinearLayout search_layout;

    @BindView(R.id.empty)
    EmptyView mEmptyView;

    private RecyclerAdapter<Product> mAdapter;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.num_lin)
    LinearLayout num_lin;

    @BindView(R.id.term_lin)
    LinearLayout term_lin;

    @BindView(R.id.identity_lin)
    LinearLayout identity_lin;

    @BindView(R.id.identity_tv)
    TextView identity_tv;

    List<Dialogs> amountList;
    List<Dialogs> termList;
    List<Dialogs> personList;

    @BindView(R.id.num_tv)
    TextView num_tv;

    @BindView(R.id.term_tv)
    TextView term_tv;

    private DialogAdapter mAdapter2;


    private static final String TAG = "SearchFragment";

    private String term = "不限";
    private String amount = "不限";
    private String user_title = "不限";
    private String ranking_list = "";

    private static final String identity[] = {"不限", "上班族", "个体户", "企业家"};
    private PopupWindow popupWindow;

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        setTabLayout();

        // 初始化Recycler
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(mAdapter = new RecyclerAdapter<Product>() {
            @Override
            protected int getItemViewType(int position, Product product) {
                // 返回cell的布局id
                return R.layout.cell_home_list;
            }

            @Override
            protected ViewHolder<Product> onCreateViewHolder(View root, int viewType) {

                return new ProductViewHolder(root, getContext());
            }
        });

        RecyclerAdapter m = new RecyclerAdapter() {
            @Override
            protected int getItemViewType(int position, Object o) {
                return 0;
            }

            @Override
            protected ViewHolder onCreateViewHolder(View root, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


            }
        };

        // 初始化占位布局
        mEmptyView.bind(mRecycler);
        setPlaceHolderView(mEmptyView);

        mRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);

            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.refreshData(term, amount, user_title, ranking_list,1);
            }
        });

        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<Product>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, Product product,int pos) {

                if (product.getSkip_type().equals("0")) {
                    DetailActivity.show(getContext(), product,"findList");
                } else {
                    WebActivity.show(getContext(), product.getName(),
                            product.getLink(),product.getId(),product.getSkip_type());
                }


            }

        });

        ClassicsFooter footer = (ClassicsFooter)root.findViewById(R.id.footer);

        footer.setFinishDuration(0);//设置刷新完成显示的停留时间

    }

    @Override
    protected void initData() {
        super.initData();

        mPresenter.start();
        if(ranking_list.equals("all")){
            this.setTabTextStyle(tabLayout, true, 0);
        }


    }



    @Override
    public void onStop() {
        super.onStop();
        term_tv.setText("贷款期限");
        num_tv.setText("贷款金额");
        identity_tv.setText("职业身份");


        tabLayout.getTabAt(0).select();


    }


    @Override
    protected void onFirstInit() {

        super.onFirstInit();
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                reflex(tabLayout);
            }
        });
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_search;
    }



    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        ranking_list = tab.getText().toString();
        if(ranking_list.equals("利率低")){
            ranking_list = "rate";
        }
        if(ranking_list.equals("放款快")){
            ranking_list = "term";
        }
        if(ranking_list.equals("易通过")){
            ranking_list = "applicants";
        }
        if(ranking_list.equals("综合排序")){
            ranking_list = "all";
        }
        refreshLayout.setLoadmoreFinished(false);
        mPresenter.setPage(1);
        mPresenter.setHasNext(true);
        mPresenter.refreshData(term, amount, user_title, ranking_list,0);

       int position = tab.getPosition();
      this.setTabTextStyle(tabLayout, true, position);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

        int position = tab.getPosition();
        this.setTabTextStyle(tabLayout, false, position);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    private void setTabLayout() {

        tabLayout.addTab(tabLayout.newTab().setText("综合排序"));

        tabLayout.addTab(tabLayout.newTab().setText("利率低"));

        tabLayout.addTab(tabLayout.newTab().setText("放款快"));

        tabLayout.addTab(tabLayout.newTab().setText("易通过"));

        tabLayout.addOnTabSelectedListener(this);
    }

    /**
     * 设置TabLayout中的字体是否要加粗
     * @param tabLayout
     * @param isBold
     * @param position
     */
    public  void setTabTextStyle(TabLayout tabLayout, boolean isBold, int position) {
        try {

            LinearLayout ly = (LinearLayout) tabLayout.getChildAt(0);
            LinearLayout tabView = (LinearLayout) ly.getChildAt(position);

            if (null != tabView && tabView.getChildCount() > 0) {
                View view = tabView.getChildAt(1);

                if (null != view) {
                    if (isBold) {

                        ((AppCompatTextView) view).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    } else {
                        ((AppCompatTextView) view).setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                    }
                }
            }
        }catch (Exception e){
            Log.e(TAG,e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onSearchDone(List<Product> product) {
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setEnableRefresh(true);
        mAdapter.replace(product);
        Log.e(TAG, "" + product.size());
        // 如果有数据，则是OK，没有数据就显示空布局
        Log.e(TAG, "i" + mAdapter.getItemCount());
        mAdapter.notifyDataSetChanged();
        mPlaceHolderView.triggerOkOrEmpty(product.size() > 0);
    }

    @Override
    public void refresh(List<Product> products) {
        Log.e(TAG,products.size()+"---------");
        mAdapter.add(products);
        mAdapter.notifyDataSetChanged();
        Log.e(TAG,products.size()+"--------------");
        mPlaceHolderView.triggerOkOrEmpty(mAdapter.getItemCount() > 0);
        refreshLayout.finishLoadmore(true);

    }

    @Override
    public void NoData() {
        refreshLayout.setLoadmoreFinished(true);
    }

    @Override
    public void getList(FilterRspModel filterRspModel) {
        amountList = filterRspModel.getList();
        termList = filterRspModel.getMonth();
        personList =filterRspModel.getUserlist();
    }

    @Override
    public void getChoose(List<Product> products) {
        Log.e(TAG,products.size()+"adasdasdasdasdadasd");
        mAdapter.replace(products);
        mAdapter.notifyDataSetChanged();

        mPlaceHolderView.triggerOkOrEmpty(mAdapter.getItemCount()>0);

    }

    @Override
    protected SearchContract.Presenter initPresenter() {
        return new SearchPresent(this);
    }

    @OnClick(R.id.num_lin)
    void numClick() {

//        AlertFragment alertFragment = new AlertFragment();
//        alertFragment.setListener(new AlertFragment.OnSelectedListener() {
//            @Override
//            public void onSelectedItem(String k, String v) {
//
//                mPresenter.setPage(1);
//                refreshLayout.setLoadmoreFinished(false);
//                mPresenter.setHasNext(true);
//                num_tv.setText(v);
//                amount = k;
//                mPresenter.choseCategory(term, amount, user_title, ranking_list);
//                Log.e(TAG, "term====" + term + "amount====" + amount + "user_title====" + user_title + "ranking_list====" + ranking_list);
//            }
//        });
//        alertFragment.setData(amountList);
//        alertFragment.show(getActivity().getSupportFragmentManager(), AlertFragment.class.getName());



        ShowPopuWindow(amountList,2);
        num_tv.setTextColor(Color.parseColor("#FFAA48"));
        gray_layout.setVisibility(View.VISIBLE);

    }

    private void ShowPopuWindow(final List<Dialogs> data, final int num) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_listview_layout, null);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,500,true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        popupWindow.showAsDropDown(search_layout,0,0);

        ListView listView  = (ListView) view.findViewById(R.id.dialog_list);
        if(data!=null){
            mAdapter2 = new DialogAdapter(getContext(), data);
            listView.setAdapter(mAdapter2);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                mPresenter.setPage(1);
                refreshLayout.setLoadmoreFinished(false);
                mPresenter.setHasNext(true);
                if(num==1){

                    identity_tv.setText(data.get(position).getV());
                    user_title = data.get(position).getK();
                }else if(num==2){
                    num_tv.setText(data.get(position).getV());
                    amount = data.get(position).getK();
                }else if(num==3){
                    term_tv.setText(data.get(position).getV());
                    term = data.get(position).getK();
                }
                mPresenter.choseCategory(term, amount, user_title, ranking_list);

            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                num_tv.setTextColor(Color.parseColor("#000000"));
                identity_tv.setTextColor(Color.parseColor("#000000"));
                term_tv.setTextColor(Color.parseColor("#000000"));
                gray_layout.setVisibility(View.GONE);
            }
        });

    }






    @OnClick(R.id.term_lin)
    void termClick() {

//        AlertFragment alertFragment = new AlertFragment();
//        alertFragment.setListener(new AlertFragment.OnSelectedListener() {
//            @Override
//            public void onSelectedItem(String k, String v) {
//
//                mPresenter.setPage(1);
//                refreshLayout.setLoadmoreFinished(false);
//                mPresenter.setHasNext(true);
//                term_tv.setText(v);
//                term = k;
//                mPresenter.choseCategory(term, amount, user_title, ranking_list);
//                Log.e(TAG, "term====" + term + "amount====" + amount + "user_title====" + user_title + "ranking_list====" + ranking_list);
//            }
//        });
//        alertFragment.setData(termList);
//        alertFragment.show(getActivity().getSupportFragmentManager(), AlertFragment.class.getName());
        ShowPopuWindow(termList,3);
        term_tv.setTextColor(Color.parseColor("#FFAA48"));
        gray_layout.setVisibility(View.VISIBLE);
    }


    @OnClick(R.id.identity_lin)
    void idenClick() {

//        AlertFragment alertFragment = new AlertFragment();
//        alertFragment.setListener(new AlertFragment.OnSelectedListener() {
//            @Override
//            public void onSelectedItem(String k, String v) {
//                mPresenter.setPage(1);
//                refreshLayout.setLoadmoreFinished(false);
//                mPresenter.setHasNext(true);
//                identity_tv.setText(v);
//                user_title = k;
//                mPresenter.choseCategory(term, amount, user_title, ranking_list);
//                Log.e(TAG, "term====" + term + "amount====" + amount + "user_title====" + user_title + "ranking_list====" + ranking_list);
//            }
//        });
//        alertFragment.setData(personList);
//        alertFragment.show(getActivity().getSupportFragmentManager(), AlertFragment.class.getName());
        ShowPopuWindow(personList,1);
        identity_tv.setTextColor(Color.parseColor("#FFAA48"));
        gray_layout.setVisibility(View.VISIBLE);

    }


    private void reflex(final TabLayout tabLayout){
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    Field mTabStripField = tabLayout.getClass().getDeclaredField("mTabStrip");
                    mTabStripField.setAccessible(true);

                    LinearLayout mTabStrip = (LinearLayout) mTabStripField.get(tabLayout);

                    int dp10 = dip2px(tabLayout.getContext(), 10);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public static int dip2px(Context context, float dipValue) {

        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    @Override
    public void showError(int str) {
        super.showError(str);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.finishLoadmore();
        mPlaceHolderView.triggerNetError();
        Button button = (Button) mEmptyView.findViewById(R.id.bu_re);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.start();
            }
        });
    }
}
