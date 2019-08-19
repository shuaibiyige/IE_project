package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Dashboard extends Fragment
{
    View dashboard;
    private Button questionnaire;
    private Button schedule;
    private Button activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        dashboard = inflater.inflate(R.layout.fragment_dashboard, container, false);

        questionnaire = (Button) dashboard.findViewById(R.id.questionnaire);
        schedule = (Button) dashboard.findViewById(R.id.schedule);
        activity = (Button) dashboard.findViewById(R.id.activity);

        Intent intent = getActivity().getIntent();
        boolean newUser = intent.getBooleanExtra("new user", false);

        if (newUser == true)
        {
            schedule.setVisibility(View.GONE);
            activity.setVisibility(View.GONE);
            questionnaire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent anotherIntent = new Intent(getActivity(), Questionnaire.class);
                    startActivity(anotherIntent);
                }
            });
        }
        else
        {
            questionnaire.setVisibility(View.GONE);
        }

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, new Schedule()).commit();
            }
        });

        return dashboard;
    }
}
