package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.ramotion.foldingcell.FoldingCell;


public class Questionnaire1 extends Fragment
{
    View question1;
    private Button next;
    private NumberProgressBar bnp;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private int checkedNumber;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question1 = inflater.inflate(R.layout.questionnaire1_layout, container, false);
//        bnp = (NumberProgressBar) question1.findViewById(R.id.progress_bar1);
//        next = (Button) question1.findViewById(R.id.next_button1);
//        radioGroup = (RadioGroup) question1.findViewById(R.id.radioGroup_gender);
//
//        final FragmentManager fragmentManager = getFragmentManager();
//        bnp.setProgress(33);
//
//        Spinner age_spinner = (Spinner) question1.findViewById(R.id.ageSpinner);
//        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.age, android.R.layout.simple_spinner_item);
//        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        age_spinner.setAdapter(adapter1);
//
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire2()).commit();
//            }
//        });

        final FoldingCell fc = (FoldingCell) question1.findViewById(R.id.folding_cell);
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
            }
        });


        return question1;
    }
}
