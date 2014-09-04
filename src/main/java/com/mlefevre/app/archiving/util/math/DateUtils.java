package com.mlefevre.app.archiving.util.math;

import java.util.Date;

public class DateUtils {

    public static long timeStampDiff(Date date1, Date date2) {
        long timeStamp = date1.getTime() - date2.getTime();

        return timeStamp;
    }


    public static int getSeconds(long timeStamp) {
        int seconds = (int) (timeStamp / 1000);

        return seconds;
    }

    public static int getMinutes(long timeStamp) {
        int minutes = (int) (timeStamp / (1000 * 60));

        return minutes;
    }

    public static int getHours(long timeStamp) {
        int hours = (int) (timeStamp / (1000 * 60 * 60));

        return hours;
    }


}
