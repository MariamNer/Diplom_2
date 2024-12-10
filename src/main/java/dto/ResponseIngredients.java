package dto;

import java.util.Arrays;

public class ResponseIngredients {
    private boolean success;
    private Ingredient[] data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Ingredient[] getData() {
        return data;
    }

    public void setData(Ingredient[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseIngredients{" +
                "success=" + success +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
