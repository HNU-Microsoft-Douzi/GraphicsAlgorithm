package priv.zxy.drawgraphics.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import priv.zxy.drawgraphics.MyApplication;
import priv.zxy.drawgraphics.R;
import priv.zxy.drawgraphics.custom_view.DrawCircleView;

public class Fragment2 extends Fragment {

    private View view;

    private EditText inputX;

    private EditText inputY;

    private DrawCircleView drawCircleView;

    private int CircleX;

    private int CircleY;

    private TextView getx;

    private TextView gety;

    private TextView radiusNumber;

    private Button button;

    private Button choose;

    private AlertDialog colorDialog;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int radius = msg.what;
            drawCircleView.setRadius(radius);
            drawCircleView.setX(CircleX + (int)drawCircleView.getWidth()/2);
            drawCircleView.setY(CircleY + (int)drawCircleView.getHeight()/2);
            getx.setText(String.valueOf(msg.arg1 - (int)drawCircleView.getWidth()/2));
            gety.setText(String.valueOf(msg.arg2 - (int)drawCircleView.getHeight()/2));
            radiusNumber.setText(String.valueOf(radius));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();

        initEvent();
    }

    public void initView() {
        drawCircleView = view.findViewById(R.id.drawCirclePage);
        inputX = view.findViewById(R.id.inputX);
        inputY = view.findViewById(R.id.inputY);
        CircleX = Integer.parseInt(inputX.getText().toString());
        CircleY = Integer.parseInt(inputY.getText().toString());
        getx = view.findViewById(R.id.getX);
        gety = view.findViewById(R.id.getY);
        radiusNumber = view.findViewById(R.id.radius_number);
        button = view.findViewById(R.id.button);
        choose = view.findViewById(R.id.chooseBt);
        colorDialog= ColorPickerDialogBuilder
                .with(getActivity())
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
                        Toast.makeText(Fragment2.this.getActivity(),"onColorSelected: 0x" + Integer.toHexString(selectedColor),Toast.LENGTH_LONG).show();
                    }
                })
                //确定和取消按钮，这里没有颜色设置的选项，但是可以修改源码
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        drawCircleView.setColor(selectedColor);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initEvent(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircleX = Integer.parseInt(inputX.getText().toString());
                CircleY = Integer.parseInt(inputY.getText().toString());
            }
        });
        drawCircleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Message msg = new Message();
                final int width = drawCircleView.getWidth() / 2;
                final int height = drawCircleView.getHeight() / 2;
                Log.d("Fragment2", "width:" + width);
                Log.d("Fragment2", "height:" + height);
                int endX, endY, radius;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        endX = (int) event.getX();
                        endY = (int) event.getY();
                        radius = (int) Math.sqrt((endX - CircleX - width) * (endX - CircleX - width) + (endY - CircleY - height) * (endY - CircleY - height));
                        msg.what = radius;
                        msg.arg1 = endX;
                        msg.arg2 = endY;
                        mHandler.sendMessage(msg);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        endX = (int) event.getX();
                        endY = (int) event.getY();
                        radius = (int) Math.sqrt((endX - CircleX - width) * (endX - CircleX - width) + (endY - CircleY - height) * (endY - CircleY - height));
                        msg.what = radius;
                        msg.arg1 = endX;
                        msg.arg2 = endY;
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