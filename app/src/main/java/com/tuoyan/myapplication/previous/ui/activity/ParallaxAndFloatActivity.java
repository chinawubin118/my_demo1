package com.tuoyan.myapplication.previous.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tuoyan.myapplication.R;
import com.tuoyan.myapplication.previous.ui.fragment.Radio1Fragment;
import com.tuoyan.myapplication.previous.ui.fragment.Radio2Fragment;
import com.tuoyan.myapplication.previous.ui.fragment.Radio3Fragment;
import com.tuoyan.myapplication.previous.utils.DeviceUtil;
import com.tuoyan.myapplication.previous.view.ParallaxAndFloatScrollView;

public class ParallaxAndFloatActivity extends AppCompatActivity {
    private RadioGroup tab_gone;//相当于floatView,可以悬浮的那块
    private LinearLayout ll_top_view, ll_bottom_view;//悬浮tab,上面那块view.....下面的布局
    private ParallaxAndFloatScrollView scroll_view;//整个可以滚动的scrollView
    private RelativeLayout rl_top;//顶部带imageView的布局
    private ImageView iv_header;//顶部的图片
    private ProgressBar progress_bar;//进度条
    private TextView tv_header;//顶部大标题

    private RadioGroup tab_visible;

    private Radio1Fragment radio1Fragment;
    private Radio2Fragment radio2Fragment;
    private Radio3Fragment radio3Fragment;

    private int deltaTop = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax_and_float);
        tab_gone = (RadioGroup) findViewById(R.id.tab_gone);
        ll_top_view = (LinearLayout) findViewById(R.id.ll_top_view);
        scroll_view = (ParallaxAndFloatScrollView) findViewById(R.id.scroll_view);
        rl_top = (RelativeLayout) findViewById(R.id.rl_top);
        iv_header = (ImageView) findViewById(R.id.iv_header);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        tab_visible = (RadioGroup) findViewById(R.id.tab_visible);
        tv_header = (TextView) findViewById(R.id.tv_header);

        radio1Fragment = new Radio1Fragment();
        radio2Fragment = new Radio2Fragment();
        radio3Fragment = new Radio3Fragment();

        deltaTop = (int) DeviceUtil.dp2px(this, 48);
        scroll_view.setDeltaTop(deltaTop);
        scroll_view.setOverScrollMode(AbsListView.OVER_SCROLL_NEVER);

        //将顶部带imageView的布局传过去
        scroll_view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {//view树布局完成时执行
            @Override
            public void onGlobalLayout() {
                scroll_view.setParallaxImageView(rl_top);
                int originalHeight = rl_top.getHeight();
                scroll_view.setOriginalHeight(originalHeight);
                scroll_view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        scroll_view.listenerFlowViewScrollState(ll_top_view, tab_gone);//设置悬浮

        scroll_view.setOnSizeChangeListener(new ParallaxAndFloatScrollView.OnSizeChangeListener() {//大小该表的监听
            @Override
            public void onSizeChange(float distanceY) {
                iv_header.getLayoutParams().height = (int) (DeviceUtil.dp2px(ParallaxAndFloatActivity.this, 86) + distanceY);
                iv_header.getLayoutParams().width = (int) (DeviceUtil.dp2px(ParallaxAndFloatActivity.this, 86) + distanceY);
                iv_header.requestLayout();

                if (DeviceUtil.px2dp(ParallaxAndFloatActivity.this, distanceY) > 30) {
                    progress_bar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onRelease(float originalHeight) {
                int originalHeaderHeight = (int) (DeviceUtil.dp2px(ParallaxAndFloatActivity.this, 86));
                iv_header.getLayoutParams().height = originalHeaderHeight;
                iv_header.getLayoutParams().width = originalHeaderHeight;
                iv_header.requestLayout();

                progress_bar.postDelayed(new Runnable() {//隐藏进度条
                    @Override
                    public void run() {
                        progress_bar.setVisibility(View.GONE);
                    }
                }, 2200);
            }
        });

        scroll_view.setSrcollingListener(new ParallaxAndFloatScrollView.SrcollingListener() {//滚动监听
            @Override
            public void srcrolling(int l, int t, int oldl, int oldt) {
                tv_header.setAlpha(t / DeviceUtil.dp2px(ParallaxAndFloatActivity.this, 172));
            }
        });

        FragmentManager manager1 = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager1.beginTransaction();
        fragmentTransaction.add(R.id.ll_bottom_view, radio1Fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tab_visible.setOnCheckedChangeListener(listener);
        tab_gone.setOnCheckedChangeListener(listener);
    }

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radio1:
                case R.id.radio4:
                    FragmentManager manager1 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = manager1.beginTransaction();
                    fragmentTransaction.replace(R.id.ll_bottom_view, radio1Fragment);
                    fragmentTransaction.commit();
                    radio1Fragment.setListViewSelection(0);

                    if (scroll_view.isFloatVisible()) {
                        scroll_view.scrollTo(0, topHeight);
                    }
                    break;
                case R.id.radio2:
                case R.id.radio5:
                    FragmentManager manager2 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction2 = manager2.beginTransaction();
                    fragmentTransaction2.replace(R.id.ll_bottom_view, radio2Fragment);
                    fragmentTransaction2.commit();
                    radio2Fragment.setListViewSelection(0);

                    if (scroll_view.isFloatVisible()) {
                        scroll_view.scrollTo(0, topHeight);
                    }
                    break;
                case R.id.radio3:
                case R.id.radio6:
                    FragmentManager manager3 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction3 = manager3.beginTransaction();
                    fragmentTransaction3.replace(R.id.ll_bottom_view, radio3Fragment);
                    fragmentTransaction3.commit();
                    radio3Fragment.setListViewSelection(0);

                    if (scroll_view.isFloatVisible()) {
                        scroll_view.scrollTo(0, topHeight);
                    }
                    break;
            }
        }
    };

    private int topHeight = 0;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            topHeight = tab_visible.getTop();
        }
    }
}
