package priv.zxy.drawgraphics.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PresenhamLine extends View {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int width = 0;
    private int height = 0;

    public PresenhamLine(Context context) {
        super(context);
    }

    public PresenhamLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PresenhamLine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCoordinate(int x1, int y1, int x2, int y2) {
        width = getWidth() / 2;
        height = getHeight() / 2;
//        if(y2 < (float)(height / width) && x2 > width && y2 > height){
//
//        }
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
        invalidate();
    }

    private void setX1(int x1) {
        this.x1 = x1 + width;
    }

    private void setY1(int y1) {
        this.y1 = y1 + height;
    }

    private void setY2(int y2) {
        this.y2 = y2;
    }

    private void setX2(int x2) {
        this.x2 = x2;
    }

    private void BresenhamLine(Canvas canvas, Paint paint, int x1, int y1, int x2, int y2) {
        if (x1 > x2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
            temp = y1;
            y1 = y2;
            y2 = temp;
        }
        int x, y, dx, dy, p;
        x = x1;
        y = y1;
        dx = x2 - x1;
        dy = y2 - y1;
        float k = (float) dy / (float) dx;
        if (k > 0 && k < 1) {
            p = 2 * dy - dx;
            for (; x <= x2; x++) {
                canvas.drawPoint(x, y, paint);

                if (p >= 0) {
                    y++;
                    p += 2 * (dy - dx);
                } else {
                    p += 2 * dy;
                }
            }
        } else if (k > 1) {
            p = dy;
            while (y < y2) {
                y++;
                if (p < 0) {
                    p += 2 * dx;
                } else {
                    x++;
                    p += 2 * (dx - dy);
                }
                canvas.drawPoint(x, y, paint);
            }
        } else if (k > -1 && k < 0) {
            p = 2 * dy + dx;
            while (x < x2) {
                x++;
                if (p >= 0) {
                    p += 2 * dy;
                } else {
                    y--;
                    p += 2 * (dy + dx);
                }
                canvas.drawPoint(x, y, paint);
            }
        } else if (k <= -1) {
            p = 2 * dx - dy;
            while (y > y2) {
                y--;
                if (p >= 0) {
                    p -= 2 * dx;
                } else {
                    x++;
                    p -= 2 * (dx + dy);
                }
                canvas.drawPoint(x, y, paint);
            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCoordinateAxis(canvas, Color.WHITE);
        @SuppressLint("DrawAllocation") Paint paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(Color.WHITE);
        paint.setStrokeCap(Paint.Cap.SQUARE);//点的形状为正方形
//        canvas.drawPoint(100,100, paint);
        BresenhamLine(canvas, paint, x1, y1, x2, y2);

    }

    //    private void drawCoordinateAxis(Canvas canvas){
//        @SuppressLint("DrawAllocation") Paint paint_line = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
//        paint_line.setStyle(Paint.Style.STROKE);
//        paint_line.setStrokeWidth(3);
//        paint_line.setColor(Color.BLACK);
//        canvas.drawLine(0, height, getWidth(), height, paint_line);
//        canvas.drawLine(getWidth() - 35, height - 20, getWidth(), height, paint_line);
//        canvas.drawLine(getWidth() - 35, height + 20, getWidth(), height, paint_line);
//        canvas.drawLine(width - 20, getHeight() - 35, width, getHeight(), paint_line);
//        canvas.drawLine(width + 20, getHeight() - 35, width, getHeight(), paint_line);
//        canvas.drawLine(width, 0, width, getHeight(), paint_line);
//
//        Paint paint_text = new Paint();
//        paint_text.setColor(Color.BLACK);
//        paint_text.setStrokeWidth(3);
//        paint_text.setTextSize(60);
//        canvas.drawText("x", getWidth()-50, height+50, paint_text);
//        canvas.drawText("y", width + 25, getHeight() - 50, paint_text);
//    }
    private void drawCoordinateAxis(Canvas canvas, int color) {
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