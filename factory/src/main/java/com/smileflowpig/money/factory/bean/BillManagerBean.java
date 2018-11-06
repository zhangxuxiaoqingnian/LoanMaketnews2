package com.smileflowpig.money.factory.bean;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillManagerBean {


    private String id;
    private String user_id;
    private String platform_id;
    private String amount;
    private String due_date;
    private String remind_time;
    private String status;
    private String create_time;
    private String repayment_time;
    private String platform_name;
    private String platform_icon;
    boolean isTrueData = true;


    /**
     * 是否到期
     */
    private boolean isDaoqi = false;

    public boolean isTrueData() {
        return isTrueData;
    }

    public BillManagerBean setTrueData(boolean trueData) {
        isTrueData = trueData;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(String platform_id) {
        this.platform_id = platform_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getRemind_time() {
        return remind_time;
    }

    public void setRemind_time(String remind_time) {
        this.remind_time = remind_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRepayment_time() {
        return repayment_time;
    }

    public void setRepayment_time(String repayment_time) {
        this.repayment_time = repayment_time;
    }

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public String getPlatform_icon() {
        return platform_icon;
    }

    public void setPlatform_icon(String platform_icon) {
        this.platform_icon = platform_icon;
    }


    /**
     * 还有多少天到期
     *
     * @return
     */
    public int getHuankuanData() {
        //当前时间
        long currentTime = System.currentTimeMillis();
        long huankuanData = getTimeMillis(due_date, "yyyy.MM.dd");
        if (currentTime > huankuanData) {
            isDaoqi = true;
        } else {
            isDaoqi = false;
        }
        long day = (currentTime - huankuanData) / (24 * 60 * 60 * 1000);
        return (int) day;
    }

    public boolean isDaoqi() {
        return isDaoqi;
    }

    public void setDaoqi(boolean daoqi) {
        isDaoqi = daoqi;
    }

    /**
     * 通过时间获取时间戳
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static long getTimeMillis(String dateStr, String format) {
        long timemillis = 0;
        if (!TextUtils.isEmpty(dateStr)) {
            try {
                DateFormat df = new SimpleDateFormat(format);
                Date date = df.parse(dateStr);
                timemillis = date.getTime();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return timemillis;
    }
}
