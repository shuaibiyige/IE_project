package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daimajia.numberprogressbar.NumberProgressBar;


public class Questionnaire2 extends Fragment
{
    View question2;
    private Button next;
    private Button previous;
    private NumberProgressBar bnp;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question2 = inflater.inflate(R.layout.questionnaire2_layout, container, false);

        bnp = (NumberProgressBar) question2.findViewById(R.id.progress_bar2);
        next = (Button) question2.findViewById(R.id.next_button2);
        previous = (Button) question2.findViewById(R.id.previous_button2);
        final FragmentManager fragmentManager = getFragmentManager();
        bnp.setProgress(66);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire3()).commit();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire1()).commit();
            }
        });

        return question2;
    }
}
