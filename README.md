# GraphicsAlgorithm

## 画图

本项目利用自定义View实现了各个图形的图形学算法。

我利用常用的算法譬如**`PresenhamLine`算法**实现了直线的画法。

利用了**中点画线法**实现了圆的算法。

而对于正方形、六芒星和五角星，我用画点的方式进行图像的描述，然后用Canvas自身的Paint实现了图像的填充。

同时，为了提升效果的趣味性，我特意增加了图形的颜色选择，你可以通过点击每个页面右上角的彩色圆盘来实现色彩的选择。

在`android`中，为了实现矩阵的变换效果，有两种方案：

- 利用点的变换来动态改变每个图形的点的位置。
- 利用getMatrix获得所画图形的Matrix，然后利用Matrix的API实现Matrix的变换

这里利用动态改变点的位置实现动态变换效果。

旋转效果的话，用了多点触碰的方式进行实现，可能会产生较为大的抖动，因为本质上是检测多点的一个斜率，而由于手指不断移动，所以抖动效果会比较强烈，没有一个好的降低抖动的方式，想法是可以使用线程来暂停0.3s实现减轻抖动，但时间原因未曾实现。

### 概论

然后画线的算法是实现在自定义View中的，而所有逇自定义View全部都在custom_view中。

为了方便的展示产生的效果，我将单独的效果封装到一个单独的Fragment当中，你可以在fragment这个目录下看到fragment的排名，而直线的Fragment就是fragment1。

### 画线

画线算法采用的是PresenhamLine算法，我将每个点的坐标抽象成了一个坐标类作为操作过程的维度基类。

而画线的算法定义在了custom_view下的DrawhamLineView中，它继承于View超类，内有onDraw函数，用来实现绘图的显示，没有关闭硬件加速，没有改动双缓冲机制。

![](https://i.imgur.com/wIjz2e6.png)

### 画圆

画圆采用了中点画线法，具体的算法和画线是一样的，写在DrawCircleView当中。

然后画圆的内容显示页面为fragment2

![](https://i.imgur.com/7PhO8kB.png)

### 画正方形

自定义View为DrawSquareView，Fragment为fragment3，算法是设置屏幕左上角作为正方形的中心，然后利用四个ProgressBar实现正方形的变换效果。

![](https://i.imgur.com/EufULCP.png)

### 画六芒星

自定义View为DrawFivePointerView，Fragment为fragment4，算法是设置屏幕左上角作为正方形的中心，然后利用四个ProgressBar实现正方形的变换效果。

![](https://i.imgur.com/Z6Fn3uX.png)

### 画五角星

自定义View为DrawSixPointerView，Fragment为fragment5，算法是设置屏幕左上角作为正方形的中心，然后利用四个ProgressBar实现正方形的变换效果。

![](https://i.imgur.com/RaYCeMC.png)

### 颜色变换

点击彩色圆盘的时候，生成一个dialog显示在界面上，注意Dialog依附于Activity而存在，所以传递getContext()的时候，就传递getActivity()方法即可。

![](https://i.imgur.com/ofngM8T.png)