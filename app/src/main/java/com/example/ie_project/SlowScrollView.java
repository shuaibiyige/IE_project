package com.example.ie_project;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

public class SlowScrollView extends HorizontalScrollView    // customised HorizontalScrollView to adjust the scrolling speed
{
    public SlowScrollView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public SlowScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlowScrollView(Context context) {
        super(context);
    }

    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 4);
    }
}
