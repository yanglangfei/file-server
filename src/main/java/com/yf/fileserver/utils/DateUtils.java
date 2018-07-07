package com.yf.fileserver.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 */
public final class DateUtils {

    private static final SimpleDateFormat SDF_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SDF_yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String TIME_START = "00:00:00";
    private static final String TIME_END = "23:59:59";

    /**
     * 获得当前日期的前N天的全部日期
     *
     * @param date
     * @param num
     * @return
     * @throws ParseException
     */
    public static List<String> getBeforeAllDate(String date, Integer num) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(SDF_yyyy_MM_dd.parse(date));
        calendar.add(Calendar.DATE, -num);
        String before = SDF_yyyy_MM_dd.format(calendar.getTime());
        return getAllDate(before, date);
    }

    /**
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static List<String> getAllDate(String startDate, String endDate) throws ParseException {
        Calendar start = Calendar.getInstance();
        start.setTime(SDF_yyyy_MM_dd.parse(startDate));
        Long startTIme = start.getTimeInMillis();

        Calendar end = Calendar.getInstance();
        end.setTime(SDF_yyyy_MM_dd.parse(endDate));
        Long endTime = end.getTimeInMillis();

        Long oneDay = 1000 * 60 * 60 * 24L;

        List<String> dates = new ArrayList<String>();
        Long time = startTIme;
        while (time <= endTime) {
            Date d = new Date(time);
            dates.add(SDF_yyyy_MM_dd.format(d));
            time += oneDay;
        }
        return dates;
    }

    /**
     * @param dateStr
     * @return
     */
    public static Date format(String dateStr) throws ParseException {
        return SDF_yyyy_MM_dd.parse(dateStr);
    }

    public static Date formatDateTime(String dateStr) throws ParseException {
        return SDF_yyyy_MM_dd_HH_mm_ss.parse(dateStr);
    }

    /**
     * 获得当前日期的日期
     *
     * @return yyyy-MM-dd
     */
    public static String getDate() {
        return getDate(new Date());
    }

    /**
     * 获得指定日期的日期
     *
     * @param date
     * @return yyyy-MM-dd
     */
    public static String getDate(Date date) {
        return getDateTimes(date)[0];
    }

    /**
     * 获取当前日期的时间
     *
     * @return
     */
    public static String getTime() {
        return getTime(new Date());
    }

    /**
     * 获取指定日期的时间
     *
     * @param date
     * @return HH:mm:ss
     */
    public static String getTime(Date date) {
        return getDateTimes()[1];
    }

    /**
     * 获得当前日期的日期时间字符串分割数组
     *
     * @return [0]=yyyy-MM-dd,[1]=HH:mm:ss
     */
    public static String[] getDateTimes() {
        return getDateTimes(new Date());
    }

    /**
     * 获得指定日期的日期时间字符串分割数组
     *
     * @param date
     * @return [0]=yyyy-MM-dd,[1]=HH:mm:ss
     */
    public static String[] getDateTimes(Date date) {
        return getDateTime(date).split(" ");
    }

    /**
     * 获得当前日期的日期时间字符串
     *
     * @return
     */
    public static String getDateTime() {
        return getDateTime(new Date());
    }

    /**
     * 获得指定日期的日期时间字符串
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTime(Date date) {
        return SDF_yyyy_MM_dd_HH_mm_ss.format(date);
    }

    /**
     * 获取当天查询日期范围
     *
     * @return ["2017-07-01 00:00:00", "2017-07-02 00:00:00"]
     */
    public static String[] getQueryDateTimeToday() {
        return formatQueryDateTimeToday(null, null);
    }

    /**
     * 格式化查询日期,如果为空，默认为当天
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String[] formatQueryDateTimeToday(String startDate, String endDate) {
        //当前时间
        Date now = new Date();


        if (startDate == null || startDate.trim().length() == 0) {
            startDate = DateUtils.getDate(now) + " " + TIME_START;
        }
        if (endDate == null || endDate.trim().length() == 0) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(now);
            calendar.add(Calendar.DATE, 1);//明天
            Date tomorrow = calendar.getTime();
            endDate = DateUtils.getDate(tomorrow) + " " + TIME_START;
        }
        return new String[]{startDate, endDate};
    }
}
