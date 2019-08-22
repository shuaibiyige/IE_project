package com.example.ie_project;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.daimajia.numberprogressbar.NumberProgressBar;

public class Questionnaire extends AppCompatActivity
{
    private Button next;
    private Button previous;
    private NumberProgressBar bnp;
    private FragmentManager fragmentManager = getFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire_template);

        bnp = (NumberProgressBar) findViewById(R.id.progress_bar);
        next = (Button) findViewById(R.id.next_button);
        previous = (Button) findViewById(R.id.previous_button);

        fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire1()).commit();
        bnp.setProgress(33);
        previous.setVisibility(View.INVISIBLE);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SharedPreferences sharedPreferences = getSharedPreferences("questionnaire_page", Context.MODE_PRIVATE);
                int page = sharedPreferences.getInt("page", 0);
                judgePage1(page);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SharedPreferences sharedPreferences = getSharedPreferences("questionnaire_page", Context.MODE_PRIVATE);
                int page = sharedPreferences.getInt("page", 0);
                judgePage2(page);
            }
        });

    }

    public void judgePage1(int page)
    {
        switch (page)
        {
            case 1:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire2()).commit();
                bnp.setProgress(66);
                previous.setVisibility(View.VISIBLE);
                next.setText("Continue");
                break;
            case 2:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire3()).commit();
                bnp.setProgress(100);
                next.setText("Submit");
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(Questionnaire.this);
                        dialog.setTitle("Almost there!");
                        dialog.setMessage("Do you wish to continue?");
                        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Intent intent = new Intent(Questionnaire.this, MainActivity.class);
                                intent.putExtra("schedule", true);
                                startActivity(intent);
                            }
                        });

                        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Intent intent = new Intent(Questionnaire.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                        dialog.show();
                    }
                });
                break;
        }
    }

    public void judgePage2(int page)
    {
        switch (page)
        {
            case 2:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire1()).commit();
                bnp.setProgress(33);
                previous.setVisibility(View.INVISIBLE);
                next.setText("Continue");
                break;
            case 3:
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire2()).commit();
                bnp.setProgress(66);
                next.setText("Continue");
                break;
        }
    }
}
