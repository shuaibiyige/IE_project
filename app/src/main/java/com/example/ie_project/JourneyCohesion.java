package com.example.ie_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class JourneyCohesion extends AppCompatActivity implements View.OnClickListener {
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journey_cohesion);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();




        back = findViewById(R.id.journey_cohesion_back);

        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.journey_cohesion:
                Intent intent = new Intent(getApplicationContext(), JourneyLearnMore.class);         // go to questionnaire page
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
