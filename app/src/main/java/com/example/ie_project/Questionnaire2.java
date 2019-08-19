package com.example.ie_project;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.content.Context.MODE_PRIVATE;

public class Questionnaire2 extends Fragment
{
    View question2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question2 = inflater.inflate(R.layout.questionnaire2_layout, container, false);
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("questionnaire_page", MODE_PRIVATE).edit();
        editor.putInt("page", 2);
        editor.apply();
        return question2;
    }
}
