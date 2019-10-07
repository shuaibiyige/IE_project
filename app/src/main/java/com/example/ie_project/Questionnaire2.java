package com.example.ie_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;


public class Questionnaire2 extends Fragment
{
    View question2;
    private Button submit;
    private RadioGroup radioGroup_gender, radioGroup, radioGroup_restrictions;
    private EditText child_name_edit, other_restrictions_text, other_hobbies_text, other_description_text;
    private CheckBox reading, dancing, music, sport, video_game, other_hobbies, adventure, health, tech, art, indoorsy, other_description;
    private String name, restriction, gender;
    private int age;
    private Set<String> hobbiesList, descriptionList;
    private RequestQueue requestQueue;
    private int user_id;
    private String dateTime;
    private HorizontalScrollView scrollView;
    private Button swipe_right, swipe_left;
    private int offset;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question2 = inflater.inflate(R.layout.questionnaire2_layout, container, false);
        requestQueue = Volley.newRequestQueue(getActivity());

        initView();
        offset = 0;

        swipe_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                scrollView.post(new Runnable() {
                    @Override
                    public void run()
                    {
                        if (offset < 2500) {
                            offset = offset + 930;
                            scrollView.smoothScrollTo(offset, 0);
                        }
                    }});
            }
        });

        swipe_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                scrollView.post(new Runnable() {
                    @Override
                    public void run()
                    {
                        if (offset > 0)
                        {
                            offset = offset - 930;
                            scrollView.smoothScrollTo(offset, 0);
                        }
                    }});
            }
        });

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
            public void onClick(View v)
            {
                recordCheckBoxHobbies();
                recordCheckBoxDescription();

                if (isValid(child_name_edit.getText().toString()) && age != 0 && hobbiesList.size() != 0 && descriptionList.size() != 0)
                {
                    name = child_name_edit.getText().toString();

                    String hobbies = "";
                    String descriptions = "";

                    for (String ele : hobbiesList) {
                        if (!hobbies.equals(""))
                            hobbies = hobbies + ", " + ele;
                        else
                            hobbies = hobbies + ", " + ele;
                    }

                    for (String ele : descriptionList) {
                        if (!descriptions.equals(""))
                            descriptions = descriptions + ", " + ele;
                        else
                            descriptions = ele;
                    }

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                    user_id = sharedPreferences.getInt("user_id", 0);
                    String user_gender = sharedPreferences.getString("user_gender", "unknown");
                    String user_age = sharedPreferences.getString("user_age", "");
                    String user_pet = sharedPreferences.getString("user_pet", "");
                    String user_restriction = sharedPreferences.getString("user_restriction", "");
                    String user_hobbies = sharedPreferences.getString("user_hobbies", "");
                    String user_description = sharedPreferences.getString("user_description", "");
                    String user_transport = sharedPreferences.getString("user_transport", "");
                    String user_home = sharedPreferences.getString("user_home", "");

                    List<String> list = new ArrayList<>();
                    list.add(user_age);                // 1
                    list.add(user_gender);             // 2
                    list.add(user_restriction);        // 3
                    list.add(user_hobbies);            // 4
                    list.add(user_description);        // 5
                    list.add(name);                    // 6
                    list.add(String.valueOf(age));     // 7
                    list.add(gender);                  // 8
                    list.add(restriction);             // 9
                    list.add(hobbies);                 // 10
                    list.add(descriptions);            // 11
                    list.add(user_transport);          // 12
                    list.add("area");                  // 13
                    list.add(user_pet);                // 14
                    list.add(user_home);               // 15

                    CheckRestAsyncTask checkRestAsyncTask = new CheckRestAsyncTask();
                    checkRestAsyncTask.execute(String.valueOf(user_id));

                    QuestionRestAsyncTask questionRestAsyncTask = new QuestionRestAsyncTask();
                    questionRestAsyncTask.execute(list);
                }
                else if (!isValid(child_name_edit.getText().toString()))
                    Toast.makeText(getActivity(),"name cannot be empty",Toast.LENGTH_SHORT).show();
                else if (age == 0)
                    Toast.makeText(getActivity(),"age cannot be empty",Toast.LENGTH_SHORT).show();
                else if (hobbiesList.size() == 0)
                    Toast.makeText(getActivity()," hobbies cannot be empty",Toast.LENGTH_SHORT).show();
                else if (descriptionList.size() == 0)
                    Toast.makeText(getActivity(),"description cannot be empty",Toast.LENGTH_SHORT).show();
            }
        });

        otherBoxChecked(other_hobbies, other_hobbies_text);
        otherBoxChecked(other_description, other_description_text);

        return question2;
    }

    public void initView()
    {
        gender = "";
        age = 0;
        name = "";
        restriction = "";
        dateTime = "";

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
        scrollView = question2.findViewById(R.id.scrollView_down);
        swipe_right = question2.findViewById(R.id.questionnaire2_swipe_right);
        swipe_left = question2.findViewById(R.id.questionnaire2_swipe_left);

        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date(time);
        dateTime = format.format(d1);
    }

    public boolean isValid(String input)       // check if empty
    {
        if (input.trim().length() != 0)
            return true;
        else
            return false;
    }

    public void recordCheckBoxHobbies()              // add the hobbies to a list
    {
        hobbiesList = new HashSet();
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

    public void recordCheckBoxDescription()             // add the descriptions to a list
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
        if (other_description.isChecked())
        {
            if (isValid(other_description_text.getText().toString()))
                descriptionList.add(other_description_text.getText().toString().trim());
        }
    }

    public void otherBoxChecked(final CheckBox checkBox, final EditText editText)            // if "other" is ticked, show the editText
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

    private class QuestionRestAsyncTask extends AsyncTask<List<String>, Void, Void>
    {
        @Override
        protected Void doInBackground (final List<String>...params)
        {
            String connectUrl = "http://ec2-13-236-44-7.ap-southeast-2.compute.amazonaws.com/letosaid/addUserAnswer.php";

            com.android.volley.Response.Listener<String> listener = new Response.Listener<String>()
            {
                @Override
                public void onResponse(String s)
                {
                    int retCode = 0;
                    try
                    {
                        JSONObject jsonObject = new JSONObject(s);
                        retCode = jsonObject.getInt("success");
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                    if (retCode == 1)
                    {
                        SharedPreferences.Editor editor = getActivity().getSharedPreferences("user", MODE_PRIVATE).edit();
                        editor.putInt("isQuestionnaire", 1);        // 0: not answered, 1: answered
                        editor.apply();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.content_frame, new Successful()).commit();
                    }
                    else {
                        Toast.makeText(getContext(),"Submit failed",Toast.LENGTH_SHORT).show();
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

            StringRequest stringRequest = new StringRequest(Request.Method.POST, connectUrl, listener, errorListener) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    Map<String, String> map = new HashMap<>();
                    map.put("user_id", String.valueOf(user_id));
                    map.put("1", params[0].get(0));
                    map.put("2", params[0].get(1));
                    map.put("3", params[0].get(2));
                    map.put("4", params[0].get(3));
                    map.put("5", params[0].get(4));
                    map.put("6", params[0].get(5));
                    map.put("7", params[0].get(6));
                    map.put("8", params[0].get(7));
                    map.put("9", params[0].get(8));
                    map.put("10", params[0].get(9));
                    map.put("11", params[0].get(10));
                    map.put("12", params[0].get(11));
                    map.put("13", params[0].get(12));
                    map.put("14", params[0].get(13));
                    map.put("15", params[0].get(14));
                    map.put("last_update", dateTime);

                    return map;
                }
            };
            requestQueue.add(stringRequest);
            return null;
        }
    }
    
    private class CheckRestAsyncTask extends AsyncTask<String, Void, Void>      // check if user answered the questionnaire before
    {
        @Override
        protected Void doInBackground (final String...params)
        {
            String connectUrl = "http://ec2-13-236-44-7.ap-southeast-2.compute.amazonaws.com/letosaid/checkUserAnswer.php";

            com.android.volley.Response.Listener<String> listener = new Response.Listener<String>()
            {
                @Override
                public void onResponse(String s)
                {
                    int retCode = 0;
                    try
                    {
                        JSONObject jsonObject = new JSONObject(s);
                        retCode = jsonObject.getInt("success");
                    }
                    catch (JSONException e)
                    {
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

            StringRequest stringRequest = new StringRequest(Request.Method.POST, connectUrl, listener, errorListener) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    Map<String, String> map = new HashMap<>();
                    map.put("user_id", params[0]);

                    return map;
                }
            };
            requestQueue.add(stringRequest);
            return null;
        }
    }
}
