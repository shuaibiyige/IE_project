package com.example.ie_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.ramotion.foldingcell.FoldingCell;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Schedule extends AppCompatActivity implements OnDateSelectedListener
{
    private MaterialCalendarView calendar;
    private Spinner start_time_spinner, end_time_spinner;
    private List<String> startList;
    private List<String> newEndList;
    private ImageView yes_schedule;
    private TextView textView;
    //private MapView mapView1, mapView2;
    private FoldingCell fc1, fc2;
    private Button select1, select2, viewMap1, viewMap2;
    private LatLng latLng;
    private Animation ani2;
    private int duration;
    private String startTime, endTime;
    private RequestQueue requestQueue;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoiY2h1YWliaSIsImEiOiJjamw2bDYxYm0wdGZ1M3duNWlnd3lqbWFuIn0.XaGfJkYUWnSO9LlBb5EPYw");
        setContentView(R.layout.activity_schedule);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        calendar = findViewById(R.id.calendarView);
        start_time_spinner = findViewById(R.id.Start_time_spinner);
        end_time_spinner = findViewById(R.id.End_time_spinner);
        yes_schedule = findViewById(R.id.schedule_yes);
        select1 = findViewById(R.id.activity_button1);
        select2 = findViewById(R.id.activity_button2);
        viewMap1 = findViewById(R.id.activity1_viewMap);
        viewMap2 = findViewById(R.id.activity2_viewMap);
        textView = findViewById(R.id.select_free_time_text);
        startList = new ArrayList<>();
        newEndList = new ArrayList<>();
        fc1 = findViewById(R.id.folding_cell1);
        fc2 = findViewById(R.id.folding_cell2);
        //mapView1 = findViewById(R.id.mapView1);
        //mapView2 = findViewById(R.id.mapView2);
        latLng = new LatLng(-37.814, 144.96332);            // focus on Melbourne by default
        ani2 = AnimationUtils.loadAnimation(this, R.anim.dashboard_image);
        user_id = 0;
        duration = 0;

        init(startList);

        //mapView1.onCreate(savedInstanceState);
        //mapView2.onCreate(savedInstanceState);

        calendar.state().edit().setCalendarDisplayMode(CalendarMode.WEEKS).commit();
        calendar.setOnDateChangedListener(this);

//        mapView1.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(@NonNull MapboxMap mapboxMap)
//            {
//                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded()
//                {
//                    @Override
//                    public void onStyleLoaded(@NonNull Style style)
//                    {
//
//                    // Map is set up and the style has loaded. Now you can add data or make other map adjustments
//
//
//                    }
//                });
//                mapboxMap.getUiSettings().setAttributionEnabled(false);
//                mapboxMap.getUiSettings().setLogoEnabled(false);
//
//                CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latLng.getLatitude(), latLng.getLongitude())).zoom(10).build();
//                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//            }
//        });

//        mapView2.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(@NonNull MapboxMap mapboxMap)
//            {
//                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded()
//                {
//                    @Override
//                    public void onStyleLoaded(@NonNull Style style)
//                    {
//
//                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments
//
//
//                    }
//                });
//                mapboxMap.getUiSettings().setAttributionEnabled(false);
//                mapboxMap.getUiSettings().setLogoEnabled(false);
//
//                CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latLng.getLatitude(), latLng.getLongitude())).zoom(10).build();
//                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//            }
//        });

        viewMap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Uri gmmIntentUri = Uri.parse("geo:-37.814,144.96332");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");          // ensure that the Google Maps app for Android handles the Intent
                if (mapIntent.resolveActivity(getPackageManager()) != null)
                    startActivity(mapIntent);
                else
                    Toast.makeText(getApplicationContext(), "Map is not available", Toast.LENGTH_SHORT).show();
            }
        });

        viewMap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Uri gmmIntentUri = Uri.parse("geo:-37.814,144.96332");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");          // ensure that the Google Maps app for Android handles the Intent
                if (mapIntent.resolveActivity(getPackageManager()) != null)
                    startActivity(mapIntent);
                else
                    Toast.makeText(getApplicationContext(), "Map is not available", Toast.LENGTH_SHORT).show();
            }
        });

        select1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog("date1", "time1", "activity1");
            }
        });

        select2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog("date2", "time2", "activity2");
            }
        });

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, startList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        start_time_spinner.setAdapter(adapter1);

        start_time_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                startTime = start_time_spinner.getItemAtPosition(position).toString();
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
                if (!startTime.equals("select") && !endTime.equals("select")) {
                    textView.setText("Select Activity");

                    if (fc1.getVisibility() == View.INVISIBLE) {
                        fc1.setVisibility(View.VISIBLE);
                        fc1.setAnimation(ani2);
                    }
                    fc1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            fc1.toggle(false);
                        }
                    });

                    if (fc2.getVisibility() == View.INVISIBLE) {
                        fc2.setVisibility(View.VISIBLE);
                        fc2.setAnimation(ani2);
                    }
                    fc2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            fc2.toggle(false);
                        }
                    });
                }
                else
                    Toast.makeText(getApplicationContext(), "Please choose time", Toast.LENGTH_SHORT).show();
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
                endTime = spinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void init(List<String> list)
    {
        list.add("select");
        list.add("10:00");
        list.add("11:00");
        list.add("12:00");
        list.add("13:00");
        list.add("14:00");
        list.add("15:00");
        list.add("16:00");
        list.add("17:00");
        list.add("18:00");
        list.add("19:00");
        list.add("20:00");
        list.add("21:00");
        list.add("22:00");
    }

    public void alertDialog(String date_text, String time_text, String activity_text)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(this, R.layout.activity_dialog, null);
        dialog.setView(dialogView);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());        //erase white background
        dialog.show();

        Button yes = dialogView.findViewById(R.id.dialog_confirm);
        Button no = dialogView.findViewById(R.id.dialog_cancel);
        TextView date = (TextView) dialogView.findViewById(R.id.dialog_date);
        TextView time = (TextView) dialogView.findViewById(R.id.dialog_time);
        TextView activity = (TextView) dialogView.findViewById(R.id.dialog_activity);

        date.setText(date_text);
        time.setText(time_text);
        activity.setText(activity_text);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                user_id = sharedPreferences.getInt("user_id", 0);

                String[] startArray = startTime.split(":");
                String[] endArray = endTime.split(":");
                duration = Integer.valueOf(endArray[0]) -  Integer.valueOf(startArray[0]);

                //int[] dataTransmission = new int[2];
                //dataTransmission[0] = user_id;
                //dataTransmission[1] = duration;


                ScheduleRestAsyncTask scheduleRestAsyncTask = new ScheduleRestAsyncTask();
                scheduleRestAsyncTask.execute(user_id, duration);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected)
    {
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        Date a = date.getDate();
        Toast.makeText(this, String.valueOf(date.getDay()), Toast.LENGTH_SHORT).show();
    }

    private class ScheduleRestAsyncTask extends AsyncTask<Integer, Void, Void>      // check if user answered the questionnaire before
    {
        @Override
        protected Void doInBackground (final Integer...params)
        {
            String connectUrl = "http://ec2-13-236-44-7.ap-southeast-2.compute.amazonaws.com/letosaid/activityAdvice.php";

            com.android.volley.Response.Listener<String> listener = new Response.Listener<String>()
            {
                @Override
                public void onResponse(String s)
                {
                    int retCode = 0;
                    try
                    {
                        JSONObject jsonObject = new JSONObject(s);
                        retCode = jsonObject.getInt("success");
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
            };

            com.android.volley.Response.ErrorListener errorListener = new com.android.volley.Response.ErrorListener() {
                public String TAG = "LOG";
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, error.getMessage(), error);
                }
            };

            StringRequest stringRequest = new StringRequest(Request.Method.POST, connectUrl, listener, errorListener) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    Map<String, String> map = new HashMap<>();
                    map.put("user_id", String.valueOf(params[0]));
                    map.put("duration", String.valueOf(params[1]));

                    return map;
                }
            };
            requestQueue.add(stringRequest);
            return null;
        }
    }
}
