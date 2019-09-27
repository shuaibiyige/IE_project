package com.example.ie_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.kyle.calendarprovider.calendar.CalendarEvent;
import com.kyle.calendarprovider.calendar.CalendarProviderManager;

import java.util.Calendar;

public class Feedback extends AppCompatActivity
{
    private PieChart mChart;
    private ListView listView;
    private ImageButton done, notDone;
    private LinearLayout layout;

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
        layout = findViewById(R.id.feedback_yes_layout);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (layout.getVisibility() == View.INVISIBLE)
                    layout.setVisibility(View.VISIBLE);
            }
        });

        notDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                alertDialog();
            }
        });
    }

    public void alertDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(this, R.layout.feedback_dialog, null);
        dialog.setView(dialogView);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());        //erase white background
        dialog.show();

        Button save = dialogView.findViewById(R.id.feedback_save_dialog);
        final EditText whyNot = dialogView.findViewById(R.id.feedback_edit_dialog);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String feedback = whyNot.getText().toString();
                if (feedback.trim().length() != 0)
                {
                    // upload



                    dialog.dismiss();
                }
                else
                    Toast.makeText(getApplicationContext(), "you haven't input anything", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
