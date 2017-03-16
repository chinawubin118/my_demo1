package com.tuoyan.myapplication.previous.ui.activity.test;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lotte.lottelibrary.base.BaseActivity;
import com.lotte.lottelibrary.http.MyGson;
import com.lotte.lottelibrary.http.OkGoHttp;
import com.lotte.lottelibrary.util.MyToast;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.tuoyan.myapplication.R;
import com.tuoyan.myapplication.previous.model.entity.InitInfo;
import com.tuoyan.myapplication.previous.model.entity.LogInfo;
import com.tuoyan.myapplication.previous.model.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 测试OkGo(OkHttp)网络请求的页面
 * 项目地址:https://github.com/jeasonlzy/okhttp-OkGo
 * 好莱坞会员成长值:http://film.qq.com/vip/activity.html
 */
public class TestOkGoActivity extends BaseActivity {
    private TextView tv_request_result, tv_level, tv_phone, tv_version_code;//请求结果的文本,学段,电话,版本号
    private TextView tv_ios_version, tv_limit_time, tv_share_url, tv_cych_url;//IOS版本号,限时,分享地址,cychURL
    private TextView tv_pmqc_url, tv_alipay_url, tv_activation_open, tv_listen_url;//pmqc_url,alipayURL,activation_open,listen_url
    private TextView tv_apkurl, tv_request_result2, tv_request_result2_info;//apkurl,第二个请求的结果.第二个请求结果信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ok_go);

        tv_request_result = (TextView) findViewById(R.id.tv_request_result);
        tv_level = (TextView) findViewById(R.id.tv_level);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_version_code = (TextView) findViewById(R.id.tv_version_code);
        tv_ios_version = (TextView) findViewById(R.id.tv_ios_version);
        tv_limit_time = (TextView) findViewById(R.id.tv_limit_time);
        tv_share_url = (TextView) findViewById(R.id.tv_share_url);
        tv_cych_url = (TextView) findViewById(R.id.tv_cych_url);
        tv_pmqc_url = (TextView) findViewById(R.id.tv_pmqc_url);
        tv_alipay_url = (TextView) findViewById(R.id.tv_alipay_url);
        tv_activation_open = (TextView) findViewById(R.id.tv_activation_open);
        tv_listen_url = (TextView) findViewById(R.id.tv_listen_url);
        tv_apkurl = (TextView) findViewById(R.id.tv_apkurl);
        tv_request_result2 = (TextView) findViewById(R.id.tv_request_result2);
        tv_request_result2_info = (TextView) findViewById(R.id.tv_request_result2_info);

        tv_request_result.setOnClickListener(this);//点击第一个结果
        tv_request_result2_info.setOnClickListener(this);//点击第二个结果

//        basicGetRequest();
//        basicPostRequest();

        HttpParams params = new HttpParams();
        params.put("phonetype", "0");
        params.put("act", "initProgram");
        params.put("imei", "imei_00000000");
        params.put("imsi", "imsi_00000000");
        params.put("version", "26");
        OkGoHttp.getInstance(this).getRequest("http://114.55.87.86:8988/phoneMobile.do", params, 1000);
        showProgress(true);

        OkGoHttp.getInstance(this).getRequest("http://115.28.230.133:8080/XddInterface/logistics/list.do?orderId=306", null, 1002);
        showProgress(true);
    }

    @Override
    public void onClick(View v) {
        if (v == tv_request_result) {
            HttpParams params2 = new HttpParams();
            params2.put("act", "login");
            params2.put("mobilephone", "18600000000");
            params2.put("password", "111111");
            params2.put("loginType", "0");
            params2.put("levelType", "01");
            OkGoHttp.getInstance(this).getRequest("http://114.55.87.86:8988/phoneMobile.do", params2, 1001);
            showProgress(true);
        }
        if (v == tv_request_result2_info) {
            OkGoHttp.getInstance(this).getRequest("http://115.28.230.133:8080/XddInterface/logistics/list.do?orderId=306", null, 1002);
        }
    }

    @Override
    public void onRequestSuccess(int tag, String jsonStr) {
        super.onRequestSuccess(tag, jsonStr);
        MyToast.showShortToast(getApplicationContext(), "请求成功");

        if (1000 == tag) {
            tv_request_result.setText("初始化数据:" + jsonStr);

            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                InitInfo initInfo = MyGson.getInstance().fromJson(jsonObject.getString("dataInfo"), InitInfo.class);
                if (null != initInfo) {//解析得到一个对象
                    try {
                        tv_level.setText("学段:" + initInfo.getSectionList().get(0).getName() + "..." + initInfo.getSectionList().get(1).getName());
                    } catch (Exception e) {
                        tv_level.setText("学段获取错误.....");
                    }
                    tv_phone.setText("电话:" + initInfo.getPhone());
                    tv_version_code.setText("安卓版本号:" + initInfo.getApkversion());
                    tv_ios_version.setText("IOS版本号:" + initInfo.getiOSVersion());
                    tv_limit_time.setText("限时:" + initInfo.getLimitTime());
                    tv_share_url.setText("分享地址:" + initInfo.getShareUrl());
                    tv_cych_url.setText("cychURL:" + initInfo.getCychURL());
                    tv_pmqc_url.setText("pmqcURL:" + initInfo.getPmqcURL());
                    tv_alipay_url.setText("alipayURL:" + initInfo.getAlipayURL());
                    tv_activation_open.setText("activationOpen:" + initInfo.getActivationOpen());
                    tv_listen_url.setText("listenURL:" + initInfo.getListenURL());
                    tv_apkurl.setText("apkurl:" + initInfo.getApkurl());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (1001 == tag) {
            tv_request_result2.setText("第二个请求的结果:" + jsonStr);
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                User user = MyGson.getInstance().fromJson(jsonObject.getString("dataInfo"), User.class);
                if (null != user) {
                    tv_request_result2_info.setText("用户信息:用户昵称:" + user.getNickName() + "  用户名" + user.getUsername() + "  电话号码:"
                            + user.getMobile() + "  性别:" + user.getSex());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (1002 == tag) {
            tv_request_result2.setText("第二个请求的结果:" + jsonStr);
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                LogInfo logInfo = MyGson.getInstance().fromJson(jsonObject.getString("dataInfo"), LogInfo.class);
                if (null != logInfo && null != logInfo.getOllist() && logInfo.getOllist().size() > 0) {
                    tv_request_result2_info.setText("物流信息:物流名称:" + logInfo.getOllist().get(0).getLogName()
                            + "    物流单号:" + logInfo.getOllist().get(0).getLogCode());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void basicGetRequest() {
        OkGo.getInstance().get("http://114.55.87.86:8988/phoneMobile.do?phonetype=0&act=initProgram&imei=&imsi=&version=26")//请求方式和请求url
                .tag("g001")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        // s 即为所需要的结果
                        Toast.makeText(getApplicationContext(), "Ok-Go请求成功!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void basicPostRequest() {
        HttpParams params = new HttpParams();
        params.put("phonetype", "0");
        params.put("act", "initProgram");
        params.put("imei", "imei_00000000");
        params.put("imsi", "imsi_00000000");
        params.put("version", "26");
        OkGo.getInstance().post("http://114.55.87.86:8988/phoneMobile.do")
                .tag("p001")
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        // s 即为所需要的结果
                        Toast.makeText(getApplicationContext(), "Ok-Go...POST请求成功!", Toast.LENGTH_SHORT).show();
                        tv_request_result.setText(s);
                    }
                });
    }
}
