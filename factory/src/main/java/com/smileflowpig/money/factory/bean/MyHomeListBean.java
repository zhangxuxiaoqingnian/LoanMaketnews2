package com.smileflowpig.money.factory.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 小狼 on 2018/10/9.
 */

public class MyHomeListBean {


    /**
     * rst : {"data":[{"id":21,"type":"顺购","remark":"凉的","place":"博雅国际中心2103","remuneration":"10.00","time":"2018-09-26 20:00","contacts":"张先生","contact_number":"15158124752","title":"买一瓶可乐","status":"已发布","publisher_avatar":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-09/20181009113433351330353.jpg","publisher_nick":"啦啦啦啦"}],"pageinfo":{"totalNum":"1","page":"1","maxPage":"1","hasNext":false,"hasPrev":false,"num":"1"}}
     * errno : 0
     * err : 成功
     * timestamp : 1539067721
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * data : [{"id":21,"type":"顺购","remark":"凉的","place":"博雅国际中心2103","remuneration":"10.00","time":"2018-09-26 20:00","contacts":"张先生","contact_number":"15158124752","title":"买一瓶可乐","status":"已发布","publisher_avatar":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-09/20181009113433351330353.jpg","publisher_nick":"啦啦啦啦"}]
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

        public static class DataBean implements Serializable{
            /**
             * id : 21
             * type : 顺购
             * remark : 凉的
             * place : 博雅国际中心2103
             * remuneration : 10.00
             * time : 2018-09-26 20:00
             * contacts : 张先生
             * contact_number : 15158124752
             * title : 买一瓶可乐
             * status : 已发布
             * publisher_avatar : http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-09/20181009113433351330353.jpg
             * publisher_nick : 啦啦啦啦
             */

            public int id;
            public String type;
            public String remark;
            public String place;
            public String remuneration;
            public String time;
            public String contacts;
            public String contact_number;
            public String title;
            public String status;
            public String publisher_avatar;
            public String publisher_nick;
        }
    }
}
