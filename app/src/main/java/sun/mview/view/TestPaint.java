package sun.mview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Yaozong on 2017/3/5.
 */

public class TestPaint extends View {

    private static final String TAG = "TestPaint";

    private int real_width, real_height;
    private Paint paint;

    public TestPaint(Context context) {
        this(context, null);
    }

    public TestPaint(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public TestPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "------TestPaint------TestPaint");
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode_width = MeasureSpec.getMode(widthMeasureSpec);
        int size_width = MeasureSpec.getSize(widthMeasureSpec);
        int mode_height = MeasureSpec.getMode(heightMeasureSpec);
        int size_height = MeasureSpec.getSize(heightMeasureSpec);

        real_width = mode_width == MeasureSpec.AT_MOST ? 300 : size_width;
        real_height = mode_height == MeasureSpec.AT_MOST ? 300 : size_height;

        setMeasuredDimension(real_width, real_height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        setPaint(canvas);

//        drawPoint(canvas);
//        drawLine(canvas);
//        drawTri(canvas);
//        drawRect(canvas);
//        drawCicle(canvas);
//        drawOval(canvas);
//        drawArc(canvas);

//        drawPath(canvas);
//        drawImg(canvas);
//        drawText(canvas);
    }


    /*paint属性配置*/
    private void setPaint(Canvas canvas) {
        Log.i(TAG, "------TestPaint------setPaint");
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(40);
        paint.setAlpha(Integer.parseInt("66", 16));
    }

    /*基本图形*/
    private void drawPoint(Canvas canvas) {
        float[] pts = new float[]{
                100, 400,
                200, 400,
                300, 400,
                400, 400,
                500, 400,
                600, 400
        };
        canvas.drawPoints(pts, 2, 2, paint);
    }

    private void drawLine(Canvas canvas) {
        paint.setStrokeCap(Paint.Cap.ROUND);//圆角
        canvas.drawLine(100, 100, 100, 200, paint);

        paint.setStrokeCap(Paint.Cap.BUTT);//缺角
        canvas.drawLine(200, 100, 200, 200, paint);

        paint.setStrokeCap(Paint.Cap.SQUARE);//方角
        canvas.drawLine(300, 100, 300, 200, paint);

        float[] pts = new float[]{
                100, 400,
                200, 400,
                300, 400,
                400, 400,
                500, 400,
                600, 400
        };
        canvas.drawLines(pts, 0, 8, paint);
//        canvas.drawLines(pts, 2, 8, paint);//起点会发生变化
    }

    private void drawTri(Canvas canvas) {
        Path triPath = new Path();
        triPath.moveTo(100, 100);
        triPath.lineTo(100, 200);
        triPath.lineTo(500, 200);
        triPath.close();
        canvas.drawPath(triPath, paint);
    }

    private void drawRect(Canvas canvas) {
        Rect rect = new Rect(100, 100, 200, 200);
        canvas.drawRect(rect, paint);

        rect = new Rect(300, 100, 400, 200);
        canvas.drawRoundRect(new RectF(rect), 90, 90, paint);
    }

    private void drawCicle(Canvas canvas) {
        //半径，填充
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200, 200, 100, paint);

        //半径+线宽，描边
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(400, 200, 100, paint);

        //半径+线宽，描边+填充
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(600, 200, 100, paint);
    }

    private void drawOval(Canvas canvas) {
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(100, 100, 300, 200);
        canvas.drawRect(rectF, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        canvas.drawOval(rectF, paint);
    }

    private void drawArc(Canvas canvas) {
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(100, 100, 300, 200);
        canvas.drawArc(rectF, 0, 270, false, paint);

        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
        rectF = new RectF(100, 300, 300, 400);
        canvas.drawArc(rectF, 0, 270, false, paint);

        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
        rectF = new RectF(100, 500, 300, 600);
        canvas.drawArc(rectF, 0, 270, true, paint);
    }

    /*特殊*/
    private void drawPath(Canvas canvas) {
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(50, 50);
        path.lineTo(50, 150);
        path.lineTo(150, 150);
        path.lineTo(200, 200);//坐标相对于起点
//        path.rLineTo(200, 200);//坐标相对于上条线的终点

        //坐标会发生跳转
        path.addRect(300, 300, 400, 400, Path.Direction.CW);
        path.lineTo(50, 500);

        path.addArc(100, 100, 200, 200, 0, 270);
        path.lineTo(50, 500);

        path.arcTo(500, 500, 600, 700, 0, 270, false);//可以添加一条连接线
        path.lineTo(50, 500);

        //发生相对位移
        path.rMoveTo(50, 50);//相对于上一段的终点
        path.lineTo(0, 900);//取原始坐标下的点
//        path.rLineTo(50, 50);
        canvas.drawPath(path, paint);

        paint.setColor(Color.GREEN);
        Path path1 = new Path();
        path1.addPath(path, 300, 100);
        canvas.drawPath(path1, paint);

    }

    private void drawImg(Canvas canvas) {
    }

    private void drawText(Canvas canvas) {
    }
}
