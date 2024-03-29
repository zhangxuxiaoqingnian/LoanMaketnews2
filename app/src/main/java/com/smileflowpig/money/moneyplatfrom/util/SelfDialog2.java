package com.smileflowpig.money.moneyplatfrom.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.smileflowpig.money.R;

public class SelfDialog2 extends Dialog {


    private Button no;//取消按钮

    private Button yes; //确定按钮

    private TextView titleTv;//消息标题文本
    private String titleStr;//从外界设置的title文本

    String message;
    //从外界设置的消息文本
    //确定文本和取消文本的显示内容
    private String noStr;

    private String yesStr;
    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器

    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    private EditText name;


    /**
     * 设置取消按钮的显示内容和监听
     *
     * @param str
     * @param onNoOnclickListener
     */
    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }


    /**
     * 设置确定按钮的显示内容和监听
     *
     * @param str
     * @param onYesOnclickListener
     */
    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }

    public SelfDialog2(Context context) {
        super(context, R.style.MyDialog);  //这个是一个给他设置的样式，我在最底下写出来
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_exercise_sure_dialog_layout2);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();

    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {

        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    String text = name.getText().toString();
                    yesOnclickListener.onYesClick(text);
                }
            }
        });

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        if (titleStr != null) {
            titleTv.setVisibility(View.VISIBLE);
            titleTv.setText(titleStr);
        }  else{
            titleTv.setVisibility(View.GONE);
        }



        //如果设置按钮的文字
        if (noStr != null) {
            no.setVisibility(View.VISIBLE);
            no.setText(noStr);
        }else{
            no.setVisibility(View.GONE);
        }
        if (yesStr != null) {
            yes.setVisibility(View.VISIBLE);
            yes.setText(yesStr);
        }else{
            yes.setVisibility(View.GONE);
        }


    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        no = (Button) findViewById(R.id.positive);
        yes = (Button) findViewById(R.id.nagative);
        titleTv = (TextView) findViewById(R.id.title);
        name = (EditText) findViewById(R.id.upname);
    }

    /**
     * 从外界Activity为Dialog设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        titleStr = title;
    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public interface onNoOnclickListener {
        void onNoClick();
    }

    public interface onYesOnclickListener{
        void onYesClick(String text);
    }
}
