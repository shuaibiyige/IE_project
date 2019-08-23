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

public class Questionnaire3 extends Fragment
{
    View question3;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question3 = inflater.inflate(R.layout.questionnaire3_layout, container, false);
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("questionnaire_page", MODE_PRIVATE).edit();
        editor.putInt("page", 3);
        editor.apply();

        Spinner age_spinner = (Spinner) question3.findViewById(R.id.ageSpinner_child);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.age_child, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age_spinner.setAdapter(adapter1);

        return question3;
    }
}
