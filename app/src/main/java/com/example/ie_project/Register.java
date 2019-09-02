package com.example.ie_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private EditText email, firstName, lastName, password, security;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getSupportActionBar().hide();

        Animation ani = AnimationUtils.loadAnimation(this, R.anim.register_anim);
        email = findViewById(R.id.new_email);
        firstName = findViewById(R.id.new_firstName);
        lastName = findViewById(R.id.new_lastName);
        password = findViewById(R.id.new_password);
        security = findViewById(R.id.new_security);
        submit = findViewById(R.id.register_submit);

        email.startAnimation(ani);
        firstName.startAnimation(ani);
        lastName.startAnimation(ani);
        password.startAnimation(ani);
        security.startAnimation(ani);
        submit.startAnimation(ani);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String user_email = email.getText().toString();
                String user_firstName = firstName.getText().toString();
                String user_lastName = lastName.getText().toString();
                String user_password = password.getText().toString();

                if (user_firstName.trim().length() != 0 && user_lastName.trim().length() != 0 && user_password.trim().length() != 0 && checkEmail(user_email))
                {

                    // save data


                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    if (!checkEmail(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                    if (user_firstName.trim().length() == 0)
                        Toast.makeText(getApplicationContext(), "First name can not be empty", Toast.LENGTH_SHORT).show();
                    if (user_lastName.trim().length() == 0)
                        Toast.makeText(getApplicationContext(), "Last name can not be empty", Toast.LENGTH_SHORT).show();
                    if (user_password.trim().length() == 0)
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
}
