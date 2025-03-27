package com.mine.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * created by xulp
 * on 2021/11/19
 */
public class Utils {

    //获取格式化后的毫秒级时间
    public static String getFormatTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        String currentSimpleDateFormat = simpleDateFormat.format(date);
        return currentSimpleDateFormat;
    }
}
