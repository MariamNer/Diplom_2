package api;

import dto.Order;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class IngredientsApi {
    @Step
    //Получение данных об ингредиентах
    public static Response getIngredients() {
        Response response = given()
                .header("Content-type", "application/json")
                .when().log().all()
                .get("/api/ingredients");
        return response;
    }
}
