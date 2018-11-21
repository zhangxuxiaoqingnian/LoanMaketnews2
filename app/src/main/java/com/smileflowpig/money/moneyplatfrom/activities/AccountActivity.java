package com.smileflowpig.money.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.smileflowpig.money.moneyplatfrom.Constant;
import com.smileflowpig.money.moneyplatfrom.frags.account.AccountTrigger;
import com.smileflowpig.money.moneyplatfrom.frags.account.LoginFragment;
import com.smileflowpig.money.moneyplatfrom.frags.account.NewLoginFragment;
import com.smileflowpig.money.moneyplatfrom.frags.account.RegisterFragment;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.Activity;
import com.smileflowpig.money.common.app.Fragment;
import com.smileflowpig.money.moneyplatfrom.frags.main.state.CreditFragment;
import com.smileflowpig.money.moneyplatfrom.util.HongbaoOperator;
import com.umeng.analytics.MobclickAgent;

//账户activity
public class AccountActivity extends Activity implements AccountTrigger, LoginFragment.onLoginListener, RegisterFragment.onRegistListener {
    private Fragment mCurFragment;
    private Fragment mLoginFragment;
    private Fragment mRegisterFragment;
    private SharedPreferences sp;

    private boolean isHongbao = false;


    /**
     * 账户Activity显示的入口
     *
     * @param context Context
     */
    public static void show(Context context) {
        context.startActivity(new Intent(context, AccountActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        // 初始化Fragment
        mCurFragment = mLoginFragment = new NewLoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.lay_container, mCurFragment)
                .commit();

        sp = getSharedPreferences("Deng", MODE_PRIVATE);
        isHongbao = getIntent().getBooleanExtra("hongbao", false);
    }

    @Override
    public void triggerView() {
        Fragment fragment;
        if (mCurFragment == mLoginFragment) {
            if (mRegisterFragment == null) {
                //默认情况下为null，
                //第一次之后就不为null了
                mRegisterFragment = new RegisterFragment();
            }
            fragment = mRegisterFragment;
        } else {
            // 因为默认请求下mLoginFragment已经赋值，无须判断null
            fragment = mLoginFragment;
        }

        // 重新赋值当前正在显示的Fragment
        mCurFragment = fragment;
        // 切换显示ø
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.lay_container, fragment)
                .commit();
    }

    @Override
    public void onLoginSuccess(boolean isSuccess) {
        if (isSuccess) {
            Toast.makeText(AccountActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            sp.edit().putBoolean("liulang", true).commit();
            if (isHongbao) {
                setResult(HongbaoOperator.HONGBAO_RESULT_LOGIN_CODE);
            } else if (getIntent().getBooleanExtra("zhengxin", false)) {
                setResult(CreditFragment.ZHENGXIN_RESULT_CODE);
            } else {

                setResult(Constant.Code.RESULT_LOGINSUC_CODE);
            }
            finish();
        }
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
    public void onRegistSuccess(boolean isGoToLogin) {
        if (isGoToLogin) {
            //返回登录页
            mCurFragment = mLoginFragment;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.lay_container, mLoginFragment)
                    .commit();
        } else {
            setResult(Constant.Code.RESULT_LOGINSUC_CODE);
            finish();
        }

    }
}
