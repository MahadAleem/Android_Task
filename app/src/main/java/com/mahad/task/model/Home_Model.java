package com.mahad.task.model;

public class Home_Model {

    String imageURL_m;
    String glassName_m;
    String drinkDetail_m;
    boolean isAlcoholic;

    public Home_Model(String imageURL_m, String glassName_m, String drinkDetail_m, boolean isAlcoholic) {
        this.imageURL_m = imageURL_m;
        this.glassName_m = glassName_m;
        this.drinkDetail_m = drinkDetail_m;
        this.isAlcoholic = isAlcoholic;
    }

    public String getImageURL_m() {
        return imageURL_m;
    }

    public String getGlassName_m() {
        return glassName_m;
    }

    public String getDrinkDetail_m() {
        return drinkDetail_m;
    }

    public boolean isAlcoholic() {
        return isAlcoholic;
    }
}
