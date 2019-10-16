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

public class JourneyRejection extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private TextView textView;
    private final static String HTML_TEXT = "<p><strong>What is rejection?</strong></p>\n" +
            "<p>Rejection evaluates the parent’s priority to the adolescent’s feelings. If there is a consideration on the \n" +
            "positive impact of the actions discussed and undertaken in the family on the adolescent’s personal \n" +
            "life. Research concludes that children who experience rejection/neglect in their parental relationship \n" +
            "detach themselves and report different forms of psychological mal-adjustment.</p>\n" +
            "<p><strong>What to do to improve handling rejection?</strong></p>\n" +
            "<p>Adopt an inclusive approach to decision making by providing an opportunity for the child to express\n" +
            "their views and/or concerns in a safe space. Cultivate a transparent sharing atmosphere at home and\n" +
            "initiate expressing feelings on recent activities, situations and experiences.\n</p>\n" +
            "<p><strong>Examples of practices that can improve handling rejection:</strong></p>\n" +
            "<p>1. Avoid vehement opposition to innocent views expressions. Exercise resistance and listen to\n" +
            "their genuine views the first time around. This reduces the secondary backlash in the future</p>\n" +
            "<p>2. Engage actively with interest in your child’s life and share your positive outlook towards the\n" +
            "improvement</p>\n" +
            "<p>3. Approach sanctioning inappropriate actions/behaviour with a gentle, but firm demeanour.\n" +
            "Be patient and guide the child through your reasoning without being judgemental</p>" +
            "<p>4. Acknowledge the nature of humans to err and support acceptance of limitations, rejections\n" +
            "and failures in life</p>";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journey_rejection);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        textView = findViewById(R.id.journey_rejection_text);
        back = findViewById(R.id.journey_rejection_back);

        textView.setText(Html.fromHtml(HTML_TEXT));    // rich text using the format of html

        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.journey_rejection_back:
                finish();
                break;
            default:
                break;
        }
    }
}
