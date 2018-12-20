package priv.zxy.drawgraphics.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static java.lang.StrictMath.abs;

public class DrawCircleView extends View {
    private float radius = 0;
    private int color = Color.WHITE;
    private int x;
    private int y;
    private int width = 0;
    private int height = 0;

    public DrawCircleView(Context context) {
        super(context);
    }

    public DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setRadius(float radius){
        this.radius = radius;
        invalidate();
    }

    public void setX(float x){
        this.x = (int)x;
    }

    public void setY(float y){
        this.y = (int)y;
    }

    public void setColor(int c){
        color = c;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCoordinateAxis(canvas, Color.WHITE);
        drawCircle(radius, canvas, color);
    }

    private void drawCircle(float radius, Canvas canvas, int color){
        Paint paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        int pointX = x;
        int pointY = y + (int)radius;
        int d = 1 - (int)radius;
        int delta1 = 3;
        int delta2 = 5 - (int)radius - (int)radius;

        canvas.drawPoint(pointX, pointY, paint);
        canvas.drawPoint(pointY, pointX, paint);
        canvas.drawPoint(getWidth() - pointX + 2 * abs(x - width), getHeight() - pointY + 2 * abs(y - height), paint);
        canvas.drawPoint(getHeight() - pointY + 2 * abs(x - width), getWidth() - pointX + 2 * abs(y - height), paint);
        canvas.drawPoint(pointX, getHeight() - pointY + 2 * abs(y - height), paint);
        canvas.drawPoint(pointY, getWidth() - pointX + 2 * abs(y - height), paint);
        canvas.drawPoint(getWidth() - pointX + 2 * abs(x - width), pointY, paint);
        canvas.drawPoint(getHeight() - pointY + 2 * abs(x - width), pointX, paint);
        while(pointX < pointY){
            if(d < 0){
                d += delta1;
                delta1 += 2;
                delta2 += 2;
                pointX ++;
            }else{
                d += delta2;
                delta1 += 2;
                delta2 += 4;
                pointX ++;
                pointY --;
            }
            canvas.drawPoint(pointX, pointY, paint);
            canvas.drawPoint(pointY, pointX, paint);
            canvas.drawPoint(getWidth() - pointX + 2 * abs(x - width), getHeight() - pointY + 2 * abs(y - height), paint);
            canvas.drawPoint(getHeight() - pointY + 2 * abs(x - width), getWidth() - pointX + 2 * abs(y - height), paint);
            canvas.drawPoint(pointX, getHeight() - pointY + 2 * abs(y - height), paint);
            canvas.drawPoint(pointY, getWidth() - pointX + 2 * abs(y - height), paint);
            canvas.drawPoint(getWidth() - pointX + 2 * abs(x - width), pointY, paint);
            canvas.drawPoint(getHeight() - pointY + 2 * abs(x - width), pointX, paint);
        }
    }

    private void drawCoordinateAxis(Canvas canvas, int color){
        width = getWidth() / 2;
        height = getHeight() / 2;
        @SuppressLint("DrawAllocation") Paint paint_line = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        paint_line.setStyle(Paint.Style.STROKE);
        paint_line.setStrokeWidth(3);
        paint_line.setColor(color);
        canvas.drawLine(0, height, getWidth(), height, paint_line);
        canvas.drawLine(getWidth() - 35, height - 20, getWidth(), height, paint_line);
        canvas.drawLine(getWidth() - 35, height + 20, getWidth(), height, paint_line);
        canvas.drawLine(width - 20, getHeight() - 35, width, getHeight(), paint_line);
        canvas.drawLine(width + 20, getHeight() - 35, width, getHeight(), paint_line);
        canvas.drawLine(width, 0, width, getHeight(), paint_line);

        Paint paint_text = new Paint();
        paint_text.setColor(color);
        paint_text.setStrokeWidth(3);
        paint_text.setTextSize(60);
        canvas.drawText("x", getWidth() - 50, height + 50, paint_text);
        canvas.drawText("y", width + 25, getHeight() - 50, paint_text);
    }
}
