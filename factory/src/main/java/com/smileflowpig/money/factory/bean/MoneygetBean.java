package com.smileflowpig.money.factory.bean;

import java.util.List;

/**
 * Created by 小狼 on 2018/11/20.
 */

public class MoneygetBean {


    /**
     * rst : {"data":[{"id":"2","type":"1","money":"8888.00","status":"1","create_time":"2018-11-20"},{"id":"1","type":"1","money":"12.88","status":"1","create_time":"2018-11-20"}],"pageinfo":{"totalNum":"2","page":"1","maxPage":"1","hasNext":false,"hasPrev":false,"num":"2"}}
     * errno : 0
     * err : 成功
     * timestamp : 1542685452
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * data : [{"id":"2","type":"1","money":"8888.00","status":"1","create_time":"2018-11-20"},{"id":"1","type":"1","money":"12.88","status":"1","create_time":"2018-11-20"}]
         * pageinfo : {"totalNum":"2","page":"1","maxPage":"1","hasNext":false,"hasPrev":false,"num":"2"}
         */

        public PageinfoBean pageinfo;
        public List<DataBean> data;

        public static class PageinfoBean {
            /**
             * totalNum : 2
             * page : 1
             * maxPage : 1
             * hasNext : false
             * hasPrev : false
             * num : 2
             */

            public String totalNum;
            public String page;
            public String maxPage;
            public boolean hasNext;
            public boolean hasPrev;
            public String num;
        }

        public static class DataBean {
            /**
             * id : 2
             * type : 1
             * money : 8888.00
             * status : 1
             * create_time : 2018-11-20
             */

            public String id;
            public String type;
            public String money;
            public String status;
            public String create_time;
        }
    }
}
