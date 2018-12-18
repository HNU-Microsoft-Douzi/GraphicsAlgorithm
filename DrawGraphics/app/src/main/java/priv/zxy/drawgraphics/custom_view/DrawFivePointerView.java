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
import priv.zxy.drawgraphics.graphgics_bean.FivePointerStar;

/**
 * 创建人: Administrator
 * 创建时间: 2018/12/16
 * 描述: 自定义View画五角星
 **/

public class DrawFivePointerView extends View {

    private FivePointerStar fivePointerStar;
    private Paint paint;
    private int color = Color.BLACK;
    private float k = 0.0f;

    public DrawFivePointerView(Context context) {
        super(context);
        initView();
    }

    public DrawFivePointerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DrawFivePointerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        fivePointerStar = new FivePointerStar();
        fivePointerStar.setR(20);//设置五角星的大半径
        fivePointerStar.setCenter(0, 0);//设置六芒星的中心为原点(左上角)

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);//添加抗锯齿效果
        paint.setStrokeWidth(2);//设置画笔宽度为2像素
        paint.setStyle(Paint.Style.FILL_AND_STROKE);//设置画笔的属性:STROKE为线条,FILL为填充，FILL_AND_STROKE为线条+填充
        paint.setColor(color);//颜色

    }

    public void setFivePointerStarSideRadius(int length){
        fivePointerStar.setR(length);
        invalidate();
    }

    public void setFivePointerStarSideDx(int dx){
        fivePointerStar.setCenter(dx, fivePointerStar.getCenter().getDy());
        invalidate();
    }

    public void setFivePointerStarSideDy(int dy){
        fivePointerStar.setCenter(fivePointerStar.getCenter().getDx(), dy);
        invalidate();
    }

    public void setFivePointerStarCenter(int dx, int dy){
        fivePointerStar.setCenter(dx, dy);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSixPointer(canvas);
    }

    private void drawSixPointer(Canvas canvas){
        Dot center = fivePointerStar.getCenter();

        int x = center.getDx();
        int y = center.getDy();

        int R = fivePointerStar.getR();
        Dot A = new Dot(x, y+R);
        Dot B = new Dot(x+(int)(R*Math.cos(18)), y+(int)(R*Math.sin(18)));
        Dot C = new Dot(x+(int)(R*Math.cos(54)), y-(int)(R*Math.sin(54)));
        Dot D = new Dot(x-(int)(R*Math.cos(54)), y-(int)(R*Math.sin(54)));
        Dot E = new Dot(x-(int)(R*Math.cos(18)), y+(int)(R*Math.sin(18)));
        canvas.drawLine(A.getDx() + k*A.getDy(), A.getDy() + k*A.getDx(), B.getDx() + k*B.getDy(), B.getDy() + k*B.getDx(), paint);
        canvas.drawLine(B.getDx() + k*B.getDy(), B.getDy() + k*B.getDx(), C.getDx() + k*C.getDy(), C.getDy() + k*C.getDx(), paint);
        canvas.drawLine(C.getDx() + k*C.getDy(), C.getDy() + k*C.getDx(), D.getDx() + k*D.getDy(), D.getDy() + k*D.getDx(), paint);
        canvas.drawLine(D.getDx() + k*D.getDy(), D.getDy() + k*D.getDx(), E.getDx() + k*E.getDy(), E.getDy() + k*E.getDx(), paint);
        canvas.drawLine(E.getDx() + k*E.getDy(), E.getDy() + k*E.getDx(), A.getDx() + k*A.getDy(), A.getDy() + k*A.getDx(), paint);
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
