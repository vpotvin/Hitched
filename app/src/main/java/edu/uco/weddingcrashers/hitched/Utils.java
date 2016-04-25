package edu.uco.weddingcrashers.hitched;

import android.content.Context;
import android.util.TypedValue;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 作者：Created by rush_yu on 2016/3/1 16:57
 * 邮箱：yu.hacker.rush@gmail.com
 * version 1.0
 */
public class Utils {

    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
               context.getResources().getDisplayMetrics());
    }
    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        String currentTime = sdf.format(date);
        return currentTime;

    }

    public static String getCurrentTime() {
        return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
    }
}
