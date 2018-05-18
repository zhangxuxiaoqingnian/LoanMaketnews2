package money.kuxuan.platform.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 *
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public class DateTimeUtil {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yy-MM-dd", Locale.ENGLISH);

    /**
     * 获取一个简单的时间字符串
     *
     * @param date Date
     * @return 时间字符串
     */
    public static String getSampleDate(Date date) {
        return FORMAT.format(date);
    }


    /**
     * 计算时间差
     * @return
     */
    public static int getDatedifference(String bigenDate,String endDate){
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long from = 0;
        long to = 0;
        try {
            from = simpleFormat.parse(bigenDate).getTime();
            to = simpleFormat.parse(endDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int days = (int) ((to - from)/(1000 * 60 * 60 * 24));

        return days;
    }



}
