package com.moringaschool.dogged.interfaces;

import com.moringaschool.dogged.models.BreedResponse;

import com.moringaschool.dogged.models.ListAllBreedsResponse;
import com.moringaschool.dogged.models.RandomBreedResponse;
import com.moringaschool.dogged.models.SubBreedResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DogApi {
    //Define your calls
    //our method for getting the list of all breeds is get
    @GET("breeds/list/all") //this is the endpoint for the list of all breeds
    //this our API call and wat is inside the angle brackets is the expected response which is the ListAllBreedsResponse
   Call<ListAllBreedsResponse> getAllBreeds();  //getAllBreeds is our method which can be any name you want.


    //method for getting random images of different dog breeds
    @GET("breeds/image/random/3")
    Call<RandomBreedResponse> getRandomBreed();

   //method to getting different dog images from a breed
    @GET("breed/hound/images/random/3")
    Call<BreedResponse> getBreed();

    //method to get different images from a subbreed
    @GET("breed/hound/afghan/images")
    Call<SubBreedResponse>getSubBreed();
}
