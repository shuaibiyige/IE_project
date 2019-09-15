package com.example.ie_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private EditText email, name, password, confirm_pwd, answer;
    private Button submit;
    private Spinner security_question;
    private int security_id;
    private RequestQueue requestQueue;
    //private TextView signup_text, email_text, name_text, password_text, confirm_pass_text, security_ques_text, answer_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getSupportActionBar().hide();

        email = findViewById(R.id.new_email);
        name = findViewById(R.id.new_name);
        password = findViewById(R.id.new_password);
        confirm_pwd = findViewById(R.id.new_confirm_password);
        security_question = findViewById(R.id.new_security);
        submit = findViewById(R.id.register_submit);
        answer = findViewById(R.id.new_answer);

        requestQueue = Volley.newRequestQueue(this);
        String[] security_question_list = getResources().getStringArray(R.array.security_question);


        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, security_question_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        security_question.setAdapter(adapter);

        security_question.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position != 0)
                    security_id = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String user_email = email.getText().toString();
                String user_name = name.getText().toString();
                String user_password = password.getText().toString();
                String user_password_confirm = confirm_pwd.getText().toString();
                String user_answer = answer.getText().toString();
                List<String> user_info = new ArrayList<>();
                user_info.add(user_email);
                user_info.add(user_name);
                user_info.add(user_password);
                //user_info.add(user_password_confirm);
                user_info.add(String.valueOf(security_id));
                user_info.add(user_answer);


                if (user_name.trim().length() != 0 && user_password.trim().length() != 0 && checkEmail(user_email) && user_password_confirm.equals(user_password) && user_answer.trim().length() != 0)
                {
                    RegisterAsyncTask registerAsyncTask = new RegisterAsyncTask();
                    registerAsyncTask.execute(user_info);


//                    Intent intent = new Intent(Register.this, MainActivity.class);
//                    startActivity(intent);
                }
                else
                {
                    if (!checkEmail(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                    else if (user_name.trim().length() == 0)
                        Toast.makeText(getApplicationContext(), "Last name can not be empty", Toast.LENGTH_SHORT).show();
                    else if (user_password.trim().length() == 0)
                        Toast.makeText(getApplicationContext(), "Password can not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkEmail(String email)
    {
        boolean flag;
        try
        {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }
        catch (Exception e)
        {
            flag = false;
        }
        return flag;
    }

    private class RegisterAsyncTask extends AsyncTask<List<String>, Void, Void>
    {
        @Override
        protected Void doInBackground (final List<String>...params)
        {
            String connectUrl = "http://ec2-13-236-44-7.ap-southeast-2.compute.amazonaws.com/letosaid/regist.php";

            com.android.volley.Response.Listener<String> listener = new Response.Listener<String>()
            {
                @Override
                public void onResponse(String s)
                {
                    String TAG = "LOGIN";
                    Log.e(TAG, s);
                    int retCode = 0;
                    try
                    {
                        JSONObject jsonObject = new JSONObject(s);
                        retCode = jsonObject.getInt("success");
                        Log.d("retCode", retCode+"");
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                    if (retCode == 1)
                    {
                        Intent intent = new Intent(Register.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Register failed",Toast.LENGTH_SHORT).show();
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
                    map.put("email", params[0].get(0));
                    map.put("name", params[0].get(1));
                    map.put("pwd", params[0].get(2));
                    map.put("sq_id", params[0].get(3));
                    map.put("sq_answer", params[0].get(4));
                    return map;
                }
            };
            requestQueue.add(stringRequest);
            return null;
        }
    }
}
