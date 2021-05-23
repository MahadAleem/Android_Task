package com.mahad.task.roomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourite")
public class Fav_Entities {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "image_url")
    String imageURL_e;
    @ColumnInfo(name = "glass_name")
    String glassName_e;
    @ColumnInfo(name = "drink_detail")
    String drinkDetail_e;
    @ColumnInfo(name = "is_alcoholic")
    boolean isAlcoholic_e;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageURL_e() {
        return imageURL_e;
    }

    public void setImageURL_e(String imageURL_e) {
        this.imageURL_e = imageURL_e;
    }

    public String getGlassName_e() {
        return glassName_e;
    }

    public void setGlassName_e(String glassName_e) {
        this.glassName_e = glassName_e;
    }

    public String getDrinkDetail_e() {
        return drinkDetail_e;
    }

    public void setDrinkDetail_e(String drinkDetail_e) {
        this.drinkDetail_e = drinkDetail_e;
    }

    public boolean isAlcoholic_e() {
        return isAlcoholic_e;
    }

    public void setAlcoholic_e(boolean alcoholic_e) {
        isAlcoholic_e = alcoholic_e;
    }
}
