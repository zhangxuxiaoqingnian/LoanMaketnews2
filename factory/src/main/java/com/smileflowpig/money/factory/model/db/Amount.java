package com.smileflowpig.money.factory.model.db;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class Amount {
    private String icon;
    private String id;
    private String name;
    private String link;
    private String skip_type;


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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
}
