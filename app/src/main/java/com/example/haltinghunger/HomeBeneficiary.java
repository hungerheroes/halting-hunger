package com.example.haltinghunger;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseUser;
import com.example.haltinghunger.fragments.BeneficiaryStatusFragment;
import com.example.haltinghunger.fragments.ViewPostFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomeBeneficiary extends AppCompatActivity {
    public static final String TAG = "HomeBeneficiary";
    RecyclerView rvPosts;
    List<Post> posts;
    PostsAdapter adapter;
    private BottomNavigationView bottomNavigationView;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.beneficiary_home);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Toast.makeText(HomeBeneficiary.this,"Home!",Toast.LENGTH_SHORT).show();
                        fragment = new ViewPostFragment();
                        break;
                    case R.id.action_volunteerStatus:
                    default:
                        Toast.makeText(HomeBeneficiary.this,"Status!",Toast.LENGTH_SHORT).show();
                        fragment = new BeneficiaryStatusFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);
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
