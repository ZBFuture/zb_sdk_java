package sign;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateLocalUtil {

    /**
     * UTC时间格式转北京时间
     * 北京时间为东八时区,领先UTC时间8小时
     *
     * @param UTCStr
     * @param format 例如: yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static String UTCToCST(String UTCStr, String format) {
        //UTC时间格式: Fri Nov 22 02:08:38 CST 2019
        String dateStr = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date date = sdf.parse(UTCStr);
            System.out.println("UTC时间: " + date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, 8);
            //calendar.getTime() 返回的是Date类型，也可以使用calendar.getTimeInMillis()获取时间戳
            //北京时间: Fri Nov 22 10:08:38 CST 2019
            System.out.println("北京时间: " + calendar.getTime());
            dateStr = new SimpleDateFormat(format).format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateStr;
    }

    public static String toUTC(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, -8);
        return format.format(calendar.getTime());
    }


    public static Date toLocalDate(String utcDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            Date date = format.parse(utcDate);
            return DateUtils.addHours(date, 8);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String toUTC() {
        return toUTC(new Date());
    }

    public static void main(String[] args) {
        System.out.println(toUTC());
        System.out.println(new Date());
    }

}
