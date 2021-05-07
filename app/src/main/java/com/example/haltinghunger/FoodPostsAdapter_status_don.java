package com.example.haltinghunger;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class FoodPostsAdapter_status_don extends RecyclerView.Adapter<FoodPostsAdapter_status_don.ViewHolder> {
    Context context;
    List<FoodPost> foodPosts;

    public FoodPostsAdapter_status_don(Context context, List<FoodPost> foodPosts) {
        this.context = context;
        this.foodPosts = foodPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.status_don_item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodPost fp = foodPosts.get(position);
        holder.bind(fp);
    }

    @Override
    public int getItemCount() {
        return foodPosts.size();
    }

//    public void addAll(List<Post> posts) {
//        this.posts.addAll(posts);
//        notifyDataSetChanged();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvDetails;
        TextView tvQuantity;
        TextView tvLocation;
        TextView tvZipCode;
        TextView tvStatus;
        ImageView ivImage;
        CheckedTextView ckNv;
        CheckedTextView ckHm;
        TextView tvStart;
        TextView tvEnd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvZipCode = itemView.findViewById(R.id.tvZipCode);
            tvStatus=itemView.findViewById(R.id.tvStatus);
            ivImage=itemView.findViewById(R.id.ivImage);
            ckNv=itemView.findViewById(R.id.ckNv);
            ckHm=itemView.findViewById(R.id.ckHm);
            tvStart=itemView.findViewById(R.id.tvStart);
            tvEnd=itemView.findViewById(R.id.tvEnd);
        }

        public void bind(FoodPost fp) {
            tvTitle.setText(fp.getTitle());
            tvDetails.setText(fp.getDetails());
            tvQuantity.setText(fp.getQuantity());
            tvLocation.setText(fp.getLocation());
            tvZipCode.setText(String.valueOf(fp.getZipCode()));
            tvStatus.setText(fp.getStatus());
            ParseFile image=fp.getImage();
            if(image!=null){
                Glide.with(context).load(fp.getImage().getUrl()).into(ivImage);
            }

            tvStart.setText(fp.getStartDate()+" "+fp.getStartTime());
            tvEnd.setText(fp.getEndDate()+" "+fp.getEndTime());
            if(fp.getNV()==true){
                ckNv.setCheckMarkDrawable(R.drawable.check);
            }
            if(fp.getHM()==true){
                ckHm.setCheckMarkDrawable(R.drawable.check);
            }

        }
    }

    public void clear() {
        foodPosts.clear();
        notifyDataSetChanged();
    }
}