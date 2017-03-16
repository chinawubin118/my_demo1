package com.tuoyan.myapplication.previous.ui.activity.test;

import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.lotte.lottelibrary.base.BaseActivity;
import com.tuoyan.myapplication.R;

public class CoordinatorLayoutActivity extends BaseActivity {
    private Button mBtnDependency;
    private int mBtnDependencyHeight = 0;
    private int mBtnDependencyWidth = 0;

    private int screenWidth = 0;
    private int screenHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        mBtnDependency = (Button) findViewById(R.id.btn_dependency);
        mBtnDependency.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (MotionEventCompat.getActionMasked(event)) {
                    case MotionEvent.ACTION_MOVE:
//                        Log.i("wubin", "l = " + (int) event.getRawX()
//                                + "t = " + (screenHeight - (int) event.getRawY())
//                                + "r = " + (screenWidth - (int) event.getRawX())
//                                + "b = " + (int) event.getRawY());
                        mBtnDependency.layout((int) event.getRawX(), (int) (event.getRawY() - mBtnDependencyHeight),
                                (int) event.getRawX() + mBtnDependencyWidth, (int) event.getRawY());
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            mBtnDependencyHeight = mBtnDependency.getMeasuredHeight();
            mBtnDependencyWidth = mBtnDependency.getMeasuredWidth();

            Log.i("wubin", "mBtnDependencyHeight = " + mBtnDependencyHeight + ".....mBtnDependencyWidth = " + mBtnDependencyWidth);
        }
    }
}
