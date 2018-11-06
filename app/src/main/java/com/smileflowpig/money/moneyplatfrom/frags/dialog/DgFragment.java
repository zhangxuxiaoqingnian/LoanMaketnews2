package com.smileflowpig.money.moneyplatfrom.frags.dialog;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.smileflowpig.money.moneyplatfrom.helper.DensityUtil;

import net.qiujuer.genius.ui.widget.Loading;

import com.smileflowpig.money.R;
import com.smileflowpig.money.common.widget.TextWatcherAdapter;

/**

 */
public class DgFragment extends DialogFragment  {
    private EditText edit_phone;
    private EditText edit_password;
    private TextView code;
    private Loading loading;
    private Button button;
    private TimeCount time;
    private TextView accountLeft;
    private TextView accountRight;
    private boolean flag = false;
    private DialogClick dialogClickListener;
    private ImageView close;
    private boolean isTime = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final Window window = getDialog().getWindow();
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_dialog, ((ViewGroup) window.findViewById(android.R.id.content)),false);
        int width = DensityUtil.dip2px(getActivity(),300);
        int height = DensityUtil.dip2px(getActivity(),270);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(width, height);//这2行,和上面的一样,注意顺序就行;
        initView(view);
        initData();
        return view;
    }

    public void setDialogLisener(DialogClick dialogListener){
        this.dialogClickListener = dialogListener;
    }

    private void initData() {
        button.setEnabled(false);
        edit_password.addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable editable) {
                if(edit_phone.getText().length()>0){
                    if(editable.length()>0){
                        button.setEnabled(true);
                        button.setBackgroundResource(R.color.bu_yellow_bg);
                    }else{
                        button.setEnabled(false);
                        button.setBackgroundResource(R.color.textThird);
                    }
                }
            }
        });


        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogClickListener.codePush(edit_phone.getText().toString(),"1");
            }
        });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!flag){
                        dialogClickListener.dialog(edit_phone.getText().toString(),edit_password.getText().toString());
                    }else{
                        dialogClickListener.dialogLogin(edit_phone.getText().toString(),edit_password.getText().toString());
                    }

                }
            });

        accountLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag){
                    accountLeft.setText("手机验证码登陆");
                    code.setVisibility(View.GONE);
                    edit_password.setHint("请输入密码");
                    flag =!flag;
                    accountRight.setVisibility(View.GONE);
                }else{
                    accountLeft.setText("账号密码验证");
                    code.setVisibility(View.VISIBLE);
                    edit_password.setHint("请输入验证码");
                    flag =!flag;
                    if(isTime)
                    accountRight.setVisibility(View.VISIBLE);
                }

            }
        });


        accountRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogClickListener.LoginSound(edit_phone.getText().toString());
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = new RotateAnimation(0, 360,Animation.RELATIVE_TO_SELF,
                        0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                animation.setDuration(500);

                animation.setFillAfter(true);//设置为true，动画转化结束后被应用
                close.startAnimation(animation);//开始动画
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                                time.cancel();
                                getDialog().dismiss();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });

    }
    public void setVisible(boolean visible){
        if(visible){
            accountRight.setVisibility(View.VISIBLE);
        }else{
            accountRight.setVisibility(View.GONE);
        }


    }
    private void initView(View root){
        edit_password = (EditText) root.findViewById(R.id.edit_password);
        edit_phone = (EditText) root.findViewById(R.id.edit_phone);
        code = (TextView) root.findViewById(R.id.code);
        loading = (Loading) root.findViewById(R.id.loading);
        button = (Button) root.findViewById(R.id.btn_submit);
        accountLeft = (TextView) root.findViewById(R.id.accountleft);
        accountRight = (TextView) root.findViewById(R.id.accountright);
        close = (ImageView) root.findViewById(R.id.close);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });
        time = new TimeCount(60000, 1000);
    }


    public interface DialogClick {
        void dialog(String phone, String code);
        void codePush(String phone, String smstype);
        void dialogLogin(String phone, String password);
        void LoginSound(String phone);
    }


    public void setCodeStart(){
        time.start();
        accountRight.setEnabled(false);
    }


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            code.setText("获取验证码");
            code.setTextColor(getResources().getColor(R.color.textPrimary));
            code.setClickable(true);
            accountRight.setVisibility(View.VISIBLE);
            accountRight.setEnabled(true);
            isTime = true;
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            code.setClickable(false);//防止重复点击
            code.setTextColor(getResources().getColor(R.color.textThird));
            code.setText("重新发送"+millisUntilFinished / 1000 + "s");
            isTime = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        time.cancel();

    }
}
