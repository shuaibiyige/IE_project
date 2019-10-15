package com.example.ie_project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;

public class Settings extends AppCompatActivity implements View.OnClickListener
{
    private Button questionnaire, journey, viewTutorial, aboutUs, back;
    private boolean isSettingNew;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //Animation ani1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.register_anim);
        Animation ani2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dashboard_image);

        questionnaire = findViewById(R.id.setting_questionnaire);
        journey = findViewById(R.id.setting_journey);
        viewTutorial = findViewById(R.id.setting_view_tutorial);
        aboutUs = findViewById(R.id.setting_about_us);

        back = findViewById(R.id.setting_back);

        questionnaire.setAnimation(ani2);
        journey.setAnimation(ani2);
        viewTutorial.setAnimation(ani2);
        aboutUs.setAnimation(ani2);
        back.setAnimation(ani2);

        questionnaire.setOnClickListener(this);
        viewTutorial.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        journey.setOnClickListener(this);
        back.setOnClickListener(this);
        isSettingNew = false;
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        isSettingNew = sharedPreferences.getBoolean("isSettingNew", false);        // if the user is new

        if(isSettingNew == true)
        {
            final TapTargetSequence sequence = new TapTargetSequence(this)
                    .targets(
                            TapTarget.forView(findViewById(R.id.setting_questionnaire), "Record details about you and your child", "Answer a quick questionnaire about you and your child to generate custom suggestions")
                                    .tintTarget(false)
                                    .outerCircleColor(R.color.tutorial_color_1)
                                    .targetRadius(140)
                                    .id(1),
                            TapTarget.forView(findViewById(R.id.setting_journey), "Assess your relationship quality", "Take the quick survey to assess the quality of your parent-child relationship")
                                    .tintTarget(false)
                                    .outerCircleColor(R.color.tutorial_color_1)
                                    .targetRadius(140)
                                    .id(2),
                            TapTarget.forView(findViewById(R.id.setting_view_tutorial), "Revisit the tutorial", "Click on this button to go through the tutorial again")
                                    .tintTarget(false)
                                    .outerCircleColor(R.color.tutorial_color_1)
                                    .targetRadius(140)
                                    .id(3)
                    );
            sequence.start();
            SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
            editor.putBoolean("isSettingNew", false);
            editor.apply();
        }

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
            case R.id.setting_view_tutorial:
                SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
                editor.putBoolean("isSettingNew", true);
                editor.putBoolean("isNew", true);
                editor.putBoolean("isJourneyNew", true);
                editor.apply();
                Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);     // go to journey page
                startActivity(intent3);
                break;
            case R.id.setting_back:
                Intent anotherIntent = new Intent(getApplicationContext(), MainActivity.class);      // back to main page
                startActivity(anotherIntent);
                break;
            case R.id.setting_about_us:
                Uri uri = Uri.parse("https://www.letosaid.cf");
                Intent intent4 = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent4);
            default:
                break;
        }
    }
}
