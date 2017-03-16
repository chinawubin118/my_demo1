package com.tuoyan.myapplication.previous.ui.activity.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.tuoyan.myapplication.R;
import com.tuoyan.myapplication.previous.view.Demo1View;

public class TestDemo1ViewActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_up, tv_down, tv_left, tv_right;
    private TextView tv_speed_up, tv_speed_down;
    private Demo1View demo1View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_demo1_view);

        demo1View = (Demo1View) findViewById(R.id.demo1View);
        tv_up = (TextView) findViewById(R.id.tv_up);
        tv_down = (TextView) findViewById(R.id.tv_down);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_right = (TextView) findViewById(R.id.tv_right);
        tv_speed_up = (TextView) findViewById(R.id.tv_speed_up);
        tv_speed_down = (TextView) findViewById(R.id.tv_speed_down);

        tv_up.setOnClickListener(this);
        tv_down.setOnClickListener(this);
        tv_left.setOnClickListener(this);
        tv_right.setOnClickListener(this);
        tv_speed_up.setOnClickListener(this);
        tv_speed_down.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tv_up) {
            demo1View.setDirection(Demo1View.DIRECTION_UP);
        }
        if (v == tv_down) {
            demo1View.setDirection(Demo1View.DIRECTION_DOWN);
        }
        if (v == tv_left) {
            demo1View.setDirection(Demo1View.DIRECTION_LEFT);
        }
        if (v == tv_right) {
            demo1View.setDirection(Demo1View.DIRECTION_RIGHT);
        }
        if (v == tv_speed_up) {
            demo1View.setSpeedUp();
        }
        if (v == tv_speed_down) {
            demo1View.setSpeedDown();
        }
    }
}
