package com.example.ie_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import com.kyle.calendarprovider.calendar.CalendarEvent;
import com.kyle.calendarprovider.calendar.CalendarProviderManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Feedback extends AppCompatActivity
{
    private ImageButton done, notDone;
    private LinearLayout layout;
    private RatingBar ratingBar;
    private TextView activity_name, activity_time;
    private String completed_name, completed_date;
    private int stars, completed_ts;
    private Button submit, done_back;
    private EditText learnt;
    private RequestQueue requestQueue;
    private boolean newFeedback = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getSupportActionBar().hide();

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        done = findViewById(R.id.done_activity);
        notDone = findViewById(R.id.not_done_activity);
        layout = findViewById(R.id.feedback_yes_layout);
        ratingBar = findViewById(R.id.feedback_star);
        activity_name = findViewById(R.id.feedback_name);
        activity_time = findViewById(R.id.feedback_time);
        submit = findViewById(R.id.submit_feedback);
        done_back = findViewById(R.id.feedback_done_back);
        learnt = findViewById(R.id.feedback_editText);

        Intent intent = getIntent();
        completed_name = intent.getStringExtra("activity_name");
        completed_ts = intent.getIntExtra("activity_ts", 0);
        completed_date = intent.getStringExtra("activity_date");
        stars = 0;

        activity_name.setText(completed_name);
        activity_time.setText(completed_date);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (layout.getVisibility() == View.INVISIBLE)
                    layout.setVisibility(View.VISIBLE);
            }
        });

        notDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (layout.getVisibility() == View.VISIBLE)
                    layout.setVisibility(View.INVISIBLE);
                alertDialog();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stars = (int)ratingBar.getRating();
                String learntText = learnt.getText().toString();
                if (learntText.trim().length() != 0)       // cannot be empty
                {
                    transmitActivity(String.valueOf(completed_ts), learntText.trim(), String.valueOf(stars), "null");
                }
                else {
                    Toast.makeText(getApplicationContext(), "what have you learnt?", Toast.LENGTH_SHORT).show();
                }

                SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
                editor.putBoolean("newFeedback", true);
                editor.apply();
            }
        });

        done_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);      // from left to right
            }
        });
    }

    public void alertDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(this, R.layout.feedback_dialog, null);
        dialog.setView(dialogView);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());        //erase white background
        dialog.show();

        Button save = dialogView.findViewById(R.id.feedback_save_dialog);
        Button dialog_back = dialogView.findViewById(R.id.feedback_notDone_back);
        final EditText whyNot = dialogView.findViewById(R.id.feedback_edit_dialog);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String feedback = whyNot.getText().toString();
                if (feedback.trim().length() != 0)       // cannot be empty
                {
                    transmitActivity(String.valueOf(completed_ts), "null", "null", whyNot.getText().toString().trim());

                    dialog.dismiss();

                    Intent anotherIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(anotherIntent);
                }
                else
                    Toast.makeText(getApplicationContext(), "you haven't input anything", Toast.LENGTH_SHORT).show();
            }
        });

        dialog_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });
    }

    public void transmitActivity(final String ts_id, final String description, final String rating, final String not_done_reason)
    {
        String connectUrl = "http://ec2-13-236-44-7.ap-southeast-2.compute.amazonaws.com/letosaid/rating.php";

        com.android.volley.Response.Listener<String> listener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String s)
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(s);
                    int retCode = jsonObject.getInt("success");

                    if (retCode == 1)
                    {
                        Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                        Intent anotherIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(anotherIntent);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Unknown error", Toast.LENGTH_SHORT).show();
                }
                catch (JSONException e)
                {
                    Toast.makeText(getApplicationContext(),"Network is unavailable", Toast.LENGTH_SHORT).show();
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
                map.put("ts_id", ts_id);
                map.put("description", description);
                map.put("rating", rating);
                map.put("not_done_reason", not_done_reason);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}
