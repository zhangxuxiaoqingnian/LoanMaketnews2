package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.smileflowpig.money.moneyplatfrom.Constant;
import com.smileflowpig.money.moneyplatfrom.util.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;
import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.factory.data.helper.BillHelper;
import com.umeng.analytics.MobclickAgent;


/**
 * 添加账单
 */
public class BillAddActivity extends Activity {

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    protected boolean isNeedNotch() {
        return true;
    }
    @BindView(R.id.addbill_text)
    TextView addBill_text;

    @BindView(R.id.addbill_platformimage)
    ImageView imageView;

    @BindView(R.id.addbill_platformname_text)
    TextView platform_name;

    @BindView(R.id.addbill_money_edit)
    EditText addbill_edit;

    @BindView(R.id.addbill_huankuandata_text)
    TextView huankuan_text;

    @BindView(R.id.addbill_tixingdata_text)
    TextView tixin_text;

    @BindView(R.id.addbill_queren_btn)
    Button queren_btn;


    //还款日期
    private String mHuankuan_data = null;


    //提醒日期
    private String tixing_time = null;

    //贷款平台
    private String platform_id;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_add_bill_layout;
    }


    @Override
    protected void initWidget() {
        super.initWidget();
        changedata();
        addbill_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changedata();
            }
        });
    }

    @OnClick(R.id.back)
    void back() {
        finish();
    }

    /**
     * 添加平台
     */
    @OnClick({R.id.addbill_text, R.id.addbill_platformname_text})
    void addPlatform() {
        Intent intent = new Intent(this, Activity_AddressBook.class);
        intent.putExtra("All_App", true);
        startActivityForResult(intent, Constant.Code.REQUEST_CODE);

    }


    /**
     * 设置还款日期
     */
    @OnClick(R.id.addbill_huankuandata_text)
    void selecthuankuanData() {
        showChooseTie();

    }

    /**
     * 设置提醒时间
     */
    @OnClick(R.id.addbill_tixingdata_text)
    void selecttixingnData() {
        chooseTixinData();
    }

    /**
     * 设置确认
     */
    @OnClick(R.id.addbill_queren_btn)
    void queren() {
        addBill();

    }

    TimePickerView pvTime;

    private void showChooseTie() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        //时间选择器
        if (pvTime == null) {
//            Calendar startData = Calendar.getInstance();
//            startData.set(1998,1,1);
//            Calendar endData = Calendar.getInstance();
//            startData.set(2053,1,1);
            pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    String formatType = "yyyy-MM-dd";
                    SimpleDateFormat dateFormat = new SimpleDateFormat(formatType,
                            Locale.SIMPLIFIED_CHINESE);
                    String huankuan_data = dateFormat.format(date);
                    huankuan_text.setText(huankuan_data);
                    dateFormat = new SimpleDateFormat("yyyy.MM.dd",
                            Locale.SIMPLIFIED_CHINESE);
                    mHuankuan_data = dateFormat.format(date);
                    changedata();
                }
            })
                    .setType(new boolean[]{true, true, true, false, false, false})
                    .setCancelText("取消")
                    .setSubmitText("确定")
                    .setTitleText("还款日期")
                    .setOutSideCancelable(false)
                    .isCyclic(false)
//                    .setRangDate(startData,endData)
                    .setTitleColor(Color.BLACK)
                    .setSubmitColor(Color.parseColor("#FEB727"))
                    .setCancelColor(Color.parseColor("#333333"))
                    .isCenterLabel(false)
                    .setLabel("年", "月", "日", "时", "分", "秒")
                    .isDialog(false)
                    .build();
        }

        pvTime.show();
    }


    /**
     * 提前几天
     */
    private int dayPosition = -1;


    OptionsPickerView optionsPickerView;

    private void chooseTixinData() {

        final List<String> optionsitem2 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            optionsitem2.add("提前" + (i + 1) + "天");
        }

        OptionsPickerView optionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int i, int i1, int i2, View view) {

                //提醒时间
                String tixing_data = optionsitem2.get(i);
                dayPosition = i + 1;
                tixin_text.setText(tixing_data);
                changedata();


            }
        }).setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("提醒时间")//标题
//                .setSubCalSize(18)//确定和取消文字大小
//                .setTitleSize(20)//标题文字大小
                .setTitleColor(Color.BLACK)
                .setSubmitColor(Color.parseColor("#FEB727"))
                .setCancelColor(Color.parseColor("#333333"))
//                .setTitleBgColor(0xFF333333)//标题背景颜色 Night mode
//                .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
//                .setContentTextSize(18)//滚轮文字大小
//                .setLabels("省", "市", "区")//设置选择的三级单位
//                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setCyclic(false, false, false)//循环与否
//                .setSelectOptions(1, 1, 1)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
//                .isDialog(true)//是否显示为对话框样式
                .isRestoreItem(true).build();//切换时是否还原，设置默认选中第一项。
        optionsPickerView.setPicker(optionsitem2);
        optionsPickerView.show();


    }


    /**
     * 添加账单
     */
    private void addBill() {
        if (TextUtils.isEmpty(platform_id)) {
            ToastUtil.show(this, "请选择贷款平台");
            return;
        }
        String amount = addbill_edit.getText().toString();
        if (TextUtils.isEmpty(amount)) {
            ToastUtil.show(this, "请输入金额");
            return;
        }
        if (TextUtils.isEmpty(mHuankuan_data)) {
            ToastUtil.show(this, "请选择还款日期");
            return;
        }
        if (dayPosition == -1) {
            ToastUtil.show(this, "请选择提醒日期");
            return;
        }
        queren_btn.setClickable(false);
        BillHelper.addBill(platform_id, amount, mHuankuan_data, dayPosition + "", new DataSource.Callback<Object>() {
            @Override
            public void onDataNotAvailable(int strRes) {
                queren_btn.setClickable(true);
                ToastUtil.show(BillAddActivity.this, "添加失败");

            }

            @Override
            public void onDataLoaded(Object o) {
                queren_btn.setClickable(true);
                setResult(Constant.Code.RESULT_ADDBILL_CODE);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.Code.REQUEST_CODE && resultCode == Constant.Code.RESULT_CHOOSEAPP_CODE) {
            addBill_text.setVisibility(View.INVISIBLE);
            platform_name.setVisibility(View.VISIBLE);
            SharedPreferences sharedPreferences = getSharedPreferences("DaiData", MODE_PRIVATE);
            String daiimg = sharedPreferences.getString("daiimg", null);
            String dainame = sharedPreferences.getString("dainame", null);
            platform_id = sharedPreferences.getString("daiid", null);
            platform_name.setText(dainame);
            Glide.with(this).load(daiimg).into(imageView);
            changedata();
        }
    }


    /**
     * 修改状态
     */
    private void changedata() {

        if (TextUtils.isEmpty(platform_id) || TextUtils.isEmpty(addbill_edit.getText().toString()) || TextUtils.isEmpty(mHuankuan_data) || dayPosition == -1) {
            //不可点击确认
            queren_btn.setClickable(false);
            queren_btn.setBackground(getResources().getDrawable(R.drawable.bu_yellow_new_normal_bg));
        } else {
            queren_btn.setClickable(true);
            queren_btn.setBackground(getResources().getDrawable(R.drawable.bu_yellow_new_bg));
        }

    }
}
