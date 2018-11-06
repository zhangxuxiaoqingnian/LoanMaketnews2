package com.smileflowpig.money.factory.bean;

import java.util.List;

/**
 * Created by 小狼 on 2018/11/2.
 */

public class MessageBean {


    /**
     * rst : [{"new_list":[{"id":"1","title":"标题","picture":"/","view_num":"1000","is_up":"1","publish_time":"2018-10-31"},{"id":"2","title":"标题2","picture":"/","view_num":"1000","is_up":"0","publish_time":"2018-10-31"}],"pageinfo":{"totalNum":"2","page":"1","maxPage":"1","hasNext":false,"hasPrev":false,"num":"2"}}]
     * errno : 0
     * err : 成功
     * timestamp : 1540968618
     */

    public String errno;
    public String err;
    public String timestamp;
    public List<RstBean> rst;

    public static class RstBean {
        /**
         * new_list : [{"id":"1","title":"标题","picture":"/","view_num":"1000","is_up":"1","publish_time":"2018-10-31"},{"id":"2","title":"标题2","picture":"/","view_num":"1000","is_up":"0","publish_time":"2018-10-31"}]
         * pageinfo : {"totalNum":"2","page":"1","maxPage":"1","hasNext":false,"hasPrev":false,"num":"2"}
         */

        public PageinfoBean pageinfo;
        public List<NewListBean> new_list;

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

        public static class NewListBean {
            /**
             * id : 1
             * title : 标题
             * picture : /
             * view_num : 1000
             * is_up : 1
             * publish_time : 2018-10-31
             */

            public String id;
            public String title;
            public String picture;
            public String view_num;
            public String is_up;
            public String publish_time;
        }
    }
}
