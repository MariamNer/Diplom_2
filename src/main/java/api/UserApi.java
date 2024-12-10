package api;

import dto.LoginUser;
import dto.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.oneOf;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
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
                .statusCode((oneOf(200, 202)));
    }
    @Step
    //авторизация пользователя
    public static Response loginUser(LoginUser loginUser){
        Response response2 = given()
                .header("Content-type", "application/json")
                .body(loginUser)
                .when().log().all()
                .post("/api/auth/login");
        return response2;
    }


    @Step
    //обновление информации о пользователе
    public static Response updateUser(String accessToken, User user){
        Response response3 = given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .body(user)
                .when().log().all()
                .patch("/api/auth/user");
        return response3;

    }

}
