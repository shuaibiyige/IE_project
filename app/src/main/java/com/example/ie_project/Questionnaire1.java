package com.example.ie_project;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.content.Context.MODE_PRIVATE;

public class Questionnaire1 extends Fragment
{
    View question1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question1 = inflater.inflate(R.layout.questionnaire1_layout, container, false);
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("questionnaire_page", MODE_PRIVATE).edit();
        editor.putInt("page", 1);
        editor.apply();
        return question1;
    }
}
