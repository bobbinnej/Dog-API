package com.moringaschool.dogged.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.dogged.models.ListAllBreedsResponse;

import java.util.List;

import butterknife.BindView;

public class AllBreedAdapter extends RecyclerView.Adapter<AllBreedAdapter.dogHolder> {
 private Context context;
 private List<String>allbreed;
    @NonNull
    @Override
    public dogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate allbreed item
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull dogHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class dogHolder extends RecyclerView.ViewHolder{

        public dogHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
