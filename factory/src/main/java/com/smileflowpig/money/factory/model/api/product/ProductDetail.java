package com.smileflowpig.money.factory.model.api.product;

import com.smileflowpig.money.factory.model.db.Dialogs;

import java.util.HashMap;
import java.util.List;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class ProductDetail {
    private String id;
    private String name;
    private String description;
    private String applicants;
    private String icon;
    private String link;

    private int upper_term;
    private String lower_term;
    private int upper_amount;
    private int lower_amount;
    private String rate;

    private String day_rate;
    private String monthly_rate;
    private String requirements;
    private String document;
    private String explain;
    private String is_hot;
    private String is_new;
    private String user_title;
    private String prod_title;
    private String sort_order;
    private String status;
    private String create_time;
    private String update_time;
    private String star;
    private String remarks;
    private String term;
    private String lend_time;
    private String show_day;
    private String[] req_list;
    HashMap<String,String> loan;
    List<Dialogs> loan_period;
    private String customer_service_number;
    private int is_collection;
    private String is_quality;

    public String getCustomer_service_number() {
        return customer_service_number;
    }

    public void setCustomer_service_number(String customer_service_number) {
        this.customer_service_number = customer_service_number;
    }

    public String getIs_quality() {
        return is_quality;
    }

    public void setIs_quality(String is_quality) {
        this.is_quality = is_quality;
    }

    public int getIs_collection() {
        return is_collection;
    }

    public void setIs_collection(int is_collection) {
        this.is_collection = is_collection;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApplicants() {
        return applicants;
    }

    public void setApplicants(String applicants) {
        this.applicants = applicants;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getUpper_term() {
        return upper_term;
    }

    public void setUpper_term(int upper_term) {
        this.upper_term = upper_term;
    }

    public String getLower_term() {
        return lower_term;
    }

    public void setLower_term(String lower_term) {
        this.lower_term = lower_term;
    }

    public int getUpper_amount() {
        return upper_amount;
    }

    public void setUpper_amount(int upper_amount) {
        this.upper_amount = upper_amount;
    }

    public int getLower_amount() {
        return lower_amount;
    }

    public void setLower_amount(int lower_amount) {
        this.lower_amount = lower_amount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDay_rate() {
        return day_rate;
    }

    public void setDay_rate(String day_rate) {
        this.day_rate = day_rate;
    }

    public String getMonthly_rate() {
        return monthly_rate;
    }

    public void setMonthly_rate(String monthly_rate) {
        this.monthly_rate = monthly_rate;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(String is_hot) {
        this.is_hot = is_hot;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getUser_title() {
        return user_title;
    }

    public void setUser_title(String user_title) {
        this.user_title = user_title;
    }

    public String getProd_title() {
        return prod_title;
    }

    public void setProd_title(String prod_title) {
        this.prod_title = prod_title;
    }

    public String getSort_order() {
        return sort_order;
    }

    public void setSort_order(String sort_order) {
        this.sort_order = sort_order;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getLend_time() {
        return lend_time;
    }

    public void setLend_time(String lend_time) {
        this.lend_time = lend_time;
    }

    public String getShow_day() {
        return show_day;
    }

    public void setShow_day(String show_day) {
        this.show_day = show_day;
    }

    public String[] getReq_list() {
        return req_list;
    }

    public void setReq_list(String[] req_list) {
        this.req_list = req_list;
    }

    public HashMap<String, String> getLoan() {
        return loan;
    }

    public void setLoan(HashMap<String, String> loan) {
        this.loan = loan;
    }

    public List<Dialogs> getLoan_period() {
        return loan_period;
    }

    public void setLoan_period(List<Dialogs> loan_period) {
        this.loan_period = loan_period;
    }
}
