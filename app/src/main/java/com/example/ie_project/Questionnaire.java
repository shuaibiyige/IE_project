package com.example.ie_project;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.daimajia.numberprogressbar.NumberProgressBar;

public class Questionnaire extends AppCompatActivity
{
    private Button next;
    private NumberProgressBar bnp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire_template);

        bnp = (NumberProgressBar)findViewById(R.id.progress_bar);
        bnp.incrementProgressBy(20);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire1()).commit();

    }
}
