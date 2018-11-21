package com.smileflowpig.money.factory.bean;

/**
 * Created by 小狼 on 2018/11/20.
 */

public class PayoverBean {


    /**
     * rst : {"id":"2","user_id":"1560","real_name":"","idcard_no":"142585836585287569","alipay_id":"","update_time":"1542600761","is_download_app":"0","is_bind_zfb":0,"money":"9999"}
     * errno : 0
     * err : 成功
     * timestamp : 1542680533
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * id : 2
         * user_id : 1560
         * real_name :
         * idcard_no : 142585836585287569
         * alipay_id :
         * update_time : 1542600761
         * is_download_app : 0
         * is_bind_zfb : 0
         * money : 9999
         */

        public String id;
        public String user_id;
        public String real_name;
        public String idcard_no;
        public String alipay_id;
        public String update_time;
        public String is_download_app;
        public int is_bind_zfb;
        public String money;
    }
}
