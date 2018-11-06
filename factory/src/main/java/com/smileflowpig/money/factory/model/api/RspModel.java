package com.smileflowpig.money.factory.model.api;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class RspModel<T> {

    //成功
    public static final int SUCCEED = 0;
    //参数错误
    public static final int PARAMETER_ERROR = 00001;
    //文件上传失败
    public static final int FILE_UPLOAD_FAILED = 00002;
    //缺少数据，请重新提交
    public static final int MISSING_DATA = 00003;
    //数据库查询失败
    public static final int DATABASE_QUERY_FAILED = 00004;
    //无查询结果
    public static final int NO_QUERY_RESULTS = 00005;
    //数据库更新错误
    public static final int DATABASE_UPDATE_ERROR = 00006;
    //未指定请求方式
    public static final int REQUESTED_MODE_NOT_SPECIFIED = 00007;
    //验证码错误
    public static final int VERIFY_CODE_ERROR = 10001;
    //未更新数据
    public static final int DATA_NOT_UPDATED = 10002;
    //密码错误
    public static final int PASSWORD_ERROR = 10003;
    //邮箱已注销
    public static final int THE_MAILBOX_HAS_BEEN_CANCELED = 10004;
    //登陆失败
    public static final int LOGON_FAILURE = 10005;
    //用户不存在
    public static final int USER_DOES_NOT_EXIST =10006;
    //非当前用户登录
    public static final int NON_CURRENT_USER_LOGIN = 10007;
    //验证问题错误
    public static final int VALIDATION_PROBLEM_ERROR = 10008;
    //验证错误
    public static final int VALIDATION_ERROR= 10009;
    //用户未登录
    public static final int USER_NOT_LOGGED_IN = 10010;
    //用户未登录或过期
    public static final int THE_USER_IS_NOT_LOGGED_IN_OR_EXPIRED = 10011;
    //密码为空
    public static final int THE_PASSWORD_IS_EMPTY = 10012;
    //邮件发送失败
    public static final int MAIL_FAILED = 10013;
    //新密码为空
    public static final int THE_NEW_PASSWORD_IS_EMPTY = 10014;
    //用户信息为空
    public static final int USER_INFORMATION_IS_EMPTY = 10015;
    //邮箱为空
    public static final int MAILBOX_EMPTY = 10016;
    //用户id为空
    public static final int USER_ID_IS_EMPTY = 10019;
    //验证问题错误
    public static final int USER_AUTHENTICATION_FAILED = 10020;
    //短信发送失败
    public static final int SMS_FAILED = 10021;
    //验证码有误
    public static final int THE_VERIFICATION_CODE_IS_OUT_OF_ORDER =10023;
    //已经通过短信验证，请勿重复
    public static final int SMS_VERIFICATION_HAS_BEEN_DONE = 10024;
    //手机号码格式有误
    public static final int THE_CELL_PHONE_NUMBER_FORMAT_IS_WRONG = 10025;
    //用户信息更新失败
    public static final int USER_INFORMATION_FAILED_TO_UPDATE= 10026;
    //密码跟旧密码一致
    public static final int THE_PASSWORD_IS_THE_SAME_AS_THE_OLD_ONE= 10027;
    //密码长度至少六位
    public static final int PASSWORD_LENGTH_IS_AT_LEAST_SIX_BITS= 10028;
    //用户名已存在
    public static final int   USER_NAME_ALREADY_EXISTS= 10029;
    //openid为空，请重试
    public static final int OPENID_IS_EMPTY= 10031;
    //手机已经被注册
    public static final int THE_CELL_PHONE_HAS_BEEN_REGISTERED= 10032;
    //获取验证码失败，请重试
    public static final int FAILED_TO_GET_THE_VERIFICATION_CODE= 10033;
    //获取token错误，请重试
    public static final int GET_THE_TOKEN_ERROR= 10036;
    //该手机尚未注册，请重试
    public static final int THE_PHONE_HAS_NOT_BEEN_REGISTERED= 10051;
    //验证信息已过期，请重新找回
    public static final int VALIDATION_INFORMATION_HAS_EXPIRED= 10052;
    //两次密码不一致，请检查重试
    public static final int THE_TWO_PASSWORD_IS_INCONSISTENT= 10053;
    //请输入产品id
    public static final int PLEASE_ENTER_THE_PRODUCT_ID= 20001;
    //请输入需要期数
    public static final int PERIOD_ERROR= 20002;
    //请输入贷款的金额
    public static final int THE_LOAN_AMOUNT_IS_WRONG= 20003;
    //没有权限
    public static final int NO_PERMISSIONS= 90001;
    //没有登录
    public static final int NOT_LOGGED_ON= 90002;
    //数据库问题
    public static final int DATABASE_PROBLEM= 90003;

    private int errno;
    private String err;
    private String  timestamp;
    private T rst;


    public boolean success() {
        return errno == SUCCEED;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getRst() {
        return rst;
    }

    public void setRst(T rst) {
        this.rst = rst;
    }
}