package com.kuxuan.push;


/**
 * 跳转类，包含跳转信息
 */
public class JumpJson {








    //跳转类型（当前只有两种）
    private int type ;



    private String  url;


    private String id;

    private String content;
    private String title;



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
