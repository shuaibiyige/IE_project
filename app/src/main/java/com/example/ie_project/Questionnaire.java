package com.example.ie_project;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.numberprogressbar.NumberProgressBar;

public class Questionnaire extends AppCompatActivity
{
    private FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        getSupportActionBar().hide();

        fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire1()).commit();
    }
}
