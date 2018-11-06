package com.smileflowpig.money.factory.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 小狼 on 2018/10/25.
 */

public class DetailBean {


    /**
     * rst : {"id":122,"name":"快贷","description":"1500内小额现金贷，线上申请，5-10分钟放款。","gjj_link":"","gjj_description":"''","applicants":83472,"icon":"http://img.henhaojie.com/Uploads/20170720/1500539515283654.jpg","link":"http://h5.yizhencom.com/activity/zjqb-guide.html?h5tuig=bangnd","upper_term":120,"lower_term":330,"upper_amount":500,"lower_amount":1500,"rate":"2.00%-2.00%","day_rate":"2.00","monthly_rate":"2.00","requirements":"18-55周岁中国公民","document":"身份证、手机","explain":"<p>捷信福袋是由捷信公司提供的用于消费目的的小额现金贷款，借款人可以选择在1至6个月时间内通过等额本息进行分期还款。<br/><\/p>","is_recommend":1,"is_hot":1,"is_new":1,"user_title":"学生党","prod_title":null,"sort_order":100,"status":1,"create_time":1490095750,"update_time":"2018-10-24 11:10:24","star":4,"remarks":"6:6个月/7:7个月/8:8个月/9:9个月/10:10个月/11:11个月\r\n24小时","term":"''","device":0,"phone_verified":0,"zhima_score":0,"credit_card":0,"customer_service_number":"","small_link":"","top_term":"11个月","top_money":"0.15万","loan_period":[{"k":"6","v":"6个月"},{"k":"7","v":"7个月"},{"k":"8","v":"8个月"},{"k":"9","v":"9个月"},{"k":"10","v":"10个月"},{"k":"11","v":"11个月"}],"loan":{"6":"6个月","7":"7个月","8":"8个月","9":"9个月","10":"10个月","11":"11个月"},"lend_time":"24小时","req_list":["18-55周岁中国公民","身份证、手机","<p>捷信福袋是由捷信公司提供的用于消费目的的小额现金贷款，借款人可以选择在1至6个月时间内通过等额本息进行分期还款。<br/><\/p>"],"show_day":"参考月利率","chinese_amount":"0.05万元-0.15万元","is_collection":0}
     * errno : 0
     * err : 成功
     * timestamp : 1540445933
     */

    public RstBean rst;
    public String errno;
    public String err;
    public String timestamp;

    public static class RstBean {
        /**
         * id : 122
         * name : 快贷
         * description : 1500内小额现金贷，线上申请，5-10分钟放款。
         * gjj_link :
         * gjj_description : ''
         * applicants : 83472
         * icon : http://img.henhaojie.com/Uploads/20170720/1500539515283654.jpg
         * link : http://h5.yizhencom.com/activity/zjqb-guide.html?h5tuig=bangnd
         * upper_term : 120
         * lower_term : 330
         * upper_amount : 500
         * lower_amount : 1500
         * rate : 2.00%-2.00%
         * day_rate : 2.00
         * monthly_rate : 2.00
         * requirements : 18-55周岁中国公民
         * document : 身份证、手机
         * explain : <p>捷信福袋是由捷信公司提供的用于消费目的的小额现金贷款，借款人可以选择在1至6个月时间内通过等额本息进行分期还款。<br/></p>
         * is_recommend : 1
         * is_hot : 1
         * is_new : 1
         * user_title : 学生党
         * prod_title : null
         * sort_order : 100
         * status : 1
         * create_time : 1490095750
         * update_time : 2018-10-24 11:10:24
         * star : 4
         * remarks : 6:6个月/7:7个月/8:8个月/9:9个月/10:10个月/11:11个月
         24小时
         * term : ''
         * device : 0
         * phone_verified : 0
         * zhima_score : 0
         * credit_card : 0
         * customer_service_number :
         * small_link :
         * top_term : 11个月
         * top_money : 0.15万
         * loan_period : [{"k":"6","v":"6个月"},{"k":"7","v":"7个月"},{"k":"8","v":"8个月"},{"k":"9","v":"9个月"},{"k":"10","v":"10个月"},{"k":"11","v":"11个月"}]
         * loan : {"6":"6个月","7":"7个月","8":"8个月","9":"9个月","10":"10个月","11":"11个月"}
         * lend_time : 24小时
         * req_list : ["18-55周岁中国公民","身份证、手机","<p>捷信福袋是由捷信公司提供的用于消费目的的小额现金贷款，借款人可以选择在1至6个月时间内通过等额本息进行分期还款。<br/><\/p>"]
         * show_day : 参考月利率
         * chinese_amount : 0.05万元-0.15万元
         * is_collection : 0
         */

        public int id;
        public String name;
        public String description;
        public String gjj_link;
        public String gjj_description;
        public int applicants;
        public String icon;
        public String link;
        public int upper_term;
        public int lower_term;
        public int upper_amount;
        public int lower_amount;
        public String rate;
        public String day_rate;
        public String monthly_rate;
        public String requirements;
        public String document;
        public String explain;
        public int is_recommend;
        public int is_hot;
        public int is_new;
        public String user_title;
        public Object prod_title;
        public int sort_order;
        public int status;
        public int create_time;
        public String update_time;
        public int star;
        public String remarks;
        public String term;
        public int device;
        public int phone_verified;
        public int zhima_score;
        public int credit_card;
        public String customer_service_number;
        public String small_link;
        public String top_term;
        public String top_money;
        public LoanBean loan;
        public String lend_time;
        public String show_day;
        public String chinese_amount;
        public int is_collection;
        public List<LoanPeriodBean> loan_period;
        public List<String> req_list;

        public static class LoanBean {
            /**
             * 6 : 6个月
             * 7 : 7个月
             * 8 : 8个月
             * 9 : 9个月
             * 10 : 10个月
             * 11 : 11个月
             */

            @SerializedName("6")
            public String _$6;
            @SerializedName("7")
            public String _$7;
            @SerializedName("8")
            public String _$8;
            @SerializedName("9")
            public String _$9;
            @SerializedName("10")
            public String _$10;
            @SerializedName("11")
            public String _$11;
        }

        public static class LoanPeriodBean {
            /**
             * k : 6
             * v : 6个月
             */

            public String k;
            public String v;
        }
    }
}
