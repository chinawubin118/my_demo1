package com.tuoyan.myapplication.previous.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.tuoyan.myapplication.R;

/**
 * Created by Administrator on 2016/12/16.
 */
public class Demo2View extends View {
    private Paint paint5;
    private int rangeX = 0;
    private int rangeY = 0;

    private String heightText = "当前高度:0m";

    public Demo2View(Context context) {
        this(context, null);
    }

    public Demo2View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Demo2View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint5 = new Paint();
        paint5.setAntiAlias(true);
        paint5.setColor(Color.parseColor("#00aa00"));
        paint5.setStyle(Paint.Style.FILL);
        Shader mShader = new LinearGradient(0, 0, 100, 100, new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.LTGRAY}
                , null, Shader.TileMode.REPEAT); // 一个材质,打造出一个线性梯度沿著一条线。
        paint5.setShader(mShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBitmap(canvas);
    }

    private Bitmap bitmap;
    private float bitmapWidth = 0;
    private float bitmapHeight = 0;

    //画图
    private void drawBitmap(Canvas canvas) {
        //画图片，就是贴图
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.nocontent);

        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();

        canvas.drawBitmap(bitmap, rangeX, rangeY, paint5);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (MotionEventCompat.getActionMasked(event)) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                heightText = "当前高度:" + (getHeight() - event.getRawY() + "m");
                rangeX = (int) (event.getRawX() - bitmapWidth / 2);
                rangeY = (int) (event.getRawY() - bitmapHeight / 2);

                layout(rangeX, rangeY, getResources().getDisplayMetrics().widthPixels - rangeX, getResources().getDisplayMetrics().heightPixels - rangeY);
                postInvalidateDelayed(100);
                break;
        }
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    //工具方法
    public int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    //工具方法
    private int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }
}
