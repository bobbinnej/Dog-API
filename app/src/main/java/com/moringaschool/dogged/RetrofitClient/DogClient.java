package com.moringaschool.dogged.RetrofitClient;

import com.moringaschool.dogged.interfaces.DogApi;

import retrofit2.Retrofit;

public class DogClient {
    // Dog Client is what is responsible for making API calls

    private static Retrofit retrofit=null;
    // this method will return the actual client which is our DogApi under interfaces package
    public static DogApi getClient(){
        // lets ensure we do not have multiple instances
        // if retrofit is null then we'll re define what retrofit is & if not null then we'll skip everything
        //and return the current value of retrofit
        if(retrofit==null){
           retrofit= new Retrofit.Builder()
                   //this is our base url for the Dog API
        }
        return retrofit.create(DogApi.class);
    }
}
