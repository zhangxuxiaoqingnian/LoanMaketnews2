package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;
import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.factory.Constant;
import com.smileflowpig.money.factory.Factory;
import com.smileflowpig.money.factory.data.helper.AccountHelper;
import com.smileflowpig.money.factory.model.api.account.CodeModel;
import com.smileflowpig.money.factory.model.api.account.CodeRspModel;
import com.smileflowpig.money.factory.model.api.account.LoginByCodeModel;
import com.smileflowpig.money.factory.model.db.User;
import com.smileflowpig.money.factory.net.Network;
import com.smileflowpig.money.factory.util.SPUtil;
import com.smileflowpig.money.moneyplatfrom.Bean.CheckGuidJson;
import com.smileflowpig.money.moneyplatfrom.util.ToastUtil;
import com.smileflowpig.money.moneyplatfrom.weight.CheckGuildPop;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 引导页
 */
public class GuildActivity extends Activity {


    @BindView(R.id.activity_guild_num_text)
    TextView activityGuildNumText;
    @BindView(R.id.activity_guild_money_text)
    TextView activityGuildMoneyText;
    @BindView(R.id.activity_guild_time_text)
    TextView activityGuildTimeText;
    @BindView(R.id.activity_guild_phone_text)
    EditText activityGuildPhoneText;
    @BindView(R.id.activity_guild_code_edit)
    EditText activityGuildCodeEdit;
    @BindView(R.id.activity_guild_getcode_text)
    TextView activityGuildGetcodeText;
    @BindView(R.id.activity_guild_checkbox)
    CheckBox activityGuildCheckbox;

    @Override
    protected boolean isNeedNotch() {
        return false;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_guild_layout;
    }


    @Override
    protected void initWidget() {
        super.initWidget();
        SPUtil.putAndApply(this, Constant.NEEDGUID, false);
    }

    @OnClick({R.id.activity_guild_choosmoney_layout, R.id.activity_guild_choostime_layout, R.id.actiity_guild_tuijian_text, R.id.actiity_guild_jump_text, R.id.activity_guild_getcode_text, R.id.activity_guild_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_guild_choosmoney_layout:
                //选择钱
                showPop(true);
                break;
            case R.id.activity_guild_choostime_layout:
                //选择时间
                showPop(false);
                break;
            case R.id.actiity_guild_tuijian_text:
                //为我推荐
                String money = activityGuildMoneyText.getText().toString();
                String time = activityGuildTimeText.getText().toString();
                if (money.equals("请选择")) {
                    ToastUtil.show(this, "请选择借款区间");
                    return;
                }
                if (time.equals("请选择")) {
                    ToastUtil.show(this, "请选择借款时间");
                    return;
                }
                boolean checked = activityGuildCheckbox.isChecked();
                if (checked) {
                    goLogin(activityGuildPhoneText.getText().toString(), activityGuildCodeEdit.getText().toString());
                } else {
                    ToastUtil.show(this, "请先同意协议");
                }
                break;
            case R.id.actiity_guild_jump_text:
                //跳过推荐
                finish();
                break;
            case R.id.activity_guild_getcode_text:
//获取验证码
                getCod();

                break;
            case R.id.activity_guild_check:
                if (activityGuildCheckbox.isChecked()) {
                    activityGuildCheckbox.setChecked(false);
                } else {
                    activityGuildCheckbox.setChecked(true);
                }

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.acitivity_guild_xieyi)
    public void onViewClicked() {
        Intent intent = new Intent(this, Webview_protocol.class);
        startActivity(intent);
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            activityGuildGetcodeText.setText("获取验证码");
            activityGuildGetcodeText.setClickable(true);

        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            activityGuildGetcodeText.setClickable(false);//防止重复点击
            activityGuildGetcodeText.setText("重新发送" + millisUntilFinished / 1000 + "s");
        }
    }

    CheckGuildPop pop;

    private void showPop(boolean isTime) {
        if (pop == null) {
            pop = new CheckGuildPop(this);
            pop.setCheckListener(new CheckGuildPop.OnCheckClickListener() {
                @Override
                public void onCheck(CheckGuidJson checkGuidJson, boolean isTime) {
                    if (isTime) {
                        activityGuildMoneyText.setText(checkGuidJson.getName());
                    } else {
                        activityGuildTimeText.setText(checkGuidJson.getName());
                    }
                }
            });

        }
        pop.setTime(isTime);
        pop.showAtLocation(findViewById(R.id.activity_guild_layout), Gravity.BOTTOM, 0, 0);
    }

    TimeCount timeCount;

    private void getCod() {
        String s = activityGuildPhoneText.getText().toString();
        if (TextUtils.isEmpty(s)) {
            //请输入手机号

            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (timeCount == null) {
            timeCount = new TimeCount(60000, 1000);
        }
        showOnlyDialogLoadding();
        getCode(s);
    }

    private void getCode(String phone) {
        CodeModel model = new CodeModel(phone, "1", "1");
        AccountHelper.loginByCode(model, new DataSource.Callback<CodeRspModel>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                hideOnDialogLoading();

            }

            @Override
            public void onDataLoaded(CodeRspModel codeRspModel) {
                hideOnDialogLoading();
                timeCount.start();
                Toast.makeText(GuildActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timeCount != null)
            timeCount.onFinish();
    }


    private void goLogin(String phone, String code) {
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        showOnlyDialogLoadding();
        LoginByCodeModel model = new LoginByCodeModel(phone, code, Network.channelId);
        AccountHelper.loginToCode(model, new DataSource.Callback<User>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                hideOnDialogLoading();
                finish();
            }

            @Override
            public void onDataLoaded(User user) {
                hideOnDialogLoading();
                if (user != null) {
                    SPUtil.putAndApply(Factory.app(), Constant.UserInfo.SESSIONID, user.getPHPSESSID());
                    SPUtil.putAndApply(Factory.app(), Constant.UserInfo.USERNAME, user.getPhone());
                    SPUtil.putAndApply(Factory.app(), Constant.UserInfo.PASSWORD, "");
                    SPUtil.putAndApply(Factory.app(), Constant.UserInfo.ISEXITE, false);
                    if (user.getUser_token() != null)
                        SPUtil.putAndApply(Factory.app(), Constant.UserInfo.CODETOKEN, user.getUser_token());
                }
                finish();
            }
        });


    }
}
