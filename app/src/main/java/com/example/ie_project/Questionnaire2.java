package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.daimajia.numberprogressbar.NumberProgressBar;


public class Questionnaire2 extends Fragment
{
    View question2;
    private Button next;
    private Button previous;
    private NumberProgressBar bnp;
    private int selectedItemCounter1 = 0;
    private int selectedItemCounter2 = 0;
    private int selectedItemCounter3 = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question2 = inflater.inflate(R.layout.questionnaire2_layout, container, false);

        initView();


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

    public void initView()
    {
        bnp = (NumberProgressBar) question2.findViewById(R.id.progress_bar2);
        next = (Button) question2.findViewById(R.id.next_button2);
        previous = (Button) question2.findViewById(R.id.previous_button2);
        CheckBox chatty = (CheckBox) question2.findViewById(R.id.chatty);
        CheckBox quiet = (CheckBox) question2.findViewById(R.id.quiet);
        CheckBox funny = (CheckBox) question2.findViewById(R.id.funny);
        CheckBox serious = (CheckBox) question2.findViewById(R.id.serious);
        CheckBox shy = (CheckBox) question2.findViewById(R.id.shy);

        CheckBox movie = (CheckBox) question2.findViewById(R.id.movie);
        CheckBox baking = (CheckBox) question2.findViewById(R.id.baking);
        CheckBox board_game = (CheckBox) question2.findViewById(R.id.board_game);
        CheckBox gaming = (CheckBox) question2.findViewById(R.id.gaming);
        CheckBox arts_crafts = (CheckBox) question2.findViewById(R.id.arts_crafts);

        CheckBox footy = (CheckBox) question2.findViewById(R.id.footy);
        CheckBox badminton = (CheckBox) question2.findViewById(R.id.badminton);
        CheckBox basketball = (CheckBox) question2.findViewById(R.id.basketball);
        CheckBox hiking = (CheckBox) question2.findViewById(R.id.hiking);

        listener1(chatty);
        listener1(quiet);
        listener1(funny);
        listener1(serious);
        listener1(shy);

        listener2(movie);
        listener2(baking);
        listener2(board_game);
        listener2(gaming);
        listener2(arts_crafts);

        listener3(footy);
        listener3(badminton);
        listener3(basketball);
        listener3(hiking);
    }

    public void listener1(final CheckBox check)
    {
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    selectedItemCounter1++;
                }
                else
                {
                    selectedItemCounter1--;
                }
                if (selectedItemCounter1 > 3)
                {
                    buttonView.setChecked(false);
                    selectedItemCounter1--;
                }
            }
        });
    }

    public void listener2(final CheckBox check)
    {
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    selectedItemCounter2++;
                }
                else
                {
                    selectedItemCounter2--;
                }
                if (selectedItemCounter2 > 3)
                {
                    buttonView.setChecked(false);
                    selectedItemCounter2--;
                }
            }
        });
    }

    public void listener3(final CheckBox check)
    {
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    selectedItemCounter3++;
                }
                else
                {
                    selectedItemCounter3--;
                }
                if (selectedItemCounter3 > 3)
                {
                    buttonView.setChecked(false);
                    selectedItemCounter3--;
                }
            }
        });
    }
}
