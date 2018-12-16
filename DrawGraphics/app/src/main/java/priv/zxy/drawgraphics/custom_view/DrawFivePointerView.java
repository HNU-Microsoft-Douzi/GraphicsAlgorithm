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
        canvas.drawLine(A.getDx(), A.getDy(), B.getDx(), B.getDy(), paint);
        canvas.drawLine(B.getDx(), B.getDy(), C.getDx(), C.getDy(), paint);
        canvas.drawLine(C.getDx(), C.getDy(), D.getDx(), D.getDy(), paint);
        canvas.drawLine(D.getDx(), D.getDy(), E.getDx(), E.getDy(), paint);
        canvas.drawLine(E.getDx(), E.getDy(), A.getDx(), A.getDy(), paint);
    }

}
