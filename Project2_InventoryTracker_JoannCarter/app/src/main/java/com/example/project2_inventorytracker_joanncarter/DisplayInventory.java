package com.example.project2_inventorytracker_joanncarter;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DisplayInventory extends AppCompatActivity {
    FloatingActionButton buttonAddItem;
    DatabaseHelper db;
    RecyclerView recyclerView;
    ArrayList<String> itemName;
    ArrayList<Integer>itemCount;
    RecyclerAdapter adapter;
    InventoryRow row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_display);
        buttonAddItem = findViewById(R.id.buttonAddItem);
        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), AddInventory.class);
                startActivity(intent);
            }

        });
        db = new DatabaseHelper(this);
        itemName = new ArrayList<>();   // list for names
        itemCount = new ArrayList<>();  // list for item count
        recyclerView = findViewById(R.id.recyclerView);
        listData();  // put values in lists
        adapter = new RecyclerAdapter(itemName, itemCount);  // create adapter for display grid
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }



    void listData(){    // add data to lists for recycler
        Cursor cursor = db.getUserData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "NO ITEMS IN INVENTORY!", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                itemName.add(cursor.getString(0));
                itemCount.add(cursor.getInt(1));
            }
        }
    }

}
