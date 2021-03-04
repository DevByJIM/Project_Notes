package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;

public class OverrideResources extends Resources {

    Context context;

    public OverrideResources(Resources original) {
        super(original.getAssets(), original.getDisplayMetrics(), original.getConfiguration());
    }

    public OverrideResources(Resources original,Context context) {
        super(original.getAssets(), original.getDisplayMetrics(), original.getConfiguration());
        this.context = context;
    }

    @Override public int getColor(int id) throws NotFoundException {
        return getColor(id, null);
    }

    @Override public int getColor(int id, Theme theme) throws NotFoundException {
        SharedPreferences preferences = context.getSharedPreferences("preferences",Context.MODE_PRIVATE);


        switch (id) {
            case R.color.primary_200:
                return preferences.getInt(R.color.primary_200+"",context.getColor(R.color.primary_200));
            case R.color.primary_500:
                return preferences.getInt(R.color.primary_500+"",context.getColor(R.color.primary_500));
            case R.color.secondary_200:
                return preferences.getInt(R.color.secondary_200+"",context.getColor(R.color.secondary_200));
            case R.color.secondary_500:
                return preferences.getInt(R.color.secondary_500+"",context.getColor(R.color.secondary_500));
            case R.color.titleColour:
                return preferences.getInt(R.color.titleColour+"",context.getColor(R.color.titleColour));
            case R.color.textColor:
                return preferences.getInt(R.color.textColor+"",context.getColor(R.color.textColor));
            default:
                return Color.BLACK;
        }
    }
}
