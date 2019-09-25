package com.example.ie_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;

public class Feedback extends AppCompatActivity
{
    private PieChart mChart;
    private ListView listView;
    private ImageButton done, notDone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().hide();

        //mChart = findViewById(R.id.pieChart);
        //listView = findViewById(R.id.listView);
        done = findViewById(R.id.done_activity);
        notDone = findViewById(R.id.not_done_activity);
    }
}
