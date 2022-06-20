package com.moringaschool.dogged.Adapters;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.dogged.FragmentClasses.ByBreed;

public class FirebaseSavedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View mView;
    Context mcontext;

    public FirebaseSavedViewHolder(View itemView) {
        super(itemView);
        mView=itemView;
        mcontext=itemView.getContext();
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
