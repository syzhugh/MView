package sun.mview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import sun.mview.R;

/**
 * Created by Yaozong on 2017/3/14.
 */

public class TestText extends View {

    private static final String TAG = "TestText";

    private Paint mPaint;

    public TestText(Context context) {
        this(context, null);
    }

    public TestText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        getAttrs(context, attrs, defStyleAttr);//getAttrs
        init();//init
    }

    private void getAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = null;
        if (array == null) {
            return;
        }

        int length = array.getIndexCount();
        for (int i = 0; i < length; i++) {
            int type = array.getIndex(i);
            switch (type) {
                case -1:
                    break;
            }
        }
        array.recycle();
    }

    private void init() {
        mPaint = new Paint();
    }


    private int baseX, baseY;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        lines(canvas);
        range(canvas);

        drawTextByTopLine(canvas);
        drawTextByMiddleLine(canvas);
    }

    private void drawTextByTopLine(Canvas canvas) {
        baseX = 0;
        baseY = 900;

        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(0, baseY, 3000, baseY, mPaint);

        mPaint.setTextSize(120);
        mPaint.setColor(Color.RED);
        String temp = "123211232112321";

        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        int baseLine = (int) (baseY - metrics.top);
        canvas.drawText(temp, 0, baseLine, mPaint);

    }

    private void drawTextByMiddleLine(Canvas canvas) {
        baseX = 0;
        baseY = 1200;

        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(0, baseY, 3000, baseY, mPaint);

        mPaint.setTextSize(120);
        mPaint.setColor(Color.RED);
        String temp = "123211232112321";

        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        int baseLine = (int) (baseY - (metrics.bottom - metrics.top) / 2 - metrics.top);
        canvas.drawText(temp, 0, baseLine, mPaint);
    }


    private void range(Canvas canvas) {
        baseX = 0;
        baseY = 600;
        mPaint.setTextSize(120);
        String temp = "123211232112321";

        //整体范围
        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        Rect rect = new Rect(baseX, (int) (baseY + metrics.top), (int) (baseX + mPaint.measureText(temp)), (int) (baseY + metrics.bottom));
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(rect, mPaint);

        //文字范围
        Rect minRect = new Rect();
        mPaint.getTextBounds(temp, 0, temp.length(), minRect);
        minRect.top = baseY + minRect.top;
        minRect.bottom = baseY + minRect.bottom;
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(minRect, mPaint);
        //文字
        mPaint.setColor(Color.RED);
        canvas.drawText(temp, baseX, baseY, mPaint);
    }

    private void lines(Canvas canvas) {
        baseX = 0;
        baseY = 300;
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(120);
//        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("abBFfGg", baseX, baseY, mPaint);

        Paint.FontMetrics metrics = mPaint.getFontMetrics();
        float bottom = metrics.bottom + baseY;
        float top = metrics.top + baseY;
        float ascent = metrics.ascent + baseY;
        float descent = metrics.descent + baseY;
        float leading = metrics.leading + baseY;

        mPaint.reset();
        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(0, baseY, 3000, baseY, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(0, bottom, 3000, bottom, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0, top, 3000, top, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, ascent, 3000, ascent, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(0, descent, 3000, descent, mPaint);
//        mPaint.setColor(Color.GREEN);
//        canvas.drawLine(0, leading, 3000, leading, mPaint);
    }



}
