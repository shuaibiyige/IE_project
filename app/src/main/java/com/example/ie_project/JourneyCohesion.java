package com.example.ie_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class JourneyCohesion extends AppCompatActivity implements View.OnClickListener
{
    private Button back;
    private TextView textView;
    private final static String HTML_TEXT = "<p><strong>What is cohesion?</strong></p>\n" +
            "<p>Cohesion evaluates what is perceived as parental emotional warmth and bonding, intimacy, mutual support, care and reliability. Cohesion is deemed to be a core construct of the parent-child relationship.</p>\n" +
            "<p><strong>What to do to improve cohesion?</strong></p>\n" +
            "<p>Embed a sense of stability with each other by means of simple practices. Consistent implementation of such practices can induce better quality bonding and help the child open-up more frequently and/or willingly.</p>\n" +
            "<p><strong>Examples of practices that can improve cohesion:</strong></p>\n" +
            "<p>1. Having regular meals together</p>\n" +
            "<p>2. Establishing daily/weekly/monthly family rituals such as movie nights, spa evenings, biking/walking exploration tours etc...</p>\n" +
            "<p>3. Actively expressing interest in your child’s life and volunteering to share information about yours to illustrate genuine willingness to be part of each other’s life (without judgement)</p>";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journey_cohesion);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        textView = findViewById(R.id.journey_cohesion_text);
        back = findViewById(R.id.journey_cohesion_back);

        textView.setText(Html.fromHtml(HTML_TEXT));

        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.journey_cohesion_back:
                finish();
                break;

            default:
                break;
        }
    }
}
