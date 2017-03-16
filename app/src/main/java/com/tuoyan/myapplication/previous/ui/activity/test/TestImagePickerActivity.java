package com.tuoyan.myapplication.previous.ui.activity.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewActivity;
import com.tuoyan.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试ImagePicker的页面(仿微信的imagePicker)
 */
public class TestImagePickerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button but;
    private RecyclerView recycler;

    private static final int IMAGE_PICKER = 1001;
    private Adapter adapter;
    private ArrayList<String> path = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_image_picker);

        but = (Button) findViewById(R.id.but);
        recycler = (RecyclerView) findViewById(R.id.recycler);

        but.setOnClickListener(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recycler.setLayoutManager(gridLayoutManager);
        adapter = new Adapter(this, path);
        recycler.setAdapter(adapter);
    }

    private ArrayList<ImageItem> images;

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

                adapter.notifyDataSetChanged();

            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == but) {
            Intent intent = new Intent(this, ImageGridActivity.class);
            startActivityForResult(intent, IMAGE_PICKER);
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
                    Toast.makeText(getApplicationContext(), "点击了图片", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(TestImagePickerActivity.this, ImagePreviewActivity.class);
                    intent.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                    intent.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, images);
                    intent.putExtra(ImagePreviewActivity.ISORIGIN, true);
                    startActivity(intent);  //如果是多选，点击图片进入预览界面
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
}
