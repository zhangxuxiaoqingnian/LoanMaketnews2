package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.common.widget.SelfDialog;
import com.smileflowpig.money.factory.presenter.account.UpdateContract;
import com.smileflowpig.money.factory.presenter.account.UpdatePresenter;
import com.umeng.analytics.MobclickAgent;

//修改密码
public class UpdateActivity extends PresenterActivity<UpdateContract.Presenter>
        implements UpdateContract.View {

    @BindView(R.id.edit_phone)
    EditText oldPassword;


    @BindView(R.id.edit_password)
    EditText newPassword;

    @BindView(R.id.edit_code)
    EditText secondPassword;
    private SelfDialog selfDialog;

    public static void show(Context context) {
        Intent intent = new Intent(context, UpdateActivity.class);
        context.startActivity(intent);
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
        return R.layout.activity_update;
    }

    @Override
    public void updateSuccess() {
        hideLoading();
        hideSoftKeyboard();
        finish();
    }

    @Override
    protected UpdateContract.Presenter initPresenter() {
        return new UpdatePresenter(this);
    }

    @OnClick(R.id.btn_submit)
    void submit() {
        String oldpassword = oldPassword.getText().toString();
        String newpassword = newPassword.getText().toString();
        String secondpassword = secondPassword.getText().toString();
        if(TextUtils.isEmpty(oldpassword)||TextUtils.isEmpty(newpassword)||TextUtils.isEmpty(secondpassword)){
            selfDialog = new SelfDialog(UpdateActivity.this);
            selfDialog.setTitle("温馨提示");
            selfDialog.setMessage(R.string.data_account_login_invalid_parameter2);
            selfDialog.setNoOnclickListener("确定", new SelfDialog.onNoOnclickListener() {
                @Override
                public void onNoClick() {
                    selfDialog.dismiss();
                }
            });
            selfDialog.show();
        }else {
            if(secondPassword.getText().toString().equals(newPassword.getText().toString())){
                String oldP = oldPassword.getText().toString();
                String newP = newPassword.getText().toString();
                if(newP.length()<6){
                    selfDialog = new SelfDialog(UpdateActivity.this);
                    selfDialog.setTitle("温馨提示");
                    selfDialog.setMessage(R.string.data_account_register_invalid_parameter_password);
                    selfDialog.setNoOnclickListener("确定", new SelfDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            selfDialog.dismiss();
                        }
                    });
                    selfDialog.show();

                }else {
                    mPresenter.update(oldP, newP, UpdateActivity.this);
                }

            }else{
                selfDialog = new SelfDialog(UpdateActivity.this);
                selfDialog.setTitle("温馨提示");
                selfDialog.setMessage(R.string.data_account_login_invalid_parameter3);
                selfDialog.setNoOnclickListener("确定", new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        selfDialog.dismiss();
                    }
                });
                selfDialog.show();
            }
        }


    }

    @OnClick(R.id.back)
    void backClick() {
        finish();
        hideSoftKeyboard();
    }

    // 隐藏软件盘
    private void hideSoftKeyboard() {
        // 当前焦点的View
        View view = getCurrentFocus();
        if (view == null)
            return;

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
