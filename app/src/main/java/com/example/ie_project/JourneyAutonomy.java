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

public class JourneyAutonomy extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private TextView textView;
    private final static String HTML_TEXT = "<p><strong>What is autonomy?</strong></p>\n" +
            "<p>Autonomy evaluates how an adolescent is prepared for independent decision making by the parent.\n" +
            "Granting appropriate autonomy and mutual influence between parent and child plays an important \n" +
            "role in fostering trust.</p>\n" +
            "<p><strong>What to do to improve autonomy?</strong></p>\n" +
            "<p>Prioritise the decisions to be made and slowly guide the child through them from the lowest to \n" +
            "highest priority decisions as they enter adolescence. Dissuade comparisons between peers, siblings, \n" +
            "cousins or any others when it comes to granting and exercising autonomy.</p>\n" +
            "<p><strong>Examples of practices that can your approach to autonomy:</strong></p>\n" +
            "<p>1. Prepare the child by discussing probable scenarios, the different viable decisions and the \n" +
            "thought process behind each decision</p>\n" +
            "<p>2. Support the child in the initial few instances in decision making and encourage healthy \n" +
            "exploration of different options before making the final decision</p>\n" +
            "<p>3. Provide positive validation to good decision making</p>" +
            "<p>4. Allow children to learn from their mistakes and provide gentle constructive feedback on \n" +
            "areas of improvement</p>";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journey_autonomy);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        textView = findViewById(R.id.journey_autonomy_text);
        back = findViewById(R.id.journey_autonomy_back);

        textView.setText(Html.fromHtml(HTML_TEXT));

        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.journey_autonomy_back:
                Intent intent = new Intent(getApplicationContext(), JourneyLearnMore.class);         // go to questionnaire page
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
