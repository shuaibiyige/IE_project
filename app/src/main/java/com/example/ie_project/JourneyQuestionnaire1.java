package com.example.ie_project;

import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class JourneyQuestionnaire1 extends Fragment
{
    private View journeyQuestionnaire;
    private Button submit, back;
    private RadioGroup radioGroup_journey_q1, radioGroup_journey_q2, radioGroup_journey_q3, radioGroup_journey_q4, radioGroup_journey_q5, radioGroup_journey_q6, radioGroup_journey_q7, radioGroup_journey_q8, radioGroup_journey_q9,radioGroup_journey_q10;
    private int q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, identification, rejection, autonomy, cohesion, conflict, user_id;
    private RequestQueue requestQueue;

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        journeyQuestionnaire = inflater.inflate(R.layout.journey_questionnaire_layout, container, false);

        requestQueue = Volley.newRequestQueue(getContext());
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



        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        user_id = sharedPreferences.getInt("user_id", 0);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(getActivity(), MainActivity.class);      // back to main page
                startActivity(backIntent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (q1 != 0 && q2 != 0 && q3 != 0 && q4 != 0 && q5 != 0 && q6 != 0 && q7 != 0 && q8 != 0 && q9 != 0 && q10 != 0)
                {
                    identification = q1 + q5;
                    rejection = q2 + q10;
                    autonomy = q3 + q6;
                    cohesion = q4 + q9;
                    conflict = q7 + q8;

                    transSurveyData(identification, rejection, autonomy, cohesion, conflict, user_id);
                }
                else if (q1 == 0)
                    Toast.makeText(getActivity(), "question1 not done", Toast.LENGTH_SHORT).show();
                else if (q2 == 0)
                    Toast.makeText(getActivity(), "question2 not done", Toast.LENGTH_SHORT).show();
                else if (q3 == 0)
                    Toast.makeText(getActivity(), "question3 not done", Toast.LENGTH_SHORT).show();
                else if (q4 == 0)
                    Toast.makeText(getActivity(), "question4 not done", Toast.LENGTH_SHORT).show();
                else if (q5 == 0)
                    Toast.makeText(getActivity(), "question5 not done", Toast.LENGTH_SHORT).show();
                else if (q6 == 0)
                    Toast.makeText(getActivity(), "question6 not done", Toast.LENGTH_SHORT).show();
                else if (q7 == 0)
                    Toast.makeText(getActivity(), "question7 not done", Toast.LENGTH_SHORT).show();
                else if (q8 == 0)
                    Toast.makeText(getActivity(), "question8 not done", Toast.LENGTH_SHORT).show();
                else if (q9 == 0)
                    Toast.makeText(getActivity(), "question9 not done", Toast.LENGTH_SHORT).show();
                else if (q10 == 0)
                    Toast.makeText(getActivity(), "question10 not done", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "some questions are not done", Toast.LENGTH_SHORT).show();
            }
        });

        return journeyQuestionnaire;
    }

    public void initView()
    {
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
        back = journeyQuestionnaire.findViewById(R.id.questionnaire_journey_back);
        identification = 0;
        rejection = 0;
        autonomy = 0;
        cohesion = 0;
        conflict = 0;
        user_id = 0;
        q1 = q2 = q3 = q4 = q5 = q6 = q7 = q8 = q9 = q10 = 0;
    }

    public void transSurveyData(final int identification, final int rejection, final int autonomy, final int cohesion, final int conflict, final int user_id)
    {
        String connectUrl = "http://ec2-13-236-44-7.ap-southeast-2.compute.amazonaws.com/letosaid/addSurveyScore.php";

        com.android.volley.Response.Listener<String> listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String s)
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(s);
                    int retCode = jsonObject.getInt("success");

                    if (retCode == 1)
                    {
                        Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), JourneySummary.class);
                        startActivity(intent);
                    }
                }
                catch (JSONException e)
                {
                    Toast.makeText(getContext(),"Network is unavailable", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        };

        com.android.volley.Response.ErrorListener errorListener = new com.android.volley.Response.ErrorListener() {
            public String TAG = "LOG";
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.POST, connectUrl, listener, errorListener)
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> map = new HashMap<>();
                map.put("user_id", String.valueOf(user_id));
                map.put("identification", String.valueOf(identification));
                map.put("rejection", String.valueOf(rejection));
                map.put("autonomy", String.valueOf(autonomy));
                map.put("cohesion", String.valueOf(cohesion));
                map.put("conflict", String.valueOf(conflict));

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
