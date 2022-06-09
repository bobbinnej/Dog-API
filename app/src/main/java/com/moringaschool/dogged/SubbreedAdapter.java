package com.moringaschool.dogged;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moringaschool.dogged.models.SubBreedResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubbreedAdapter extends RecyclerView.Adapter<SubbreedAdapter.subbreedViewHolder> {
private Context context;
private List<String> subbreed;

    public SubbreedAdapter(List<String> subbreed, Context context) {
        this.subbreed = subbreed;
        this.context=context;
    }

    @NonNull
    @Override
    public subbreedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the subbreed item layout resource file
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.subbreed_item, parent,false);
        subbreedViewHolder subViewHolder=new subbreedViewHolder(view);
        return subViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull subbreedViewHolder holder, int position) {
        Glide.with(context).load(subbreed.get(position)).into(holder.subreedImage);
    }

    @Override
    public int getItemCount() {
        return subbreed.size();
    }

    public class subbreedViewHolder extends RecyclerView.ViewHolder{
       @BindView(R.id.subbreedImageView) ImageView subreedImage;

        public subbreedViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
