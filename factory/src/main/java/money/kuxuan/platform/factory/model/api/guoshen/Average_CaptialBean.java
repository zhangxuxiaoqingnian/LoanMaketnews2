package money.kuxuan.platform.factory.model.api.guoshen;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Allence on 2017/12/22 0022.
 */

public class Average_CaptialBean implements Serializable {

    //支付利息
    private String payInterest;
    //还款总额
    private String paytotalMoney;
    //贷款总额
    private String totalloan;
    //贷款利率
    private String loanrate;
    //还款月数
    private String paymonth;

    //还款月供列表
    private List<String> monthlypaymomeyList;


    public Average_CaptialBean(String payInterest, String paytotalMoney, String totalloan, String loanrate, String paymonth, List<String> monthlypaymomeyList) {
        this.payInterest = payInterest;
        this.paytotalMoney = paytotalMoney;
        this.totalloan = totalloan;
        this.loanrate = loanrate;
        this.paymonth = paymonth;
        this.monthlypaymomeyList = monthlypaymomeyList;
    }

    public String getPayInterest() {
        return payInterest;
    }

    public void setPayInterest(String payInterest) {
        this.payInterest = payInterest;
    }

    public String getPaytotalMoney() {
        return paytotalMoney;
    }

    public void setPaytotalMoney(String paytotalMoney) {
        this.paytotalMoney = paytotalMoney;
    }

    public String getTotalloan() {
        return totalloan;
    }

    public void setTotalloan(String totalloan) {
        this.totalloan = totalloan;
    }

    public String getLoanrate() {
        return loanrate;
    }

    public void setLoanrate(String loanrate) {
        this.loanrate = loanrate;
    }

    public String getPaymonth() {
        return paymonth;
    }

    public void setPaymonth(String paymonth) {
        this.paymonth = paymonth;
    }

    public List<String> getMonthlypaymomeyList() {
        return monthlypaymomeyList;
    }

    public void setMonthlypaymomeyList(List<String> monthlypaymomeyList) {
        this.monthlypaymomeyList = monthlypaymomeyList;
    }
}
