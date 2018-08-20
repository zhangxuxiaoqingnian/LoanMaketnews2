package money.kuxuan.platform.factory.model.api.launcher;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class LaunchRspModel {

//    private String android;
//    private String url;
//    private String version;
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getVersion() {
//        return version;
//    }
//
//    public void setVersion(String version) {
//        this.version = version;
//    }
//
//    public String getAndroid() {
//        return android;
//    }
//
//    public void setAndroid(String android) {
//        this.android = android;
//    }
    public int id;
    public int channel_id;
    public int status;
    public int ios;
    public String android;
    public int h5;
    public String url;
    public String create_time;
    public String update_time;
    public String version;
    public String note;
    public String icon;
    public Object name;
    public Object company;
    public Object ios_store_url;
    public Object android_store_url;
    public Object extend_url;
    public String skip_type;
    public String credit_hidden;


    public String getAndroid() {
        return android;
    }

    public void setAndroid(String android) {
        this.android = android;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCredit_hidden() {
        return credit_hidden;
    }

    public void setCredit_hidden(String credit_hidden) {
        this.credit_hidden = credit_hidden;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
