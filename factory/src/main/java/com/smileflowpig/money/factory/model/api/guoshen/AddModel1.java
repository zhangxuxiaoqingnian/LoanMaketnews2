package com.smileflowpig.money.factory.model.api.guoshen;

/**
 * Created by Allence on 2018/5/9 0009.
 */

public class AddModel1 {


    int id;
    String platform;
    String periods;
    float amount;
    float monthly_rate;
    String first_time;

    public AddModel1(int id, String platform, String periods, float amount, float monthly_rate, String first_time) {
        this.id = id;
        this.platform = platform;
        this.periods = periods;
        this.amount = amount;
        this.monthly_rate = monthly_rate;
        this.first_time = first_time;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }



    public float getMonthly_rate() {
        return monthly_rate;
    }

    public void setMonthly_rate(float monthly_rate) {
        this.monthly_rate = monthly_rate;
    }

    public String getFirst_time() {
        return first_time;
    }

    public void setFirst_time(String first_time) {
        this.first_time = first_time;
    }
}
