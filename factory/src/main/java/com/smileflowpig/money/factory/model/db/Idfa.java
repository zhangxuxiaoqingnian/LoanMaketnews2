package com.smileflowpig.money.factory.model.db;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class Idfa {
    private String device;
    private String source;
    private String idfa;

    public Idfa(String device, String source, String idfa) {
        this.device = device;
        this.source = source;
        this.idfa = idfa;
    }
}
