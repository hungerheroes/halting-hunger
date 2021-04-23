package com.example.haltinghunger;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseUser;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);   
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.Logout){
            ParseUser.logOut();
            ParseUser currentUser = ParseUser.getCurrentUser();
            Intent i =new Intent(this,LoginActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
