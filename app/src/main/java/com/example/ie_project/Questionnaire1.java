package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Questionnaire1 extends Fragment
{
    View question1;
    private Button next;
    private int selectedItemCounter1 = 0;
    private String gender;
    private RadioGroup radioGroup;
    private CheckBox dog, cat, other_pet;
    private EditText other_pet_text;
    private Set<String> petList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question1 = inflater.inflate(R.layout.questionnaire1_layout, container, false);

        final FragmentManager fragmentManager = getFragmentManager();

        initView();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton gender_radio = question1.findViewById(checkedId);
                if (gender_radio.getId() == R.id.male_parent)
                    gender = "male";
                else
                    gender = "female";
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordCheckBox();




                fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire2()).commit();

            }
        });

        other_pet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (other_pet.isChecked()) {
                    other_pet_text.setVisibility(View.VISIBLE);
                }
                else {
                    other_pet_text.setVisibility(View.INVISIBLE);
                }
            }
        });


//        final FoldingCell fc = (FoldingCell) question1.findViewById(R.id.folding_cell);
//        fc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fc.toggle(false);
//            }
//        });

        return question1;
    }

    public void initView()
    {
        gender = "";
        petList = new HashSet();
        radioGroup = question1.findViewById(R.id.radioGroup_gender);
        next = question1.findViewById(R.id.next_ques);
        dog = question1.findViewById(R.id.dog);
        cat = question1.findViewById(R.id.cat);
        other_pet = question1.findViewById(R.id.other_pet);
        other_pet_text = question1.findViewById(R.id.other_pet_text);

//        listener1(dog);
//        listener1(cat);
    }

    public void listener1(final CheckBox check)
    {
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    //selectedItemCounter1++;
                    petList.add(buttonView.getText().toString());
                }
                else
                {
                    //selectedItemCounter1--;
                    for (String ele: petList)
                    {
                        if (ele.equals(buttonView.getText().toString()))
                            petList.remove(ele);            //if unchecked, remove from the list
                    }
                }
            }
        });
    }

    public boolean isValid(String input)
    {
        if (input.trim().length() != 0)
            return true;
        else
            return false;
    }

    public void recordCheckBox()
    {
        if (dog.isChecked())
            petList.add("dog");
        if (cat.isChecked())
            petList.add("cat");
        if (other_pet.isChecked())
        {
            if (isValid(other_pet_text.getText().toString()))
                petList.add(other_pet_text.getText().toString().trim());
        }
    }
}
