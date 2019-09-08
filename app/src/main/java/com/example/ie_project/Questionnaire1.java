package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.ramotion.foldingcell.FoldingCell;


public class Questionnaire1 extends Fragment
{
    View question1;
    private Button submit;
    private int selectedItemCounter1 = 0;
    private ImageView haveCar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question1 = inflater.inflate(R.layout.questionnaire1_layout, container, false);

        final FragmentManager fragmentManager = getFragmentManager();

        initView();

        haveCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "yes", Toast.LENGTH_SHORT).show();

            }
        });

//        bnp = (NumberProgressBar) question1.findViewById(R.id.progress_bar1);
//        next = (Button) question1.findViewById(R.id.next_button1);
//        radioGroup = (RadioGroup) question1.findViewById(R.id.radioGroup_gender);
//
//        final FragmentManager fragmentManager = getFragmentManager();
//        bnp.setProgress(33);
//
//        Spinner age_spinner = (Spinner) question1.findViewById(R.id.ageSpinner);
//        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.age, android.R.layout.simple_spinner_item);
//        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        age_spinner.setAdapter(adapter1);
//
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragmentManager.beginTransaction().replace(R.id.content_frame, new Questionnaire2()).commit();
//            }
//        });

//        final FoldingCell fc = (FoldingCell) question1.findViewById(R.id.folding_cell);
//        fc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fc.toggle(false);
//            }
//        });

//        getScreenSizeOfDevice2();
//        ViewGroup.LayoutParams lp;
//        LinearLayout l = question1.findViewById(R.id.l);
//        lp = l.getLayoutParams();
//        lp.width = (int)Math.round((5.24/5.14) * (l.getLayoutParams().width));
//        Log.d(TAG, "line: " + l.getLayoutParams().width);


        submit = (Button) question1.findViewById(R.id.submit_ques);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Successful()).commit();            }
        });

        return question1;
    }

    public void initView()
    {
        CheckBox movie = (CheckBox) question1.findViewById(R.id.movie);
        CheckBox baking = (CheckBox) question1.findViewById(R.id.baking);
        CheckBox diy = (CheckBox) question1.findViewById(R.id.diy);
        CheckBox gaming = (CheckBox) question1.findViewById(R.id.board_gaming);
        CheckBox painting = (CheckBox) question1.findViewById(R.id.painting);
        CheckBox cooking = (CheckBox) question1.findViewById(R.id.cooking);

        haveCar = question1.findViewById(R.id.have_car);

        listener1(movie);
        listener1(gaming);
        listener1(baking);
        listener1(diy);
        listener1(painting);
        listener1(cooking);
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
                if (selectedItemCounter1 > 3)
                {
                    buttonView.setChecked(false);
                    selectedItemCounter1--;
                }
            }
        });
    }

}
