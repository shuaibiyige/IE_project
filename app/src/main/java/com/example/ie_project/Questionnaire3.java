package com.example.ie_project;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.content.Context.MODE_PRIVATE;

public class Questionnaire3 extends Fragment
{
    View question3;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question3 = inflater.inflate(R.layout.questionnaire3_layout, container, false);
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("questionnaire_page", MODE_PRIVATE).edit();
        editor.putInt("page", 3);
        editor.apply();
        return question3;
    }
}
