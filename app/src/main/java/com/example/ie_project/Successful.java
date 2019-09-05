package com.example.ie_project;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Successful extends Fragment
{
    View successful;
    Button head;
    ImageView image;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        successful = inflater.inflate(R.layout.successful_layout, container, false);

        head = successful.findViewById(R.id.head_home);
        image = successful.findViewById(R.id.ok_image);
        Animation ani = AnimationUtils.loadAnimation(getActivity(), R.anim.dashboard_image);


        head.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        image.startAnimation(ani);

        return successful;
    }
}
