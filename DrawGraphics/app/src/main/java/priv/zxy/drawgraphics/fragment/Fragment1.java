package priv.zxy.drawgraphics.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import priv.zxy.drawgraphics.R;
import priv.zxy.drawgraphics.custom_view.PresenhamLine;

public class Fragment1 extends Fragment {
    private PresenhamLine drawLine;
    private TextView getX;
    private TextView getY;
    private View view;
    private Button choose;

    AlertDialog colorDialog= ColorPickerDialogBuilder
            .with(this.getContext())
            .setTitle("Choose color")//标题
            //初始样式，这里要同时要设置透明度默认是透明度最大
            .initialColor(R.color.deepGray)
            //设置是圆形还是花型
            .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
            //.wheelType(ColorPickerView.WHEEL_TYPE.CIRCLE)
            .density(10)//设置密集度值越大，越密集
            //设置监听事件
            .setOnColorSelectedListener(new OnColorSelectedListener() {
                @Override
                public void onColorSelected(int selectedColor) {
                    Toast.makeText(Fragment1.this.getActivity(),"onColorSelected: 0x" + Integer.toHexString(selectedColor),Toast.LENGTH_LONG).show();
                }
            })
            //确定和取消按钮，这里没有颜色设置的选项，但是可以修改源码
            .setPositiveButton("ok", new ColorPickerClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                    //颜色选中后需要做的事情
                    //    changeBackgroundColor(selectedColor);
                }
            })
            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            })
            .build();

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int x2 = msg.arg1;
            int y2 = msg.arg2;
            drawLine.setCoordinate(0, 0, x2, y2);
            getX.setText("" + (x2 - drawLine.getWidth()/2));
            getY.setText("" + (y2 - drawLine.getHeight()/2));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment1,null);
       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

        initEvent();
    }

    public void initView(){
        drawLine = view.findViewById(R.id.drawLine);
        getX = view.findViewById(R.id.getX);
        getY = view.findViewById(R.id.getY);
        choose = view.findViewById(R.id.chooseBt);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initEvent(){
        drawLine.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Message msg = new Message();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        msg.arg1 = (int) event.getX();
                        msg.arg2 = (int) event.getY();
                        mHandler.sendMessage(msg);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        msg.arg1 = (int) event.getX();
                        msg.arg2 = (int) event.getY();
                        mHandler.sendMessage(msg);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorDialog.show();
            }
        });
    }
}
