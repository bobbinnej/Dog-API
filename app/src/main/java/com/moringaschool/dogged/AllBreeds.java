package com.moringaschool.dogged;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        recyclerView=view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new AllBreedAdapter(listAllBreedsResponses);
        recyclerView.setAdapter(adapter);

        dogApi= DogClient.getClient();
        getAllBreeds();

    }
    //method to fetch my data
    private void getAllBreeds(){
        //call our client
        Call<ListAllBreedsResponse> call=dogApi.getAllBreeds();
        call.enqueue(new Callback<ListAllBreedsResponse>() {
            @Override
            public void onResponse(Call<ListAllBreedsResponse> call, Response<ListAllBreedsResponse> response) {

                ListAllBreedsResponse listAllBreedsResponses=response.body();
            }

            @Override
            public void onFailure(Call<ListAllBreedsResponse> call, Throwable t) {

            }
        });

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_breeds, container, false);
    }


}