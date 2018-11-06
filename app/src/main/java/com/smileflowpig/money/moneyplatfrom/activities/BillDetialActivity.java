package com.smileflowpig.money.moneyplatfrom.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smileflowpig.money.moneyplatfrom.Adapter.BillDetialAdapter;
import com.smileflowpig.money.moneyplatfrom.Constant;
import com.smileflowpig.money.moneyplatfrom.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;
import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.common.widget.SelfDialog;
import com.smileflowpig.money.factory.bean.BillDetialBean;
import com.smileflowpig.money.factory.bean.BillDetialData;
import com.smileflowpig.money.factory.bean.BillStatusData;
import com.smileflowpig.money.factory.data.helper.BillHelper;


/**
 * 账单详情
 */
public class BillDetialActivity extends Activity {


    @BindView(R.id.activity_billdetial_daihuanmoney_text)
    TextView daihuan_text;
    @BindView(R.id.activity_billdetial_jiekuanmoney_text)
    TextView jiekuan_text;
    @BindView(R.id.activity_billdetial_yihuanmoney_text)
    TextView yihuan_text;

    @BindView(R.id.activity_bill_detial_recyclerView)
    RecyclerView recyclerView;


    private String id;
    private String platform_id;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_billdetial_layout;
    }


    @Override
    protected void initWidget() {
        super.initWidget();
        id = getIntent().getStringExtra("id");
        platform_id = getIntent().getStringExtra("platform_id");
        initAdapter();
        getDetialData(page + "", platform_id, false);

    }

    /**
     * 删除账单
     */
    @OnClick(R.id.tv_title_delete)
    void deletePlat() {
        delete();
    }

    @OnClick(R.id.back)
    void goToback() {
        finish();
    }

    private void delete() {
        BillHelper.deleteBill(id, new DataSource.Callback<Object>() {
            @Override
            public void onDataNotAvailable(int strRes) {
                ToastUtil.show(BillDetialActivity.this, "删除失败");
            }

            @Override
            public void onDataLoaded(Object o) {
                ToastUtil.show(BillDetialActivity.this, "删除成功");
                setResult(Constant.Code.RESULT_DELETEBILL_CODE);
                finish();

            }
        });

    }


    private int page = 1;
    private boolean isHaveNext = false;

    BillDetialAdapter adapter;

    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new BillDetialAdapter(R.layout.item_bill_detial_layout);
        adapter.bindToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnSettingListener(new BillDetialAdapter.OnSettingClickListener() {
            @Override
            public void onChangeStatus(BillDetialBean billDetialBean) {
                if (billDetialBean.getStatus().equals("0")) {
                    // TODO: 2018/11/4 设置已还
                    showChangeStatusDialog(billDetialBean.getId());
                }
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (isHaveNext) {
                    page++;
                    getDetialData(page + "", platform_id, true);
                } else {
                    adapter.loadMoreEnd(true);
                }
            }
        });
    }

    /**
     * 获取详情
     *
     * @param page
     * @param id
     * @param isLoadMore
     */
    private void getDetialData(String page, String id, final boolean isLoadMore) {
        BillHelper.getDetial(page, id, new DataSource.Callback<BillDetialData>() {
            @Override
            public void onDataNotAvailable(int strRes) {

            }

            @Override
            public void onDataLoaded(BillDetialData billDetialData) {
                if (billDetialData != null)
                    setData(billDetialData, isLoadMore);

            }
        });
    }


    private void setData(BillDetialData billDetialData, boolean isLoadMore) {
        if (isLoadMore) {
            if (billDetialData != null && billDetialData.getBill_list() != null) {
                adapter.addData(billDetialData.getBill_list());
                isHaveNext = billDetialData.getPageInfo().hasNext;
            }
        } else {
            if (billDetialData != null && billDetialData.getAmount() != null) {
                if (!TextUtils.isEmpty(billDetialData.getAmount().getAwait_amount()))
                    daihuan_text.setText("￥" + billDetialData.getAmount().getAwait_amount());
                else
                    daihuan_text.setText("￥0");
                if (!TextUtils.isEmpty(billDetialData.getAmount().getAlready_amount()))
                    yihuan_text.setText("￥" + billDetialData.getAmount().getAlready_amount());
                else
                    yihuan_text.setText("￥0");
                if (!TextUtils.isEmpty(billDetialData.getAmount().getAll_amount()))
                    jiekuan_text.setText("￥" + billDetialData.getAmount().getAll_amount());
                else
                    jiekuan_text.setText("￥0");
            }
            if (billDetialData != null && billDetialData.getBill_list() != null) {
                adapter.setNewData(billDetialData.getBill_list());

            }
            if (billDetialData != null) {
                if (billDetialData.getPageInfo() != null) {
                    isHaveNext = billDetialData.getPageInfo().hasNext;
                }
            }
        }
    }


    SelfDialog selfDialog;

    private void showChangeStatusDialog(final String id) {
        if (selfDialog == null) {
            selfDialog = new SelfDialog(this);
            selfDialog.setTitle("提示");
            selfDialog.setMessage(R.string.billchange_message);
            selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
                @Override
                public void onYesClick() {

                    changeStatus(id);
                    selfDialog.dismiss();
                }
            });
            selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                @Override
                public void onNoClick() {
                    selfDialog.dismiss();
                }
            });
        } else {
            selfDialog.show();
        }

    }

    /**
     * @param id
     */
    private void changeStatus(final String id) {
        BillHelper.changeStatus(id, "1", new DataSource.Callback<BillStatusData>() {
            @Override
            public void onDataNotAvailable(int strRes) {

            }

            @Override
            public void onDataLoaded(BillStatusData billStatusData) {
                if (billStatusData != null) {
                    setResult(Constant.Code.RESULT_DELETEBILL_CODE);
                    List<BillDetialBean> data = adapter.getData();
                    for (BillDetialBean da : data) {
                        if (da.getId().equals(id)) {
                            da.setStatus("1");
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

            }
        });

    }
}
