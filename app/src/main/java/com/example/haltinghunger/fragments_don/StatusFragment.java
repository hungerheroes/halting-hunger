package com.example.haltinghunger.fragments_don;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haltinghunger.FoodPost;
import com.example.haltinghunger.FoodPostsAdapter;
import com.example.haltinghunger.FoodPostsAdapter_status_don;
import com.example.haltinghunger.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class StatusFragment extends Fragment {

    public static final String TAG="Status_Don";
    protected RecyclerView rvStatusDon;

    protected FoodPostsAdapter_status_don adapter;
    protected List<FoodPost> allPosts;

    private SwipeRefreshLayout swipeContainer;

    public StatusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status_don, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvStatusDon= view.findViewById(R.id.rvStatusDon);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        allPosts=new ArrayList<>();
        adapter=new FoodPostsAdapter_status_don(getContext(),allPosts);
        rvStatusDon.setAdapter(adapter);
        rvStatusDon.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                queryPosts();
                swipeContainer.setRefreshing(false);
            }
        });
    }

    protected void queryPosts() {
        ParseQuery<FoodPost> query=ParseQuery.getQuery(FoodPost.class);
        query.include(FoodPost.KEY_DONOR);
        query.whereEqualTo(FoodPost.KEY_DONOR, ParseUser.getCurrentUser());
        query.include(FoodPost.KEY_CREATED_KEY);
        query.setLimit(20);
        query.addDescendingOrder(FoodPost.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<FoodPost>() {
            @Override
            public void done(List<FoodPost> objects, ParseException e) {
                if(e!=null){
                    Log.e(TAG,"Issue with getting posts");
                    return;
                }
                for(FoodPost post:objects){
                    Log.i(TAG,"POST: "+post.getTitle()+", USER: "+post.getStatus());
                }
                allPosts.addAll(objects);
                adapter.notifyDataSetChanged();
            }
        });

    }

}