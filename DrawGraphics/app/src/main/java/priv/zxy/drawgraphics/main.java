package priv.zxy.drawgraphics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import priv.zxy.drawgraphics.fragment.Fragment1;
import priv.zxy.drawgraphics.fragment.Fragment2;
import priv.zxy.drawgraphics.fragment.Fragment3;
import priv.zxy.drawgraphics.fragment.Fragment4;
import priv.zxy.drawgraphics.fragment.Fragment5;

public class main extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private ArrayList<Fragment> fragments;

    private RadioGroup rg;

    private RadioButton rb;

    private FragmentManager fragmentManager;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;
    Fragment5 fragment5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
    }

    /**
     * ViewPager切换页面的步骤:
     * 1.声明Adapter的对象，需要复写FragmentPagerAdapter的getItem和getCount函数，传入的参数为fragment的列表
     * 2.将fragment对象加入到Fragment的列表中
     * 3.为ViewPager设置适配器setAdapter
     * 4.通过setOffScreenPageLimit(count)设置缓存的fragment数目
     *
     * 注意，一般来说，复写的Transformer继承自ViewPager.PageTransformer，它需要获得对象后设置setCurrentItem向其中传递frament列表
     *     然后在通过vierPager对象的setPageTransofrmer对象呈递Transformer对象，实现页面的切换效果。
     */
    private void initView() {
        fragments = new ArrayList<Fragment>();

        fragmentManager = getSupportFragmentManager();

        rg = findViewById(R.id.rg_rab_bar);

        rg.setOnCheckedChangeListener(this);

        rb = findViewById(R.id.rb_channel1);
        rb.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        switch(checkedId){
            case R.id.rb_channel1:
                if(fragment1 == null){
                    fragment1 = new Fragment1();
                }
                fTransaction.replace(R.id.ly_content, fragment1).commit();
                break;
            case R.id.rb_channel2:
                if(fragment2 == null){
                    fragment2 = new Fragment2();
                }
                fTransaction.replace(R.id.ly_content, fragment2).commit();
                break;
            case R.id.rb_channel3:
                if(fragment3 == null){
                    fragment3 = new Fragment3();
                }
                fTransaction.replace(R.id.ly_content, fragment3).commit();
                break;
            case R.id.rb_channel4:
                if(fragment4 == null){
                    fragment4 = new Fragment4();
                }
                fTransaction.replace(R.id.ly_content, fragment4).commit();
                break;
            case R.id.rb_channel5:
                if(fragment5 == null){
                    fragment5 = new Fragment5();
                }
                fTransaction.replace(R.id.ly_content, fragment5).commit();
                break;
        }
    }
}
