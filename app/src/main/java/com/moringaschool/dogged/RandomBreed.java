package com.moringaschool.dogged;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.moringaschool.dogged.RetrofitClient.DogClient;
import com.moringaschool.dogged.interfaces.DogApi;
import com.moringaschool.dogged.models.RandomBreedResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RandomBreed extends Fragment {
    DogApi dogApi;
    private List<RandomBreedResponse> random;
    private RandomBreedAdapter randomBreedAdapter;
    private RecyclerView recyclerView;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dogApi= DogClient.getClient();
        getRandomBreed();
    }

    public void getRandomBreed(){
        Call<RandomBreedResponse>call= dogApi.getRandomBreed();
        call.enqueue(new Callback<RandomBreedResponse>() {
            @Override
            public void onResponse(Call<RandomBreedResponse> call, Response<RandomBreedResponse> response) {

                int status = response.code();
                RandomBreedResponse randomBreedResponse=response.body();
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
        random=new ArrayList<>();

        randomBreedAdapter= new RandomBreedAdapter(random);
        // Inflate the layout for this fragment
        return view;
    }


    }