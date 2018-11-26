package com.smileflowpig.money.moneyplatfrom.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.smileflowpig.money.moneyplatfrom.util.DisplayUtils2;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.widget.EmptyView;
import com.smileflowpig.money.common.widget.recycler.RecyclerAdapter;
import com.smileflowpig.money.factory.model.db.Message;
import com.smileflowpig.money.factory.presenter.message.MessageContract;
import com.smileflowpig.money.factory.presenter.message.MessagePresenter;
import com.umeng.analytics.MobclickAgent;

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
    private int cont;

    List<Message> messageList;
    private int coverd;

    /**
     * 消息界面的入口
     * @param context
     */
    public static void show(Context context,int nums){
        Intent intent = new Intent(context, MessageActivity.class);
        intent.putExtra("coverd",nums);
        context.startActivity(intent);
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        coverd = bundle.getInt("coverd", -1);
        return super.initArgs(bundle);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_message;
    }
    @Override
    protected boolean isNeedNotch() {
        return true;
    }
    @Override
    protected void initWidget() {
        super.initWidget();

        mPresenter.start();
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new DisplayUtils2.SpacesItemDecoration());
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

        if(coverd==1){
            mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<Message>() {
                @Override
                public void onItemClick(RecyclerAdapter.ViewHolder holder, Message message,int pos) {
                    DetailActivity.show(MessageActivity.this,message.getProduct_id(),"news",0,11);
                }
            });
        }else {
            mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<Message>() {
                @Override
                public void onItemClick(RecyclerAdapter.ViewHolder holder, Message message,int pos) {
                    DetailActivity.show(MessageActivity.this,message.getProduct_id(),"news",0,12);
                }
            });
        }

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


