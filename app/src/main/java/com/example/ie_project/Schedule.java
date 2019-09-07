package com.example.ie_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;


public class Schedule extends AppCompatActivity
{
    private MaterialCalendarView calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        calendar = (MaterialCalendarView) findViewById(R.id.calendarView);
        calendar.state().edit().setCalendarDisplayMode(CalendarMode.WEEKS).commit();

    }
}
