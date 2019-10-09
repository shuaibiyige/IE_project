package com.example.ie_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class JourneyLearnMore extends AppCompatActivity implements View.OnClickListener
{
    private Button cohesion, conflict,identification,rejection,autonomy,back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_learn_more);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Animation ani1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.register_anim);
        Animation ani2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashboard_image);

        cohesion = findViewById(R.id.journey_cohesion);
        conflict = findViewById(R.id.journey_conflict);
        identification = findViewById(R.id.journey_identification);
        rejection = findViewById(R.id.journey_rejection);
        autonomy = findViewById(R.id.journey_autonomy);
        back = findViewById(R.id.journey_learn_more_back);

        cohesion.setAnimation(ani2);
        conflict.setAnimation(ani2);
        identification.setAnimation(ani2);
        rejection.setAnimation(ani2);
        autonomy.setAnimation(ani2);
        back.setAnimation(ani2);

        cohesion.setOnClickListener(this);
        conflict.setOnClickListener(this);
        identification.setOnClickListener(this);
        rejection.setOnClickListener(this);
        autonomy.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.journey_cohesion:
                Intent intent = new Intent(getApplicationContext(), JourneyCohesion.class);         // go to questionnaire page
                startActivity(intent);
                break;
            case R.id.journey_conflict:
                Intent intent2 = new Intent(getApplicationContext(), JourneyConflict.class);     // go to journey page
                startActivity(intent2);
                break;
            case R.id.journey_identification:
                Intent intent3 = new Intent(getApplicationContext(), JourneyIdentification.class);      // back to main page
                startActivity(intent3);
                break;
            case R.id.journey_rejection:
                Intent intent4 = new Intent(getApplicationContext(), JourneyRejection.class);     // go to journey page
                startActivity(intent4);
                break;
            case R.id.journey_autonomy:
                Intent intent5 = new Intent(getApplicationContext(), JourneyAutonomy.class);     // go to journey page
                startActivity(intent5);
                break;
            case R.id.journey_learn_more_back:
                Intent intent6 = new Intent(getApplicationContext(), JourneySummary.class);      // back to main page
                startActivity(intent6);
                break;
            default:
                break;
        }
    }
}
