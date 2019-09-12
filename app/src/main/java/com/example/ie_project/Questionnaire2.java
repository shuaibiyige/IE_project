package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashSet;
import java.util.Set;


public class Questionnaire2 extends Fragment
{
    View question2;
    private Button submit;
    private RadioGroup radioGroup_gender, radioGroup, radioGroup_restrictions;
    private EditText child_name_edit, other_restrictions_text, other_hobbies_text, other_description_text;
    private CheckBox reading, dancing, music, sport, video_game, other_hobbies, adventure, health, tech, art, indoorsy, other_description;
    private String name, restriction, gender;
    private int age;
    //private int selectedItemCounter1 = 0;
    private Set<String> hobbiesList, descriptionList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question2 = inflater.inflate(R.layout.questionnaire2_layout, container, false);
        final FragmentManager fragmentManager = getFragmentManager();


        initView();

        radioGroup_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton gender_radio = question2.findViewById(checkedId);
                if (gender_radio.getId() == R.id.male_parent)
                    gender = "male";
                else
                    gender = "female";
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton age_button = (RadioButton) question2.findViewById(checkedId);
                if (age_button.getText().toString().equals("13"))
                    age = 13;
                if (age_button.getText().toString().equals("14"))
                    age = 14;
                else
                    age = 15;
            }
        });

        radioGroup_restrictions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton restriction_radio = question2.findViewById(checkedId);
                if (restriction_radio.getId() == R.id.restrictions_none_child) {
                    other_restrictions_text.setVisibility(View.INVISIBLE);
                    restriction = "none";
                }
                if (restriction_radio.getId() == R.id.restrictions_vegan_child) {
                    other_restrictions_text.setVisibility(View.INVISIBLE);
                    restriction = "vegan";
                }
                if (restriction_radio.getId() == R.id.restrictions_vegetarian_child) {
                    other_restrictions_text.setVisibility(View.INVISIBLE);
                    restriction = "vegetarian";
                }
                if (restriction_radio.getId() == R.id.restrictions_other_child)
                {
                    other_restrictions_text.setVisibility(View.VISIBLE);
                    if (isValid(other_restrictions_text.getText().toString()))
                        restriction = other_restrictions_text.getText().toString();
                }
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid(child_name_edit.getText().toString()))
                    name = child_name_edit.getText().toString();

                recordCheckBoxHobbies();
                recordCheckBoxDescription();




                fragmentManager.beginTransaction().replace(R.id.content_frame, new Successful()).commit();
            }
        });

        otherBoxChecked(other_hobbies, other_hobbies_text);
        otherBoxChecked(other_description, other_description_text);

//        final FoldingCell fc = (FoldingCell) question1.findViewById(R.id.folding_cell);
//        fc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fc.toggle(false);
//            }
//        });



        return question2;
    }

    public void initView()
    {
        gender = "";
        age = 0;
        name = "";
        restriction = "";
        hobbiesList = new HashSet();
        descriptionList = new HashSet();
        submit = question2.findViewById(R.id.submit_ques);
        radioGroup_gender = question2.findViewById(R.id.radioGroup_gender_child);
        radioGroup = question2.findViewById(R.id.radioGroup_age_child);
        child_name_edit = question2.findViewById(R.id.child_name);
        radioGroup_restrictions = question2.findViewById(R.id.radioGroup_restrictions_child);
        other_restrictions_text = question2.findViewById(R.id.restrictions_other_child_text);
        reading = question2.findViewById(R.id.reading_child);
        dancing = question2.findViewById(R.id.dancing_child);
        music = question2.findViewById(R.id.music_child);
        sport = question2.findViewById(R.id.sports_games_child);
        video_game = question2.findViewById(R.id.video_games_child);
        other_hobbies = question2.findViewById(R.id.other_hobbies_child);
        other_hobbies_text = question2.findViewById(R.id.other_hobbies_child_text);
        adventure = question2.findViewById(R.id.adventure_lover_child);
        health = question2.findViewById(R.id.health_conscious_child);
        tech = question2.findViewById(R.id.tech_savy_child);
        art = question2.findViewById(R.id.arts_child);
        indoorsy = question2.findViewById(R.id.indoorsy_child);
        other_description = question2.findViewById(R.id.describe_other_child);
        other_description_text = question2.findViewById(R.id.type_description_child);

//        listener1(movie);
//        listener1(gaming);
//        listener1(baking);
//        listener1(diy);
//        listener1(painting);
//        listener1(cooking);
    }

//    public void listener1(final CheckBox check)
//    {
//        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
//            {
//                if (isChecked)
//                {
//                    selectedItemCounter1++;
//                }
//                else
//                {
//                    selectedItemCounter1--;
//                }
//                if (selectedItemCounter1 > 3)
//                {
//                    buttonView.setChecked(false);
//                    selectedItemCounter1--;
//                }
//            }
//        });
//    }

    public boolean isValid(String input)
    {
        if (input.trim().length() != 0)
            return true;
        else
            return false;
    }

    public void recordCheckBoxHobbies()
    {
        if (reading.isChecked())
            hobbiesList.add("reading books");
        if (dancing.isChecked())
            hobbiesList.add("dancing");
        if (music.isChecked())
            hobbiesList.add("music");
        if (sport.isChecked())
            hobbiesList.add("sports / games");
        if (video_game.isChecked())
            hobbiesList.add("video games");
        if (other_hobbies.isChecked())
        {
            if (isValid(other_hobbies_text.getText().toString()))
                hobbiesList.add(other_hobbies_text.getText().toString().trim());
        }
    }

    public void recordCheckBoxDescription()
    {
        if (adventure.isChecked())
            descriptionList.add("adventure lover");
        if (health.isChecked())
            descriptionList.add("health conscious");
        if (tech.isChecked())
            descriptionList.add("tech savy");
        if (art.isChecked())
            descriptionList.add("adept at arts & crafts");
        if (indoorsy.isChecked())
            descriptionList.add("indoorsy");
        if (other_description.isChecked())
        {
            if (isValid(other_description_text.getText().toString()))
                descriptionList.add(other_description_text.getText().toString().trim());
        }
    }

    public void otherBoxChecked(final CheckBox checkBox, final EditText editText)
    {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    editText.setVisibility(View.VISIBLE);
                }
                else {
                    editText.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

}
