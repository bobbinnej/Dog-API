package com.moringaschool.dogged.FragmentClasses;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
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
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;
import com.moringaschool.dogged.Adapters.RandomBreedAdapter;
import com.moringaschool.dogged.R;
import com.moringaschool.dogged.RetrofitClient.DogClient;
//import com.moringaschool.dogged.SessionManager.LogoutSession;
import com.moringaschool.dogged.constants.Constants;
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
    private ScaleGestureDetector scaleGestureDetector;
    private Matrix matrix=new Matrix();
    ImageView imageView;
    @BindView(R.id.randomRecycler) RecyclerView randomRecycler;


    @SuppressLint("ResourceAsColor")


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         ButterKnife.bind(this,view);

         imageView=view.findViewById(R.id.randomImageView);
         scaleGestureDetector=new ScaleGestureDetector(randomRecycler.getContext(), new ScaleListener());


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

//zo in and out
    public boolean onTouchEvent(MotionEvent event){
        scaleGestureDetector.onTouchEvent(event);
        return true;
    }

    class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor= detector.getScaleFactor();
            scaleFactor=Math.max(0.1f, Math.min(scaleFactor,5.0f));
            matrix.setScale(scaleFactor,scaleFactor);
            imageView.setImageMatrix(matrix);
            
            return true;
        }
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