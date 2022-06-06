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
    @BindView(R.id.view_pager) ViewPager viewpager;
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

        randombreedfragment=new RandomBreed();
        bybreedfragment=new ByBreed();
        bysubbreedfragment=new BySubBreed();
        allbreedsfragment=new AllBreeds();

        tablayout.setupWithViewPager(viewpager);
        //viewpager adapter for our tab layout to add dynamic views
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),0);
        viewPagerAdapter.addFragment( randombreedfragment,"Random");
        viewPagerAdapter.addFragment(bybreedfragment,"Breed");
        viewPagerAdapter.addFragment( bysubbreedfragment,"SubBreed");
        viewPagerAdapter.addFragment(allbreedsfragment,"AllBreed");
        viewpager.setAdapter(viewPagerAdapter);

        //display our icons for the titles
        tablayout.getTabAt(0).setIcon(R.drawable.ic_dog_breed_svgrepo_com);
        tablayout.getTabAt(1).setIcon(R.drawable.ic_breed2);
        tablayout.getTabAt(2).setIcon(R.drawable.ic_subbreed1);
        tablayout.getTabAt(3).setIcon(R.drawable.ic_allbreed1);

    }
  // inner class for our viewpager adapter that extende fragment adapter pager class
    private class ViewPagerAdapter extends FragmentPagerAdapter {

        //list to store our fragements
      private List<Fragment>fragments= new ArrayList<>();
      // this list will store our fragment titles
      private List<String>fragmentTitle= new ArrayList<>();

      public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
          super(fm, behavior);
      }

      public void addFragment(Fragment fragment, String title){
                fragments.add(fragment);
                fragmentTitle.add(title);
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
          return fragmentTitle.get(position);
      }
  }
}