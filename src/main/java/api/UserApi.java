package api;

import dto.LoginUser;
import dto.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class UserApi {

    @Step
    //создание пользователя
    public static Response createUser(User user){
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when().log().all()
                .post("/api/auth/register");
        return response;
    }
    @Step
    //удаление пользователя
    public static void deleteUser(String accessToken){
        given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when().log().all()
                .delete("/api/auth/user")
                .then().log().all()
                .statusCode(SC_OK);
    }
    @Step
    //авторизация пользователя
    public static Response loginUser(LoginUser credentials){
        Response response2 = given()
                .header("Content-type", "application/json")
                .body(credentials)
                .when().log().all()
                .post("/api/auth/login");
        return response2;
    }


    @Step
    //обновление информации о пользователе
    public static Response updateUser(String accessToken, LoginUser credentials){
        Response response3 = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when().log().all()
                .patch("/api/auth/user");
        return response3;

    }

}
