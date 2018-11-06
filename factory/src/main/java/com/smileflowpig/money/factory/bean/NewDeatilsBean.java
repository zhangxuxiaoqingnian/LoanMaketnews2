package com.smileflowpig.money.factory.bean;

import java.util.List;

/**
 * Created by 小狼 on 2018/9/20.
 */

public class NewDeatilsBean {


    /**
     * rst : [{"id":820348,"status":1,"periods":"1/12","amount":"198.50","date":"2018-09-19 00:00:00","repayment_id":63},{"id":820349,"status":0,"periods":"2/12","amount":"198.50","date":"2018-10-19 00:00:00","repayment_id":63},{"id":820350,"status":1,"periods":"3/12","amount":"198.50","date":"2018-11-19 00:00:00","repayment_id":63},{"id":820351,"status":1,"periods":"4/12","amount":"198.50","date":"2018-12-19 00:00:00","repayment_id":63},{"id":820352,"status":0,"periods":"5/12","amount":"198.50","date":"2019-01-19 00:00:00","repayment_id":63},{"id":820353,"status":0,"periods":"6/12","amount":"198.50","date":"2019-02-19 00:00:00","repayment_id":63},{"id":820354,"status":0,"periods":"7/12","amount":"198.50","date":"2019-03-19 00:00:00","repayment_id":63},{"id":820355,"status":0,"periods":"8/12","amount":"198.50","date":"2019-04-19 00:00:00","repayment_id":63},{"id":820356,"status":0,"periods":"9/12","amount":"198.50","date":"2019-05-19 00:00:00","repayment_id":63},{"id":820357,"status":0,"periods":"10/12","amount":"198.50","date":"2019-06-19 00:00:00","repayment_id":63},{"id":820358,"status":0,"periods":"11/12","amount":"198.50","date":"2019-07-19 00:00:00","repayment_id":63},{"id":820359,"status":0,"periods":"12/12","amount":"198.50","date":"2019-08-19 00:00:00","repayment_id":63}]
     * errno : 0
     * err : 成功
     * timestamp : 1537421247
     */

    public String errno;
    public String err;
    public String timestamp;
    public List<RstBean> rst;

    public static class RstBean {
        /**
         * id : 820348
         * status : 1
         * periods : 1/12
         * amount : 198.50
         * date : 2018-09-19 00:00:00
         * repayment_id : 63
         */

        public int id;
        public int status;
        public String periods;
        public String amount;
        public String date;
        public int repayment_id;
        public boolean keyi;


        public RstBean(int id, int status, String periods, String amount, String date, int repayment_id, boolean keyi) {
            this.id = id;
            this.status = status;
            this.periods = periods;
            this.amount = amount;
            this.date = date;
            this.repayment_id = repayment_id;
            this.keyi = keyi;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getPeriods() {
            return periods;
        }

        public void setPeriods(String periods) {
            this.periods = periods;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getRepayment_id() {
            return repayment_id;
        }

        public void setRepayment_id(int repayment_id) {
            this.repayment_id = repayment_id;
        }

        public boolean isKeyi() {
            return keyi;
        }

        public void setKeyi(boolean keyi) {
            this.keyi = keyi;
        }
    }
}
