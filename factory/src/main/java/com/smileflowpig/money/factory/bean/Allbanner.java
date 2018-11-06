package com.smileflowpig.money.factory.bean;

import java.util.List;

/**
 * Created by 小狼 on 2018/10/19.
 */

public class Allbanner {


    /**
     * rst : {"homeact":[{"id":247,"url":"/details.html?id=129","stime":1539685478,"etime":1540981480,"photo":"http://img.henhaojie.com/Homeact/20181016/1539685314700444.jpg","status":1,"sort":2,"note":"小赢卡贷10.16","create_time":1539685314,"update_time":"2018-10-16 18:21:54","postion":null,"device":"0","version":"2","product_id":"129","link":""},{"id":155,"url":"/details.html?id=341","stime":1531988332,"etime":1543547489,"photo":"http://img.henhaojie.com/Homeact/20171213/1513134608596769.png","status":1,"sort":1,"note":"省呗","create_time":1513134608,"update_time":"2018-10-18 10:55:33","postion":null,"device":"0","version":"2","product_id":"341","link":""},{"id":237,"url":"/details.html?id=362","stime":1536742917,"etime":1540976514,"photo":"http://img.henhaojie.com/Homeact/20180912/1536742864639810.jpg","status":1,"sort":1,"note":"万达普惠","create_time":1536742864,"update_time":"2018-09-12 17:01:04","postion":null,"device":"0","version":"2","product_id":"362","link":""},{"id":241,"url":"/details.html?id=401","stime":1537511963,"etime":1540967964,"photo":"http://img.henhaojie.com/Homeact/20180921/1537511877178071.png","status":1,"sort":1,"note":"秒贝","create_time":1537511877,"update_time":"2018-09-21 14:37:57","postion":null,"device":"0","version":"2","product_id":"401","link":""},{"id":243,"url":"/details.html?id=333","stime":1539055896,"etime":1543548697,"photo":"http://img.henhaojie.com/Homeact/20181009/1539055746329533.jpg","status":1,"sort":1,"note":"酷街","create_time":1539055746,"update_time":"2018-10-09 11:29:06","postion":null,"device":"0","version":"2","product_id":"333","link":""},{"id":249,"url":"/details.html?id=115","stime":1539831232,"etime":1543546433,"photo":"http://img.henhaojie.com/Homeact/20181018/1539831064138110.png","status":1,"sort":1,"note":"极速快贷","create_time":1539831064,"update_time":"2018-10-18 10:51:04","postion":null,"device":"0","version":"2","product_id":"115","link":""}],"msglist":["北京，158****6543在给你花成功借款<em>1000<\/em>元","济南，胡**在现金侠成功借款<em>500<\/em>元","北京，*哥在拍拍贷成功借款<em>10000<\/em>元","南京，ali***cy在学生贷成功借款<em>5000<\/em>元","北京，132****2643在给你花成功借款<em>2000<\/em>元"]}
     * errno : 0
     * err : 成功
     * timestamp : 1539917149
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        public List<HomeactBean> homeact;
        public List<String> msglist;

        public static class HomeactBean {
            /**
             * id : 247
             * url : /details.html?id=129
             * stime : 1539685478
             * etime : 1540981480
             * photo : http://img.henhaojie.com/Homeact/20181016/1539685314700444.jpg
             * status : 1
             * sort : 2
             * note : 小赢卡贷10.16
             * create_time : 1539685314
             * update_time : 2018-10-16 18:21:54
             * postion : null
             * device : 0
             * version : 2
             * product_id : 129
             * link :
             */

            public int id;
            public String url;
            public int stime;
            public int etime;
            public String photo;
            public int status;
            public int sort;
            public String note;
            public int create_time;
            public String update_time;
            public Object postion;
            public String device;
            public String version;
            public String product_id;
            public String link;
        }
    }
}
