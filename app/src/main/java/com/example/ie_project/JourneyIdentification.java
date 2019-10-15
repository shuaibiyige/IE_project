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

public class JourneyIdentification extends AppCompatActivity implements View.OnClickListener
{
    private Button back;
    private TextView textView;
    private final static String HTML_TEXT = "<p><strong>What is identification as a family unit?</strong></p>\n" +
            "<p>Identification evaluates the adolescent’s sense of belonging to the family and recognising parent has \n" +
            "a mentor and/or role model. From the initial research in all psychotherapeutic disciplines,\n" +
            "identification with parent has been recognized as being significant in the processes of socialization \n" +
            "and bonding between child and parent.</p>\n" +
            "<p><strong>What to do to improve identification as a family unit?</strong></p>\n" +
            "<p>The best way to improve identification is to lead by example. The ideal behaviour expected from the child would be adopted more willingly by the child if the parent becomes a practitioner and satisfies the same expectation. \n</p>\n" +
            "<p><strong>Examples of practices that can improve identification: \n</strong></p>\n" +
            "<p>1. Encourage adopting new habits/practices as a family rather than instructing the child to pick \n" +
            "them up</p>\n" +
            "<p>2. Incorporate new practices as a change in perspective without pin-pointing the \n" +
            "limitations/lack of performance of the child in specific areas. This general approach helps the child grow without insecurities about their limitations\n</p>\n" +
            "<p>3. Being a source of positivity and encouragement whenever possible and exposing the child \n" +
            "sensitively to the limitations of the parent as well. It is always important to show the human \n" +
            "side of the parent and not just focus on the ideal expectation</p>";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.journey_identification);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        textView = findViewById(R.id.journey_identification_text);
        back = findViewById(R.id.journey_identification_back);

        textView.setText(Html.fromHtml(HTML_TEXT));

        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.journey_identification_back:
                finish();
                break;

            default:
                break;
        }
    }
}
