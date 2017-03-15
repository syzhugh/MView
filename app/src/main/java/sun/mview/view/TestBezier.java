package sun.mview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

/**
 * Created by Yaozong on 2017/3/15.
 */

public class TestBezier extends View {
    private Paint mPaint;
    private float preX, preY;
    private Path mPath, wavePath;

    public TestBezier(Context context) {
        this(context, null);
    }

    public TestBezier(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestBezier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        testBezier(canvas);

        drawPath(canvas);
        drawWave(canvas);
    }

    private void testBezier(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);

        Path path = new Path();
        path.moveTo(200, 200);
        path.quadTo(250, 150, 300, 200);
        path.quadTo(350, 250, 400, 200);
        path.rQuadTo(50, -50, 100, 0);
        canvas.drawPath(path, mPaint);
    }

    private void drawPath(Canvas canvas) {
        mPath = new Path();
        mPaint.setColor(Color.YELLOW);
        canvas.drawPath(mPath, mPaint);
    }


    private float lumbda = 500, dx;

    private void drawWave(Canvas canvas) {
        mPaint.reset();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);

        wavePath = new Path();
        float initX = 0, initY = 600;

        wavePath.moveTo(-lumbda + dx, (getHeight() + 100) * (dx / 3 / lumbda));

        int length = 0;
        while (length < getWidth() + lumbda) {
            wavePath.rQuadTo(lumbda / 4, 100, lumbda / 2, 0);
            wavePath.rQuadTo(lumbda / 4, -100, lumbda / 2, 0);
            length += lumbda;
        }

        wavePath.lineTo(getWidth(), getHeight());
        wavePath.lineTo(0, getHeight());
        wavePath.close();

        canvas.drawPath(wavePath, mPaint);
    }

    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, lumbda);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

    //bezier实现画笔
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                preX = event.getX();
                preY = event.getY();
                mPath.moveTo(preX, preY);
                return true;
            }
            case MotionEvent.ACTION_MOVE:
                float endX = (preX + event.getX()) / 2;
                float endY = (preY + event.getY()) / 2;
                mPath.quadTo(preX, preY, endX, endY);
                preX = event.getX();
                preY = event.getY();
                invalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    //lineto实现画笔
    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mPath.moveTo(event.getX(), event.getY());
                return true;
            }
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                invalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }*/
}
