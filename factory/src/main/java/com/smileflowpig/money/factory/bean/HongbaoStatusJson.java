package com.smileflowpig.money.factory.bean;

public class HongbaoStatusJson {

    private String msg;

    private String red_packet_id;

    private int type;

    private String money;


    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRed_packet_id() {
        return red_packet_id;
    }

    public void setRed_packet_id(String red_packet_id) {
        this.red_packet_id = red_packet_id;
    }
}
