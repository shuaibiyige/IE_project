package com.example.ie_project;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        hello.setTextSize(184/hello.getTextSize() * 70);
        signIn_text.setTextSize(53/signIn_text.getTextSize() * 20);
        forget.setTextSize(40/forget.getTextSize() * 15);

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
//                    Intent intent = new Intent(Login.this, MainActivity.class);
//                    startActivity(intent);
//                }
//                else        // not valid
//                {
//                    if (!checkEmail(email.getText().toString()))
//                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
//                    else if (password.getText().toString().trim().length() == 0)
//                        Toast.makeText(getApplicationContext(), "Password can not be empty", Toast.LENGTH_SHORT).show();
//                }
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);

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

    private int getDensity()
    {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }

}
