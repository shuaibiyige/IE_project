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
import android.widget.Button;
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
import com.roughike.swipeselector.OnSwipeItemSelectedListener;
import com.roughike.swipeselector.SwipeItem;
import com.roughike.swipeselector.SwipeSelector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Dashboard extends Fragment
{
    View dashboard;
    private ImageView setting, addEvent, review, journey;
    private TextView welcome;
    //private LinearLayout linearLayout;
    private RequestQueue requestQueue;
    private SwipeSelector upcomingSwipeSelector, completedSwipeSelector;
    private List<Activity> upcoming, completed;
    private int user_id, completed_ts;
    private Button goToFeedback;
    private String completed_name, completed_date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        dashboard = inflater.inflate(R.layout.fragment_dashboard, container, false);

        requestQueue = Volley.newRequestQueue(getActivity());
        upcoming = new ArrayList<>();
        completed = new ArrayList<>();
        completed_name = "";
        completed_date = "";
        completed_ts = 0;

        welcome = (TextView) dashboard.findViewById(R.id.welcome_name);
        addEvent = (ImageView) dashboard.findViewById(R.id.schedule_add_event);
        review = (ImageView) dashboard.findViewById(R.id.schedule_review);
        journey = (ImageView) dashboard.findViewById(R.id.schedule_journey);
        setting = (ImageView) dashboard.findViewById(R.id.dashboard_setting);
        upcomingSwipeSelector = (SwipeSelector) dashboard.findViewById(R.id.upcomingSelector);
        completedSwipeSelector = (SwipeSelector) dashboard.findViewById(R.id.completedSelector);
        goToFeedback = dashboard.findViewById(R.id.dashboard_feedback);

        getBothActivity();

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

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), History.class);            // go to history page
                startActivity(intent);
            }
        });

        journey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        goToFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Feedback.class);           // go to feedback page

                SwipeItem selectedItem = completedSwipeSelector.getSelectedItem();
                completed_ts = (int) selectedItem.value;
                completed_name = selectedItem.description;
                completed_date = selectedItem.title;

                intent.putExtra("activity_ts", completed_ts);
                intent.putExtra("activity_name", completed_name);
                intent.putExtra("activity_date", completed_date);

                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String user_name = sharedPreferences.getString("user_name", "");
        user_id = sharedPreferences.getInt("user_id", 0);

        welcome.setText("Hey, " + user_name);

        return dashboard;
    }

    public void swipeSelector(SwipeSelector swipeSelector, List<Activity> activities)
    {
        if (activities.size() == 0)
        {
            swipeSelector.setItems(new SwipeItem(0, "no activity found", ""));
        }
        else
         {
             SwipeItem[] swipeItems = new SwipeItem[activities.size()];
             for (int i = 0; i < activities.size(); i++)
             {
                 SwipeItem item = new SwipeItem(activities.get(i).getTs(), activities.get(i).getDate(), activities.get(i).getName());
                 swipeItems[i] = item;
             }
             swipeSelector.setItems(swipeItems);
        }
    }

    public void getBothActivity()
    {
        String connectUrl = "http://ec2-13-236-44-7.ap-southeast-2.compute.amazonaws.com/letosaid/whetherFinish.php";

        com.android.volley.Response.Listener<String> listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String s)
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(s);
                    int retCode = jsonObject.getInt("success");

                    upcoming.clear();
                    completed.clear();

                    JSONArray upcoming_activity = jsonObject.getJSONArray("unFinishActivity");
                    JSONArray completed_activity = jsonObject.getJSONArray("finishActivity");

                    for (int i = 0; i < upcoming_activity.length(); i++)
                    {
                        JSONObject object = upcoming_activity.getJSONObject(i);
                        String upcoming_id = object.getString("recom_id");
                        String upcoming_date = object.getString("schedule_date");
                        String upcoming_title = object.getString("title");
                        String upcoming_ts = object.getString("ts_id");
                        Activity activity = new Activity(upcoming_date, upcoming_title, Integer.valueOf(upcoming_id), Integer.valueOf(upcoming_ts));
                        upcoming.add(activity);
                    }

                    for (int i = 0; i < completed_activity.length(); i++)
                    {
                        JSONObject object = completed_activity.getJSONObject(i);
                        String completed_id = object.getString("recom_id");
                        String completed_date = object.getString("schedule_date");
                        String completed_title = object.getString("title");
                        String completed_ts = object.getString("ts_id");
                        Activity activity = new Activity(completed_date, completed_title, Integer.valueOf(completed_id), Integer.valueOf(completed_ts));
                        completed.add(activity);
                    }

                    swipeSelector(upcomingSwipeSelector, upcoming);
                    swipeSelector(completedSwipeSelector, completed);
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
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
                Date date = new Date(System.currentTimeMillis());
                String time_date = simpleDateFormat1.format(date);
                String time_time = simpleDateFormat2.format(date);

                Map<String, String> map = new HashMap<>();
                map.put("user_id", String.valueOf(user_id));
                map.put("system_date", time_date);
                map.put("system_time", time_time);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
