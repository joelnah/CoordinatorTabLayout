package nah.prayer.coordinatortablayout;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import nah.prayer.library.CoordinatorTabLayout;
import nah.prayer.library.listener.LoadHeaderImagesListener;

public class MainActivity extends AppCompatActivity {

    private CoordinatorTabLayout mCoordinatorTabLayout;
    private int[] mImageArray, mColorArray;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"Android", "iOS", "Web", "Other"};
    private final String[] stringArray = {"aaaaaaaaaaaaaa", "bbbbbbbbbbbbbbbb"};
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        initViewPager();
        mImageArray = new int[]{
                R.mipmap.bg_android,
                R.mipmap.bg_ios,
                R.mipmap.bg_js,
                R.mipmap.bg_other};
        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light};

        mCoordinatorTabLayout =  findViewById(R.id.coordinatortablayout);

/*
        //Type1
        mCoordinatorTabLayout.setTranslucentStatusBar(this)
                .setImageArray(mImageArray, mColorArray)
                .setExplanation(stringArray, 20 , Color.GREEN)
                .setupWithViewPager(mViewPager);*/


        //Type2
        mCoordinatorTabLayout.setTranslucentStatusBar(this)
                .setContentScrimColorArray(mColorArray)
                .setExplanation(stringArray, 20 , Color.GREEN)
                .setLoadHeaderImagesListener(new LoadHeaderImagesListener() {
                    @Override
                    public void loadHeaderImages(ImageView imageView, int position) {
                        switch (position) {
                            case 0:
                                loadImages(imageView, "https://raw.githubusercontent.com/hugeterry/CoordinatorTabLayout/master/sample/src/main/res/mipmap-hdpi/bg_android.jpg");
                                break;
                            case 1:
                                loadImages(imageView, "https://raw.githubusercontent.com/hugeterry/CoordinatorTabLayout/master/sample/src/main/res/mipmap-hdpi/bg_js.jpg");
                                break;
                            case 2:
                                loadImages(imageView, "https://raw.githubusercontent.com/hugeterry/CoordinatorTabLayout/master/sample/src/main/res/mipmap-hdpi/bg_ios.jpg");
                                break;
                            case 3:
                                loadImages(imageView, "https://raw.githubusercontent.com/hugeterry/CoordinatorTabLayout/master/sample/src/main/res/mipmap-hdpi/bg_other.jpg");
                                break;
                            default:
                                break;
                        }
                    }
                })
                .setupWithViewPager(mViewPager);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        for (String title : mTitles) {
            mFragments.add(MainFragment.getInstance(title));
        }
    }

    private void initViewPager() {
        mViewPager = findViewById(R.id.vp);
      //  mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
    }


    private void loadImages(ImageView imageView, String url) {
        Glide.with(this).load(url).into(imageView);
    }

}