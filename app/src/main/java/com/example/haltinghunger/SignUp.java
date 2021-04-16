package com.example.haltinghunger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity {
    EditText registerUserName;
    EditText registerPassword;
    EditText  userPhone;
    EditText userAddress;
    EditText userEmail;
    RadioGroup userType;
    RadioButton typeBtn;
    EditText userOrg;
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        registerUserName = findViewById(R.id.rgUsername);
        registerPassword = findViewById(R.id.rgPassword);
        userPhone = findViewById(R.id.etPhone);
        userAddress = findViewById(R.id.etAddress);
        userEmail = findViewById(R.id.etEmail);
        userOrg = findViewById(R.id.etOrganization);
        userType = (RadioGroup)findViewById(R.id.roleType);
        signUpBtn = (Button)findViewById(R.id.btnRegister);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int typeId = userType.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) userType.findViewById(typeId);
                    String selectedType = (String) radioButton.getText();
                    Integer phoneNumber = Integer.parseInt(userPhone.getText().toString());
                    ParseUser user = new ParseUser();
                    user.setUsername(registerUserName.getText().toString());
                    user.setPassword(registerPassword.getText().toString());
                    user.setEmail(userEmail.getText().toString());
                    user.put("phone",phoneNumber);
                    user.put("address",userAddress.getText().toString());
                    user.put("organization",userOrg.getText().toString());
                    user.put("type",selectedType);
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e!=null){
                                Toast.makeText(getApplicationContext(),"Issue with Sign Up",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            goToLoginPage();
                            Toast.makeText(getApplicationContext(),"Success!",Toast.LENGTH_SHORT).show();

                        }
                    });

            }
        });

    }

    private void goToLoginPage() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}