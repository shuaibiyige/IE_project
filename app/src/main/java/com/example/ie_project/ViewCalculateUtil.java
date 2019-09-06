package com.example.ie_project;

import android.view.View;
import android.widget.LinearLayout;


public class ViewCalculateUtil
{
    public static void setViewLinearLayoutParam(View view, int width, int heigth, int topMargin, int bottomMargin, int leftMargin, int rightMargin){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if(width!= LinearLayout.LayoutParams.MATCH_PARENT && width != LinearLayout.LayoutParams.WRAP_CONTENT){
            layoutParams.width = UIUtiles.getInstance(JettApplication.getInstance()).getWidth(width);
        }else{
            layoutParams.width = width;
        }

        if(heigth!= LinearLayout.LayoutParams.MATCH_PARENT && heigth != LinearLayout.LayoutParams.WRAP_CONTENT){
            layoutParams.height = UIUtiles.getInstance(JettApplication.getInstance()).getHeight(heigth);
        }else{
            layoutParams.height = heigth;
        }
        layoutParams.topMargin = UIUtiles.getInstance(JettApplication.getInstance()).getHeight(topMargin);
        layoutParams.bottomMargin = UIUtiles.getInstance(JettApplication.getInstance()).getHeight(bottomMargin);
        layoutParams.leftMargin = UIUtiles.getInstance(JettApplication.getInstance()).getWidth(leftMargin);
        layoutParams.rightMargin = UIUtiles.getInstance(JettApplication.getInstance()).getWidth(rightMargin);
        view.setLayoutParams(layoutParams) ;


    }
}
