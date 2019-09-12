package com.example.ie_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.List;


public class Schedule extends AppCompatActivity
{
    private MaterialCalendarView calendar;
    private Spinner start_time_spinner, end_time_spinner;
    private List<String> startList;
    private List<String> newEndList;
    private ImageView yes_schedule;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        calendar = findViewById(R.id.calendarView);
        calendar.state().edit().setCalendarDisplayMode(CalendarMode.WEEKS).commit();
        start_time_spinner = findViewById(R.id.Start_time_spinner);
        end_time_spinner = findViewById(R.id.End_time_spinner);
        yes_schedule = findViewById(R.id.schedule_yes);
        textView = findViewById(R.id.select_free_time_text);
        startList = new ArrayList<>();
        newEndList = new ArrayList<>();

        init(startList);

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, startList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        start_time_spinner.setAdapter(adapter1);

        start_time_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String startTime = start_time_spinner.getItemAtPosition(position).toString();
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
                    final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, newEndList);
                    spinner2(adapter2, end_time_spinner);
                }
                else
                {
                    List<String> data = new ArrayList<>();
                    data.add("select");
                    final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, data);
                    spinner2(adapter2, end_time_spinner);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        yes_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Select Activity");



            }
        });

        final FoldingCell fc = (FoldingCell) findViewById(R.id.folding_cell);
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
            }
        });


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
