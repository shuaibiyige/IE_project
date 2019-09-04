package com.example.ie_project;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private EditText email, name, password, answer;
    private Button submit;
    private Spinner security;
    private TextView signup_text, email_text, name_text, password_text, confirm_pass_text, security_ques_text, answer_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getSupportActionBar().hide();

        email = findViewById(R.id.new_email);
        name = findViewById(R.id.new_name);
        password = findViewById(R.id.new_password);
        security = findViewById(R.id.new_security);
        submit = findViewById(R.id.register_submit);
        answer = findViewById(R.id.new_answer);
        signup_text = findViewById(R.id.signup_text);
        email_text = findViewById(R.id.email_text);
        name_text = findViewById(R.id.name_text);
        password_text = findViewById(R.id.password_text);
        confirm_pass_text = findViewById(R.id.confirm_pass_text);
        security_ques_text = findViewById(R.id.security_ques_text);
        answer_text = findViewById(R.id.answer_text);


        if (Build.VERSION.SDK_INT < 26)
        {
            signup_text.setTextSize(84/signup_text.getTextSize() * 70);
            email_text.setTextSize(50/email_text.getTextSize() * 14);
            name_text.setTextSize(50/name_text.getTextSize() * 14);
            password_text.setTextSize(50/password_text.getTextSize() * 14);
            confirm_pass_text.setTextSize(50/confirm_pass_text.getTextSize() * 14);
            security_ques_text.setTextSize(50/security_ques_text.getTextSize() * 14);
            answer_text.setTextSize(50/answer_text.getTextSize() * 14);
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//                String user_email = email.getText().toString();
//                String user_name = name.getText().toString();
//                String user_password = password.getText().toString();
//
//                if (user_name.trim().length() != 0 && user_password.trim().length() != 0 && checkEmail(user_email))
//                {
//
//                    // save data
//
//
//                    Intent intent = new Intent(Register.this, MainActivity.class);
//                    startActivity(intent);
//                }
//                else
//                {
//                    if (!checkEmail(email.getText().toString()))
//                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
//                    if (user_name.trim().length() == 0)
//                        Toast.makeText(getApplicationContext(), "Last name can not be empty", Toast.LENGTH_SHORT).show();
//                    if (user_password.trim().length() == 0)
//                        Toast.makeText(getApplicationContext(), "Password can not be empty", Toast.LENGTH_SHORT).show();
//                }
                Intent intent = new Intent(Register.this, MainActivity.class);
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
}
