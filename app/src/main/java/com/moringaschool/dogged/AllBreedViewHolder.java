package com.moringaschool.dogged;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllBreedViewHolder extends RecyclerView.ViewHolder {
     TextView view;

    public AllBreedViewHolder(@NonNull View itemView) {
        super(itemView);

    }
    public TextView getView(){
        return view;
    }
}
