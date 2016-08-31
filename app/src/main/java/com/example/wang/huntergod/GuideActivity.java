package com.example.wang.huntergod;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2016/7/7.
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {

    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private int[] ids = {R.id.iv1, R.id.iv2, R.id.iv3};
    private List<View> views;
    private Button start_btn;
    private int point;
    private SoundPool soundPool;


    // 底部小点图片
    private ImageView[] dots;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setTitle("遊戲說明");

        // 初始化页面
        initViews();

        // 初始化底部小点
        initDots();
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(this);

        views = new ArrayList<View>();
        // 初始化引导图片列表
        views.add(inflater.inflate(R.layout.what_new_one, null));
        views.add(inflater.inflate(R.layout.what_new_two, null));
        views.add(inflater.inflate(R.layout.what_new_three, null));


        // 初始化Adapter
        vpAdapter = new ViewPagerAdapter(views, this);

        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);
        // 绑定回调

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
        point = soundPool.load(this, R.raw.water_drop, 1);

        start_btn = (Button) views.get(2).findViewById(R.id.iv_start_weibo);
        start_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                soundPool.play(point, 1.0F, 1.0F, 0, 0, 1.0F);
                Intent i = new Intent(GuideActivity.this, ChooseActivity.class);
                startActivity(i);
                finish();

            }
        });


        vp.setOnPageChangeListener(this);
    }



    private void initDots() {

        dots = new ImageView[views.size()];

        // 循环取得小点图片
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) findViewById(ids[i]);


        }

    }



    // 当滑动状态改变时调用
    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    // 当当前页面被滑动时调用
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    // 当新的页面被选中时调用
    @Override
    public void onPageSelected(int arg0) {
        // 设置底部小点选中状态
        for (int i = 0; i < ids.length; i++) {
            if (arg0 == i) {
                // 亮点
                dots[i].setImageResource(R.drawable.point_gray);
            } else {
                // 暗点
                dots[i].setImageResource(R.drawable.point_white);
            }
        }
    }
}

