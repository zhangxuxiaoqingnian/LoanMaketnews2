package com.smileflowpig.money.factory.bean;

/**
 * Created by 小狼 on 2018/11/4.
 */

public class MessBannerBean {


    /**
     * rst : {"content":"W我我我我","img":"http://img.henhaojie.com/Upload"}
     * errno : 0
     * err : 成功
     * timestamp : 1540977716
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * content : W我我我我
         * img : http://img.henhaojie.com/Upload
         */

        public String content;
        public String img;
    }
}
