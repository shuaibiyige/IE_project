package com.example.ie_project;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Thankyou extends Fragment
{
    View thankyou;
    private Button thank;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        thankyou = inflater.inflate(R.layout.thankyou_layout, container, false);
        thank = (Button) thankyou.findViewById(R.id.finish);
        thank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return thankyou;
    }
}
