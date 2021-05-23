package com.mahad.task.model;

public class Fav_Model {

    String imageURL_fav;
    String glassName_fav;
    String drinkDetail_fav;
    boolean isAlcoholic_fav;


    public Fav_Model(String imageURL_fav, String glassName_fav, String drinkDetail_fav, boolean isAlcoholic_fav) {
        this.imageURL_fav = imageURL_fav;
        this.glassName_fav = glassName_fav;
        this.drinkDetail_fav = drinkDetail_fav;
        this.isAlcoholic_fav = isAlcoholic_fav;
    }

    public String getImageURL_fav() {
        return imageURL_fav;
    }

    public String getGlassName_fav() {
        return glassName_fav;
    }

    public String getDrinkDetail_fav() {
        return drinkDetail_fav;
    }

    public boolean isAlcoholic_fav() {
        return isAlcoholic_fav;
    }
}
