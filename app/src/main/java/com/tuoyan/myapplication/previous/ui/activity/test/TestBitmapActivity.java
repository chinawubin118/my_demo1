package com.tuoyan.myapplication.previous.ui.activity.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lotte.lottelibrary.base.BaseActivity;
import com.lotte.lottelibrary.http.OkGoHttp;
import com.lotte.lottelibrary.util.MyToast;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.tuoyan.myapplication.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class TestBitmapActivity extends BaseActivity {
    private ImageView image_view;

    private static final int IMAGE_PICKER = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bitmap);

        image_view = (ImageView) findViewById(R.id.image_view);
        image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestBitmapActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, IMAGE_PICKER);
            }
        });
    }

    private ArrayList<ImageItem> images;
    private ArrayList<String> path = new ArrayList<>();//图片路径

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (null != images && images.size() > 0) {
                    for (int i = 0; i < images.size(); i++) {
                        path.add(images.get(i).path);//图片路径
                    }
                }
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }

            Bitmap bitmap = BitmapFactory.decodeFile(path.get(0));
            InputStream inputStream = Bitmap2InputStream(bitmap, 80);


            //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
            HttpHeaders headers = new HttpHeaders();
            headers.put("flag", path.get(0));    //header不支持中文
            HttpParams params = new HttpParams();
            params.put("flag", "header");     //param支持中文,直接传,不要自己编码
            params.put("image_file1", new File(path.get(0)));//image_file1
            //http://www.schoo1.com/Upload/upload
            OkGoHttp.getInstance(this).postRequest("http://www.schoo1.com/Upload/upload", params, 600);
        }
    }

    @Override
    public void onRequestSuccess(int tag, String jsonStr) {
        super.onRequestSuccess(tag, jsonStr);
        if (600 == tag) {
            MyToast.showLongToast(getApplicationContext(), jsonStr);
        }
    }

    @Override
    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
        super.upProgress(currentSize, totalSize, progress, networkSpeed);
        Log.i("okgo", "currentSize.......... = " + currentSize);
    }

    /**
     * Bitmap to InputStream
     *
     * @param bm
     * @param quality
     * @return
     */
    public static InputStream Bitmap2InputStream(Bitmap bm, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        return is;
    }
}
