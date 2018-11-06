package com.smileflowpig.money.factory.bean;

import java.util.List;

/**
 * Created by 小狼 on 2018/10/30.
 */

public class HomeurlBean {


    /**
     * rst : [{"id":9,"name":"小额极速贷","icon":"http://182.92.118.1/h5List/icon/20181025/1540464217251317.png","url":"http://bw.quyaqu.com/xiaohuazhu/category.html","type":1},{"id":4,"name":"大额低息","icon":"http://182.92.118.1/h5List/icon/20181023/1540277892821359.png","url":"http://bw.quyaqu.com/xiaohuazhu/category.html","type":1},{"id":3,"name":"最新上线","icon":"http://182.92.118.1/h5List/icon/20181023/1540277977441849.png","url":"http://bw.quyaqu.com/xiaohuazhu/category.html","type":1},{"id":2,"name":"一定借到钱","icon":"http://182.92.118.1/h5List/icon/20181023/1540278050708453.png","url":"http://bw.quyaqu.com/xiaohuazhu/category.html","type":1},{"id":1,"name":"信用卡申请","icon":"http://182.92.118.1/h5List/icon20181023/1540277911159592.png","url":"http://bw.quyaqu.com/xiaohuazhu/creditCard.html","type":2}]
     * errno : 0
     * err : 成功
     * timestamp : 1540864529
     */

    public String errno;
    public String err;
    public String timestamp;
    public List<RstBean> rst;

    public static class RstBean {
        /**
         * id : 9
         * name : 小额极速贷
         * icon : http://182.92.118.1/h5List/icon/20181025/1540464217251317.png
         * url : http://bw.quyaqu.com/xiaohuazhu/category.html
         * type : 1
         */

        public int id;
        public String name;
        public String icon;
        public String url;
        public int type;
    }
}
