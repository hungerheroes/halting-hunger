package com.example.haltinghunger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodPostsAdapter extends RecyclerView.Adapter<FoodPostsAdapter.ViewHolder> {
    Context context;
    List<FoodPost> foodPosts;

    public FoodPostsAdapter(Context context, List<FoodPost> foodPosts) {
        this.context = context;
        this.foodPosts = foodPosts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
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

//    public void clear() {
//        posts.clear();
//        notifyDataSetChanged();
//    }
//
//    public void addAll(List<Post> posts) {
//        this.posts.addAll(posts);
//        notifyDataSetChanged();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvDonorName;
        TextView tvDetails;
        TextView tvQuantity;
        TextView tvLocation;
        TextView tvZipCode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDonorName = itemView.findViewById(R.id.tvDonorName);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvZipCode = itemView.findViewById(R.id.tvZipCode);
        }

        public void bind(FoodPost fp) {
            tvTitle.setText(fp.getTitle());
            tvDonorName.setText(fp.getDonor().getUsername());
            tvDetails.setText(fp.getDetails());
            tvQuantity.setText(fp.getQuantity());
            tvLocation.setText(fp.getLocation());
//            tvZipCode.setText(fp.getZipCode());
        }
    }
}