package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.roughike.swipeselector.SwipeItem;
import com.roughike.swipeselector.SwipeSelector;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Dashboard extends Fragment
{
    View dashboard;
    private ImageView setting, addEvent, review, journey;
    private TextView welcome;
    private LinearLayout linearLayout;
    private RequestQueue requestQueue;
    private SwipeSelector upcomingSwipeSelector, completedSwipeSelector;
    private List<Activity> upcoming, completed;
    private int user_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        dashboard = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Animation ani1 = AnimationUtils.loadAnimation(getActivity(), R.anim.register_anim);
        Animation ani2 = AnimationUtils.loadAnimation(getActivity(), R.anim.dashboard_image);

        requestQueue = Volley.newRequestQueue(getActivity());
        upcoming = new ArrayList<>();
        completed = new ArrayList<>();

        welcome = (TextView) dashboard.findViewById(R.id.welcome_name);
        addEvent = (ImageView) dashboard.findViewById(R.id.schedule_add_event);
        review = (ImageView) dashboard.findViewById(R.id.schedule_review);
        journey = (ImageView) dashboard.findViewById(R.id.schedule_journey);
        setting = (ImageView) dashboard.findViewById(R.id.dashboard_setting);
        upcomingSwipeSelector = (SwipeSelector) dashboard.findViewById(R.id.upcomingSelector);
        completedSwipeSelector = (SwipeSelector) dashboard.findViewById(R.id.completedSelector);

        addEvent.startAnimation(ani2);
        review.startAnimation(ani2);
        journey.startAnimation(ani2);

        //swipeSelector(upcomingSwipeSelector);
        //swipeSelector(completedSwipeSelector);

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), Schedule.class);           // go to schedule page
                startActivity(intent);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), Settings.class);           // go to setting page
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String user_name = sharedPreferences.getString("user_name", "");
        user_id = sharedPreferences.getInt("user_id", 0);
        //int isQuestionnaire = sharedPreferences.getInt("isQuestionnaire", 0);
        //int isSchedule = sharedPreferences.getInt("isSchedule", 0);

        welcome.setText("Hey, " + user_name);

//        if (isQuestionnaire == 1)                     // questionnaire is done
//            tick_ques.setVisibility(View.VISIBLE);    // show the tick
//        if (isSchedule == 1)
//            tick_schedule.setVisibility(View.VISIBLE);

//
//        questionnaire.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent anotherIntent = new Intent(getActivity(), Questionnaire.class);         // go to questionnaire page
//                startActivity(anotherIntent);
//            }
//        });

//
//        feedback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Feedback.class);           // go to feedback page
//                startActivity(intent);
//            }
//        });

        return dashboard;
    }

    public void swipeSelector(SwipeSelector swipeSelector, List<Activity> activities)
    {
//        swipeSelector.setItems(
//            new SwipeItem(0, "Slide one", "Description for slide one."),
//            new SwipeItem(1, "Slide two", "Description for slide two."),
//            new SwipeItem(2, "Slide three", "Description for slide three.")
//        );

        for (Activity activity : activities)
        {
            swipeSelector.setItems(new SwipeItem(0, activity.getDate(), activity.getName()));
        }
    }

    public void getUpcoming()
    {
        String connectUrl = "http://ec2-13-236-44-7.ap-southeast-2.compute.amazonaws.com/letosaid/activityAdvice.php";

        com.android.volley.Response.Listener<String> listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String s)
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(s);
                    //retCode = jsonObject.getInt("success");
                    //String activity_date = jsonObject.getString();
                    //String activity_title = jsonObject.getString();
                    //Activity activity = new Activity(activity_date, activity_title);
                }
                catch (JSONException e)
                {
                    Toast.makeText(getActivity(),"Network is unavailable", Toast.LENGTH_SHORT).show();
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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, connectUrl, listener, errorListener)
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> map = new HashMap<>();
                map.put("user_id", String.valueOf(user_id));

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
