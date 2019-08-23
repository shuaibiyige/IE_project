package com.example.ie_project;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

        Spinner age_spinner = (Spinner) question1.findViewById(R.id.ageSpinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.age, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age_spinner.setAdapter(adapter1);

        return question1;
    }
}
