package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;

public class Schedule extends Fragment
{
    private View schedule;
    private ImageButton submit;
    private MaterialCalendarView calendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        schedule = inflater.inflate(R.layout.fragment_schedule, container, false);
        calendar = (MaterialCalendarView) schedule.findViewById(R.id.calendarView);
        submit = (ImageButton) schedule.findViewById(R.id.schedule_finish);

        Spinner spinner_start = (Spinner) schedule.findViewById(R.id.Start_time_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.start_time, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_start.setAdapter(adapter1);

        Spinner spinner_end = (Spinner) schedule.findViewById(R.id.End_time_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.end_time, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_end.setAdapter(adapter2);

        Calendar setting = Calendar.getInstance();
        int year = setting.get(Calendar.YEAR);
        int month = setting.get(Calendar.MONTH)+1;
        int day = setting.get(Calendar.DAY_OF_MONTH);


        calendar.state().edit().setCalendarDisplayMode(CalendarMode.WEEKS).commit();

        return schedule;
    }
}
