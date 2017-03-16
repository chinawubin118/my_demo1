package com.tuoyan.myapplication.previous.ui.activity.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tuoyan.myapplication.R;
import com.tuoyan.myapplication.previous.ui.activity.ImagePagerActivity;
import com.tuoyan.myapplication.previous.utils.GlideLoader;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

public class TestImageSelectorActivity extends AppCompatActivity {

    private Adapter adapter;
    private ArrayList<String> path = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_image_selector);

        Button but = (Button) super.findViewById(R.id.but);
        RecyclerView recycler = (RecyclerView) super.findViewById(R.id.recycler);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageConfig imageConfig
                        = new ImageConfig.Builder(
                        // GlideLoader 可用自己用的缓存库
                        new GlideLoader())
                        // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                        .steepToolBarColor(getResources().getColor(R.color.blue))
                        // 标题的背景颜色 （默认黑色）
                        .titleBgColor(getResources().getColor(R.color.blue))
                        // 提交按钮字体的颜色  （默认白色）
                        .titleSubmitTextColor(getResources().getColor(R.color.white))
                        // 标题颜色 （默认白色）
                        .titleTextColor(getResources().getColor(R.color.white))
                        // 开启单选   （默认为多选）  (单选为singleSelect)
//                        .singleSelect()
                        .crop()
                        // 多选时的最大数量   （默认9张）
                        .mutiSelectMaxSize(9)
                        // 已选择的图片路径
                        .pathList(path)
                        // 拍照后存放的图片路径（默认 /temp/picture）
                        .filePath("/ImageSelector/Pictures")
                        // 开启拍照功能 （默认开启）
                        .showCamera()
                        .requestCode(REQUEST_CODE)
                        .build();

                ImageSelector.open(TestImageSelectorActivity.this, imageConfig);// 开启图片选择器
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recycler.setLayoutManager(gridLayoutManager);
        adapter = new Adapter(this, path);
        recycler.setAdapter(adapter);
    }

    public static final int REQUEST_CODE = 1000;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);

            for (String path : pathList) {
                Log.i("json", path);
            }
            path.clear();
            path.addAll(pathList);
            adapter.notifyDataSetChanged();
        }
    }

    public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
        private Context context;
        private LayoutInflater mLayoutInflater;
        private List<String> result;

        public Adapter(Context context, List<String> result) {
            mLayoutInflater = LayoutInflater.from(context);
            this.context = context;
            this.result = result;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.image, null));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            try {
                Glide.with(context).load(result.get(position)).placeholder(R.mipmap.ic_launcher).into(holder.image);
            } catch (Exception e) {
                Toast.makeText(context, "图片数据有误", Toast.LENGTH_SHORT).show();
            }

            holder.image.setOnClickListener(new View.OnClickListener() {//点击每一张图片时,跳转到图片查看页面
                @Override
                public void onClick(View v) {
                    imageBrower(position, path);
                }
            });
        }

        @Override
        public int getItemCount() {
            return result.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView image;

            public ViewHolder(View itemView) {
                super(itemView);
                image = (ImageView) itemView.findViewById(R.id.image);
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
}
