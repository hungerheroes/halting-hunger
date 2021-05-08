package com.example.haltinghunger;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

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
        Button  btnPickup;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDonorName = itemView.findViewById(R.id.tvDonorName);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvZipCode = itemView.findViewById(R.id.tvZipCode);
            btnPickup = itemView.findViewById(R.id.btnPickup);
        }

        public void bind(FoodPost fp) {
            tvTitle.setText(fp.getTitle());
            tvDonorName.setText(fp.getDonor().getUsername());
            tvDetails.setText(fp.getDetails());
            tvQuantity.setText(fp.getQuantity());
            tvLocation.setText(fp.getLocation());
            tvZipCode.setText(String.valueOf(fp.getZipCode()));
            String currentUsername = ParseUser.getCurrentUser().getUsername();
            String currentUserId = ParseUser.getCurrentUser().getObjectId();
            btnPickup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ParseQuery<ParseObject> post = ParseQuery.getQuery("Post");
                    post.getInBackground(fp.getObjectId(), new GetCallback<ParseObject>() {
                        @Override
                        public void done(ParseObject postObj, ParseException e) {
                            if (e == null) {
                                // Now let's update it with some new data. In this case, only cheatMode and score
                                // will get sent to the Parse Cloud. playerName hasn't changed.
                                postObj.put("status", "Pickup confirmed by "+currentUsername);
                                postObj.saveInBackground();
                                Toast.makeText(context.getApplicationContext(), "Picked up",Toast.LENGTH_SHORT).show();
                                Log.i("Stat","Picked Successful");
                            } else {
                                // Failed
                                Log.e("Stat",e.getMessage(),e);
                            }
                        }
                    });

                }
            });
        }
    }

    public void clear() {
        foodPosts.clear();
        notifyDataSetChanged();
    }
}