package com.example.project2_inventorytracker_joanncarter;

public class InventoryRow {
    private String itemName;
    private Integer itemCount;
    public InventoryRow(String itemName, Integer itemCount){   // create a row for the recycler view to display the data in
        this.itemName = itemName;
        this.itemCount = itemCount;
    }

    public String getItemName(){
        return itemName;
    }
    public Integer getItemCount(){
        return itemCount;
    }

    public void setInventoryInfo(String itemName, Integer itemCount){
        this.itemName = itemName;
        this.itemCount = itemCount;
    }

}
