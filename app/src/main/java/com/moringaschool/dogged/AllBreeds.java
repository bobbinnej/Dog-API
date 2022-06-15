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

import com.moringaschool.dogged.Adapters.AllBreedAdapter;
import com.moringaschool.dogged.RetrofitClient.DogClient;
import com.moringaschool.dogged.interfaces.DogApi;
import com.moringaschool.dogged.models.ListAllBreedsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllBreeds extends Fragment {
    DogApi dogApi;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AllBreedAdapter adapter;
    List<ListAllBreedsResponse>listAllBreedsResponses;



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dogApi= DogClient.getClient();
        getAllBreeds();

    }
    //method to fetch my data
    public void getAllBreeds(){
        //call our client
        Call<ListAllBreedsResponse> call=dogApi.getAllBreeds();
        call.enqueue(new Callback<ListAllBreedsResponse>() {
            @Override
            public void onResponse(Call<ListAllBreedsResponse> call, Response<ListAllBreedsResponse> response) {

                ListAllBreedsResponse listAllBreedsResponses=response.body();
                int status=response.code();
            }

            @Override
            public void onFailure(Call<ListAllBreedsResponse> call, Throwable t) {

                Log.e(TAG,"OOOOOOPERATION  get All Breed FAILED"+t.getMessage());

            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_all_breeds,container,false);
        recyclerView=view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new AllBreedAdapter(listAllBreedsResponses));

        return view;
    }

    // enable options menu

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }



}