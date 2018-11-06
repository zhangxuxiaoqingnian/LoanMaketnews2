package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.smileflowpig.money.moneyplatfrom.Adapter.BillManagerAdapter;
import com.smileflowpig.money.moneyplatfrom.Adapter.BillManagerComAdapter;
import com.smileflowpig.money.moneyplatfrom.App;
import com.smileflowpig.money.moneyplatfrom.Constant;
import com.smileflowpig.money.moneyplatfrom.util.AlarmOpertor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.widget.SelfDialog;
import com.smileflowpig.money.factory.bean.BillData;
import com.smileflowpig.money.factory.bean.BillManagerBean;
import com.smileflowpig.money.factory.presenter.bill.BillContract;
import com.smileflowpig.money.factory.presenter.bill.BillPresenter;


/**
 * 账单管理
 * create by xiaoxie
 */
public class BillManagerActivity extends PresenterActivity<BillContract.Presenter> implements BillContract.View {


    @BindView(R.id.billmanager_radiogroup)
    RadioGroup radioGroup;

    @BindView(R.id.daihuan_moneyRbtn)
    RadioButton daiHuan_rbtn;


    @BindView(R.id.yihuan_moneyRbtn)
    RadioButton yihuan_rbtn;

    @BindView(R.id.bill_recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.bill_com_recyclerView)
    RecyclerView comRecyclerView;

    private int page = 1;
    private int comPage = 1;
    private int status = 1;
    private boolean isHaveNext = false;
    private boolean isHaveNextCom = false;

    private int currentPosition = 0;


    @Override
    protected BillPresenter initPresenter() {
        return new BillPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_bill_layout;
    }


    private int currentType = 1;

    @OnClick(R.id.back)
    void goBack() {
        finish();
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        initAdapter();
        initRadioGroup();
    }


    private void initRadioGroup() {
        radioGroup.clearCheck();
        daiHuan_rbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //选择代还账单
                if (b) {
                    changeType(1);
                }
            }
        });

        yihuan_rbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //选择已还账单
                if (b) {
                    changeType(2);
                }
            }
        });
        daiHuan_rbtn.setChecked(true);
    }


    BillManagerAdapter adapter;
    //已还清账单
    BillManagerComAdapter comAdapter;

    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager cominearLayoutManager = new LinearLayoutManager(this);
        cominearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new BillManagerAdapter(R.layout.item_bill_layout);
        comAdapter = new BillManagerComAdapter(R.layout.item_bill_com_layout);
        adapter.bindToRecyclerView(recyclerView);
        comAdapter.bindToRecyclerView(comRecyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        comRecyclerView.setLayoutManager(cominearLayoutManager);
        recyclerView.setAdapter(adapter);
        comRecyclerView.setAdapter(comAdapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (isHaveNext) {
                    page++;
                    getData(page + "", status + "", false);
                } else {
                    adapter.loadMoreEnd(true);
                }
            }
        });
        adapter.disableLoadMoreIfNotFullPage();
        comAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (isHaveNextCom) {
                    comPage++;
                    getData(comPage + "", status + "", false);
                } else {
                    comAdapter.loadMoreEnd(true);
                }
            }
        });
        comAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BillManagerBean item = comAdapter.getItem(position);
                Intent intent = new Intent(BillManagerActivity.this, BillDetialActivity.class);
                intent.putExtra("platform_id", item.getPlatform_id());
                intent.putExtra("id", item.getId());
                startActivityForResult(intent, Constant.Code.REQUEST_CODE);


            }
        });

        adapter.setGoListener(new BillManagerAdapter.OnGoListener() {
            @Override
            public void goToDetial(BillManagerBean item) {
                Intent intent = new Intent(BillManagerActivity.this, BillDetialActivity.class);
                intent.putExtra("platform_id", item.getPlatform_id());
                intent.putExtra("id", item.getId());
                startActivityForResult(intent, Constant.Code.REQUEST_CODE);

            }

            @Override
            public void goTosettingyihuan(BillManagerBean item) {
                showChangeStatusDialog(item.getId());

            }

            @Override
            public void goToAdd() {
                Intent inet = new Intent(BillManagerActivity.this, BillAddActivity.class);
                startActivityForResult(inet, Constant.Code.REQUEST_CODE);

            }
        });
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
                    showLoading();
                    mPresenter.changeStatus(id, "1");
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

    @Override
    public void showError(int str) {
        super.showError(str);
    }


    private void getData(String page, String status, boolean isRefresh) {
        mPresenter.getLists(page, status, isRefresh);
    }

    private void changeType(int type) {
        switch (type) {
            case 1:
                currentPosition = 0;
                currentType = 1;
                daiHuan_rbtn.setTextColor(Color.WHITE);
                yihuan_rbtn.setTextColor(Color.parseColor("#ffb43b"));
                status = 0;
                break;
            case 2:
                currentPosition = 1;
                currentType = 2;
                yihuan_rbtn.setTextColor(Color.WHITE);
                daiHuan_rbtn.setTextColor(Color.parseColor("#ffb43b"));
                status = 1;
                break;
        }
        getData(page + "", status + "", false);
    }

    @Override
    public void setData(BillData billData, boolean isRefresh) {
        // TODO: 2018/11/4 填充数据
        if (currentPosition == 0) {
            if (recyclerView.getVisibility() != View.VISIBLE)
                recyclerView.setVisibility(View.VISIBLE);
            if (comRecyclerView.getVisibility() != View.INVISIBLE)
                comRecyclerView.setVisibility(View.INVISIBLE);
            if (adapter != null) {
                if (isRefresh)
                    adapter.loadMoreComplete();
                List<BillManagerBean> data = adapter.getData();
                if (data != null && data.size() != 0) {
                    data.remove(data.size() - 1);
                }
                if (billData != null && billData.getData() != null && billData.getData().size() != 0) {
                    isHaveNext = billData.getPageInfoBean().hasNext;
                    billData.getData().add(new BillManagerBean().setTrueData(false));
                    if (isRefresh) {
                        adapter.addData(billData.getData());
                    } else {
                        adapter.setNewData(billData.getData());
                    }
                    addAlarLists(billData.getData());
                } else {
                    if (isRefresh) {
                        ArrayList<BillManagerBean> list = new ArrayList<>();
                        list.add(new BillManagerBean().setTrueData(false));
                        adapter.setNewData(list);
                    }
                }
            }

        } else {
            if (comAdapter != null) {
                if (comRecyclerView.getVisibility() != View.VISIBLE)
                    comRecyclerView.setVisibility(View.VISIBLE);
                if (recyclerView.getVisibility() != View.INVISIBLE)
                    recyclerView.setVisibility(View.INVISIBLE);
                if (isRefresh)
                    comAdapter.loadMoreComplete();
                if (billData != null && billData.getData() != null && billData.getData().size() != 0) {
                    isHaveNextCom = billData.getPageInfoBean().hasNext;
                    if (isRefresh) {
                        comAdapter.addData(billData.getData());
                    } else {
                        comAdapter.setNewData(billData.getData());
                    }

                }
            }
        }
    }

    @Override
    public void changeStatus(String id, String status) {
        hideDialogLoading();
        List<BillManagerBean> data = adapter.getData();
        int position = -1;
        for (int i = 0; i < data.size(); i++) {
            if (i == data.size() - 1) {
                break;
            }
            if (data.get(i).getId().equals(id)) {
                position = i;
                data.get(i).setStatus("1");
                break;
            }
        }
        if (position != -1) {
            data.remove(position);
            adapter.notifyDataSetChanged();
        }

    }


    private void addAlarLists(final ArrayList<BillManagerBean> lists) {
        for (BillManagerBean b : lists) {
            addAlarm(b);
        }

    }

    /**
     * 添加闹铃
     *
     * @param billManagerBean
     */
    private void addAlarm(BillManagerBean billManagerBean) {
        if (billManagerBean == null) {
            return;
        }
        if (!billManagerBean.isTrueData()) {
            return;
        }
        if (billManagerBean.getStatus().equals("1")) {
            return;
        }
        long timemillis = 0;
        String due_date = billManagerBean.getDue_date();
        try {
            DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
            Date date = df.parse(due_date);
            timemillis = date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (timemillis != 0) {
            String remind_time = billManagerBean.getRemind_time();
            if (TextUtils.isEmpty(remind_time))
                return;
            try {
                int remind = Integer.parseInt(remind_time);
                timemillis = timemillis - (remind * 24 * 60 * 60 * 1000);
                if (timemillis <= System.currentTimeMillis()) {
                    return;
                }
                //测试
//                timemillis = System.currentTimeMillis() + 2 * 60 * 1000;
                AlarmOpertor.getInstance(App.context).setAlarm(timemillis, "还款提醒", "温馨提示：还有"+remind_time+"天就是您"+billManagerBean.getPlatform_name()+"产品的还款日了，快去还款吧！");
            } catch (Exception e) {

            }

        }

    }

    @Override
    public void showNoData() {
        if (currentPosition == 0) {
            if (recyclerView.getVisibility() != View.VISIBLE)
                recyclerView.setVisibility(View.VISIBLE);
            if (comRecyclerView.getVisibility() != View.INVISIBLE)
                comRecyclerView.setVisibility(View.INVISIBLE);
            if (adapter != null) {
                ArrayList<BillManagerBean> list = new ArrayList<>();
                list.add(new BillManagerBean().setTrueData(false));
                adapter.setNewData(list);
            }
        } else {
            if (comRecyclerView.getVisibility() != View.VISIBLE)
                comRecyclerView.setVisibility(View.VISIBLE);
            if (recyclerView.getVisibility() != View.INVISIBLE)
                recyclerView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.Code.REQUEST_CODE) {

            if (resultCode == Constant.Code.RESULT_DELETEBILL_CODE) {
//                List<BillManagerBean> list = adapter.getData();
//                int position = -1;
//                for (int i = 0; i < list.size(); i++) {
//                    if (list.get(i).getId().equals(delete_id)) {
//                        position = i;
//                        break;
//                    }
//                }
//                if (position != -1) {
//                    list.remove(position);
//                    adapter.notifyDataSetChanged();
//                }
                refresh();

            } else if (resultCode == Constant.Code.RESULT_DELETEBILL_CODE) {
                //刷新
                refresh();

            } else if (resultCode == Constant.Code.RESULT_ADDBILL_CODE) {
                //刷新
                refresh();
            }
        }
    }

    private void refresh() {
        changeType(currentType);
    }
}
