package com.smileflowpig.money.factory.model.api.hongbao;

public class ReceiveModel {

    private String red_packet_id;

    public ReceiveModel(String red_packet_id) {
        this.red_packet_id = red_packet_id;
    }

    public String getRed_packet_id() {
        return red_packet_id;
    }

    public void setRed_packet_id(String red_packet_id) {
        this.red_packet_id = red_packet_id;
    }
}
