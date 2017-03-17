package sun.mview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Yaozong on 2017/3/17.
 */

public class TestXfermode extends View {
    private Paint mPaint;

    public TestXfermode(Context context) {
        this(context, null);
    }

    public TestXfermode(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestXfermode(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        testXfermode(canvas);
    }


    private int index;

    private void testXfermode(Canvas canvas) {

        int layer = canvas.saveLayer(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint, Canvas.ALL_SAVE_FLAG);

        mPaint.setStyle(Paint.Style.FILL);
        //1
        mPaint.setColor(Color.RED);
        canvas.drawCircle(200, 200, 100, mPaint);
        //2
        mPaint.setColor(Color.GREEN);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.values()[index]));
        canvas.drawRect(200, 200, 300, 300, mPaint);
        mPaint.setXfermode(null);
        PorterDuff.Mode.values()[index].name();

        canvas.restoreToCount(layer);

        mPaint.reset();
        mPaint.setStrokeWidth(5);
        mPaint.setTextSize(120);
        mPaint.setColor(Color.BLACK);
        canvas.drawText(PorterDuff.Mode.values()[index].name(), 0, 400, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                index = index < 15 ? index + 1 : 0;
                postInvalidate();
                return true;
            default:
        }

        return super.onTouchEvent(event);
    }
}
