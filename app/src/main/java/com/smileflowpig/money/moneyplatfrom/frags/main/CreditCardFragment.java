package com.smileflowpig.money.moneyplatfrom.frags.main;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smileflowpig.money.moneyplatfrom.frags.dialog.DgFragment;
import com.smileflowpig.money.moneyplatfrom.web.WebActivity;

import java.util.List;

import butterknife.BindView;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterFragment;
import com.smileflowpig.money.common.widget.DividerItemDecoration;
import com.smileflowpig.money.common.widget.EmptyView;
import com.smileflowpig.money.common.widget.PortraitView;
import com.smileflowpig.money.common.widget.recycler.RecyclerAdapter;
import com.smileflowpig.money.factory.Constant;
import com.smileflowpig.money.factory.model.db.CreditCardProduct;
import com.smileflowpig.money.factory.model.db.User;
import com.smileflowpig.money.factory.presenter.creditcard.CreditcardContract;
import com.smileflowpig.money.factory.presenter.creditcard.CreditcardPresent;
import com.smileflowpig.money.factory.util.LoginUtil;
import com.smileflowpig.money.factory.util.SPUtil;


/**
 * Created by Allence
 */

public class CreditCardFragment extends PresenterFragment<CreditcardContract.Presenter>
        implements CreditcardContract.View,DgFragment.DialogClick{

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @BindView(R.id.empty)
    EmptyView mEmptyView;

    private RecyclerAdapter<CreditCardProduct> mAdapter;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    CreditCardProduct creditCardProduct;


    public CreditCardFragment(){

    }

    @Override
    protected CreditcardContract.Presenter initPresenter() {
        return new CreditcardPresent(this);
    }


    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mAdapter = new RecyclerAdapter<CreditCardProduct>() {
            @Override
            protected int getItemViewType(int position, CreditCardProduct creditCardProduct) {
                return R.layout.cell_creditcard_list;
            }

            @Override
            protected ViewHolder<CreditCardProduct> onCreateViewHolder(View root, int viewType) {
                return new CreditCardViewHolder(root,getActivity());
            }
        });


        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
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

        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<CreditCardProduct>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, CreditCardProduct product,int pos) {

                setCreditCardProduct(product);
                mPresenter.loginState(Integer.parseInt(product.getId()));

            }
        });

        ClassicsFooter footer = (ClassicsFooter)root.findViewById(R.id.footer);

        footer.setFinishDuration(0);//设置刷新完成显示的停留时间

    }


    private void setCreditCardProduct(CreditCardProduct creditCardProduct){

        this.creditCardProduct = creditCardProduct;

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void onFirstInit() {
        super.onFirstInit();
        mPresenter.start();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_creditcard;
    }

    @Override
    public void requestData(List<CreditCardProduct> products) {
//        hideLoading();
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setEnableRefresh(true);
        mAdapter.replace(products);
        mAdapter.notifyDataSetChanged();
        // 如果有数据，则是OK，没有数据就显示空布局
        if(products.size()==0){
            mPlaceHolderView.triggerEmpty("无数据");
        }else{
            mPlaceHolderView.triggerOk();
        }
    }

    @Override
    public void refresh(List<CreditCardProduct> products) {

        mAdapter.add(products);
        mAdapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore(true);

    }

    @Override
    public void NoData() {
        refreshLayout.setLoadmoreFinished(true);
    }


    @Override
    public void showError(int str) {
        super.showError(str);
        mPlaceHolderView.triggerNetError();
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.finishLoadmore();
        refreshLayout.setEnableRefresh(false);
        Button button = (Button) mEmptyView.findViewById(R.id.bu_re);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshLayout.setEnableLoadmore(true);
                refreshLayout.setEnableRefresh(true);
                mPresenter.start();
            }
        });
    }



    public class CreditCardViewHolder extends RecyclerAdapter.ViewHolder<CreditCardProduct> {
        private Context context;
        //产品日利率或月利率数值
        @BindView(R.id.txt_rate_number)
        TextView txt_rate_number;
        //日利率或月利率
        @BindView(R.id.txt_rate)
        TextView txt_rate;
        //产品人数
        @BindView(R.id.txt_people_number)
        TextView txt_people_number;
        //产品描述
        @BindView(R.id.txt_desc)
        TextView txt_desc;
        //产品图片
        @BindView(R.id.im_portrait)
        PortraitView im_portrait;
        //产品名称
        @BindView(R.id.txt_name)
        TextView txt_name;

        @BindView(R.id.application)
        TextView txtApplication;

        @BindView(R.id.iv_first)
        ImageView iv_first;

        @BindView(R.id.iv_second)
        ImageView iv_second;

        @BindView(R.id.textqi)
        TextView textqi;

        public CreditCardViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
        }

        protected void onBind(CreditCardProduct creditCardProduct) {

            im_portrait.setup(Glide.with(context), creditCardProduct.getCard_icon());
            txt_name.setText(creditCardProduct.getName());
            txt_desc.setText(creditCardProduct.getDesc());

            txtApplication.setText("申请人数:");
            txt_people_number.setText(creditCardProduct.getApply_num());
            txt_rate.setVisibility(View.GONE);
            textqi.setVisibility(View.GONE);
            txt_rate_number.setVisibility(View.GONE);

            int size =0;

            if(creditCardProduct.getKeywords()!=null){
                size = creditCardProduct.getKeywords().size();
            }

                if(size==1){
                    if(creditCardProduct.getKeywords().get(0).equals("礼")){
                        Glide.with(context).load(R.drawable.li).into(iv_first);
                    }else if(creditCardProduct.getKeywords().get(0).equals("荐")){
                        Glide.with(context).load(R.drawable.jian).into(iv_first);
                    }
                }else if(size==2){
                    Glide.with(context).load(R.drawable.jian).into(iv_first);
                    Glide.with(context).load(R.drawable.li).into(iv_second);
                }


        }


    }


    DgFragment dgFragment;
    @Override
    public void stateError() {

        Boolean isExit = (Boolean) SPUtil.get(getActivity(), Constant.UserInfo.ISEXITE, true);
        if (isExit) {
            dgFragment = new DgFragment();
            dgFragment.setDialogLisener(this);
            dgFragment.show(getActivity().getFragmentManager(), "EditNameDialog");
        } else {
            LoginUtil.getInstance().reLoadLogin(new LoginUtil.OnLoadListener() {
                @Override
                public void onLoadSuccess(User user) {
                    state();
                }

                @Override
                public void onLoadFail(int strRes) {
                    //登录失败
                    //ToastUtil.show(getActivity(),getActivity().getResources().getString(strRes));
                }
            });
        }

    }

    @Override
    public void state() {

        WebActivity.show(getContext(), creditCardProduct.getName(),
                creditCardProduct.getUrl());
    }


    @Override
    public void codeSuccess() {
        Toast.makeText(getContext(), "验证码发送成功", Toast.LENGTH_SHORT).show();
        dgFragment.setCodeStart();
        dgFragment.setVisible(false);
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
        dgFragment.dismiss();
    }

    @Override
    public void loginFailure(@StringRes int strRes) {
        Toast.makeText(getContext(), strRes, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dialog(String phone, String code) {
        mPresenter.login(phone, code);
    }

    @Override
    public void codePush(String phone, String smstype) {
        mPresenter.codePush(phone, smstype);
    }

    @Override
    public void dialogLogin(String phone, String password) {
        mPresenter.loginP(phone, password);
    }

    @Override
    public void LoginSound(String phone) {
        mPresenter.SoundCode(phone);
    }

}
