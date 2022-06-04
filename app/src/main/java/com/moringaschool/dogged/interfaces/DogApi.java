package com.moringaschool.dogged.interfaces;

import androidx.cardview.widget.CardView;

import com.moringaschool.dogged.models.ListAllBreedsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DogApi {
    //Define your calls
    //our method for getting the list of all breeds is get
    @GET("breeds/list/all") //this is the endpoint for the list of all breeds
    //this our API call and wat is inside the angle brackets is the expected response which is the ListAllBreedsResponse
    Call<ListAllBreedsResponse> getAllBreeds();  //getAllBreeds is our method which can be any name you want.


}
