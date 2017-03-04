package sun.mview.viewgroup;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Yaozong on 2017/3/3.
 */

public class TestViewGroup extends ViewGroup {
    private static final String TAG = "TestViewGroup";

    private int real_width, real_height;

    public TestViewGroup(Context context) {
        super(context);
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode_width = MeasureSpec.getMode(widthMeasureSpec);
        int size_width = MeasureSpec.getSize(widthMeasureSpec);
        int mode_height = MeasureSpec.getMode(heightMeasureSpec);
        int size_height = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        /*顺序
        * 0 1
        * 2 3
        * */
        int up_width = 0;
        int down_width = 0;
        int left_height = 0;
        int right_height = 0;

        View childView = null;
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams layoutParams = null;

        for (int i = 0; i < getChildCount(); i++) {
            childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            layoutParams = (MarginLayoutParams) childView.getLayoutParams();
            switch (i) {
                case 0:
                    up_width += cWidth + layoutParams.leftMargin + layoutParams.rightMargin;
                    left_height += cHeight + layoutParams.topMargin + layoutParams.bottomMargin;
                    break;
                case 1:
                    up_width += cWidth + layoutParams.leftMargin + layoutParams.rightMargin;
                    right_height += cHeight + layoutParams.topMargin + layoutParams.bottomMargin;
                    break;
                case 2:
                    down_width += cWidth + layoutParams.leftMargin + layoutParams.rightMargin;
                    left_height += cHeight + layoutParams.topMargin + layoutParams.bottomMargin;
                    break;
                case 3:
                    down_width += cWidth + layoutParams.leftMargin + layoutParams.rightMargin;
                    right_height += cHeight + layoutParams.topMargin + layoutParams.bottomMargin;
                    break;
            }
        }
        Log.i(TAG, "up_width:" + up_width + "  down_width:" + down_width);
        Log.i(TAG, "left_height:" + left_height + "  right_height:" + right_height);

        real_width = mode_width == MeasureSpec.AT_MOST ? Math.max(up_width, down_width) : size_width;
        real_height = mode_height == MeasureSpec.AT_MOST ? Math.max(left_height, right_height) : size_height;

        Log.i(TAG, "mode_width:" + mode_width + "  mode_height:" + mode_height);
        Log.i(TAG, "real_width:" + real_width + "  real_height:" + real_height);

        setMeasuredDimension(real_width, real_height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View childView;
        int cWidth;
        int cHeight;
        MarginLayoutParams layoutParams;
        int cl = 0, ct = 0, cr = 0, cb = 0;

        for (int i = 0; i < getChildCount(); i++) {
            childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            layoutParams = (MarginLayoutParams) childView.getLayoutParams();
            switch (i) {
                case 0:
                    cl = layoutParams.leftMargin;
                    ct = layoutParams.topMargin;
                    break;
                case 1:
                    cl = getWidth() - cWidth - layoutParams.rightMargin - layoutParams.leftMargin;
                    ct = layoutParams.topMargin;
                    break;
                case 2:
                    cl = layoutParams.leftMargin;
                    ct = getHeight() - cHeight - layoutParams.bottomMargin;
                    break;
                case 3:
                    cl = getWidth() - cWidth - layoutParams.rightMargin
                            - layoutParams.leftMargin;
                    ct = getHeight() - cHeight - layoutParams.bottomMargin;
                    break;
            }
            cr = cl + cWidth;
            cb = cHeight + ct;
            childView.layout(cl, ct, cr, cb);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
