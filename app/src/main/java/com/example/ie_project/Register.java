package com.example.ie_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    private EditText email, firstName, lastName, password;

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

        email.startAnimation(ani);
        firstName.startAnimation(ani);
        lastName.startAnimation(ani);
        password.startAnimation(ani);

    }
}
