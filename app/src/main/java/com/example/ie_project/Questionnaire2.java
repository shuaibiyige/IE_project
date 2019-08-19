package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Questionnaire2 extends Fragment
{
    View question2;
    private Button next;
    private Button previous;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question2 = inflater.inflate(R.layout.questionnaire2_layout, container, false);
        next = (Button) question2.findViewById(R.id.next2);
        previous = (Button) question2.findViewById(R.id.previous2);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Thankyou()).commit();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire1()).commit();
            }
        });
        return question2;
    }
}
