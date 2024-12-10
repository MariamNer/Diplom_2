package api;

import dto.Order;
import dto.Orders;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderApi {

    @Step
    //создание заказа с авторизацией
    public static Response createOrderWithAuth(String accessToken, Order order) {
         Response response = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .and()
                .body(order)
                .when().log().all()
                .post("/api/orders/");
         return response;
    }

    @Step
    //создание заказа без авторизацией
    public static Response createOrderWithoutAuth(Order order) {
        Response response1 = given()
                .header("Content-type", "application/json")
                .body(order)
                .when().log().all()
                .post("/api/orders/");
        return response1;
    }



    @Step
    //Получение заказов конкретного пользователя
    public Response getOrderForUserWithAuth(String accessToken) {
        Response response3 = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .and()
                .when().log().all()
                .get("/api/orders/");
        return response3;
    }

    @Step
    //Получение заказов без токена
    public Response getOrderWithoutAuth() {
        Response response4 = given()
                .header("Content-type", "application/json")
                .when().log().all()
                .get("/api/orders/");
        return response4;
    }

//    @Step
//    //Десериализуем заказы конкретного пользователя
//    public Orders orders (String accessToken) {
//        Response response = given()
//                .header("Content-type", "application/json")
//                .header("Authorization", accessToken)
//                .and()
//                .body().as(Orders.class)
//                .when().log().all()
//                .get("api/orders/");
//        return response;
//  }


}
