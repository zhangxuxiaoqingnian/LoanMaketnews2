package com.smileflowpig.money.factory.model.api.guoshen;

import java.io.Serializable;

/**
 * Created by Allence on 2017/12/22 0022.
 */

public class Average_Captial_Plus_InterestBean implements Serializable{

    private String MonthlyPayments;
    private String payInterest;
    private String repayment;
    private String totalloan;
    private String rate;
    private String refundMonth;


    public Average_Captial_Plus_InterestBean(String monthlyPayments, String payInterest, String repayment, String totalloan, String rate, String refundMonth) {
        MonthlyPayments = monthlyPayments;
        this.payInterest = payInterest;
        this.repayment = repayment;
        this.totalloan = totalloan;
        this.rate = rate;
        this.refundMonth = refundMonth;
    }

    public String getMonthlyPayments() {
        return MonthlyPayments;
    }

    public void setMonthlyPayments(String monthlyPayments) {
        MonthlyPayments = monthlyPayments;
    }

    public String getPayInterest() {
        return payInterest;
    }

    public void setPayInterest(String payInterest) {
        this.payInterest = payInterest;
    }

    public String getRepayment() {
        return repayment;
    }

    public void setRepayment(String repayment) {
        this.repayment = repayment;
    }

    public String getTotalloan() {
        return totalloan;
    }

    public void setTotalloan(String totalloan) {
        this.totalloan = totalloan;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRefundMonth() {
        return refundMonth;
    }

    public void setRefundMonth(String refundMonth) {
        this.refundMonth = refundMonth;
    }
}
