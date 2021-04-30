package com.example.haltinghunger;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Main";
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 42;
    private EditText etTitle;
    private EditText etDetails;
    private EditText etQuantity;
    private CheckBox chNVeg;
    private CheckBox chHomemade;
    private EditText etLocation;
    private EditText etZipCode;
    private Button btnSubmit;
    private ImageView etPostImage;
    private Button btnUploadImg;
    private File photoFile;
    public String photoFileName = "photo.jpg";

    public EditText etMonth;
    public EditText etMonth1;
    public EditText etDate;
    public EditText etDate1;
    public EditText etYear;
    public EditText etYear1;
    public EditText etHour;
    public EditText etHour1;
    public EditText etMin;
    public EditText etMin1;
    public EditText etAmPm;
    public EditText etAmPm1;

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
        etPostImage = findViewById(R.id.etPostImage);
        btnUploadImg = findViewById(R.id.btnUploadImg);

        etMonth=findViewById(R.id.etMonth);
        etMonth1 = findViewById(R.id.etMonth1);
        etDate = findViewById(R.id.etDate);
        etDate1 = findViewById(R.id.etDate1);
        etYear = findViewById(R.id.etYear);
        etYear1 = findViewById(R.id.etYear1);
        etHour = findViewById(R.id.etHour);
        etHour1 = findViewById(R.id.etHour1);
        etMin = findViewById(R.id.etMin);
        etMin1 = findViewById(R.id.etMin1);
        etAmPm = findViewById(R.id.etAmPm);
        etAmPm1=findViewById(R.id.etAmPm1);

        btnUploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCamera();
            }
        });
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

                String start_date=(etMonth.getText().toString())+"/"+(etDate.getText().toString())+"/"+(etYear.getText().toString());
                String start_time=(etHour.getText().toString())+":"+(etMin.getText().toString())+" "+(etAmPm.getText().toString());
                String end_date=(etMonth1.getText().toString())+"/"+(etDate1.getText().toString())+"/"+(etYear1.getText().toString());
                String end_time=(etHour1.getText().toString())+":"+(etMin1.getText().toString())+" "+(etAmPm1.getText().toString());

                Boolean nv = false, hm = false;
                if (chNVeg.isChecked()) {
                    nv = true;
                }
                if (chHomemade.isChecked()) {
                    hm = true;
                }
                String location = etLocation.getText().toString();
                Integer zip = Integer.valueOf(etZipCode.getText().toString());
                if(photoFile == null || etPostImage.getDrawable() == null){
                    Toast.makeText(MainActivity.this, "No image", Toast.LENGTH_SHORT).show();
                    return;
                }
                String status="Waiting for confirmation";
                savePost(currentUser, title, details, quantity, nv, hm, location, zip,photoFile, start_date,start_time,end_date,end_time,status);
            }
        });
    }

    private void launchCamera() {
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(MainActivity.this, "com.codepath.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // by this point we have the camera photo on disk
                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                // RESIZE BITMAP, see section below
                // Load the taken image into a preview

                etPostImage.setImageBitmap(takenImage);
            } else { // Result was a failure
                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Returns the File for a photo stored on disk given the fileName
    public File getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        return  new File(mediaStorageDir.getPath() + File.separator + fileName);


    }
    private void savePost(ParseUser currentUser, String title, String details, String quantity, Boolean nv, Boolean hm, String location, Integer zip, File photoFile, String start_date, String start_time, String end_date, String end_time, String status) {
        FoodPost fp = new FoodPost();
        fp.setDonor(currentUser);
        fp.setTitle(title);
        fp.setDetails(details);
        fp.setQuantity(quantity);
        fp.setNV(nv);
        fp.setHM(hm);
        fp.setLocation(location);
        fp.setZipCode(zip);
        fp.setImage(new ParseFile(photoFile));
        fp.setStartDate(start_date);
        fp.setStartTime(start_time);
        fp.setEndDate(end_date);
        fp.setEndTime(end_time);
        fp.setStatus(status);
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
                etPostImage.setImageResource(0);
                etMonth.setText("");
                etMonth1.setText("");
                etDate.setText("");
                etDate1.setText("");
                etYear.setText("");
                etYear1.setText("");
                etHour.setText("");
                etHour1.setText("");
                etMin.setText("");
                etMin1.setText("");
                etAmPm.setText("");
                etAmPm1.setText("");
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
