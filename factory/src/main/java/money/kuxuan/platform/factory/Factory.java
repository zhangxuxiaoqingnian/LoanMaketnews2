package money.kuxuan.platform.factory;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.StringRes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import money.kuxuan.platform.common.app.Application;
import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.persistence.Account;



/**
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public class Factory {
    private static final String TAG = Factory.class.getSimpleName();
    // 单例模式
    private static final Factory instance;
    // 全局的线程池
    private final Executor executor;
    // 全局的Gson
    private final Gson gson;


    static {
        instance = new Factory();
    }

    private Factory() {
        // 新建一个4个线程的线程池
        executor = Executors.newFixedThreadPool(4);
        gson = new GsonBuilder()
                // 设置时间格式
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
//                // 设置一个过滤器，数据库级别的Model不进行Json转换
//                .setExclusionStrategies(new DBFlowExclusionStrategy())
                .create();
    }

    /**
     * Factory 中的初始化
     */
    public static void setup() {
//        // 初始化数据库
//        FlowManager.init(new FlowConfig.Builder(app())
//                .openDatabasesOnInit(true) // 数据库初始化的时候就开始打开
//                .build());

//         持久化的数据进行初始化
        Account.load(app());
    }

    /**
     * 返回全局的Application
     *
     * @return Application
     */
    public static Application app() {
        return Application.getInstance();
    }


    /**
     * 异步运行的方法
     *
     * @param runnable Runnable
     */
    public static void runOnAsync(Runnable runnable) {
        // 拿到单例，拿到线程池，然后异步执行
        instance.executor.execute(runnable);
    }

    /**
     * 返回一个全局的Gson，在这可以进行Gson的一些全局的初始化
     *
     * @return Gson
     */
    public static Gson getGson() {
        return instance.gson;
    }


    /**
     * 进行错误Code的解析，
     * 把网络返回的Code值进行统一的规划并返回为一个String资源
     *
     * @param model    RspModel
     * @param callback DataSource.FailedCallback 用于返回一个错误的资源Id
     */
    public static void decodeRspCode(RspModel model, DataSource.FailedCallback callback) {

        if (model == null) {
            decodeRspCode(R.string.no_data, callback);
            return;
        }

        // 进行Code区分
        switch (model.getErrno()) {
            case RspModel.SUCCEED:
                return;
            case RspModel.PARAMETER_ERROR:
                decodeRspCode(R.string.data_rsp_error_parameters, callback);
                break;
            case RspModel.FILE_UPLOAD_FAILED:
                decodeRspCode(R.string.data_upload_error, callback);
                break;
            case RspModel.MISSING_DATA:
                decodeRspCode(R.string.missing_data, callback);
                break;
            case RspModel.DATABASE_QUERY_FAILED:
                decodeRspCode(R.string.database_query_failed, callback);
                break;
            case RspModel.NO_QUERY_RESULTS:
                decodeRspCode(R.string.no_query_results, callback);
                break;
            case RspModel.DATABASE_UPDATE_ERROR:
                decodeRspCode(R.string.user_does_not_exist, callback);
                break;
            case RspModel.REQUESTED_MODE_NOT_SPECIFIED:
                decodeRspCode(R.string.requested_mode_not_specified, callback);
                break;
            case RspModel.VERIFY_CODE_ERROR:
                decodeRspCode(R.string.verify_code_error, callback);
                break;
            case RspModel.DATA_NOT_UPDATED:
                decodeRspCode(R.string.data_not_updated, callback);
                break;
            case RspModel.THE_MAILBOX_HAS_BEEN_CANCELED:
                decodeRspCode(R.string.the_mailbox_has_been_canceled, callback);
                break;
            case RspModel.LOGON_FAILURE:
                decodeRspCode(R.string.logon_failure, callback);
                break;
            case RspModel.USER_DOES_NOT_EXIST:
                decodeRspCode(R.string.user_does_not_exist, callback);
                break;
            case RspModel.NON_CURRENT_USER_LOGIN:
                decodeRspCode(R.string.non_current_user_login, callback);
                break;
            case RspModel.VALIDATION_PROBLEM_ERROR:
                decodeRspCode(R.string.validation_problem_error, callback);
                break;
            case RspModel.VALIDATION_ERROR:
                decodeRspCode(R.string.validation_error, callback);
                break;
            case RspModel.USER_NOT_LOGGED_IN:
                decodeRspCode(R.string.user_not_logged_in, callback);
                break;
            case RspModel.THE_USER_IS_NOT_LOGGED_IN_OR_EXPIRED:
                decodeRspCode(R.string.the_user_is_not_logged_in_or_expired, callback);
                break;
            case RspModel.THE_PASSWORD_IS_EMPTY:
                decodeRspCode(R.string.the_password_is_empty, callback);
                break;
            case RspModel.MAIL_FAILED:
                decodeRspCode(R.string.mail_failed, callback);
                break;
            case RspModel.THE_NEW_PASSWORD_IS_EMPTY:
                decodeRspCode(R.string.the_new_password_is_empty, callback);
                break;
            case RspModel.USER_INFORMATION_IS_EMPTY:
                decodeRspCode(R.string.user_information_is_empty, callback);
                break;
            case RspModel.MAILBOX_EMPTY:
                decodeRspCode(R.string.mailbox_empty, callback);
                break;

            case RspModel.USER_ID_IS_EMPTY:
                decodeRspCode(R.string.user_id_is_empty, callback);
                break;
            case RspModel.USER_AUTHENTICATION_FAILED:
                decodeRspCode(R.string.user_authentication_failed, callback);
                break;
            case RspModel.SMS_FAILED:
                decodeRspCode(R.string.sms_failed, callback);
                break;
            case RspModel.THE_VERIFICATION_CODE_IS_OUT_OF_ORDER:
                decodeRspCode(R.string.the_verification_code_is_out_of_order, callback);
                break;
            case RspModel.SMS_VERIFICATION_HAS_BEEN_DONE:
                decodeRspCode(R.string.sms_verification_has_been_done, callback);
                break;
            case RspModel.THE_CELL_PHONE_NUMBER_FORMAT_IS_WRONG:
                decodeRspCode(R.string.the_cell_phone_number_format_is_wrong, callback);
                break;
            case RspModel.USER_INFORMATION_FAILED_TO_UPDATE:
                decodeRspCode(R.string.user_information_failed_to_update, callback);
                break;
            case RspModel.THE_PASSWORD_IS_THE_SAME_AS_THE_OLD_ONE:
                decodeRspCode(R.string.the_password_is_the_same_as_the_old_one, callback);
                break;
            case RspModel.PASSWORD_LENGTH_IS_AT_LEAST_SIX_BITS:
                decodeRspCode(R.string.password_length_is_at_least_six_bits, callback);
                break;
            case RspModel.USER_NAME_ALREADY_EXISTS:
                decodeRspCode(R.string.user_name_already_exists, callback);
                break;
            case RspModel.OPENID_IS_EMPTY:
                decodeRspCode(R.string.openid_is_empty, callback);
                break;
            case RspModel.THE_CELL_PHONE_HAS_BEEN_REGISTERED:
                decodeRspCode(R.string.the_cell_phone_has_been_registered, callback);
                break;
            case RspModel.FAILED_TO_GET_THE_VERIFICATION_CODE:
                decodeRspCode(R.string.failed_to_get_the_verification_code, callback);
                break;
            case RspModel.GET_THE_TOKEN_ERROR:
                decodeRspCode(R.string.get_the_token_error, callback);
                break;
            case RspModel.THE_PHONE_HAS_NOT_BEEN_REGISTERED:
                decodeRspCode(R.string.the_phone_has_not_been_registered, callback);
                break;
            case RspModel.VALIDATION_INFORMATION_HAS_EXPIRED:
                decodeRspCode(R.string.validation_information_has_expired, callback);
                break;
            case RspModel.THE_TWO_PASSWORD_IS_INCONSISTENT:
                decodeRspCode(R.string.the_two_password_is_inconsistent, callback);
                break;
            case RspModel.PLEASE_ENTER_THE_PRODUCT_ID:
                decodeRspCode(R.string.please_enter_the_product_id, callback);
                break;
            case RspModel.PERIOD_ERROR:
                decodeRspCode(R.string.period_error, callback);
                break;
            case RspModel.THE_LOAN_AMOUNT_IS_WRONG:
                decodeRspCode(R.string.the_loan_amount_is_wrong, callback);
                break;
            case RspModel.NO_PERMISSIONS:
                decodeRspCode(R.string.no_permissions, callback);
                break;
            case RspModel.NOT_LOGGED_ON:
                decodeRspCode(R.string.not_logged_on, callback);
                break;
            case RspModel.DATABASE_PROBLEM:
                decodeRspCode(R.string.data_rsp_error_parameters, callback);
                break;
            case RspModel.PASSWORD_ERROR:
                decodeRspCode(R.string.password_error, callback);
                break;
            default:
                decodeRspCode(R.string.data_network_error, callback);
                break;
        }
    }

    /**
     * 将网络返回的Code解析，把网络返回的Code只进行统一的规划并划分一个String资源
     *
     * @param resId
     * @param callback 用于返回一个错误的ID
     */

    private static void decodeRspCode(@StringRes final int resId,
                                      final DataSource.FailedCallback callback) {
        if (callback != null)
            callback.onDataNotAvailable(resId);
    }


    /**
     * 收到账户退出的消息需要进行账户退出重新登录
     */
    private void logout() {

    }

    /**
     * 获取版本号
     * @param context
     * @return
     * @throws Exception
     */
    public static String getVersionName(Context context) throws Exception
    {
        // 获取packagemanager的实例
        PackageManager packageManager =context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
        String version = packInfo.versionName;
        return version;
    }

}
