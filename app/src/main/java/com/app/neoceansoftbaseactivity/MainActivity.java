package com.app.neoceansoftbaseactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.app.mylibrary.BaseActivity;
import com.app.mylibrary.tools.DensityUtil;

public class MainActivity extends BaseActivity {



    @Override
    protected void initData() {


    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isImmersion() {
        return true;
    }

    @Override
    protected boolean isScreen() {
        return false;
    }

    @Override
    protected boolean isPicScreen() {
        return false;
    }

    @Override
    protected int setImmersionColor() {
        return android.R.color.holo_orange_dark;
    }

    @Override
    protected boolean isSwipebackActivity() {
        return true;
    }

    @Override
    protected boolean isExit() {
        return false;
    }

    @Override
    protected boolean isRegistEventBus() {
        return false;
    }
}
