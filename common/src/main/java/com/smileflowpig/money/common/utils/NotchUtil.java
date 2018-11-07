package com.smileflowpig.money.common.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Method;

/**
 * 刘海屏工具类
 * Created by xieshengqi on 2018/10/26.
 */

public class NotchUtil {

    /**
     * 获取手机chang'shang厂商
     *
     * @return
     */
    public static String getPhoneMessage() {
        String brand = Build.BRAND;
        return brand;

    }

    /**
     * 判断华为手机是否有刘海屏了
     *
     * @param context
     * @return
     */
    public static boolean hasNotchAtHuawei(Context context) {
        boolean ret = false;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class HwNotchSizeUtil = classLoader.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("Notch", "hasNotchAtHuawei ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("Notch", "hasNotchAtHuawei NoSuchMethodException");
        } catch (Exception e) {
            Log.e("Notch", "hasNotchAtHuawei Exception");
        } finally {
            return ret;
        }


    }

    /**
     * 获取华为刘海屏尺寸
     *
     * @param context
     * @return
     */
    public static int[] getNotchSizeAtHuawei(Context context) {
        int[] ret = new int[]{0, 0};
        try {
            ClassLoader cl = context.getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize");
            ret = (int[]) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("Notch", "getNotchSizeAtHuawei ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("Notch", "getNotchSizeAtHuawei NoSuchMethodException");
        } catch (Exception e) {
            Log.e("Notch", "getNotchSizeAtHuawei Exception");
        } finally {
            return ret;
        }
    }

    public static final int VIVO_NOTCH = 0x00000020;//是否有刘海
    public static final int VIVO_FILLET = 0x00000008;//是否有圆角
    public static final int VIVO_NOTCHHEIGHT = 35;//刘海屏固定高度


    //vivo是否有刘海屏
    public static boolean hasNotchAtVivo(Context context) {
        boolean ret = false;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class FtFeature = classLoader.loadClass("android.util.FtFeature");
            Method method = FtFeature.getMethod("isFeatureSupport", int.class);
            ret = (boolean) method.invoke(FtFeature, VIVO_NOTCH);
        } catch (ClassNotFoundException e) {
            Log.e("Notch", "hasNotchAtVivo ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("Notch", "hasNotchAtVivo NoSuchMethodException");
        } catch (Exception e) {
            Log.e("Notch", "hasNotchAtVivo Exception");
        } finally {
            return ret;
        }
    }

    //oppo是否有刘海屏
    public static boolean hasNotchAtOPPO(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }


    //小米刘海屏高度略低于状态啦高度
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }


    /**
     * 适配刘海屏
     *
     * @param view
     * @param context
     */
    public static void adaptationView(ViewGroup view, Context context) {
        String phoneMessage = getPhoneMessage();
        int padTop = 0;
//        int padTop = view.getPaddingTop();
        int padLeft = view.getPaddingLeft();
        int padrigth = view.getPaddingRight();
        int padBottom = view.getPaddingBottom();
        View stateBar = new View(context);
        ViewGroup.LayoutParams pa = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(context));
//        View childAt = view.getChildAt(1);
//        if (childAt == null) {
//            return;
//        }
        if (!TextUtils.isEmpty(phoneMessage)) {
//            if (phoneMessage.equals("Huawei")) {
//                if (hasNotchAtHuawei(context)) {
//                    int[] notchSizeAtHuawei = getNotchSizeAtHuawei(context);
//                    padTop = padTop + notchSizeAtHuawei[1];
//
//                }
//            } else if (phoneMessage.equals("Oppo")) {
//                if (hasNotchAtOPPO(context)) {
//                    padTop = padTop + DisplayUtil.dip2px(VIVO_NOTCHHEIGHT);
//                }
//            } else if (phoneMessage.equals("Vivo")) {
//                if (hasNotchAtVivo(context)) {
//                    padTop = padTop + DisplayUtil.dip2px(VIVO_NOTCHHEIGHT);
//                }
//            } else if (phoneMessage.equals("Xiaomi")) {
//                padTop = padTop + getStatusBarHeight(context);
//            } else if (phoneMessage.equals("google")) {
//                padTop = padTop + DisplayUtil.dip2px(VIVO_NOTCHHEIGHT);
//            }
//            view.setPadding(padLeft, padTop, padrigth, padBottom);
            pa.height = DisplayUtil.dip2px(VIVO_NOTCHHEIGHT);
//            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
//            layoutParams.height = layoutParams.height + paddingTop;
//            childAt.setLayoutParams(layoutParams);
//            childAt.setPadding(childAt.getPaddingLeft(), paddingTop, childAt.getPaddingRight(), childAt.getPaddingBottom());
            stateBar.setLayoutParams(pa);
                stateBar.setBackgroundColor(Color.parseColor("#FEB727"));

            view.addView(stateBar, 0);
        }
    }


}
