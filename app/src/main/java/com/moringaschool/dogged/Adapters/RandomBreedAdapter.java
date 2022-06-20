package com.moringaschool.dogged.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.moringaschool.dogged.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RandomBreedAdapter extends RecyclerView.Adapter<RandomBreedAdapter.itemViewHolder> {
    private Context context;
    private List<String> random;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    public RandomBreedAdapter(List<String> random, Context context) {
        this.random = random;
        this.context=context;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mAuth=FirebaseAuth.getInstance();
        // create a viewholder and inflate its xml layout
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.random_breed_item,parent,false);
        itemViewHolder viewHolder= new itemViewHolder(view);
           return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {



            Glide.with(context).load(random.get(position)).into(holder.randomImage);

            isLiked(holder.likeBtn,holder.likesCount);
            //like button
        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.likeBtn.getTag().equals("Like")){
                    FirebaseDatabase.getInstance().getReference().child("Likes")
                            .child(FirebaseAuth.getInstance().getUid())
                            .setValue(true);
                }else{
                    FirebaseDatabase.getInstance().getReference().child("Likes")
                            .child(FirebaseAuth.getInstance().getUid())
                            .removeValue();

                }
            }
        });


    }

    @Override
    public int getItemCount() {

        return random.size();
    }

    public class itemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.randomImageView) ImageView randomImage;

        ImageView likeBtn;
        TextView likesCount;

        DatabaseReference databaseReference;
        FirebaseDatabase database=FirebaseDatabase.getInstance();

        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            likeBtn=itemView.findViewById(R.id.likeBtn);
            likesCount=itemView.findViewById(R.id.likesCount);


        }


    }

    private void isLiked( final ImageView imageView, final TextView textView){
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Likes");
        databaseReference.push().setValue(random);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                textView.setText(snapshot.getChildrenCount()+ "likes");
                if(snapshot.child(user.getUid()).exists()){
                    imageView.setImageResource(R.drawable.ic_liked);
                    imageView.setTag("Liked");

                }else{
                    imageView.setImageResource(R.drawable.ic_dislike);
                    imageView.setTag("Like");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, ""+error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }




}
