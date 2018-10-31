package money.kuxuan.platform.factory.bean;

import java.util.List;

/**
 * Created by 小狼 on 2018/10/11.
 */

public class AllMyBean {


    /**
     * rst : {"data":[{"id":32,"type":"其他","remark":"无","place":"招商银行常营支行","remuneration":"15.00","time":"2018-10-11 18:00","contacts":"马女士","contact_number":"15856225318","title":"银行排队","status":"进行中","recipient_id":3263452,"publisher_id":2371054,"recipient_avatar":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-10/20181010151744184921999.jpg","recipient_phone":"17795225813","recipient_nick":"李李李","publisher_avatar":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-10/20181010151212721413142.jpg","publisher_nick":"大华"},{"id":31,"type":"排队","remark":"无","place":"福广大厦1005","remuneration":"20.00","time":"2018-10-10 16:00","contacts":"马女士","contact_number":"15156285511","title":"送文件","status":"已发布","recipient_id":0,"publisher_id":2371054,"recipient_avatar":"","recipient_phone":"","recipient_nick":"","publisher_avatar":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-10/20181010151212721413142.jpg","publisher_nick":"大华"},{"id":30,"type":"顺购","remark":"1","place":"南昌","remuneration":"20.00","time":"2018-10-10 05:12","contacts":"伤伤","contact_number":"15070882893","title":"买水","status":"进行中","recipient_id":2371054,"publisher_id":3262032,"recipient_avatar":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-10/20181010151212721413142.jpg","recipient_phone":"15201106781","recipient_nick":"大华","publisher_avatar":"","publisher_nick":"v_2893"},{"id":24,"type":"顺购","remark":"买之前联系","place":"东方银座3层","remuneration":"50.00","time":"2018-10-05 17:30","contacts":"小睿","contact_number":"15836582336","title":"买一箱水","status":"进行中","recipient_id":3256986,"publisher_id":2371054,"recipient_avatar":"","recipient_phone":"18519099635","recipient_nick":"v_9635","publisher_avatar":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-10/20181010151212721413142.jpg","publisher_nick":"大华"},{"id":21,"type":"顺购","remark":"凉的","place":"博雅国际中心2103","remuneration":"10.00","time":"2018-09-26 20:00","contacts":"张先生","contact_number":"15158124752","title":"买一瓶可乐","status":"进行中","recipient_id":2371054,"publisher_id":1712290,"recipient_avatar":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-10/20181010151212721413142.jpg","recipient_phone":"15201106781","recipient_nick":"大华","publisher_avatar":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-09/20181009113433351330353.jpg","publisher_nick":"啦啦啦啦"}],"pageInfo":{"totalNum":"5","page":"1","maxPage":"1","hasNext":false,"hasPrev":false,"num":"5"}}
     * errno : 0
     * err : 成功
     * timestamp : 1539227488
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * data : [{"id":32,"type":"其他","remark":"无","place":"招商银行常营支行","remuneration":"15.00","time":"2018-10-11 18:00","contacts":"马女士","contact_number":"15856225318","title":"银行排队","status":"进行中","recipient_id":3263452,"publisher_id":2371054,"recipient_avatar":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-10/20181010151744184921999.jpg","recipient_phone":"17795225813","recipient_nick":"李李李","publisher_avatar":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-10/20181010151212721413142.jpg","publisher_nick":"大华"},{"id":31,"type":"排队","remark":"无","place":"福广大厦1005","remuneration":"20.00","time":"2018-10-10 16:00","contacts":"马女士","contact_number":"15156285511","title":"送文件","status":"已发布","recipient_id":0,"publisher_id":2371054,"recipient_avatar":"","recipient_phone":"","recipient_nick":"","publisher_avatar":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-10/20181010151212721413142.jpg","publisher_nick":"大华"},{"id":30,"type":"顺购","remark":"1","place":"南昌","remuneration":"20.00","time":"2018-10-10 05:12","contacts":"伤伤","contact_number":"15070882893","title":"买水","status":"进行中","recipient_id":2371054,"publisher_id":3262032,"recipient_avatar":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-10/20181010151212721413142.jpg","recipient_phone":"15201106781","recipient_nick":"大华","publisher_avatar":"","publisher_nick":"v_2893"},{"id":24,"type":"顺购","remark":"买之前联系","place":"东方银座3层","remuneration":"50.00","time":"2018-10-05 17:30","contacts":"小睿","contact_number":"15836582336","title":"买一箱水","status":"进行中","recipient_id":3256986,"publisher_id":2371054,"recipient_avatar":"","recipient_phone":"18519099635","recipient_nick":"v_9635","publisher_avatar":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-10/20181010151212721413142.jpg","publisher_nick":"大华"},{"id":21,"type":"顺购","remark":"凉的","place":"博雅国际中心2103","remuneration":"10.00","time":"2018-09-26 20:00","contacts":"张先生","contact_number":"15158124752","title":"买一瓶可乐","status":"进行中","recipient_id":2371054,"publisher_id":1712290,"recipient_avatar":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-10/20181010151212721413142.jpg","recipient_phone":"15201106781","recipient_nick":"大华","publisher_avatar":"http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-09/20181009113433351330353.jpg","publisher_nick":"啦啦啦啦"}]
         * pageInfo : {"totalNum":"5","page":"1","maxPage":"1","hasNext":false,"hasPrev":false,"num":"5"}
         */

        public PageInfoBean pageInfo;
        public List<DataBean> data;

        public static class PageInfoBean {
            /**
             * totalNum : 5
             * page : 1
             * maxPage : 1
             * hasNext : false
             * hasPrev : false
             * num : 5
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
             * id : 32
             * type : 其他
             * remark : 无
             * place : 招商银行常营支行
             * remuneration : 15.00
             * time : 2018-10-11 18:00
             * contacts : 马女士
             * contact_number : 15856225318
             * title : 银行排队
             * status : 进行中
             * recipient_id : 3263452
             * publisher_id : 2371054
             * recipient_avatar : http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-10/20181010151744184921999.jpg
             * recipient_phone : 17795225813
             * recipient_nick : 李李李
             * publisher_avatar : http://invoicing.oss-cn-beijing.aliyuncs.com/avatar/2018-10-10/20181010151212721413142.jpg
             * publisher_nick : 大华
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
            public int recipient_id;
            public int publisher_id;
            public String recipient_avatar;
            public String recipient_phone;
            public String recipient_nick;
            public String publisher_avatar;
            public String publisher_nick;
        }
    }
}
