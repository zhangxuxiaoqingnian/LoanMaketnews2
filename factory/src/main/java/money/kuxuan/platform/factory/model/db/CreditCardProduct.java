package money.kuxuan.platform.factory.model.db;

import java.util.List;

/**
 * Created by Administrator on 2017/12/4 0004.
 */

public class CreditCardProduct {

        private String id;
        private String name;
        private String desc;
        private List<String> keywords;
        private String icon;
        private String card_icon;
        private String url;
        private String onshelf;
        private String apply_num;
        private String sort;
        private String operating_personnel;
        private String online_time;
        private String offline_time;
        private String created_at;
        private String updated_at;

    public String getApply_num() {
        return apply_num;
    }

    public void setApply_num(String apply_num) {
        this.apply_num = apply_num;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCard_icon() {
        return card_icon;
    }

    public void setCard_icon(String card_icon) {
        this.card_icon = card_icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOnshelf() {
        return onshelf;
    }

    public void setOnshelf(String onshelf) {
        this.onshelf = onshelf;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOperating_personnel() {
        return operating_personnel;
    }

    public void setOperating_personnel(String operating_personnel) {
        this.operating_personnel = operating_personnel;
    }

    public String getOnline_time() {
        return online_time;
    }

    public void setOnline_time(String online_time) {
        this.online_time = online_time;
    }

    public String getOffline_time() {
        return offline_time;
    }

    public void setOffline_time(String offline_time) {
        this.offline_time = offline_time;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
