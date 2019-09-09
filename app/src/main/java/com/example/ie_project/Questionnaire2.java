package com.example.ie_project;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;


public class Questionnaire2 extends Fragment
{
    View question2;
    private Button submit;
    private int selectedItemCounter1 = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        question2 = inflater.inflate(R.layout.questionnaire2_layout, container, false);
        final FragmentManager fragmentManager = getFragmentManager();


        initView();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.content_frame, new Successful()).commit();
            }
        });

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


//        submit = (Button) question1.findViewById(R.id.submit_ques);
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragmentManager.beginTransaction().replace(R.id.content_frame, new Successful()).commit();            }
//        });

        return question2;
    }

    public void initView()
    {
        submit = question2.findViewById(R.id.submit_ques);

//        listener1(movie);
//        listener1(gaming);
//        listener1(baking);
//        listener1(diy);
//        listener1(painting);
//        listener1(cooking);
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
