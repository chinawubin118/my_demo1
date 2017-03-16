package com.tuoyan.myapplication.previous.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.bigkoo.svprogresshud.listener.OnDismissListener;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tuoyan.myapplication.R;
import com.tuoyan.myapplication.previous.ui.activity.test.TestHomeAndTaskKeyActivity;
import com.tuoyan.myapplication.previous.ui.activity.test.TestImageSelectorActivity;
import com.tuoyan.myapplication.previous.utils.GlideImageLoader;
import com.uuch.adlibrary.AdConstant;
import com.uuch.adlibrary.AdManager;
import com.uuch.adlibrary.bean.AdInfo;
import com.uuch.adlibrary.transformer.DepthPageTransformer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btn_show_dialog;//点击按钮:显示弹窗
    private Button btn_test_key_activity, btn_photo_check_activity, btn_test_image_selector;//跳转到测试按键的页面,跳转到图片查看器页面,选择图片的页面
    private Button to_parallax_activity, to_another_navigation;//视差悬浮页面,另外一个导航页
    private SVProgressHUD mSVProgressHUD;//仿ios的弹窗
    int progress = 0;

    private ArrayList<AdInfo> advList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
//        HttpHeaders headers = new HttpHeaders();
//        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文
//        headers.put("commonHeaderKey2", "commonHeaderValue2");
//        HttpParams params = new HttpParams();
//        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
//        params.put("commonParamsKey2", "这里支持中文参数");

        OkGo.init(getApplication());

        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()

                    //打开该调试开关,控制台会使用 红色error 级别打印log,并不是错误,是为了显眼,不需要就不要加入该行
                    .debug("OkGo")

                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(12000)  //全局的连接超时时间
                    .setReadTimeOut(12000)     //全局的读取超时时间
                    .setWriteTimeOut(12000)    //全局的写入超时时间

                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)

                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                    //如果不想让框架管理cookie,以下不需要
//                .setCookieStore(new MemoryCookieStore())                //cookie使用内存缓存（app退出后，cookie消失）
                    .setCookieStore(new PersistentCookieStore());          //cookie持久化存储，如果cookie不过期，则一直有效

            //可以设置https的证书,以下几种方案根据需要自己设置,不需要不用设置
//                    .setCertificates()                                  //方法一：信任所有证书
//                    .setCertificates(getAssets().open("srca.cer"))      //方法二：也可以自己设置https证书
//                    .setCertificates(getAssets().open("aaaa.bks"), "123456", getAssets().open("srca.cer"))//方法三：传入bks证书,密码,和cer证书,支持双向加密

            //可以添加全局拦截器,不会用的千万不要传,错误写法直接导致任何回调不执行
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return chain.proceed(chain.request());
//                    }
//                })

            //这两行同上,不需要就不要传
//                    .addCommonHeaders(headers)                            //设置全局公共头
//                    .addCommonParams(params);                             //设置全局公共参数
        } catch (Exception e) {
            e.printStackTrace();
        }

        //初始化ImageLoader
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder() //
                .showImageForEmptyUri(R.mipmap.ic_launcher) //
                .showImageOnFail(R.mipmap.ic_launcher) //
                .cacheInMemory(true) //
                .cacheOnDisk(true) //
                .build();//
        ImageLoaderConfiguration config = new ImageLoaderConfiguration//
                .Builder(getApplicationContext())//
                .defaultDisplayImageOptions(defaultOptions)//
                .discCacheSize(50 * 1024 * 1024)//
                .discCacheFileCount(100)// 缓存一百张图片
                .writeDebugLogs()//
                .build();//
        ImageLoader.getInstance().init(config);

        //初始化ImagePicker(仿微信的imagePicker)
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素

        initViews();
        initData();
        setListeners();
    }

    private void initViews() {
        btn_show_dialog = (Button) findViewById(R.id.btn_show_dialog);
        btn_test_key_activity = (Button) findViewById(R.id.btn_test_key_activity);
        btn_photo_check_activity = (Button) findViewById(R.id.btn_photo_check_activity);
        btn_test_image_selector = (Button) findViewById(R.id.btn_test_image_selector);
        to_parallax_activity = (Button) findViewById(R.id.to_parallax_activity);
        to_another_navigation = (Button) findViewById(R.id.to_another_navigation);

        mSVProgressHUD = new SVProgressHUD(this);
    }

    private void setListeners() {
        btn_show_dialog.setOnClickListener(mOnclickListener);
        btn_test_key_activity.setOnClickListener(mOnclickListener);
        btn_photo_check_activity.setOnClickListener(mOnclickListener);
        btn_test_image_selector.setOnClickListener(mOnclickListener);
        to_parallax_activity.setOnClickListener(mOnclickListener);
        to_another_navigation.setOnClickListener(mOnclickListener);

        mSVProgressHUD.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(SVProgressHUD hud) {
                Toast.makeText(getApplicationContext(), "dismiss", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private MyOnclickListener mOnclickListener;

    //监听器
    private class MyOnclickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_show_dialog:
                    showDialog();
                    break;
                case R.id.btn_test_key_activity:
                    Intent intent = new Intent(MainActivity.this, TestHomeAndTaskKeyActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_photo_check_activity:
                    ArrayList list = new ArrayList();
                    list.add("http://114.55.57.132:8089/uploadFile/shopImg/shopIcon/B85DA7AF-1E6E-4329-884A-9EE805FC0EF2.png");
                    list.add("http://114.55.57.132:8089/uploadFile/shopImg/shopIcon/0C884EE9-F572-4C58-BC49-A7EB0957440B.png");
                    imageBrower(0, list);
                    break;
                case R.id.btn_test_image_selector:
                    Intent intent1 = new Intent(MainActivity.this, TestImageSelectorActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.to_parallax_activity://去下拉视差页面
                    Intent intent2 = new Intent(MainActivity.this, ParallaxAndFloatActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.to_another_navigation://去另一个导航页
                    Intent intent3 = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent3);
                    break;
            }
        }
    }

    /**
     * 打开图片查看器
     *
     * @param position
     * @param urls2
     */
    protected void imageBrower(int position, ArrayList<String> urls2) {
        Intent intent = new Intent(this, ImagePagerActivity.class);
        // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls2);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
        startActivity(intent);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mOnclickListener = new MyOnclickListener();

        advList = new ArrayList<AdInfo>();
        AdInfo adInfo = new AdInfo();
        adInfo.setActivityImg("http://dy.sparke.cn:8080/online/uploadFile/product/filesPhoneImg/2016072909074615291.png");
        advList.add(adInfo);

        adInfo = new AdInfo();
        adInfo.setActivityImg("http://dy.sparke.cn:8080/online/uploadFile/product/filesPhoneImg/2016080610087273834.jpg");
        advList.add(adInfo);
    }

    private void showDialog() {
        //创建广告活动管理对象
        AdManager adManager = new AdManager(MainActivity.this, advList);
        adManager.setOverScreen(true).setPageTransformer(new DepthPageTransformer());//三种切换动画...DepthPageTransformer；RotateDownPageTransformer；ZoomOutPageTransformer；
        //执行弹窗的显示操作
        adManager.showAdDialog(AdConstant.ANIM_DOWN_TO_UP);
    }

    /**
     * 显示带进度的dialog
     *
     * @param view
     */
    public void showWithProgress(View view) {
        progress = 0;
        mSVProgressHUD.getProgressBar().setProgress(progress);//先重设了进度再显示，避免下次再show会先显示上一次的进度位置所以要先将进度归0
        mSVProgressHUD.showWithProgress("进度 " + progress + "%", SVProgressHUD.SVProgressHUDMaskType.Black);
        mHandler.sendEmptyMessageDelayed(0, 500);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progress = progress + 5;
            if (mSVProgressHUD.getProgressBar().getMax() != mSVProgressHUD.getProgressBar().getProgress()) {
                mSVProgressHUD.getProgressBar().setProgress(progress);
                mSVProgressHUD.setText("进度 " + progress + "%");

                mHandler.sendEmptyMessageDelayed(0, 500);
            } else {
                mSVProgressHUD.dismiss();
            }
        }
    };
}
