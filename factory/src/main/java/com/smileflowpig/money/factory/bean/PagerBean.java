package com.smileflowpig.money.factory.bean;

import java.util.List;

/**
 * Created by 小狼 on 2018/11/21.
 */

public class PagerBean {


    /**
     * rst : {"data":[{"type":"2","name":"节日红包","date":"2018-11-20","total_money":"2.00"},{"type":"1","name":"新手红包","date":"2018-11-20","total_money":"2.00"}],"pageinfo":{"totalNum":"2","page":"1","maxPage":"1","hasNext":false,"hasPrev":false,"num":"2"}}
     * errno : 0
     * err : 成功
     * timestamp : 1542795005
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * data : [{"type":"2","name":"节日红包","date":"2018-11-20","total_money":"2.00"},{"type":"1","name":"新手红包","date":"2018-11-20","total_money":"2.00"}]
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
             * type : 2
             * name : 节日红包
             * date : 2018-11-20
             * total_money : 2.00
             */

            public String type;
            public String name;
            public String date;
            public String money;
            public String total_money;
        }
    }
}
