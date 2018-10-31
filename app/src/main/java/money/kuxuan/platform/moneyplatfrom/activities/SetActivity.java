package money.kuxuan.platform.moneyplatfrom.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;
import money.kuxuan.platform.common.SecondEvent;
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.factory.Constant;
import money.kuxuan.platform.factory.netword.NetRequestUtils;
import money.kuxuan.platform.factory.presenter.account.ExistContract;
import money.kuxuan.platform.factory.presenter.account.ExistPresenter;
import money.kuxuan.platform.moneyplatfrom.R;
import money.kuxuan.platform.moneyplatfrom.helper.SPUtil;


public class SetActivity extends PresenterActivity<ExistContract.Presenter>
        implements ExistContract.View {

    private SharedPreferences sp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


    }

    public static void show(Context context) {
        Intent intent = new Intent(context, SetActivity.class);
        context.startActivity(intent);
    }

    //顶部导航栏返回键点击事件
    @OnClick(R.id.back)
    void back() {
        finish();
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_set;
    }


    @OnClick(R.id.exist_button)
    void onExist() {
        mPresenter.exist();
        sp = getSharedPreferences("Deng",MODE_PRIVATE);
        sp.edit().clear().commit();
    }

    @Override
    public void ExistSuccess() {
        EventBus.getDefault().post(new SecondEvent("登录/注册"));
        SPUtil.clear(this);
        money.kuxuan.platform.factory.util.SPUtil.clear(this);
        money.kuxuan.platform.factory.util.SPUtil.putAndApply(this, Constant.UserInfo.ISEXITE, true);
        finish();

    }

    @OnClick(R.id.about)
    void aboutClick() {
        AboutActivity.show(this);
    }

    @Override
    protected ExistContract.Presenter initPresenter() {
        return new ExistPresenter(this);
    }

    @OnClick(R.id.update)
    void clickUpdate() {
        UpdateActivity.show(this);
    }
}
