package com.smileflowpig.money.factory.bean;

import java.util.List;

/**
 * Created by 小狼 on 2018/12/11.
 */

public class PushBean {


    /**
     * res : [{"title":"小花猪","sub_title":"小花猪app","content":"小花猪~~~","push_time":"2018-12-06 08:10:00","link":"","product_id":410,"xhz_type":3},{"title":"小花猪","sub_title":"小花猪app","content":"小花猪~~~","push_time":"2018-12-06 08:10:00","link":"","product_id":410,"xhz_type":3},{"title":"小花猪app","sub_title":"","content":"小花猪又来了~~","push_time":"2018-12-06 08:11:00","link":"https://m.henhaojie.com/xiaohuazhu/information.htm","product_id":0,"xhz_type":2},{"title":"小花猪app","sub_title":"","content":"小花猪又来了~~","push_time":"2018-12-06 08:11:00","link":"https://m.henhaojie.com/xiaohuazhu/information.htm","product_id":0,"xhz_type":2},{"title":"小花猪","sub_title":"","content":"小花猪拱了你一下下~~","push_time":"2018-12-06 08:15:00","link":"","product_id":0,"xhz_type":1},{"title":"小花猪","sub_title":"","content":"小花猪拱了你一下下~~","push_time":"2018-12-06 08:15:00","link":"","product_id":0,"xhz_type":1}]
     * message : 成功
     * code : 0
     */

    public String message;
    public int code;
    public List<ResBean> res;

    public static class ResBean {
        /**
         * title : 小花猪
         * sub_title : 小花猪app
         * content : 小花猪~~~
         * push_time : 2018-12-06 08:10:00
         * link :
         * product_id : 410
         * xhz_type : 3
         */

        public String title;
        public String sub_title;
        public String content;
        public String push_time;
        public String link;
        public int product_id;
        public int xhz_type;
    }
}
