package com.moringaschool.dogged;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.dogged.FragmentClasses.AllBreeds;
import com.moringaschool.dogged.FragmentClasses.ByBreed;
import com.moringaschool.dogged.FragmentClasses.BySubBreed;
import com.moringaschool.dogged.FragmentClasses.RandomBreed;
import com.moringaschool.dogged.Login.LoginActivity;

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
   private AlertDialog alertDialog;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //toolbar
        setSupportActionBar(toolbar);
//        getSupportActionBar().hide();// this particula line will hide the title bar
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
        viewPagerAdapter.addFragment(allbreedsfragment,"BreedList");
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sidemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        // handle menu item clicks
        int id =item.getItemId();

        if(id==R.id.logoutMenu){
            //Toast.makeText(getActivity(),"Logging you out...", Toast.LENGTH_SHORT).show();
            logoutUser();

            return true;
        }
        if(id==R.id.themeMenu){
           // Toast.makeText(getActivity(), "Dark and Light mode", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure you want to logout");
        builder.setTitle("Success");
        builder.setCancelable(false);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // redirect to splashscreen
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
                dialog.cancel();

                // back to logoutActivity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();



    }

}