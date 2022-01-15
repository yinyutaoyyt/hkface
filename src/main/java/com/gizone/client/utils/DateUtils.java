/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.gizone.client.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    private static String[] weeks = {"7", "1", "2", "3", "4", "5", "6"};

    /**
     * 获取今天的日期
     * 格式：20200921
     *
     * @return String
     */
    public static String getTodateString() {
        String dateStr = DateUtils.getDate("yyyyMMdd");
        return dateStr;
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    public static String formatDateElseNull(Date date, Object... pattern) {
        if (date == null) {
            return null;
        }
        return formatDate(date, pattern);
    }


    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date
     * @return
     */
    public static long pastHour(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date
     * @return
     */
    public static long pastMinutes(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 获取两个日期之间的小时数
     *
     * @param before
     * @param after
     * @return
     */
    public static long getDistanceHour(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60);
    }

    /**
     * 获取两个日期之间的分钟数
     *
     * @param before
     * @param after
     * @return
     */
    public static long getDistanceMinute(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60);
    }

    /**
     * 转换为Calendar对象
     *
     * @param date 日期对象
     * @return Calendar对象
     */
    public static Calendar calendar(Date date) {
        return calendar(date.getTime());
    }

    /**
     * 转换为Calendar对象
     *
     * @param millis 时间戳
     * @return Calendar对象
     */
    public static Calendar calendar(long millis) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return cal;
    }

    // ------------------------------------ Offset start ----------------------------------------------

    /**
     * 获取某天的开始时间
     *
     * @param date 日期
     * @return Date
     */
    public static Date beginOfDay(Date date) {
        return beginOfDay(calendar(date)).getTime();
    }

    /**
     * 获取某天的结束时间
     *
     * @param date 日期
     * @return Date
     */
    public static Date endOfDay(Date date) {
        return endOfDay(calendar(date)).getTime();
    }

    /**
     * 获取某月的开始时间
     *
     * @param date 日期
     * @return Date
     */
    public static Date beginOfMonth(Date date) {
        return beginOfMonth(calendar(date)).getTime();
    }

    /**
     * 获取某天的结束时间
     *
     * @param calendar 日期 {@link Calendar}
     * @return {@link Calendar}
     */
    public static Calendar endOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar;
    }

    /**
     * 获取某天的开始时间
     *
     * @param calendar 日期 {@link Calendar}
     * @return {@link Calendar}
     */
    public static Calendar beginOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * 获取某月的开始时间
     *
     * @return
     */
    public static Calendar beginOfMonth(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    // --------------------------------------------------- Offset for now

    /**
     * 偏移毫秒数
     *
     * @param date   日期
     * @param offset 偏移毫秒数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetMillisecond(Date date, int offset) {
        return offset(date, Calendar.MILLISECOND, offset);
    }

    /**
     * 偏移秒数
     *
     * @param date   日期
     * @param offset 偏移秒数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetSecond(Date date, int offset) {
        return offset(date, Calendar.SECOND, offset);
    }

    /**
     * 偏移分钟
     *
     * @param date   日期
     * @param offset 偏移分钟数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetMinute(Date date, int offset) {
        return offset(date, Calendar.MINUTE, offset);
    }

    /**
     * 偏移小时
     *
     * @param date   日期
     * @param offset 偏移小时数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetHour(Date date, int offset) {
        return offset(date, Calendar.HOUR_OF_DAY, offset);
    }

    /**
     * 偏移天
     *
     * @param date   日期
     * @param offset 偏移天数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetDay(Date date, int offset) {
        return offset(date, Calendar.DAY_OF_YEAR, offset);
    }

    /**
     * 偏移周
     *
     * @param date   日期
     * @param offset 偏移周数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetWeek(Date date, int offset) {
        return offset(date, Calendar.WEEK_OF_YEAR, offset);
    }

    /**
     * 偏移月
     *
     * @param date   日期
     * @param offset 偏移月数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetMonth(Date date, int offset) {
        return offset(date, Calendar.MONTH, offset);
    }

    /**
     * 偏移年
     *
     * @param date   日期
     * @param offset 偏移年数，正数向未来偏移，负数向历史偏移
     * @return 偏移后的日期
     */
    public static Date offsetYear(Date date, int offset) {
        return offset(date, Calendar.YEAR, offset);
    }

    /**
     * 获取指定日期偏移指定时间后的时间
     *
     * @param date      基准日期
     * @param dateField 偏移的粒度大小（小时、天、月等）
     * @param offset    偏移量，正数为向后偏移，负数为向前偏移
     * @return 偏移后的日期
     */
    public static Date offset(Date date, int dateField, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(dateField, offset);
        return cal.getTime();
    }

    /**
     * 获取本周开始时间
     *
     * @return
     */
    public static Date getBeginDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 判断当前时间在结束时间内
     *
     * @param nowTime 当前时间
     * @param endTime 结束时间
     */
    public static boolean isBeforeDate(Date nowTime, Date endTime) {
        if (nowTime.getTime() == endTime.getTime()) {
            return true;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (date.before(end)) {
            return true;
        }
        return false;
    }

    /**
     * 判断当前时间在结束时间内
     *
     * @param nowTime               当前时间
     * @param endTime               结束时间
     * @param endTimeIsContainToDay 结束时间是否包含当天
     */
    public static boolean isBeforeDate(Date nowTime, Date endTime, boolean endTimeIsContainToDay) {
        return isBeforeDate(nowTime, endTimeIsContainToDay ? endOfDay(endTime) : endTime);
    }

    /**
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     * @author sunran   判断当前时间在时间区间内
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取指定日期上一天的时间
     *
     * @param date
     * @return
     */
    public static Date getLastDay(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, -24);
        String yesterdayDate = dateFormat.format(calendar.getTime());
        return DateUtils.parseDate(yesterdayDate);
    }

    /**
     * 获取指定日期下一天的数据
     *
     * @param date
     * @return
     */
    public static Date getNextDay(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, +24);
        String yesterdayDate = dateFormat.format(calendar.getTime());
        return DateUtils.parseDate(yesterdayDate);
    }

    /**
     * 传入的时间 到今天的时间过了多少 小时 分钟
     *
     * @param crossTime
     * @return
     */
    public static String todayMinutesToHours(Date crossTime) {
        long distanceMinute = DateUtils.getDistanceMinute(crossTime, new Date());
        return DateUtils.minutesToHours((int) distanceMinute, null, null);
    }

    /**
     * 分钟转为小时
     *
     * @param time
     * @return
     */
    public static String minutesToHours(Integer time, String formatHour, String formatMin) {
        if (StringUtils.isEmpty(formatHour)) {
            formatHour = Constant.FORMAT_HOUR;
        }
        if (StringUtils.isEmpty(formatMin)) {
            formatMin = Constant.FORMAT_MIN;
        }
        int hours = (int) Math.floor(time / 60);
        int minute = time % 60;
        return String.format("%s%s%s%s", hours, formatHour, minute, formatMin);
    }

    /**
     * 传入Data类型日期，返回字符串类型时间（ISO8601标准时间）
     *
     * @param date
     * @return
     */
    public static String getISO8601Timestamp(Date date) {
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        df.setTimeZone(tz);
        String nowAsISO = df.format(date);
        return nowAsISO;
    }

    /**
     * 传入Data类型日期，返回字符串类型时间（ISO8601标准时间）-不带毫秒
     *
     * @param date
     * @return
     */
    public static String getISO8601Time(Date date) {
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(tz);
        String nowAsISO = df.format(date);
        return nowAsISO;
    }

    /**
     * 传入Data类型日期，获取 年月日周时分秒 格式 2109072164758
     *
     * @param date
     * @return
     */
    public static String getYyMMddWeeekHHmmss(Date date) {
        String format = DateFormatUtils.format(date, "yyMMdd-HHmmss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int i = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return format.replace("-", weeks[i]);
    }

    /**
     * 获取某月的天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取上个月 年月 yyyy-mm
     *
     * @param date
     * @return
     */
    public static Date getLastDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    /**
     * YYYY-MM 后的时间
     *
     * @param date
     * @return
     */
    public static String getLastDates(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String format = sdf.format(DateUtils.getLastDate(date));
        return format;
    }

    /**
     * 获取上上个月 年月 yyyy-mm
     *
     * @param date
     * @return
     */
    public static Date getLastDate2(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -2);
        return cal.getTime();
    }

    /**
     * YYYY-MM 后的时间上上个月
     *
     * @param date
     * @return
     */
    public static String getLastDates2(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String format = sdf.format(DateUtils.getLastDate2(date));
        return format;
    }

    /**
     * 获取去年上个月 年月 yyyy-mm
     *
     * @param date
     * @return
     */
    public static Date getLastDateYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, -1);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取去年上个月 年月 yyyy-mm
     *
     * @param date
     * @return
     */
    public static String getLastDateYears(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String format = sdf.format(DateUtils.getLastDateYear(date));
        return format;
    }

    /**
     * 获取近几天的日期
     *
     * @param days
     * @return List<String>
     */
    public static Date getDateAdd(int days) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -days);
        return c.getTime();
    }

    /**
     * 最近几天日期
     *
     * @param days
     * @return
     */
    public static List<String> getDaysBetwwen(int days) {
        List<String> dayss = new ArrayList<>();
        Calendar start = Calendar.getInstance();
        start.setTime(getDateAdd(days));
        Long startTIme = start.getTimeInMillis();
        Calendar end = Calendar.getInstance();
        end.setTime(new Date());
        Long endTime = end.getTimeInMillis();
        Long oneDay = 1000 * 60 * 60 * 24l;
        Long time = startTIme;
        while (time <= endTime) {
            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(df.format(d));
            dayss.add(df.format(d));
            time += oneDay;
        }
        return dayss;
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static String time() {
        long time = new Date().getTime();
        return String.valueOf(time);
    }


    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String format = sdf.format(DateUtils.getLastDateYear(new Date()));
        System.out.println(format);

//        System.out.println(DateUtils.getISO8601Timestamp(new Date()));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));

//		Date now = DateUtils.parseDate("2018-11-13 10:21:02");
//		Date a = DateUtils.parseDate("2018-11-13");
//		Date b = DateUtils.beginOfMonth(a);
//		System.out.println(DateUtils.formatDate(b));
//
//		boolean isBefore = DateUtils.isBeforeDate(now, a);
//		System.out.println(isBefore);

		/*a = DateUtils.setHours(a, 0);
		a = DateUtils.setMinutes(a, 0);
		a = DateUtils.setSeconds(a, 0);*/
//		System.out.println(formatDateTime(DateUtils.offsetYear(a, -1)));
//		Date date = DateUtils.parseDate("2021-06-08 00:00:00");
//		System.out.println(DateUtils.offsetWeek(date,1));
//		System.out.println(DateUtils.getNextDay(date));
    }
}
