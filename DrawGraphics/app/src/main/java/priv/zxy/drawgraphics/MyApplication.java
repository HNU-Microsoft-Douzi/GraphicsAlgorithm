package priv.zxy.drawgraphics;

import android.app.Application;
import android.content.Context;

/**
 * 创建人: Administrator
 * 创建时间: 2018/12/20
 * 描述:
 **/
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
