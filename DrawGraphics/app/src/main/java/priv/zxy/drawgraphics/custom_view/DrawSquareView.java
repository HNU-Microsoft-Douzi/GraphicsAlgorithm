package priv.zxy.drawgraphics.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import priv.zxy.drawgraphics.graphgics_bean.Dot;
import priv.zxy.drawgraphics.graphgics_bean.Square;

/**
 * 创建人: Administrator
 * 创建时间: 2018/12/16
 * 描述:自定义画正方形的View
 *
 * 注意:自定义View的时候，三个构造函数必须全部写上，不然的话就会报错
 **/

public class DrawSquareView extends View {

    private Square square;

    private Paint paint;

    private int color = Color.WHITE;

    private float k = 0.0f;

    public DrawSquareView(Context context) {
        super(context);
        initView();
    }

    public DrawSquareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DrawSquareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        square = new Square();
        square.setSideLength(20);//设置正方形的初始化边长
        square.setCenter(0, 0);//设置正方形的中心为原点(左上角)

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);//添加抗锯齿效果
        paint.setStrokeWidth(2);//设置画笔宽度为2像素
        paint.setStyle(Paint.Style.FILL);//设置画笔的属性:STROKE为线条,FILL为填充，FILL_AND_STROKE为线条+填充
        paint.setColor(color);//颜色
    }

    public void setSquareSideLength(int length){
        square.setSideLength(length);
        invalidate();
    }

    public void setSquareDx(int dx){
        square.setCenter(dx, square.getCenter().getDy());
        invalidate();
    }

    public void setSquareDy(int dy){
        square.setCenter(square.getCenter().getDx(), dy);
        invalidate();
    }

    public void setSquareCenter(int dx, int dy){
        square.setCenter(dx, dy);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSquare(canvas);
    }

    private void drawSquare(Canvas canvas){
        Dot squareCenter = square.getCenter();
        int halfSideLength = square.getSideLength() / 2;

        Dot left_up = new Dot(squareCenter.getDx() - halfSideLength, squareCenter.getDy() - halfSideLength);
        Dot right_up = new Dot(squareCenter.getDx() + halfSideLength, squareCenter.getDy() - halfSideLength);
        Dot left_down = new Dot(squareCenter.getDx() - halfSideLength, squareCenter.getDy() + halfSideLength);
        Dot right_down = new Dot(squareCenter.getDx() + halfSideLength, squareCenter.getDy() + halfSideLength);

        Path path = new Path();
        /**
         * 画线其实可以用PresenhamLine算法封装成函数后实现，这里为了方便起见直接调用canvase的drawLine函数
         */
        path.moveTo(left_up.getDx() + k*left_up.getDy(), left_up.getDy() + k*left_up.getDx());
        path.lineTo(right_up.getDx()+ k*left_up.getDy(), right_up.getDy() + k*right_up.getDx());
        path.lineTo(right_down.getDx()+ k*left_up.getDy(), right_down.getDy() + k*right_down.getDx());
        path.lineTo(left_down.getDx()+ k*left_up.getDy(), left_down.getDy() + k*left_down.getDx());
        path.lineTo(left_up.getDx()+ k*left_up.getDy(), left_up.getDy() + k*left_up.getDx());
        /**
         * 对于填充而言，我们可以使用填充算法，但是我这里直接使用canvas的画正方形来进行填充
         */
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
