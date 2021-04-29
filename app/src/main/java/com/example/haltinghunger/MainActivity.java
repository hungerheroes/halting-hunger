package com.example.haltinghunger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Main";
    private EditText etTitle;
    private EditText etDetails;
    private EditText etQuantity;
    private CheckBox chNVeg;
    private CheckBox chHomemade;
    private EditText etLocation;
    private EditText etZipCode;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post);

        etTitle = findViewById(R.id.etTitle);
        etDetails = findViewById(R.id.etDetails);
        etQuantity = findViewById(R.id.etQuantity);
        chNVeg = findViewById(R.id.chNVeg);
        chHomemade = findViewById(R.id.chHomemade);
        etLocation = findViewById(R.id.etLocation);
        etZipCode = findViewById(R.id.etZipCode);
        btnSubmit = findViewById(R.id.btnSubmit);

//        queryPosts();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser currentUser = ParseUser.getCurrentUser();
                String title = etTitle.getText().toString();
                if (title.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                String details = etDetails.getText().toString();
                String quantity = etQuantity.getText().toString();
                Boolean nv = false, hm = false;
                if (chNVeg.isChecked()) {
                    nv = true;
                }
                if (chHomemade.isChecked()) {
                    hm = true;
                }
                String location = etLocation.getText().toString();
                Integer zip = Integer.valueOf(etZipCode.getText().toString());
                savePost(currentUser, title, details, quantity, nv, hm, location, zip);
            }
        });
    }

    private void savePost(ParseUser currentUser, String title, String details, String quantity, Boolean nv, Boolean hm, String location, Integer zip) {
        FoodPost fp = new FoodPost();
        fp.setDonor(currentUser);
        fp.setTitle(title);
        fp.setDetails(details);
        fp.setQuantity(quantity);
        fp.setNV(nv);
        fp.setHM(hm);
        fp.setLocation(location);
        fp.setZipCode(zip);
        fp.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error saving post", e);
                    Toast.makeText(MainActivity.this, "Error saving post", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "Post saved successfully");
                etTitle.setText("");
                etDetails.setText("");
                etQuantity.setText("");
                chNVeg.setChecked(false);
                chHomemade.setChecked(false);
                etLocation.setText("");
                etZipCode.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.Logout) {
            ParseUser.logOut();
            ParseUser currentUser = ParseUser.getCurrentUser();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
