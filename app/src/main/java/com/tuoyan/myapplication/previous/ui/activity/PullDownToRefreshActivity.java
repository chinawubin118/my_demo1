package com.tuoyan.myapplication.previous.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lotte.lottelibrary.base.BaseActivity;
import com.lotte.lottelibrary.util.MyToast;
import com.tuoyan.myapplication.R;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 测试下拉刷新的页面
 */
public class PullDownToRefreshActivity extends BaseActivity {
    private RecyclerView rcv_list;
    private PtrClassicFrameLayout mPtrFrame;

    private int lastVisiblePostion;
    private ArrayList<String> stringList = new ArrayList<String>();
    private MyRecyclerViewAdater adater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_down_to_refresh);

        rcv_list = (RecyclerView) findViewById(R.id.rcv_list);
        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_list_view_frame);

        for (int i = 0; i < 20; i++) {
            stringList.add("这是第" + i + "条数据...");
        }

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_list.setLayoutManager(layoutManager);
        adater = new MyRecyclerViewAdater();
        rcv_list.setAdapter(adater);

        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        stringList.clear();
                        for (int i = 0; i < 20; i++) {
                            stringList.add("这是第" + i + "条数据...");
                        }

                        adater.notifyDataSetChanged();
                        mPtrFrame.refreshComplete();//2s后结束刷新
                        MyToast.showShortToast(getApplicationContext(), "刷新完成");
                    }
                }, 1000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

        // the following are default settings
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);

        rcv_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && adater != null && lastVisiblePostion + 1 == adater.getItemCount()) {
                    if (stringList.size() <= 100) {
                        Log.i("json", "stringList.size() = " + stringList.size());
                        showProgressWithText(true, "加载更多...");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i < 20; i++) {
                                    stringList.add("这是新加载的第" + i + "条数据...");
                                }
                                adater.notifyDataSetChanged();
                                showProgress(false);
                                handler.sendEmptyMessage(1000);
                            }
                        }, 1000);
                    } else {
                        MyToast.showShortToast(getApplicationContext(), "没有更多数据了");
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisiblePostion = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1000:
                    MyToast.showShortToast(getApplication(), "加载完成");
                    break;
            }
        }
    };

    private class MyRecyclerViewAdater extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_test_ptr_list, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.tvString.setText(stringList.get(position));
        }

        @Override
        public int getItemCount() {
            return null == stringList ? 0 : stringList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView tvString;

            public MyViewHolder(View itemView) {
                super(itemView);
                tvString = (TextView) itemView.findViewById(R.id.tv_string);
            }
        }
    }
}
