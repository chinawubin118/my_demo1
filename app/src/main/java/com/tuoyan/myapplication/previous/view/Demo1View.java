package com.tuoyan.myapplication.previous.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.tuoyan.myapplication.R;

/**
 * Created by Administrator on 2016/12/17.
 */

public class Demo1View extends View {
    public int moveDirection = DIRECTION_RIGHT;
    private Bitmap bitmap;
    private Bitmap bitmapConan;
    private int conanWidth = 0;
    private int conanHeight = 0;
    private Paint bitmapPaint, textPaint;
    private float bitmapX = 100, bitmapY = 100;//图形的位置(x,y)
    private int speedPix = 5;//移动的速度

    private String hintTetx = "";

    public Demo1View(Context context) {
        this(context, null);
    }

    public Demo1View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Demo1View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_loading_lotte);
        bitmapConan = BitmapFactory.decodeResource(getResources(), R.drawable.conan);
        conanWidth = bitmapConan.getWidth();
        conanHeight = bitmapConan.getHeight();
        bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.parseColor("#aa0000"));
        textPaint.setTextSize(sp2px(18));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmapConan, getWidth() / 2 - conanWidth / 2, getHeight() / 2 - conanHeight / 2 - dp2px(40), bitmapPaint);
        canvas.drawText(hintTetx, getWidth() / 2, 100, textPaint);
        startMove(canvas);
    }

    private void startMove(Canvas canvas) {
        if (moveDirection == DIRECTION_UP) {
            if (bitmapY > 10) {
                bitmapY -= speedPix;
                canvas.drawBitmap(bitmap, bitmapX, bitmapY, bitmapPaint);
            } else {
                bitmapY = getHeight() - 10;
            }
        } else if (moveDirection == DIRECTION_DOWN) {
            if (bitmapY < getHeight() - 10) {
                bitmapY += speedPix;
                canvas.drawBitmap(bitmap, bitmapX, bitmapY, bitmapPaint);
            } else {
                bitmapY = 10;
            }
        } else if (moveDirection == DIRECTION_LEFT) {
            if (bitmapX > 10) {
                bitmapX -= speedPix;
                canvas.drawBitmap(bitmap, bitmapX, bitmapY, bitmapPaint);
            } else {
                bitmapX = getWidth() - 10;
            }
        } else if (moveDirection == DIRECTION_RIGHT) {
            if (bitmapX < getWidth() - 10) {
                bitmapX += speedPix;
                canvas.drawBitmap(bitmap, bitmapX, bitmapY, bitmapPaint);
            } else {
                bitmapX = 10;
            }
        }

        boolean xTrue = (bitmapX > getWidth() / 2 - conanWidth / 2) && bitmapX < getWidth() / 2 + conanWidth / 2;
        boolean yTrue = ((bitmapY + dp2px(40)) > getHeight() / 2 - conanHeight) && (bitmapY < getHeight() / 2 - conanHeight);
        if (xTrue && yTrue) {
            hintTetx = "撞了啊...";
            if (moveDirection == DIRECTION_RIGHT) {
                moveDirection = DIRECTION_LEFT;
            } else if (moveDirection == DIRECTION_LEFT) {
                moveDirection = DIRECTION_RIGHT;
            } else if (moveDirection == DIRECTION_UP) {
                moveDirection = DIRECTION_DOWN;
            } else if (moveDirection == DIRECTION_DOWN) {
                moveDirection = DIRECTION_UP;
            }
        } else {
            hintTetx = "";
        }

        invalidate();
    }

    public void setDirection(int direction) {
        moveDirection = direction;
        invalidate();
    }

    public void setSpeedUp() {
        speedPix++;
        invalidate();
    }

    public void setSpeedDown() {
        speedPix--;
        invalidate();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return true;
//    }

    //工具方法
    public int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    //工具方法
    private int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_DOWN = 2;
    public static final int DIRECTION_LEFT = 3;
    public static final int DIRECTION_RIGHT = 4;
}
