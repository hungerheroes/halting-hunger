package com.example.haltinghunger;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeBeneficiary extends AppCompatActivity {
    public static final String TAG = "HomeBeneficiary";
    RecyclerView rvPosts;
    List<Post> posts;
    PostsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.beneficiary_home);
    }
}
