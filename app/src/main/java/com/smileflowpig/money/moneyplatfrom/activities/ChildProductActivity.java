package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smileflowpig.money.moneyplatfrom.helper.ProductViewHolder;
import com.smileflowpig.money.moneyplatfrom.web.WebActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.widget.EmptyView;
import com.smileflowpig.money.common.widget.recycler.RecyclerAdapter;
import com.smileflowpig.money.factory.model.db.Product;
import com.smileflowpig.money.factory.presenter.child.ChildContract;
import com.smileflowpig.money.factory.presenter.child.ChildPresenter;


public class ChildProductActivity extends PresenterActivity<ChildContract.Presenter>
        implements ChildContract.View {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    public static final String TITLE = "TITLE";

    @BindView(R.id.empty)
    EmptyView mEmptyView;

    private RecyclerAdapter<Product> mAdapter;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    @BindView(R.id.tv_title)
    TextView tv_title;
    //标题
    private String mTitle;

    private static final String TAG = "ActiveFragment";


    /**
     * 新品区 等界面
     *
     * @param context context
     */
    public static void show(Context context, String title) {
        Intent intent = new Intent(context, ChildProductActivity.class);


        intent.putExtra(TITLE, title);


        context.startActivity(intent);
    }

    @Override
    protected boolean initArgs(Bundle bundle) {

        mTitle = bundle.getString(TITLE);
        return !TextUtils.isEmpty(mTitle);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_child_product;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mPresenter.start();
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mAdapter = new RecyclerAdapter<Product>() {
            @Override
            protected int getItemViewType(int position, Product product) {
                // 返回cell的布局id
                return R.layout.cell_home_list;
            }

            @Override
            protected ViewHolder<Product> onCreateViewHolder(View root, int viewType) {
                return new ProductViewHolder(root, ChildProductActivity.this);
            }
        });
        mRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

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
                mPresenter.requestData();
            }
        });

        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<Product>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, Product product,int pos) {
                if(product.getSkip_type().equals("0")){


                   DetailActivity.show(ChildProductActivity.this,product,type_channel);
                }else{
                    WebActivity.show(ChildProductActivity.this,product.getName(),
                            product.getLink(),product.getId(),product.getSkip_type());
                }

            }

        });
        ClassicsFooter footer = (ClassicsFooter) findViewById(R.id.footer);

        footer.setFinishDuration(0);//设置刷新完成显示的停留时间

    }

    /**
     * 首次数据的回调
     * @param product
     */
    @Override
    public void onDone(List<Product> product) {
        hideLoading();
        mAdapter.replace(product);
        Log.e(TAG, "i" + product.size());
        // 如果有数据，则是OK，没有数据就显示空布局
        Log.e(TAG, "i" + mAdapter.getItemCount());
        mPlaceHolderView.triggerOkOrEmpty(mAdapter.getItemCount() > 0);
    }

    /**
     * 下拉加载
     * @param products
     */
    @Override
    public void refresh(List<Product> products) {
        mAdapter.add(products);
        mAdapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore(true);
    }

    /**
     * 数据加载完成的回调
     */
    @Override
    public void NoData() {
        refreshLayout.setLoadmoreFinished(true);
    }



    String type_channel ="newKind";
    @Override
    protected void initData() {
        super.initData();

        if (mTitle.equals("1")) {
            mTitle = "极速贷";
            type_channel = "speedKind";
        }
        if (mTitle.equals("2")) {
            mTitle = "热门贷";
            type_channel = "hotKind";
        }
        if (mTitle.equals("3")) {
            mTitle = "信用贷";
            type_channel = "creditKind";
        }
        if (mTitle.equals("4")) {
            mTitle = "大额贷";
            type_channel = "LargeAmount_Kind";
        }
        tv_title.setText(mTitle);

    }

    //顶部导航栏返回键点击事件
    @OnClick(R.id.back)
    void back() {

        finish();
    }

    @Override
    protected ChildContract.Presenter initPresenter() {
        return new ChildPresenter(this, mTitle);
    }


}
