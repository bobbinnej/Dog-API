package com.moringaschool.dogged;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.moringaschool.dogged.RetrofitClient.DogClient;
import com.moringaschool.dogged.interfaces.DogApi;
import com.moringaschool.dogged.models.BreedResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ByBreed extends Fragment {
    DogApi dogApi;
    private List<BreedResponse>breed;
    LinearLayoutManager linearLayoutManager;

    // our recycler view
    @BindView(R.id.breedRecycler)RecyclerView breedRecyler;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        dogApi= DogClient.getClient();

        breedRecyler.setLayoutManager(new LinearLayoutManager(getContext()));
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
                List<String>list=breedResponse.getMessage();
                breedRecyler.setAdapter(new BreedAdapter(list,getContext()));
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

    // enable options menu

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }


}