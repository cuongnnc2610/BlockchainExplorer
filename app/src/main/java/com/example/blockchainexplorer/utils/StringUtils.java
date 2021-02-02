package com.example.blockchainexplorer.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Locale;

public class StringUtils {

    public static String timestampInSecondToDateString(long timestamp){
        Date date = new Date(timestamp * 1000);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        return dateFormat.format(date);
    }

}
