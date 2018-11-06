package com.smileflowpig.money.moneyplatfrom.frags.main;


import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smileflowpig.money.moneyplatfrom.activities.Activity_CreditCard_ByStages;
import com.smileflowpig.money.moneyplatfrom.activities.Activity_HouseCalc;
import com.smileflowpig.money.moneyplatfrom.activities.Activity_Income_Tax_Calc;
import com.smileflowpig.money.moneyplatfrom.activities.Activity_loanCarCalc;
import com.smileflowpig.money.moneyplatfrom.frags.dialog.DgFragment;
import com.smileflowpig.money.moneyplatfrom.web.WebActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import com.smileflowpig.money.BuildConfig;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterFragment;
import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.common.widget.EmptyView;
import com.smileflowpig.money.common.widget.recycler.RecyclerAdapter;
import com.smileflowpig.money.factory.Constant;
import com.smileflowpig.money.factory.data.helper.ActiveHelper;
import com.smileflowpig.money.factory.model.db.Active;
import com.smileflowpig.money.factory.model.db.Tool;
import com.smileflowpig.money.factory.model.db.User;
import com.smileflowpig.money.factory.presenter.active.ActiveContract;
import com.smileflowpig.money.factory.presenter.active.ActivePresenter;
import com.smileflowpig.money.factory.util.LoginUtil;
import com.smileflowpig.money.factory.util.SPUtil;


/**
 *
 */
public class ActiveFragment extends PresenterFragment<ActiveContract.Presenter>
        implements ActiveContract.View, DgFragment.DialogClick {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @BindView(R.id.empty)
    EmptyView mEmptyView;
    private RecyclerAdapter<Active> mAdapter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.img_one)
    ImageView img_one;

    @BindView(R.id.img_two)
    ImageView img_two;

    @BindView(R.id.img_three)
    ImageView img_three;

    @BindView(R.id.tool_one)
    TextView tool_one;

    @BindView(R.id.tool_two)
    TextView tool_two;

    @BindView(R.id.tool_three)
    TextView tool_three;

    @BindView(R.id.tv_one)
    TextView tv_one;

    @BindView(R.id.tv_two)
    TextView tv_two;

    @BindView(R.id.tv_three)
    TextView tv_three;

    @BindView(R.id.count_one)
    LinearLayout count_one;

    @BindView(R.id.count_two)
    LinearLayout count_two;

    @BindView(R.id.count_three)
    LinearLayout count_three;

    private List<Active> list2=new ArrayList<>();

    private static final String TAG = "ActiveFragment";
    //未登陆弹框
    DgFragment dgFragment;

    private Active active;
    //private static final String URL = "http://web.kuxuan-inc.com/Atesting/index.html?channel_id=" + BuildConfig.CHANNLE;
//private static final String URL = "https://newapi.henhaojie.com/Atesting/index.html?channel_id=" + BuildConfig.CHANNLE;
//线上接口
    private static final String URL = "https://m.henhaojie.com/Atesting/index.html?channel_id=" + BuildConfig.CHANNLE;
    //测试接口
    //private static final String URL = "https://bw.quyaqu.com/Atesting/index.html?channel_id=" + BuildConfig.CHANNLE;
    //    private static final String URL = "http://m.henhaojie.com/Atesting/index.html?channel_id=" + BuildConfig.CHANNLE;
    List<Active> activeList;

    public ActiveFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        // 初始化Recycler

        final List<Tool> list2=new ArrayList<>();
        ActiveHelper.getToolData(new DataSource.Callback<List<Tool>>() {
            @Override
            public void onDataNotAvailable(int strRes) {

            }

            @Override
            public void onDataLoaded(List<Tool> list) {
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).show==1){
                        list2.add(list.get(i));
                    }
                }
                //请求计算器的网络请求
                getShow(list2);
            }
        });
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(mAdapter = new RecyclerAdapter<Active>() {
            @Override
            protected int getItemViewType(int position, Active userCard) {
                // 返回cell的布局id
                return R.layout.cell_active_list;
            }

            @Override
            protected ViewHolder<Active> onCreateViewHolder(View root, int viewType) {
                return new ActiveFragment.ViewHolder(root);
            }
        });
        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<Active>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, Active active,int pos) {

                Log.e(TAG, active.getActivity_url());
                //mPresenter.loginState();
                setActive(active);
                state();

            }

        });
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
    }

    public void getShow(final List<Tool> list){
        if(list.size()==1){
            img_one.setVisibility(View.VISIBLE);
            tool_one.setVisibility(View.VISIBLE);
            tv_one.setVisibility(View.GONE);
            Glide.with(getActivity()).load(list.get(0).icon).into(img_one);
            tool_one.setText(list.get(0).name);
            tv_two.setVisibility(View.VISIBLE);

            count_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    WebActivity.show(getActivity(),list.get(0).name,list.get(0).url);
                }
            });

        }else if (list.size()==2){
            img_one.setVisibility(View.VISIBLE);
            tool_one.setVisibility(View.VISIBLE);
            tv_one.setVisibility(View.GONE);
            Glide.with(getActivity()).load(list.get(0).icon).into(img_one);
            tool_one.setText(list.get(0).name);

            img_two.setVisibility(View.VISIBLE);
            tool_two.setVisibility(View.VISIBLE);
            tv_two.setVisibility(View.GONE);
            Glide.with(getActivity()).load(list.get(1).icon).into(img_two);
            tool_two.setText(list.get(1).name);

            tv_three.setVisibility(View.VISIBLE);

            count_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            count_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            count_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    WebActivity.show(getActivity(),list.get(0).name,list.get(0).url);
                }
            });
            count_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    WebActivity.show(getActivity(),list.get(1).name,list.get(1).url);
                }
            });


        }else if(list.size()==3){
            img_one.setVisibility(View.VISIBLE);
            tool_one.setVisibility(View.VISIBLE);
            tv_one.setVisibility(View.GONE);
            Glide.with(getActivity()).load(list.get(0).icon).into(img_one);
            tool_one.setText(list.get(0).name);

            img_two.setVisibility(View.VISIBLE);
            tool_two.setVisibility(View.VISIBLE);
            tv_two.setVisibility(View.GONE);
            Glide.with(getActivity()).load(list.get(1).icon).into(img_two);
            tool_two.setText(list.get(1).name);

            img_three.setVisibility(View.VISIBLE);
            tool_three.setVisibility(View.VISIBLE);
            tv_three.setVisibility(View.GONE);
            Glide.with(getActivity()).load(list.get(2).icon).into(img_three);
            tool_three.setText(list.get(2).name);

            count_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    WebActivity.show(getActivity(),list.get(0).name,list.get(0).url);
                }
            });
            count_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    WebActivity.show(getActivity(),list.get(1).name,list.get(1).url);
                }
            });
            count_three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    WebActivity.show(getActivity(),list.get(2).name,list.get(2).url);
                }
            });

        }
    }

    private void setActive(Active active) {
        this.active = active;
    }

    @Override
    protected void onFirstInit() {
        super.onFirstInit();
        mPresenter.start();
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_active;
    }

    @Override
    public void requestData(List<Active> activeList) {

        refreshLayout.finishLoadmore();
        refreshLayout.finishRefresh();
        list2.addAll(activeList);
        mAdapter.replace(list2);

        mPlaceHolderView.triggerOkOrEmpty(mAdapter.getItemCount() > 0);
    }

    @Override
    public void refresh(List<Active> ActivityRefreshList) {
        mAdapter.add(ActivityRefreshList);
        mAdapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore(true);
    }

    @Override
    public void NoData() {
        refreshLayout.setLoadmoreFinished(true);
    }


    @OnClick({R.id.lin_creditcardbystages,R.id.lin_loancar,R.id.lin_housecalc,R.id.lin_incometaxcalc})
    void click(View view){

        Intent intent;

        switch (view.getId()){

            case R.id.lin_creditcardbystages:
                intent = new Intent(getContext(), Activity_CreditCard_ByStages.class);
                startActivity(intent);
                break;
            case R.id.lin_loancar:

                intent =new Intent(getContext(), Activity_loanCarCalc.class);
                startActivity(intent);

                break;

            case R.id.lin_housecalc:
                intent = new Intent(getContext(), Activity_HouseCalc.class);
                startActivity(intent);
                break;

            case R.id.lin_incometaxcalc:

                intent = new Intent(getContext(), Activity_Income_Tax_Calc.class);
                startActivity(intent);

                break;


        }


    }

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
//                    ToastUtil.show(getActivity(),getActivity().getResources().getString(strRes));
                }
            });
        }

    }

    @Override
    public void state() {

        WebActivity.show(getContext(), active.getTitle(),
                active.getActivity_url());
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
    protected ActiveContract.Presenter initPresenter() {
        return new ActivePresenter(this);
    }

    @OnClick(R.id.left_lin)
    void leftClick() {
        WebActivity.show(getContext(), "身价计算器", URL);
    }

//    @OnClick(R.id.right_lin)
//    void rightClick() {
//        PlaceActivity.show(getContext(), "贷款计算器");
//    }

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

    /**
     * 每一个Cell的布局操作
     */
    class ViewHolder extends RecyclerAdapter.ViewHolder<Active> {
        @BindView(R.id.active_title)
        TextView active_title;

        @BindView(R.id.active_image)
        ImageView image_url;

        @BindView(R.id.time)
        TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(Active active) {
            active_title.setText(active.getTitle());
            Glide.with(getContext())
                    .load(active.getImage_url())
                    .into(image_url);
            time.setText(active.getStart_time());

        }


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
                mPresenter.start();
            }
        });
    }


}
