package com.tuoyan.myapplication.previous.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
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

public class Demo0View extends View {
    private Paint paint1, paint2, paint3, paint4, paint5;
    private float rangeX = 0f;
    private float rangeY = 0f;

    private String heightText = "当前高度:0m";

    public Demo0View(Context context) {
        this(context, null);
    }

    public Demo0View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Demo0View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint1 = new Paint();
        paint1.setAntiAlias(true);
        paint1.setColor(Color.parseColor("#aa0000"));
        paint1.setTextSize(sp2px(18));

        paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setColor(Color.parseColor("#0000aa"));

        paint3 = new Paint();
        paint3.setAntiAlias(true);
        paint3.setColor(Color.parseColor("#00aa00"));
        paint3.setStyle(Paint.Style.STROKE);//设置空心

        paint4 = new Paint();
        paint4.setAntiAlias(true);
        paint4.setColor(Color.parseColor("#00aa00"));
        paint4.setStyle(Paint.Style.FILL);

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
        draw1(canvas);
        drawLine(canvas);
        drawCircle(canvas);
        drawArc(canvas);
        drawRect(canvas);
        drawOvalAndFan(canvas);
        drawBeiSaiEr(canvas);
        drawBitmap(canvas);
    }

    private void draw1(Canvas canvas) {
        canvas.drawText(heightText, dp2px(60), dp2px(20), paint1);
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, dp2px(44), dp2px(20), paint1);//大圆

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, dp2px(10), paint2);//中间一个小圆
    }

    private void drawLine(Canvas canvas) {
        canvas.drawLine(0, 0, getWidth(), getHeight(), paint1);//左上到右下
        canvas.drawLine(getWidth(), 0, 0, getHeight(), paint1);//右上到左下
    }

    private void drawArc(Canvas canvas) {
        RectF oval1 = new RectF(dp2px(30), dp2px(100), dp2px(50), dp2px(120));
        canvas.drawArc(oval1, 180, 180, false, paint3);//小弧形

        oval1.set(dp2px(70), dp2px(100), dp2px(90), dp2px(120));
        canvas.drawArc(oval1, 180, 180, false, paint3);//小弧形

        oval1.set(dp2px(40), dp2px(120), dp2px(80), dp2px(140));
        canvas.drawArc(oval1, 0, 180, false, paint3);//小弧形


        oval1.set(dp2px(30), dp2px(160), dp2px(50), dp2px(180));
        canvas.drawArc(oval1, 180, 180, false, paint4);//小弧形

        oval1.set(dp2px(70), dp2px(160), dp2px(90), dp2px(180));
        canvas.drawArc(oval1, 180, 180, false, paint4);//小弧形

        oval1.set(dp2px(40), dp2px(180), dp2px(80), dp2px(200));
        canvas.drawArc(oval1, 0, 180, false, paint4);//小弧形
    }

    private void drawRect(Canvas canvas) {
        canvas.drawRect(getWidth() - dp2px(60), dp2px(140), getWidth() - dp2px(40), dp2px(160), paint4);// 正方形
        canvas.drawRect(getWidth() - dp2px(80), dp2px(170), getWidth() - dp2px(30), dp2px(190), paint4);// 长方形

        RectF oval3 = new RectF(getWidth() - dp2px(80), dp2px(200), getWidth() - dp2px(30), dp2px(220));// 设置个新的长方形
        canvas.drawRoundRect(oval3, dp2px(4), dp2px(4), paint4);//画圆角矩形 第二个参数是x半径，第三个参数是y半径
    }

    private void drawOvalAndFan(Canvas canvas) {
        // 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是真的时候画扇形，是假的时候画弧线
        RectF oval2 = new RectF(getWidth() / 2 - dp2px(40), dp2px(130), getWidth() / 2 + dp2px(40), dp2px(200));// 设置个新的长方形，扫描测量
        canvas.drawArc(oval2, 210, 120, true, paint5);
        // 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是真的时候画扇形，是假的时候画弧线
        //画椭圆，把oval改一下
        oval2.set(getWidth() / 2 - dp2px(30), dp2px(70), getWidth() / 2 + dp2px(30), dp2px(120));
        canvas.drawOval(oval2, paint5);
    }

    //画贝塞尔曲线
    private void drawBeiSaiEr(Canvas canvas) {
        Path path2 = new Path();
        path2.moveTo(getWidth() / 2 - dp2px(60), getHeight() - dp2px(100));//设置Path的起点
        path2.quadTo(getWidth() / 2, getHeight() - dp2px(80), getWidth() / 2 + dp2px(60), getHeight() - dp2px(100));//贝塞尔的控制点和终点
        path2.lineTo(getWidth() / 2 + dp2px(60), getHeight() - dp2px(60));
        path2.quadTo(getWidth() / 2, getHeight() - dp2px(80), getWidth() / 2 - dp2px(60), getHeight() - dp2px(60));
        path2.lineTo(getWidth() / 2 - dp2px(60), getHeight() - dp2px(100));
        path2.close();
        canvas.drawPath(path2, paint5);//画出贝塞尔曲线

        float[] pointArray = new float[]{getWidth() / 2 - dp2px(40), getHeight() - dp2px(50), getWidth() / 2, getHeight() - dp2px(50), getWidth() / 2 + dp2px(40), getHeight() - dp2px(50)};
        canvas.drawPoints(pointArray, paint5);//画多个点
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

        long currenTimeMillis = System.currentTimeMillis();
        canvas.drawBitmap(bitmap, rangeX, rangeY, paint5);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (MotionEventCompat.getActionMasked(event)) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                heightText = "当前高度:" + (getHeight() - event.getY() + "m");
                rangeX = event.getX() - bitmapWidth / 2;
                rangeY = event.getY() - bitmapHeight / 2;
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        rangeY = getHeight() / 2 + dp2px(20);
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
