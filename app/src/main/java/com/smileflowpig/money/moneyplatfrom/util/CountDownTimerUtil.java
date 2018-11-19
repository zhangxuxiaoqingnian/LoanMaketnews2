package com.smileflowpig.money.moneyplatfrom.util;


import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CountDownTimerUtil extends android.os.CountDownTimer {
    private TextView day, shi, fen, miao;
    private long millisInFuture;
    private long countDownInterval;

    long day_time = 0l;
    long shi_time = 0l;
    long fen_time = 0l;
    long miao_time = 0l;


    public CountDownTimerUtil(long millisInFuture, long countDownInterval, TextView day, TextView shi, TextView fen, TextView miao) {
        super(millisInFuture, countDownInterval);
        this.day = day;
        this.shi = shi;
        this.fen = fen;
        this.miao = miao;
        this.millisInFuture = millisInFuture;
        this.countDownInterval = countDownInterval;
    }

    @Override
    public void onTick(long date) {
        Log.e("毫秒", date + "");
        day_time = date / (1000 * 60 * 60 * 24);
        shi_time = (date / (1000 * 60 * 60) - day_time * 24);
        fen_time = ((date / (60 * 1000)) - day_time * 24 * 60 - shi_time * 60);
        miao_time = (date / 1000 - day_time * 24 * 60 * 60 - shi_time * 60 * 60 - fen_time * 60);

        day.setText(day_time + "");
        shi.setText(shi_time + "");
        fen.setText(fen_time + "");
        miao.setText(miao_time + "");

    }


    public long getMillisInFuture() {
        return millisInFuture;
    }

    public void setMillisInFuture(long millisInFuture) {
        this.millisInFuture = millisInFuture;
    }

    public long getCountDownInterval() {
        return countDownInterval;
    }

    public void setCountDownInterval(long countDownInterval) {
        this.countDownInterval = countDownInterval;
    }

    @Override
    public void onFinish() {

    }


}