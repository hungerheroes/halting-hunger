package com.example.haltinghunger;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
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
        TextView tvLocation;
        TextView tvZipCode;
        ImageView ivImage;
        TextView tvStart;
        TextView tvEnd;
        CheckedTextView ckNv;
        CheckedTextView ckHm;

        Button  btnPickup;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDonorName = itemView.findViewById(R.id.tvDonorName);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvZipCode = itemView.findViewById(R.id.tvZipCode);
            btnPickup = itemView.findViewById(R.id.btnPickup);
            ivImage=itemView.findViewById(R.id.ivImage);
            ckNv=itemView.findViewById(R.id.ckNv);
            ckHm=itemView.findViewById(R.id.ckHm);
            tvStart=itemView.findViewById(R.id.tvStart);
            tvEnd=itemView.findViewById(R.id.tvEnd);
        }

        public void bind(FoodPost fp) {
            tvTitle.setText(fp.getTitle());
            tvDonorName.setText(fp.getDonor().getUsername());
            tvDetails.setText(fp.getDetails());
            tvLocation.setText(fp.getLocation());
            tvZipCode.setText(String.valueOf(fp.getZipCode()));
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

            ParseUser parseUser = ParseUser.getCurrentUser();
            String currentUsername = parseUser.getUsername();
            String currentUserId = parseUser.getObjectId();
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
                                postObj.put("beneficiary",parseUser);
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