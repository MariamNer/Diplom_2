package dto;

import java.util.Arrays;

public class Order {

    public String[] ingredients;

    public Order(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

}
