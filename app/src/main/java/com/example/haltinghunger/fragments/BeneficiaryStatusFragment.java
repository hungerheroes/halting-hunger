package com.example.haltinghunger.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.haltinghunger.BenStatusAdaptor;
import com.example.haltinghunger.FoodPost;
import com.example.haltinghunger.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class BeneficiaryStatusFragment extends Fragment {

    public static final String TAG="Status_Benificiary";
    protected RecyclerView rvStatusBeneficiary;

    protected BenStatusAdaptor adapter;
    protected List<FoodPost> allPosts;

    private SwipeRefreshLayout benSwipeContainer;

    public BeneficiaryStatusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beneficiary_status, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvStatusBeneficiary= view.findViewById(R.id.rvStatusBeneficiary);
        benSwipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.benSwipeContainer);
        allPosts=new ArrayList<>();
        adapter = new BenStatusAdaptor(getContext(),allPosts);
        rvStatusBeneficiary.setAdapter(adapter);
        rvStatusBeneficiary.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();

        benSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                queryPosts();
                benSwipeContainer.setRefreshing(false);
            }
        });
    }

    protected void queryPosts() {
        ParseQuery<FoodPost> query=ParseQuery.getQuery(FoodPost.class);
        query.include(FoodPost.KEY_VOLUNTEER);
        query.whereEqualTo(FoodPost.KEY_VOLUNTEER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.findInBackground(new FindCallback<FoodPost>() {
            @Override
            public void done(List<FoodPost> objects, ParseException e) {
                if(e!=null){
                    Log.e(TAG,"Issue with getting posts");
                    return;
                }
                for(FoodPost post:objects){
                    Log.i(TAG,"Title: "+post.getTitle()+", Status: "+post.getStatus());
                }
                allPosts.addAll(objects);
                adapter.notifyDataSetChanged();
            }
        });

    }
}