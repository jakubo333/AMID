package com.example.lab2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import static java.lang.Math.min;

public class Panel extends View {

    private Paint lightPaint;
    private Paint darkPaint;
    private Paint borderPaint;

    private int rows = 8;
    private int cols = 8;

    private boolean inverted = false;

    public Panel(Context context) {
        super(context);
        init();
    }

    public Panel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Panel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        lightPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        lightPaint.setStyle(Paint.Style.FILL);
        lightPaint.setColor(0xFF00E5FF);

        darkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        darkPaint.setStyle(Paint.Style.FILL);
        darkPaint.setColor(0xFFFFFF00);

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(4);
        borderPaint.setColor(0xFF000000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();

        int boardSize = min(w, h);
        int cellSize = boardSize / rows;

        int offsetX = (w - (cellSize * cols)) / 2;
        int offsetY = (h - (cellSize * rows)) / 2;

        Rect r = new Rect();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                boolean dark = ((row + col) % 2 != 0);
                if (inverted) dark = !dark;

                int left = offsetX + col * cellSize;
                int top = offsetY + row * cellSize;
                int right = left + cellSize;
                int bottom = top + cellSize;

                r.set(left, top, right, bottom);
                canvas.drawRect(r, dark ? darkPaint : lightPaint);
            }
        }

        int left = offsetX;
        int top = offsetY;
        int right = offsetX + cellSize * cols;
        int bottom = offsetY + cellSize * rows;
        canvas.drawRect(left, top, right, bottom, borderPaint);
    }

    public void resetBoard() {
        inverted = false;
        invalidate();
    }

    public void invertColors() {
        inverted = !inverted;
        invalidate();
    }
}
