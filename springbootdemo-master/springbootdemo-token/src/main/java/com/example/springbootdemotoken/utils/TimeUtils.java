package com.example.springbootdemotoken.utils;

import cn.hutool.core.date.DateUnit;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;


public class TimeUtils {

    private static String timePattern = "yyyy-MM-dd HH:mm:ss";

    private static LocalDate FIRSTDAYOFMONTH = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());

    private static LocalDate LASTDAYOFMONTH = FIRSTDAYOFMONTH.with(TemporalAdjusters.lastDayOfMonth());

    private static LocalDate FIRSTDAYOFNEXTMONTH = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).plusDays(1);

    private static LocalDate LASTDAYOFNEXTMONTH = FIRSTDAYOFNEXTMONTH.with(TemporalAdjusters.lastDayOfMonth());


    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getDateString() {
        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return format;
    }

    /**
     * 日期格式转换
     *
     * @param str
     * @param format
     * @return
     */
    public static Date transferToDate(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getFirstDayOfMonth() {
        return FIRSTDAYOFMONTH.toString();
    }


    public static String getFirstDayOfNextMonth() {
        return FIRSTDAYOFNEXTMONTH.toString();
    }


    public static String getLastDayOfMonth() {
        return LASTDAYOFMONTH.toString();
    }


    public static String getLastDayOfNextMonth() {
        return LASTDAYOFNEXTMONTH.toString();
    }


    public static String getOriginalDateNextDay(Date originalDate) {

        LocalDateTime now = LocalDateTime.ofInstant(originalDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime of = now.plusDays(1);
        return of.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


    public static String getOriginalDateNextDay(String originalDate) {
        Date orDate = transferToDate(originalDate, "yyyy-MM-dd");
        return getOriginalDateNextDay(orDate);
    }


    public static Long getDiffCurrentTime(String originDate) {

        LocalDateTime orgin = LocalDateTime.class.cast(LocalDateTime.parse(originDate.toString(), DateTimeFormatter.ofPattern(timePattern)));

        return Duration.between(orgin, LocalDateTime.now()).getSeconds();

    }


    public static Date plusSeconds2Date(Long seconds) {
        LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(seconds);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    public class TimeInterval {

        private long time;
        private boolean isNano;

        public TimeInterval() {
            this(false);
        }

        public TimeInterval(boolean isNano) {
            this.isNano = isNano;
//            start();
        }

        /**
         * @return 开始计时并返回当前时间
         */
//        public long start() {
//            time = DateUtils.current(isNano);
//            return time;
//        }

        /**
         * @return 返回开始时间
         */
        public long startTime() {
            return time;
        }

        /**
         * @return 重新计时并返回从开始到当前的持续时间
         */
//        public long intervalRestart() {
//            long now = DateUtils.current(isNano);
//            long d = now - time;
//            time = now;
//            return d;
//        }

        /**
         * 重新开始计算时间（重置开始时间）
         *
         * @return this
         * @since 3.0.1
         */
//        public TimeInterval restart() {
//            time = DateUtils.current(isNano);
//            return this;
//        }

        //----------------------------------------------------------- Interval

        /**
         * 从开始到当前的间隔时间（毫秒数）<br>
         * 如果使用纳秒计时，返回纳秒差，否则返回毫秒差
         *
         * @return 从开始到当前的间隔时间（毫秒数）
         */
//        public long interval() {
//            return DateUtils.current(isNano) - time;
//        }

        /**
         * 从开始到当前的间隔时间（毫秒数）
         *
         * @return 从开始到当前的间隔时间（毫秒数）
         */
//        public long intervalMs() {
//            return isNano ? interval() / 1000000L : interval();
//        }

        /**
         * 从开始到当前的间隔秒数，取绝对值
         *
         * @return 从开始到当前的间隔秒数，取绝对值
         */
//        public long intervalSecond() {
//            return intervalMs() / DateUnit.SECOND.getMillis();
//        }

        /**
         * 从开始到当前的间隔分钟数，取绝对值
         *
         * @return 从开始到当前的间隔分钟数，取绝对值
         */
//        public long intervalMinute() {
//            return intervalMs() / DateUnit.MINUTE.getMillis();
//        }

        /**
         * 从开始到当前的间隔小时数，取绝对值
         *
         * @return 从开始到当前的间隔小时数，取绝对值
         */
//        public long intervalHour() {
//            return intervalMs() / DateUnit.HOUR.getMillis();
//        }

        /**
         * 从开始到当前的间隔天数，取绝对值
         *
         * @return 从开始到当前的间隔天数，取绝对值
         */
//        public long intervalDay() {
//            return intervalMs() / DateUnit.DAY.getMillis();
//        }

        /**
         * 从开始到当前的间隔周数，取绝对值
         *
         * @return 从开始到当前的间隔周数，取绝对值
         */
//        public long intervalWeek() {
//            return intervalMs() / DateUnit.WEEK.getMillis();
//        }

    }
}

