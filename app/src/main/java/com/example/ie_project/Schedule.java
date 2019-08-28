package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.List;

public class Schedule extends Fragment
{
    private View schedule;
    private ImageButton submit;
    private ImageView food;
    private ImageView sport;
    private MaterialCalendarView calendar;
    private List<String> startList;
    private List<String> newEndList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        schedule = inflater.inflate(R.layout.fragment_schedule, container, false);
        calendar = (MaterialCalendarView) schedule.findViewById(R.id.calendarView);
        submit = (ImageButton) schedule.findViewById(R.id.schedule_finish);
        food = (ImageView) schedule.findViewById(R.id.activity_image_food);
        sport = (ImageView) schedule.findViewById(R.id.activity_image_sport);
        startList = new ArrayList<>();
        newEndList = new ArrayList<>();

        init(startList);

        final Spinner spinner_start = (Spinner) schedule.findViewById(R.id.Start_time_spinner);
        final Spinner spinner_end = (Spinner) schedule.findViewById(R.id.End_time_spinner);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, startList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_start.setAdapter(adapter1);

        food.setVisibility(View.INVISIBLE);
        sport.setVisibility(View.INVISIBLE);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                food.setVisibility(View.VISIBLE);
                sport.setVisibility(View.VISIBLE);
            }
        });

        activity(food);
        activity(sport);

        calendar.state().edit().setCalendarDisplayMode(CalendarMode.WEEKS).commit();

        spinner_start.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String startTime = spinner_start.getItemAtPosition(position).toString();
                newEndList.clear();
                init(newEndList);
                int i = 0;
                while(i < position + 1)
                {
                    newEndList.remove(0);
                    i++;
                }
                if (!startTime.equals("select"))
                {
                    final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, newEndList);
                    spinner2(adapter2, spinner_end);
                }
                else
                {
                    List<String> data = new ArrayList<>();
                    data.add("select");
                    final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, data);
                    spinner2(adapter2, spinner_end);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return schedule;
    }

    public void spinner2(ArrayAdapter<String> adapter, final Spinner spinner)
    {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String endTime = spinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void activity(ImageView imageView)
    {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Coming in the iteration 2!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void init(List<String> list)
    {
        list.add("select");
        list.add("10 am");
        list.add("11 am");
        list.add("12 pm");
        list.add("1 pm");
        list.add("2 pm");
        list.add("3 pm");
        list.add("4 pm");
        list.add("5 pm");
        list.add("6 pm");
        list.add("7 pm");
        list.add("8 pm");
        list.add("9 pm");
        list.add("10 pm");
    }
}
