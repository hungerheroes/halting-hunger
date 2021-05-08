package com.example.haltinghunger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class BenStatusAdaptor extends RecyclerView.Adapter<BenStatusAdaptor.ViewHolder> {
    Context context;
    List<FoodPost> foodPosts;

    public BenStatusAdaptor(Context context, List<FoodPost> foodPosts) {
        this.context = context;
        this.foodPosts = foodPosts;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_upcoming, parent, false);
        return new BenStatusAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BenStatusAdaptor.ViewHolder holder, int position) {
        FoodPost fp = foodPosts.get(position);
        holder.bind(fp);
    }

    @Override
    public int getItemCount() {
        return foodPosts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvStTitle;
        TextView tvStDetails;
        TextView tvStZipCode;
        TextView tvStStatus;
        ImageView ivStImage;
        public ViewHolder(@NonNull  View itemView){
            super(itemView);
            tvStTitle = itemView.findViewById(R.id.tvStTitle);
            tvStDetails = itemView.findViewById(R.id.tvStDetails);
            tvStZipCode = itemView.findViewById(R.id.tvStZipCode);
            tvStStatus = itemView.findViewById(R.id.tvStStatus);
            ivStImage = itemView.findViewById(R.id.ivStImage);
        }

        public void bind(FoodPost fp) {
            tvStTitle.setText(fp.getTitle());
            tvStDetails.setText(fp.getDetails());
            tvStStatus.setText(fp.getStatus());
            tvStZipCode.setText(String.valueOf(fp.getZipCode()));
            ParseFile image=fp.getImage();
            if(image!=null){
                Glide.with(context).load(fp.getImage().getUrl()).into(ivStImage);
            }
        }
    }

    public void clear() {
        foodPosts.clear();
        notifyDataSetChanged();
    }
}
