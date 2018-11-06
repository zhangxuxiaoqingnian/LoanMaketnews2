package com.smileflowpig.money.factory.bean;

import java.util.List;

/**
 * Created by 小狼 on 2018/10/26.
 */

public class MemoBean {


    /**
     * rst : {"data":[{"id":"16","user_id":"1560","platform_id":"131","how_to_use":"","how_much":"","how_long":"","due_date":"","gross_interest":"","create_time":"1540478780","platform_name":"2345贷款王","platform_icon":"http://182.92.118.1/Uploads/20170321/1490098678688438.png"}],"pageinfo":{"totalNum":"1","page":"1","maxPage":"1","hasNext":false,"hasPrev":false,"num":"1"}}
     * errno : 0
     * err : 成功
     * timestamp : 1540541367
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * data : [{"id":"16","user_id":"1560","platform_id":"131","how_to_use":"","how_much":"","how_long":"","due_date":"","gross_interest":"","create_time":"1540478780","platform_name":"2345贷款王","platform_icon":"http://182.92.118.1/Uploads/20170321/1490098678688438.png"}]
         * pageinfo : {"totalNum":"1","page":"1","maxPage":"1","hasNext":false,"hasPrev":false,"num":"1"}
         */

        public PageinfoBean pageinfo;
        public List<DataBean> data;

        public static class PageinfoBean {
            /**
             * totalNum : 1
             * page : 1
             * maxPage : 1
             * hasNext : false
             * hasPrev : false
             * num : 1
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
             * id : 16
             * user_id : 1560
             * platform_id : 131
             * how_to_use :
             * how_much :
             * how_long :
             * due_date :
             * gross_interest :
             * create_time : 1540478780
             * platform_name : 2345贷款王
             * platform_icon : http://182.92.118.1/Uploads/20170321/1490098678688438.png
             */

            public String id;
            public String user_id;
            public String platform_id;
            public String how_to_use;
            public String how_much;
            public String how_long;
            public String due_date;
            public String gross_interest;
            public String create_time;
            public String platform_name;
            public String platform_icon;
        }
    }
}
