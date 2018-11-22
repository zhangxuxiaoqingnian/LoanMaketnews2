package com.smileflowpig.money.factory.bean;

/**
 * Created by 小狼 on 2018/11/22.
 */

public class MoneyBean {


    /**
     * rst : {"money":2}
     * errno : 0
     * err : 成功
     * timestamp : 1542851750
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * money : 2
         */

        public int money;
    }
}
