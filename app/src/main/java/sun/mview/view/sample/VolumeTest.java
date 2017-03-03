package sun.mview.view.sample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.blankj.utilcode.utils.ConvertUtils;

import sun.mview.R;

/**
 * Created by Yaozong on 2016/10/5.
 */

public class VolumeTest extends View {

    private int color;
    private int width;
    private int height;

    private Bitmap bitmap;

    private Paint paint;


    public VolumeTest(Context context) {
        this(context, null);
    }

    public VolumeTest(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VolumeTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VolumeTest, defStyleAttr, 0);

        int length = array.length();
        for (int i = 0; i < length; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.VolumeTest_test_color:
                    color = array.getColor(attr, Color.BLACK);
                    break;
            }
        }
        array.recycle();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.volume_1);
        paint = new Paint();

        width = ConvertUtils.dp2px(context, 40);
        height = width;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        paint.setColor(color);
        RectF rectF1 = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(rectF1, 25, 25, paint);

        paint.setAlpha(0);
        RectF rectFPic = new RectF(
                (getWidth() - width) / 2,
                getHeight() * 0.15f, (getWidth() + width) / 2,
                height + getHeight() * 0.15f);

        paint.reset();
        canvas.drawBitmap(bitmap, null, rectFPic, paint);


        Paint paint = new Paint();
        int save1 = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        paint.setStrokeWidth(35);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);

        canvas.drawLine(getWidth() * 0.15f,
                rectFPic.bottom + getHeight() * 0.15f,
                getWidth() * 0.85f,
                rectFPic.bottom + getHeight() * 0.15f, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        paint.setStrokeWidth(32);
        paint.setColor(color);
        canvas.drawLine(getWidth() * 0.15f,
                rectFPic.bottom + getHeight() * 0.15f,
                getWidth() * 0.85f,
                rectFPic.bottom + getHeight() * 0.15f, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(save1);

        paint.setStrokeWidth(25);
        paint.setColor(Color.WHITE);

        float temp = 0;
        if (progress <= 0f)
            temp = 0.15f;
        else if (progress >= 1.0f) {
            temp = 0.85f;
        } else {
            temp = progress;
        }

        canvas.drawLine(getWidth() * 0.15f,
                rectFPic.bottom + getHeight() * 0.15f,
                getWidth() * temp,
                rectFPic.bottom + getHeight() * 0.15f, paint);
        paint.setXfermode(null);



    }

    private float progress = 0;

    public void changeVolume(float temp) {
        this.progress = temp;
        invalidate();
    }

}
