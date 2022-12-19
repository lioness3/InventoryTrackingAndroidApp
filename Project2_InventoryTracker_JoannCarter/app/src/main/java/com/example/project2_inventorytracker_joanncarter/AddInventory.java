package com.example.project2_inventorytracker_joanncarter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddInventory extends AppCompatActivity {
    EditText nameInput, countInput;
    Button addNewItem;
    DatabaseHelper DB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinventory);  // display values
        nameInput = findViewById(R.id.itemNameInput);
        countInput = findViewById(R.id.itemCountInput);
        addNewItem = findViewById(R.id.buttonAddNewItem);

        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString();  // get values from input
                String count = countInput.getText().toString();
                int numberCount = Integer.parseInt(count);
                DB.insertInventoryData(name,numberCount );  // insert values in database
                Intent intent = new Intent(getApplicationContext(), DisplayInventory.class);  //navigate to diplay data
                startActivity(intent);
            }
        });
    }
}
