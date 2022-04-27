package com.example.campus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // 装在 Fragment 的集合
    private List<Fragment> mFragments;

    // 3 个 Tab 对应的布局
    private LinearLayout mTabNews;
    private LinearLayout mTabPhotos;
    private LinearLayout mTabHome;

    // 5 个 Tab 对应的 ImageButton
    private ImageButton mImgNews;
    private ImageButton mImgPhotos;
    private ImageButton mImgHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();  // 初始化空间
        initEvents();  // 初始化事件
        initFragments();  // 初始化数据
        initFirstRun(0);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        // 将 3 个 Fragment 加入到集合中
        mFragments.add(new TabNews());
        mFragments.add(new TabPhotos());
        mFragments.add(new TabHome());
    }

    private void initViews() {
        FrameLayout frag_layout = (FrameLayout) findViewById(R.id.frag_layout);

        mTabNews = (LinearLayout) findViewById(R.id.id_tab_news);
        mTabPhotos = (LinearLayout) findViewById(R.id.id_tab_photos);
        mTabHome = (LinearLayout) findViewById(R.id.id_tab_home);

        mImgNews = (ImageButton) findViewById(R.id.id_tab_news_img);
        mImgPhotos = (ImageButton) findViewById(R.id.id_tab_photos_img);
        mImgHome = (ImageButton) findViewById(R.id.id_tab_home_img);

    }

    /*设置底部导航键的点击事件*/
    private void initEvents() {
        mTabNews.setOnClickListener(onClickListener);
        mTabPhotos.setOnClickListener(onClickListener);
        mTabHome.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 先将四个 ImageButton 设置为默认色
            restImages();
            // 然后根据点击的Tab切换不同的碎片，并设置对应的ImageButton为pressed时的图片
            switch (view.getId())
            {
                case R.id.id_tab_news:
                    selectTab(0);
                    break;
                case R.id.id_tab_photos:
                    selectTab(1);
                    break;
                case R.id.id_tab_home:
                    selectTab(2);
                    break;
            }
        }
    };

    private void restImages() {
        mImgNews.setImageResource(R.mipmap.tab_news_normal);
        mImgPhotos.setImageResource(R.mipmap.tab_takephotos_normal);
        mImgHome.setImageResource(R.mipmap.tab_homepage_normal);
    }

    private void selectTab(int i) {
        setCurrentFragment(i);
        switch (i)
        {
            case 0:
                mImgNews.setImageResource(R.mipmap.tab_news_pressed);
                break;
            case 1:
                mImgPhotos.setImageResource(R.mipmap.tab_takephotos_pressed);
                break;
            case 2:
                mImgHome.setImageResource(R.mipmap.tab_homepage_pressed);
        }
    }

    private void setCurrentFragment(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frag_layout, mFragments.get(i));
        transaction.commit();
    }

    private void initFirstRun(int i) {
        restImages();
        selectTab(i);
    }

}