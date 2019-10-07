package com.example.ie_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class History extends AppCompatActivity implements View.OnClickListener
{
    private List<HistoryList> mData;
    private ListView listView;
    private PieChart mChart;
    private Button start_button, end_button, all_button, back;
    private Calendar calendar;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().hide();

        listView = findViewById(R.id.listView);
        mChart = findViewById(R.id.pieChart);
        start_button = findViewById(R.id.history_start);
        end_button = findViewById(R.id.history_end);
        all_button = findViewById(R.id.history_all);
        back = findViewById(R.id.history_back);

        LayoutInflater inflater =getLayoutInflater();
        initData();

        HistoryAdapter historyAdapter = new HistoryAdapter(inflater, mData);
        listView.setAdapter(historyAdapter);

        start_button.setOnClickListener(this);
        end_button.setOnClickListener(this);
        all_button.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void initData()
    {
        mData = new ArrayList<HistoryList>();
        HistoryList historyList = new HistoryList("2019-01-01", "name", 5, Color.parseColor("#ff9abfeb"));

        mData.add(historyList);
        mData.add(historyList);
        mData.add(historyList);
        mData.add(historyList);
        mData.add(historyList);
        mData.add(historyList);
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
                time = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                if (input == 1)
                    start_button.setText(time);
                else
                    end_button.setText(time);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
}
