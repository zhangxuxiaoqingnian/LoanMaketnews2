package com.smileflowpig.money.factory.bean;

/**
 * Created by 小狼 on 2018/10/27.
 */

public class CancleCollectBean {


    /**
     * rst : {"msg":"取消收藏成功"}
     * errno : 0
     * err : 成功
     * timestamp : 1540791916
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * msg : 取消收藏成功
         */

        public String msg;
    }
}
