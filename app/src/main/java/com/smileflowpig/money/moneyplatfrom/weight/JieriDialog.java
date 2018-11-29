package com.smileflowpig.money.moneyplatfrom.weight;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smileflowpig.money.R;


/**
 * 节日红包
 */
public class JieriDialog extends Dialog {

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
     */
    public void setNoOnclickListener(OnHongBaoClickListener listener) {
        this.mLis = listener;

    }

  OnHongBaoClickListener mLis;

    public interface OnHongBaoClickListener {
        void onCancle();


        void onHongbaoClicck();
    }


    public JieriDialog(Context context) {
        super(context, com.smileflowpig.money.common.R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jieri_hongbao_ad_layout);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();

    }


    float downx, downY;

    /**
     * 初始化界面的确定和取消监听器
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initEvent() {
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLis != null)
                    mLis.onCancle();
            }
        });

        //设置取消按钮被点击后，向外界提供监听
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mLis!=null)
//                    mLis.onHongbaoClicck();
//            }
//        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        downx = event.getX();
                        downY = event.getY();


                        break;
                    case MotionEvent.ACTION_UP:
                        float upX = event.getX();
                        float upY = event.getY();
                        if (Math.abs(upY - downY) <= 10 && Math.abs(upX - downx) <= 10) {

                            boolean in = isIn(upX, upY, v);
                            if (in) {
                                if (mLis != null)
                                    mLis.onHongbaoClicck();
                            }
                        }

                        break;
                }
                return true;
            }
        });
    }

    private boolean isIn(float x, float y, View v) {
        int[] l = {0, 0};
        v.getLocationInWindow(l);
        int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
        int left1 = right / 5 * 2;
        int right1 = right / 5 * 4;
        int top1 = bottom / 7 * 3;
        int bottom1 = bottom / 7 * 5;
        Rect rect = new Rect(left1, top1, right1, bottom1);
        boolean contains = rect.contains((int) x, (int) y);
        return contains;
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
        void onNoClick();

    }

    public interface onImageOnclickListener {
        void onImageClick();

    }
}
