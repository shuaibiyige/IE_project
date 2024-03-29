package com.example.ie_project;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity
{
    private Button signIn;
    private TextView signUp;
    private EditText email;
    private EditText password;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();

        requestQueue = Volley.newRequestQueue(this);

        signIn = (Button) findViewById(R.id.sign_in);
        signUp = (TextView) findViewById(R.id.sign_up);
        email = (EditText) findViewById(R.id.email_sign_in);
        password = (EditText) findViewById(R.id.password_sign_in);
        //hello = (TextView) findViewById(R.id.hello);
        //signIn_text = (TextView) findViewById(R.id.signin_text);

        password.setTransformationMethod(PasswordTransformationMethod.getInstance());    // hide number

//        int[] list = size();
//        int width = list[0];
//        int height = list[1];

//        hello.setTextSize((height / hello.getTextSize()) * 25);
//        signIn_text.setTextSize(height / signIn_text.getTextSize() * 2);

        signIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (checkEmail(email.getText().toString()) &&  password.getText().toString().trim().length() != 0)
                {
                    List<String> identity = new ArrayList<>();
                    String user_email = email.getText().toString();
                    String ueser_password = password.getText().toString();
                    identity.add(user_email);
                    identity.add(ueser_password);

                    LoginRestAsyncTask loginRestAsyncTask = new LoginRestAsyncTask();
                    loginRestAsyncTask.execute(identity);              // get data from database
                }
                else           // not valid
                {
                    if (!checkEmail(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                    else if (password.getText().toString().trim().length() == 0)
                        Toast.makeText(getApplicationContext(), "Password can not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);       // go to signUp page
                startActivity(intent);
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

//    public int[] size()
//    {
//        int[] list = new int[2];
//        WindowManager wm = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics dm = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;         // screen width（pixel）
//        int height = dm.heightPixels;       // screen height（pixel）
//        float density = dm.density;         // screen density（0.75 / 1.0 / 1.5）
//        int densityDpi = dm.densityDpi;     // screen density dpi（120 / 160 / 240）
//        // screen width algorithm:screen width（pixel）/screen density
//        int screenWidth = (int) (width / density);    // screen width(dp)
//        int screenHeight = (int) (height / density);  // screen height(dp)
//        list[0] = screenWidth;
//        list[1] = screenHeight;
//        return list;
//    }

    private class LoginRestAsyncTask extends AsyncTask<List<String>, Void, Void>
    {
        @Override
        protected Void doInBackground (final List<String>...params)
        {
            String connectUrl = "http://ec2-13-236-44-7.ap-southeast-2.compute.amazonaws.com/letosaid/login.php";

            com.android.volley.Response.Listener<String> listener = new Response.Listener<String>()
            {
                @Override
                public void onResponse(String s)
                {
                    int retCode = 0;
                    String user_name = "";
                    int user_id = 0;
                    try
                    {
                        JSONObject jsonObject = new JSONObject(s);
                        retCode = jsonObject.getInt("success");

                        if (retCode == 1)
                        {
                            user_name = jsonObject.getString("userName");
                            user_id = jsonObject.getInt("userId");

                            SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
                            editor.putString("user_name", user_name);
                            editor.putInt("user_id", user_id);
                            editor.putBoolean("isNew", false);             // not a new user
                            editor.apply();
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"User not found", Toast.LENGTH_SHORT).show();
                        }
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

            StringRequest stringRequest = new StringRequest(Request.Method.POST, connectUrl, listener, errorListener)
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    Map<String, String> map = new HashMap<>();
                    map.put("email", params[0].get(0));
                    map.put("pwd", params[0].get(1));
                    return map;
                }
            };
            requestQueue.add(stringRequest);
            return null;
        }
    }
}
