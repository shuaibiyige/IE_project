package com.example.ie_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity implements View.OnClickListener
{
    private Button questionnaire, journey, changePassword, back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        questionnaire = findViewById(R.id.setting_questionnaire);
        journey = findViewById(R.id.setting_journey);
        changePassword = findViewById(R.id.setting_change_password);
        back = findViewById(R.id.setting_back);

        questionnaire.setOnClickListener(this);
        journey.setOnClickListener(this);
        changePassword.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.setting_questionnaire:
                Intent intent = new Intent(getApplicationContext(), Questionnaire.class);         // go to questionnaire page
                startActivity(intent);
                break;
            case R.id.setting_journey:



                break;
            case R.id.setting_change_password:



                break;
            case R.id.setting_back:
                Intent anotherIntent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(anotherIntent);
                break;
             default:
                break;
        }
    }
}
