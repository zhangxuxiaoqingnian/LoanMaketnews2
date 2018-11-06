package com.smileflowpig.money.moneyplatfrom.helper;

/**
 * Created by xieshengqi on 2017/4/25.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by IT_mo on 2016/5/12.
 * QQ:420733721
 */
public class SPUtil {

    /**
     * SharedPreferences存储在sd卡中的文件名字
     */
    private static String getSpName(Context context) {
        return context.getPackageName() + "_preferences";
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     */
    public static void putAndApply(Context context, String key, Object o) {

        SharedPreferences sp = context.getSharedPreferences(getSpName(context), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (o instanceof String) {
            editor.putString(key, (String) o);
        } else if (o instanceof Integer) {
            editor.putInt(key, (Integer) o);
        } else if (o instanceof Float) {
            editor.putFloat(key, (Float) o);
        } else if (o instanceof Long) {
            editor.putLong(key, (Long) o);
        } else if (o instanceof Boolean) {
            editor.putBoolean(key, (Boolean) o);
        } else {
            editor.putString(key, o.toString());
        }
        //提交
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 放入对象
     *
     * @param context
     * @param key
     * @param t
     */
    public static void setGsonData(Context context, String key, Object t) {
        Gson gson = new Gson();
        String json = gson.toJson(t);
        SharedPreferences sp = context.getSharedPreferences(getSpName(context), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, json);
        SharedPreferencesCompat.apply(editor);
    }
//
//    /**
//     * 取出对象
//     *
//     * @param context
//     * @param key
//     * @param object
//     * @return
//     */
//    public static Object getGsonData(Context context, String key, Object object) {
//        Gson gson = new Gson();
//        SharedPreferences sp = context.getSharedPreferences(getSpName(context), Context.MODE_PRIVATE);
//        String json = sp.getString(key, null);
//        if (object instanceof User) {
//            Type type = new TypeToken<User>() {
//            }.getType();
//            User user = null;
//            try {
//                user = gson.fromJson(json, type);
//            } catch (Exception e) {
//            }
//            return user;
//        }
//        return null;
//    }


    /**
     * 放入权限
     *
     * @param context
     * @param key
     * @param json
     */
    public static void saveAuth(Context context, String key, String json) {
        SharedPreferences sp = context.getSharedPreferences(getSpName(context), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, json);
        SharedPreferencesCompat.apply(editor);
    }

//    /**
//     * 获取权限集合
//     *
//     * @param context
//     * @param key
//     * @return
//     */
//    public static ArrayList<MobileRecourceJson> getAuthJsonLists(Context context, String key) {
//        Gson gson = new Gson();
//        SharedPreferences sp = context.getSharedPreferences(getSpName(context), Context.MODE_PRIVATE);
//        String json = sp.getString(key, null);
//        Type type = new TypeToken<ArrayList<MobileRecourceJson>>() {
//        }.getType();
//        ArrayList<MobileRecourceJson> lists = null;
//        try {
//            lists = gson.fromJson(json, type);
//        } catch (Exception e) {
//
//        }
//        return lists;
//    }
//
//    /**
//     * 判断是不是项目超级管理员
//     *
//     * @return
//     */
//    public static boolean isSuperManager(Context context, String key) {
//        SharedPreferences sp = context.getSharedPreferences(getSpName(context), Context.MODE_PRIVATE);
//        return sp.getBoolean(key, false);
//    }
//
//    /**
//     * 搜索历史记录
//     *
//     * @param context
//     * @param key
//     * @return
//     */
//    public static ArrayList<SearchData> getSearchData(Context context, String key) {
//        Gson gson = new Gson();
//        SharedPreferences sp = context.getSharedPreferences(getSpName(context), Context.MODE_PRIVATE);
//        String json = sp.getString(key, null);
//        Type type = new TypeToken<ArrayList<SearchData>>() {
//        }.getType();
//        ArrayList<SearchData> list = null;
//        try {
//            list = gson.fromJson(json, type);
//        } catch (Exception e) {
//        }
//        return list;
//
//
//    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     */
    public static Object get(Context context, String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(getSpName(context), Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        } else {
            return null;
        }
    }

    /**
     * 移除某个key值已经对应的值
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(getSpName(context), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     */
    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(getSpName(context), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(getSpName(context), Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(getSpName(context), Context.MODE_PRIVATE);
        return sp.getAll();
    }

    //*************************************内部类*******************************************//

    /**
     * 优先使用SharedPreferences的apply方法，如果找不到则使用commit方法
     */
    private static class SharedPreferencesCompat {

        //查看SharedPreferences是否有apply方法
        private static final Method sApplyMethod = findApply();

        private static Method findApply() {

            try {
                Class cls = SharedPreferences.class;
                return cls.getMethod("apply");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         */
        public static void apply(SharedPreferences.Editor editor) {

            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            editor.commit();
        }

    }

}