package com.example.ie_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class Settings extends AppCompatActivity implements View.OnClickListener
{
    private Button questionnaire, journey, back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Animation ani1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.register_anim);
        Animation ani2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashboard_image);

        questionnaire = findViewById(R.id.setting_questionnaire);
        journey = findViewById(R.id.setting_journey);
        back = findViewById(R.id.setting_back);

        questionnaire.setAnimation(ani2);
        journey.setAnimation(ani2);
        back.setAnimation(ani2);

        questionnaire.setOnClickListener(this);
        journey.setOnClickListener(this);
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
                Intent intent2 = new Intent(getApplicationContext(), JourneyQuestionnaire.class);     // go to journey page
                startActivity(intent2);
                break;
            case R.id.setting_back:
                Intent anotherIntent = new Intent(getApplicationContext(), MainActivity.class);      // back to main page
                startActivity(anotherIntent);
                break;
             default:
                break;
        }
    }
}
