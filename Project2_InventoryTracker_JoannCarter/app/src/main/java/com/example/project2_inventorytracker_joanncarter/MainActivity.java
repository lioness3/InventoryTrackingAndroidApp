package com.example.project2_inventorytracker_joanncarter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText login, password;
    DatabaseHelper DB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        Button buttonCreateAccount = (Button)findViewById(R.id.buttonCreateAccount);
        TextView titleLogIn = (TextView) findViewById(R.id.titleLogIn);
        TextView titleCreateAccount = (TextView) findViewById(R.id.titleCreateAccount);
        login = (EditText) findViewById(R.id.login);
        password = (EditText)findViewById(R.id.password);

// send a messagre to the user asking for permission to send them a text message when the inventory item is low
           if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)){
                new AlertDialog.Builder(this)
                        .setTitle("Permission to Text You")
                        .setMessage("Would you like to recieve a text when an inventory item is at 0?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {   // user agrees
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
                            }
                        })
                        .setNegativeButton("NOPE", new DialogInterface.OnClickListener() {   //user disagrees
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
           }else{
               ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
           }

// Clicking Login
        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String username = login.getText().toString();
                String pw = password.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pw)) {    // validate entries are completed
                    Toast.makeText(MainActivity.this, "Missing Data...", Toast.LENGTH_SHORT).show();  // error message
                } else {
                    Boolean validateUserPassword = DB.validateUserPassword(username, pw);
                    if (validateUserPassword == true) {
                        Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();  // successs message
                        Intent intent = new Intent(getApplicationContext(), DisplayInventory.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Fail!", Toast.LENGTH_SHORT).show();  // error message
                    }
                }
            }
        });
// Clicking Create Account
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);  // navigate to sign up page on click of sign up
                startActivity(intent);


            }
        });


    }
}
