package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.daimajia.numberprogressbar.NumberProgressBar;


public class Questionnaire3 extends Fragment
{
    View question3;
    private Button next;
    private Button previous;
    private NumberProgressBar bnp;
    private int selectedItemCounter1 = 0;
    private int selectedItemCounter2 = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question3 = inflater.inflate(R.layout.questionnaire3_layout, container, false);

        initView();

        final FragmentManager fragmentManager = getFragmentManager();
        bnp.setProgress(100);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
//                dialog.setTitle("Almost there!");
//                dialog.setMessage("Do you wish to continue?");
//                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        Intent intent = new Intent(getActivity(), MainActivity.class);
//                        intent.putExtra("schedule", true);
//                        startActivity(intent);
//                    }
//                });
//                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        Intent intent = new Intent(getActivity(), MainActivity.class);
//                        startActivity(intent);
//                    }
//                });
//                dialog.show();
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Successful()).commit();

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

    public void initView()
    {
        bnp = (NumberProgressBar) question3.findViewById(R.id.progress_bar3);
        next = (Button) question3.findViewById(R.id.next_button3);
        previous = (Button) question3.findViewById(R.id.previous_button3);
        CheckBox Chinese = (CheckBox) question3.findViewById(R.id.Chinese);
        CheckBox American = (CheckBox) question3.findViewById(R.id.American);
        CheckBox Korean = (CheckBox) question3.findViewById(R.id.Korean);
        CheckBox Japanese = (CheckBox) question3.findViewById(R.id.Japanese);
        CheckBox Australian = (CheckBox) question3.findViewById(R.id.Australian);

        CheckBox movie_child = (CheckBox) question3.findViewById(R.id.movie_child);
        CheckBox baking_child = (CheckBox) question3.findViewById(R.id.baking_child);
        CheckBox board_game_child = (CheckBox) question3.findViewById(R.id.board_game_child);
        CheckBox gaming_child = (CheckBox) question3.findViewById(R.id.gaming_child);
        CheckBox arts_crafts_child = (CheckBox) question3.findViewById(R.id.arts_crafts_child);

        listener1(Chinese);
        listener1(American);
        listener1(Korean);
        listener1(Japanese);
        listener1(Australian);

        listener2(movie_child);
        listener2(baking_child);
        listener2(board_game_child);
        listener2(gaming_child);
        listener2(arts_crafts_child);
    }

    public void listener1(final CheckBox check)
    {
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    selectedItemCounter1++;
                }
                else
                {
                    selectedItemCounter1--;
                }
                if (selectedItemCounter1 > 2)
                {
                    buttonView.setChecked(false);
                    selectedItemCounter1--;
                }
            }
        });
    }

    public void listener2(final CheckBox check)
    {
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    selectedItemCounter2++;
                }
                else
                {
                    selectedItemCounter2--;
                }
                if (selectedItemCounter2 > 3)
                {
                    buttonView.setChecked(false);
                    selectedItemCounter2--;
                }
            }
        });
    }
}
