package com.tuoyan.myapplication.previous.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lotte.lottelibrary.base.BaseActivity;
import com.lotte.lottelibrary.util.MyToast;
import com.tuoyan.myapplication.R;
import com.tuoyan.myapplication.previous.utils.LocalDisplay;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * 第二个测试下拉刷新的页面
 */
public class PullDownToRefresh2Activity extends BaseActivity {

    private RecyclerView rcv_list;
    private PtrFrameLayout mPtrFrame;

    private int lastVisiblePostion;
    private ArrayList<String> stringList = new ArrayList<String>();
    private MyRecyclerViewAdater adater;

    final String[] mStringList = {"Tuoyan", "ANDROID"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_down_to_refresh2);

        LocalDisplay.init(this);

        rcv_list = (RecyclerView) findViewById(R.id.rcv_list);
        mPtrFrame = (PtrFrameLayout) findViewById(R.id.rotate_header_list_view_frame);

        for (int i = 0; i < 40; i++) {
            stringList.add("这是第" + i + "条数据...");
        }

        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rcv_list.setLayoutManager(layoutManager);
        adater = new MyRecyclerViewAdater();
        rcv_list.setAdapter(adater);

        // header
        final StoreHouseHeader header = new StoreHouseHeader(getApplicationContext());
        header.setPadding(0, LocalDisplay.dp2px(15), 0, 0);
        /**
         * using a string, support: A-Z 0-9 - .
         * you can add more letters by {@link in.srain.cube.views.ptr.header.StoreHousePath#addChar}
         */
        header.initWithString(mStringList[0]);
//        setHeaderTitle(mTitlePre + mStringList[0]);

//        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        stringList.clear();
                        for (int i = 0; i < 40; i++) {
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
                return isCanPullDown;
            }
        });

        // for changing string
        mPtrFrame.addPtrUIHandler(new PtrUIHandler() {

            private int mLoadTime = 0;

            @Override
            public void onUIReset(PtrFrameLayout frame) {
                mLoadTime++;
                String string = mStringList[mLoadTime % mStringList.length];
                header.initWithString(string);
            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {
                String string = mStringList[mLoadTime % mStringList.length];
//                setHeaderTitle(mTitlePre + string);
            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

            }
        });

        mPtrFrame.setDurationToCloseHeader(3000);
        mPtrFrame.setHeaderView(header);
        mPtrFrame.addPtrUIHandler(header);

        rcv_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && adater != null && lastVisiblePostion + 1 == adater.getItemCount()) {
                    if (stringList.size() <= 120) {
                        Log.i("json", "stringList.size() = " + stringList.size());
                        showProgressWithText(true, "加载更多...");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i < 40; i++) {
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

                    isCanPullDown = false;
                } else if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0) {//这样应该是到顶部了
                    isCanPullDown = true;
                } else {
                    isCanPullDown = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisiblePostion = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private boolean isCanPullDown = true;//只有recyclerView到达顶部的时候才能下拉

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
