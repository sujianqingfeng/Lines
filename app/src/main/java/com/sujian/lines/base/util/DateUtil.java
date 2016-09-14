package com.sujian.lines.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sujian on 2016/9/14.
 * Mail:121116111@qq.com
 */
public class DateUtil {

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return String.valueOf(Integer.valueOf(df.format(new Date())));
    }
}
