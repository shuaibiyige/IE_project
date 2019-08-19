package com.example.ie_project;

import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.daimajia.numberprogressbar.NumberProgressBar;

public class Questionnaire extends AppCompatActivity
{
    private Button next;
    private Button previous;
    private NumberProgressBar bnp;
    private FragmentManager fragmentManager = getFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire_template);

        bnp = (NumberProgressBar) findViewById(R.id.progress_bar);
        next = (Button) findViewById(R.id.next_button);
        previous = (Button) findViewById(R.id.previous_button);

        fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire1()).commit();
        bnp.setProgress(20);
        previous.setVisibility(View.INVISIBLE);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SharedPreferences sharedPreferences = getSharedPreferences("questionnaire_page", Context.MODE_PRIVATE);
                int page = sharedPreferences.getInt("page", 0);
                judgePage1(page);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SharedPreferences sharedPreferences = getSharedPreferences("questionnaire_page", Context.MODE_PRIVATE);
                int page = sharedPreferences.getInt("page", 0);
                judgePage2(page);
            }
        });

    }

    public void judgePage1(int page)
    {
        switch (page)
        {
            case 1:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire2()).commit();
                bnp.setProgress(40);
                previous.setVisibility(View.VISIBLE);
                break;
            case 2:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire3()).commit();
                bnp.setProgress(60);
                break;
        }
    }

    public void judgePage2(int page)
    {
        switch (page)
        {
            case 2:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire1()).commit();
                bnp.setProgress(20);
                previous.setVisibility(View.INVISIBLE);
                break;
            case 3:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire2()).commit();
                bnp.setProgress(40);
                break;
        }
    }
}
