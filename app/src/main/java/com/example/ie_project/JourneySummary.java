package com.example.ie_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JourneySummary extends AppCompatActivity
{
    private RadarChart chart;
    private int confirm1, confirm2;
    private RequestQueue requestQueue;
    private ArrayList<RadarEntry> pre = new ArrayList<>();
    private ArrayList<RadarEntry> cur = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journey_summary);
        getSupportActionBar().hide();

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        chart = findViewById(R.id.radarChart);
        init();
        //chart.setBackgroundColor(Color.rgb(60, 65, 82));

        chart.getDescription().setEnabled(false);

        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.LTGRAY);
        chart.setWebLineWidthInner(1f);
        chart.setWebColorInner(Color.LTGRAY);
        chart.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
//        MarkerView mv = new RadarMarkerView(this, R.layout.radar_markerview);
//        mv.setChartView(chart); // For bounds control
//        chart.setMarker(mv); // Set the marker to the chart


        chart.animateXY(1400, 1400, Easing.EaseInOutQuad);

        XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(9f);     // X坐标值字体大小

        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new ValueFormatter() {
            private final String[] mActivities = new String[]{"Identification", "Rejection", "Autonomy", "Cohesion", "Conflict"};

            @Override
            public String getFormattedValue(float value)
            {
                return mActivities[(int) value % mActivities.length];
            }
        });
        //xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = chart.getYAxis();
        yAxis.setLabelCount(9, false);    // Y坐标值标签个数
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(10f);
        yAxis.setDrawLabels(false);

        Legend l = chart.getLegend();
        //l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);    // 图例X间距
        l.setYEntrySpace(5f);    // 图例Y间距
        l.setTextSize(10f);
        //l.setTextColor(Color.WHITE);
    }

    private void setData(ArrayList<RadarEntry> previous, ArrayList<RadarEntry> current)
    {
        RadarDataSet set1 = new RadarDataSet(previous, "Previous");

        set1.setColor(Color.rgb(103, 110, 129));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarDataSet set2 = new RadarDataSet(current, "Current");

        set2.setColor(Color.rgb(121, 162, 175));
        set2.setFillColor(Color.rgb(121, 162, 175));
        set2.setDrawFilled(true);
        set2.setFillAlpha(180);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        chart.setData(data);
        chart.invalidate();
    }

    private void init()
    {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("user", Context.MODE_PRIVATE);
        int user_id = sharedPreferences.getInt("user_id", 0);

        confirm1 = 0;
        confirm2 = 0;

        readDataFromDbCurrent(user_id);
        readDataFromDbPrevious(user_id);
        //if (confirm1 == 1 && confirm2 == 1)
            //setData(pre, cur);
    }

    private void readDataFromDbCurrent(final int id)
    {
        String connectUrl = "http://ec2-13-236-44-7.ap-southeast-2.compute.amazonaws.com/letosaid/getNowSurveyScore.php";

        com.android.volley.Response.Listener<String> listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String s)
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(s);
                    int retCode = jsonObject.getInt("success");

                    if (retCode == 1)
                    {
                        cur.clear();
                        int identification = jsonObject.getInt("identification");
                        int rejection = jsonObject.getInt("rejection");
                        int autonomy = jsonObject.getInt("autonomy");
                        int cohesion = jsonObject.getInt("cohesion");
                        int conflict = jsonObject.getInt("conflict");

                        cur.add(new RadarEntry(identification));
                        cur.add(new RadarEntry(rejection));
                        cur.add(new RadarEntry(autonomy));
                        cur.add(new RadarEntry(cohesion));
                        cur.add(new RadarEntry(conflict));

                        readDataFromDbPrevious(id);
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
                map.put("user_id", String.valueOf(id));

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void readDataFromDbPrevious(final int id)
    {
        String connectUrl = "http://ec2-13-236-44-7.ap-southeast-2.compute.amazonaws.com/letosaid/getPreSurveyScore.php";

        com.android.volley.Response.Listener<String> listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String s)
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(s);
                    int retCode = jsonObject.getInt("success");

                    if (retCode == 1)
                    {
                        pre.clear();
                        int identification = jsonObject.getInt("identification");
                        int rejection = jsonObject.getInt("rejection");
                        int autonomy = jsonObject.getInt("autonomy");
                        int cohesion = jsonObject.getInt("cohesion");
                        int conflict = jsonObject.getInt("conflict");

                        pre.add(new RadarEntry(identification));
                        pre.add(new RadarEntry(rejection));
                        pre.add(new RadarEntry(autonomy));
                        pre.add(new RadarEntry(cohesion));
                        pre.add(new RadarEntry(conflict));

                        setData(pre, cur);
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
                map.put("user_id", String.valueOf(id));

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
