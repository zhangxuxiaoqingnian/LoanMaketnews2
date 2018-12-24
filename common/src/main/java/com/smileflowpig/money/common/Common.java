package com.smileflowpig.money.common;

/**
 * @author qiujuer
 */

public class Common {


    public static class Guoshen {

        public final static String Cheacked = "CHEACKED";


    }


    public static class Daichao {

        public final static String CLICKNUMBER = "clicknumber";
        public final static String CLICKDATEFOUR = "clickdatefour";


    }


    /**
     * 一些不可变的永恒的参数
     * 通常用于一些配置.
     */
    public interface Constance {

        //手机号正则，11位手机号
        String REGEX_MOBILE = "[1][3,4,5,7,8][0-9]{9}$";
////         基础的网络请求地址
//        String API_URL = "http://api.kuxuan-inc.com/user/";
//         基础的网络请求地址`




        //正式接口
        String API_URL = "https://xiaohuazhu.tthbw.com/user/";
        //测试接口
      //String API_URL = "http://bw.quyaqu.com/user/";
     //String API_URL = "http://182.92.118.1:5220/user/";

        // 最大的上传图片大小860kb
        long MAX_UPLOAD_IMAGE_LENGTH = 860 * 1024;

        int showDialog = 0;

    }
}
