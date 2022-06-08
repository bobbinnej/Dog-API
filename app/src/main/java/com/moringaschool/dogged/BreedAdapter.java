package com.moringaschool.dogged;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BreedAdapter extends RecyclerView.Adapter<BreedAdapter.breedViewHolder> {
 private Context context;
 private List<String> breed;

    public BreedAdapter(List<String> breed, Context context) {
        this.breed = breed;
        this.context=context;
    }

    @NonNull
    @Override
    public breedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the bybreed.xml layout resource file
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.by_breed_item,parent,false);
        breedViewHolder breedHolder=new breedViewHolder(view);
        return breedHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull breedViewHolder holder, int position) {
        Glide.with(context).load(breed.get(position)).into(holder.breedImage);
    }

    @Override
    public int getItemCount()
    {
        return breed.size();
    }

    public class breedViewHolder extends RecyclerView.ViewHolder {
      @BindView(R.id.breedImageView) ImageView breedImage;

        public breedViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

