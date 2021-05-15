package com.example.haltinghunger;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class BenStatusAdaptor extends RecyclerView.Adapter<BenStatusAdaptor.ViewHolder> {
    Context context;
    List<FoodPost> foodPosts;
    canBtn cancelBtnfn;
    comBtn completeBtnfn;

    public interface canBtn{
        void onCancelclick(int position,FoodPost fp);
    }

    public interface comBtn{
        void onComplete(int position,FoodPost fp);
    }

    public BenStatusAdaptor(Context context, List<FoodPost> foodPosts,canBtn cancelBtnfn,comBtn completeBtnfn) {
        this.context = context;
        this.foodPosts = foodPosts;
        this.cancelBtnfn=cancelBtnfn;
        this.completeBtnfn=completeBtnfn;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.upcoming_item_post, parent, false);
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
        TextView tvStZipCode;
        ImageView ivStImage;
        TextView tvStart;
        TextView tvEnd;
        TextView tvLocation;
        Button btnCancel;
        Button btnComplete;

        public ViewHolder(@NonNull  View itemView){
            super(itemView);
            tvStTitle = itemView.findViewById(R.id.tvTitle);
            tvStZipCode = itemView.findViewById(R.id.tvZipCode);
            ivStImage = itemView.findViewById(R.id.ivImage);
            tvStart=itemView.findViewById(R.id.tvStart);
            tvEnd=itemView.findViewById(R.id.tvEnd);
            tvLocation=itemView.findViewById(R.id.tvLocation);
            btnCancel= itemView.findViewById(R.id.btnCancel);
            btnComplete=itemView.findViewById(R.id.btnComplete);
        }

        public void bind(FoodPost fp) {
            tvStTitle.setText(fp.getTitle());
            tvStZipCode.setText(String.valueOf(fp.getZipCode()));
            ParseFile image=fp.getImage();
            if(image!=null){
                Glide.with(context).load(fp.getImage().getUrl()).into(ivStImage);
            }
            tvStart.setText(fp.getStartDate()+" "+fp.getStartTime());
            tvEnd.setText(fp.getEndDate()+" "+fp.getEndTime());
            tvLocation.setText(fp.getLocation());

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cancelBtnfn.onCancelclick(getAdapterPosition(),fp);
//                    fp.put(FoodPost.KEY_STATUS, "Waiting for confirmation");
////                    fp.put("beneficiary","Tftofbf1Ex");
//                    fp.saveInBackground();
                }
            });

            btnComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    completeBtnfn.onComplete(getAdapterPosition(),fp);
                }
            });
        }
    }

    public void clear() {
        foodPosts.clear();
        notifyDataSetChanged();
    }
}
