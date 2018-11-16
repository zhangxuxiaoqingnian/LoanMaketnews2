package com.smileflowpig.money.moneyplatfrom.weight;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smileflowpig.money.R;
import com.smileflowpig.money.common.widget.AdDialog;

/**
 * 红包广告
 */
public class HongbaoDialog extends Dialog {

    private TextView no;//关闭按钮

    private ImageView imageView;
    private String url;//从外界设置的title文本
    @StringRes
    int message;
    //从外界设置的消息文本
    //确定文本和取消文本的显示内容
    private String noStr;

    /**
     * 设置取消按钮的显示内容和监听
     *

     */
    public void setNoOnclickListener(OnHongBaoClickListener listener) {
       this.mLis = listener;

    }

    OnHongBaoClickListener mLis;
    public interface OnHongBaoClickListener{
        void onCancle();


        void onHongbaoClicck();
    }




    public HongbaoDialog(Context context) {
        super(context, com.smileflowpig.money.common.R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hongbao_ad_layout);
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
               if(mLis!=null)
                   mLis.onCancle();
            }
        });

        //设置取消按钮被点击后，向外界提供监听
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mLis!=null)
                    mLis.onHongbaoClicck();
            }
        });

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        if (url != null) {
            Glide.with(getContext()).load(url).into(imageView);
        }

    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        no = (TextView) findViewById(R.id.hongbao_ad_text);

        imageView = (ImageView) findViewById(R.id.hongbao_ad_img);

    }

    /**
     * 从外界Activity为Dialog设置标题
     */
    public void setImageView(String url) {
        this.url = url;
    }



    public interface onNoOnclickListener {
        public void onNoClick();

    }

    public interface onImageOnclickListener {
        public void onImageClick();

    }
}