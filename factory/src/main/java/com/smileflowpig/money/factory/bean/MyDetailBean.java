package com.smileflowpig.money.factory.bean;

/**
 * Created by 小狼 on 2018/10/10.
 */

public class MyDetailBean {


    /**
     * rst : {"id":30,"type":"顺购","remark":"1","place":"南昌","remuneration":"20.00","timestamp":1539119520,"time":"2018-10-10 05:12","contacts":"伤伤","contact_number":"15070882893","title":"买水","status":"已发布","create_time":1539115989,"update_time":"2018-10-10 08:16:24","publisher_id":3262032,"recipient_id":0,"recipient_avatar":"","recipient_phone":"","recipient_nick":"","is_publisher":"0","publisher_avatar":"","publisher_nick":"v_2893"}
     * errno : 0
     * err : 成功
     * timestamp : 1539139033
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * id : 30
         * type : 顺购
         * remark : 1
         * place : 南昌
         * remuneration : 20.00
         * timestamp : 1539119520
         * time : 2018-10-10 05:12
         * contacts : 伤伤
         * contact_number : 15070882893
         * title : 买水
         * status : 已发布
         * create_time : 1539115989
         * update_time : 2018-10-10 08:16:24
         * publisher_id : 3262032
         * recipient_id : 0
         * recipient_avatar :
         * recipient_phone :
         * recipient_nick :
         * is_publisher : 0
         * publisher_avatar :
         * publisher_nick : v_2893
         */

        public int id;
        public String type;
        public String remark;
        public String place;
        public String remuneration;
        public int timestamp;
        public String time;
        public String contacts;
        public String contact_number;
        public String title;
        public String status;
        public int create_time;
        public String update_time;
        public int publisher_id;
        public int recipient_id;
        public String recipient_avatar;
        public String recipient_phone;
        public String recipient_nick;
        public String is_publisher;
        public String publisher_avatar;
        public String publisher_nick;
    }
}
