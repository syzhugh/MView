package sun.mview.animation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Yaozong on 2017/3/18.
 */

public class TestValueAnimation extends View {
    private Paint mPaint;

    public TestValueAnimation(Context context) {
        this(context, null);
    }

    public TestValueAnimation(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestValueAnimation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    }
}
