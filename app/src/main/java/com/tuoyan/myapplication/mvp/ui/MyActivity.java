package com.tuoyan.myapplication.mvp.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lotte.lottelibrary.util.MyToast;
import com.tuoyan.myapplication.R;
import com.tuoyan.myapplication.mvp.presenter.IMyPresenter;
import com.tuoyan.myapplication.mvp.presenter.MyPresenter;

public class MyActivity extends AppCompatActivity implements View.OnClickListener, IMyActivity {
    private Toolbar mToolbar;
    private EditText et_input;//
    private Button btn_input, btn_query;//
    private TextView tv_result;

    private IMyPresenter myPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        myPresenter = new MyPresenter(this);

        initViews();
        setToolbar();//402880f55809e6d201580a347c950017
        setListeners();
    }

    //初始化View控件
    private void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        et_input = (EditText) findViewById(R.id.et_input);
        btn_input = (Button) findViewById(R.id.btn_input);
        btn_query = (Button) findViewById(R.id.btn_query);
        tv_result = (TextView) findViewById(R.id.tv_result);
    }

    //设置Toolbar
    private void setToolbar() {
        mToolbar.setTitle("获取人数");
        mToolbar.setSubtitle("获取访客/粉丝/关注人数");
        mToolbar.setSubtitleTextColor(Color.parseColor("#88ff0000"));
        mToolbar.setNavigationIcon(R.drawable.back);//左上角的返回按钮
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.showShortToast(getApplicationContext(), "点击返回...");
            }
        });
    }

    private void setListeners() {
        btn_input.setOnClickListener(this);
        btn_query.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_input) {
            et_input.setText("402880f55809e6d201580a347c950017");
        }
        if (v == btn_query){
            myPresenter.onQueryClick();
        }
    }

    //返回时输入的内容
    @Override
    public String getData() {
        return et_input.getText().toString();
    }

    //设置数据到View
    @Override
    public void setDataToView(String data) {
        tv_result.setText(data);
    }
}
