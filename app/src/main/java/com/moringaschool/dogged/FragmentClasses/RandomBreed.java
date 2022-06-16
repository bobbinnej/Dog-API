package com.moringaschool.dogged.FragmentClasses;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.moringaschool.dogged.Adapters.RandomBreedAdapter;
import com.moringaschool.dogged.R;
import com.moringaschool.dogged.RetrofitClient.DogClient;
//import com.moringaschool.dogged.SessionManager.LogoutSession;
import com.moringaschool.dogged.interfaces.DogApi;
import com.moringaschool.dogged.models.RandomBreedResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RandomBreed extends Fragment {
    DogApi dogApi;
    private List<RandomBreedResponse> random;
    private RandomBreedAdapter randomBreedAdapter;
    LinearLayoutManager linearLayoutManager;
    Context context;
    private ShimmerFrameLayout shimmerFrameLayout;
//    LogoutSession session;

    @BindView(R.id.randomRecycler) RecyclerView randomRecycler;


    @SuppressLint("ResourceAsColor")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         ButterKnife.bind(this,view);
        randomRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        dogApi= DogClient.getClient();


       shimmerFrameLayout=view.findViewById(R.id.shimmerFrameLayout);
        shimmerFrameLayout.startShimmer();
        final Handler handler=new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getRandomBreed();
            }
        }, 900);

    }



    public void getRandomBreed(){
        Call<RandomBreedResponse>call= dogApi.getRandomBreed();
        call.enqueue(new Callback<RandomBreedResponse>() {
            @Override
            public void onResponse(Call<RandomBreedResponse> call, Response<RandomBreedResponse> response) {
                  if(response.isSuccessful()){
                      shimmerFrameLayout.stopShimmer();
                      shimmerFrameLayout.setVisibility(View.GONE);
                      int status = response.code();
                      RandomBreedResponse randomBreedResponse=response.body();
                      List<String> list=randomBreedResponse.getMessage();
                      //setting adapter to recycler view;
                      randomRecycler.setAdapter(new RandomBreedAdapter(list, getContext()));
                  }
            }

            @Override
            public void onFailure(Call<RandomBreedResponse> call, Throwable t) {
                Log.e(TAG,"OOOOOOPERATION  get Random Breed FAILED"+t.getMessage());
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_random_breed, container, false);

        return view;
    }

    // enable options menu

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }



}