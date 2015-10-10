package com.jvn.degreespree;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.jvn.degreespree.controllers.GameController;

public class MainView extends FragmentActivity {
    private FragmentManager fm;
    private GameController controller;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        fm = getSupportFragmentManager();
        controller = new GameController(this);
    }

    public void setView(Fragment fragment) {

        fm.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        currentFragment = fragment;
    }

    public float getDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float logicalDensity = metrics.density;
        return logicalDensity;

    }

}
