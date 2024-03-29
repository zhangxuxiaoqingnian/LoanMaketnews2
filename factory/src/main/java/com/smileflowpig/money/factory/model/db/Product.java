package com.smileflowpig.money.factory.model.db;

import com.smileflowpig.money.common.factory.model.Products;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class Product implements Products {
    private String id;
    private String name;
    private String description;
    private String prod_title;
    private String applicants;
    private String icon;
    private String day_rate;
    private String monthly_rate;
    private String show_day;
    private String skip_type;
    private String link;
    private String num;
    private String lend_time;
    private String upper_amount;
    private String lower_amount;

    public String getUpper_amount() {
        return upper_amount;
    }

    public void setUpper_amount(String upper_amount) {
        this.upper_amount = upper_amount;
    }

    public String getLower_amount() {
        return lower_amount;
    }

    public void setLower_amount(String lower_amount) {
        this.lower_amount = lower_amount;
    }

    public String getLend_time() {
        return lend_time;
    }

    public void setLend_time(String lend_time) {
        this.lend_time = lend_time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSkip_type() {
        return skip_type;
    }

    public void setSkip_type(String skip_type) {
        this.skip_type = skip_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProd_title() {
        return prod_title;
    }

    public void setProd_title(String prod_title) {
        this.prod_title = prod_title;
    }

    public String getApplicants() {
        return applicants;
    }

    public void setApplicants(String applicants) {
        this.applicants = applicants;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDay_rate() {
        return day_rate;
    }

    public void setDay_rate(String day_rate) {
        this.day_rate = day_rate;
    }

    public String getMonthly_rate() {
        return monthly_rate;
    }

    public void setMonthly_rate(String monthly_rate) {
        this.monthly_rate = monthly_rate;
    }

    public String getShow_day() {
        return show_day;
    }

    public void setShow_day(String show_day) {
        this.show_day = show_day;
    }
}
