package com.example.project2_inventorytracker_joanncarter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<String> inventoryNameList;
    private ArrayList<Integer> inventoryCountList;
    public RecyclerAdapter(ArrayList<String> inventoryNameList, ArrayList<Integer> inventoryCountList){
        this.inventoryNameList = inventoryNameList;
        this.inventoryCountList = inventoryCountList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameText;
        private TextView countText;

        public MyViewHolder(final View view){
            super(view);
            nameText = view.findViewById(R.id.itemName);   //display values
            countText = view.findViewById(R.id.itemCount);
        }
    }
    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {  // populate adapter
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {   // set text in row for recycler
        String name = inventoryNameList.get(position);
        Integer count = inventoryCountList.get(position);
        holder.nameText.setText(name);
        holder.countText.setText(count);

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
