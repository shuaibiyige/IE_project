package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.daimajia.numberprogressbar.NumberProgressBar;


public class Questionnaire3 extends Fragment
{
    View question3;
    private Button next;
    private Button previous;
    private NumberProgressBar bnp;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question3 = inflater.inflate(R.layout.questionnaire3_layout, container, false);

        bnp = (NumberProgressBar) question3.findViewById(R.id.progress_bar3);
        next = (Button) question3.findViewById(R.id.next_button3);
        previous = (Button) question3.findViewById(R.id.previous_button3);
        final FragmentManager fragmentManager = getFragmentManager();
        bnp.setProgress(100);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Almost there!");
                dialog.setMessage("Do you wish to continue?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        intent.putExtra("schedule", true);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire2()).commit();
            }
        });

        Spinner age_spinner = (Spinner) question3.findViewById(R.id.ageSpinner_child);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.age_child, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age_spinner.setAdapter(adapter1);

        return question3;
    }
}
