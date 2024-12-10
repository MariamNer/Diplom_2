package user;

import api.Diplom_2_utils;
import dto.LoginUser;
import dto.User;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.After;
import api.UserApi;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.equalTo;

public class LoginUserTest extends BestTest {

    private String accessToken;

    @Test
    @Description("Логин пользователя")
    @DisplayName("Логин под существующим пользователем")
    @Step
    public void loginExistingUser() {
        User user = Diplom_2_utils.getRandom();
        Response response = UserApi.createUser(user);
        response.then().log().all();
        Response response1 = UserApi.loginUser(new LoginUser(user.email, user.password));
        response1.then().log().all()
                .assertThat().body("success", equalTo(true))
                .statusCode(SC_OK);
    }

    @Test
    @Description("Логин пользователя")
    @DisplayName("Логин с неверным логином и паролем")
    @Step
    public void loginIncorrectLoginAndPassword() {
        User user = Diplom_2_utils.getRandom();
        Response response = UserApi.createUser(user);
        response.then().log().all();
        Response response1 = UserApi.loginUser(new LoginUser(user.email +"a", user.password + "a"));
        response1.then().log().all()
                .assertThat().body("success", equalTo(false))
                .statusCode(SC_UNAUTHORIZED);
    }




    @After
    public void tearDown(){
        try{
            UserApi.deleteUser(accessToken);
        } catch(java.lang.IllegalArgumentException error) {
        }
    }

}
