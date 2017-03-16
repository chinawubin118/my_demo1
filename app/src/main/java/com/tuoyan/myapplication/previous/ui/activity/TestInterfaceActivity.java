//package com.tuoyan.myapplication.activity;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//
//import com.tuoyan.myapplication.http.PhpHttp;
//
//public class TestInterfaceActivity extends AppCompatActivity implements INetResult {
//    private static String URL = "http://192.168.31.169/aaa.php";
//
//    private PhpHttp phpHttp = new PhpHttp(this, this);

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_interface);
//
//        phpHttp.requestPhp("hello", "android", URL);
//    }
//
//    @Override
//    public void onRequestSuccess(int requestCode) {
//        Log.i("json", "onRequestSuccess.....requestCode = " + requestCode);
//    }
//
//    @Override
//    public void onRequestError(int requestCode, String errorCode, String errorInfo) {
//        Log.i("json", "onRequestError.....requestCode = " + requestCode);
//    }
//
//    @Override
//    public void onRequestFaild(String errorNo, String errorMessage) {
//        Log.i("json", "onRequestError.....errorMessage = " + errorMessage);
//    }
//
//    @Override
//    public void onNoConnect() {
//        Log.i("json", "onNoConnect.....onNoConnect = onNoConnect");
//    }
//}
