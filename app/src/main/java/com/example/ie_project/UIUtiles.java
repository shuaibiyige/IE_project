package com.example.ie_project;

import android.content.Context;
import android.net.wifi.aware.WifiAwareManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class UIUtiles
{
    private static  UIUtiles ourInstance;
    public static UIUtiles getInstance(){
        return ourInstance;
    }
    public static UIUtiles getInstance(Context context){
        if(ourInstance==null){
            ourInstance = new UIUtiles(context);
        }
        return ourInstance;
    }

    public static final int STANDARD_WIDTH =1080;
    public static final int STANDARD_HEIGHT=1872;

    private static final String DIMEN_CLASS = "com.android.internal.R$dimen";
    //实际设备分辨率 480 800
    public float displayMetricsWidth;
    public float getDisplayMetricsHeight;

    //initialization
    private UIUtiles(Context context){
        //获取屏幕真实宽高
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if(this.displayMetricsWidth == 0.0F || this.getDisplayMetricsHeight == 0.0F){
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            //获取到状态框的宽高
            int systemBarHeight = getSystemBarHeight(context);
            //处理真实宽高的问题
            if(displayMetrics.widthPixels>displayMetrics.heightPixels){
                //横屏
                this.displayMetricsWidth = (float)displayMetrics.heightPixels;
                this.getDisplayMetricsHeight = (float)displayMetrics.widthPixels-systemBarHeight;
            }else{
                //竖屏
                this.displayMetricsWidth=(float)displayMetrics.widthPixels;
                this.getDisplayMetricsHeight = (float)displayMetrics.heightPixels;
            }
        }
    }

    private int getSystemBarHeight(Context context){
        return getValue(context,"com.android.internal.R$dimen","system_bar_height", 48);
    }


    private int getValue(Context context, String attrGroupClass, String attrName, int defValue) {
        try {
            Class e = Class.forName(attrGroupClass);
            Object obj = e.newInstance();
            Field field = e.getField(attrName);
            //获取到的是一个ID
            int x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelOffset(x);
        } catch (Exception e1) {
            return defValue;
        }
    }

    //开始获取缩放以后的结果
    public float getWidth(float width){
        return width*this.displayMetricsWidth/1080.0F;
    }

    public float getHeight(float height){
        return height*this.getDisplayMetricsHeight/1872.0F;
    }

    public int getWidth(int width){
        return (int)(width*this.displayMetricsWidth/1080.0F);
    }

    public int getHeight(int height){
        return (int)(height*this.getDisplayMetricsHeight/1872.0F);
    }
}
