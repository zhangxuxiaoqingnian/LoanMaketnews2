package money.kuxuan.platform.factory.model.api.launcher;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class LaunchRspModel {

    private String android;
    private String url;
    private String version;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAndroid() {
        return android;
    }

    public void setAndroid(String android) {
        this.android = android;
    }
}
