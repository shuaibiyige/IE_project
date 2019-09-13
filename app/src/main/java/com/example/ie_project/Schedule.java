package com.example.ie_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
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
    private MapView mapView1, mapView2;
    private FoldingCell fc1, fc2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoiY2h1YWliaSIsImEiOiJjamw2bDYxYm0wdGZ1M3duNWlnd3lqbWFuIn0.XaGfJkYUWnSO9LlBb5EPYw");
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
        fc1 = findViewById(R.id.folding_cell1);
        fc2 = findViewById(R.id.folding_cell2);
        mapView1 = findViewById(R.id.mapView1);
        mapView2 = findViewById(R.id.mapView2);

        final Animation ani2 = AnimationUtils.loadAnimation(this, R.anim.dashboard_image);


        init(startList);

        mapView1.onCreate(savedInstanceState);
        mapView2.onCreate(savedInstanceState);

        mapView1.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap)
            {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded()
                {
                    @Override
                    public void onStyleLoaded(@NonNull Style style)
                    {

                    // Map is set up and the style has loaded. Now you can add data or make other map adjustments


                    }
                });
                mapboxMap.getUiSettings().setAttributionEnabled(false);
                mapboxMap.getUiSettings().setLogoEnabled(false);
            }
        });

        mapView2.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap)
            {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded()
                {
                    @Override
                    public void onStyleLoaded(@NonNull Style style)
                    {

                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments


                    }
                });
                mapboxMap.getUiSettings().setAttributionEnabled(false);
                mapboxMap.getUiSettings().setLogoEnabled(false);
            }
        });

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
            public void onClick(View v)
            {
                textView.setText("Select Activity");

                if (fc1.getVisibility() == View.INVISIBLE)
                {
                    fc1.setVisibility(View.VISIBLE);
                    fc1.setAnimation(ani2);
                }
                fc1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        fc1.toggle(false);
                    }
                });

                if (fc2.getVisibility() == View.INVISIBLE)
                {
                    fc2.setVisibility(View.VISIBLE);
                    fc2.setAnimation(ani2);
                }
                fc2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        fc2.toggle(false);
                    }
                });


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
