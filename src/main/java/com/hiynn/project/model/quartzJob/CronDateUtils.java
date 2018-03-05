package com.hiynn.project.model.quartzJob;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * date与cron互相转换  
 * <p>Title: CronDateUtils </p>
 * <p>Description: TODO </p>
 * Date: 2017年8月28日 下午9:43:47
 * @author hydata@hiynn.com
 * @version 1.0 </p> 
 * Significant Modify：
 * Date               Author           Content
 * ==========================================================
 * 2017年8月28日         hydata         创建文件,实现基本功能
 * 
 * ==========================================================
 */
public class CronDateUtils {  
    private static final String CRON_DATE_FORMAT = "ss mm HH dd MM ? yyyy";  
  
    /*** 
     * 
     * @param date 时间 
     * @return  cron类型的日期 
     */  
    public static String getCron(final Date  date){  
        SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);  
        String formatTimeStr = "";  
        if (date != null) {  
            formatTimeStr = sdf.format(date);  
        }  
        return formatTimeStr;  
    }  
  
    /*** 
     * 
     * @param cron Quartz cron的类型的日期 
     * @return  Date日期 
     */  
  
    public static Date getDate(final String cron) {  
  
  
        if(cron == null) {  
            return null;  
        }  
  
        SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);  
        Date date = null;  
        try {  
            date = sdf.parse(cron);  
        } catch (ParseException e) {  
            return null;// 此处缺少异常处理,自己根据需要添加  
        }  
        return date;  
    }  
}  
