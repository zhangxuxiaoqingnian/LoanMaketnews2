package money.kuxuan.platform.moneyplatfrom.activities;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.common.widget.EmptyView;
import money.kuxuan.platform.common.widget.recycler.RecyclerAdapter;
import money.kuxuan.platform.factory.model.db.Message;
import money.kuxuan.platform.factory.presenter.message.MessageContract;
import money.kuxuan.platform.factory.presenter.message.MessagePresenter;
import money.kuxuan.platform.moneyplatfrom.R;

//消息界面
public class MessageActivity extends PresenterActivity<MessageContract.Presenter>
implements MessageContract.View{

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @BindView(R.id.empty)
    EmptyView mEmptyView;
    private RecyclerAdapter<Message> mAdapter;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.tv_title)
    TextView tv_title;

    private static final String TAG = "MessageActivity";

    List<Message> messageList;

    /**
     * 消息界面的入口
     * @param context
     */
    public static void show(Context context){
        Intent intent = new Intent(context,MessageActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        mPresenter.start();
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mAdapter = new RecyclerAdapter<Message>() {
            @Override
            protected int getItemViewType(int position, Message message) {
                // 返回cell的布局id
                return R.layout.cell_message_list;
            }

            @Override
            protected ViewHolder<Message> onCreateViewHolder(View root, int viewType) {
                return new MessageActivity.ViewHolder(root);
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
                mPresenter.relodeMessage();
            }
        });
        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<Message>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, Message message) {
                DetailActivity.show(MessageActivity.this,message.getProduct_id(),"news");
            }
        });
    }

    @Override
    public void setMessage(List<Message> messgae) {
        hideLoading();
        mAdapter.replace(messgae);
        mPlaceHolderView.triggerOkOrEmpty(mAdapter.getItemCount() > 0);
    }

    /**
     * 无数据时的回调
     */
    @Override
    public void noData() {
        refreshLayout.setLoadmoreFinished(true);
    }

    @Override
    protected MessageContract.Presenter initPresenter() {
        return new MessagePresenter(this);
    }

    /**
     * 下拉加载
     * @param messageList
     */
    @Override
    public void refresh(List<Message> messageList) {
        mAdapter.add(messageList);
        mAdapter.notifyDataSetChanged();
        refreshLayout.finishLoadmore(true);
    }


    /**
     * 每一个Cell的布局操作
     */
    class ViewHolder extends RecyclerAdapter.ViewHolder<Message> {
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.mes_img)
        ImageView mes_img;
        @BindView(R.id.content_tv)
        TextView content_tv;
        @BindView(R.id.title_tv)
        TextView title_tv;


        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(Message message) {
            time.setText(message.getCreate_time());

            Glide.with(MessageActivity.this).load(message.getImage_url())
                    .into(mes_img);
            content_tv.setText(message.getContent());
            title_tv.setText(message.getTitle());
        }



    }

    //顶部导航栏返回键点击事件
    @OnClick(R.id.back)
    void back() {

        finish();
    }
}


