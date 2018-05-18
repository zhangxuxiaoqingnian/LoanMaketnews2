package money.kuxuan.platform.factory.model.api.account;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class CodeModel {
    private String phone;
    private String qrcode;
    private String smstype;


    public CodeModel(String phone, String qrcode, String smstype) {
        this.phone = phone;
        this.qrcode = qrcode;
        this.smstype = smstype;
    }

    public CodeModel(String phone, String smstype) {
        this.phone = phone;
        this.smstype = smstype;
    }
}
