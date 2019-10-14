package com.example.ie_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
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
    private int user_id;

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
        all_button = findViewById(R.id.history_search);
        back = findViewById(R.id.history_back);

        initData();

        start_button.setOnClickListener(this);
        end_button.setOnClickListener(this);
        all_button.setOnClickListener(this);
        back.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                alertDialog(mData.get(position));
            }
        });
    }

    private void initData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        user_id = sharedPreferences.getInt("user_id", 0);
        doneList = new ArrayList<HistoryList>();
        notDoneList = new ArrayList<HistoryList>();
        mData = new ArrayList<>();
        getData("null", "null", "1");          // get all data
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
            case R.id.history_search:
                if (!start_button.getText().toString().equals("Start date") && !end_button.getText().toString().equals("End date"))
                    getData(start_button.getText().toString(), end_button.getText().toString(), "0");
                else
                    Toast.makeText(getApplicationContext(),"Choose time interval first", Toast.LENGTH_SHORT).show();
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

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
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
                    start_time = time;
                    end_button.setText("End date");
                }
                else
                {
                    end_button.setText(time);
                    end_time = time;
                }
            }
        }, year, month, day);
        if (input == 2 && !start_button.getText().toString().equals("Start date"))
        {
            calendar.set(Integer.valueOf(start_time.split("-")[0]), Integer.valueOf(start_time.split("-")[1]) - 1, Integer.valueOf(start_time.split("-")[2]));
            datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
            datePickerDialog.show();
        }
        else if (input == 2 && start_button.getText().toString().equals("Start date"))
        {
            Toast.makeText(getApplicationContext(),"Choose start date first", Toast.LENGTH_SHORT).show();
        }
        else if (input == 1)
            datePickerDialog.show();
    }

    public void alertDialog(HistoryList historyList)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(this, R.layout.history_dialog, null);
        dialog.setView(dialogView);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());        //erase white background
        dialog.show();

        Button yes_dialog = dialogView.findViewById(R.id.history_dialog_confirm);
        RatingBar dialog_ratingBar = dialogView.findViewById(R.id.feedback_star);
        TextView description_dialog = dialogView.findViewById(R.id.history_dialog_description);
        TextView dialog_title = dialogView.findViewById(R.id.history_dialog_title);

        dialog_title.setText(historyList.getName());
        description_dialog.setText(historyList.getDescription());
        dialog_ratingBar.setRating(historyList.getStars());

        yes_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void pieChart(int done, int notDone)
    {
        if (done == 0 && notDone == 0)
        {
            Toast.makeText(getApplicationContext(), "no data found", Toast.LENGTH_SHORT).show();
            mChart.setVisibility(View.INVISIBLE);
        }
        else
        {
            mChart.setVisibility(View.VISIBLE);
            entries.clear();

            entries.add(new PieEntry(done, "Done"));
            entries.add(new PieEntry(notDone, "Not Done"));

            mChart.setUsePercentValues(true);
            mChart.getDescription().setEnabled(false);
            mChart.setDrawEntryLabels(false);

            mChart.setExtraOffsets(5, 5, 5, 5);
            mChart.setDragDecelerationFrictionCoef(0.95f);

            mChart.setDrawHoleEnabled(false);
            mChart.setHoleColor(Color.WHITE);

            mChart.setTransparentCircleColor(Color.WHITE);
            mChart.setTransparentCircleAlpha(110);
            mChart.setHoleRadius(50f);
            mChart.setTransparentCircleRadius(31f);

            mChart.setDrawCenterText(false);

            mChart.setRotationAngle(0);
            mChart.setRotationEnabled(true);
            mChart.setHighlightPerTapEnabled(true);

            Legend l = mChart.getLegend();
            l.setEnabled(true);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);       // label is on the right
            l.setOrientation(Legend.LegendOrientation.VERTICAL);
            l.setTextSize(17f);

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
            data.setDrawValues(false);                   // hide drawn value
            mChart.setData(data);

            mChart.highlightValues(null);

            mChart.invalidate();
        }
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
                    mData.clear();

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
                            String done_learnt = object.getString("description");
                            HistoryList historyList = new HistoryList(done_date, done_title, Integer.valueOf(done_rating), Color.parseColor("#ffa0eb9a"), done_learnt);
                            doneList.add(historyList);
                        }

                        for (int i = 0; i < notDone_activity.length(); i++)
                        {
                            JSONObject object = notDone_activity.getJSONObject(i);
                            String notDone_date = object.getString("schedule_date");
                            String notDone_title = object.getString("title");
                            String notDone_reason = object.getString("not_done_reason");
                            HistoryList historyList = new HistoryList(notDone_date, notDone_title, 0, Color.parseColor("#FFF78C"), notDone_reason);
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
