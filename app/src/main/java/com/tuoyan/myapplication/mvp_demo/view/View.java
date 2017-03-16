package com.tuoyan.myapplication.mvp_demo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.tuoyan.myapplication.R;
import com.tuoyan.myapplication.mvp.ui.MyActivity;
import com.tuoyan.myapplication.mvp_demo.presenter.IPresenter;
import com.tuoyan.myapplication.mvp_demo.presenter.Presenter;

public class View extends AppCompatActivity implements IView {
    private IPresenter presenter;
    private Button button, my_button;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        button = (Button) findViewById(R.id.button);
        my_button = (Button) findViewById(R.id.my_button);
        text = (TextView) findViewById(R.id.text);

        presenter = new Presenter(this); //2 Presenter初始化
        presenter.onCreate();   //3 将生命周期回调传给Presenter
        button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                presenter.performOnClick(v); //4 用户输入
            }
        });

        my_button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(View.this, MyActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setData(final String data) {
        runOnUiThread(new Runnable() {     //ugly
                          public void run() {
                              text.setText(data);
                          }
                      }
        );
    }
}
