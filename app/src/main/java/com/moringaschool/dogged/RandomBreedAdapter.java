package com.moringaschool.dogged;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.dogged.models.RandomBreedResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomBreedAdapter extends RecyclerView.Adapter<RandomBreedAdapter.itemViewHolder> {
    private Context context;
    private List<RandomBreedResponse> random;

    public RandomBreedAdapter(List<RandomBreedResponse> random) {
        this.random = random;
        this.context=context;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create a viewholder and inflate its xml layout
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.random_breed_item,parent,false);
        itemViewHolder viewHolder= new itemViewHolder(view);
           return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {
         holder
    }

    @Override
    public int getItemCount() {
        return random.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder{
         TextView txt;
        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
            txt=itemView.findViewById(R.id.random_title);
            txt=itemView.findViewById(R.id.nextRandom);
        }
    }

    public bindRandom(RandomBreedResponse randomBreedResponse){
        Picasso.get()
    }

}
