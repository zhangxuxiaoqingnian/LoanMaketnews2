package com.smileflowpig.money.factory.bean;

import java.io.Serializable;

public class HongbaoBean implements Serializable {


    private HongbaoJson novice;

    private HongbaoJson festival;

    public HongbaoJson getNovice() {
        return novice;
    }

    public void setNovice(HongbaoJson novice) {
        this.novice = novice;
    }

    public HongbaoJson getFfestical() {
        return festival;
    }

    public void setFfestical(HongbaoJson ffestical) {
        this.festival = ffestical;
    }
}
