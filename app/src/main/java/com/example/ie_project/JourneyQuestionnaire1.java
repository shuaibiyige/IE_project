package com.example.ie_project;

import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class JourneyQuestionnaire1 extends Fragment {
    View journeyQuestionnaire;
    private Button submit;
    private RadioGroup radioGroup_journey_q1, radioGroup_journey_q2, radioGroup_journey_q3, radioGroup_journey_q4, radioGroup_journey_q5, radioGroup_journey_q6, radioGroup_journey_q7, radioGroup_journey_q8, radioGroup_journey_q9,radioGroup_journey_q10;
    private int q1, q2, q3, q4, q5, q6, q7, q8, q9, q10;

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        journeyQuestionnaire = inflater.inflate(R.layout.journey_questionnaire_layout, container, false);
        final FragmentManager fragmentManager = getFragmentManager();

        initView();

        radioGroup_journey_q1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton journey_q1 = journeyQuestionnaire.findViewById(checkedId);
                if (journey_q1.getId() == R.id.journey_q1_always) {
                    q1 = 5;
                }
                if (journey_q1.getId() == R.id.journey_q1_often) {
                    q1 = 4;
                }
                if (journey_q1.getId() == R.id.journey_q1_sometimes) {
                    q1 = 3;
                }
                if (journey_q1.getId() == R.id.journey_q1_rarely) {
                    q1 = 2;
                }
                if (journey_q1.getId() == R.id.journey_q1_never) {
                    q1 = 1;
                }
            }
        });

        radioGroup_journey_q2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton journey_q2 = journeyQuestionnaire.findViewById(checkedId);
                if (journey_q2.getId() == R.id.journey_q2_always) {
                    q2 = 5;
                }
                if (journey_q2.getId() == R.id.journey_q2_often) {
                    q2 = 4;
                }
                if (journey_q2.getId() == R.id.journey_q2_sometimes) {
                    q2 = 3;
                }
                if (journey_q2.getId() == R.id.journey_q2_rarely) {
                    q2 = 2;
                }
                if (journey_q2.getId() == R.id.journey_q2_never) {
                    q2 = 1;
                }
            }
        });

        radioGroup_journey_q3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton journey_q3 = journeyQuestionnaire.findViewById(checkedId);
                if (journey_q3.getId() == R.id.journey_q3_always) {
                    q3 = 5;
                }
                if (journey_q3.getId() == R.id.journey_q3_often) {
                    q3 = 4;
                }
                if (journey_q3.getId() == R.id.journey_q3_sometimes) {
                    q3 = 3;
                }
                if (journey_q3.getId() == R.id.journey_q3_rarely) {
                    q3 = 2;
                }
                if (journey_q3.getId() == R.id.journey_q3_never) {
                    q3 = 1;
                }
            }
        });

        radioGroup_journey_q4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton journey_q4 = journeyQuestionnaire.findViewById(checkedId);
                if (journey_q4.getId() == R.id.journey_q4_always) {
                    q4 = 5;
                }
                if (journey_q4.getId() == R.id.journey_q4_often) {
                    q4 = 4;
                }
                if (journey_q4.getId() == R.id.journey_q4_sometimes) {
                    q4 = 3;
                }
                if (journey_q4.getId() == R.id.journey_q4_rarely) {
                    q4 = 2;
                }
                if (journey_q4.getId() == R.id.journey_q4_never) {
                    q4 = 1;
                }
            }
        });

        radioGroup_journey_q5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton journey_q5 = journeyQuestionnaire.findViewById(checkedId);
                if (journey_q5.getId() == R.id.journey_q5_always) {
                    q5 = 5;
                }
                if (journey_q5.getId() == R.id.journey_q5_often) {
                    q5 = 4;
                }
                if (journey_q5.getId() == R.id.journey_q5_sometimes) {
                    q5 = 3;
                }
                if (journey_q5.getId() == R.id.journey_q5_rarely) {
                    q5 = 2;
                }
                if (journey_q5.getId() == R.id.journey_q5_never) {
                    q5 = 1;
                }
            }
        });

        radioGroup_journey_q6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton journey_q6 = journeyQuestionnaire.findViewById(checkedId);
                if (journey_q6.getId() == R.id.journey_q6_always) {
                    q6 = 5;
                }
                if (journey_q6.getId() == R.id.journey_q6_often) {
                    q6 = 4;
                }
                if (journey_q6.getId() == R.id.journey_q6_sometimes) {
                    q6 = 3;
                }
                if (journey_q6.getId() == R.id.journey_q6_rarely) {
                    q6 = 2;
                }
                if (journey_q6.getId() == R.id.journey_q6_never) {
                    q6 = 1;
                }
            }
        });

        radioGroup_journey_q7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton journey_q7 = journeyQuestionnaire.findViewById(checkedId);
                if (journey_q7.getId() == R.id.journey_q7_always) {
                    q7 = 1;
                }
                if (journey_q7.getId() == R.id.journey_q7_often) {
                    q7 = 2;
                }
                if (journey_q7.getId() == R.id.journey_q7_sometimes) {
                    q7 = 3;
                }
                if (journey_q7.getId() == R.id.journey_q7_rarely) {
                    q7 = 4;
                }
                if (journey_q7.getId() == R.id.journey_q7_never) {
                    q7 = 5;
                }
            }
        });

        radioGroup_journey_q8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton journey_q8 = journeyQuestionnaire.findViewById(checkedId);
                if (journey_q8.getId() == R.id.journey_q8_always) {
                    q8 = 1;
                }
                if (journey_q8.getId() == R.id.journey_q8_often) {
                    q8 = 2;
                }
                if (journey_q8.getId() == R.id.journey_q8_sometimes) {
                    q8 = 3;
                }
                if (journey_q8.getId() == R.id.journey_q8_rarely) {
                    q8 = 4;
                }
                if (journey_q8.getId() == R.id.journey_q8_never) {
                    q8 = 5;
                }
            }
        });

        radioGroup_journey_q9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton journey_q9 = journeyQuestionnaire.findViewById(checkedId);
                if (journey_q9.getId() == R.id.journey_q9_always) {
                    q9 = 5;
                }
                if (journey_q9.getId() == R.id.journey_q9_often) {
                    q9 = 4;
                }
                if (journey_q9.getId() == R.id.journey_q9_sometimes) {
                    q9 = 3;
                }
                if (journey_q9.getId() == R.id.journey_q9_rarely) {
                    q9 = 2;
                }
                if (journey_q9.getId() == R.id.journey_q9_never) {
                    q9 = 1;
                }
            }
        });

        radioGroup_journey_q10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton journey_q10 = journeyQuestionnaire.findViewById(checkedId);
                if (journey_q10.getId() == R.id.journey_q10_always) {
                    q10 = 5;
                }
                if (journey_q10.getId() == R.id.journey_q10_often) {
                    q10 = 4;
                }
                if (journey_q10.getId() == R.id.journey_q10_sometimes) {
                    q10 = 3;
                }
                if (journey_q10.getId() == R.id.journey_q10_rarely) {
                    q10 = 2;
                }
                if (journey_q10.getId() == R.id.journey_q10_never) {
                    q10 = 1;
                }
            }
        });




        return journeyQuestionnaire;
    }

    public void initView(){
        radioGroup_journey_q1 = journeyQuestionnaire.findViewById(R.id.radioGroup_journey_q1);
        radioGroup_journey_q2 = journeyQuestionnaire.findViewById(R.id.radioGroup_journey_q2);
        radioGroup_journey_q3 = journeyQuestionnaire.findViewById(R.id.radioGroup_journey_q3);
        radioGroup_journey_q4 = journeyQuestionnaire.findViewById(R.id.radioGroup_journey_q4);
        radioGroup_journey_q5 = journeyQuestionnaire.findViewById(R.id.radioGroup_journey_q5);
        radioGroup_journey_q6 = journeyQuestionnaire.findViewById(R.id.radioGroup_journey_q6);
        radioGroup_journey_q7 = journeyQuestionnaire.findViewById(R.id.radioGroup_journey_q7);
        radioGroup_journey_q8 = journeyQuestionnaire.findViewById(R.id.radioGroup_journey_q8);
        radioGroup_journey_q9 = journeyQuestionnaire.findViewById(R.id.radioGroup_journey_q9);
        radioGroup_journey_q10 = journeyQuestionnaire.findViewById(R.id.radioGroup_journey_q10);
        submit = journeyQuestionnaire.findViewById(R.id.journey_submit);
    }
}
