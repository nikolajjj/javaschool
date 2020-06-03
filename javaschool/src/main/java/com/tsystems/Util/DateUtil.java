package com.tsystems.Util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date getCurrentMonthBegin() {
        return getCurrentMonthBeginOrEnd(false);
    }

    public static Date getCurrentMonthEnd() {
        return getCurrentMonthBeginOrEnd(true);
    }

    /**
     *
     * @param month - if month is false, then return current month begin
     *              otherwise return current month end
     * @return
     */
    public static Date getCurrentMonthBeginOrEnd(Boolean month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());  //today

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (month) {
            calendar.add(Calendar.MONTH, 1); //add one month
        }

        Date result = calendar.getTime();
        calendar.setTime(new Date());

        return result;
    }

    public static double diffInHours(Date begin, Date end) {
        long resultMills = end.getTime() - begin.getTime();
        double resultHours = (float) resultMills / 1000 / 60 / 60;

        return resultHours;
    }
}
