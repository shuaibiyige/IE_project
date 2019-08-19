package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Schedule extends Fragment
{
    private View schedule;
    private Button submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        schedule = inflater.inflate(R.layout.fragment_schedule, container, false);
        submit = schedule.findViewById(R.id.schedule_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, new Dashboard()).commit();
            }
        });
        return schedule;
    }
}
