package com.example.haltinghunger.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.haltinghunger.FoodPost;
import com.example.haltinghunger.FoodPostsAdapter;
import com.example.haltinghunger.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class ViewPostFragment extends Fragment {
    public static final String TAG = "ViewPostFrag";
    private Button filterBtn;
    private Button searchBtn;
    private EditText etZipcode;
    private EditText etItemSearch;
    private RecyclerView rvDisplayPosts;
    private FoodPostsAdapter adapter;
    private List<FoodPost> allPosts;

    public ViewPostFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
//    public static ViewPostFragment newInstance(String param1, String param2) {
//        ViewPostFragment fragment = new ViewPostFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        filterBtn = view.findViewById(R.id.btnFilter);
        searchBtn = view.findViewById(R.id.btnSearch);
        etZipcode = view.findViewById(R.id.etZipcode);
        etItemSearch = view.findViewById(R.id.etItemSearch);
        rvDisplayPosts = view.findViewById(R.id.rvDisplayPosts);

        allPosts = new ArrayList<>();
        adapter = new FoodPostsAdapter(getContext(), allPosts);

        rvDisplayPosts.setAdapter(adapter);
        rvDisplayPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etZipcode.getText().toString().trim().length() > 0) {
                    final Integer zipcode = Integer.parseInt(etZipcode.getText().toString());
                    Log.i("Filter btn click", "Zip " + zipcode);
                    queryPosts(zipcode);
                } else {
                    queryPosts();
                }
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etItemSearch.getText().toString().trim().length() > 0) {
                    final String searchItem = etItemSearch.getText().toString();
                    Log.i("Filter btn click", "search for " + searchItem);
                    queryPosts(searchItem);
                } else {
                    queryPosts();
                }
            }
        });
        queryPosts();
    }

    private void queryPosts() {
        ParseQuery<FoodPost> query = ParseQuery.getQuery(FoodPost.class);
        query.include(FoodPost.KEY_DONOR);
        query.findInBackground(new FindCallback<FoodPost>() {
            @Override
            public void done(List<FoodPost> FoodPosts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (FoodPost FoodPost : FoodPosts) {
                    Log.i(TAG, "ANS!: " + FoodPost.getTitle() + "// " + FoodPost.getZipCode() + "// " + FoodPost.getDonor().getUsername());
                }
                allPosts.clear();
                allPosts.addAll(FoodPosts);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void queryPosts(Integer zipcode) {
        ParseQuery<FoodPost> query = ParseQuery.getQuery(FoodPost.class);
        query.include(FoodPost.KEY_DONOR);
        query.whereEqualTo("zipcode", zipcode);
        query.findInBackground(new FindCallback<FoodPost>() {
            @Override
            public void done(List<FoodPost> FoodPosts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (FoodPost FoodPost : FoodPosts) {
                    Log.i(TAG, "ANS!: " + FoodPost.getTitle() + "// " + FoodPost.getZipCode() + "// " + FoodPost.getDonor().getUsername());
                }
                allPosts.clear();
                allPosts.addAll(FoodPosts);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void queryPosts(String searchItem) {
        ParseQuery<FoodPost> query = ParseQuery.getQuery(FoodPost.class);
        query.include(FoodPost.KEY_DONOR);
//        query.whereContains("details", searchItem);
        query.whereMatches("details", "(" + searchItem + ")", "i");
        query.findInBackground(new FindCallback<FoodPost>() {
            @Override
            public void done(List<FoodPost> FoodPosts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (FoodPost FoodPost : FoodPosts) {
                    Log.i(TAG, "ANS!: " + FoodPost.getTitle() + "// " + FoodPost.getZipCode() + "// " + FoodPost.getDonor().getUsername());
                }
                allPosts.clear();
                allPosts.addAll(FoodPosts);
                adapter.notifyDataSetChanged();
            }
        });
    }

}