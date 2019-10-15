package com.example.ie_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
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
import com.kyle.calendarprovider.calendar.CalendarEvent;

import com.kyle.calendarprovider.calendar.CalendarProviderManager;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.ramotion.foldingcell.FoldingCell;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Schedule extends AppCompatActivity implements OnDateSelectedListener
{
    private MaterialCalendarView calendar;
    private Spinner start_time_spinner, end_time_spinner, innerSpinner1, innerSpinner2, typeSpinner, moneySpinner, travelSpinner;
    private List<String> startList, newEndList;
    private ImageView yes_schedule;
    private TextView textView;
    private FoldingCell fc1, fc2;
    private Button select1, select2, viewMap1, viewMap2, back;
    private Animation ani2;
    private int user_id, activity1_duration, activity2_duration, activity_id1, activity_id2;
    private String startTime, endTime, chosenDate, activity_type, activity_money, activity_travel;
    private RequestQueue requestQueue;
    private CalendarDay calendarDay;
    private TextView activity_title1_view, activity_name1_view, activity_description1_view, activity_address1_view, activity_title2_view, activity_name2_view, activity_description2_view, activity_address2_view;
    private String latitude1, longitude1, latitude2, longitude2, activity_title1, activity_name1, activity_description1, activity_address1, activity_title2, activity_name2, activity_description2, activity_address2;
    private String activity1_startTime, activity2_startTime;
    private CalendarEvent calendarEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // require the calendar permission from the user
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR, Manifest.permission.READ_CALENDAR}, 1);
        }

        calendar = findViewById(R.id.calendarView);
        start_time_spinner = findViewById(R.id.Start_time_spinner);
        end_time_spinner = findViewById(R.id.End_time_spinner);
        yes_schedule = findViewById(R.id.schedule_yes);
        select1 = findViewById(R.id.activity_button1);
        select2 = findViewById(R.id.activity_button2);
        viewMap1 = findViewById(R.id.activity1_viewMap);
        viewMap2 = findViewById(R.id.activity2_viewMap);
        textView = findViewById(R.id.select_free_time_text);
        back = findViewById(R.id.schedule_back);

        activity_title1_view = findViewById(R.id.activity1_title);
        activity_title2_view = findViewById(R.id.activity2_title);
        activity_name1_view = findViewById(R.id.activity1_name);
        activity_name2_view = findViewById(R.id.activity2_name);
        activity_description1_view = findViewById(R.id.activity1_description);
        activity_description2_view = findViewById(R.id.activity2_description);
        activity_address1_view = findViewById(R.id.activity1_address);
        activity_address2_view = findViewById(R.id.activity2_address);
        innerSpinner1 = findViewById(R.id.schedule_activity1_spinner);
        innerSpinner2 = findViewById(R.id.schedule_activity2_spinner);
        typeSpinner = findViewById(R.id.schedule_type);
        moneySpinner = findViewById(R.id.schedule_money);
        travelSpinner = findViewById(R.id.schedule_travel);

        startList = new ArrayList<>();
        newEndList = new ArrayList<>();
        fc1 = findViewById(R.id.folding_cell1);
        fc2 = findViewById(R.id.folding_cell2);

        ani2 = AnimationUtils.loadAnimation(this, R.anim.dashboard_image);
        user_id = 0;
        activity1_duration = 0;
        activity2_duration = 0;
        latitude1 = "";
        longitude1 = "";
        latitude2 = "";
        longitude2 = "";
        activity_title1 = "";
        activity_name1 = "";
        activity_description1 = "";
        activity_address1 = "";
        activity_title2 = "";
        activity_name2 = "";
        activity_description2 = "";
        activity_address2 = "";
        activity_id1 = 0;
        activity_id2 = 0;
        chosenDate = "";
        activity1_startTime = "select";
        activity2_startTime = "select";
        activity_type = "Type";
        activity_money = "Money";
        activity_travel = "Travel";

        init(startList);
        calendarInit();
        initNewSpinner();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Schedule.this, MainActivity.class);
                startActivity(intent);
            }
        });

        viewMap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (longitude1.equals("") || latitude1.equals(""))
                    Toast.makeText(getApplicationContext(), "Map is not available", Toast.LENGTH_SHORT).show();
                else
                {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + latitude1 + "," + longitude1 + "(" + activity_name1 +")");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");              // ensure that the Google Maps app for Android handles the Intent
                    if (mapIntent.resolveActivity(getPackageManager()) != null)
                        startActivity(mapIntent);
                    else
                        Toast.makeText(getApplicationContext(), "Map is not available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewMap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (longitude2.equals("") || latitude2.equals(""))
                    Toast.makeText(getApplicationContext(), "Map is not available", Toast.LENGTH_SHORT).show();
                else {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + latitude2 + "," + longitude2 + "(" + activity_name2 +")");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");              // ensure that the Google Maps app for Android handles the Intent
                    if (mapIntent.resolveActivity(getPackageManager()) != null)
                        startActivity(mapIntent);
                    else
                        Toast.makeText(getApplicationContext(), "Map is not available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        innerSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                activity1_startTime = innerSpinner1.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        innerSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                activity2_startTime = innerSpinner2.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        select1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (!activity1_startTime.equals("select"))
                {
                    int cal = Integer.valueOf(activity1_startTime.split(":")[0]) + activity1_duration;
                    String activity_endTime = cal + ":00";
                    String[] time_cal = chosenDate.split("-");


                    if(activity1_startTime.equals(activity2_startTime) )
                        Toast.makeText(getApplicationContext(), "Please choose another start time", Toast.LENGTH_SHORT).show();
                    else
                        alertDialog(chosenDate, activity1_startTime + "-" + activity_endTime, activity_title1, activity_description1, activity_address1, Integer.valueOf(time_cal[0]), Integer.valueOf(time_cal[1]), Integer.valueOf(time_cal[2]), Integer.valueOf(activity1_startTime.split(":")[0]), cal, activity_id1);
                }
                else
                    Toast.makeText(getApplicationContext(), "Please choose the start time", Toast.LENGTH_SHORT).show();
            }
        });

        select2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (!activity2_startTime.equals("select"))
                {
                    int cal = Integer.valueOf(activity2_startTime.split(":")[0]) + activity2_duration;
                    String activity_endTime = cal + ":00";
                    String[] time_cal = chosenDate.split("-");

                    if(activity1_startTime.equals(activity2_startTime) )
                        Toast.makeText(getApplicationContext(), "Please choose another start time", Toast.LENGTH_SHORT).show();
                    else
                        alertDialog(chosenDate, activity2_startTime + "-" + activity_endTime, activity_title2, activity_description2, activity_address2, Integer.valueOf(time_cal[0]), Integer.valueOf(time_cal[1]), Integer.valueOf(time_cal[2]), Integer.valueOf(activity2_startTime.split(":")[0]), cal, activity_id2);
                }
                else
                    Toast.makeText(getApplicationContext(), "Please choose the start time", Toast.LENGTH_SHORT).show();
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

        yes_schedule.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!startTime.equals("select") && !endTime.equals("select") && !chosenDate.equals("") && !activity_type.equals("Type") && !activity_money.equals("Money") && !activity_travel.equals("Travel"))
                {
                    textView.setText("Select Activity");

                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                    user_id = sharedPreferences.getInt("user_id", 0);

                    String[] startArray = startTime.split(":");
                    String[] endArray = endTime.split(":");
                    int duration = Integer.valueOf(endArray[0]) - Integer.valueOf(startArray[0]);

                    volley(user_id, duration, activity_money, activity_travel, activity_type);
                }
                else if (chosenDate.equals(""))
                    Toast.makeText(getApplicationContext(), "Please choose a date", Toast.LENGTH_SHORT).show();
                else if (startTime.equals("select") || endTime.equals("select"))
                    Toast.makeText(getApplicationContext(), "Please choose time", Toast.LENGTH_SHORT).show();
                else if (activity_type.equals("Type"))
                    Toast.makeText(getApplicationContext(), "Enter type preference", Toast.LENGTH_SHORT).show();
                else if (activity_money.equals("Money"))
                    Toast.makeText(getApplicationContext(), "Enter spending preference", Toast.LENGTH_SHORT).show();
                else if (activity_travel.equals("Travel"))
                    Toast.makeText(getApplicationContext(), "Enter travel preference", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Some preferences are empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(Schedule.this, MainActivity.class);
        startActivity(intent);
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

    private void innerSpinner(Spinner spinner, int inner_startTime, int duration)
    {
        List<String> list = new ArrayList<>();
        list.add("select");
        list.add(inner_startTime + ":00");
        for (int i = 1; i <= duration; i++)
        {
            int inner_endTime = inner_startTime + i;
            list.add(inner_endTime + ":00");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void initNewSpinner()
    {
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                activity_type = typeSpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        moneySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                activity_money = moneySpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        travelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                activity_travel = travelSpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void alertDialog(final String date_text, final String time_text, final String activity_text, final String activity_des, final String activity_add, final int year, final int month, final int day, final int startHour, final int endHour, final int activity_id)
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
        TextView date = dialogView.findViewById(R.id.dialog_date);
        TextView time = dialogView.findViewById(R.id.dialog_time);
        TextView activity = dialogView.findViewById(R.id.dialog_activity);

        date.setText(date_text);
        time.setText(time_text);
        activity.setText(activity_text);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(year, month - 1, day, startHour, 0);
                long startMillis = beginTime.getTimeInMillis();
                Calendar endTime = Calendar.getInstance();
                endTime.set(year, month - 1 , day, endHour, 0);
                long endMillis = endTime.getTimeInMillis();

                calendarEvent = new CalendarEvent(activity_text, activity_des, activity_add, startMillis, endMillis, 0, null);          // add the event to the calendar
                transmitActivity(String.valueOf(activity_id), date_text, time_text, dialog);
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
        int month = date.getMonth() + 1;
        int day = date.getDay();
        String dayFormat = "";
        String monthFormat = "";
        if (day < 10)
            dayFormat = "0" + day;
        else
            dayFormat = "" + day;
        if (month < 10)
            monthFormat = "0" + month;
        else
            monthFormat = "" + month;

        calendarDay = date;
        chosenDate = year + "-" + monthFormat + "-" + dayFormat;
    }

    public void volley(final int user_id, final int duration, final String moneyData, final String travelData, final String typeData)
    {
        String connectUrl = "http://ec2-13-236-44-7.ap-southeast-2.compute.amazonaws.com/letosaid/activityAdvice.php";

        com.android.volley.Response.Listener<String> listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String s)
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(s);
                    //retCode = jsonObject.getInt("success");
                    activity_id1 = jsonObject.getInt("recom_id1");
                    activity_title1 = jsonObject.getString("title1");
                    activity_name1 = jsonObject.getString("activity_name1");
                    activity_description1 = jsonObject.getString("activity_description1");
                    activity_address1 = jsonObject.getString("activity_address1");
                    latitude1 =  jsonObject.getString("latitude1");
                    longitude1 = jsonObject.getString("longitude1");
                    activity1_duration = jsonObject.getInt("duration1");

                    activity_id2 = jsonObject.getInt("recom_id2");
                    activity_title2 = jsonObject.getString("title2");
                    activity_name2 = jsonObject.getString("activity_name2");
                    activity_description2 = jsonObject.getString("activity_description2");
                    activity_address2 = jsonObject.getString("activity_address2");
                    latitude2 =  jsonObject.getString("latitude2");
                    longitude2 = jsonObject.getString("longitude2");
                    activity2_duration = jsonObject.getInt("duration2");

                    if (!activity_name1.equals("") && !activity_name2.equals(""))
                    {
                        activity_title1_view.setText(activity_title1);
                        activity_name1_view.setText(activity_name1);
                        activity_description1_view.setText(activity_description1);

                        activity_title2_view.setText(activity_title2);
                        activity_name2_view.setText(activity_name2);
                        activity_description2_view.setText(activity_description2);

                        if (activity_address1.equals(""))
                            activity_address1_view.setText("At home" + " (" + activity1_duration + " hour)");
                        else
                            activity_address1_view.setText(activity_address1);

                        if (activity_address2.equals(""))
                            activity_address2_view.setText("At home" + " (" + activity2_duration + " hour)");
                        else
                            activity_address2_view.setText(activity_address2);

                        innerSpinner(innerSpinner1, Integer.valueOf(startTime.split(":")[0]), duration);
                        innerSpinner(innerSpinner2, Integer.valueOf(startTime.split(":")[0]), duration);

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
                    {
                        Toast.makeText(getApplicationContext(),"No activities found", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (JSONException e)
                {
                    Toast.makeText(getApplicationContext(),"Network is unavailable", Toast.LENGTH_SHORT).show();
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, connectUrl, listener, errorListener)
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> map = new HashMap<>();
                map.put("user_id", String.valueOf(user_id));
                map.put("duration", String.valueOf(duration));
                map.put("type", typeData);
                map.put("money", moneyData);
                map.put("travel", travelData);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void transmitActivity(final String transmit_id, final String transmit_date, String transmit_time, final AlertDialog dialog)
    {
        String connectUrl = "http://ec2-13-236-44-7.ap-southeast-2.compute.amazonaws.com/letosaid/addSchedule.php";

        String[] time_list = transmit_time.split("-");
        final String transmit_start = time_list[0];
        final String transmit_end = time_list[1];

        com.android.volley.Response.Listener<String> listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String s)
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(s);
                    int retCode = jsonObject.getInt("success");

                    if (retCode != 0)
                    {
                        int result = CalendarProviderManager.addCalendarEvent(getApplicationContext(), calendarEvent);
                        if (result == 0)
                        {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "successful", Toast.LENGTH_SHORT).show();
                        }
                        else if (result == -1)
                        {
                            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                        }
                        else if (result == -2)
                        {
                            Toast.makeText(getApplicationContext(), "permission denied", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Please choose another time", Toast.LENGTH_SHORT).show();
                }
                catch (JSONException e)
                {
                    Toast.makeText(getApplicationContext(),"Network is unavailable", Toast.LENGTH_SHORT).show();
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, connectUrl, listener, errorListener)
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> map = new HashMap<>();
                map.put("user_id", String.valueOf(user_id));
                map.put("schedule_date", transmit_date);
                map.put("start_time", transmit_start);
                map.put("end_time", transmit_end);
                map.put("re_id", transmit_id);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }


    public void calendarInit()
    {
        Calendar calendarRange = Calendar.getInstance();
        int yearRange = calendarRange.get(Calendar.YEAR);
        int endYearRange = yearRange;
        int monthRange = calendarRange.get(Calendar.MONTH);                  // start from 0
        int endMonthRange = monthRange;
        int startDayRange = calendarRange.get(Calendar.DAY_OF_MONTH);
        int endDayRange = startDayRange;
        int maxDay = calendarRange.getActualMaximum(Calendar.DAY_OF_MONTH);

        if (startDayRange + 6 > maxDay)
        {
            endMonthRange++;
            endDayRange = startDayRange + 7 - maxDay;
        }
        else
            endDayRange = startDayRange + 7;
        if (endMonthRange > 11)
            endYearRange++;

        calendar.state().edit().setCalendarDisplayMode(CalendarMode.WEEKS)
                .setMinimumDate(CalendarDay.from(yearRange, monthRange, startDayRange))
                .setMaximumDate(CalendarDay.from(endYearRange, endMonthRange, endDayRange))
                .commit();
        calendar.setOnDateChangedListener(this);
    }
}
