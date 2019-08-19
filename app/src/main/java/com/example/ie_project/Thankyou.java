package com.example.ie_project;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Thankyou extends Fragment
{
    View thankyou;
    private Button wantContinue;
    private Button notContinue;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        thankyou = inflater.inflate(R.layout.thankyou_layout, container, false);
        wantContinue = (Button) thankyou.findViewById(R.id.wantContinue);
        notContinue = (Button) thankyou.findViewById(R.id.notContinue);

        notContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        wantContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("schedule", true);
                startActivity(intent);
                //FragmentManager fragmentManager = getFragmentManager();
                //fragmentManager.beginTransaction().replace(R.id.content_frame, new Schedule()).commit();
            }
        });
        return thankyou;
    }
}
