package com.moringaschool.dogged;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager viewpager;
    @BindView(R.id.tab_layout)   TabLayout tablayout;


    //my fragments
    private RandomBreed randombreedfragment;
   private ByBreed bybreedfragment;
   private BySubBreed bysubbreedfragment;
   private AllBreeds allbreedsfragment;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();// this particula line will hide the title bar
        toolbar.setTitleTextColor(Color.WHITE);

        allbreedsfragment=new AllBreeds();
        bybreedfragment=new ByBreed();
        bysubbreedfragment=new BySubBreed();
        randombreedfragment=new RandomBreed();

        tablayout.setupWithViewPager(viewpager);
        //viewpager adapter for our tab layout to add dynamic views
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),0);

    }
  // inner class for our viewpager adapter that extende fragment adapter pager class
    private class ViewPagerAdapter extends FragmentPagerAdapter {

        //list to store our fragements
      private List<Fragment>fragments= new ArrayList<>();
      // this list will store our fragment titles
      private List<String>fragmentsTitle= new ArrayList<>();

      public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
          super(fm, behavior);
      }

      public void addFragment(Fragment fragment){

      }

      @NonNull
      @Override
      public Fragment getItem(int position) {

          return fragments.get(position);
      }

      @Override
      public int getCount() {
         return fragments.size();
      }

      @Nullable
      @Override
      public CharSequence getPageTitle(int position) {
          return fragmentsTitle.get(position);
      }
  }
}