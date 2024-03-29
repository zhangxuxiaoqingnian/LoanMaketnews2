package com.smileflowpig.money.moneyplatfrom.activities;


import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import net.qiujuer.genius.ui.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.app.PresenterActivity;
import com.smileflowpig.money.factory.presenter.user.ActionContract;
import com.smileflowpig.money.factory.presenter.user.ActionPresenter;
import com.umeng.analytics.MobclickAgent;

/**
 * 意见反馈activity
 */
public class ActionActivity extends PresenterActivity<ActionContract.Presenter>
implements  ActionContract.View{
    @BindView(R.id.action_edittext)
    EditText action_edittext;

    @BindView(R.id.count_tv)
    TextView count_tv;
    int count  = 100;

    @BindView(R.id.action_button)
    Button action_button;
    @Override
    protected boolean isNeedNotch() {
        return true;
    }
    /**
     * 意见反馈activity的入口
     * @param context
     */
  public static void show(Context context){
      Intent intent = new Intent(context, ActionActivity.class);
      context.startActivity(intent);
  }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_action;
    }

    //顶部导航栏返回键点击事件
    @OnClick(R.id.back)
    void back() {

        finish();
        hideSoftKeyboard();
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
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
    @Override
    protected void initWidget() {
        super.initWidget();
        //监听字数变化的操作
        action_button.setEnabled(false);
        action_button.setBackgroundResource(R.drawable.button_alpha_bu);
        action_edittext.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    action_button.setEnabled(true);
                    action_button.setBackgroundResource(R.drawable.bu_yellow_bg);
                }else{
                    action_button.setEnabled(false);
                    action_button.setBackgroundResource(R.drawable.button_alpha_bu);
                }
                int number = count - s.length();
                count_tv.setText("还可以输入"+number+"字");
                selectionStart = action_edittext.getSelectionStart();
                selectionEnd = action_edittext.getSelectionEnd();
                //删除多余输入的字（不会显示出来）
                if (temp.length() > count) {
                    s.delete(selectionStart - 1, selectionEnd);
                    action_edittext.setText(s);
                }
                //设置光标在最后
                action_edittext.setSelection(s.length());
            }
        });
    }

    /**
     * button点击事件
     */
    @OnClick(R.id.action_button)
    void onAction(){
        String action = action_edittext.getText().toString();
        mPresenter.send(action);
    }

    @Override
    public void sendSuccess() {

        finish();
    }

    @Override
    protected ActionContract.Presenter initPresenter() {
        return new ActionPresenter(this);
    }
}
