package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    DatabaseHelper db;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db = new DatabaseHelper(this);
        Button createAccountButton = findViewById(R.id.createAccountButton);
        EditText fullnameInput =findViewById(R.id.nameInput);
        EditText usernameInput =findViewById(R.id.signupUsernameInput);
        EditText passwordInput =findViewById(R.id.passwordSignUpInput);
        EditText confirmPasswordInput =findViewById(R.id.cPasswordInput);



        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullname = fullnameInput.getText().toString();
                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();
                String confirmPassword = confirmPasswordInput.getText().toString();
                if (password.equals(confirmPassword))
                {
                    User user = new User(fullname,username, password);
                    long result = db.insertUser(user);
                    if (result > 0)
                    {
                        Toast.makeText(SignUpActivity.this, "Successfully", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(SignUpActivity.this, "Unsuccessfully", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}