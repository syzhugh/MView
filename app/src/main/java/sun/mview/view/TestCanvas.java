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


    private int baseX, baseY;

    public TestCanvas(Context context) {
        this(context, null);
    }

    public TestCanvas(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestCanvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_HARDWARE, null);

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

        int debug = 2;
        switch (debug) {
            case 1:
                test1(canvas);
                break;
            case 2:
                test2(canvas);
                break;
        }
    }


    //状态保存
    private void test1(Canvas canvas) {
        canvas.save();
        transform(canvas);
        canvas.restore();

        canvas.save();
        clip(canvas);
        canvas.restore();

        canvas.drawColor(Color.parseColor("#9900ffff"));
    }

    //flag状态保存
    private void test2(Canvas canvas) {
        mPaint.reset();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);

        canvas.drawColor(Color.YELLOW);

        mPaint.setColor(Color.RED);
        canvas.drawRect(200, 200, 400, 400, mPaint);

        canvas.save();
        canvas.rotate(15);
        mPaint.setColor(Color.parseColor("#3300ff00"));
        canvas.drawRect(400, 400, 600, 600, mPaint);
        canvas.restore();

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(600, 400, 800, 600, mPaint);

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
