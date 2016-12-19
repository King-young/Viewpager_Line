package com.example.ll.viewpager_line;

import android.graphics.Matrix;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private View mLine;
    private ViewPager mViewpager;
    private List<Fragment> fragments;
    private int start_page;
    private int move_px;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLine=findViewById(R.id.line);
        mViewpager= (ViewPager) findViewById(R.id.viewpager);
        fragments=new ArrayList<>();
        fragments.add(new Fragment_one());
        fragments.add(new Fragment_two());
        fragments.add(new Fragment_three());
        fragments.add(new Fragment_four());
        //获取屏幕对象
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        //获取屏幕宽度
        int width = outMetrics.widthPixels;
        //每次位移的距离==屏幕宽度/组件个数
        move_px=width/fragments.size();
        //修改线的长度为textview的宽度
        ViewGroup.LayoutParams lp=mLine.getLayoutParams();
        lp.width=move_px;
        mLine.setLayoutParams(lp);
        //设置adapter
        mViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //使用动画移动位置
                Animation animation=new TranslateAnimation(start_page*move_px,position*move_px,0,0);
                //更新当前位置
                start_page=position;
                //是否保持最终状态
                animation.setFillAfter(true);
                //动画持续时间
                animation.setDuration(500);
                //执行动画
                mLine.startAnimation(animation);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();





    }
}
