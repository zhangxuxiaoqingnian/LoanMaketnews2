package com.smileflowpig.money.factory.model.db;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class BannerData {
    private String product_id;
    //跳转的产品url
    private String url;
    private String stime;
    private String etime;
    //显示图片
    private String photo;
    private String status;
    private String sort;
    private String note;
    private String create_time;
    private String update_time;
    private String postion;
    private String skip_type;
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSkip_type() {
        return skip_type;
    }

    public void setSkip_type(String skip_type) {
        this.skip_type = skip_type;
    }

    public String getId() {
        return product_id;
    }

    public void setId(String id) {
        this.product_id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
    }
}
