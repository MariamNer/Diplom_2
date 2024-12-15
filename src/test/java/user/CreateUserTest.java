package user;

import api.Diplom_2_utils;
import api.UserApi;
import dto.ResponseUser;
import dto.User;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends BestTest {

    private String accessToken;

    @Test
    @Description("Создание пользователя")
    @DisplayName("Создание уникального пользователя")
    @Step
    public void createUniqueUser(){
        User user = Diplom_2_utils.getRandom();
        Response response = UserApi.createUser(user);
        ResponseUser responseUser = response.as(ResponseUser.class);
        accessToken = responseUser.accessToken;
        response.then().log().all()
                .assertThat().body("success", equalTo(true))
                .statusCode(SC_OK);

    }

    @Test
    @Description("Создание пользователя")
    @DisplayName("Создать пользователя, который уже зарегистрирован")
    @Step
    public void beCreateUser(){
        User user = Diplom_2_utils.getRandom();
        Response response = UserApi.createUser(user);
        ResponseUser responseUser = response.as(ResponseUser.class);
        accessToken = responseUser.accessToken;
        response.then().log().all()
                .assertThat().body("success", equalTo(true))
                .statusCode(SC_OK);

        Response response1 = UserApi.createUser(user);
        response1.then().log().all()
                .assertThat().body("message", equalTo("User already exists"))
                .statusCode(SC_FORBIDDEN);
    }

    @Test
    @Description("Создание пользователя")
    @DisplayName("Создать пользователя без email")
    @Step
    public void createUserWithoutEmail(){
        User user = Diplom_2_utils.getRandom();
        User userWithoutEmail = new User("", user.password, user.name);
        Response response = UserApi.createUser(userWithoutEmail);
        response.then().log().all()
                .assertThat().body("success", equalTo(false))
                .and()
                .assertThat().body("message", equalTo("Email, password and name are required fields"))
                .statusCode(SC_FORBIDDEN);

    }



    @Test
    @Description("Создание пользователя")
    @DisplayName("Создать пользователя без password")
    @Step
    public void createUserWithoutPassword(){
        User user = Diplom_2_utils.getRandom();
        User userWithoutEmail = new User(user.email, "", user.name);
        Response response = UserApi.createUser(userWithoutEmail);
        response.then().log().all()
                .assertThat().body("success", equalTo(false))
                .and()
                .assertThat().body("message", equalTo("Email, password and name are required fields"))
                .statusCode(SC_FORBIDDEN);

    }


    @Test
    @Description("Создание пользователя")
    @DisplayName("Создать пользователя без name")
    @Step
    public void createUserWithoutName(){
        User user = Diplom_2_utils.getRandom();
        User userWithoutEmail = new User(user.email, user.password, "");
        Response response = UserApi.createUser(userWithoutEmail);
        response.then().log().all()
                .assertThat().body("success", equalTo(false))
                .and()
                .assertThat().body("message", equalTo("Email, password and name are required fields"))
                .statusCode(SC_FORBIDDEN);
    }



    @After
    public void tearDown(){
        try{
            UserApi.deleteUser(accessToken);
        } catch(java.lang.IllegalArgumentException error) {
        }
    }

}
