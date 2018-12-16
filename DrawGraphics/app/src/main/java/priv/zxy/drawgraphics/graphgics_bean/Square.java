package priv.zxy.drawgraphics.graphgics_bean;

/**
 * 创建人: Administrator
 * 创建时间: 2018/12/16
 * 描述: 正方形基类
 **/

public class Square {

    private int sideLength = 0;

    private Dot center;

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    /**
     * @return 返回的是一个int形式的数组，数组大小为2，格式为[dx, dy]
     */
    public Dot getCenter() {
        return center;
    }

    public void setCenter(int dx, int dy) {
        this.center = new Dot(dx, dy);
    }
}
