package com.smileflowpig.money.factory.model.db;

/**
 * Created by 小狼 on 2018/5/30.
 */

public class DeleteApp {


    /**
     * rst : {"product_apply_id":"0"}
     * errno : 0
     * err : 成功
     * timestamp : 1527665976
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * product_apply_id : 0
         */

        public String product_apply_id;
    }
}
