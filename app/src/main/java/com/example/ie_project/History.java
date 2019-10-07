package com.example.ie_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class History extends AppCompatActivity implements View.OnClickListener
{
    private List<HistoryList> mData;
    private ListView listView;
    private PieChart mChart;
    private Button start_button, end_button, all_button, back;
    private Calendar calendar;
    private String time, start_time, end_time;
    private ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
    private RequestQueue requestQueue;
    private List<HistoryList> doneList, notDoneList;
    private int user_id, done_percentage, notDone_percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().hide();

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        listView = findViewById(R.id.listView);
        mChart = findViewById(R.id.pieChart);
        start_button = findViewById(R.id.history_start);
        end_button = findViewById(R.id.history_end);
        all_button = findViewById(R.id.history_all);
        back = findViewById(R.id.history_back);

        initData();

        start_button.setOnClickListener(this);
        end_button.setOnClickListener(this);
        all_button.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void initData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        user_id = sharedPreferences.getInt("user_id", 0);
        done_percentage = 0;
        notDone_percentage = 0;
        doneList = new ArrayList<HistoryList>();
        notDoneList = new ArrayList<HistoryList>();
        mData = new ArrayList<>();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.history_start:
                datePicker(1);
                break;
            case R.id.history_end:
                datePicker(2);
                break;
            case R.id.history_all:
                getData("null", "null", "1");
                break;
            case R.id.history_back:
                Intent anotherIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(anotherIntent);
                break;
            default:
                break;
        }
    }

    private void datePicker(final int input)
    {
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                String newMonth = "";
                String newDay = "";
                int rightMonth = monthOfYear + 1;
                if (rightMonth < 10)
                    newMonth = "0" + rightMonth;
                else
                    newMonth = rightMonth + "";
                if (dayOfMonth < 10)
                    newDay = "0" + dayOfMonth;
                else
                    newDay = dayOfMonth + "";
                time = year + "-" + newMonth + "-" + newDay;
                if (input == 1)
                {
                    start_button.setText(time);
                    //start_time = time;
                }
                else
                {
                    end_button.setText(time);
                    //end_time = time;
                }
                try
                {
//                    if (!compareDate(start_button.getText().toString(), end_button.getText().toString()))
//                    {
//                        Toast.makeText(getApplicationContext(), "invalid time interval", Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
                    if (!start_button.getText().toString().equals("start date") && !end_button.getText().toString().equals("end date"))
                        getData(start_button.getText().toString(), end_button.getText().toString(), "0");
                    //}
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "unknown error", Toast.LENGTH_SHORT).show();
                }
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private boolean compareDate(String time1, String time2) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date a = sdf.parse(time1);
        Date b = sdf.parse(time2);
        if(a.before(b))
            return true;
        else
            return false;
    }

    private void pieChart(int done, int notDone)
    {
        entries.clear();

        entries.add(new PieEntry(1, "Done"));
        entries.add(new PieEntry(2, "Not Done"));

        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 5, 5, 5);
        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);
        mChart.setHoleRadius(50f);
        mChart.setTransparentCircleRadius(31f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        Legend l = mChart.getLegend();
        l.setEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);

        mChart.setEntryLabelColor(Color.BLACK);
        mChart.setEntryLabelTextSize(17f);

        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        //data.setValueTextSize(17f);
        //data.setValueTextColor(Color.BLACK);
        mChart.setData(data);

        mChart.highlightValues(null);

        mChart.invalidate();
    }

    private void getData(final String start, final String end, final String status)
    {
        String connectUrl = "http://ec2-13-236-44-7.ap-southeast-2.compute.amazonaws.com/letosaid/history.php";

        com.android.volley.Response.Listener<String> listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String s)
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(s);
                    int retCode = jsonObject.getInt("success");
                    doneList.clear();
                    notDoneList.clear();

                    if (retCode == 1)
                    {
                        JSONArray done_activity = jsonObject.getJSONArray("done");
                        JSONArray notDone_activity = jsonObject.getJSONArray("not_done");

                        for (int i = 0; i < done_activity.length(); i++)
                        {
                            JSONObject object = done_activity.getJSONObject(i);
                            String done_rating = object.getString("activity_rating");
                            String done_date = object.getString("schedule_date");
                            String done_title = object.getString("title");
                            HistoryList historyList = new HistoryList(done_date, done_title, Integer.valueOf(done_rating), Color.parseColor("#ffa0eb9a"));
                            doneList.add(historyList);
                        }

                        for (int i = 0; i < notDone_activity.length(); i++)
                        {
                            JSONObject object = notDone_activity.getJSONObject(i);
                            String notDone_date = object.getString("schedule_date");
                            String notDone_title = object.getString("title");
                            HistoryList historyList = new HistoryList(notDone_date, notDone_title, 100, Color.parseColor("#FFF78C"));
                            notDoneList.add(historyList);
                        }

                        pieChart(doneList.size(), notDoneList.size());

                        for (HistoryList ele1: doneList)
                        {
                            mData.add(ele1);
                        }
                        for (HistoryList ele2: notDoneList)
                        {
                            mData.add(ele2);
                        }

                        LayoutInflater inflater = getLayoutInflater();
                        HistoryAdapter historyAdapter = new HistoryAdapter(inflater, mData);
                        listView.setAdapter(historyAdapter);
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
                map.put("start_date", start);
                map.put("end_date", end);
                map.put("is_all", status);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
