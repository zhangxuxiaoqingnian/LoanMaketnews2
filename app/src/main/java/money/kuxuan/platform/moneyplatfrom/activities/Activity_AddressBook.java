package money.kuxuan.platform.moneyplatfrom.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.widget.EmptyView;
import money.kuxuan.platform.factory.data.helper.MineHelper;
import money.kuxuan.platform.factory.model.db.AllApp;
import money.kuxuan.platform.factory.model.db.AllCard;
import money.kuxuan.platform.factory.model.db.Bean;
import money.kuxuan.platform.factory.model.db.DeleteApp;
import money.kuxuan.platform.factory.presenter.addplatform.AddPlatformPresenter;
import money.kuxuan.platform.factory.presenter.addplatform.AddPlatfromContract;
import money.kuxuan.platform.moneyplatfrom.Adapter.FastFlexAdapter;
import money.kuxuan.platform.moneyplatfrom.Adapter.HeaderRecyclerAndFooterWrapperAdapter;
import money.kuxuan.platform.moneyplatfrom.Adapter.ViewHolder;
import money.kuxuan.platform.moneyplatfrom.Constant;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.helper.FastFlexBean;
import money.kuxuan.platform.moneyplatfrom.util.DividerItemDecoration;

public class Activity_AddressBook extends PresenterActivity<AddPlatfromContract.Presenter>
        implements AddPlatfromContract.View  {



    private static final String TAG = "zxt";
    private RecyclerView mRv;
    private FastFlexAdapter mAdapter;
    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;
    private LinearLayoutManager mManager;
    private List<FastFlexBean> mDatas;

    private SuspensionDecoration mDecoration;

    /**
     * 右侧边栏导航区域
     */
    private IndexBar mIndexBar;

    /**
     * 显示指示器DialogText
     */
    private TextView mTvSideBarHint;
    private List<AllApp.HBean> list=new ArrayList();
    private boolean all_app;
    private EmptyView empty;
    private SharedPreferences sharedPreferences;

    public void getDataOfAllApp(List<AllApp.HBean> a){
        if (a!=null&&a.size()>0)
        {
                list.addAll(a);
        }
    }


    @Override
    protected void initWidget() {
        super.initWidget();

        sharedPreferences = getSharedPreferences("DaiData",MODE_PRIVATE);
        Intent intent = getIntent();
        all_app = intent.getBooleanExtra("All_App", false);
        if(all_app){
            list.clear();
            //请求所有app接口
            MineHelper.allShap(new DataSource.Callback<AllApp>() {
                @Override
                public void onDataNotAvailable(int strRes) {


                }

                @Override
                public void onDataLoaded(AllApp allApp) {
                    getDataOfAllApp(allApp.A);
                    getDataOfAllApp(allApp.B);
                    getDataOfAllApp(allApp._$0);
                    getDataOfAllApp(allApp._$1);
                    getDataOfAllApp(allApp._$2);
                    getDataOfAllApp(allApp._$3);
                    getDataOfAllApp(allApp._$4);
                    getDataOfAllApp(allApp._$5);
                    getDataOfAllApp(allApp._$6);
                    getDataOfAllApp(allApp._$7);
                    getDataOfAllApp(allApp._$8);
                    getDataOfAllApp(allApp._$9);
                    getDataOfAllApp(allApp.C);
                    getDataOfAllApp(allApp.D);
                    getDataOfAllApp(allApp.E);
                    getDataOfAllApp(allApp.F);
                    getDataOfAllApp(allApp.G);
                    getDataOfAllApp(allApp.H);
                    getDataOfAllApp(allApp.I);
                    getDataOfAllApp(allApp.J);
                    getDataOfAllApp(allApp.K);
                    getDataOfAllApp(allApp.L);
                    getDataOfAllApp(allApp.M);
                    getDataOfAllApp(allApp.N);
                    getDataOfAllApp(allApp.O);
                    getDataOfAllApp(allApp.P);
                    getDataOfAllApp(allApp.Q);
                    getDataOfAllApp(allApp.R);
                    getDataOfAllApp(allApp.S);
                    getDataOfAllApp(allApp.T);
                    getDataOfAllApp(allApp.U);
                    getDataOfAllApp(allApp.V);
                    getDataOfAllApp(allApp.W);
                    getDataOfAllApp(allApp.X);
                    getDataOfAllApp(allApp.Y);
                    getDataOfAllApp(allApp.Z);

                }
            });

        }else {
            list.clear();
            //请求所有信用卡接口
            MineHelper.allCard(new DataSource.Callback<AllApp>() {
                @Override
                public void onDataNotAvailable(int strRes) {


                }

                @Override
                public void onDataLoaded(AllApp allCard) {


                    getDataOfAllApp(allCard.A);
                    getDataOfAllApp(allCard.B);
                    getDataOfAllApp(allCard._$0);
                    getDataOfAllApp(allCard._$1);
                    getDataOfAllApp(allCard._$2);
                    getDataOfAllApp(allCard._$3);
                    getDataOfAllApp(allCard._$4);
                    getDataOfAllApp(allCard._$5);
                    getDataOfAllApp(allCard._$6);
                    getDataOfAllApp(allCard._$7);
                    getDataOfAllApp(allCard._$8);
                    getDataOfAllApp(allCard._$9);
                    getDataOfAllApp(allCard.C);
                    getDataOfAllApp(allCard.D);
                    getDataOfAllApp(allCard.E);
                    getDataOfAllApp(allCard.F);
                    getDataOfAllApp(allCard.G);
                    getDataOfAllApp(allCard.H);
                    getDataOfAllApp(allCard.I);
                    getDataOfAllApp(allCard.J);
                    getDataOfAllApp(allCard.K);
                    getDataOfAllApp(allCard.L);
                    getDataOfAllApp(allCard.M);
                    getDataOfAllApp(allCard.N);
                    getDataOfAllApp(allCard.O);
                    getDataOfAllApp(allCard.P);
                    getDataOfAllApp(allCard.Q);
                    getDataOfAllApp(allCard.R);
                    getDataOfAllApp(allCard.S);
                    getDataOfAllApp(allCard.T);
                    getDataOfAllApp(allCard.U);
                    getDataOfAllApp(allCard.V);
                    getDataOfAllApp(allCard.W);
                    getDataOfAllApp(allCard.X);
                    getDataOfAllApp(allCard.Y);
                    getDataOfAllApp(allCard.Z);

                }
            });

        }
        mRv = (RecyclerView) findViewById(R.id.rv);
        empty = (EmptyView) findViewById(R.id.empty);

        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));

        mAdapter = new FastFlexAdapter(this, mDatas);
        mAdapter.Setnum(new FastFlexAdapter.Getnum() {
            @Override
            public void data(int pos) {
                if(all_app){
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("daiimg",mDatas.get(pos).getImgurl());
                    edit.putString("dainame",mDatas.get(pos).getPlatform());
                    edit.putString("daiid",mDatas.get(pos).getUuid());
                    edit.commit();


                    //当点击一个产品时调用添加到过审接口并关闭此Activity
                    MineHelper.addapp(mDatas.get(pos).getUuid(), new DataSource.Callback<DeleteApp>() {
                        @Override
                        public void onDataNotAvailable(int strRes) {

                        }

                        @Override
                        public void onDataLoaded(DeleteApp deleteApp) {
setResult(Constant.Code.RESULT_CHOOSEAPP_CODE);
                            finish();
                        }
                    });
                }else{

                    MineHelper.addcard(mDatas.get(pos).getUuid(), new DataSource.Callback<DeleteApp>() {
                        @Override
                        public void onDataNotAvailable(int strRes) {

                        }

                        @Override
                        public void onDataLoaded(DeleteApp deleteApp) {

                            finish();
                        }
                    });
                }


            }
        });
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                holder.setText(R.id.tv_platform, (String) o);
            }
        };
        //        mHeaderAdapter.setHeaderView(R.layout.item_city, "测试头部");

        mRv.setAdapter(mHeaderAdapter);
        mHeaderAdapter.notifyDataSetChanged();
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));

//        empty.bind(mRv);
//        setPlaceHolderView(empty);
        //如果add两个，那么按照先后顺序，依次渲染。
        mRv.addItemDecoration(new DividerItemDecoration(Activity_AddressBook.this, DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager

        //        initDatas(getResources().getStringArray(R.array.provinces));


        initDatas(list);

    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_addressbook;
    }

    @Override
    protected AddPlatfromContract.Presenter initPresenter() {
        return new AddPlatformPresenter(this);
    }


    @OnClick(R.id.back)
    void back(){
        finish();

    }

    @Override
    public void showError(int str) {
        super.showError(str);
        mPlaceHolderView.triggerNetError();
        Button button = (Button) empty.findViewById(R.id.bu_re);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.start();
            }
        });
    }

    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final List<AllApp.HBean> data) {
        //延迟200ms 模拟加载数据中....
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {

                mDatas = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {

                    FastFlexBean fastFlexBean = new FastFlexBean();
                    fastFlexBean.setPlatform(data.get(i).name);//设置城市名称
                    if(all_app){
                        fastFlexBean.setImgurl(data.get(i).icon);
                    }else {
                        fastFlexBean.setImgurl(data.get(i).card_icon);
                    }

                    fastFlexBean.setUuid(data.get(i).id+"");
                    mDatas.add(fastFlexBean);

                }

                mIndexBar.setmSourceDatas(mDatas)//设置数据
                        .setHeaderViewCount(mHeaderAdapter.getHeaderViewCount())//设置HeaderView数量
                        .invalidate();

                mAdapter.setDatas(mDatas);
                mHeaderAdapter.notifyDataSetChanged();
                mDecoration.setmDatas(mDatas);
            }
        }, 200);

    }


}
