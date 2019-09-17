package com.example.ie_project;

import android.content.ContentValues;
import android.provider.CalendarContract;

import java.util.TimeZone;

public class CalendarProviderManager
{
    public  static ContentValues setupEvent(long startTime, long endTime, String eventTitle, String eventDes,
                                   String eventLocation, ContentValues event)
    {
        // 事件开始时间
        event.put(CalendarContract.Events.DTSTART, startTime);
        // 事件结束时间
        event.put(CalendarContract.Events.DTEND, endTime);
        // 事件标题
        event.put(CalendarContract.Events.TITLE, eventTitle);
        // 事件描述(对应手机系统日历备注栏)
        event.put(CalendarContract.Events.DESCRIPTION, eventDes);
        // 事件地点
        event.put(CalendarContract.Events.EVENT_LOCATION, eventLocation);
        // 事件时区
        event.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());
        // 定义事件的显示，默认即可
        event.put(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_DEFAULT);
        // 事件的状态
        event.put(CalendarContract.Events.STATUS, 0);
        // 设置事件提醒警报可用
        event.put(CalendarContract.Events.HAS_ALARM, 1);
        // 设置事件忙
        event.put(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
        // 设置事件重复规则
        // event.put(CalendarContract.Events.RRULE, );

        return event;
    }
}
