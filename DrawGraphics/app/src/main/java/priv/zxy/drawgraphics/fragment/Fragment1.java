package priv.zxy.drawgraphics.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import priv.zxy.drawgraphics.R;
import priv.zxy.drawgraphics.custom_view.PresenhamLine;

public class Fragment1 extends Fragment {
    private PresenhamLine drawLine;
    private TextView getX;
    private TextView getY;
    private View view;
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
    }

    @SuppressLint("ClickableViewAccessibility")
    public void initView(){
        drawLine = view.findViewById(R.id.drawLine);
        getX = view.findViewById(R.id.getX);
        getY = view.findViewById(R.id.getY);

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
    }
}
