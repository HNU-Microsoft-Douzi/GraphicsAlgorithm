package priv.zxy.drawgraphics.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import priv.zxy.drawgraphics.R;
import priv.zxy.drawgraphics.custom_view.DrawSixPointerView;

/**
 * 创建人: Administrator
 * 创建时间: 2018/12/16
 * 描述: 建模正六边形
 **/

public class Fragment4 extends Fragment {

    private View view;

    private DrawSixPointerView sixPointerView;

    private SeekBar scaleBar;

    private SeekBar shiftBar;

    private SeekBar lengthwaysMoveBar;

    private SeekBar skewBar;

    /**
     * 分别记录上次两点的触碰记录
     */
    private int temp1Dx = 0;
    private int temp1Dy = 0;
    private int temp2Dx = 0;
    private int temp2Dy = 0;
    private float k1 = 0;//第一次的两点斜率
    private float k2 = 0;//第二次的两点斜率

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment4, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

        initEvent();
    }

    private void initView(){
        sixPointerView = view.findViewById(R.id.sixPointerView);

        scaleBar = view.findViewById(R.id.scaleBar);

        shiftBar = view.findViewById(R.id.shiftMoveBar);

        lengthwaysMoveBar = view.findViewById(R.id.lengthwaysMoveBar);

        skewBar = view.findViewById(R.id.skewBar);
    }

    /**
     * 这里的旋转和单点触碰有点问题
     * 因为旋转需要多点触碰，而多点触碰的基础就是肯定会至少先有一个指头触碰到屏幕，先执行一次单点触碰的内容。
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initEvent(){
        //控制正六芒星的位置
        sixPointerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int pointerCount = event.getPointerCount();//得到触碰点的个数(手指个数)
                if (pointerCount == 2){
                    int finger1Dx = (int)event.getX(0);
                    int finger1Dy = (int)event.getY(0);
                    int finger2Dx = (int)event.getX(1);
                    int finger2Dy = (int)event.getY(1);

                    switch (event.getActionMasked()){
                        case MotionEvent.ACTION_DOWN:
                            break;
                        case MotionEvent.ACTION_MOVE:
                            if (k1 != 0 && k2 != 0){
                                float degree = (float)Math.toDegrees(Math.atan((k1-k2)/(1+k1*k2)));
                                sixPointerView.setRotation(degree);
                            }
                            break;
                        case MotionEvent.ACTION_UP:

                            break;
                    }
                    temp1Dx = finger1Dx;
                    temp1Dy = finger1Dy;
                    temp2Dx = finger2Dx;
                    temp2Dy = finger2Dy;
                    if (finger1Dx != finger2Dx) k1 = Math.abs(finger1Dy - finger2Dy) / Math.abs(finger1Dx - finger2Dx);
                    if (temp2Dx != temp2Dy) k2 = Math.abs(temp1Dy - temp2Dy) / Math.abs(temp2Dx - temp2Dy);
                }
                return true;//如果在onTouch返回了false的话，事件就不会再继续呈递下去，后面的事件就无法再被检测到了。
            }
        });


        //控制正六边形的缩放大小
        scaleBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sixPointerView.setSixPointerStarSideLength(10 * progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //控制正方形的平移
        shiftBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sixPointerView.setSquareDx(15* progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        lengthwaysMoveBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sixPointerView.setSquareDy(17*progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        skewBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float k = (float)progress / 100.0f;
                sixPointerView.setSkew(k);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
