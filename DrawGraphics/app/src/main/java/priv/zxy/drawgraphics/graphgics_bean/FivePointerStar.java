package priv.zxy.drawgraphics.graphgics_bean;

/**
 * 创建人: Administrator
 * 创建时间: 2018/12/16
 * 描述: 五角星基类
 **/

public class FivePointerStar {
    private Dot center;

    private int R;

    private int r;

    public Dot getCenter() {
        return center;
    }

    public void setCenter(int dx, int dy) {
        this.center = new Dot(dx, dy);
    }

    public int getR() {
        return R;
    }

    public void setR(int biggerRadius) {
        this.R = biggerRadius;
        this.r = (int)(R*Math.sin(18)/Math.sin(36));
    }

    public void getr(int smallerRadius){
        this.r = smallerRadius;
    }
}
