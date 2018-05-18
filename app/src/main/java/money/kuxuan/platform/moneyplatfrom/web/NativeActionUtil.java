package money.kuxuan.platform.moneyplatfrom.web;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import com.asm.base.android.util.app.AppInfo;
import com.asm.base.android.util.app.AppMethods;

import java.util.HashMap;
import java.util.List;

public class NativeActionUtil {
    private static final String NATIVE_ACTION_START = "nativeaction://";

    public static boolean dealNativeUrl(WebActivity webActivity, String url) {

      if (!TextUtils.isEmpty(url) && url.startsWith("tel:")) {
            dealWithTel(webActivity, url);
            return true;
        } else if (!TextUtils.isEmpty(url) && url.startsWith("sms:")) {
            dealWithSms(webActivity, url);
            return true;
        } else if (!TextUtils.isEmpty(url) && url.startsWith("mailto:")) {
            dealWithMail(webActivity, url);
            return true;
        }  if (!url.startsWith("http")) {
            try {
                // 以下固定写法
                final Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(url));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                webActivity.startActivity(intent);
            } catch (Exception e) {
                // 防止没有安装的情况
                e.printStackTrace();
            }
            return true;
        }
    else if (!TextUtils.isEmpty(url) && url.contains(".apk?")) {
          dealWithApk(webActivity, url);
          return true;
      }
        else if (!TextUtils.isEmpty(url) && url.contains(".apk")) {
            dealWithApk(webActivity, url);
            return true;
        }


        return false;
    }

    public static void dealWithTel(final Activity activity, final String url) {
        Uri uri = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_DIAL, uri);
        if (canHandleIntent(it)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("温馨提示").setMessage("是否询问客服？").setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                        //android6.0后可以对权限判断
                        if (ActivityCompat
                            .checkSelfPermission(activity, android.Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                            AppMethods.showToast("请在应用管理中打开“电话”访问权限！");
                            return;
                        }
                        activity.startActivity(intent);
                    }
                }).show();
        } else {
            // 当前手机不支持
            AppMethods.showToast("当前设备不支持此操作");
        }
    }

    public static void dealWithMail(Activity activity, String url) {
        Uri uri = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        if (canHandleIntent(it)) {
            activity.startActivity(it);
        } else {
            // 当前手机不支持
            AppMethods.showToast("当前设备不支持此操作");
        }
    }

    public static void dealWithSms(Activity activity, String url) {
        Uri uri = Uri.parse("smsto:" + url.substring(4, url.length()));
        Intent sms = new Intent(Intent.ACTION_SENDTO, uri);
        sms.putExtra("sms_body", "");
        if (canHandleIntent(sms)) {
            activity.startActivity(sms);
        } else {
            // 当前手机不支持
            AppMethods.showToast("当前设备不支持此操作");
        }
    }

    public static void dealWithApk(Activity activity, String url) {

            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            activity.startActivity(intent);
        }



    /**
     * 是否支持该intent的处理
     */
    public static boolean canHandleIntent(Intent intent) {
        final PackageManager packageManager = AppInfo.getAppContext().getPackageManager();
        List<ResolveInfo> supportList =
            packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (supportList != null && supportList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }


    private static class NativeUrlParse {
        private String url;
        private String function; // 方法字段
        private HashMap<String, String> parameters; // 参数

        public NativeUrlParse(String url) {
            this.url = url.trim();
            parse();

        }

        public String getUrl() {
            return url;
        }

        /**
         * 获取方法字段
         *
         * @return
         */
        public String getFunction() {
            return function;
        }

        /**
         * 获取参数
         *
         * @return
         */
        public HashMap<String, String> getParameters() {
            return parameters;
        }

        /**
         * 解析
         */
        public void parse() {
            if (!TextUtils.isEmpty(url)) {
                String parameterString = url.substring(NATIVE_ACTION_START.length());
                int functionEndIndex = parameterString.indexOf("?");
                if (functionEndIndex >= 0) {
                    function = parameterString.substring(0, functionEndIndex);
                    String parameterSplits[] =
                        parameterString.substring(functionEndIndex + 1).split("&");
                    parseParameters(parameterSplits);
                } else {
                    function = parameterString;
                }
            }
        }

        /**
         * 解析参数
         *
         * @param parameterSplits
         */
        private void parseParameters(String parameterSplits[]) {
            if (parameterSplits != null && parameterSplits.length > 0) {
                if (parameters == null) {
                    parameters = new HashMap<String, String>();
                }
                for (String parameter : parameterSplits) {
                    int epos = parameter.indexOf('=');
                    if (epos > 0 && epos < parameter.length() - 1) {
                        parameters.put(parameter.substring(0, epos), parameter.substring(epos + 1));
                    }
                }
            }
        }

    }

}

