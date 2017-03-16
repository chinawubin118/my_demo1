package com.tuoyan.myapplication.previous.utils;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.loader.ImageLoader;
import com.tuoyan.myapplication.R;

/**
 * Created by Lotte on 2016/10/8.
 * this is for imagePicker(仿微信的imagePicker)
 */
public class GlideImageLoader implements ImageLoader {
    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        try {
            Glide.with(activity)
                    .load(path)
                    .placeholder(R.mipmap.default_image)
//                .centerCrop()
                    .error(R.mipmap.default_image)
                    .into(imageView);
        } catch (Exception e) {
            Toast.makeText(activity, "图片数据有误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void clearMemoryCache() {

    }
}
