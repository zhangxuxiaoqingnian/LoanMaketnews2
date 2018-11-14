package com.smileflowpig.money.moneyplatfrom.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by 小狼 on 2018/11/8.
 */

public class MyScrollView extends ScrollView {
    private onFinishedListener mListener;
    private OnScrollListener listener;

    public void setOnScrollListener(OnScrollListener listener) {
        this.listener = listener;
    }
    public interface onFinishedListener {
        void onFinish(boolean isFinish);
    }

    public void setmListener(onFinishedListener mListener) {
        this.mListener = mListener;

    }

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();

    }

    //设置接口
    public interface OnScrollListener{
        void onScroll(int scrollY);
    }

    //重写原生onScrollChanged方法，将参数传递给接口，由接口传递出去
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(listener != null){
            //这里我只传了垂直滑动的距离
            listener.onScroll(t);
        }
    }


    public boolean isfinishScroll() {
        boolean isfinish = false;
        Class scrollview = ScrollView.class;
        try {
            Field scrollField = scrollview.getDeclaredField("mScroller");
            scrollField.setAccessible(true);
            Object scroller = scrollField.get(this);
            Class overscroller = scrollField.getType();
            Method finishField = overscroller.getMethod("isFinished");
            finishField.setAccessible(true);
            isfinish = (boolean) finishField.invoke(scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return isfinish;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                mHandler.sendEmptyMessageDelayed(0, 10);
                break;
        }

        return super.onTouchEvent(ev);

    }


    // 用于 存储 上一次 滚动的Y坐标
    private int lastY = -1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
// 打印 每次 Y坐标 滚动的距离 // LogUtils.e(scrollView.getScrollY() + "");
// 获取到 滚动的 Y 坐标距离
                int scrollY = getScrollY();
                // 如果 滚动 的Y 坐标 的 最后一次 滚动到的Y坐标 一致说明 滚动已经完成
                if (scrollY == lastY) {
// ScrollView滚动完成 处理相应的事件
                    mListener.onFinish(true);
                } else {
// 滚动的距离 和 之前的不相等 那么 再发送消息 判断一次
                    // 将滚动的 Y 坐标距离 赋值给
                    lastY = scrollY;
                    mHandler.sendEmptyMessageDelayed(0, 10);
                }
            }
        }
    };


}

