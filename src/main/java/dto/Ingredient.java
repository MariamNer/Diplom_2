package dto;

public class Ingredient {
    private String _id;

    public Ingredient(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "_id='" + _id + '\'' +
                '}';
    }
}
