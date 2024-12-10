package dto;

import java.util.Arrays;

public class Orders {
    private String _id;
    private String[] ingredients;
    private String status;
    private String name;
    private String createdAt;
    private String updatedAt;
    private int number;

    public Orders(int number, String updatedAt, String createdAt, String name, String status, String[] ingredients, String _id) {
        this.number = number;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.name = name;
        this.status = status;
        this.ingredients = ingredients;
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getNumber() {
        return number;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "_id='" + _id + '\'' +
                ", ingredients=" + Arrays.toString(ingredients) +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", number=" + number +
                '}';
    }
}
