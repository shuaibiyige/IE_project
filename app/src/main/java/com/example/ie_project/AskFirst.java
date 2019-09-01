package com.example.ie_project;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class AskFirst extends AppCompatActivity
{
    private Button yes;
    private Button no;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_first_user);
        getSupportActionBar().hide();

        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskFirst.this, MainActivity.class);
                intent.putExtra("new user", true);
                startActivity(intent);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskFirst.this, MainActivity.class);
                intent.putExtra("new user", false);
                startActivity(intent);
            }
        });
    }
}
