package com.example.ie_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

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
                Intent intent = new Intent(getActivity(), JourneyQuestionnaire.class);   // go to JourneyQuestionnaire page
                startActivity(intent);
            }
        });

        image.startAnimation(ani);

        return successful;
    }
}
