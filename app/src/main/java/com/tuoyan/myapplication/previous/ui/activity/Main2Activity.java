package com.tuoyan.myapplication.previous.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lotte.lottelibrary.util.MyToast;
import com.tuoyan.myapplication.R;
import com.tuoyan.myapplication.previous.ui.activity.test.CoordinatorLayoutActivity;
import com.tuoyan.myapplication.previous.ui.activity.test.ForegroundServiceActivity;
import com.tuoyan.myapplication.previous.ui.activity.test.TestBitmapActivity;
import com.tuoyan.myapplication.previous.ui.activity.test.TestDemo1ViewActivity;
import com.tuoyan.myapplication.previous.ui.activity.test.TestImagePickerActivity;
import com.tuoyan.myapplication.previous.ui.activity.test.TestOkGoActivity;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_test_interface, btn_test_ok_http_go;//测试接口页面,okhttp-OkGo网络框架测试页面
    private Button btn_test_image_picker, btn_test_pull_down_refresh, btn_test_pull_down_refresh2;//仿微信图片选择器,下拉刷新,下拉刷新2
    private Button btn_test_tool_bar, btn_test_bitmap_upload, btn_test_foreground_service;//ToolBar页面,Bitmap上传,前台service
    private Button btn_test_demo1_view, btn_test_retrofit, btn_test_retrofit_2;//测试Demo1View,test_retrofit,test_retrofit_2
    private Button btn_test_CoordinatorLayout, btn_test_mvp;//test_CoordinatorLayout,test_mvp

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn_test_interface = (Button) findViewById(R.id.btn_test_interface);
        btn_test_ok_http_go = (Button) findViewById(R.id.btn_test_ok_http_go);
        btn_test_image_picker = (Button) findViewById(R.id.btn_test_image_picker);
        btn_test_pull_down_refresh = (Button) findViewById(R.id.btn_test_pull_down_refresh);
        btn_test_pull_down_refresh2 = (Button) findViewById(R.id.btn_test_pull_down_refresh2);
        btn_test_tool_bar = (Button) findViewById(R.id.btn_test_tool_bar);
        btn_test_CoordinatorLayout = (Button) findViewById(R.id.btn_test_CoordinatorLayout);
        btn_test_bitmap_upload = (Button) findViewById(R.id.btn_test_bitmap_upload);
        btn_test_foreground_service = (Button) findViewById(R.id.btn_test_foreground_service);
        btn_test_demo1_view = (Button) findViewById(R.id.btn_test_demo1_view);
        btn_test_retrofit = (Button) findViewById(R.id.btn_test_retrofit);
        btn_test_retrofit_2 = (Button) findViewById(R.id.btn_test_retrofit_2);
        btn_test_mvp = (Button) findViewById(R.id.btn_test_mvp);

        btn_test_interface.setOnClickListener(this);
        btn_test_ok_http_go.setOnClickListener(this);
        btn_test_image_picker.setOnClickListener(this);
        btn_test_pull_down_refresh.setOnClickListener(this);
        btn_test_pull_down_refresh2.setOnClickListener(this);
        btn_test_tool_bar.setOnClickListener(this);
        btn_test_CoordinatorLayout.setOnClickListener(this);
        btn_test_bitmap_upload.setOnClickListener(this);
        btn_test_foreground_service.setOnClickListener(this);
        btn_test_demo1_view.setOnClickListener(this);
        btn_test_retrofit.setOnClickListener(this);
        btn_test_retrofit_2.setOnClickListener(this);
        btn_test_mvp.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == btn_test_interface) {//测试接口页面
//            Intent intent = new Intent(this, TestInterfaceActivity.class);
//            startActivity(intent);
            MyToast.showShortToast(getApplicationContext(), "界面暂时不用了");
        }
        if (v == btn_test_ok_http_go) {//OkGo网络框架测试页面
            Intent intent = new Intent(this, TestOkGoActivity.class);
            startActivity(intent);
        }
        if (v == btn_test_image_picker) {//仿微信的.....图片选择框架
            Intent intent = new Intent(this, TestImagePickerActivity.class);
            startActivity(intent);
        }
        if (v == btn_test_pull_down_refresh) {//下拉刷新
            Intent intent = new Intent(this, PullDownToRefreshActivity.class);
            startActivity(intent);
        }
        if (v == btn_test_pull_down_refresh2) {
            Intent intent = new Intent(this, PullDownToRefresh2Activity.class);
            startActivity(intent);
        }
        if (v == btn_test_tool_bar) {
            Intent intent = new Intent(this, ToolBarActivity.class);
            startActivity(intent);
        }
        if (v == btn_test_CoordinatorLayout) {
            Intent intent = new Intent(this, CoordinatorLayoutActivity.class);
            startActivity(intent);
        }
        if (v == btn_test_bitmap_upload) {
            Intent intent = new Intent(this, TestBitmapActivity.class);
            startActivity(intent);
        }
        if (v == btn_test_foreground_service) {//测试前台Service的页面
            Intent intent = new Intent(this, ForegroundServiceActivity.class);
            startActivity(intent);
        }
        if (v == btn_test_demo1_view) {
            Intent intent = new Intent(this, TestDemo1ViewActivity.class);
            startActivity(intent);
        }
        if (v == btn_test_retrofit) {
            Intent intent = new Intent(this, RetrofitActivity.class);
            startActivity(intent);
        }
        if (v == btn_test_retrofit_2) {
            Intent intent = new Intent(this, Retrofit2Activity.class);
            startActivity(intent);
        }
        if (v == btn_test_mvp) {
            Intent intent = new Intent(this, com.tuoyan.myapplication.mvp_demo.view.View.class);
            startActivity(intent);
        }
    }
}
