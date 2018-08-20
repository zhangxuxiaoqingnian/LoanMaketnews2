package money.kuxuan.platform.moneyplatfrom.guoshen.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import money.kuxuan.platform.common.SecondEvent;
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.factory.model.api.guoshen.GetRepaymentModel;
import money.kuxuan.platform.factory.model.api.guoshen.PopModel;
import money.kuxuan.platform.factory.model.api.guoshen.RepaymentListBean;
import money.kuxuan.platform.factory.model.db.User;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.activities.AccountActivity;
import money.kuxuan.platform.moneyplatfrom.guoshen.adapter.AmortizeAdapter;
import money.kuxuan.platform.moneyplatfrom.guoshen.adapter.LayoutAdapter;
import money.kuxuan.platform.moneyplatfrom.guoshen.adapter.Pop_Adapter;
import money.kuxuan.platform.moneyplatfrom.util.ToastUtil;

/**
 * Created by Allence on 2018/5/7 0007.
 */

public class HomeActivity extends PresenterActivity<HomeConreact.Presenter> implements HomeConreact.View{

    @BindView(R.id.viewpager)
    RecyclerViewPager mRecyclerViewPager;

    @BindView(R.id.mrecyclerview)
    RecyclerView mrecyclerview;

    @BindView(R.id.rel_empty)
    RelativeLayout rel_empty;

    @Override
    protected HomeConreact.Presenter initPresenter() {
        return new HomePresenter(this);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.guoshen_home;
    }


    public static void show(Context context){
            Intent intent = new Intent(context,HomeActivity.class);
            context.startActivity(intent);
    }


    @Override
    protected void initWidget() {
        super.initWidget();
        EventBus.getDefault().register(this);
        initTabViewPager();
        initRecycleView();
        mPresenter.getLoginStats();

    }

    AmortizeAdapter amortizeAdapter;
    private void initRecycleView() {

        mrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        amortizeAdapter = new AmortizeAdapter(R.layout.guoshen_amortizeitem);
        mrecyclerview.setAdapter(amortizeAdapter);

    }

    @BindView(R.id.lin_home)
    LinearLayout linearLayout;


    @OnClick(R.id.iv_mine)
    public void onclick(){

        if(flag){
            MineActivity.show(this);
        }else {
            //MineActivity.show(this);
            Intent intent = new Intent(this, AccountActivity.class);
            this.startActivityForResult(intent, money.kuxuan.platform.moneyplatfrom.Constant.Code.REQUEST_CODE);
        }

    }

    boolean flag;
    @OnClick(R.id.iv_add)
    public  void add(){

        if(flag){
            AddActivity.show(this);
        }else {
            //MineActivity.show(this);
            Intent intent = new Intent(this, AccountActivity.class);
            this.startActivityForResult(intent, money.kuxuan.platform.moneyplatfrom.Constant.Code.REQUEST_CODE);
        }

    }



    PopupWindow popupWindow;
    Pop_Adapter pop_adapter;
    @OnClick(R.id.tv_updatestatus)
    public void clcik(){
        mPresenter.getPopDetai(mRepaymentListBeans.get(index).getId());
    }


    @Subscribe
    public void onEventMainThread(GetRepaymentModel event) {

        mPresenter.getRepaymentList();

    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     *            屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }
    LayoutAdapter layoutAdapter;
    int index=0;
    private void initTabViewPager() {

        LinearLayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false);
        mRecyclerViewPager.setLayoutManager(layout);
        layoutAdapter = new LayoutAdapter(this, mRecyclerViewPager);
        mRecyclerViewPager.setAdapter(layoutAdapter);

        mRecyclerViewPager.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int i, int i1) {

                amortizeAdapter.setNewData(mRepaymentListBeans.get(i1).getDetail());
                index = i1;

            }
        });

        mRecyclerViewPager.setHasFixedSize(true);
        //        mRecyclerView.setLongClickable(true);
        mRecyclerViewPager.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
                //                updateState(scrollState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                //                mPositionText.setText("First: " + mRecyclerViewPager.getFirstVisiblePosition());
                int childCount = mRecyclerViewPager.getChildCount();
                int width = mRecyclerViewPager.getChildAt(0).getWidth();
                int padding = (mRecyclerViewPager.getWidth() - width) / 2;
                //                mCountText.setText("Count: " + childCount);

                for (int j = 0; j < childCount; j++) {
                    View v = recyclerView.getChildAt(j);
                    //往左 从 padding 到 -(v.getWidth()-padding) 的过程中，由大到小
                    float rate = 0;
                    ;
                    if (v.getLeft() <= padding) {
                        if (v.getLeft() >= padding - v.getWidth()) {
                            rate = (padding - v.getLeft()) * 1f / v.getWidth();
                        } else {
                            rate = 1;
                        }
                        v.setScaleY(1 - rate * 0.1f);
                        v.setScaleX(1 - rate * 0.1f);
                    } else {
                        //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                        if (v.getLeft() <= recyclerView.getWidth() - padding) {
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                        }
                        v.setScaleY(0.9f + rate * 0.1f);
                        v.setScaleX(0.9f + rate * 0.1f);
                    }
                }
            }
        });
        mRecyclerViewPager.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (mRecyclerViewPager.getChildCount() < 3) {
                    if (mRecyclerViewPager.getChildAt(1) != null) {
                        if (mRecyclerViewPager.getCurrentPosition() == 0) {
                            View v1 = mRecyclerViewPager.getChildAt(1);
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        } else {
                            View v1 = mRecyclerViewPager.getChildAt(0);
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        }
                    }
                } else {
                    if (mRecyclerViewPager.getChildAt(0) != null) {
                        View v0 = mRecyclerViewPager.getChildAt(0);
                        v0.setScaleY(0.9f);
                        v0.setScaleX(0.9f);
                    }
                    if (mRecyclerViewPager.getChildAt(2) != null) {
                        View v2 = mRecyclerViewPager.getChildAt(2);
                        v2.setScaleY(0.9f);
                        v2.setScaleX(0.9f);
                    }
                }
            }
        });
    }

    @Override
    public void stateLogin(User user) {
        flag = true;
        mPresenter.getRepaymentList();
    }


    @BindView(R.id.tv_updatestatus)
    TextView tv_updatestatus;

    @BindView(R.id.rel_tab)
    RelativeLayout rel_tab;
    private void setViewVisible(){

        mRecyclerViewPager.setVisibility(View.VISIBLE);
        rel_tab.setVisibility(View.VISIBLE);
        mrecyclerview.setVisibility(View.VISIBLE);
        tv_updatestatus.setVisibility(View.VISIBLE);

    }

    private void setViewGone(){

        mRecyclerViewPager.setVisibility(View.GONE);
        rel_tab.setVisibility(View.GONE);
        mrecyclerview.setVisibility(View.GONE);
        tv_updatestatus.setVisibility(View.GONE);

    }




    @Override
    public void setNoLogin() {
        flag = false;
        setViewGone();
        rel_empty.setVisibility(View.VISIBLE);
    }


    List<RepaymentListBean> mRepaymentListBeans;
    @Override
    public void setRepaymentListView(List<RepaymentListBean> repaymentListBeans) {
        if(repaymentListBeans.size()==0){
            return;
        }
        rel_empty.setVisibility(View.GONE);
        setViewVisible();
        mRepaymentListBeans = repaymentListBeans;
        layoutAdapter.setNewData(repaymentListBeans);
        amortizeAdapter.setNewData(repaymentListBeans.get(index).getDetail());

    }

    @Override
    public void setPopDtailView(List<PopModel> popModels) {


        View view  = getLayoutInflater().inflate(R.layout.guoshen_popupwindow,null,false);
        RecyclerView guoshen_poprecyclerView = (RecyclerView) view.findViewById(R.id.guoshen_poprecyclerView);
        TextView tv_update1 = (TextView) view.findViewById(R.id.tv_update1);
        TextView tv_update0 = (TextView) view.findViewById(R.id.tv_update0);

        guoshen_poprecyclerView.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
                int count = state.getItemCount();

                if (count > 0) {
                    if(count>4){
                        count =4;
                    }
                    int realHeight = 0;
                    int realWidth = 0;
                    for(int i = 0;i < count; i++){
                        View view = recycler.getViewForPosition(0);
                        if (view != null) {
                            measureChild(view, widthSpec, heightSpec);
                            int measuredWidth = View.MeasureSpec.getSize(widthSpec);
                            int measuredHeight = view.getMeasuredHeight();
                            realWidth = realWidth > measuredWidth ? realWidth : measuredWidth;
                            realHeight += measuredHeight;
                        }
                        setMeasuredDimension(realWidth, realHeight);
                    }
                } else {
                    super.onMeasure(recycler, state, widthSpec, heightSpec);
                }
            }
        });

        pop_adapter = new Pop_Adapter(R.layout.guoshen_popitem,tv_update0,tv_update1,(HomePresenter)mPresenter,popModels);

        guoshen_poprecyclerView.setAdapter(pop_adapter);
        popupWindow = new PopupWindow(view, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        setBackgroundAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
        popupWindow.showAtLocation(linearLayout, Gravity.BOTTOM,0,0);



    }

    @Override
    public void putStatusView() {

        ToastUtil.show(getApplicationContext(),"修改成功");
        popupWindow.dismiss();
        mPresenter.getRepaymentList();

    }


    @Subscribe
    public void onEventMainThread(SecondEvent event) {

        String msg = event.getMsg();
        if (msg.equals("登录/注册")) {
            setNoLogin();
        } else {
            mPresenter.getLoginStats();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
