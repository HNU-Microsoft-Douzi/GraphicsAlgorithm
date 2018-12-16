package priv.zxy.drawgraphics.graphgics_bean;

/**
 * 创建人: Administrator
 * 创建时间: 2018/12/16
 * 描述:六芒星基类
 **/

public class SixPointerStar {

    private Dot center;

    private int sideLength;

    public Dot getCenter() {
        return center;
    }

    public void setCenter(int dx, int dy) {
        this.center = new Dot(dx, dy);
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }
}
