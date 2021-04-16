package com.example.haltinghunger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUpBtn = (Button) findViewById(R.id.btnSignUp);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });
    }

    private void openRegisterActivity() {
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }

}