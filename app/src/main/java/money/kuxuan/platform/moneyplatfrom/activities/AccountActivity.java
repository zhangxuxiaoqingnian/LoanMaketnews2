package money.kuxuan.platform.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;

import money.kuxuan.platform.common.app.Activity;
import money.kuxuan.platform.common.app.Fragment;
import money.kuxuan.platform.moneyplatfrom.Constant;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.frags.account.AccountTrigger;
import money.kuxuan.platform.moneyplatfrom.frags.account.LoginFragment;
import money.kuxuan.platform.moneyplatfrom.frags.account.RegisterFragment;

//账户activity
public class AccountActivity extends Activity implements AccountTrigger, LoginFragment.onLoginListener, RegisterFragment.onRegistListener {
    private Fragment mCurFragment;
    private Fragment mLoginFragment;
    private Fragment mRegisterFragment;


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
        mCurFragment = mLoginFragment = new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.lay_container, mCurFragment)
                .commit();
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
        if(isSuccess)
        setResult(Constant.Code.RESULT_LOGINSUC_CODE);
        finish();
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
