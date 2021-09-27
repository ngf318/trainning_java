package com.test.neil.kavayi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by neil on  2020/3/16
 */
public class ViewDesign extends View {


    public ViewDesign(Context context) {
        super(context);
    }

    public ViewDesign(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        screen_width = metrics.widthPixels;

        linePaint.setColor(Color.parseColor("#ff00ff"));
        rectPaint.setColor(Color.parseColor("#0000ff"));
    }

    Paint linePaint = new Paint();
    Paint rectPaint = new Paint();

    int screen_width;
    float x, av;
    float start = 100;
    float high = 300;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(10, 100, 10, 700, linePaint);
        canvas.drawLine(10, 700, screen_width - 10, 700, linePaint);
        canvas.drawLine(screen_width - 10, 100, screen_width - 10, 700, linePaint);

        canvas.drawRect(start, high, start + x, 700, rectPaint);
        canvas.drawRect(start + x + av, high, start + 2 * x + av, 700, rectPaint);
        canvas.drawRect(start + 2 * x + 2 * av, high, start + 2 * x + 2 * av + x, 700, rectPaint);
    }

    public void setZ(float z) {
        this.high = z;
        invalidate();
    }

    public void setY(float y) {
        this.start = y;
        this.av = (screen_width - 10 - 2 * start - 3 * x) / 2;
        invalidate();
    }

    public void setX(float x) {
        this.x = x;
        this.av = (screen_width - 10 - 2 * start - 3 * x) / 2;
        invalidate();
    }
}
