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

import com.moringaschool.dogged.Adapters.SubbreedAdapter;
import com.moringaschool.dogged.RetrofitClient.DogClient;
import com.moringaschool.dogged.interfaces.DogApi;
import com.moringaschool.dogged.models.SubBreedResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BySubBreed extends Fragment {
    DogApi dogApi;
    private List<SubBreedResponse> subbreed;
    LinearLayoutManager linearLayoutManager;


    // recycler view
    @BindView(R.id.subbreedRecycler)  RecyclerView subRecycler;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        dogApi= DogClient.getClient();
        subRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        getSubBreed();
    }

    public void getSubBreed(){
        Call<SubBreedResponse>call= dogApi.getSubBreed();
        call.enqueue(new Callback<SubBreedResponse>() {
            @Override
            public void onResponse(Call<SubBreedResponse> call, Response<SubBreedResponse> response) {
                int status=response.code();
                SubBreedResponse subBreedResponse=response.body();

               List<String>list=subBreedResponse.getMessage();
                subRecycler.setAdapter(new SubbreedAdapter(list, getContext()));
            }

            @Override
            public void onFailure(Call<SubBreedResponse> call, Throwable t) {
                Log.e(TAG,"OOOOOOPERATION  get sub Breed FAILED"+t.getMessage());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_by_sub_breed, container, false);
    }


}