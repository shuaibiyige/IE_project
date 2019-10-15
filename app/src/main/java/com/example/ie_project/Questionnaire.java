package com.example.ie_project;


import android.os.Bundle;
import androidx.fragment.app.FragmentManager;

import androidx.appcompat.app.AppCompatActivity;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;


public class Questionnaire extends AppCompatActivity
{
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        getSupportActionBar().hide();

        fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire1()).commit();
    }
}
