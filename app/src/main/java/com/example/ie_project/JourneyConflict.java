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

public class JourneyConflict extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private TextView textView;
    private final static String HTML_TEXT = "<p><strong>What is conflict and bonding beyond conflict?</strong></p>\n" +
            "<p>Conflict evaluates the experiences of conflict of views between the parent and the child. Chronic or \n" +
            "very frequent conflict usually results from underlying difficulties in the relationship. Lack of adequate \n" +
            "family coping strategies cause a distress in the relationship.</p>\n" +
            "<p><strong>What to do to improve bonding beyond conflict?</strong></p>\n" +
            "<p>Active listening during conflicts and absorbing the details is essential. Patient, persuasive approach to \n" +
            "dealing with conflicts by respecting opposing point of views is recommended. Exhibiting such \n" +
            "characteristics by practices to the child re-enforces these concepts enabling reciprocation.</p>\n" +
            "<p><strong>Examples of practices that can improve bonding beyond conflicts:</strong></p>\n" +
            "<p>1. Implementing a habit of taking time-outs during sensitive conversationsto give some personal \n" +
            "space to collect individual thoughts</p>\n" +
            "<p>2. Active listening and empathising the conflicting point of view when it is proposed/brought up \n" +
            "first</p>\n" +
            "<p>3. Using non-judgemental tones and statements such as “I feel/I worry/My concern is” instead \n" +
            "of accusatory statements</p>";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journey_conflict);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        textView = findViewById(R.id.journey_conflict_text);
        back = findViewById(R.id.journey_conflict_back);

        textView.setText(Html.fromHtml(HTML_TEXT));

        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.journey_conflict_back:
                finish();
                break;

            default:
                break;
        }
    }
}
