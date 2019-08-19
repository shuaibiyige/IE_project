package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Questionnaire1 extends Fragment
{
    View question1;
    private Button next;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question1 = inflater.inflate(R.layout.questionnaire1_layout, container, false);
        next = (Button) question1.findViewById(R.id.next1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire2()).commit();
            }
        });
        return question1;
    }
}
