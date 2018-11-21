package com.smileflowpig.money.factory.bean;

import java.io.Serializable;

public class HongbaoJson implements Serializable {


    /**
     * id : 1
     * type : 1
     * slogan : 新手大礼包
     * total_money : 100.00
     * total_number : 100
     * status : 1
     * create_time : 0
     * update_time : 0
     * name : 新手红包
     * surplus_amount : 100.00
     * surplus_number : 100
     * start_time : 1542672000
     * end_time : 1542844800
     */

    private int id;
    private int type;
    private String slogan;
    private String total_money;
    private String total_number;
    private int status;
    private String create_time;
    private String update_time;
    private String name;
    private String surplus_amount;
    private String surplus_number;
    private String start_time;
    private String end_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getTotal_money() {
        return total_money;
    }

    public void setTotal_money(String total_money) {
        this.total_money = total_money;
    }

    public String getTotal_number() {
        return total_number;
    }

    public void setTotal_number(String total_number) {
        this.total_number = total_number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurplus_amount() {
        return surplus_amount;
    }

    public void setSurplus_amount(String surplus_amount) {
        this.surplus_amount = surplus_amount;
    }

    public String getSurplus_number() {
        return surplus_number;
    }

    public void setSurplus_number(String surplus_number) {
        this.surplus_number = surplus_number;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
