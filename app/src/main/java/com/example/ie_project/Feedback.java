package com.example.ie_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;

public class Feedback extends AppCompatActivity
{
    private PieChart mChart;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        mChart = findViewById(R.id.pieChart);
        listView = findViewById(R.id.listView);
    }
}
