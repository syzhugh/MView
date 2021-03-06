package sun.mview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import sun.mview.R;

/**
 * Created by Yaozong on 2017/3/2.
 */

public class TestAttrs extends View {
    private static final String TAG = "Test";

    private int height, width;

    public TestAttrs(Context context) {
        this(context, null);
    }

    public TestAttrs(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        context.getTheme().obtainStyledAttributes(attrs, R.styleable.TestView, defStyleAttr, 0);
        TypedArray array = context.getResources().obtainAttributes(attrs, R.styleable.TestView);
        int length = array.getIndexCount();

        for (int i = 0; i < length; i++) {
            Log.i(TAG, "i:" + i);
            int type = array.getIndex(i);
            switch (type) {
                case R.styleable.TestView_testview_height:
                    Log.i(TAG, "testview_height:");
                    Log.i(TAG, ":" + array.getDimension(type, -1));
                    break;
                case R.styleable.TestView_testview_width:
                    Log.i(TAG, "testview_width:");
                    Log.i(TAG, ":" + array.getDimension(type, -1));
                    break;
                case R.styleable.TestView_testview_bg:
                    Log.i(TAG, "testview_bg:");
                    Log.i(TAG, ":" + array.getColor(type, -1));
                    break;
            }
        }
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "------TestView------onMeasure");
        Log.i(TAG, "widthMeasureSpec:" + Integer.toBinaryString(widthMeasureSpec));
        Log.i(TAG, "heightMeasureSpec:" + Integer.toBinaryString(heightMeasureSpec));

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        Log.i(TAG, "widthMode:" + Integer.toBinaryString(widthMode));
        Log.i(TAG, "widthSize:" + widthSize);
        Log.i(TAG, "heightMode:" + Integer.toBinaryString(heightMode));
        Log.i(TAG, "heightSize:" + heightSize);

        int desiredWidth, desiredHeight;

        if (widthMode == MeasureSpec.AT_MOST) {
            Log.i(TAG, "width  AT_MOST");
            desiredWidth = 50;
        } else if (widthMode == MeasureSpec.EXACTLY) {
            Log.i(TAG, "width  EXACTLY");
            desiredWidth = widthSize;
        } else {
            desiredWidth = widthSize;
        }


        if (heightMode == MeasureSpec.AT_MOST) {
            Log.i(TAG, "height  AT_MOST");
            desiredHeight = 50;
        } else if (heightMode == MeasureSpec.EXACTLY) {
            Log.i(TAG, "height  EXACTLY");
            desiredHeight = heightSize;
        } else {
            desiredHeight = heightSize;
        }

        setMeasuredDimension(desiredWidth, desiredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i(TAG, "------TestView------onLayout");
        Log.i(TAG, "left:" + left);
        Log.i(TAG, "top:" + top);
        Log.i(TAG, "right:" + right);
        Log.i(TAG, "bottom:" + bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i(TAG, "getMeasuredWidth:" + getMeasuredWidth() + "getMeasuredHeight:" + getMeasuredHeight());
        Log.i(TAG, "getWidth:" + getWidth() + "getHeight:" + getHeight());

    }
}
