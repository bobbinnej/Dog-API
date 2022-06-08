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

public class AllBreedAdapter extends RecyclerView.Adapter<AllBreedViewHolder> {
    private List<ListAllBreedsResponse> listAllBreedsResponses;
    public AllBreedAdapter(List<ListAllBreedsResponse> listAllBreedsResponses) {
        this.listAllBreedsResponses = listAllBreedsResponses;
    }










    @NonNull
    @Override
    public AllBreedViewHolder onCreateViewHolder( ViewGroup  parent, int viewType) {

        // create a viewholder and inflate its xml layout
        View view;
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AllBreedViewHolder holder, int position) {
      holder.getView().setText(listAllBreedsResponses.get(position).getMessage());

    }
    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView breedName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
