package com.smileflowpig.money.factory.bean;

/**
 * Created by 小狼 on 2018/11/19.
 */

public class MyidentcardBean {


    /**
     * rst : {"id":"3","user_id":"7","real_name":"赵","idcard_no":"1","alipay_id":"","update_time":"0"}
     * errno : 0
     * err : 成功
     * timestamp : 1542359072
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * id : 3
         * user_id : 7
         * real_name : 赵
         * idcard_no : 1
         * alipay_id :
         * update_time : 0
         */

        public String id;
        public String user_id;
        public String real_name;
        public String idcard_no;
        public String alipay_id;
        public String update_time;
    }
}
