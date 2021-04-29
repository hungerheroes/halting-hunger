package com.example.haltinghunger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    RadioGroup loginUserType;
    RadioButton loginTypeBtn;
    Button signUpBtn;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        loginUserType = findViewById(R.id.loginUserType);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onclick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                int loginUserTypeId = loginUserType.getCheckedRadioButtonId();
                loginTypeBtn = loginUserType.findViewById(loginUserTypeId);
                String loginSelectedType = (String) loginTypeBtn.getText();

                loginUser(username, password, loginSelectedType);
            }
        });

        signUpBtn = (Button) findViewById(R.id.btnSignUp);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });
    }

    private void loginUser(String username, String password, String loginSelectedType) {
        Log.i(TAG, "Login occurs " + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {

            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Parse user login error : " + e);
                    Toast.makeText(LoginActivity.this, "Issue with login - please retry!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (loginSelectedType.equals("Donor")) {
                    gotoHomeOfUser();
                } else if (loginSelectedType.equals("Volunteer")) {
                    goToBeneficiaryHome();
                }

//                ParseUser.getCurrentUser().UserClass.gettype();
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToBeneficiaryHome() {
        Intent i = new Intent(this, HomeBeneficiary.class);
        startActivity(i);
        finish();
    }

    private void gotoHomeOfUser() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void openRegisterActivity() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

}