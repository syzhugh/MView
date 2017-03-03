package sun.mview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import sun.mview.R;

/**
 * Created by Yaozong on 2017/3/3.
 */

public class DrawContents extends View {

    private static final String TAG = "DrawContents";


    private int realWidth, realHeight;

    private Rect rect, textRect;

    /*attrs*/
    private int width, height, bgColor;
    private int imgSrc;
    private Bitmap img;

    private String text;
    private int textColor;
    private int textSize;

    /*tools*/
    private Paint paint;


    public DrawContents(Context context) {
        this(context, null);
    }

    public DrawContents(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawContents(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "------DrawContents------DrawContents");
        getAttrs(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        int mode_width, size_width, mode_height, size_height;
        mode_width = MeasureSpec.getMode(widthMeasureSpec);
        size_width = MeasureSpec.getSize(widthMeasureSpec);
        mode_height = MeasureSpec.getMode(heightMeasureSpec);
        size_height = MeasureSpec.getSize(heightMeasureSpec);

        if (mode_width == MeasureSpec.AT_MOST) {
        } else {
            realWidth = size_width;
        }

        if (mode_height == MeasureSpec.AT_MOST) {

        } else {
            realHeight = size_height;
        }

        setMeasuredDimension(realWidth, realHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void init() {
        paint = new Paint();
        img = BitmapFactory.decodeResource(getResources(), imgSrc);
        Log.i(TAG, "" + (img == null));

        rect = new Rect();
        textRect = new Rect();

        paint.reset();
//        paint.setTextSize(TextUtils.);
    }

    private void getAttrs(Context context, @Nullable AttributeSet attrs) {
//        TypedArray array = context.getResources().obtainAttributes(attrs, R.styleable.DrawContents);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DrawContents, 0, 0);
        int length = array.getIndexCount();
        for (int i = 0; i < length; i++) {
            int type = array.getIndex(i);
            switch (type) {
                case R.styleable.DrawContents_testview_width:
                    width = (int) array.getDimension(type, 0);
                    Log.i(TAG, "width:" + width);
                    break;
                case R.styleable.DrawContents_testview_height:
                    height = (int) array.getDimension(type, 0);
                    Log.i(TAG, "height:" + height);
                    break;
                case R.styleable.DrawContents_testview_bg:
                    bgColor = array.getColor(type, Color.parseColor("#000000"));
                    Log.i(TAG, "bgColor:" + bgColor);
                    break;
                case R.styleable.DrawContents_testview_pic:
                    imgSrc = array.getResourceId(type, -1);
                    Log.i(TAG, "imgSrc:" + imgSrc);
                    break;
                case R.styleable.DrawContents_testview_text:
                    text = array.getString(type);
                    Log.i(TAG, "content: " + (text == null) + "  " + text);
                    break;
                case R.styleable.DrawContents_testview_textcolor:
                    bgColor = array.getColor(type, Color.parseColor("#000000"));
                    Log.i(TAG, "textColor:" + textColor);
                    break;
                case R.styleable.DrawContents_testview_textsize:
                    textSize = array.getDimensionPixelSize(type,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                                    16.0f, getResources().getDisplayMetrics()));
                    Log.i(TAG, "textSize:" + textSize);
                    break;
            }
        }
        array.recycle();
    }
}
