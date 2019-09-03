package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Dashboard extends Fragment
{
    View dashboard;
    private ImageView tick_ques, tick_schedule, tick_feedback, man_image;
    private TextView welcome, questionnaire, schedule, feedback;
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        dashboard = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Animation ani1 = AnimationUtils.loadAnimation(getActivity(), R.anim.register_anim);
        Animation ani2 = AnimationUtils.loadAnimation(getActivity(), R.anim.dashboard_image);

        questionnaire = (TextView) dashboard.findViewById(R.id.questionnaire);
        welcome = (TextView) dashboard.findViewById(R.id.welcome_name);
        tick_ques = (ImageView) dashboard.findViewById(R.id.tick_ques);
        schedule = (TextView) dashboard.findViewById(R.id.schedule);
        tick_schedule = (ImageView) dashboard.findViewById(R.id.tick_sch);
        feedback = (TextView) dashboard.findViewById(R.id.feedback);
        tick_feedback = (ImageView) dashboard.findViewById(R.id.tick_feedback);
        linearLayout = (LinearLayout) dashboard.findViewById(R.id.lineLayout);
        man_image = (ImageView) dashboard.findViewById(R.id.man_image);

        welcome.startAnimation(ani1);
        linearLayout.startAnimation(ani1);
        man_image.startAnimation(ani2);

//        if (newUser == true)
//        {
//            //schedule.setVisibility(View.GONE);
//            questionnaire.setEnabled(true);
//            schedule.setEnabled(false);
//            schedule.setBackgroundColor(Color.parseColor("#dcdcdc"));
//            questionnaire.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent anotherIntent = new Intent(getActivity(), Questionnaire.class);
//                    startActivity(anotherIntent);
//                }
//            });
//        }
//        else
//        {
//            //questionnaire.setVisibility(View.GONE);
//            schedule.setEnabled(true);
//            questionnaire.setEnabled(false);
//            questionnaire.setBackgroundColor(Color.parseColor("#dcdcdc"));
//        }
//
//        schedule.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.content_main, new Schedule()).commit();
//            }
//        });

        return dashboard;
    }
}
