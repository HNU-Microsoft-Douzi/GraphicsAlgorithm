package priv.zxy.drawgraphics.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import priv.zxy.drawgraphics.graphgics_bean.Dot;
import priv.zxy.drawgraphics.graphgics_bean.SixPointerStar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/12/16
 * 描述:
 **/
public class DrawSixPointerView extends View {

    private static final String TAG = "DrawSixPointerView";

    private SixPointerStar sixPointerStar;

    private Paint paint;

    private int color = Color.WHITE;

    private float k = 0.0f;

    public DrawSixPointerView(Context context) {
        super(context);
        initView();
    }

    public DrawSixPointerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DrawSixPointerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void setSquareDx(int dx){
        sixPointerStar.setCenter(dx, sixPointerStar.getCenter().getDy());
        invalidate();
    }

    public void setSquareDy(int dy){
        sixPointerStar.setCenter(sixPointerStar.getCenter().getDx(), dy);
        invalidate();
    }

    private void initView(){
        sixPointerStar = new SixPointerStar();
        sixPointerStar.setSideLength(20);//设置正六芒星的初始化边长
        sixPointerStar.setCenter(0, 0);//设置六芒星的中心为原点(左上角)

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);//添加抗锯齿效果
        paint.setStrokeWidth(2);//设置画笔宽度为2像素
        paint.setStyle(Paint.Style.FILL_AND_STROKE);//设置画笔的属性:STROKE为线条,FILL为填充，FILL_AND_STROKE为线条+填充
        paint.setColor(color);//颜色
    }

    public void setSixPointerStarSideLength(int length){
        sixPointerStar.setSideLength(length);
        invalidate();
    }

    public void setSixPointerStarCenter(int dx, int dy){
        sixPointerStar.setCenter(dx, dy);
        invalidate();
    }

    /**
     * 实际测试发现:
     * onDraw并不是没帧都调用的，为了节省，只有在界面发生变化的时候，才会调用onDraw函数
     * @param canvas 画布
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSixPointer(canvas);
    }

    private void drawSixPointer(Canvas canvas){
        Dot center = sixPointerStar.getCenter();
        int halfSideLength = sixPointerStar.getSideLength() / 2;

        Dot left_up = new Dot(center.getDx()-halfSideLength, center.getDy()+(int)(Math.sqrt(3)*halfSideLength));
        Dot right_up = new Dot(center.getDx()+halfSideLength, center.getDy()+(int)(Math.sqrt(3)*halfSideLength));
        Dot left_down = new Dot(center.getDx()-halfSideLength, center.getDy()-(int)(Math.sqrt(3)*halfSideLength));
        Dot right_down = new Dot(center.getDx()+halfSideLength, center.getDy()-(int)(Math.sqrt(3)*halfSideLength));
        Dot left = new Dot(center.getDx()-sixPointerStar.getSideLength(), center.getDy());
        Dot right = new Dot(center.getDx()+sixPointerStar.getSideLength(), center.getDy());
        Path path = new Path();
        path.moveTo(left_up.getDx() + k*left_up.getDy(), left_up.getDy() + k*left_up.getDx());
        path.lineTo(right_up.getDx() + k*right_up.getDy(), right_up.getDy() + k*right_up.getDx());
        path.lineTo(right.getDx() + k*right.getDy(), right.getDy() + k*right.getDx());
        path.lineTo(right_down.getDx() + k*right_down.getDy(), right_down.getDy() + k*right_down.getDx());
        path.lineTo(left_down.getDx() + k*left_down.getDy(), left_down.getDy() + k*left_down.getDx());
        path.lineTo(left.getDx() + k*left.getDy(), left.getDy() + k*left.getDx());
        path.lineTo(left_up.getDx() + k*left_up.getDy(), left_up.getDy() + k*left_up.getDx());
        canvas.drawPath(path, paint);
    }

    /**
     * 错切：水平和垂直同时错切
     * @param k 水平方向和垂直方向的斜率
     */
    public void setSkew(float k){
        this.k = k;
        invalidate();
    }

}
