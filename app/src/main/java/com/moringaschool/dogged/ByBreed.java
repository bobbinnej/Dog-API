package com.moringaschool.dogged;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moringaschool.dogged.RetrofitClient.DogClient;
import com.moringaschool.dogged.interfaces.DogApi;
import com.moringaschool.dogged.models.BreedResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ByBreed extends Fragment {
    DogApi dogApi;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dogApi= DogClient.getClient();
        getBreed();
    }

    //method to get my data
    public void getBreed(){
        Call<BreedResponse> call=dogApi.getBreed();
        call.enqueue(new Callback<BreedResponse>() {
            @Override
            public void onResponse(Call<BreedResponse> call, Response<BreedResponse> response) {
                BreedResponse breedResponse=response.body();
                int status=response.code();
            }

            @Override
            public void onFailure(Call<BreedResponse> call, Throwable t) {
                Log.e(TAG,"OOOOOOPERATION  get  Breed FAILED"+t.getMessage());

            }
        });

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_by_breed, container, false);
    }
}