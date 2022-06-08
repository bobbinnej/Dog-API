package com.moringaschool.dogged;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moringaschool.dogged.models.RandomBreedResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RandomBreedAdapter extends RecyclerView.Adapter<RandomBreedAdapter.itemViewHolder> {
    private Context context;
    private List<String> random;

    public RandomBreedAdapter(List<String> random, Context context) {
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
        Glide.with(context).load(random.get(position)).into(holder.randomImage);

    }

    @Override
    public int getItemCount() {
        return random.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.randomImageView) ImageView randomImage;

        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

}
