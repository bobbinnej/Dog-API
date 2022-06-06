package com.moringaschool.dogged;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.dogged.models.ListAllBreedsResponse;

import java.util.ArrayList;
import java.util.List;

public class AllBreedAdapter extends RecyclerView.Adapter<AllBreedAdapter.ViewHolder> {
    private ArrayList<ListAllBreedsResponse>modelAdapter;
    private Context context;


    public AllBreedAdapter(ArrayList<ListAllBreedsResponse>modelAdapter) {
        this.modelAdapter=modelAdapter;
    }

    @NonNull
    @Override
    public AllBreedAdapter.ViewHolder onCreateViewHolder( ViewGroup  parent, int viewType) {

        // create a viewholder and inflate its xml layout
        View view= LayoutInflater.from(context).inflate(R.layout.allbreed_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllBreedAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Breed;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Breed=itemView.findViewById(R.id.breedName);
        }
    }
}
