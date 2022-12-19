package com.example.project2_inventorytracker_joanncarter;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    EditText password, confirmPassword,username;
    Button buttonCreateAccount;
    DatabaseHelper DB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

         username =  findViewById(R.id.usernameInput);   //display values
         password = findViewById(R.id.passwordInput);
         confirmPassword = findViewById(R.id.confirmPasswordInput);
         buttonCreateAccount =  findViewById(R.id.submitSignUp);



         buttonCreateAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String userLogin = username.getText().toString();    // get user entries for username, and password
                String pw = password.getText().toString();
                String confirmPW = confirmPassword.getText().toString();

                if (TextUtils.isEmpty(userLogin) || TextUtils.isEmpty(pw) || TextUtils.isEmpty(confirmPW)) {   // validate all entries are completed
                    Toast.makeText(SignUp.this, "Missing Data...", Toast.LENGTH_SHORT).show();
                } else {
                    if (pw.equals(confirmPW)) {   //validate passwords match
                        Boolean validateUserExists = DB.validateUserExists(userLogin);
                        if (validateUserExists == false) {   // validate the user doesnt exist already
                            Boolean insert = DB.insertUserData(userLogin, pw);
                            if (insert == true) {
                                Toast.makeText(SignUp.this, "Success!", Toast.LENGTH_SHORT).show();  //success message
                                Intent intent = new Intent(getApplicationContext(), DisplayInventory.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUp.this, "Fail!", Toast.LENGTH_SHORT).show();  // fail message
                            }
                        } else {
                            Toast.makeText(SignUp.this, "This account already exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUp.this, "Passwords DO NOT match", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
}