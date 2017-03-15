package sun.mview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Yaozong on 2017/3/14.
 */

public class TestCanvas extends View {
    private Paint mPaint;

    public TestCanvas(Context context) {
        this(context, null);
    }

    public TestCanvas(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        canvas.save();
        transform(canvas);
        canvas.restore();


        canvas.save();
        clip(canvas);
        canvas.restore();

        saveAndRestore(canvas);
    }

    private void saveAndRestore(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#9900ffff"));
    }

    private void clip(Canvas canvas) {
        baseX = 0;
        baseY = 400;

        canvas.clipRect(baseX, baseY, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawColor(Color.YELLOW);
        canvas.clipRect(baseX, baseY + 100, baseX + 300, baseY + 400);
        canvas.drawColor(Color.RED);
    }

    private void transform(Canvas canvas) {
        baseX = 0;
        baseY = 0;

        Rect rect = new Rect(baseX, baseX, 300, 300);
        //init
        mPaint.setColor(Color.RED);
        canvas.drawRect(rect, mPaint);
        //translate
        canvas.translate(100, 100);
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(rect, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(rect, mPaint);
        //rotate
        canvas.rotate(45);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rect, mPaint);

        canvas.rotate(-45);
        canvas.translate(-100, -100);
    }
}
