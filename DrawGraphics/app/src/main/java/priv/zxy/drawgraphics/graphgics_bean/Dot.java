package priv.zxy.drawgraphics.graphgics_bean;

/**
 * 创建人: Administrator
 * 创建时间: 2018/12/16
 * 描述: Dot基类
 **/

public class Dot {

    public Dot(int x, int y){
        dx = x;
        dy = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    private int dx;

    private int dy;
}
