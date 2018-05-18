package money.kuxuan.platform.moneyplatfrom.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.factory.presenter.addplatform.AddPlatformPresenter;
import money.kuxuan.platform.factory.presenter.addplatform.AddPlatfromContract;
import money.kuxuan.platform.moneyplatfrom.Adapter.FastFlexAdapter;
import money.kuxuan.platform.moneyplatfrom.Adapter.HeaderRecyclerAndFooterWrapperAdapter;
import money.kuxuan.platform.moneyplatfrom.Adapter.ViewHolder;
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


    @Override
    protected void initWidget() {
        super.initWidget();

        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));

        mAdapter = new FastFlexAdapter(this, mDatas);
        mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(mAdapter) {
            @Override
            protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId, Object o) {
                holder.setText(R.id.tv_platform, (String) o);
            }
        };
        //        mHeaderAdapter.setHeaderView(R.layout.item_city, "测试头部");

        mRv.setAdapter(mHeaderAdapter);
        mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, mDatas).setHeaderViewCount(mHeaderAdapter.getHeaderViewCount()));

        //如果add两个，那么按照先后顺序，依次渲染。
        mRv.addItemDecoration(new DividerItemDecoration(Activity_AddressBook.this, DividerItemDecoration.VERTICAL_LIST));

        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar

        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager

        //        initDatas(getResources().getStringArray(R.array.provinces));


        String[] strArr = {"水电费","总积分的","覆盖面通过","水电费GV","从VB","f的说法是个","问问人内","那地方公开","电饭煲个体","问题",
                "旁边","十多个","区别","白豆腐干","你干活","让他个人","时代光华机遇","版本","每个月","在v","dfg","ujyj","nhgt"};

        initDatas(strArr);

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


    /**
     * 组织数据源
     *
     * @param data
     * @return
     */
    private void initDatas(final String[] data) {
        //延迟200ms 模拟加载数据中....
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {

                mDatas = new ArrayList<>();
                for (int i = 0; i < data.length; i++) {

                    FastFlexBean fastFlexBean = new FastFlexBean();
                    fastFlexBean.setPlatform(data[i]);//设置城市名称
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
