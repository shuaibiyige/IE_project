package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class Dashboard extends Fragment
{
    View dashboard;
    private ImageView tick_ques, tick_schedule, tick_feedback, man_image;
    private TextView welcome, questionnaire, schedule, feedback, todo_text;
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
        todo_text = (TextView) dashboard.findViewById(R.id.todo_text);
        tick_schedule = (ImageView) dashboard.findViewById(R.id.tick_sch);
        feedback = (TextView) dashboard.findViewById(R.id.feedback);
        tick_feedback = (ImageView) dashboard.findViewById(R.id.tick_feedback);
        linearLayout = (LinearLayout) dashboard.findViewById(R.id.lineLayout);
        man_image = (ImageView) dashboard.findViewById(R.id.man_image);

        welcome.startAnimation(ani1);
        linearLayout.startAnimation(ani1);
        man_image.startAnimation(ani2);

        tick_ques.setVisibility(View.INVISIBLE);
        tick_schedule.setVisibility(View.INVISIBLE);
        tick_feedback.setVisibility(View.INVISIBLE);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String user_name = sharedPreferences.getString("user_name", "");
        int isQuestionnaire = sharedPreferences.getInt("isQuestionnaire", 0);
        int isSchedule = sharedPreferences.getInt("isSchedule", 0);

        welcome.setText("Hey, " + user_name);
        if (isQuestionnaire == 1)                     // questionnaire is done
            tick_ques.setVisibility(View.VISIBLE);    // show the tick
        if (isSchedule == 1)
            tick_schedule.setVisibility(View.VISIBLE);

//        int[] list = size();
//        int width = list[0];
//        int height = list[1];
//        man_image.getLayoutParams().height = height;
//        man_image.getLayoutParams().width = width;
//        man_image.requestLayout();
//
//        todo_text.setTextSize((height / todo_text.getTextSize()) * 6);


        questionnaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent anotherIntent = new Intent(getActivity(), Questionnaire.class);         // go to questionnaire page
                startActivity(anotherIntent);
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Schedule.class);           // go to schedule page
                startActivity(intent);
            }
        });

        return dashboard;
    }

    public int[] size()
    {
        int[] list = new int[2];
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)
        list[0] = screenWidth;
        list[1] = screenHeight;
        return list;
    }
}
