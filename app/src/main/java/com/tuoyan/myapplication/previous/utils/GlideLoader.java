package com.tuoyan.myapplication.previous.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yancy.imageselector.ImageLoader;

/**
 * Created by Lotte on 2016/8/29.
 * this is for image_selector
 */
public class GlideLoader implements ImageLoader {
    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        try {
            Glide.with(context)
                    .load(path)
                    .placeholder(com.yancy.imageselector.R.mipmap.imageselector_photo)
//                .centerCrop()
                    .into(imageView);
        } catch (Exception e) {
            Toast.makeText(context, "图片数据有误", Toast.LENGTH_SHORT).show();
        }
    }
}
