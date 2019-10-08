package com.example.ie_project;


import android.os.Bundle;
import androidx.fragment.app.FragmentManager;

import androidx.appcompat.app.AppCompatActivity;


public class JourneyQuestionnaire extends AppCompatActivity
{
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_questionnaire);
        getSupportActionBar().hide();

        fragmentManager.beginTransaction().replace(R.id.journey_content_frame, new JourneyQuestionnaire1()).commit();
    }
}