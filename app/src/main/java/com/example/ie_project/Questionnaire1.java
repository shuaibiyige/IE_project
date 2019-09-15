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

import static android.content.Context.MODE_PRIVATE;
import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;


public class Questionnaire1 extends Fragment
{
    View question1;
    private Button next;
    private String gender, transport, age, restriction;
    private RadioButton age_30, age_35, age_40, age_45up;
    private RadioGroup radioGroup_gender, radioGroup_transport, radioGroup_age1, radioGroup_age2, radioGroup_restrictions;
    private CheckBox dog, cat, other_pet, reading, cooking, music, collecting, sport, other_hobbies, adventure, health, tech, art, indoorsy, other_description, swimming, sport_court, videoGame, barbeque, games, other_home;
    private EditText other_pet_text, other_hobbies_text, other_restrictions_text, other_description_text, other_home_text;
    private Set<String> petList, hobbiesList, descriptionList, homeList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question1 = inflater.inflate(R.layout.questionnaire1_layout, container, false);

        final FragmentManager fragmentManager = getFragmentManager();

        initView();

        radioGroup_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
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

        radioGroup_transport.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton transport_radio = question1.findViewById(checkedId);
                transport = transport_radio.getText().toString();
            }
        });

        radioGroup_age1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.age_30_34:
                        if (age_30.isChecked()) {
                            radioGroup_age2.clearCheck();
                            age = "30-34";
                        }
                        break;
                    case R.id.age_35_39:
                        if (age_35.isChecked()) {
                            radioGroup_age2.clearCheck();
                            age = "35-39";
                        }
                        break;
                }

            }
        });

        radioGroup_age2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.age_40_44:
                        if (age_40.isChecked()) {
                            radioGroup_age1.clearCheck();
                            age = "40-44";
                        }
                        break;
                    case R.id.age_45up:
                        if (age_45up.isChecked()) {
                            radioGroup_age1.clearCheck();
                            age = "45 up";
                        }
                        break;
                }
            }
        });

        radioGroup_restrictions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton restriction_radio = question1.findViewById(checkedId);
                if (restriction_radio.getId() == R.id.restrictions_none) {
                    other_restrictions_text.setVisibility(View.INVISIBLE);
                    restriction = "none";
                }
                if (restriction_radio.getId() == R.id.restrictions_vegan) {
                    other_restrictions_text.setVisibility(View.INVISIBLE);
                    restriction = "vegan";
                }
                if (restriction_radio.getId() == R.id.restrictions_vegetarian) {
                    other_restrictions_text.setVisibility(View.INVISIBLE);
                    restriction = "vegetarian";
                }
                if (restriction_radio.getId() == R.id.restrictions_other)
                {
                    other_restrictions_text.setVisibility(View.VISIBLE);
                    if (isValid(other_restrictions_text.getText().toString()))
                        restriction = other_restrictions_text.getText().toString();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordCheckBoxPet();
                recordCheckBoxHobbies();
                recordCheckBoxDescription();
                recordCheckBoxHome();

                String pets = "";
                String hobbies = "";
                String descriptions = "";
                String homes = "";

                for (String ele : petList)
                {
                    if (!pets.equals(""))
                        pets = pets + ", " + ele;
                    else
                        pets = ele;
                }

                for (String ele : hobbiesList)
                {
                    if (!hobbies.equals(""))
                        hobbies = hobbies + ", " + ele;
                    else
                        hobbies = ele;
                }

                for (String ele : descriptionList)
                {
                    if (!descriptions.equals(""))
                        descriptions = descriptions + ", " + ele;
                    else
                        descriptions = ele;
                }

                for (String ele : homeList)
                {
                    if (!homes.equals(""))
                        homes = homes + ", " + ele;
                    else
                        homes = ele;
                }

                if (age != null && hobbiesList.size() != 0 && descriptionList.size() != 0 && petList.size() != 0 && transport != null)
                {

                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("user", MODE_PRIVATE).edit();
                    editor.putString("user_gender", gender);
                    editor.putString("user_age", age);
                    editor.putString("user_pet", pets);
                    editor.putString("user_transport", transport);
                    editor.putString("user_hobbies", hobbies);
                    editor.putString("user_restriction", restriction);
                    editor.putString("user_description", descriptions);
                    editor.putString("user_home", homes);
                    editor.apply();

                    fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire2()).commit();
                }
                else if (age == null)
                    Toast.makeText(getActivity(),"age cannot be empty",Toast.LENGTH_SHORT).show();
                else if (hobbiesList.size() == 0)
                    Toast.makeText(getActivity(),"hobbies cannot be empty",Toast.LENGTH_SHORT).show();
                else if (descriptionList.size() == 0)
                    Toast.makeText(getActivity(),"description cannot be empty",Toast.LENGTH_SHORT).show();
                else if (petList.size() == 0)
                    Toast.makeText(getActivity(),"pet cannot be empty",Toast.LENGTH_SHORT).show();
                else if (transport == null)
                    Toast.makeText(getActivity(),"transport cannot be empty",Toast.LENGTH_SHORT).show();
            }
        });

        otherBoxChecked(other_pet, other_pet_text);
        otherBoxChecked(other_hobbies, other_hobbies_text);
        otherBoxChecked(other_description, other_description_text);
        otherBoxChecked(other_home, other_home_text);

        return question1;
    }

    public void initView()
    {
        age_30 = question1.findViewById(R.id.age_30_34);
        age_35 = question1.findViewById(R.id.age_35_39);
        age_40 = question1.findViewById(R.id.age_40_44);
        age_45up = question1.findViewById(R.id.age_45up);
        radioGroup_gender = question1.findViewById(R.id.radioGroup_gender);
        radioGroup_transport = question1.findViewById(R.id.radioGroup_transport);
        radioGroup_age1 = question1.findViewById(R.id.radioGroup_age1);
        radioGroup_age2 = question1.findViewById(R.id.radioGroup_age2);
        radioGroup_restrictions = question1.findViewById(R.id.radioGroup_restrictions);
        next = question1.findViewById(R.id.next_ques);
        dog = question1.findViewById(R.id.dog);
        cat = question1.findViewById(R.id.cat);
        other_pet = question1.findViewById(R.id.other_pet);
        other_pet_text = question1.findViewById(R.id.other_pet_text);
        reading = question1.findViewById(R.id.reading);
        cooking = question1.findViewById(R.id.cooking_baking);
        music = question1.findViewById(R.id.music);
        collecting = question1.findViewById(R.id.collecting);
        sport = question1.findViewById(R.id.sports_games);
        other_hobbies = question1.findViewById(R.id.other_hobbies);
        other_hobbies_text = question1.findViewById(R.id.other_hobbies_text);
        other_restrictions_text = question1.findViewById(R.id.other_restrictions_text);
        adventure = question1.findViewById(R.id.adventure_lover);
        health = question1.findViewById(R.id.health_conscious);
        tech = question1.findViewById(R.id.tech_savy);
        art = question1.findViewById(R.id.arts);
        indoorsy = question1.findViewById(R.id.indoorsy);
        other_description = question1.findViewById(R.id.describe_other);
        other_description_text = question1.findViewById(R.id.type_description);
        swimming = question1.findViewById(R.id.swimming_pool);
        sport_court = question1.findViewById(R.id.sports_court);
        videoGame = question1.findViewById(R.id.video_games);
        barbeque = question1.findViewById(R.id.barbeque);
        games = question1.findViewById(R.id.games_puzzles);
        other_home = question1.findViewById(R.id.home_other);
        other_home_text = question1.findViewById(R.id.other_home_text);

//        listener1(dog);
//        listener1(cat);
    }

//    public void listener1(final CheckBox check)
//    {
//        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
//            {
//                if (isChecked)
//                {
//                    //selectedItemCounter1++;
//                    petList.add(buttonView.getText().toString());
//                }
//                else
//                {
//                    //selectedItemCounter1--;
//                    for (String ele: petList)
//                    {
//                        if (ele.equals(buttonView.getText().toString()))
//                            petList.remove(ele);            //if unchecked, remove from the list
//                    }
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

    public void recordCheckBoxPet()
    {
        petList = new HashSet();
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

    public void recordCheckBoxDescription()
    {
        descriptionList = new HashSet();
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
        if (other_hobbies.isChecked())
        {
            if (isValid(other_description_text.getText().toString()))
                descriptionList.add(other_description_text.getText().toString().trim());
        }
    }

    public void recordCheckBoxHobbies()
    {
        hobbiesList = new HashSet();
        if (reading.isChecked())
            hobbiesList.add("reading books");
        if (cooking.isChecked())
            hobbiesList.add("cooking / baking");
        if (music.isChecked())
            hobbiesList.add("music");
        if (collecting.isChecked())
            hobbiesList.add("collecting");
        if (sport.isChecked())
            hobbiesList.add("sports / games");
        if (other_hobbies.isChecked())
        {
            if (isValid(other_hobbies_text.getText().toString()))
                hobbiesList.add(other_hobbies_text.getText().toString().trim());
        }
    }

    public void recordCheckBoxHome()
    {
        homeList = new HashSet();
        if (swimming.isChecked())
            homeList.add("swimming pool");
        if (sport_court.isChecked())
            homeList.add("sports court");
        if (videoGame.isChecked())
            homeList.add("video games");
        if (barbeque.isChecked())
            homeList.add("barbeque grill");
        if (games.isChecked())
            homeList.add("games & puzzles");
        if (other_home.isChecked())
        {
            if (isValid(other_home_text.getText().toString()))
                homeList.add(other_home_text.getText().toString().trim());
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
