package money.kuxuan.platform.moneyplatfrom.activities;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import money.kuxuan.platform.common.app.PresenterActivity;
import money.kuxuan.platform.factory.presenter.account.UpdateContract;
import money.kuxuan.platform.factory.presenter.account.UpdatePresenter;
import money.kuxuan.platform.moneyplatfrom.R;

//修改密码
public class UpdateActivity extends PresenterActivity<UpdateContract.Presenter>
        implements UpdateContract.View {

    @BindView(R.id.edit_phone)
    EditText oldPassword;


    @BindView(R.id.edit_password)
    EditText newPassword;

    @BindView(R.id.edit_code)
    EditText secondPassword;

    public static void show(Context context) {
        Intent intent = new Intent(context, UpdateActivity.class);
        context.startActivity(intent);
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
        if(secondPassword.getText().toString().equals(newPassword.getText().toString())){
            String oldP = oldPassword.getText().toString();
            String newP = newPassword.getText().toString();
            mPresenter.update(oldP, newP);
        }else{
            Toast.makeText(this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
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
