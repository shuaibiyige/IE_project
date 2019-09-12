package com.example.ie_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Response;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity
{
    private Button signIn;
    private TextView signUp, hello, signIn_text, forget;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();

        signIn = (Button) findViewById(R.id.sign_in);
        signUp = (TextView) findViewById(R.id.sign_up);
        email = (EditText) findViewById(R.id.email_sign_in);
        password = (EditText) findViewById(R.id.password_sign_in);
        forget = (TextView) findViewById(R.id.forget);
        hello = (TextView) findViewById(R.id.hello);
        signIn_text = (TextView) findViewById(R.id.signin_text);

        int[] list = size();
        int width = list[0];
        int height = list[1];

        hello.setTextSize((height / hello.getTextSize()) * 25);
        signIn_text.setTextSize(height / signIn_text.getTextSize() * 2);
        forget.setTextSize(height / forget.getTextSize());

        signIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                if (checkEmail(email.getText().toString()) &&  password.getText().toString().trim().length() != 0)
//                {
//                    String user_email = email.getText().toString();
//                    String ueser_password = password.getText().toString();
//
//                    // save data
//
//
//
//
////                    Intent intent = new Intent(Login.this, MainActivity.class);
////                    startActivity(intent);
//                }
//                else        // not valid
//                {
//                    if (!checkEmail(email.getText().toString()))
//                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
//                    else if (password.getText().toString().trim().length() == 0)
//                        Toast.makeText(getApplicationContext(), "Password can not be empty", Toast.LENGTH_SHORT).show();
//                }
//                Intent intent = new Intent(Login.this, MainActivity.class);
//                startActivity(intent);
                loginRestAsyncTask getPassword = new loginRestAsyncTask();
                getPassword.execute();


            }
        });

        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {



            }
        });






        //basicReadWrite();

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

    public int[] size()
    {
        int[] list = new int[2];
        WindowManager wm = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;         // 屏幕宽度（像素）
        int height = dm.heightPixels;       // 屏幕高度（像素）
        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;     // 屏幕密度dpi（120 / 160 / 240）
        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
        int screenHeight = (int) (height / density);// 屏幕高度(dp)
        list[0] = screenWidth;
        list[1] = screenHeight;
        return list;
    }

    public void basicReadWrite()
    {
        // [START write_message]
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        String n= myRef.push().getKey();

        User user = new User("jj", 2);

        myRef.child("User").child(n).setValue(user);
        // [END write_message]

        // [START read_message]
        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d("a", "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("a", "Failed to read value.", error.toException());
//            }
//        });
        // [END read_message]
    }

    private class loginRestAsyncTask extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground (String...params)
        {

            String id = databaseConnection.login();
            //System.out.println(id);
            return null;
        }


    }
}
